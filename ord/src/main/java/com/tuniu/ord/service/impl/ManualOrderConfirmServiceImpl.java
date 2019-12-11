package com.tuniu.ord.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.operation.framework.base.time.DateFormatUtils;
import com.tuniu.operation.framework.base.util.SystemConstants;
import com.tuniu.ord.common.constant.Constants;
import com.tuniu.ord.common.task.ManualTaskVo;
import com.tuniu.ord.common.task.OrderTaskEnum;
import com.tuniu.ord.common.task.TaskFlow;
import com.tuniu.ord.common.util.BaseDomainUtil;
import com.tuniu.ord.common.util.CollectionUtils;
import com.tuniu.ord.common.util.ExternalRestUtil;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.common.util.SessionUtil;
import com.tuniu.ord.common.util.TspCommonUtil;
import com.tuniu.ord.common.util.UserSessionUtil;
import com.tuniu.ord.core.exception.SaaSSystemException;
import com.tuniu.ord.core.init.SystemInitParameter;
import com.tuniu.ord.domain.ManualOrder;
import com.tuniu.ord.domain.ManualOrderOccupy;
import com.tuniu.ord.domain.ManualTourist;
import com.tuniu.ord.domain.ManualTouristCertificate;
import com.tuniu.ord.domain.OrdDealOrder;
import com.tuniu.ord.domain.OrdPeopleTourist;
import com.tuniu.ord.domain.OrdSalesOrder;
import com.tuniu.ord.enums.ChannelClassify;
import com.tuniu.ord.enums.NewOrderState;
import com.tuniu.ord.enums.OrderStateEnum;
import com.tuniu.ord.persistence.ManualOrderMapper;
import com.tuniu.ord.persistence.ManualOrderOccupyMapper;
import com.tuniu.ord.persistence.ManualTouristCertificateMapper;
import com.tuniu.ord.persistence.ManualTouristMapper;
import com.tuniu.ord.persistence.OrdDealOrderMapper;
import com.tuniu.ord.persistence.OrdPeopleTouristMapper;
import com.tuniu.ord.persistence.OrdSalesOrderMapper;
import com.tuniu.ord.proxy.GRPProxy;
import com.tuniu.ord.proxy.StockProxy;
import com.tuniu.ord.service.IOrderIdServiceClient;
import com.tuniu.ord.service.ManualOrderConfirmService;
import com.tuniu.ord.vo.ConsumerVo;
import com.tuniu.ord.vo.DProductStock;
import com.tuniu.ord.vo.DepartDateStock;
import com.tuniu.ord.vo.ProductStockVo;
import com.tuniu.ord.vo.ResponseVo;
import com.tuniu.ord.vo.SaleVo;
import com.tuniu.ord.vo.SalesConfirmVo;
import com.tuniu.ord.vo.StockOccupyInfoOutput;
import com.tuniu.ord.vo.external.MemberInfoVo;

@Service
public class ManualOrderConfirmServiceImpl implements ManualOrderConfirmService {

    private static Logger LOGGER = LoggerFactory.getLogger(ManualOrderConfirmServiceImpl.class);

    @Resource
    private StockProxy stockProxy;

    @Resource
    private GRPProxy gRPProxy;

    @Resource
    private TaskFlow taskFlow;

    @Resource
    private ManualOrderOccupyMapper manualOrderOccupyMapper;

    @Resource
    private OrdSalesOrderMapper ordSalesOrderMapper;

    @Resource
    private OrdDealOrderMapper ordDealOrderMapper;

    @Resource
    private ManualTouristMapper manualTouristMapper;

    @Resource
    private OrdPeopleTouristMapper ordPeopleTouristMapper;

    @Resource
    private ManualOrderMapper manualOrderMapper;

    @Resource
    private ManualTouristCertificateMapper certificateMapper;

