package com.tuniu.ord.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.operation.framework.base.rest.RestCodec;
import com.tuniu.operation.framework.base.time.DateFormatUtils;
import com.tuniu.ord.common.util.BaseDomainUtil;
import com.tuniu.ord.common.util.CollectionUtils;
import com.tuniu.ord.common.validator.ArgumentValidator;
import com.tuniu.ord.core.exception.OrderSystemException;
import com.tuniu.ord.core.exception.SaaSSystemException;
import com.tuniu.ord.core.init.SystemInitParameter;
import com.tuniu.ord.domain.CheckLoss;
import com.tuniu.ord.domain.CheckLossDetail;
import com.tuniu.ord.domain.CheckLossDiffInfo;
import com.tuniu.ord.domain.CheckLossTourist;
import com.tuniu.ord.domain.OrdChannelOrder;
import com.tuniu.ord.domain.OrdDealOrder;
import com.tuniu.ord.domain.OrdPriceDetail;
import com.tuniu.ord.domain.OrdSalesOrder;
import com.tuniu.ord.enums.ChannelOrderStatusEnum;
import com.tuniu.ord.enums.OperatorTypeEnum;
import com.tuniu.ord.enums.OrdError;
import com.tuniu.ord.enums.OrderStateEnum;
import com.tuniu.ord.persistence.CheckLossDetailMapper;
import com.tuniu.ord.persistence.CheckLossDiffInfoMapper;
import com.tuniu.ord.persistence.CheckLossMapper;
import com.tuniu.ord.persistence.CheckLossTouristMapper;
import com.tuniu.ord.persistence.OrdChannelOrderMapper;
import com.tuniu.ord.persistence.OrdDealOrderMapper;
import com.tuniu.ord.persistence.OrdPriceDetailMapper;
import com.tuniu.ord.persistence.OrdSalesOrderMapper;
import com.tuniu.ord.proxy.RTXProxy;
import com.tuniu.ord.service.CheckLossService;
import com.tuniu.ord.vo.CheckLossDepartVo;
import com.tuniu.ord.vo.CheckLossQueryVo;
import com.tuniu.ord.vo.CheckLossReqVo;
import com.tuniu.ord.vo.CheckLossTouristVo;
import com.tuniu.ord.vo.CheckLossTuniuInfoVo;
import com.tuniu.ord.vo.ConfirmSalesQueryVo;
import com.tuniu.ord.vo.OrdSaleOrderSeleVo;
import com.tuniu.ord.vo.ProductsVo;
import com.tuniu.ord.vo.RTXInputVo;
import com.tuniu.ord.vo.SalesInfoWhenCheckLossVo;

@Service(value = "checkLossServiceImpl")
public class CheckLossServiceImpl implements CheckLossService {

    private static Logger logger = LoggerFactory.getLogger(CheckLossServiceImpl.class);

    @Resource
    private CheckLossMapper checkLossMapper;

    @Resource
    private CheckLossTouristMapper checkLossTouristMapper;

    @Resource
    private CheckLossDiffInfoMapper checkLossDiffInfoMapper;

    @Resource
    private OrdSalesOrderMapper ordSalesOrderMapper;

    @Resource
    private CheckLossDetailMapper checkLossDetailMapper;

    @Resource
    private OrdDealOrderMapper dealOrderMapper;

    @Resource
    private OrdPriceDetailMapper ordPriceDetailMapper;

    @Resource
    private OrdChannelOrderMapper channelOrderMapper;

    @Override
    public List<CheckLoss> getCheckLossList(CheckLossQueryVo checkLossQueryVo) {
        return checkLossMapper.selectCheckLossList(checkLossQueryVo);
    }

    @Override
    public int count(CheckLossQueryVo checkLossQueryVo) {
        return checkLossMapper.count(checkLossQueryVo);
    }

