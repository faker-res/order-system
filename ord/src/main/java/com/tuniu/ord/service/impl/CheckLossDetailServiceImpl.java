package com.tuniu.ord.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.operation.framework.base.time.DateFormatUtils;
import com.tuniu.ord.common.util.BaseDomainUtil;
import com.tuniu.ord.common.util.BeanUtil;
import com.tuniu.ord.common.util.CollectionUtils;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.common.util.UserSessionUtil;
import com.tuniu.ord.common.validator.ArgumentValidator;
import com.tuniu.ord.core.exception.SaaSSystemException;
import com.tuniu.ord.core.init.SystemInitParameter;
import com.tuniu.ord.domain.CheckLoss;
import com.tuniu.ord.domain.CheckLossDetail;
import com.tuniu.ord.domain.CheckLossDiffInfo;
import com.tuniu.ord.domain.OrdDealOrder;
import com.tuniu.ord.domain.OrdSalesOrder;
import com.tuniu.ord.enums.OrderStateEnum;
import com.tuniu.ord.persistence.CheckLossDetailMapper;
import com.tuniu.ord.persistence.CheckLossDiffInfoMapper;
import com.tuniu.ord.persistence.CheckLossMapper;
import com.tuniu.ord.persistence.CheckLossTouristMapper;
import com.tuniu.ord.persistence.OrdDealOrderMapper;
import com.tuniu.ord.persistence.OrdSalesOrderMapper;
import com.tuniu.ord.service.CheckLossDetailService;
import com.tuniu.ord.vo.CheckLossAgencyInfoVo;
import com.tuniu.ord.vo.CheckLossDetailShowVo;
import com.tuniu.ord.vo.CheckLossDetailUpdateVo;
import com.tuniu.ord.vo.CheckLossDiffPriceVo;
import com.tuniu.ord.vo.CheckLossIdParamVo;
import com.tuniu.ord.vo.CheckLossResourceVo;
import com.tuniu.ord.vo.CheckLossRespDepartVo;
import com.tuniu.ord.vo.CheckLossRespVo;
import com.tuniu.ord.vo.CheckLossTouristVo;
import com.tuniu.ord.vo.OrdSaleOrderSeleVo;
import com.tuniu.ord.vo.ProductsVo;

import net.sf.json.JSONObject;

@Service(value = "checkLossDetailServiceImpl")
public class CheckLossDetailServiceImpl implements CheckLossDetailService {

    private static Logger logger = LoggerFactory.getLogger(CheckLossDetailServiceImpl.class);

    @Resource
    private CheckLossDetailMapper checkLossDetailMapper;

    @Resource
    private CheckLossMapper checkLossMapper;

    @Resource
    private CheckLossTouristMapper checkLossTouristMapper;

    @Resource
    private OrdSalesOrderMapper ordSalesOrderMapper;

    @Resource
    private CheckLossDiffInfoMapper checkLossDiffInfoMapper;

    @Resource
    private OrdDealOrderMapper dealOrderMapper;