    @Resource
    private IOrderIdServiceClient iOrderIdServiceImplClient;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ResponseVo confirm(Integer orderOccupyId) {
        ResponseVo responseVo = new ResponseVo();

        ManualOrderOccupy manualOrderOccupy = manualOrderOccupyMapper.selectByPrimaryKey(orderOccupyId);
        if (manualOrderOccupy == null) {
            throw new SaaSSystemException("占位记录不存在");
        }

        ManualTaskVo currentTask = taskFlow.getCurrentTask(manualOrderOccupy.getManualOrderId());
        if (currentTask == null) {
            throw new SaaSSystemException("当前任务节点不存在");
        }
        Integer taskCode = currentTask.getTaskCode();
        if (OrderTaskEnum.CONFIRM.getKey().intValue() != taskCode.intValue()) {
            throw new SaaSSystemException("当前任务节点不是【确认节点】请完成之前的节点流程");
        }

        if (manualOrderOccupy.getStatus().intValue() == 3) {
            throw new SaaSSystemException("占位状态已经为完成确认");
        }

        Integer dOrderId = manualOrderOccupy.getdOrderId();

        OrdDealOrder ordDealOrder = ordDealOrderMapper.selectByOrderId(dOrderId);
        // 订单系统小库存先成功
        int occupyStatus;
        // 更新ord_deal_order 维护确认数量
        try {
            int confirm = manualOrderOccupy.getNumber() + ordDealOrder.getConfirmNum();
            int occupuNum = ordDealOrder.getOccupyNum() - manualOrderOccupy.getNumber();
            OrdDealOrder ordDealOrderUpdate = new OrdDealOrder();
            ordDealOrderUpdate.setConfirmNum(confirm);
            ordDealOrderUpdate.setOccupyNum(occupuNum);
            ordDealOrderUpdate.setId(dOrderId);
            ordDealOrderMapper.updateByPrimaryKeySelective(ordDealOrderUpdate);

            occupyStatus = 3;
        } catch (Exception e) {
            occupyStatus = 4;
        }

        ManualOrderOccupy occupy = new ManualOrderOccupy();
        occupy.setId(orderOccupyId);
        occupy.setStatus(occupyStatus);
        manualOrderOccupyMapper.updateByPrimaryKeySelective(occupy);

        if (occupyStatus == 4) {
            responseVo.setSuccess(false);
            responseVo.setMsg("确认失败");
            return responseVo;
        }

        int ordSalesOrderId = generateSaleOrder(manualOrderOccupy, ordDealOrder);

        try {
            ProductStockVo productStockVo = initSignParam(manualOrderOccupy, dOrderId, ordDealOrder, ordSalesOrderId);
            LOGGER.info("productStockVo:{}", JsonUtil.toString(productStockVo));
            ResponseVo productContractSignResponse = stockProxy.productContractSign(productStockVo);
            if (!productContractSignResponse.isSuccess()) {
                throw new SaaSSystemException(productContractSignResponse.getMsg());
            }
        } catch (Exception e) {

            LOGGER.error("库存签约出库失败，请技术支持", e);
        }
        return responseVo;
    }

