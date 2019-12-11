/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年9月2日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.operation.framework.base.time.DateUtils;
import com.tuniu.operation.framework.base.util.SystemConstants;
import com.tuniu.ord.common.constant.Constants;
import com.tuniu.ord.common.util.CollectionUtils;
import com.tuniu.ord.common.util.EnvironmentUtil;
import com.tuniu.ord.common.util.SessionUtil;
import com.tuniu.ord.common.util.UserSessionUtil;
import com.tuniu.ord.core.mail.client.ORDMailClient;
import com.tuniu.ord.domain.OrdPeopleTourist;
import com.tuniu.ord.domain.OrdSalesOrder;
import com.tuniu.ord.enums.OrderStateEnum;
import com.tuniu.ord.enums.ProductCategoryEnum;
import com.tuniu.ord.persistence.OrdPeopleTouristMapper;
import com.tuniu.ord.persistence.OrdSalesOrderMapper;
import com.tuniu.ord.proxy.DPSProxy;
import com.tuniu.ord.proxy.GRPProxy;
import com.tuniu.ord.proxy.PRDProxy;
import com.tuniu.ord.proxy.SCSProxy;
import com.tuniu.ord.service.IOrderIdServiceClient;
import com.tuniu.ord.service.SimpleConfirmService;
import com.tuniu.ord.vo.ConsumerVo;
import com.tuniu.ord.vo.DPSDeptInfo;
import com.tuniu.ord.vo.EDMInputVo;
import com.tuniu.ord.vo.OrderSynInfo;
import com.tuniu.ord.vo.ProductIdParamVo;
import com.tuniu.ord.vo.ProductInfoToOrdVo;
import com.tuniu.ord.vo.QueryRelationsIdOutputVo;
import com.tuniu.ord.vo.QueryRelationsIdVo;
import com.tuniu.ord.vo.ResponseVo;
import com.tuniu.ord.vo.SaleVo;
import com.tuniu.ord.vo.SalesConfirmVo;
import com.tuniu.ord.vo.SimpleOrderInfoVo;
import com.tuniu.ord.vo.StatisticsLog;
import com.tuniu.ord.vo.Tourist;
import com.tuniu.ord.vo.TransferedAOrderIdsInputAndOutputVo;

/**
 * @author zhairongping
 * 
 */