    @SuppressWarnings("unchecked")
    @Override
    public void respCheckLossDetailToAPI(CheckLossIdParamVo input) {
        logger.debug("respCheckLossDetailToAPI:" + JsonUtil.toString(input));

        // 根据核损Id查询核损信息
        CheckLoss checkLoss = checkLossMapper.selectByPrimaryKey(input.getCheckLossId());

        // 反馈信息Vo
        CheckLossRespVo checkLossRespVo = new CheckLossRespVo();
        checkLossRespVo.setApiKey(checkLoss.getApiKey());
        checkLossRespVo.setSessionId(checkLoss.getSessionId());
        checkLossRespVo.setTimestamp(DateFormatUtils.formatDatetime(new Date()));

        List<ProductsVo> products = new ArrayList<ProductsVo>();
        ProductsVo productsVo = new ProductsVo();

        // 根据核损Id查询核损详情
        List<CheckLossDetail> checkLossDetailList = checkLossDetailMapper.selectCheckLossDetailList(input);
        if (!CollectionUtils.isNotEmpty(checkLossDetailList)) {
            throw new SaaSSystemException("核损详情为空");
        }

        // 供应商信息
        CheckLossAgencyInfoVo agencyInfo = new CheckLossAgencyInfoVo();
        agencyInfo.setAgencyLossSchemeId(checkLoss.getId());
        agencyInfo.setEffectiveTime(DateFormatUtils.formatDatetime(checkLossDetailList.get(0).getExpireTime()));
        agencyInfo.setRemark(checkLossDetailList.get(0).getRemark());
        agencyInfo.setAgencyOwnerId(UserSessionUtil.getUid());
        agencyInfo.setAgencyOwnerName(UserSessionUtil.getNickname());

        List<CheckLossRespDepartVo> departs = new ArrayList<CheckLossRespDepartVo>();

        List<CheckLossDiffInfo> checkLossDiffInfos = checkLossDiffInfoMapper.queryInfoByCheckLossId(input.getCheckLossId());
        if (CollectionUtils.isNotEmpty(checkLossDiffInfos)) {
            Set<Integer> channelDealOrderIdSet = new HashSet<Integer>();
            for (CheckLossDiffInfo checkLossDiffInfo : checkLossDiffInfos) {
                channelDealOrderIdSet.add(checkLossDiffInfo.getChannelDealOrderId());
            }
            // CheckLossRespTemp
            for (Integer dealOrderId : channelDealOrderIdSet) {
                CheckLossRespDepartVo cldv = new CheckLossRespDepartVo();
                for (CheckLossDiffInfo checkLossDiffInfo : checkLossDiffInfos) {
                    if (checkLossDiffInfo.getChannelDealOrderId().intValue() == dealOrderId) {
                        cldv.setAdultNum(checkLossDiffInfo.getLossAdultNum());
                        cldv.setChildNum(checkLossDiffInfo.getLossChildNum());
                        break;
                    }
                }
                cldv.setDate(DateFormatUtils.formatDate(checkLoss.getTourDate()));

                List<BigDecimal> adultPriceList = new ArrayList<BigDecimal>();
                List<BigDecimal> childPriceList = new ArrayList<BigDecimal>();
                List<CheckLossDiffPriceVo> checkLossDiffPriceVos = checkLossDiffInfoMapper
                        .queryCheckLossDIffPrice(input.getCheckLossId());
                for (CheckLossDiffPriceVo checkLossDiffPriceVo : checkLossDiffPriceVos) {
                    if (dealOrderId.intValue() == checkLossDiffPriceVo.getChannelDealOrderId().intValue()) {
                        adultPriceList.add(checkLossDiffPriceVo.getLossAdultCustomer());
                        childPriceList.add(checkLossDiffPriceVo.getLossChildCustomer());
                    }
                }

                cldv.setLossAdultAgency(getAveragePirce(adultPriceList));
                cldv.setLossChildAgency(getAveragePirce(childPriceList));
                cldv.setLossAdultCustomer(BigDecimal.ZERO);
                cldv.setLossBabyCustomer(BigDecimal.ZERO);
                cldv.setLossChildCustomer(BigDecimal.ZERO);

                OrdDealOrder ordDealOrder = dealOrderMapper.selectByOrderId(dealOrderId);
                cldv.setRoundId(ordDealOrder.getExtBatchId());
                if (ordDealOrder.getDealOrderType().intValue() == 1) {
                    cldv.setStockType(1);
                } else if (ordDealOrder.getDealOrderType().intValue() == 2) {
                    cldv.setStockType(6);
                } else if (ordDealOrder.getDealOrderType().intValue() == 3) {
                    cldv.setStockType(2);
                }
                cldv.setExtPurchaseId(dealOrderId);
                departs.add(cldv);
            }
        } else {
            for (CheckLossDetail cld : checkLossDetailList) {
                CheckLossRespDepartVo cldv = new CheckLossRespDepartVo();
                cldv.setChildNum(new Integer(cld.getChildCountBefore().intValue() - cld.getChildCountAfter().intValue()));
                cldv.setAdultNum(new Integer(cld.getAdultCountBefore().intValue() - cld.getAdultCountAfter().intValue()));
                cldv.setBabyNum(new Integer(cld.getBabyCountBefore().intValue() - cld.getBabyCountAfter().intValue()));
                cldv.setDate(DateFormatUtils.formatDate(checkLoss.getTourDate()));
                cldv.setLossAdultAgency(cld.getLossAdultCustomer());
                cldv.setLossChildAgency(cld.getLossChildCustomer());
                cldv.setLossBabyAgency(cld.getLossBabyCustomer());
                cldv.setLossAdultCustomer(BigDecimal.ZERO);
                cldv.setLossBabyCustomer(BigDecimal.ZERO);
                cldv.setLossChildCustomer(BigDecimal.ZERO);
                cldv.setRoundId(cld.getBatchNumber());
                cldv.setStockType(cld.getStockType());
                cldv.setExtPurchaseId(cld.getDealOrderId());
                departs.add(cldv);
            }
        }

        // resource
        CheckLossResourceVo clr = new CheckLossResourceVo();
        // 获取核损单详情需要的部分关联信息
        List<CheckLossDetailShowVo> showList = checkLossDetailMapper.selectDetailInfo(input);
        if (CollectionUtils.isNotEmpty(showList)) {
            clr.setCostCurrencyType(showList.get(0).getCurrencyType());
            clr.setLossAdultAgencyExtra(BigDecimal.ZERO);
            clr.setLossAdultCustomerExtra(BigDecimal.ZERO);
            clr.setLossBabyAgencyExtra(BigDecimal.ZERO);
            clr.setLossChildAgencyExtra(BigDecimal.ZERO);
            clr.setLossBabyCustomerExtra(BigDecimal.ZERO);
            clr.setLossChildCustomerExtra(BigDecimal.ZERO);
            clr.setDepartDates(departs);
        }
        productsVo.setAgencyInfo(agencyInfo);
        productsVo.setResource(clr);
        products.add(productsVo);
        checkLossRespVo.setProducts(products);

        // 将checkLossRespVo转化成json字符串，然后生成treeMap进行一级key排序
        String checkLossRespStr = JsonUtil.toString(checkLossRespVo);

        Map<String, Object> hashMap = JsonUtil.toBean(checkLossRespStr, Map.class);
        hashMap.put("success", new Boolean(true));

        logger.debug("发送核损出参before" + (JSONObject.fromObject(hashMap)));
        String sign = null;
        try {
            sign = BeanUtil.getSignature(JSONObject.fromObject(hashMap), SystemInitParameter.appSecretForAPI);
            logger.debug("签名结果:" + sign);
        } catch (Exception e) {
            throw new SaaSSystemException(e.getMessage());
        }
        hashMap.put("sign", sign);
        logger.debug("发送核损出参after" + JsonUtil.toString(hashMap));

        // 传参到A
        String respFromA = RestUtil.executeToAPI(SystemInitParameter.checkRespToAPI, "POST", JsonUtil.toString(hashMap));
        logger.debug("A类出参：" + respFromA);
        if (respFromA != null) {
            Map<String, Object> resultMap = JsonUtil.toBean(respFromA, Map.class);
            if ((Boolean) resultMap.get("success")) {
                checkLoss.setRespTime(new Date());
                checkLoss.setStatus("1");
                BaseDomainUtil.setBasePropertyValue(checkLoss);
                // 修改核损信息的反馈时间
                checkLossMapper.updateByPrimaryKeySelective(checkLoss);

                // 修改销售订单状态为已反馈核损
                OrdSaleOrderSeleVo ordSaleOrderSeleVo = new OrdSaleOrderSeleVo();
                ordSaleOrderSeleVo.setRequirementId(checkLoss.getRequirementId());
                ordSaleOrderSeleVo.setSellOrderId(checkLoss.getSellOrderId());
                List<OrdSalesOrder> ordSalesOrders = ordSalesOrderMapper.selectBySaleOrdleIdAndRequirement(ordSaleOrderSeleVo);
                for (OrdSalesOrder ordSalesOrder : ordSalesOrders) {
                    ordSalesOrder.setStatusCode(OrderStateEnum.CONFIRMLOSS.getStatusCode());
                    ordSalesOrderMapper.updateByPrimaryKeySelective(ordSalesOrder);
                }
            } else {
                throw new SaaSSystemException((String) resultMap.get("msg"));
            }
        } else {
            throw new SaaSSystemException("核损同步反馈结果为空");
        }
    }