    private ProductStockVo initSignParam(ManualOrderOccupy manualOrderOccupy, Integer dOrderId, OrdDealOrder ordDealOrder,
            int ordSalesOrderId) {
        ProductStockVo productStockVo = new ProductStockVo();
        productStockVo.setClientFlag(Constants.CLIENT_FLAG);
        productStockVo.setOrderId(ordSalesOrderId);
        productStockVo.setProductId(ordDealOrder.getProductId());
        productStockVo.setProductName(ordDealOrder.getProductName());
        productStockVo.setSessionId(SessionUtil.createSessionId());
        List<DProductStock> dProducts = new ArrayList<DProductStock>();
        DProductStock dProductStock = new DProductStock();
        dProductStock.setCostCurrencyType(8);
        List<DepartDateStock> departDates = new ArrayList<DepartDateStock>();
        DepartDateStock dateStock = new DepartDateStock();

        List<StockOccupyInfoOutput> queryOccupyInfo = null;
        try {
            queryOccupyInfo = TspCommonUtil.queryOccupyInfo(dOrderId);
        } catch (Exception e) {
            LOGGER.error("根据切位单查询占位记录出错", e);
            throw new SaaSSystemException(e.getMessage());
        }

        List<Integer> outIds = new ArrayList<Integer>();
        for (StockOccupyInfoOutput infoOutput : queryOccupyInfo) {
            if (infoOutput.getLeftNumber().intValue() > 0) {
                outIds.add(infoOutput.getId());
            }
        }
        dateStock.setOutIds(outIds);
        dateStock.setDepartDate(DateFormatUtils.formatDate(ordDealOrder.getDepartDate()));
        dateStock.setAdultNum(manualOrderOccupy.getNumber());
        dateStock.setChildNum(0);
        dateStock.setAdultCost(ordDealOrder.getAdultPrice());
        dateStock.setChildCost(BigDecimal.ZERO);
        dateStock.setBabyCost(BigDecimal.ZERO);
        dateStock.setAdultCostRMB(ordDealOrder.getAdultPrice());
        dateStock.setChildCostRMB(BigDecimal.ZERO);
        dateStock.setBabyCostRMB(BigDecimal.ZERO);
        departDates.add(dateStock);
        dProductStock.setDepartDates(departDates);
        dProductStock.setdProductId(ordDealOrder.getProductId());
        dProductStock.setVendorId(123456);
        dProductStock.setVendorName("tuniu");
        dProducts.add(dProductStock);
        productStockVo.setdProducts(dProducts);
        return productStockVo;
    }