    /**
     * A类发送D类核损
     */
    @Override
    public void addCheckLossByAPI(CheckLossReqVo checkLossReqVo) throws Exception {
        try {
            validateCheckLossData(checkLossReqVo);
        } catch (Exception e) {
            if (e instanceof OrderSystemException) {
                OrderSystemException exception = (OrderSystemException) e;
                int code = exception.getErrCode();
                if (code == 10001) {
                    insertErrorInfo(checkLossReqVo);

                    // rtx提醒产品专员
                    Integer uid = null;
                    try {
                        Integer extPurchaseId = checkLossReqVo.getProducts().get(0).getDepartDates().get(0).getExtPurchaseId();
                        if (extPurchaseId != null) {
                            OrdDealOrder ordDealOrder = dealOrderMapper.selectByOrderId(extPurchaseId);
                            if (ordDealOrder != null) {
                                uid = ordDealOrder.getProductStaffId();
                            }
                        }
                        RTXInputVo input = new RTXInputVo();
                        input.setTitle("D类订单系统");
                        Integer tuniuOrderId = checkLossReqVo.getProducts().get(0).getTuniuInfo().getTuniuOrderId();
                        String param = "{channelOrderId:" + tuniuOrderId + "}";
                        String content = SystemInitParameter.doCheckLossException + "?" + RestCodec.encodeBase64(param);
                        input.setContent("核损人员信息有误！请去人员信息列表页处理。[查看详情|" + content + "]");
                        input.setUids(Collections.singletonList(uid));
                        RTXProxy.sendRTX(input);
                    } catch (Exception e2) {
                        logger.error("核损rtx发送错误", e);
                    }

                    logger.info("核损人员信息不一致。return");
                    return;
                }
            }
            throw e;
        }

        convertRoundId2DealOrderId(checkLossReqVo);

        // 判断产品列表是否为空
        if (CollectionUtils.isNotEmpty(checkLossReqVo.getProducts())) {
            List<ProductsVo> productsVos = checkLossReqVo.getProducts();
            // 循环遍历出产品列表，进行核损处理
            for (ProductsVo productsVo : productsVos) {
                CheckLossTuniuInfoVo tuniuInfo = productsVo.getTuniuInfo();

                List<String> touristIds = new ArrayList<String>(productsVo.getLossTourist().size());
                for (CheckLossTouristVo checkLossTouristVo : productsVo.getLossTourist()) {
                    touristIds.add(checkLossTouristVo.getTouristId());
                }

                Integer requirementId = tuniuInfo.getRequirementId();
                Integer tuniuOrderId = tuniuInfo.getTuniuOrderId();
                // D系统人员和切位单关系
                List<SalesInfoWhenCheckLossVo> salesInfo = checkLossTouristMapper.querySalesByCheckLossInfo(requirementId,
                        tuniuOrderId, touristIds);

                // D对的切位单
                Set<Integer> dealOrderIdSet = new HashSet<Integer>();
                for (SalesInfoWhenCheckLossVo salesInfoWhenCheckLossVo : salesInfo) {
                    dealOrderIdSet.add(salesInfoWhenCheckLossVo.getDealOrderId());
                }

                // 根据外部核损人员对应到我们自己系统的核损信息 切位单号 核损几个大人 儿童
                List<CheckLossDealOrderInfo> list = new ArrayList<CheckLossDealOrderInfo>();
                for (Integer dealOrderId : dealOrderIdSet) {
                    CheckLossDealOrderInfo checkLossDealOrderInfo = new CheckLossDealOrderInfo();
                    checkLossDealOrderInfo.setDealOrderId(dealOrderId);
                    checkLossDealOrderInfo.setDealOrderType(dealOrderMapper.selectByOrderId(dealOrderId).getDealOrderType());
                    for (SalesInfoWhenCheckLossVo salesInfoWhenCheckLossVo : salesInfo) {
                        int touristType = salesInfoWhenCheckLossVo.getTouristType();
                        Integer orderId = salesInfoWhenCheckLossVo.getDealOrderId();
                        if (dealOrderId.intValue() == orderId.intValue()) {
                            if (touristType == 0) {
                                int num = checkLossDealOrderInfo.getLossAdultNum();
                                checkLossDealOrderInfo.setLossAdultNum(num + 1);
                            } else if (touristType == 1) {
                                int num = checkLossDealOrderInfo.getLossChildNum();
                                checkLossDealOrderInfo.setLossChildNum(num + 1);
                            }
                        }
                    }
                    list.add(checkLossDealOrderInfo);
                }
                logger.info("list:" + JsonUtil.toString(list));

                // 随便根据一个切位单查询产品信息
                Integer dealOrderId = (Integer) dealOrderIdSet.toArray()[0];
                CheckLoss checkLoss = initCheckLoss(checkLossReqVo, tuniuInfo, dealOrderId);
                checkLossMapper.insertSelective(checkLoss);
                Integer checkLossId = checkLoss.getId();
                logger.info("checkLossId:" + checkLossId);

                logger.info("保存核损详情");
                for (CheckLossDealOrderInfo checkLossDealOrderInfo : list) {
                    initCheckLossDetailInfo(tuniuInfo, checkLossId, checkLossDealOrderInfo);
                }

                // 原来的对应关系
                List<CheckLossDealOrderInfo> orignalDealOrderId = new ArrayList<CheckLossDealOrderInfo>();

                // 外部的核损切位单信息
                List<Integer> dealOrderIdList = new ArrayList<Integer>();
                List<CheckLossDepartVo> checkLossDepartVos = productsVo.getDepartDates();
                for (CheckLossDepartVo checkLossDepartVo : checkLossDepartVos) {
                    int adultLossNum = checkLossDepartVo.getLossAdultNum() == null ? 0 : checkLossDepartVo.getLossAdultNum();
                    int childLossNum = checkLossDepartVo.getLossChildNum() == null ? 0 : checkLossDepartVo.getLossChildNum();
                    for (int i = 0; i < adultLossNum + childLossNum; i++) {
                        dealOrderIdList.add(checkLossDepartVo.getExtPurchaseId());
                    }

                    CheckLossDealOrderInfo checkLossDealOrderInfo = new CheckLossDealOrderInfo();
                    checkLossDealOrderInfo.setDealOrderId(checkLossDepartVo.getExtPurchaseId());
                    checkLossDealOrderInfo.setLossAdultNum(adultLossNum);
                    checkLossDealOrderInfo.setLossChildNum(childLossNum);
                    orignalDealOrderId.add(checkLossDealOrderInfo);
                }

                List<CheckLossDiffInfo> diffInfos = new ArrayList<CheckLossDiffInfo>();

                // 销售单中人员和切位单的关系
                if (CollectionUtils.isNotEmpty(salesInfo)) {
                    for (SalesInfoWhenCheckLossVo salesInfoWhenCheckLossVo : salesInfo) {
                        CheckLossDiffInfo checkLossDiffInfo = new CheckLossDiffInfo();
                        checkLossDiffInfo.setCheckLossId(checkLossId);
                        checkLossDiffInfo.setChannelOrderId(String.valueOf(tuniuOrderId));
                        checkLossDiffInfo.setRequirementId(requirementId);
                        checkLossDiffInfo.setTouristId(salesInfoWhenCheckLossVo.getTouristId());
                        for (int i = 0; i < dealOrderIdList.size(); i++) {
                            if (salesInfoWhenCheckLossVo.getDealOrderId().intValue() == dealOrderIdList.get(i).intValue()) {
                                checkLossDiffInfo.setChannelDealOrderId(dealOrderIdList.get(i).intValue());
                                checkLossDiffInfo.setChannelAdultPrice(salesInfoWhenCheckLossVo.getAdultPrice());
                                checkLossDiffInfo.setChannelChildPrice(salesInfoWhenCheckLossVo.getChildPrice());
                                dealOrderIdList.remove(i);
                                break;
                            }
                        }
                        checkLossDiffInfo.setDealOrderId(salesInfoWhenCheckLossVo.getDealOrderId());
                        checkLossDiffInfo.setAdultPrice(salesInfoWhenCheckLossVo.getAdultPrice());
                        checkLossDiffInfo.setChildPrice(salesInfoWhenCheckLossVo.getChildPrice());
                        BaseDomainUtil.setBasePropertyValue(checkLossDiffInfo);

                        diffInfos.add(checkLossDiffInfo);
                    }
                } else {
                    throw new SaaSSystemException("没有核损人员对于的销售信息");
                }

                logger.info("剩余未分配的DealOrderId:" + JsonUtil.toString(dealOrderIdList));
                // 差异表中外部切位单为空说明和销售切位单不匹配 随机填充
                for (CheckLossDiffInfo checkLossDiffInfo : diffInfos) {
                    if (checkLossDiffInfo.getChannelDealOrderId() == null) {
                        OrdPriceDetail ordPriceDetail = ordPriceDetailMapper.selectByOrderId(dealOrderIdList.get(0));
                        checkLossDiffInfo.setChannelDealOrderId(dealOrderIdList.get(0));
                        checkLossDiffInfo.setChannelAdultPrice(ordPriceDetail.getAdultPrice());
                        checkLossDiffInfo.setChannelChildPrice(ordPriceDetail.getChildPrice());
                        dealOrderIdList.remove(0);
                    }
                }

                for (CheckLossDiffInfo checkLossDiffInfo : diffInfos) {
                    for (CheckLossDealOrderInfo checkLossDealOrderInfo : orignalDealOrderId) {
                        if (checkLossDiffInfo.getChannelDealOrderId().intValue() == checkLossDealOrderInfo.getDealOrderId()) {
                            checkLossDiffInfo.setLossAdultNum(checkLossDealOrderInfo.getLossAdultNum());
                            checkLossDiffInfo.setLossChildNum(checkLossDealOrderInfo.getLossChildNum());
                        }
                    }
                    checkLossDiffInfoMapper.insertSelective(checkLossDiffInfo);
                }

                logger.info("保存核损游客详情");
                // 查询核损对应的游客并保存
                for (SalesInfoWhenCheckLossVo salesInfoWhenCheckLossVo : salesInfo) {
                    CheckLossTourist checkLossTourist = new CheckLossTourist();
                    checkLossTourist.setCheckLossId(checkLossId);
                    checkLossTourist.setOrdPeopleTouristId(salesInfoWhenCheckLossVo.getPeopleTouristId());
                    BaseDomainUtil.setBasePropertyValue(checkLossTourist);
                    checkLossTouristMapper.insertSelective(checkLossTourist);
                }

                // 修改销售订单状态为核损中
                OrdSaleOrderSeleVo ordSaleOrderSeleVo = new OrdSaleOrderSeleVo();
                ordSaleOrderSeleVo.setRequirementId(requirementId);
                ordSaleOrderSeleVo.setSellOrderId(tuniuOrderId);
                List<OrdSalesOrder> ordSalesOrders = ordSalesOrderMapper.selectBySaleOrdleIdAndRequirement(ordSaleOrderSeleVo);
                ArgumentValidator.isNotNullEmpty("ordSalesOrders", ordSalesOrders);
                for (OrdSalesOrder ordSalesOrder : ordSalesOrders) {
                    ordSalesOrder.setStatusCode(OrderStateEnum.LOSSING.getStatusCode());
                    ordSalesOrderMapper.updateByPrimaryKeySelective(ordSalesOrder);
                }

                // 发送核损的rtx提醒 默认单团期可以这么写
                if (checkLoss.getUserId() != null && checkLoss.getUserId() != 0) {
                    RTXInputVo input = new RTXInputVo();
                    input.setTitle("D类订单系统");
                    String param = "{checkLossId:" + checkLossId + "}";
                    String content = SystemInitParameter.doCheckLossPage + "?" + RestCodec.encodeBase64(param);
                    input.setContent("您有一条新的核损消息，请去处理。[查看详情|" + content + "]");
                    input.setUids(Collections.singletonList(checkLoss.getUserId()));
                    RTXProxy.sendRTX(input);
                }
            }
        }
    }