@Service
public class SimpleConfirmServiceImpl implements SimpleConfirmService {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleConfirmServiceImpl.class);

    @Resource
    private OrdSalesOrderMapper ordSalesOrderMapper;

    @Resource
    private IOrderIdServiceClient iOrderIdServiceImplClient;

    @Resource
    private OrdPeopleTouristMapper ordPeopleTouristMapper;

    @Resource
    private GRPProxy gRPProxy;

    @Resource
    private SCSProxy sCSProxy;

    @Resource
    private DPSProxy dPSProxy;

    @Resource
    private PRDProxy pRDProxy;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 执行数据迁移任务分解成5个子任务：1>添加销售单;2>添加出游人;3>向组团系统下销售单;4>同步订单信息给财务系统;5>保存迁移成功的A订单 .每个子任务由一个独立的类来完成
     */
    @Override
    public void savePlaceOrder(SimpleOrderInfoVo simpleOrderInfoVo) {
        // TODO Auto-generated method stub
        // 记录操作结果
        StatisticsLog sl = new StatisticsLog();
        sl.setTuniuOrderId(simpleOrderInfoVo.getTuniuOrderId());

        // 添加销售单
        OrdSalesOrder ordSalesOrder = new OrdSalesOrder();
        initOrdSalesOrderParam(ordSalesOrder, simpleOrderInfoVo);
        ordSalesOrderMapper.insertSelective(ordSalesOrder);
        sl.setdOrderId(ordSalesOrder.getId());
        sl.getSteps()
                .add("【step1】A类订单" + simpleOrderInfoVo.getTuniuOrderId() + "——D类销售单" + ordSalesOrder.getId() + "【添加销售单】成功");

        // 添加出游人
        List<Integer> touristIds = new ArrayList<Integer>();
        for (Tourist tourist : simpleOrderInfoVo.getTouristInfoList()) {
            OrdPeopleTourist ordPeopleTourist = new OrdPeopleTourist();
            initOrdPeopleTouristParam(ordPeopleTourist, ordSalesOrder.getId(), tourist);
            ordPeopleTouristMapper.insertSelective(ordPeopleTourist);
            touristIds.add(ordPeopleTourist.getId());
        }
        sl.getSteps()
                .add("【step2】A类订单" + simpleOrderInfoVo.getTuniuOrderId() + "——D类销售单" + ordSalesOrder.getId() + "【添加出游人】成功");

        // 同步订单信息给财务系统
        OrderSynInfo orderSynInfo = new OrderSynInfo();
        initOrderSynInfoParam(orderSynInfo, simpleOrderInfoVo, ordSalesOrder.getId(), sl);
        ResponseVo sCsRes = sCSProxy.synOrder(orderSynInfo);
        if (sCsRes.isSuccess() == false) {
            if (EnvironmentUtil.isPrdEnv()) {
                // 发邮件提醒
                EDMInputVo eDMInputVo = new EDMInputVo();
                initEDMParam(eDMInputVo, ordSalesOrder.getId(), orderSynInfo);
                ORDMailClient.sendMailToSCS(eDMInputVo);
            }
            sl.getSteps().add("【step3】A类订单" + simpleOrderInfoVo.getTuniuOrderId() + "——D类销售单" + ordSalesOrder.getId()
                    + "【同步订单信息给财务系统】失败:" + sCsRes.getMsg() + ",请求入参:" + JsonUtil.toString(orderSynInfo));
        } else {
            sl.getSteps().add("【step3】A类订单" + simpleOrderInfoVo.getTuniuOrderId() + "——D类销售单" + ordSalesOrder.getId()
                    + "【同步订单信息给财务系统】成功");
        }

        // 向组团系统下销售单
        SalesConfirmVo salesConfirmVo = new SalesConfirmVo();
        initSalesConfirmParam(salesConfirmVo, simpleOrderInfoVo, ordSalesOrder.getId(), touristIds, sl);
        ResponseVo grpRes = gRPProxy.sendSalesConfirm(salesConfirmVo);
        if (grpRes.isSuccess() == false) {
            sl.getSteps().add("【step4】A类订单" + simpleOrderInfoVo.getTuniuOrderId() + "——D类销售单" + ordSalesOrder.getId()
                    + "【向组团系统下销售单】失败:" + grpRes.getMsg() + ",请求入参:" + JsonUtil.toString(salesConfirmVo));
            throw new RuntimeException(sl.toString());
        } else {
            sl.getSteps().add(
                    "【step4】A类订单" + simpleOrderInfoVo.getTuniuOrderId() + "——D类销售单" + ordSalesOrder.getId() + "【向组团系统下销售单】成功");
            sl.setSuccess(true);
            LOG.info("数据迁移统计结果:[{}]", sl.toString());
        }
    }

    /**
     * 初始化向组团系统下销售单实例
     */
    private void initSalesConfirmParam(SalesConfirmVo salesConfirmVo, SimpleOrderInfoVo simpleOrderInfoVo, Integer orderId,
            List<Integer> touristIds, StatisticsLog sl) {
        salesConfirmVo.setTenantId(Constants.tenantId);
        salesConfirmVo.setUid(Integer.valueOf(UserSessionUtil.getUid()));
        salesConfirmVo.setNickname(UserSessionUtil.getNickname());
        salesConfirmVo.setToken(Constants.token);
        salesConfirmVo.setSessionId(SessionUtil.createSessionId());
        salesConfirmVo.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
        List<SaleVo> sales = new ArrayList<SaleVo>();
        SaleVo sale = new SaleVo();
        sale.setAddNum(simpleOrderInfoVo.getRoomAddNum());
        sale.setAddPrice(simpleOrderInfoVo.getRoomAddPrice());
        sale.setAdultCost(simpleOrderInfoVo.getAffirmAdultPrice());
        sale.setAdultNum(simpleOrderInfoVo.getAffirmAdultNum());
        sale.setChannelId("1");
        // 添加外部渠道订单号
        sale.setChannelOrderId(String.valueOf(simpleOrderInfoVo.getTuniuOrderId()));
        sale.setChildCost(simpleOrderInfoVo.getAffirmChildPrice());
        sale.setChildNum(simpleOrderInfoVo.getAffirmChildNum());
        sale.setCurrencyType(String.valueOf(simpleOrderInfoVo.getCostCurrencyType()));
        sale.setOrderId(orderId);
        if (null != simpleOrderInfoVo.getDate()) {
            sale.setStartDate(simpleOrderInfoVo.getDate());
        }
        BigDecimal adultTotalPrice = simpleOrderInfoVo.getAffirmAdultPrice()
                .multiply(new BigDecimal(simpleOrderInfoVo.getAffirmAdultNum()));
        BigDecimal childTotalPrice = simpleOrderInfoVo.getAffirmChildPrice()
                .multiply(new BigDecimal(simpleOrderInfoVo.getAffirmChildNum()));
        BigDecimal roomTotalPrice = simpleOrderInfoVo.getRoomAddPrice()
                .multiply(new BigDecimal(simpleOrderInfoVo.getRoomAddNum()));
        sale.setTotalCost(adultTotalPrice.add(childTotalPrice).add(roomTotalPrice));
        sale.setTourBasicId(simpleOrderInfoVo.getTourBasicId());
        sale.setUpgradeId(0);

        // 调用产品系统查看产品线
        ProductIdParamVo productIdParamVo = new ProductIdParamVo();
        productIdParamVo.setProductId(simpleOrderInfoVo.getdProductId());
        // 针对订单系统查询产品信息
        ResponseVo pRDRes = pRDProxy.getProductInfoToOrd(productIdParamVo);
        ProductInfoToOrdVo pio = new ProductInfoToOrdVo();
        if (pRDRes.isSuccess() == true) {
            pio = JsonUtil.toBean(JsonUtil.toString(pRDRes.getData()), ProductInfoToOrdVo.class);
        } else {
            sl.getSteps().add("【step4】调用D类产品系统接口异常======" + JsonUtil.toString(productIdParamVo) + ",途牛订单号"
                    + simpleOrderInfoVo.getTuniuOrderId());
            throw new RuntimeException(sl.toString());
        }
        Integer productLineId = pio.getProductLineId();
        sale.setProductLineId(productLineId);

        List<ConsumerVo> consumers = new ArrayList<ConsumerVo>();
        List<Tourist> touristList = simpleOrderInfoVo.getTouristInfoList();
        for (int i = 0; i < touristList.size(); i++) {
            Tourist tt = touristList.get(i);
            ConsumerVo consumer = new ConsumerVo();
            consumer = new ConsumerVo();
            if (null != tt.getBirthday() && !"".equals(tt.getBirthday())) {
                Date birth_ = null;
                try {
                    birth_ = sdf.parse(tt.getBirthday());
                } catch (ParseException e) {
                    LOG.info(e.getMessage());
                }
                int year = birth_.getYear();
                // 计算年龄规则
                Date nowDay = Calendar.getInstance().getTime();
                consumer.setConsumerBirthday(tt.getBirthday());
                consumer.setConsumerAge(nowDay.getYear() - year + 1);
            }
            consumer.setConsumerAgeSegment(tt.getTouristType());
            consumer.setConsumerCardNo(tt.getPsptId());
            consumer.setCardType(tt.getPsptType());
            consumer.setConsumerId(touristIds.get(i));
            consumer.setConsumerLevel(new Integer(0));
            consumer.setConsumerName(tt.getTouristName());
            consumer.setConsumerPhone(tt.getTouristTel());
            consumer.setSex(tt.getSex());
            try {
                consumer.setFabId(Integer.parseInt(tt.getTouristId()));
            } catch (Exception ex) {
                LOG.error("转化会员ID失败", ex);
            }
            consumers.add(consumer);
        }
        sale.setConsumers(consumers);
        sale.setMigrateFlag(new Integer(1));

        sales.add(sale);
        salesConfirmVo.setSales(sales);

        /* 额外校验产品线下有无组织信息 */
        /** 访问部门类目管理系统获取部门信息开始 **/
        QueryRelationsIdVo queryRelationsIdVo = new QueryRelationsIdVo();
        queryRelationsIdVo.setProductLineId(productLineId);
        // 访问部门类目管理系统获取部门信息
        ResponseVo dpsRes = dPSProxy.queryRelationsId(queryRelationsIdVo);
        QueryRelationsIdOutputVo queryRelationsIdOutputVo = null;
        DPSDeptInfo defaultDept = new DPSDeptInfo();
        if (dpsRes.isSuccess() == true) {
            queryRelationsIdOutputVo = JsonUtil.toBean(JsonUtil.toString(dpsRes.getData()), QueryRelationsIdOutputVo.class);
        } else {
            sl.getSteps().add("【step4】【产品线ID】" + productLineId + ";根据产品线id、部门id查询关联关系接口异常或者返回失败");
            throw new RuntimeException(sl.toString());
        }
        // 查找产品线与部门的结果标志
        boolean flag = true;
        if (null != queryRelationsIdOutputVo) {
            if (CollectionUtils.isNotEmpty(queryRelationsIdOutputVo.getRows())) {
                for (DPSDeptInfo dept : queryRelationsIdOutputVo.getRows()) {
                    if (Constants.PRODUCT_BRAND_ID.intValue() == dept.getProductBrandId().intValue()
                            && Constants.SALE_STYLE_ID.intValue() == dept.getSaleStyleId().intValue()) {
                        defaultDept = dept;
                        LOG.info("【产品线ID】" + productLineId + ";遍历部门列表匹配的部门信息=====" + JsonUtil.toString(defaultDept));
                        flag = false;
                        break;
                    }
                }
            } else {
                flag = false;
                sl.getSteps().add("【step4】【产品线ID】" + productLineId + "下无部门信息");
                throw new RuntimeException(sl.toString());
            }
            if (flag == true) {
                sl.getSteps().add("【step4】【产品线ID】" + productLineId + "有部门列表，但是没有匹配成功");
                throw new RuntimeException(sl.toString());
            }
        }
        /** 访问部门类目管理系统获取部门信息 结束 **/
    }

    /**
     * 初始化同步订单信息实例
     * 
     * @param orderSynInfo
     * @param simpleOrderInfoVo
     * @param orderId
     * @param sl
     */
    public void initOrderSynInfoParam(OrderSynInfo orderSynInfo, SimpleOrderInfoVo simpleOrderInfoVo, Integer orderId,
            StatisticsLog sl) {
        orderSynInfo.setId(orderId);
        orderSynInfo.setState(Constants.CONFIRMED_ORDERSTATUS);
        orderSynInfo.setStatusDesc(OrderStateEnum.CONFIRMED.getStatusName());
        BigDecimal adultAmount = simpleOrderInfoVo.getAffirmAdultPrice()
                .multiply(new BigDecimal(simpleOrderInfoVo.getAffirmAdultNum()));
        BigDecimal childAmount = simpleOrderInfoVo.getAffirmChildPrice()
                .multiply(new BigDecimal(simpleOrderInfoVo.getAffirmChildNum()));
        BigDecimal roomAmount = simpleOrderInfoVo.getRoomAddPrice().multiply(new BigDecimal(simpleOrderInfoVo.getRoomAddNum()));
        BigDecimal totalAmount = new BigDecimal(0);
        totalAmount = totalAmount.add(adultAmount).add(childAmount).add(roomAmount);
        orderSynInfo.setContractAmount(totalAmount);
        orderSynInfo.setSyncTime(sdft.format(new Date()));
        orderSynInfo.setProduct(simpleOrderInfoVo.getdProductId());

        ProductIdParamVo productIdParamVo = new ProductIdParamVo();
        productIdParamVo.setProductId(simpleOrderInfoVo.getdProductId());
        // 针对订单系统查询产品信息
        ResponseVo pRDRes = pRDProxy.getProductInfoToOrd(productIdParamVo);
        ProductInfoToOrdVo pio = new ProductInfoToOrdVo();
        if (pRDRes.isSuccess() == true) {
            pio = JsonUtil.toBean(JsonUtil.toString(pRDRes.getData()), ProductInfoToOrdVo.class);
        } else {
            sl.getSteps().add("【step3】调用D类产品系统接口异常======" + JsonUtil.toString(productIdParamVo) + ",途牛订单号"
                    + simpleOrderInfoVo.getTuniuOrderId());
            throw new RuntimeException(sl.toString());
        }

        String startCityCode = pio.getStartCityCode();
        String startCityName = pio.getStartCityName();
        Integer destinationFirstCategory = pio.getDestinationFirstCategory();
        String destinationFirstCategoryName = pio.getDestinationFirstCategoryName();
        Integer firstDestinationGrouping = pio.getFirstDestinationGrouping();
        String firstDestinationGroupingName = pio.getFirstDestinationGroupingName();
        Integer secondDestinationGrouping = pio.getSecondDestinationGrouping();
        String secondDestinationGroupingName = pio.getSecondDestinationGroupingName();
        Integer destination = pio.getDestination();
        String destinationName = pio.getDestinationName();
        Integer productLineId = pio.getProductLineId();
        // 出发城市
        orderSynInfo.setStartCityCode(startCityCode);
        orderSynInfo.setStartCityName(startCityName);
        // 产品品类
        orderSynInfo.setProductCategory(ProductCategoryEnum.PRODUCT_CATAGORY.getKey());
        orderSynInfo.setProductCategoryName(ProductCategoryEnum.PRODUCT_CATAGORY.getName());
        orderSynInfo.setProductSubCategory(ProductCategoryEnum.PRODUCT_SUB_CATAGORY.getKey());
        orderSynInfo.setProductSubCategoryName(ProductCategoryEnum.PRODUCT_SUB_CATAGORY.getName());
        // 类目信息
        orderSynInfo.setDestinationFirstCategory(destinationFirstCategory);
        orderSynInfo.setDestinationFirstCategoryName(destinationFirstCategoryName);
        orderSynInfo.setFirstDestinationGrouping(firstDestinationGrouping);
        orderSynInfo.setFirstDestinationGroupingName(firstDestinationGroupingName);
        orderSynInfo.setSecondDestinationGrouping(secondDestinationGrouping);
        orderSynInfo.setSecondDestinationGroupingName(secondDestinationGroupingName);
        orderSynInfo.setDestination(destination);
        orderSynInfo.setDestinationName(destinationName);
        orderSynInfo.setSignCompany(Constants.SIGN_COMPANY);
        orderSynInfo.setSignCompanyName(Constants.SIGN_COMPANY_NAME);
        orderSynInfo.setIsSubOrder(new Integer(1));
        orderSynInfo.setPrimaryOrderId(simpleOrderInfoVo.getTuniuOrderId());

        /** 访问部门类目管理系统获取部门信息开始 **/
        QueryRelationsIdVo queryRelationsIdVo = new QueryRelationsIdVo();
        queryRelationsIdVo.setProductLineId(productLineId);
        // 访问部门类目管理系统获取部门信息
        ResponseVo dpsRes = dPSProxy.queryRelationsId(queryRelationsIdVo);
        QueryRelationsIdOutputVo queryRelationsIdOutputVo = null;
        DPSDeptInfo defaultDept = new DPSDeptInfo();
        if (dpsRes.isSuccess() == true) {
            queryRelationsIdOutputVo = JsonUtil.toBean(JsonUtil.toString(dpsRes.getData()), QueryRelationsIdOutputVo.class);
        } else {
            sl.getSteps().add("【step3】【产品线ID】" + productLineId + ";根据产品线id、部门id查询关联关系接口异常或者返回失败");
            throw new RuntimeException(sl.toString());
        }
        // 查找产品线与部门的结果标志
        boolean flag = true;
        if (null != queryRelationsIdOutputVo) {
            if (CollectionUtils.isNotEmpty(queryRelationsIdOutputVo.getRows())) {
                for (DPSDeptInfo dept : queryRelationsIdOutputVo.getRows()) {
                    if (Constants.PRODUCT_BRAND_ID.intValue() == dept.getProductBrandId().intValue()
                            && Constants.SALE_STYLE_ID.intValue() == dept.getSaleStyleId().intValue()) {
                        defaultDept = dept;
                        LOG.info("【产品线ID】" + productLineId + ";遍历部门列表匹配的部门信息=====" + JsonUtil.toString(defaultDept));
                        flag = false;
                        break;
                    }
                }
            } else {
                flag = false;
                sl.getSteps().add("【step3】【产品线ID】" + productLineId + "下无部门信息");
                throw new RuntimeException(sl.toString());
            }
            if (flag == true) {
                sl.getSteps().add("【step3】【产品线ID】" + productLineId + "有部门列表，但是没有匹配成功");
                throw new RuntimeException(sl.toString());
            }
        }
        /** 访问部门类目管理系统获取部门信息 结束 **/
        // 部门信息
        Integer regionCode = defaultDept.getDivisionId();
        String regionName = defaultDept.getDivisionName();
        Integer departmentCode = defaultDept.getDepartmentId();
        String departmentName = defaultDept.getDepartmentName();
        Integer groupId = defaultDept.getGroupId();
        String groupName = defaultDept.getGroupName();
        orderSynInfo.setRegionCode(regionCode);
        orderSynInfo.setRegionName(regionName);
        orderSynInfo.setDepartmentCode(departmentCode);
        orderSynInfo.setDepartmentName(departmentName);
        orderSynInfo.setGroupId(groupId);
        orderSynInfo.setGroupName(groupName);

        /******************* 出游归来时间处理 ******************/
        // 出游日期
        String date = simpleOrderInfoVo.getDate();
        if (date != null && !"".equals(date)) {
            orderSynInfo.setStartDate(date.concat(" 00:00:00"));
            orderSynInfo.setCollectionTime(date.concat(" 00:00:00"));
            // 出游天数
            String tourDay = pRDProxy.getTourDay(pRDProxy.getProductTourDay(productIdParamVo));
            if (tourDay != null) {
                try {
                    Date backDate = com.tuniu.ngsp.ddg.util.date.DateUtils.addDays(sdf.parse(date),
                            Integer.parseInt(tourDay) - 1);
                    orderSynInfo.setBackDate(sdft.format(backDate));
                } catch (NumberFormatException e) {
                    LOG.error(e.getMessage());
                } catch (ParseException e) {
                    LOG.error(e.getMessage());
                }
            }
        }
    }

    /**
     * 初始化邮件实体
     * 
     * @param eDMInputVo
     * @param orderId
     */
    public void initEDMParam(EDMInputVo eDMInputVo, Integer orderId, OrderSynInfo orderSynInfo) {
        String title = "【结算平台系统中同步订单信息接口异常技术支持邮件通知】";
        StringBuffer sb = new StringBuffer();
        sb.append("【销售订单【" + orderId + "】同步订单信息接口异常");
        sb.append("同步订单信息给结算中心请求地址:" + SystemConstants.SYN_ORDER + ";请求参数:" + JsonUtil.toString(orderSynInfo));
        eDMInputVo.setSubject(title);
        eDMInputVo.setMsgText(sb.toString());
    }

    /**
     * 初始化销售单实例
     * 
     * @param ordSalesOrder
     * @param simpleOrderInfoVo
     */
    public void initOrdSalesOrderParam(OrdSalesOrder ordSalesOrder, SimpleOrderInfoVo simpleOrderInfoVo) {
        // 访问POS客服端生产订单号
        Long orderNum = iOrderIdServiceImplClient.getOrderNum(Constants.INTERVAL_TYPE_ID);
        LOG.info("【A类订单号】=" + simpleOrderInfoVo.getTuniuOrderId() + "【访问POS客服端生产订单号orderNum】=" + orderNum);
        ordSalesOrder.setId(orderNum.intValue());
        ordSalesOrder.setOrderId(simpleOrderInfoVo.getdOrderId());
        ordSalesOrder.setExtOrderId(simpleOrderInfoVo.getTuniuOrderId());
        ordSalesOrder.setExtProductId(simpleOrderInfoVo.getaProductId());
        ordSalesOrder.setExtProductName(simpleOrderInfoVo.getaProductName());
        ordSalesOrder.setStatusCode(OrderStateEnum.CONFIRMED.getStatusCode());
        ordSalesOrder.setCostCurrencyType(String.valueOf(simpleOrderInfoVo.getCostCurrencyType()));
        ordSalesOrder.setSaleChannel(simpleOrderInfoVo.getChannelId());
        ordSalesOrder.setAdultCount(simpleOrderInfoVo.getAffirmAdultNum());
        ordSalesOrder.setAdultPrice(simpleOrderInfoVo.getAffirmAdultPrice());
        ordSalesOrder.setChildCount(simpleOrderInfoVo.getAffirmChildNum());
        ordSalesOrder.setChildPrice(simpleOrderInfoVo.getAffirmChildPrice());
        ordSalesOrder.setRoomAddNum(simpleOrderInfoVo.getRoomAddNum());
        ordSalesOrder.setRoomAddPrice(simpleOrderInfoVo.getRoomAddPrice());
        ordSalesOrder.setCostCurrencyType(simpleOrderInfoVo.getCostCurrencyType());

        Date date = DateUtils.now();
        ordSalesOrder.setAddUid(new Integer(UserSessionUtil.getUid()));
        ordSalesOrder.setAddUname(UserSessionUtil.getNickname());
        ordSalesOrder.setAddTime(date);
        ordSalesOrder.setUpdateUid(new Integer(UserSessionUtil.getUid()));
        ordSalesOrder.setUpdateUname(UserSessionUtil.getNickname());
        ordSalesOrder.setUpdateTime(date);
    }

    /**
     * 
     * 初始化出游人实例
     * 
     * @param ordPeopleTourist
     * @param orderId
     * @param tourist
     */
    public void initOrdPeopleTouristParam(OrdPeopleTourist ordPeopleTourist, Integer orderId, Tourist tt) {
        ordPeopleTourist.setSellOrderId(orderId);
        ordPeopleTourist.setCountry(tt.getCountry());
        ordPeopleTourist.setFirstname(tt.getFirstName());
        ordPeopleTourist.setIsDefault(tt.getIsContactTourist());
        ordPeopleTourist.setLastname(tt.getLastName());
        ordPeopleTourist.setName(tt.getTouristName());
        if (null != tt.getPsptEndDate() && !"".equals(tt.getPsptEndDate())) {
            try {
                ordPeopleTourist.setPsptEndDate(sdf.parse(tt.getPsptEndDate()));
            } catch (ParseException e) {
                LOG.error(e.getMessage());
            }
        }
        ordPeopleTourist.setPsptId(tt.getPsptId());
        ordPeopleTourist.setPsptType(String.valueOf(tt.getPsptType()));
        ordPeopleTourist.setSex(String.valueOf(tt.getSex()));
        ordPeopleTourist.setTel(tt.getTouristTel());
        ordPeopleTourist.setTouristType(String.valueOf(tt.getTouristType()));
        ordPeopleTourist.setFabContactId(tt.getTouristId());
        if (null != tt.getBirthday() && !"".equals(tt.getBirthday())) {
            Date birth_ = null;
            try {
                birth_ = sdf.parse(tt.getBirthday());
            } catch (ParseException e) {
                LOG.error(e.getMessage());
            }
            int year = birth_.getYear();
            ordPeopleTourist.setBirthOfDate(birth_);
            // 计算年龄规则
            Date nowDay = Calendar.getInstance().getTime();
            ordPeopleTourist.setAge(nowDay.getYear() - year + 1);
        }

        Date date = DateUtils.now();
        ordPeopleTourist.setAddUid(new Integer(UserSessionUtil.getUid()));
        ordPeopleTourist.setAddUname(UserSessionUtil.getNickname());
        ordPeopleTourist.setAddTime(date);
        ordPeopleTourist.setUpdateUid(new Integer(UserSessionUtil.getUid()));
        ordPeopleTourist.setUpdateUname(UserSessionUtil.getNickname());
        ordPeopleTourist.setUpdateTime(date);
    }

    /**
     * 数据迁移成功的A类订单
     */
    @Override
    public void getTransferedAOrderIds(TransferedAOrderIdsInputAndOutputVo transferedAOrderIdsInputAndOutputVo) {
        // TODO Auto-generated method stub
        // 业务逻辑
        List<OrdSalesOrder> list = ordSalesOrderMapper
                .getTransferedAOrderIds(transferedAOrderIdsInputAndOutputVo.getOrderIds());
        if (list == null || list.size() == 0) {
            transferedAOrderIdsInputAndOutputVo.setOrderIds(new ArrayList());
            return;
        }
        Set<Integer> aOrderIdlist = new HashSet<Integer>();
        Iterator<OrdSalesOrder> iter = list.iterator();
        while (iter.hasNext()) {
            OrdSalesOrder oso = iter.next();
            aOrderIdlist.add(oso.getExtOrderId());
        }
        transferedAOrderIdsInputAndOutputVo.setOrderIds(new ArrayList<Integer>(aOrderIdlist));
    }

}