    private int generateSaleOrder(ManualOrderOccupy manualOrderOccupy, OrdDealOrder ordDealOrder) {
        OrdSalesOrder ordSalesOrder = new OrdSalesOrder();
        Long orderNum = iOrderIdServiceImplClient.getOrderNum(Constants.INTERVAL_TYPE_ID);
        ordSalesOrder.setId(orderNum.intValue());
        ordSalesOrder.setOrderId(manualOrderOccupy.getdOrderId());
        ordSalesOrder.setExtOrderId(manualOrderOccupy.getManualOrderId());
        ordSalesOrder.setExtProductId(ordDealOrder.getProductId());
        ordSalesOrder.setExtProductName(ordDealOrder.getProductName());
        ordSalesOrder.setStatusCode(OrderStateEnum.INITIAL.getStatusCode());
        ordSalesOrder.setAdultCount(manualOrderOccupy.getAdultNum());
        ordSalesOrder.setChildCount(manualOrderOccupy.getChildNum());
        ordSalesOrder.setAdultPrice(ordDealOrder.getAdultPrice());
        ordSalesOrder.setChildPrice(ordDealOrder.getChildPrice());
        ordSalesOrder.setStatusCode(OrderStateEnum.CONFIRMED.getStatusCode());
        BaseDomainUtil.setBasePropertyValue(ordSalesOrder);
        ordSalesOrderMapper.insertSelective(ordSalesOrder);
        return orderNum.intValue();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void finishConfirm(Integer manualOrderId) {
        ManualOrder manualOrder = manualOrderMapper.selectByPrimaryKey(manualOrderId);
        if (NewOrderState.CONFIRM.getStatusCode().equals(manualOrder.getStatusCode())) {
            throw new SaaSSystemException("不可重复完成确认");
        }

        int peopleNum = 0;
        // 确保订单全部确认完成
        List<OrdSalesOrder> ordSalesOrders = ordSalesOrderMapper.getSalesInfoByManualOrderId(manualOrderId);
        if (CollectionUtils.isNotEmpty(ordSalesOrders)) {
            for (OrdSalesOrder ordSalesOrder : ordSalesOrders) {
                peopleNum = ordSalesOrder.getAdultCount() + ordSalesOrder.getChildCount() + peopleNum;
                if (!OrderStateEnum.CONFIRMED.getStatusCode().equals(ordSalesOrder.getStatusCode())) {
                    throw new SaaSSystemException("订单【" + manualOrderId + "】存在未确认的销售订单请先确认");
                }
            }
        } else {
            throw new SaaSSystemException("订单【" + manualOrderId + "】没有确认信息");
        }

        SalesConfirmVo salesConfirmVo = new SalesConfirmVo();
        salesConfirmVo.setUid(UserSessionUtil.getUid());
        salesConfirmVo.setNickname(UserSessionUtil.getNickname());
        salesConfirmVo.setSessionId(SessionUtil.createSessionId());
        salesConfirmVo.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
        salesConfirmVo.setToken(Constants.token);

        List<ManualTourist> manualTourists = manualTouristMapper.selectByManualOrderId(manualOrderId);
        if (CollectionUtils.isNotEmpty(manualTourists)) {
            if (peopleNum != manualTourists.size()) {
                throw new SaaSSystemException("订单【" + manualOrderId + "】确认数量和游客人数不一致");
            }
        } else {
            throw new SaaSSystemException("订单【" + manualOrderId + "】对应的游客信息为空");
        }

        List<ManualTourist> adultManualTourists = new ArrayList<ManualTourist>();
        List<ManualTourist> childManualTourists = new ArrayList<ManualTourist>();
        for (ManualTourist manualTourist : manualTourists) {
            if (manualTourist.getTouristType() == 0) {
                adultManualTourists.add(manualTourist);
            } else if (manualTourist.getTouristType() == 1) {
                childManualTourists.add(manualTourist);
            }
        }

        String memberName = "";

        List<SaleVo> sales = new ArrayList<SaleVo>();
        for (OrdSalesOrder ordSalesOrder : ordSalesOrders) {
            List<ManualTourist> selectedPeople = new ArrayList<ManualTourist>();

            Integer adultCount = ordSalesOrder.getAdultCount();
            if (adultCount != null && adultCount > 0) {
                for (int i = 0; i < adultCount; i++) {
                    selectedPeople.add(adultManualTourists.get(0));

                    adultManualTourists.remove(0);
                }
            }

            Integer childCount = ordSalesOrder.getChildCount();
            if (childCount != null && childCount > 0) {
                for (int i = 0; i < childCount; i++) {
                    selectedPeople.add(childManualTourists.get(0));

                    childManualTourists.remove(0);
                }
            }

            SaleVo saleVo = new SaleVo();
            saleVo.setOrderId(ordSalesOrder.getId());

            Integer d_order_id = ordSalesOrder.getOrderId();
            OrdDealOrder ordDealOrder = ordDealOrderMapper.selectByOrderId(d_order_id);
            saleVo.setTourBasicId(ordDealOrder.getGroupId());
            saleVo.setChannelId(String.valueOf(ChannelClassify.getEnumByKey(ordDealOrder.getSellChannelCode()).getValue()));
            saleVo.setChannelOrderId(String.valueOf(ordSalesOrder.getExtOrderId()));
            saleVo.setMemberId(Integer.valueOf(ordDealOrder.getDistributorId()));

            if ("".equals(memberName)) {
                com.tuniu.ord.vo.common.ResponseVo<MemberInfoVo> memberInfo = ExternalRestUtil
                        .getMemberInfoById(Integer.valueOf(ordDealOrder.getDistributorId()));
                if (memberInfo.getData() != null) {
                    memberName = memberInfo.getData().getFullName();
                }
            }
            saleVo.setMemberName(memberName);
            saleVo.setStartDate(DateFormatUtils.formatDate(ordDealOrder.getDepartDate()));
            List<ConsumerVo> consumerVos = new ArrayList<ConsumerVo>();

            for (ManualTourist manualTourist : selectedPeople) {
                OrdPeopleTourist ordPeopleTourist = new OrdPeopleTourist();
                ordPeopleTourist.setSellOrderId(ordSalesOrder.getId());
                ordPeopleTourist.setFabContactId(String.valueOf(manualTourist.getId()));
                ordPeopleTourist.setTouristType(manualTourist.getTouristType().toString());
                ordPeopleTourist.setName(manualTourist.getName());
                ordPeopleTourist.setFirstname(manualTourist.getFirstname());
                ordPeopleTourist.setLastname(manualTourist.getLastname());
                ordPeopleTourist.setSex(manualTourist.getSex().toString());
                ordPeopleTourist.setTel(manualTourist.getTel());
                List<ManualTouristCertificate> touristCertificates = certificateMapper.selectByTouristId(manualTourist.getId());
                if (CollectionUtils.isNotEmpty(touristCertificates)) {
                    Integer psptType = touristCertificates.get(0).getPsptType();
                    ordPeopleTourist.setPsptId(touristCertificates.get(0).getPsptId());
                    ordPeopleTourist.setPsptType(psptType != null ? String.valueOf(psptType) : null);
                }
                BaseDomainUtil.setBasePropertyValue(ordPeopleTourist);

                ordPeopleTouristMapper.insertSelective(ordPeopleTourist);

                ConsumerVo consumerVo = new ConsumerVo();
                // consumerVo.setConsumerAge(consumerAge);
                consumerVo.setConsumerAgeSegment(manualTourist.getTouristType());
                // consumerVo.setConsumerBirthday(consumerBirthday);
                if (CollectionUtils.isNotEmpty(touristCertificates)) {
                    consumerVo.setCardType(touristCertificates.get(0).getPsptType());
                    consumerVo.setConsumerCardNo(touristCertificates.get(0).getPsptId());
                }
                consumerVo.setConsumerId(ordPeopleTourist.getId());
                // consumerVo.setConsumerLevel(consumerLevel);
                consumerVo.setConsumerName(manualTourist.getName());
                consumerVo.setConsumerPhone(manualTourist.getTel());
                consumerVo.setFabId(Integer.valueOf(ordPeopleTourist.getFabContactId()));
                consumerVo.setSex(Integer.valueOf(manualTourist.getSex()));
                consumerVo.getConsumerAgeSegment();

                consumerVos.add(consumerVo);
            }
            saleVo.setAdultNum(ordSalesOrder.getAdultCount());
            saleVo.setChildNum(ordSalesOrder.getChildCount());
            BigDecimal adultCost = ordSalesOrder.getAdultPrice().multiply(new BigDecimal(ordSalesOrder.getAdultCount()));
            saleVo.setAdultCost(adultCost);
            BigDecimal childCost = ordSalesOrder.getChildPrice().multiply(new BigDecimal(ordSalesOrder.getChildCount()));
            saleVo.setChildCost(childCost);
            saleVo.setTotalCost(adultCost.add(childCost));
            saleVo.setCurrencyType("8");
            saleVo.setSpecialRemark("");
            saleVo.setAddNum(0);

            saleVo.setConsumers(consumerVos);

            sales.add(saleVo);
        }
        salesConfirmVo.setSales(sales);

        // 更新流程表
        taskFlow.finish(manualOrderId, OrderTaskEnum.CONFIRM);

        String result = RestUtil.executeWithTenantId(SystemInitParameter.sendSalesConfirm, SystemConstants.HTTP_POST,
                JsonUtil.toString(salesConfirmVo));
        if (null == result || "".equals(result)) {
            throw new SaaSSystemException("销售单下单无返回");
        }

        ResponseVo responseVo = JsonUtil.toBean(result, ResponseVo.class);
        if (!responseVo.isSuccess()) {
            throw new SaaSSystemException(responseVo.getMsg());
        }
    }
}