    private void insertErrorInfo(CheckLossReqVo checkLossReqVo) {
        OrdChannelOrder ordChannelOrder = new OrdChannelOrder();
        ordChannelOrder.setChannelOrderId(checkLossReqVo.getProducts().get(0).getTuniuInfo().getTuniuOrderId());
        ordChannelOrder.setStatus(ChannelOrderStatusEnum.DEALING.getKey());
        ordChannelOrder.setOriginalParam(JsonUtil.toString(checkLossReqVo));
        ordChannelOrder.setOperatorType(OperatorTypeEnum.LOSS_OPE.getKey());

        List<CheckLossTouristVo> lossTourist = checkLossReqVo.getProducts().get(0).getLossTourist();
        ordChannelOrder.setBeforeChangeTourist(JsonUtil.toString(lossTourist));
        BaseDomainUtil.setBasePropertyValue(ordChannelOrder);
        channelOrderMapper.insertSelective(ordChannelOrder);
    }

    public static void validateCheckLossData(CheckLossReqVo checkLossReqVo) {
        ArgumentValidator.isNotNull("checkLossReqVo", checkLossReqVo);

        if (CollectionUtils.isNotEmpty(checkLossReqVo.getProducts())) {
            for (int i = 0; i < checkLossReqVo.getProducts().size(); i++) {
                ArgumentValidator.isNotNull("tuniuInfo", checkLossReqVo.getProducts().get(i).getTuniuInfo());
                ArgumentValidator.isNotNull("agencyInfo", checkLossReqVo.getProducts().get(i).getAgencyInfo());
                ArgumentValidator.isNotNull("lossTourist", checkLossReqVo.getProducts().get(i).getLossTourist());
                ArgumentValidator.isNotNull("departDates", checkLossReqVo.getProducts().get(i).getDepartDates());

                int checkLossPeopleCount = 0;
                // 判断核损人数与传递的实际人数是否匹配
                for (CheckLossDepartVo checkLossDepartVo : checkLossReqVo.getProducts().get(i).getDepartDates()) {
                    ArgumentValidator.isNotNull("lossAdultNum", checkLossDepartVo.getLossAdultNum());
                    ArgumentValidator.isNotNull("lossChildNum", checkLossDepartVo.getLossChildNum());
                    ArgumentValidator.isNotNull("checkLossDepartVo.getDate()", checkLossDepartVo.getDate());
                    ArgumentValidator.isNotNull("checkLossDepartVo.getLossAdultNum()", checkLossDepartVo.getLossAdultNum());
                    ArgumentValidator.isNotNull("checkLossDepartVo.getLossChildNum()", checkLossDepartVo.getLossChildNum());
                    ArgumentValidator.isNotNull("checkLossDepartVo.getStockType()", checkLossDepartVo.getStockType());
                    // ArgumentValidator.isNotNull("checkLossDepartVo.getRoundId()", checkLossDepartVo.getRoundId());

                    checkLossPeopleCount = checkLossPeopleCount + checkLossDepartVo.getLossAdultNum().intValue()
                            + checkLossDepartVo.getLossChildNum().intValue()
                            + (checkLossDepartVo.getLossBabyNum() == null ? 0 : checkLossDepartVo.getLossBabyNum().intValue());
                }

                int touristNum = checkLossReqVo.getProducts().get(i).getLossTourist().size();
                if (touristNum > checkLossPeopleCount) {
                    throw new OrderSystemException(OrdError.TOURIST_NUM_NO_DIFFERENCE_WHEN_CHECKLOSS);
                }

                if (touristNum < checkLossPeopleCount) {
                    throw new SaaSSystemException("切位单对应的数量多于人员的数量");
                }

                for (CheckLossTouristVo checkLossTouristVo : checkLossReqVo.getProducts().get(i).getLossTourist()) {
                    ArgumentValidator.isNotNull("checkLossTouristVo.getPsptId()", checkLossTouristVo.getPsptId());
                    ArgumentValidator.isNotNull("checkLossTouristVo.getTouristName()", checkLossTouristVo.getTouristName());
                    ArgumentValidator.isNotNull("checkLossTouristVo.getPsptType()", checkLossTouristVo.getPsptType());
                    ArgumentValidator.isNotNull("checkLossTouristVo.getSex()", checkLossTouristVo.getSex());
                }
            }
        }
    }