    private BigDecimal getAveragePirce(List<BigDecimal> price) {
        BigDecimal total = BigDecimal.ZERO;
        for (BigDecimal decimal : price) {
            total = total.add(decimal);
        }

        return total.divide(new BigDecimal(price.size()), 2, RoundingMode.HALF_UP);
    }

    public static class CheckLossRespTemp {
        private Integer dealOrderId;
        private Integer adultNum;
        private Integer childNum;

        public Integer getDealOrderId() {
            return dealOrderId;
        }

        public void setDealOrderId(Integer dealOrderId) {
            this.dealOrderId = dealOrderId;
        }

        public Integer getAdultNum() {
            return adultNum;
        }

        public void setAdultNum(Integer adultNum) {
            this.adultNum = adultNum;
        }

        public Integer getChildNum() {
            return childNum;
        }

        public void setChildNum(Integer childNum) {
            this.childNum = childNum;
        }

    }

    @Override
    public CheckLossDetailShowVo queryCheckLossDetailById(CheckLossIdParamVo input) {
        CheckLoss checkLoss = checkLossMapper.selectByPrimaryKey(input.getCheckLossId());
        if (checkLoss == null) {
            throw new SaaSSystemException("核损单为空");
        }

        CheckLossDetailShowVo checkLossDetailShowVo = new CheckLossDetailShowVo();

        List<CheckLossDetailShowVo> detailShowVoList = checkLossDetailMapper.selectDetailInfo(input);
        if (CollectionUtils.isNotEmpty(detailShowVoList)) {
            checkLossDetailShowVo = detailShowVoList.get(0);
        }

        // 根据核损id获取核损游客信息
        CheckLossTouristVo checkLossTouristVo = new CheckLossTouristVo();
        checkLossTouristVo.setCheckLossId(input.getCheckLossId());
        List<CheckLossTouristVo> touristList = checkLossTouristMapper
                .selectCheckLossTouristByCheckLossIdAndPsptId(checkLossTouristVo);

        List<String> CheckLossTouristName = new ArrayList<String>(touristList.size());
        for (CheckLossTouristVo touristInfo : touristList) {
            CheckLossTouristName.add(touristInfo.getTouristName());
        }
        checkLossDetailShowVo.setTourInfo(StringUtils.collectionToDelimitedString(CheckLossTouristName, ","));

        List<Map<String, Object>> roundList = new ArrayList<Map<String, Object>>();
        List<CheckLossDetail> checkLossDetailList = checkLossDetailMapper.selectCheckLossDetailList(input);
        if (CollectionUtils.isNotEmpty(checkLossDetailList)) {
            int tourAdultBefore = 0;
            int tourChildBefore = 0;
            int tourAdultAfter = 0;
            int tourChildAfter = 0;
            for (CheckLossDetail checkLossDetail : checkLossDetailList) {
                // 变更前成人人数
                tourAdultBefore = tourAdultBefore + checkLossDetail.getAdultCountBefore().intValue();

                // 变更前儿童人数
                tourChildBefore = tourChildBefore + checkLossDetail.getChildCountBefore().intValue();

                // 变更后成人人数
                tourAdultAfter = tourAdultAfter + checkLossDetail.getAdultCountAfter().intValue();

                // 变更后儿童人数
                tourChildAfter = tourChildAfter + checkLossDetail.getChildCountAfter().intValue();

                HashMap<String, Object> roundMap = new HashMap<String, Object>();
                roundMap.put("resellFlag", checkLossDetail.getResellFlag());
                roundMap.put("roundId", checkLossDetail.getBatchNumber());
                roundMap.put("adultCountBefore", checkLossDetail.getAdultCountBefore());
                roundMap.put("childCountBefore", checkLossDetail.getChildCountBefore());
                roundMap.put("babyCountBefore", checkLossDetail.getBabyCountBefore());
                roundMap.put("adultCountAfter", checkLossDetail.getAdultCountAfter());
                roundMap.put("childCountAfter", checkLossDetail.getChildCountAfter());
                roundMap.put("babyCountAfter", checkLossDetail.getBabyCountAfter());
                roundMap.put("lossAdultCustomer", checkLossDetail.getLossAdultCustomer());
                roundMap.put("lossChildCustomer", checkLossDetail.getLossChildCustomer());
                roundMap.put("lossBabyCustomer", checkLossDetail.getLossBabyCustomer());
                List<CheckLossTouristVo> tourList = new ArrayList<CheckLossTouristVo>();
                for (CheckLossTouristVo tourist : touristList) {
                    if (tourist.getBranchId().intValue() == checkLossDetail.getDealOrderId().intValue()) {
                        tourList.add(tourist);
                    }
                }
                if (null != checkLossDetail.getExpireTime()) {
                    checkLossDetailShowVo.setExpireTime(DateFormatUtils.formatDatetime(checkLossDetail.getExpireTime()));
                } else {
                    checkLossDetailShowVo.setExpireTime("");
                }
                checkLossDetailShowVo.setRemark(checkLossDetail.getRemark());
                roundMap.put("touristList", tourList);
                roundList.add(roundMap);
            }
            checkLossDetailShowVo.setTourRequirement("变更前：" + tourAdultBefore + "大" + tourChildBefore + "小" + "  变更后："
                    + tourAdultAfter + "大" + tourChildAfter + "小");
        }
        checkLossDetailShowVo.setRoundList(roundList);
        if ("0".equals(checkLossDetailShowVo.getStatus())) {
            checkLossDetailShowVo.setStatus("未处理");
        } else {
            checkLossDetailShowVo.setStatus("已处理");
        }

        checkLossDetailShowVo.setCheckLossType(checkLoss.getCheckLossType());
        return checkLossDetailShowVo;
    }