    private void initCheckLossDetailInfo(CheckLossTuniuInfoVo tuniuInfo, Integer checkLossId,
            CheckLossDealOrderInfo checkLossDealOrderInfo) {
        ConfirmSalesQueryVo confirmSalesQueryVo = new ConfirmSalesQueryVo();
        confirmSalesQueryVo.setExtOrderId(tuniuInfo.getTuniuOrderId());
        confirmSalesQueryVo.setOrderId(checkLossDealOrderInfo.getDealOrderId());
        confirmSalesQueryVo.setRequirementId(tuniuInfo.getRequirementId());
        OrdSalesOrder ordSalesOrder = ordSalesOrderMapper.getSaleByConfirmQuery(confirmSalesQueryVo);

        CheckLossDetail checkLossDetail = new CheckLossDetail();
        checkLossDetail.setCheckLossId(checkLossId);
        checkLossDetail.setDealOrderId(ordSalesOrder.getOrderId());
        checkLossDetail.setAdultCountBefore(ordSalesOrder.getAdultCount().intValue());
        checkLossDetail.setChildCountBefore(ordSalesOrder.getChildCount().intValue());
        // 判断核损后人数变更是否正确
        checkLossDetail.setAdultCountAfter(
                checkLossDetail.getAdultCountBefore().intValue() - checkLossDealOrderInfo.getLossAdultNum());
        checkLossDetail.setChildCountAfter(
                checkLossDetail.getChildCountBefore().intValue() - checkLossDealOrderInfo.getLossChildNum());
        if (checkLossDealOrderInfo.getDealOrderType().intValue() == 2) {//现询
            checkLossDetail.setStockType(6);
        } else if (checkLossDealOrderInfo.getDealOrderType().intValue() == 1) {//库存
            checkLossDetail.setStockType(1);
        } else if (checkLossDealOrderInfo.getDealOrderType().intValue() == 3) {//fs
            checkLossDetail.setStockType(2);
        }else{
           throw new SaaSSystemException("核损D系统不存在与API对应的stockType类型");
        }

        try {
            // 自动核损才有核损时间带过来
            if (tuniuInfo.getEffectiveTime() != null) {
                checkLossDetail.setExpireTime(DateFormatUtils.parseDatetime(tuniuInfo.getEffectiveTime()));
            }
        } catch (ParseException e) {
            throw new SaaSSystemException("时间转化错误");
        }
        checkLossDetailMapper.insertSelective(checkLossDetail);
    }