    @Override
    public boolean updateCheckLossDetail(CheckLossDetailUpdateVo checkLossDetailUpdateVo) {
        try {
            CheckLossIdParamVo checkLossIdParamVo = new CheckLossIdParamVo();
            checkLossIdParamVo.setCheckLossId(checkLossDetailUpdateVo.getCheckLossId());
            // 根据核损Id获取核损详情信息
            List<CheckLossDetail> checkLossDetailList = checkLossDetailMapper.selectCheckLossDetailList(checkLossIdParamVo);
            if (CollectionUtils.isNotEmpty(checkLossDetailList)) {
                for (int i = 0; i < checkLossDetailList.size(); i++) {
                    for (Integer roundId : checkLossDetailUpdateVo.getRoundId()) {
                        if (roundId.intValue() == checkLossDetailList.get(i).getDealOrderId().intValue()) {
                            // 判断核损单价是否为null,不是则赋值，如果是默认为0
                            BigDecimal tempDec = checkLossDetailUpdateVo.getLossAdultCustomer().get(i);
                            checkLossDetailList.get(i).setLossAdultCustomer(tempDec == null ? new BigDecimal(0) : tempDec);

                            tempDec = checkLossDetailUpdateVo.getLossChildCustomer().get(i);
                            checkLossDetailList.get(i).setLossChildCustomer(tempDec == null ? new BigDecimal(0) : tempDec);
                            checkLossDetailList.get(i).setResellFlag(checkLossDetailUpdateVo.getResellFlag().get(i));
                            checkLossDetailList.get(i).setRemark(checkLossDetailUpdateVo.getRemark());
                            checkLossDetailList.get(i).setAddUid(new Integer(UserSessionUtil.getUid()));
                            checkLossDetailList.get(i).setAddUname(UserSessionUtil.getNickname());
                            checkLossDetailList.get(i).setUpdateUid(new Integer(UserSessionUtil.getUid()));
                            checkLossDetailList.get(i).setUpdateUname(UserSessionUtil.getNickname());
                            try {
                                checkLossDetailList.get(i)
                                        .setExpireTime(DateFormatUtils.parseDatetime(checkLossDetailUpdateVo.getExpireTime()));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            // 更新核损详情信息
                            checkLossDetailMapper.updateByPrimaryKeySelective(checkLossDetailList.get(i));
                            CheckLoss checkLoss = checkLossMapper.selectByPrimaryKey(checkLossDetailUpdateVo.getCheckLossId());
                            ArgumentValidator.isNotNull("checkLoss", checkLoss);
                            checkLoss.setUpdateUid(new Integer(UserSessionUtil.getUid()));
                            checkLoss.setUpdateUname(UserSessionUtil.getNickname());
                            checkLoss.setRespTime(new Date());
                            checkLossMapper.updateByPrimaryKey(checkLoss);
                        }
                    }
                }
                return true;
            }
        } catch (Exception e) {
            logger.error("更新核损单失败", e.getMessage());
            return false;
        }

        return true;
    }

}