    private CheckLoss initCheckLoss(CheckLossReqVo checkLossReqVo, CheckLossTuniuInfoVo tuniuInfo, Integer dealOrderId) {
        OrdDealOrder ordDealOrder = dealOrderMapper.selectByOrderId(dealOrderId);
        CheckLoss checkLoss = new CheckLoss();
        checkLoss.setSellOrderId(tuniuInfo.getTuniuOrderId());
        checkLoss.setRequirementId(tuniuInfo.getRequirementId());
        checkLoss.setProductId(ordDealOrder.getProductId());
        checkLoss.setProductName(ordDealOrder.getProductName());
        checkLoss.setGroupId(ordDealOrder.getGroupId());
        checkLoss.setGroupName(ordDealOrder.getGroupName());
        checkLoss.setTourDate(ordDealOrder.getDepartDate());
        // TODO 可以先写死了 后期要修改
        checkLoss.setSellChannel("途牛A");
        checkLoss.setUserId(ordDealOrder.getProductStaffId());
        checkLoss.setName(ordDealOrder.getProductStaffName());
        checkLoss.setStatus("0");
        checkLoss.setApiKey(checkLossReqVo.getApiKey());
        checkLoss.setTimestamp(checkLossReqVo.getTimestamp());
        checkLoss.setSign(checkLossReqVo.getSign());
        checkLoss.setSessionId(checkLossReqVo.getSessionId());

        if (StringUtils.isEmpty(tuniuInfo.getEffectiveTime())) {
            checkLoss.setCheckLossType(0);
        } else {
            checkLoss.setCheckLossType(1);
        }
        BaseDomainUtil.setBasePropertyValue(checkLoss);
        return checkLoss;
    }

    private void convertRoundId2DealOrderId(CheckLossReqVo checkLossReqVo) {
        List<ProductsVo> products = checkLossReqVo.getProducts();
        for (ProductsVo productsVo : products) {
            List<CheckLossDepartVo> departDates = productsVo.getDepartDates();
            for (CheckLossDepartVo checkLossDepartVo : departDates) {
                if (checkLossDepartVo.getExtPurchaseId() == null || checkLossDepartVo.getExtPurchaseId().intValue() == 0) {
                    OrdDealOrder ordDealOrder = dealOrderMapper.getOrdDealOrderByRoundId(checkLossDepartVo.getRoundId());
                    if (ordDealOrder == null) {
                        throw new SaaSSystemException("A批次没有对应的切位单");
                    }
                    checkLossDepartVo.setExtPurchaseId(ordDealOrder.getOrderId());
                }
            }
        }
    }

    public static class CheckLossDealOrderInfo {
        private Integer dealOrderId;
        // 1库存 2现询 3 fs
        private Integer dealOrderType;
        private int lossAdultNum;
        private int lossChildNum;

        public Integer getDealOrderType() {
            return dealOrderType;
        }

        public void setDealOrderType(Integer dealOrderType) {
            this.dealOrderType = dealOrderType;
        }

        public Integer getDealOrderId() {
            return dealOrderId;
        }

        public void setDealOrderId(Integer dealOrderId) {
            this.dealOrderId = dealOrderId;
        }

        public int getLossAdultNum() {
            return lossAdultNum;
        }

        public void setLossAdultNum(int lossAdultNum) {
            this.lossAdultNum = lossAdultNum;
        }

        public int getLossChildNum() {
            return lossChildNum;
        }

        public void setLossChildNum(int lossChildNum) {
            this.lossChildNum = lossChildNum;
        }
    }
}
