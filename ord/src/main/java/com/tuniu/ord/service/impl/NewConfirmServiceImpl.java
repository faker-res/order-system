/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年9月27日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.operation.framework.base.time.DateUtils;
import com.tuniu.operation.framework.base.util.SystemConstants;
import com.tuniu.ord.common.constant.Constants;
import com.tuniu.ord.common.util.BaseDomainUtil;
import com.tuniu.ord.common.util.BeanUtil;
import com.tuniu.ord.common.util.CollectionUtils;
import com.tuniu.ord.common.util.EnvironmentUtil;
import com.tuniu.ord.common.util.ExternalRestUtil;
import com.tuniu.ord.common.util.SessionUtil;
import com.tuniu.ord.common.util.UserSessionUtil;
import com.tuniu.ord.core.datasource.TSPEnumUtil;
import com.tuniu.ord.core.exception.SaaSSystemException;
import com.tuniu.ord.core.mail.client.DPSSys;
import com.tuniu.ord.core.mail.client.GRPSys;
import com.tuniu.ord.core.mail.client.ORDMailClient;
import com.tuniu.ord.core.mail.client.SCSSys;
import com.tuniu.ord.core.mail.client.STKSys;
import com.tuniu.ord.domain.CheckLoss;
import com.tuniu.ord.domain.CheckLossDetail;
import com.tuniu.ord.domain.CheckLossTourist;
import com.tuniu.ord.domain.OrdDealOrder;
import com.tuniu.ord.domain.OrdPeopleTourist;
import com.tuniu.ord.domain.OrdPriceDetail;
import com.tuniu.ord.domain.OrdSalesOrder;
import com.tuniu.ord.enums.CheckModeEnum;
import com.tuniu.ord.enums.OrderStateEnum;
import com.tuniu.ord.enums.ProductCategoryEnum;
import com.tuniu.ord.enums.StockTypeEnum;
import com.tuniu.ord.enums.SupplierEnum;
import com.tuniu.ord.persistence.CheckLossDetailMapper;
import com.tuniu.ord.persistence.CheckLossMapper;
import com.tuniu.ord.persistence.CheckLossTouristMapper;
import com.tuniu.ord.persistence.OrdDealOrderMapper;
import com.tuniu.ord.persistence.OrdPeopleTouristMapper;
import com.tuniu.ord.persistence.OrdPriceDetailMapper;
import com.tuniu.ord.persistence.OrdSalesOrderMapper;
import com.tuniu.ord.proxy.APIProxy;
import com.tuniu.ord.proxy.DPSProxy;
import com.tuniu.ord.proxy.GRPProxy;
import com.tuniu.ord.proxy.PRDProxy;
import com.tuniu.ord.proxy.SCSProxy;
import com.tuniu.ord.proxy.StockProxy;
import com.tuniu.ord.service.IOrderIdServiceClient;
import com.tuniu.ord.service.NewConfirmService;
import com.tuniu.ord.vo.CancelConfirmInputVo;
import com.tuniu.ord.vo.CancelConfirmResult;
import com.tuniu.ord.vo.CancelSubConfirmResult;
import com.tuniu.ord.vo.CheckLossTouristVo;
import com.tuniu.ord.vo.ConfirmFeedBackInputVo;
import com.tuniu.ord.vo.ConfirmInputVo;
import com.tuniu.ord.vo.ConfirmRequestQueryVo;
import com.tuniu.ord.vo.ConfirmSalesQueryVo;
import com.tuniu.ord.vo.ConsumerVo;
import com.tuniu.ord.vo.DPSDeptInfo;
import com.tuniu.ord.vo.DProFSVo;
import com.tuniu.ord.vo.DProductStock;
import com.tuniu.ord.vo.DealConfirmResult;
import com.tuniu.ord.vo.DealSubConfirmResult;
import com.tuniu.ord.vo.DepartDate;
import com.tuniu.ord.vo.DepartDateAPI;
import com.tuniu.ord.vo.DepartDateFSVo;
import com.tuniu.ord.vo.DepartDateStock;
import com.tuniu.ord.vo.LossApplyInfoDataVo;
import com.tuniu.ord.vo.LossApplyInfoVo;
import com.tuniu.ord.vo.NewDepartDate;
import com.tuniu.ord.vo.OrderSynInfo;
import com.tuniu.ord.vo.PeopleTouristVo;
import com.tuniu.ord.vo.ProBaseOutputVo;
import com.tuniu.ord.vo.ProBaseVo;
import com.tuniu.ord.vo.Product;
import com.tuniu.ord.vo.ProductAPI;
import com.tuniu.ord.vo.ProductIdParamVo;
import com.tuniu.ord.vo.ProductStockVo;
import com.tuniu.ord.vo.QueryRelationsIdOutputVo;
import com.tuniu.ord.vo.QueryRelationsIdVo;
import com.tuniu.ord.vo.ResProVo;
import com.tuniu.ord.vo.ResponseVo;
import com.tuniu.ord.vo.ResultVo;
import com.tuniu.ord.vo.SaleVo;
import com.tuniu.ord.vo.SalesConfirmVo;
import com.tuniu.ord.vo.StockInputFSVo;
import com.tuniu.ord.vo.StockOccupyInfoOutput;
import com.tuniu.ord.vo.StockOccupyOutputVo;
import com.tuniu.ord.vo.StockOccupyQueryVo;
import com.tuniu.ord.vo.StockOutOutputVo;
import com.tuniu.ord.vo.StockOutQueryVo;
import com.tuniu.ord.vo.TourBaseInputVo;
import com.tuniu.ord.vo.TourBaseOutputVo;
import com.tuniu.ord.vo.Tourist;
import com.tuniu.ord.vo.TuniuInfo;
import com.tuniu.ord.vo.common.ExternalResponseObj;
import com.tuniu.ord.vo.external.QueryGroupOutputVo;

import net.sf.json.JSONObject;

/**
 * @author zhairongping
 *
 */
@Service
public class NewConfirmServiceImpl implements NewConfirmService {
    private static final Logger LOG = LoggerFactory.getLogger(NewConfirmServiceImpl.class);

    @Resource
    private OrdSalesOrderMapper ordSalesOrderMapper;

    @Resource
    private OrdPeopleTouristMapper ordPeopleTouristMapper;

    @Resource
    private IOrderIdServiceClient iOrderIdServiceImplClient;

    @Resource
    private GRPProxy gRPProxy;

    @Resource
    private StockProxy stockProxy;

    @Resource
    private SCSProxy sCSProxy;

    /*************************************************/
    @Resource
    private OrdDealOrderMapper ordDealOrderMapper;

    @Resource
    private PRDProxy pRDProxy;

    @Resource
    private DPSProxy dPSProxy;

    @Resource
    private APIProxy aPIProxy;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /*****************************************************/
    @Resource
    private CheckLossDetailMapper checkLossDetailMapper;

    @Resource
    private CheckLossTouristMapper checkLossTouristMapper;

    @Resource
    private CheckLossMapper checkLossMapper;

    @Resource
    private OrdPriceDetailMapper ordPriceDetailMapper;

    /*
     * (non-Javadoc) 发起确认校验
     * 
     * @see com.tuniu.ord.service.NewConfirmService#validate(com.tuniu.ord.vo.ConfirmInputVo)
     */
    @Override
    public ResultVo validateNewInitiateConfirm(ConfirmInputVo confirmInputVo) {
        ResultVo resultVo = new ResultVo();
        if (confirmInputVo.getProducts() == null || confirmInputVo.getProducts().size() == 0) {
            resultVo.setSuccess(false);
            resultVo.setMsg("products产品信息不能为空");
            return resultVo;
        }
        Product product = confirmInputVo.getProducts().get(0);
        List<DepartDate> departDates = product.getDepartDates();
        if (departDates == null || departDates.size() == 0) {
            resultVo.setSuccess(false);
            resultVo.setMsg("departDatesA类团期信息不能为空");
            return resultVo;
        }
        for (DepartDate departDate : departDates) {

            // 库存类型 为FS,不进行下面校验规则
            if (departDate.getStockType() != null && departDate.getStockType().intValue() == StockTypeEnum.Free_Sale.getKey()) {
                // 根据A资源查询D产品ID
                ResProVo resProVo = new ResProVo();
                resProVo.setResId(departDate.getResourceId());
                ResponseVo result = pRDProxy.queryProByResId(resProVo);
                if (!result.isSuccess()) {
                    resultVo.setSuccess(false);
                    resultVo.setMsg(result.getMsg());
                    return resultVo;
                }
                continue;
            }

            // 追加校验规则
            Integer DOrderId = departDate.getExtPurchaseId();
            if (DOrderId != null && DOrderId.intValue() != 0) {
                OrdDealOrder ordDealOrder2 = ordDealOrderMapper.selectByOrderId(DOrderId);
                if (null == ordDealOrder2) {
                    resultVo.setSuccess(false);
                    resultVo.setMsg("切位单:" + DOrderId + "查询不到D类订单");
                    return resultVo;
                }
            } else {
                String roundId = departDate.getRoundId();
                if (null == roundId || "".equals(roundId)) {
                    resultVo.setSuccess(false);
                    resultVo.setMsg("A资源入库批次号不能为空");
                    return resultVo;
                }
                // 根据A资源入库批次号查询D类订单、产品ID和产品名称以及责任人
                OrdDealOrder ordDealOrder = ordDealOrderMapper.getOrdDealOrderByRoundId(Integer.valueOf(roundId));
                if (null == ordDealOrder) {
                    resultVo.setSuccess(false);
                    resultVo.setMsg("A资源入库批次号:" + roundId + "查询不到D类订单");
                    return resultVo;
                }
            }

        }
        TuniuInfo tuniuInfo = product.getTuniuInfo();
        if (tuniuInfo == null) {
            resultVo.setSuccess(false);
            resultVo.setMsg("tuniuInfo供应商不能为空");
            return resultVo;
        }
        Integer tuniuOrderId = tuniuInfo.getTuniuOrderId();
        Integer requirementId = tuniuInfo.getRequirementId();
        if (tuniuOrderId == null || tuniuOrderId.intValue() == 0) {
            resultVo.setSuccess(false);
            resultVo.setMsg("tuniuOrderId途牛订单号不能为空");
            return resultVo;
        }
        if (requirementId == null || requirementId.intValue() == 0) {
            resultVo.setSuccess(false);
            resultVo.setMsg("requirementId需求id不能为空");
            return resultVo;
        }
        // 保证同一个发起确认请求只处理一次
        ConfirmRequestQueryVo confirmRequestQueryVo = new ConfirmRequestQueryVo();
        confirmRequestQueryVo.setTuniuOrderId(tuniuOrderId);
        confirmRequestQueryVo.setRequirementId(requirementId);
        int countNum = ordSalesOrderMapper.countConfirmRequest(confirmRequestQueryVo);
        if (countNum > 0) {// 已处理该请求，直接返回
            resultVo.setSuccess(false);
            resultVo.setMsg("已处理过请求");
            return resultVo;
        }
        List<Tourist> tourist = product.getTourist();
        if (tourist == null || tourist.size() == 0) {
            resultVo.setSuccess(false);
            resultVo.setMsg("出游人列表不能为空");
            return resultVo;
        }

        String touristName = null;
        Integer psptType = null;
        String touristId = null;
        for (Tourist tst : tourist) {
            touristName = tst.getTouristName();
            psptType = tst.getPsptType();
            touristId = tst.getTouristId();
            if (null == touristName || "".equals(touristName)) {
                resultVo.setSuccess(false);
                resultVo.setMsg("游客姓名必填");
                return resultVo;
            }
            if (null == psptType) {
                resultVo.setSuccess(false);
                resultVo.setMsg("游客证件类型必填");
                return resultVo;
            }
            if (null == touristId || "".equals(touristId)) {
                resultVo.setSuccess(false);
                resultVo.setMsg("游客ID必填");
                return resultVo;
            }
        }

        Integer countDepartDateNumber = new Integer(0);
        Integer countTouristNumber = new Integer(tourist.size());
        for (DepartDate departDate : departDates) {
            countDepartDateNumber += departDate.getAffirmAdultNum() + departDate.getAffirmChildNum()
                    + departDate.getAffirmBabyNum();
        }
        if (countDepartDateNumber.intValue() > countTouristNumber.intValue()) {
            resultVo.setSuccess(false);
            // resultVo.setMsg("所有批次总人数必须和出游总人数相等");
            resultVo.setMsg("所有批次总人数(位置数)不能大于出游总人数");
            return resultVo;
        }
        if (countDepartDateNumber.intValue() == countTouristNumber.intValue()) {
            resultVo.setOpeFlag(true);
        } else {
            resultVo.setOpeFlag(false);
        }
        return resultVo;
    }

    /*
     * 重新组装批次和出游人 (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.NewConfirmService#rebuilt(com.tuniu.ord.vo.Product)
     */
    @Override
    public List<NewDepartDate> rebuilt(Product product) {
        // 循环批次,计算批次总数，取出游人的对应批次人数
        List<NewDepartDate> list = new ArrayList<NewDepartDate>();
        List<DepartDate> departDates = product.getDepartDates();
        List<Tourist> tourists = product.getTourist();
        int j = 0;
        for (int n = 0; n < departDates.size(); n++) {
            NewDepartDate newDD = new NewDepartDate();
            DepartDate ordDD = departDates.get(n);
            copyProperty(newDD, ordDD);
            int countNum = ordDD.getAffirmAdultNum().intValue() + ordDD.getAffirmChildNum().intValue()
                    + ordDD.getAffirmBabyNum().intValue();
            for (int i = 0; i < countNum; i++) {
                newDD.getTouristList().add(tourists.get(j));
                j++;
            }
            list.add(newDD);
        }
        return list;
    }

    /*
     * 单批次处理发起确认逻辑并记录处理结果(不添加事务) (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.NewConfirmService#dealConfirm(com.tuniu.ord.vo.NewDepartDate, com.tuniu.ord.vo.ConfirmInputVo,
     * com.tuniu.ord.vo.DealConfirmResult)
     */
    @Override
    public void dealConfirm(NewDepartDate newDepartDate, ConfirmInputVo confirmInputVo, DealConfirmResult dealConfirmResult) {
        DealSubConfirmResult dealSubConfirmResult = new DealSubConfirmResult();

        try {
            dealStockType(newDepartDate, confirmInputVo);
        } catch (Exception ex) {
            LOG.error("FS的特殊处理失败:" + ex.getMessage(), ex);
        }

        // 添加销售单
        OrdSalesOrder ordSalesOrder = new OrdSalesOrder();
        initOrdSalesOrderParam(ordSalesOrder, newDepartDate, confirmInputVo, dealSubConfirmResult);
        ordSalesOrderMapper.insertSelective(ordSalesOrder);
        TuniuInfo tuniuInfo = confirmInputVo.getProducts().get(0).getTuniuInfo();
        LOG.info("落库后订单号:{},外部订单号:{},需求号:{}", ordSalesOrder.getId(), tuniuInfo.getTuniuOrderId(), tuniuInfo.getRequirementId());
        // 记录日志
        dealSubConfirmResult.setdOrderId(ordSalesOrder.getId());
        dealSubConfirmResult.setRoundId(newDepartDate.getRoundId());
        dealSubConfirmResult.getSteps().add("【step1】添加销售单" + ordSalesOrder.getId() + "成功");

        // 添加出游人
        for (Tourist tourist : newDepartDate.getTouristList()) {
            OrdPeopleTourist ordPeopleTourist = new OrdPeopleTourist();
            initOrdPeopleTouristParam(ordPeopleTourist, ordSalesOrder.getId(), tourist, dealSubConfirmResult);
            ordPeopleTouristMapper.insertSelective(ordPeopleTourist);
            tourist.setConsumerId(ordPeopleTourist.getId());
        }

        // 记录日志
        dealSubConfirmResult.getSteps().add("【step2】添加出游人成功");

        // 签约出库
        ProductStockVo productStockVo = new ProductStockVo();
        initProductStockParam(productStockVo, newDepartDate, confirmInputVo, ordSalesOrder.getId(), dealSubConfirmResult);
        ResponseVo result = stockProxy.productContractSign(productStockVo);

        // 记录日志
        if (result.isSuccess()) {
            dealSubConfirmResult.getSteps().add("【step4】签约出库成功");
        } else {
            dealSubConfirmResult.getSteps().add("【step4】签约出库" + TSPEnumUtil.changeTSPName(SystemConstants.PRODUCT_CONTRACT_SIGN)
                    + "失败:" + result.getMsg() + ",请求入参:" + JsonUtil.toString(productStockVo));
            if (EnvironmentUtil.isPrdEnv()) {
                ORDMailClient
                        .sendMailToSystem(new STKSys("签约出库" + TSPEnumUtil.changeTSPName(SystemConstants.PRODUCT_CONTRACT_SIGN)
                                + "失败:" + result.getMsg() + ",请求入参:" + JsonUtil.toString(productStockVo)));
            }
        }

        // 同步订单信息给财务系统
        OrderSynInfo orderSynInfo = new OrderSynInfo();
        initOrderSynInfoParam(orderSynInfo, newDepartDate, confirmInputVo, ordSalesOrder.getId(), dealSubConfirmResult);
        ResponseVo sCsRes = sCSProxy.synOrder(orderSynInfo);

        // 记录日志
        if (sCsRes.isSuccess()) {
            dealSubConfirmResult.getSteps().add("【step6】同步订单信息给财务系统成功");
        } else {
            dealSubConfirmResult.getSteps().add("【step6】同步订单信息给财务系统" + SystemConstants.SYN_ORDER + "失败:" + sCsRes.getMsg()
                    + ",请求入参:" + JsonUtil.toString(orderSynInfo));
            if (EnvironmentUtil.isPrdEnv()) {
                ORDMailClient.sendMailToSystem(new SCSSys("同步订单信息给财务系统" + SystemConstants.SYN_ORDER + "失败:" + sCsRes.getMsg()
                        + ",请求入参:" + JsonUtil.toString(orderSynInfo)));
            }
        }

        // 向组团系统下销售单
        SalesConfirmVo salesConfirmVo = new SalesConfirmVo();
        initSalesConfirmParam(salesConfirmVo, newDepartDate, confirmInputVo, ordSalesOrder.getId(), dealSubConfirmResult);
        ResponseVo grpRes = gRPProxy.sendSalesConfirm(salesConfirmVo);

        // 记录日志
        if (grpRes.isSuccess()) {
            dealSubConfirmResult.getSteps().add("【step7】向组团系统下销售单成功");
        } else {
            dealSubConfirmResult.getSteps()
                    .add("【step7】向组团系统下销售单失败:" + grpRes.getMsg() + ",请求入参:" + JsonUtil.toString(salesConfirmVo));
            if (EnvironmentUtil.isPrdEnv()) {
                ORDMailClient.sendMailToSystem(
                        new GRPSys("向组团系统下销售单失败:" + grpRes.getMsg() + ",请求入参:" + JsonUtil.toString(salesConfirmVo)));
            }
        }

        dealConfirmResult.getRoundList().add(dealSubConfirmResult);
    }

    /*
     * 异步反馈 (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.NewConfirmService#confirmFeedBack(com.tuniu.ord.vo.ConfirmFeedBackInputVo)
     */
    @Override
    public void confirmFeedBack(ConfirmFeedBackInputVo confirmFeedBackInputVo, DealConfirmResult dealConfirmResult) {
        ResponseVo responseVo = aPIProxy.confirmFeedBack(confirmFeedBackInputVo);
        if (responseVo.isSuccess()) {
            dealConfirmResult.setFeedBack("调用API确认反馈成功");
        } else {
            dealConfirmResult.setFeedBack("调用API确认反馈失败:" + responseVo.getErrorMsg());
        }
    }

    /*
     * 取消确认校验 (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.NewConfirmService#validateNewCancelConfirm(com.tuniu.ord.vo.CancelConfirmInputVo)
     */
    @Override
    public ResultVo validateNewCancelConfirm(CancelConfirmInputVo cancelConfirmInputVo) {
        ResultVo resultVo = new ResultVo();
        // 核损方案号
        Integer agencyLossSchemeId = null;
        try {
            agencyLossSchemeId = cancelConfirmInputVo.getAgencyInfo().getAgencyLossSchemeId();
        } catch (Exception ex) {
            StringBuffer sb = new StringBuffer();
            sb.append("【取消确认请求参数有问题,");
            sb.append("请核实取消确认请求参数:" + JsonUtil.toString(cancelConfirmInputVo) + "】");
            resultVo.setSuccess(false);
            resultVo.setMsg(sb.toString());
            return resultVo;
        }

        if (null == agencyLossSchemeId) {
            StringBuffer sb = new StringBuffer();
            sb.append("【取消确认请求参数中核损方案号为空,");
            sb.append("取消确认请求参数:" + JsonUtil.toString(cancelConfirmInputVo) + "】");
            resultVo.setSuccess(false);
            resultVo.setMsg(sb.toString());
            return resultVo;
        }

        // 根据核损单号获取很损详情列表
        List<CheckLossDetail> list = checkLossDetailMapper.getCheckLossNum(agencyLossSchemeId);
        if (!CollectionUtils.isNotEmpty(list)) {
            StringBuffer sb = new StringBuffer();
            sb.append("【核损方案号:" + agencyLossSchemeId + "不存在;");
            sb.append("取消确认请求参数:" + JsonUtil.toString(cancelConfirmInputVo) + "】");
            resultVo.setSuccess(false);
            resultVo.setMsg(sb.toString());
            return resultVo;
        }

        CheckLoss checkLossCheck = checkLossMapper.selectByPrimaryKey(agencyLossSchemeId);
        if (null == checkLossCheck) {
            StringBuffer sb = new StringBuffer();
            sb.append("【根据核损核损方案号查询售卖订单号和需求ID出现异常");
            sb.append("取消确认请求参数:" + JsonUtil.toString(cancelConfirmInputVo) + "】");
            resultVo.setSuccess(false);
            resultVo.setMsg(sb.toString());
            return resultVo;
        }

        for (CheckLossDetail checkLossDetail : list) {

            // 根据A资源入库批次号或者切位单号查询切位单
            Integer orderId = checkLossDetail.getDealOrderId();
            OrdDealOrder ordDealOrderCheck = null;
            if (orderId != null && orderId.intValue() > 0) {
                ordDealOrderCheck = ordDealOrderMapper.selectByOrderId(orderId);
            } else {
                ordDealOrderCheck = ordDealOrderMapper.getOrdDealOrderByRoundId(checkLossDetail.getBatchNumber());
            }

            if (null == ordDealOrderCheck) {
                StringBuffer sb = new StringBuffer();
                sb.append("【根据A资源入库批次号或者切位单号查询切位单出现异常");
                sb.append("取消确认请求参数:" + JsonUtil.toString(cancelConfirmInputVo) + "】");
                resultVo.setSuccess(false);
                resultVo.setMsg(sb.toString());
                return resultVo;
            }

            ConfirmSalesQueryVo confirmSalesQueryCheck = new ConfirmSalesQueryVo();
            confirmSalesQueryCheck.setOrderId(ordDealOrderCheck.getOrderId());
            confirmSalesQueryCheck.setExtOrderId(checkLossCheck.getSellOrderId());
            confirmSalesQueryCheck.setRequirementId(checkLossCheck.getRequirementId());
            // 根据A资源入库批次(D类订单)、售卖订单号和需求ID查询D的销售单
            OrdSalesOrder ordSalesOrderCheck = ordSalesOrderMapper.getSaleByConfirmQuery(confirmSalesQueryCheck);
            if (null == ordSalesOrderCheck) {
                StringBuffer sb = new StringBuffer();
                sb.append("【根据A资源入库批次(D类订单)、售卖订单号和需求ID查询D的销售单出现异常");
                sb.append("取消确认请求参数:" + JsonUtil.toString(cancelConfirmInputVo) + "】");
                resultVo.setSuccess(false);
                resultVo.setMsg(sb.toString());
                return resultVo;
            }
        }

        // 根据核损单号获取核损出游人列表
        CheckLossTouristVo checkLossTouristVo = new CheckLossTouristVo();
        checkLossTouristVo.setCheckLossId(agencyLossSchemeId);
        List<CheckLossTourist> lossTouristList = checkLossTouristMapper.getLossTouristList(checkLossTouristVo);
        if (!CollectionUtils.isNotEmpty(lossTouristList)) {
            StringBuffer sb = new StringBuffer();
            sb.append("【核损方案号:" + agencyLossSchemeId + "获取核损出游人列表为空;");
            sb.append("取消确认请求参数:" + JsonUtil.toString(cancelConfirmInputVo) + "】");
            resultVo.setSuccess(false);
            resultVo.setMsg(sb.toString());
            return resultVo;
        }

        return resultVo;
    }

    /*
     * 处理取消确认 (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.NewConfirmService#dealCancelConfirm(com.tuniu.ord.vo.CancelConfirmInputVo)
     */
    @Override
    public void dealCancelConfirm(CancelConfirmInputVo cancelConfirmInputVo) {
        // 核损方案号
        Integer agencyLossSchemeId = cancelConfirmInputVo.getAgencyInfo().getAgencyLossSchemeId();

        // 根据核损单号获取很损详情列表
        List<CheckLossDetail> list = checkLossDetailMapper.getCheckLossNum(agencyLossSchemeId);

        // 日志记录
        CancelConfirmResult cancelConfirmResult = new CancelConfirmResult();
        cancelConfirmResult.setAgencyLossSchemeId(agencyLossSchemeId);

        // 分批次取消签约出库
        for (CheckLossDetail checkLossDetail : list) {
            CancelSubConfirmResult cancelSubConfirmResult = new CancelSubConfirmResult();
            cancelSubConfirmResult.setCheckLossDetailId(checkLossDetail.getId());

            // 取消签约出库
            ProductStockVo productStockVo = new ProductStockVo();
            initProductStockParam(productStockVo, cancelConfirmInputVo, checkLossDetail, agencyLossSchemeId,
                    cancelSubConfirmResult);
            ResponseVo resProductStock = stockProxy.productCancelContractSign(productStockVo);

            // 记录日志
            if (resProductStock.isSuccess()) {
                cancelSubConfirmResult.getSteps().add("【step3】取消签约成功");
            } else {
                cancelSubConfirmResult.getSteps()
                        .add("【step3】取消签约" + TSPEnumUtil.changeTSPName(SystemConstants.PRODUCT_CANCEL_CONTRACT_SIGN) + "失败:"
                                + resProductStock.getMsg() + ",请求入参:" + JsonUtil.toString(productStockVo));
                if (EnvironmentUtil.isPrdEnv()) {
                    ORDMailClient.sendMailToSystem(
                            new STKSys("取消签约" + TSPEnumUtil.changeTSPName(SystemConstants.PRODUCT_CANCEL_CONTRACT_SIGN) + "失败:"
                                    + resProductStock.getMsg() + ",请求入参:" + JsonUtil.toString(productStockVo)));
                }
            }

            // 向GRP发起核损
            LossApplyInfoVo lossApplyInfoVo = new LossApplyInfoVo();
            initLossApplyInfoParam(lossApplyInfoVo, checkLossDetail, agencyLossSchemeId, cancelSubConfirmResult);
            ResponseVo resLossApplyInfo = gRPProxy.applyLossInfo(lossApplyInfoVo);

            // 记录日志
            if (resLossApplyInfo.isSuccess()) {
                cancelSubConfirmResult.getSteps().add("【step4】向GRP发起核损申请成功");
            } else {
                cancelSubConfirmResult.getSteps().add(
                        "【step4】向GRP发起核损申请失败:" + resLossApplyInfo.getMsg() + ",请求入参:" + JsonUtil.toString(lossApplyInfoVo));
                if (EnvironmentUtil.isPrdEnv()) {
                    ORDMailClient.sendMailToSystem(new GRPSys(
                            "向GRP发起核损申请失败:" + resLossApplyInfo.getMsg() + ",请求入参:" + JsonUtil.toString(lossApplyInfoVo)));
                }
            }

            // 添加单批次结果
            cancelConfirmResult.getRoundList().add(cancelSubConfirmResult);
        }

        // 显示操作结果
        LOG.info("【新确认管理-取消确认请求的操作结果】" + JsonUtil.toString(cancelConfirmResult) + "######请求入参:"
                + JsonUtil.toString(cancelConfirmInputVo));
    }

    /**
     * 初始化取消签约的实例
     * 
     * @param productStockVo
     * @param cancelConfirmInputVo
     * @param checkLossDetail
     * @param agencyLossSchemeId
     */
    private void initProductStockParam(ProductStockVo productStockVo, CancelConfirmInputVo cancelConfirmInputVo,
            CheckLossDetail checkLossDetail, Integer agencyLossSchemeId, CancelSubConfirmResult cancelSubConfirmResult) {
        productStockVo.setClientFlag(Constants.CLIENT_FLAG);
        productStockVo.setSessionId(cancelConfirmInputVo.getSessionId());

        // 根据核损核损方案号查询售卖订单号和需求ID,
        CheckLoss checkLoss = checkLossMapper.selectByPrimaryKey(agencyLossSchemeId);

        // 根据A资源入库批次号或者切位单号查询切位单
        Integer orderId = checkLossDetail.getDealOrderId();
        OrdDealOrder ordDealOrder = null;
        if (orderId != null && orderId.intValue() > 0) {
            ordDealOrder = ordDealOrderMapper.selectByOrderId(orderId);
        } else {
            ordDealOrder = ordDealOrderMapper.getOrdDealOrderByRoundId(checkLossDetail.getBatchNumber());
        }

        ConfirmSalesQueryVo confirmSalesQueryVo = new ConfirmSalesQueryVo();
        confirmSalesQueryVo.setOrderId(ordDealOrder.getOrderId());
        confirmSalesQueryVo.setExtOrderId(checkLoss.getSellOrderId());
        confirmSalesQueryVo.setRequirementId(checkLoss.getRequirementId());
        // 根据A资源入库批次(D类订单)、售卖订单号和需求ID查询D的销售单
        OrdSalesOrder ordSalesOrder = ordSalesOrderMapper.getSaleByConfirmQuery(confirmSalesQueryVo);
        productStockVo.setOrderId(ordSalesOrder.getId());

        cancelSubConfirmResult.setdOrderId(ordSalesOrder.getId());

        List<DProductStock> dps = new ArrayList<DProductStock>();
        DProductStock dProductStock = new DProductStock();
        dProductStock.setdProductId(ordDealOrder.getProductId());
        dProductStock.setVendorId(SupplierEnum.TUNIU.getVendorId());
        List<DepartDateStock> dds = new ArrayList<DepartDateStock>();
        DepartDateStock departDateStock = new DepartDateStock();
        Integer lossAdultNum = checkLossDetail.getAdultCountBefore() - checkLossDetail.getAdultCountAfter();
        Integer lossChildNum = checkLossDetail.getChildCountBefore() - checkLossDetail.getChildCountAfter();
        Integer lossBabyNum = checkLossDetail.getBabyCountBefore() - checkLossDetail.getBabyCountAfter();
        departDateStock.setAdultNum(lossAdultNum + lossChildNum);
        departDateStock.setChildNum(0);
        departDateStock.setBabyNum(lossBabyNum);
        departDateStock.setRecovery((int) checkLossDetail.getResellFlag());
        departDateStock.setDepartDate(sdf.format(ordDealOrder.getDepartDate()));

        // 根据销售单号查询出库信息
        StockOutQueryVo stockOutQueryVo = new StockOutQueryVo();
        stockOutQueryVo.setOrderId(ordSalesOrder.getId());
        ResponseVo res = stockProxy.queryStockOutInfo(stockOutQueryVo);
        Integer outId = null;
        if (res.isSuccess()) {
            StockOutOutputVo stockOutOutputVo = JsonUtil.toBean(JsonUtil.toString(res.getData()), StockOutOutputVo.class);
            if (stockOutOutputVo.getCount().intValue() > 0) {
                outId = stockOutOutputVo.getRows().get(0).getId();
                cancelSubConfirmResult.getSteps().add("【step1】根据销售单号查询出库信息成功");
            } else {
                cancelSubConfirmResult.getSteps()
                        .add("【step1】根据销售单号查询出库信息" + TSPEnumUtil.changeTSPName(SystemConstants.QUERY_STOCK_OUT_INFO) + "返回结果为空:"
                                + JsonUtil.toString(res.getData()) + ",请求入参:" + JsonUtil.toString(stockOutQueryVo));
                if (EnvironmentUtil.isPrdEnv()) {
                    ORDMailClient.sendMailToSystem(new STKSys(
                            "根据销售单号查询出库信息" + TSPEnumUtil.changeTSPName(SystemConstants.QUERY_STOCK_OUT_INFO) + "返回结果为空:"
                                    + JsonUtil.toString(res.getData()) + ",请求入参:" + JsonUtil.toString(stockOutQueryVo)));
                }
            }
        } else {
            cancelSubConfirmResult.getSteps()
                    .add("【step1】根据销售单号查询出库信息" + TSPEnumUtil.changeTSPName(SystemConstants.QUERY_STOCK_OUT_INFO) + "失败:"
                            + res.getMsg() + ",请求入参:" + JsonUtil.toString(stockOutQueryVo));
            if (EnvironmentUtil.isPrdEnv()) {
                ORDMailClient.sendMailToSystem(
                        new STKSys("根据销售单号查询出库信息" + TSPEnumUtil.changeTSPName(SystemConstants.QUERY_STOCK_OUT_INFO) + "失败:"
                                + res.getMsg() + ",请求入参:" + JsonUtil.toString(stockOutQueryVo)));
            }
        }

        departDateStock.setOutId(outId);
        dds.add(departDateStock);
        dProductStock.setDepartDates(dds);
        dps.add(dProductStock);
        productStockVo.setdProducts(dps);

        // 计算新的订单价格
        BigDecimal adultCount = checkLossDetail.getLossAdultCustomer().multiply(new BigDecimal(lossAdultNum));
        BigDecimal childCount = checkLossDetail.getLossChildCustomer().multiply(new BigDecimal(lossChildNum));
        BigDecimal babyCount = checkLossDetail.getLossBabyCustomer().multiply(new BigDecimal(lossBabyNum));
        BigDecimal lossTotalCount = adultCount.add(childCount).add(babyCount);
        BigDecimal newOrderTotalCount = ordSalesOrder.getTotalPrice().subtract(lossTotalCount);

        BigDecimal confirmPrice = new BigDecimal(lossAdultNum).multiply(ordSalesOrder.getAdultPrice())
                .add(new BigDecimal(lossChildNum).multiply(ordSalesOrder.getChildPrice()));

        BigDecimal checkLossPrice = checkLossDetail.getLossAdultCustomer().multiply(new BigDecimal(lossAdultNum))
                .add(checkLossDetail.getLossChildCustomer().multiply(new BigDecimal(lossChildNum)));

        BigDecimal price = ordSalesOrder.getTotalPrice().subtract(confirmPrice).add(checkLossPrice);
        LOG.info("lossAfterPrice:{}", JsonUtil.toString(ordSalesOrder));

        // 更新销售单价格和财务价格
        OrdSalesOrder newOrdSalesOrder = new OrdSalesOrder();
        newOrdSalesOrder.setId(ordSalesOrder.getId());
        newOrdSalesOrder.setTotalPrice(price);
        newOrdSalesOrder.setUpdateTime(DateUtils.now());
        ordSalesOrderMapper.updateByPrimaryKeySelective(newOrdSalesOrder);
        OrderSynInfo orderSynInfo = new OrderSynInfo();
        orderSynInfo.setId(ordSalesOrder.getId());
        orderSynInfo.setContractAmount(newOrderTotalCount);
        ResponseVo sCsRes = sCSProxy.updateOrder(orderSynInfo);

        if (sCsRes.isSuccess()) {
            cancelSubConfirmResult.getSteps().add("【step2】更新订单信息给财务系统成功");
        } else {
            cancelSubConfirmResult.getSteps().add("【step2】更新订单信息给财务系统" + SystemConstants.UPDATE_ORDER + "失败:" + sCsRes.getMsg()
                    + ",请求入参:" + JsonUtil.toString(orderSynInfo));
            if (EnvironmentUtil.isPrdEnv()) {
                ORDMailClient.sendMailToSystem(new SCSSys("更新订单信息给财务系统" + SystemConstants.UPDATE_ORDER + "失败:" + sCsRes.getMsg()
                        + ",请求入参:" + JsonUtil.toString(orderSynInfo)));
            }
        }
    }

    /**
     * 初始化向GRP发起核损的实例
     * 
     * @param lossApplyInfoVo
     * @param checkLossDetail
     * @param agencyLossSchemeId
     */
    private void initLossApplyInfoParam(LossApplyInfoVo lossApplyInfoVo, CheckLossDetail checkLossDetail,
            Integer agencyLossSchemeId, CancelSubConfirmResult cancelSubConfirmResult) {
        List<LossApplyInfoDataVo> data = new ArrayList<LossApplyInfoDataVo>();
        LossApplyInfoDataVo lossApplyInfoDataVo = new LossApplyInfoDataVo();
        Integer lossAdultNum = checkLossDetail.getAdultCountBefore() - checkLossDetail.getAdultCountAfter();
        Integer lossChildNum = checkLossDetail.getChildCountBefore() - checkLossDetail.getChildCountAfter();
        BigDecimal totalLoss = checkLossDetail.getLossAdultCustomer().multiply(new BigDecimal(lossAdultNum))
                .add(checkLossDetail.getLossChildCustomer().multiply(new BigDecimal(lossChildNum)));

        // 根据核损核损方案号查询售卖订单号和需求ID,
        CheckLoss checkLoss = checkLossMapper.selectByPrimaryKey(agencyLossSchemeId);

        // 根据A资源入库批次号或者切位单号查询切位单
        Integer orderId = checkLossDetail.getDealOrderId();
        OrdDealOrder ordDealOrder = null;
        if (orderId != null && orderId.intValue() > 0) {
            ordDealOrder = ordDealOrderMapper.selectByOrderId(orderId);
        } else {
            ordDealOrder = ordDealOrderMapper.getOrdDealOrderByRoundId(checkLossDetail.getBatchNumber());
        }

        ConfirmSalesQueryVo confirmSalesQueryVo = new ConfirmSalesQueryVo();
        confirmSalesQueryVo.setOrderId(ordDealOrder.getOrderId());
        confirmSalesQueryVo.setExtOrderId(checkLoss.getSellOrderId());
        confirmSalesQueryVo.setRequirementId(checkLoss.getRequirementId());
        // 根据A资源入库批次(D类订单)、售卖订单号和需求ID查询D的销售单
        OrdSalesOrder ordSalesOrder = ordSalesOrderMapper.getSaleByConfirmQuery(confirmSalesQueryVo);

        lossApplyInfoDataVo.setAdultNum(lossAdultNum);
        lossApplyInfoDataVo.setChildNum(lossChildNum);
        lossApplyInfoDataVo.setTotalLoss(totalLoss);
        lossApplyInfoDataVo.setChargeUid(checkLossDetail.getAddUid());
        lossApplyInfoDataVo.setChargeUname(checkLossDetail.getAddUname());
        lossApplyInfoDataVo.setIsSaleAgain((int) checkLossDetail.getResellFlag());
        lossApplyInfoDataVo.setCheckMode(CheckModeEnum.AUTOMATIC_MODE.getKey());
        lossApplyInfoDataVo.setOrderId(ordSalesOrder.getId());
        // FIXME 先写死channelId：1
        lossApplyInfoDataVo.setChannelId(1);
        lossApplyInfoDataVo.setChannelOrderId(String.valueOf(ordSalesOrder.getExtOrderId()));
        lossApplyInfoDataVo.setCurrencyType(ordSalesOrder.getCostCurrencyType());

        // 根据核损单号获取核损出游人列表
        CheckLossTouristVo checkLossTouristVo = new CheckLossTouristVo();
        checkLossTouristVo.setCheckLossId(agencyLossSchemeId);
        List<CheckLossTourist> lossTouristList = checkLossTouristMapper.getLossTouristList(checkLossTouristVo);
        // 获取D销售单下出游人列表
        PeopleTouristVo peopleTouristVo = new PeopleTouristVo();
        List<Integer> sellOrderIds = new ArrayList<Integer>();
        sellOrderIds.add(ordSalesOrder.getId());
        peopleTouristVo.setSellOrderIds(sellOrderIds);
        List<OrdPeopleTourist> ordPeopleTouristList = ordPeopleTouristMapper.selectByFields(peopleTouristVo);
        List<Integer> peopleIds = new ArrayList<Integer>();
        if (CollectionUtils.isNotEmpty(ordPeopleTouristList)) {
            for (OrdPeopleTourist ordPeopleTourist : ordPeopleTouristList) {
                peopleIds.add(ordPeopleTourist.getId());
            }
        }
        List<Integer> consumerIds = new ArrayList<Integer>();
        // 判断该批次下的出游人
        for (CheckLossTourist clt : lossTouristList) {
            if (peopleIds.contains(clt.getOrdPeopleTouristId())) {
                consumerIds.add(clt.getOrdPeopleTouristId());
            }
        }
        lossApplyInfoDataVo.setConsumerIds(consumerIds);

        data.add(lossApplyInfoDataVo);
        lossApplyInfoVo.setData(data);
    }

    /**
     * 复制属性值
     * 
     * @param newDD
     * @param ordDD
     */
    private void copyProperty(NewDepartDate newDD, DepartDate ordDD) {
        newDD.setAffirmAdultNum(ordDD.getAffirmAdultNum());
        newDD.setAffirmAdultPrice(ordDD.getAffirmAdultPrice());
        newDD.setAffirmBabyNum(ordDD.getAffirmBabyNum());
        newDD.setAffirmBabyPrice(ordDD.getAffirmBabyPrice());
        newDD.setAffirmChildNum(ordDD.getAffirmChildNum());
        newDD.setAffirmChildPrice(ordDD.getAffirmChildPrice());
        newDD.setCostCurrencyType(ordDD.getCostCurrencyType());
        newDD.setDate(ordDD.getDate());
        newDD.setResourceId(ordDD.getResourceId());
        newDD.setRoomAddNum(ordDD.getRoomAddNum());
        newDD.setRoomAddPrice(ordDD.getRoomAddPrice());
        newDD.setRoomAddPriceOption(ordDD.getRoomAddPriceOption());
        newDD.setRoundId(ordDD.getRoundId());
        newDD.setTotalAdultNum(ordDD.getTotalAdultNum());
        newDD.setTotalBabyNum(ordDD.getTotalBabyNum());
        newDD.setTotalChildNum(ordDD.getTotalChildNum());

        newDD.setExtPurchaseId(ordDD.getExtPurchaseId());
        newDD.setStockType(ordDD.getStockType());
    }

    /**
     * 初始化销售单实例
     * 
     * @param ordSalesOrder
     * @param newDepartDate
     * @param confirmInputVo
     */
    private void initOrdSalesOrderParam(OrdSalesOrder ordSalesOrder, NewDepartDate newDepartDate, ConfirmInputVo confirmInputVo,
            DealSubConfirmResult dealSubConfirmResult) {
        TuniuInfo tuniuInfo = confirmInputVo.getProducts().get(0).getTuniuInfo();
        OrdDealOrder ordDealOrder = ordDealOrderMapper.getOrdDealOrderByRoundId(Integer.valueOf(newDepartDate.getRoundId()));

        if (newDepartDate.getExtPurchaseId() != null && newDepartDate.getExtPurchaseId().intValue() != 0) {
            ordDealOrder = ordDealOrderMapper.selectByOrderId(newDepartDate.getExtPurchaseId());
        }

        // 访问POS客服端生产订单号
        Long orderNum = iOrderIdServiceImplClient.getOrderNum(Constants.INTERVAL_TYPE_ID);
        LOG.info("首次从POS服务端生产订单号:{},外部订单号:{},需求号:{}", orderNum, tuniuInfo.getTuniuOrderId(), tuniuInfo.getRequirementId());
        ordSalesOrder.setId(orderNum.intValue());
        ordSalesOrder.setOrderId(ordDealOrder.getOrderId());
        ordSalesOrder.setExtOrderId(tuniuInfo.getTuniuOrderId());
        ordSalesOrder.setRequirementId(tuniuInfo.getRequirementId());
        ordSalesOrder.setExtProductId(tuniuInfo.getProductId());
        ordSalesOrder.setExtProductName(tuniuInfo.getProductName());
        // 确认中->确认成功
        ordSalesOrder.setStatusCode(OrderStateEnum.CONFIRMED.getStatusCode());

        Date date = DateUtils.now();
        ordSalesOrder.setAddUid(new Integer(UserSessionUtil.getUid()));
        ordSalesOrder.setAddUname(UserSessionUtil.getNickname());
        ordSalesOrder.setAddTime(date);
        ordSalesOrder.setUpdateUid(new Integer(UserSessionUtil.getUid()));
        ordSalesOrder.setUpdateUname(UserSessionUtil.getNickname());
        ordSalesOrder.setUpdateTime(date);

        // 字符串转成日期
        try {
            ordSalesOrder.setStartDate(sdf.parse(newDepartDate.getDate()));
        } catch (ParseException e2) {
            LOG.error(e2.getMessage());
        }
        ordSalesOrder.setCostCurrencyType(newDepartDate.getCostCurrencyType().toString());
        ordSalesOrder.setSaleChannel(tuniuInfo.getName());
        ordSalesOrder.setAdultCount(newDepartDate.getAffirmAdultNum());
        ordSalesOrder.setAdultPrice(newDepartDate.getAffirmAdultPrice());
        ordSalesOrder.setChildCount(newDepartDate.getAffirmChildNum());
        ordSalesOrder.setChildPrice(newDepartDate.getAffirmChildPrice());
        ordSalesOrder.setRoomAddNum(newDepartDate.getRoomAddNum());
        ordSalesOrder.setRoomAddPrice(newDepartDate.getRoomAddPrice());
        ordSalesOrder.setResourceId(newDepartDate.getResourceId());

        // 记录销售单总价
        BigDecimal adultAmount = newDepartDate.getAffirmAdultPrice()
                .multiply(new BigDecimal(newDepartDate.getAffirmAdultNum()));
        BigDecimal childAmount = newDepartDate.getAffirmChildPrice()
                .multiply(new BigDecimal(newDepartDate.getAffirmChildNum()));
        BigDecimal roomAmount = newDepartDate.getRoomAddPrice().multiply(new BigDecimal(newDepartDate.getRoomAddNum()));
        BigDecimal totalAmount = new BigDecimal(0);
        totalAmount = totalAmount.add(adultAmount).add(childAmount).add(roomAmount);
        ordSalesOrder.setTotalPrice(totalAmount);

        // 系统参数
        ordSalesOrder.setApiKey(confirmInputVo.getApiKey());
        ordSalesOrder.setTimestamp(confirmInputVo.getTimestamp());
        ordSalesOrder.setSign(confirmInputVo.getSign());
    }

    /**
     * 初始化出游人实例
     * 
     * @param ordPeopleTourist
     * @param orderId
     * @param tt
     */
    private void initOrdPeopleTouristParam(OrdPeopleTourist ordPeopleTourist, Integer orderId, Tourist tt,
            DealSubConfirmResult dealSubConfirmResult) {
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
     * 初始化向组团系统下销售单实例
     * 
     * @param salesConfirmVo
     * @param newDepartDate
     * @param confirmInputVo
     * @param orderId
     * @param dealSubConfirmResult
     */
    private void initSalesConfirmParam(SalesConfirmVo salesConfirmVo, NewDepartDate newDepartDate,
            ConfirmInputVo confirmInputVo, Integer orderId, DealSubConfirmResult dealSubConfirmResult) {
        TuniuInfo tuniuInfo = confirmInputVo.getProducts().get(0).getTuniuInfo();
        Product product = confirmInputVo.getProducts().get(0);
        OrdDealOrder ordDealOrder = ordDealOrderMapper.getOrdDealOrderByRoundId(Integer.valueOf(newDepartDate.getRoundId()));

        if (newDepartDate.getExtPurchaseId() != null && newDepartDate.getExtPurchaseId().intValue() != 0) {
            ordDealOrder = ordDealOrderMapper.selectByOrderId(newDepartDate.getExtPurchaseId());

        }
        salesConfirmVo.setTenantId(Constants.tenantId);
        salesConfirmVo.setUid(Integer.valueOf(UserSessionUtil.getUid()));
        salesConfirmVo.setNickname(UserSessionUtil.getNickname());
        salesConfirmVo.setToken(Constants.token);
        salesConfirmVo.setSessionId(SessionUtil.createSessionId());
        salesConfirmVo.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
        List<SaleVo> sales = new ArrayList<SaleVo>();
        SaleVo sale = new SaleVo();
        sale.setSpecialRemark(product.getOrderRemark());
        sale.setOrderRemark(product.getOrderRemark());
        sale.setRemark(product.getRemark());
        sale.setSupplyRemark(product.getSupplyRemark());
        sale.setOtherResources(product.getOtherResources());
        sale.setAddNum(newDepartDate.getRoomAddNum());
        sale.setAddPrice(newDepartDate.getRoomAddPrice());
        sale.setAdultCost(newDepartDate.getAffirmAdultPrice());
        sale.setAdultNum(newDepartDate.getAffirmAdultNum());
        // FIXME 写死
        sale.setChannelId("1");
        // 添加外部渠道订单号
        sale.setChannelOrderId(tuniuInfo.getTuniuOrderId().toString());
        sale.setChildCost(newDepartDate.getAffirmChildPrice());
        sale.setChildNum(newDepartDate.getAffirmChildNum());
        sale.setCurrencyType(newDepartDate.getCostCurrencyType().toString());
        sale.setOrderId(orderId);
        if (null != newDepartDate.getDate()) {
            sale.setStartDate(newDepartDate.getDate());
        }
        BigDecimal adultTotalPrice = newDepartDate.getAffirmAdultPrice()
                .multiply(new BigDecimal(newDepartDate.getAffirmAdultNum()));
        BigDecimal childTotalPrice = newDepartDate.getAffirmChildPrice()
                .multiply(new BigDecimal(newDepartDate.getAffirmChildNum()));
        BigDecimal roomTotalPrice = newDepartDate.getRoomAddPrice().multiply(new BigDecimal(newDepartDate.getRoomAddNum()));
        sale.setTotalCost(adultTotalPrice.add(childTotalPrice).add(roomTotalPrice));
        sale.setTourBasicId(ordDealOrder.getGroupId());

        ResProVo resProVo = new ResProVo();
        resProVo.setResId(newDepartDate.getResourceId());
        ResponseVo responseVo = pRDProxy.queryProByResId(resProVo);
        if (responseVo != null && responseVo.isSuccess() && responseVo.getData() != null) {
            ResProVo proVo = JsonUtil.toBean(JsonUtil.toString(responseVo.getData()), ResProVo.class);
            if (proVo.getTourUpgradeId() != null) {
                sale.setUpgradeId(proVo.getTourUpgradeId());
            }
        }

        // 访问产品系统获取产品线->访问数据库获取产品线
        sale.setProductLineId(ordDealOrder.getProductLineId());

        List<ConsumerVo> consumers = new ArrayList<ConsumerVo>();
        List<Tourist> touristList = newDepartDate.getTouristList();
        for (Tourist tt : touristList) {
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
            consumer.setConsumerId(tt.getConsumerId());
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
        sales.add(sale);
        salesConfirmVo.setSales(sales);
    }

    /**
     * 初始化同步订单信息实例
     * 
     * @param orderSynInfo
     * @param newDepartDate
     * @param confirmInputVo
     * @param orderId
     * @param dealSubConfirmResult
     */
    private void initOrderSynInfoParam(OrderSynInfo orderSynInfo, NewDepartDate newDepartDate, ConfirmInputVo confirmInputVo,
            Integer orderId, DealSubConfirmResult dealSubConfirmResult) {
        TuniuInfo tuniuInfo = confirmInputVo.getProducts().get(0).getTuniuInfo();
        OrdDealOrder ordDealOrder = ordDealOrderMapper.getOrdDealOrderByRoundId(Integer.valueOf(newDepartDate.getRoundId()));

        if (newDepartDate.getExtPurchaseId() != null && newDepartDate.getExtPurchaseId().intValue() != 0) {
            ordDealOrder = ordDealOrderMapper.selectByOrderId(newDepartDate.getExtPurchaseId());
        }

        orderSynInfo.setId(orderId);
        orderSynInfo.setState(Constants.CONFIRMED_ORDERSTATUS);
        orderSynInfo.setStatusDesc(OrderStateEnum.CONFIRMED.getStatusName());
        BigDecimal adultAmount = newDepartDate.getAffirmAdultPrice()
                .multiply(new BigDecimal(newDepartDate.getAffirmAdultNum()));
        BigDecimal childAmount = newDepartDate.getAffirmChildPrice()
                .multiply(new BigDecimal(newDepartDate.getAffirmChildNum()));
        BigDecimal roomAmount = newDepartDate.getRoomAddPrice().multiply(new BigDecimal(newDepartDate.getRoomAddNum()));
        BigDecimal totalAmount = new BigDecimal(0);
        totalAmount = totalAmount.add(adultAmount).add(childAmount).add(roomAmount);
        orderSynInfo.setContractAmount(totalAmount);
        orderSynInfo.setSyncTime(sdft.format(new Date()));
        orderSynInfo.setProduct(ordDealOrder.getProductId());

        // 出发城市
        orderSynInfo.setStartCityCode(ordDealOrder.getDepartureCityCode().toString());
        orderSynInfo.setStartCityName(ordDealOrder.getDepartureCityName());
        // 产品品类
        orderSynInfo.setProductCategory(ProductCategoryEnum.PRODUCT_CATAGORY.getKey());
        orderSynInfo.setProductCategoryName(ProductCategoryEnum.PRODUCT_CATAGORY.getName());
        orderSynInfo.setProductSubCategory(ProductCategoryEnum.PRODUCT_SUB_CATAGORY.getKey());
        orderSynInfo.setProductSubCategoryName(ProductCategoryEnum.PRODUCT_SUB_CATAGORY.getName());
        // 类目信息
        orderSynInfo.setDestinationFirstCategory(ordDealOrder.getDestCategoryId());
        orderSynInfo.setDestinationFirstCategoryName(ordDealOrder.getDestCategoryName());
        orderSynInfo.setFirstDestinationGrouping(ordDealOrder.getFirstDestGroupId());
        orderSynInfo.setFirstDestinationGroupingName(ordDealOrder.getFirstDestGroupName());
        orderSynInfo.setSecondDestinationGrouping(ordDealOrder.getSecDestGroupId());
        orderSynInfo.setSecondDestinationGroupingName(ordDealOrder.getSecDestGroupName());
        orderSynInfo.setDestination(ordDealOrder.getDestId());
        orderSynInfo.setDestinationName(ordDealOrder.getDestName());

        orderSynInfo.setSignCompany(Constants.SIGN_COMPANY);
        orderSynInfo.setSignCompanyName(Constants.SIGN_COMPANY_NAME);
        orderSynInfo.setIsSubOrder(new Integer(1));
        orderSynInfo.setPrimaryOrderId(tuniuInfo.getTuniuOrderId());

        /** 访问部门类目管理系统获取部门信息开始 **/
        QueryRelationsIdVo queryRelationsIdVo = new QueryRelationsIdVo();
        Integer productLineId = ordDealOrder.getProductLineId();
        queryRelationsIdVo.setProductLineId(productLineId);
        // 访问部门类目管理系统获取部门信息
        ResponseVo dpsRes = dPSProxy.queryRelationsId(queryRelationsIdVo);
        QueryRelationsIdOutputVo queryRelationsIdOutputVo = null;
        DPSDeptInfo defaultDept = new DPSDeptInfo();
        if (dpsRes.isSuccess()) {
            queryRelationsIdOutputVo = JsonUtil.toBean(JsonUtil.toString(dpsRes.getData()), QueryRelationsIdOutputVo.class);
        } else {
            dealSubConfirmResult.getSteps().add("【step5】根据产品线id、部门id查询关联关系" + SystemConstants.QUERY_RELATIONS_ID + "接口失败:"
                    + dpsRes.getMsg() + ",请求入参:" + JsonUtil.toString(queryRelationsIdVo));
            if (EnvironmentUtil.isPrdEnv()) {
                ORDMailClient.sendMailToSystem(new DPSSys("根据产品线id、部门id查询关联关系" + SystemConstants.QUERY_RELATIONS_ID + "接口失败:"
                        + dpsRes.getMsg() + ",请求入参:" + JsonUtil.toString(queryRelationsIdVo)));
            }
        }
        // 查找产品线与部门的结果标志
        boolean flag = true;
        if (null != queryRelationsIdOutputVo) {
            if (CollectionUtils.isNotEmpty(queryRelationsIdOutputVo.getRows())) {
                for (DPSDeptInfo dept : queryRelationsIdOutputVo.getRows()) {
                    if (Constants.PRODUCT_BRAND_ID.intValue() == dept.getProductBrandId().intValue()
                            && Constants.SALE_STYLE_ID.intValue() == dept.getSaleStyleId().intValue()) {
                        defaultDept = dept;
                        dealSubConfirmResult.getSteps().add("【step5】根据产品线id、部门id查询关联关系查询成功");
                        flag = false;
                        break;
                    }
                }
            } else {
                dealSubConfirmResult.getSteps().add("【step5】根据产品线id、部门id查询关联关系" + SystemConstants.QUERY_RELATIONS_ID
                        + "查询【产品线ID】" + productLineId + "下无部门信息,请求入参:" + JsonUtil.toString(queryRelationsIdVo));
                if (EnvironmentUtil.isPrdEnv()) {
                    ORDMailClient.sendMailToSystem(new DPSSys("根据产品线id、部门id查询关联关系" + SystemConstants.QUERY_RELATIONS_ID
                            + "查询【产品线ID】" + productLineId + "下无部门信息,请求入参:" + JsonUtil.toString(queryRelationsIdVo)));
                }
                flag = false;
            }
            if (flag == true) {
                dealSubConfirmResult.getSteps().add("【step5】根据产品线id、部门id查询关联关系" + SystemConstants.QUERY_RELATIONS_ID
                        + "查询【产品线ID】" + productLineId + "有部门列表，但是没有匹配成功,请求入参:" + JsonUtil.toString(queryRelationsIdVo));
                if (EnvironmentUtil.isPrdEnv()) {
                    ORDMailClient.sendMailToSystem(new DPSSys("根据产品线id、部门id查询关联关系" + SystemConstants.QUERY_RELATIONS_ID
                            + "查询【产品线ID】" + productLineId + "有部门列表，但是没有匹配成功,请求入参:" + JsonUtil.toString(queryRelationsIdVo)));
                }
            }
        }
        /** 访问部门类目管理系统获取部门信息 结束 **/
        // 部门信息
        orderSynInfo.setRegionCode(defaultDept.getDivisionId());
        orderSynInfo.setRegionName(defaultDept.getDivisionName());
        orderSynInfo.setDepartmentCode(defaultDept.getDepartmentId());
        orderSynInfo.setDepartmentName(defaultDept.getDepartmentName());
        orderSynInfo.setGroupId(defaultDept.getGroupId());
        orderSynInfo.setGroupName(defaultDept.getGroupName());

        // 同步出游时间和归来时间
        if (ordDealOrder.getDepartDate() != null) {
            orderSynInfo.setStartDate(sdft.format(ordDealOrder.getDepartDate()));
            orderSynInfo.setCollectionTime(sdft.format(ordDealOrder.getDepartDate()));
        }
        if (ordDealOrder.getBackDate() != null) {
            orderSynInfo.setBackDate(sdft.format(ordDealOrder.getBackDate()));
        }
    }

    /**
     * 初始化签约出库实例
     * 
     * @param productStockVo
     * @param newDepartDate
     * @param confirmInputVo
     * @param orderId
     * @param dealSubConfirmResult
     */
    private void initProductStockParam(ProductStockVo productStockVo, NewDepartDate newDepartDate,
            ConfirmInputVo confirmInputVo, Integer orderId, DealSubConfirmResult dealSubConfirmResult) {
        TuniuInfo tuniuInfo = confirmInputVo.getProducts().get(0).getTuniuInfo();

        // 查询占位单号方式更改
        OrdDealOrder ordDealOrder = ordDealOrderMapper.getOrdDealOrderByRoundId(Integer.valueOf(newDepartDate.getRoundId()));

        if (newDepartDate.getExtPurchaseId() != null && newDepartDate.getExtPurchaseId().intValue() != 0) {
            ordDealOrder = ordDealOrderMapper.selectByOrderId(newDepartDate.getExtPurchaseId());
        }

        // 根据切位单号查询占位记录
        List<Integer> outIds = queryOccupyInfo(ordDealOrder.getOrderId(), dealSubConfirmResult);

        // 签约出库入参第一步
        DepartDateStock departDateStock = new DepartDateStock();
        departDateStock.setOutIds(outIds);
        departDateStock.setDepartDate(newDepartDate.getDate());
        departDateStock.setAdultCost(newDepartDate.getAffirmAdultPrice());
        departDateStock.setAdultCostRMB(newDepartDate.getAffirmAdultPrice());
        // 成人数 = 成人数 + 儿童数
        departDateStock.setAdultNum(newDepartDate.getAffirmAdultNum() + newDepartDate.getAffirmChildNum());
        departDateStock.setBabyCost(new BigDecimal(0));
        departDateStock.setBabyCostRMB(new BigDecimal(0));
        departDateStock.setBabyNum(new Integer(0));
        departDateStock.setChildCost(newDepartDate.getAffirmChildPrice());
        departDateStock.setChildCostRMB(newDepartDate.getAffirmChildPrice());
        departDateStock.setChildNum(0);
        List<DepartDateStock> dd = new ArrayList<DepartDateStock>();
        dd.add(departDateStock);
        // 签约出库入参第二步
        DProductStock dProductStock = new DProductStock();
        dProductStock.setdProductId(ordDealOrder.getProductId());
        dProductStock.setCostCurrencyType(newDepartDate.getCostCurrencyType());
        dProductStock.setVendorId(SupplierEnum.TUNIU.getVendorId());
        dProductStock.setVendorName(SupplierEnum.TUNIU.getVendorName());
        dProductStock.setDepartDates(dd);
        List<DProductStock> dProducts = new ArrayList<DProductStock>();
        dProducts.add(dProductStock);
        // 签约出库入参第三步
        productStockVo.setOrderId(orderId);
        productStockVo.setProductId(tuniuInfo.getProductId());
        productStockVo.setProductName(tuniuInfo.getProductName());
        productStockVo.setClientFlag(Constants.CLIENT_FLAG);
        productStockVo.setSessionId(SessionUtil.createSessionId());
        productStockVo.setdProducts(dProducts);
    }

    /**
     * 根据切位单号查询占位记录
     * 
     * @param orderId
     * @return
     */
    private List<Integer> queryOccupyInfo(Integer orderId, DealSubConfirmResult dealSubConfirmResult) {
        List<Integer> outIds = new ArrayList<Integer>();
        StockOccupyQueryVo stockOccupyQueryVo = new StockOccupyQueryVo();
        stockOccupyQueryVo.setOccupyObjId(orderId);
        ResponseVo responseVo = stockProxy.queryOccupyInfo(stockOccupyQueryVo);
        if (!responseVo.isSuccess()) {
            dealSubConfirmResult.getSteps()
                    .add("【step3】【根据切位单号查询占位记录" + TSPEnumUtil.changeTSPName(SystemConstants.QUERY_OCCUPY_INFO) + "】接口失败:"
                            + responseVo.getMsg() + ",请求入参:" + JsonUtil.toString(stockOccupyQueryVo));
            if (EnvironmentUtil.isPrdEnv()) {
                ORDMailClient.sendMailToSystem(
                        new STKSys("【根据切位单号查询占位记录" + TSPEnumUtil.changeTSPName(SystemConstants.QUERY_OCCUPY_INFO) + "】接口失败:"
                                + responseVo.getMsg() + ",请求入参:" + JsonUtil.toString(stockOccupyQueryVo)));
            }
            return null;
        } else {
            StockOccupyOutputVo stockOccupyOutputVo = JsonUtil.toBean(JsonUtil.toString(responseVo.getData()),
                    StockOccupyOutputVo.class);
            if (stockOccupyOutputVo.getCount().intValue() <= 0) {
                dealSubConfirmResult.getSteps()
                        .add("【step3】【根据切位单号查询占位记录" + TSPEnumUtil.changeTSPName(SystemConstants.QUERY_OCCUPY_INFO)
                                + "】返回结果为空,请求入参:" + JsonUtil.toString(stockOccupyQueryVo));
                if (EnvironmentUtil.isPrdEnv()) {
                    ORDMailClient.sendMailToSystem(
                            new STKSys("【根据切位单号查询占位记录" + TSPEnumUtil.changeTSPName(SystemConstants.QUERY_OCCUPY_INFO)
                                    + "】返回结果为空,请求入参:" + JsonUtil.toString(stockOccupyQueryVo)));
                }
                return null;
            } else {
                for (StockOccupyInfoOutput soio : stockOccupyOutputVo.getRows()) {
                    if (soio.getLeftNumber().intValue() > 0) {
                        outIds.add(soio.getId());
                    }
                }
                dealSubConfirmResult.getSteps()
                        .add("【step3】【根据切位单号查询占位记录" + TSPEnumUtil.changeTSPName(SystemConstants.QUERY_OCCUPY_INFO)
                                + "】成功,占位单列表为:" + outIds + ",请求入参:" + JsonUtil.toString(stockOccupyQueryVo));
                return outIds;
            }
        }
    }

    /**
     * 初始化异步反馈实例
     * 
     * @param confirmFeedBackInputVo
     * @param newDepartDateList
     * @param confirmInputVo
     * @param dealSubConfirmResult
     */
    @Override
    public void initConfirmFeedBackInputParam(ConfirmFeedBackInputVo confirmFeedBackInputVo,
            List<NewDepartDate> newDepartDateList, ConfirmInputVo confirmInputVo) {
        // 系统参数(保存)
        confirmFeedBackInputVo.setApiKey(Constants.API_KEY);
        confirmFeedBackInputVo.setSign(confirmInputVo.getSign());
        confirmFeedBackInputVo.setTimestamp(sdft.format(Calendar.getInstance().getTime()));
        confirmFeedBackInputVo.setSessionId(confirmInputVo.getSessionId());
        confirmFeedBackInputVo.setSuccess(true);
        List<DepartDateAPI> dda = new ArrayList<DepartDateAPI>();
        ProductAPI productAPI = new ProductAPI();
        List<ProductAPI> pa = new ArrayList<ProductAPI>();
        for (NewDepartDate departDate : newDepartDateList) {
            DepartDateAPI departDateAPI = new DepartDateAPI();
            departDateAPI.setAdultCost(departDate.getAffirmAdultPrice());
            departDateAPI.setAdultNum(departDate.getAffirmAdultNum());
            departDateAPI.setChildCost(departDate.getAffirmChildPrice());
            departDateAPI.setChildNum(departDate.getAffirmChildNum());
            departDateAPI.setDate(departDate.getDate());
            departDateAPI.setRoomAddNum(departDate.getRoomAddNum());
            departDateAPI.setRoomAddPrice(departDate.getRoomAddPrice());
            departDateAPI.setRoundId(Integer.parseInt(departDate.getRoundId()));
            departDateAPI.setExtPurchaseId(departDate.getExtPurchaseId());
            departDateAPI.setStockType(departDate.getStockType());
            dda.add(departDateAPI);
        }
        productAPI.setAgencyOrderId("123");
        productAPI.setAgencyOwnerId(new Integer(123));
        productAPI.setAgencyOwnerName("123");
        productAPI.setAgencyProductId("123");
        productAPI.setDepartDates(dda);
        pa.add(productAPI);
        confirmFeedBackInputVo.setProducts(pa);

        // API系统的安全机制处理
        dealSign(confirmFeedBackInputVo);
    }

    /**
     * API系统的安全机制处理
     * 
     * @param confirmFeedBackInputVo
     */
    private void dealSign(ConfirmFeedBackInputVo confirmFeedBackInputVo) {
        Map<String, Object> map = JsonUtil.toBean(JsonUtil.toString(confirmFeedBackInputVo), HashMap.class);
        try {
            String newSign = BeanUtil.getSignature(JSONObject.fromObject(map), Constants.SECRET_KEY);
            confirmFeedBackInputVo.setSign(newSign);
        } catch (Exception e) {
            LOG.error("API系统的安全机制处理失败:" + e.getMessage() + ",####请求入参:" + JsonUtil.toString(confirmFeedBackInputVo));
        }
    }

    /**
     * 根据库存类型处理
     * 
     * @param newDepartDate
     */
    private void dealStockType(NewDepartDate newDepartDate, ConfirmInputVo confirmInputVo) {

        // 库存类型是FS的特殊处理
        if (newDepartDate.getStockType() != null
                && StockTypeEnum.Free_Sale.getKey() == newDepartDate.getStockType().intValue()) {
            // 根据A资源查询D产品ID
            ResProVo resProVo = new ResProVo();
            resProVo.setResId(newDepartDate.getResourceId());
            ResponseVo result = pRDProxy.queryProByResId(resProVo);
            if (result.isSuccess()) {
                resProVo = JsonUtil.toBean(JsonUtil.toString(result.getData()), ResProVo.class);
            }

            // 根据产品ID查询产品信息
            ProBaseVo proBaseVo = new ProBaseVo();
            proBaseVo.setId(resProVo.getProductId());
            ResponseVo result2 = pRDProxy.queryProByProId(proBaseVo);
            ProBaseOutputVo proBaseOutputVo = new ProBaseOutputVo();
            if (result2.isSuccess()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map = JsonUtil.toBean(JsonUtil.toString(result2.getData()), Map.class);
                List<ProBaseOutputVo> list = new ArrayList<ProBaseOutputVo>();
                list = JsonUtil.toList(JsonUtil.toString(map.get("rows")), ProBaseOutputVo.class);
                proBaseOutputVo = list.get(0);
            }

            // 根据产品ID和团期查询团信息
            TourBaseInputVo tourBaseInputVo = new TourBaseInputVo();
            tourBaseInputVo.setProductId(resProVo.getProductId());
            List<String> departDate = new ArrayList<String>();
            departDate.add(newDepartDate.getDate());
            tourBaseInputVo.setDepartDate(departDate);
            ResponseVo result3 = pRDProxy.queryTour(tourBaseInputVo);
            TourBaseOutputVo tourBaseOutputVo = new TourBaseOutputVo();
            if (result3.isSuccess()) {
                Map<String, Object> map2 = new HashMap<String, Object>();
                map2 = JsonUtil.toBean(JsonUtil.toString(result3.getData()), Map.class);
                List<TourBaseOutputVo> list2 = new ArrayList<TourBaseOutputVo>();
                list2 = JsonUtil.toList(JsonUtil.toString(map2.get("rows")), TourBaseOutputVo.class);
                int count = (int) map2.get("count");
                if (count > 0) {
                    tourBaseOutputVo = list2.get(0);
                }
            }

            // 生成切位单
            OrdDealOrder ordDealOrder = new OrdDealOrder();
            initOrdDealOrder(ordDealOrder, proBaseOutputVo, tourBaseOutputVo, newDepartDate);
            ordDealOrderMapper.insertSelective(ordDealOrder);
            ordDealOrder.setOrderId(ordDealOrder.getId());
            ordDealOrderMapper.updateByPrimaryKeySelective(ordDealOrder);
            LOG.info("库存类型是FS生产的切位单号:{},请求入参:{}", ordDealOrder.getId(), JsonUtil.toString(confirmInputVo));

            // 生成价格详情
            OrdPriceDetail ordPriceDetail = new OrdPriceDetail();
            initOrdPriceDetail(ordPriceDetail, ordDealOrder.getId(), newDepartDate);
            ordPriceDetailMapper.insertSelective(ordPriceDetail);

            // 设置newDepartDate的属性extPurchaseId
            newDepartDate.setExtPurchaseId(ordDealOrder.getId());

            // 调用入即占接口
            StockInputFSVo stockInputFSVo = new StockInputFSVo();
            initStockInputFSVo(stockInputFSVo, ordDealOrder, newDepartDate, proBaseOutputVo);
            ResponseVo result4 = stockProxy.productOccupySynApply(stockInputFSVo);
            LOG.info("入即占接口请求入参:{},响应结果:{}", JsonUtil.toString(stockInputFSVo), JsonUtil.toString(result4));

        }
    }

    /**
     * 初始化价格
     * 
     * @param ordPriceDetail
     * @param orderId
     * @param newDepartDate
     */
    public void initOrdPriceDetail(OrdPriceDetail ordPriceDetail, Integer orderId, NewDepartDate newDepartDate) {
        ordPriceDetail.setOrderId(orderId);
        ordPriceDetail.setStockAdultCount(newDepartDate.getAffirmAdultNum());
        ordPriceDetail.setAdultPrice(newDepartDate.getAffirmAdultPrice());
        ordPriceDetail.setStockChildCount(newDepartDate.getAffirmChildNum());
        ordPriceDetail.setChildPrice(newDepartDate.getAffirmChildPrice());
        ordPriceDetail.setCurrencyType("8");

        BaseDomainUtil.setBasePropertyValue(ordPriceDetail);
    }

    /**
     * 初始化切位单
     * 
     * @param ordDealOrder
     * @param proBaseOutputVo
     * @param tourBaseOutputVo
     */
    public void initOrdDealOrder(OrdDealOrder ordDealOrder, ProBaseOutputVo proBaseOutputVo, TourBaseOutputVo tourBaseOutputVo,
            NewDepartDate newDepartDate) {
        ordDealOrder.setProductId(proBaseOutputVo.getId());
        ordDealOrder.setProductName(proBaseOutputVo.getProductName());
        ordDealOrder.setDestCategoryId(proBaseOutputVo.getDestCategoryId());
        ordDealOrder.setDestCategoryName(proBaseOutputVo.getDestCategoryName());
        ordDealOrder.setFirstDestGroupId(proBaseOutputVo.getFirstDestGroupId());
        ordDealOrder.setFirstDestGroupName(proBaseOutputVo.getFirstDestGroupName());
        ordDealOrder.setSecDestGroupId(proBaseOutputVo.getSecDestGroupId());
        ordDealOrder.setSecDestGroupName(proBaseOutputVo.getSecDestGroupName());
        ordDealOrder.setDestId(proBaseOutputVo.getDestId());
        ordDealOrder.setDestName(proBaseOutputVo.getDestName());
        ordDealOrder.setProductLineId(proBaseOutputVo.getProductLineId());

        ordDealOrder.setDepartureCityCode(proBaseOutputVo.getDepartureCityCode());
        ordDealOrder.setDepartureCityName(proBaseOutputVo.getDepartureCityName());
        ordDealOrder.setContinentCode(proBaseOutputVo.getContinentCode());
        ordDealOrder.setContinentName(proBaseOutputVo.getContinentName());
        ordDealOrder.setCountryCode(proBaseOutputVo.getCountryCode());
        ordDealOrder.setCountryName(proBaseOutputVo.getCountryName());
        ordDealOrder.setProvinceCode(proBaseOutputVo.getProvinceCode());
        ordDealOrder.setProvinceName(proBaseOutputVo.getProvinceName());
        ordDealOrder.setCityCode(proBaseOutputVo.getCityCode());
        ordDealOrder.setCityName(proBaseOutputVo.getCityName());
        ordDealOrder.setDistrictCode(proBaseOutputVo.getDistrictCode());
        ordDealOrder.setDistrictName(proBaseOutputVo.getDistrictName());

        ordDealOrder.setGroupId(tourBaseOutputVo.getTourId());
        ordDealOrder.setGroupName(tourBaseOutputVo.getTourName());
        try {
            ordDealOrder
                    .setDepartDate(org.apache.commons.lang3.time.DateUtils.parseDate(newDepartDate.getDate(), "yyyy-MM-dd"));
        } catch (ParseException ex) {
            LOG.error(ex.getMessage(), ex);
        }

        ProductIdParamVo productIdParamVo = new ProductIdParamVo();
        productIdParamVo.setProductId(proBaseOutputVo.getId());
        // 出游天数
        String tourDay = pRDProxy.getTourDay(pRDProxy.getProductTourDay(productIdParamVo));
        if (tourDay != null) {
            Date backDate = com.tuniu.ngsp.ddg.util.date.DateUtils.addDays(ordDealOrder.getDepartDate(),
                    Integer.parseInt(tourDay) - 1);
            ordDealOrder.setBackDate(backDate);
        }

        ordDealOrder.setDealOrderType(new Integer(3));
        ordDealOrder.setDealOrderNum(
                newDepartDate.getAffirmAdultNum() + newDepartDate.getAffirmChildNum() + newDepartDate.getAffirmBabyNum());
        ordDealOrder.setAdultPrice(newDepartDate.getAffirmAdultPrice());
        ordDealOrder.setChildPrice(newDepartDate.getAffirmChildPrice());
        ordDealOrder.setStatusCode(OrderStateEnum.INITIAL.getStatusCode());

        BaseDomainUtil.setBasePropertyValue(ordDealOrder);

    }

    /**
     * 初始化入即占入参
     * 
     * @param stockInputFSVo
     * @param ordDealOrder
     * @param newDepartDate
     */
    public void initStockInputFSVo(StockInputFSVo stockInputFSVo, OrdDealOrder ordDealOrder, NewDepartDate newDepartDate,
            ProBaseOutputVo proBaseOutputVo) {
        DepartDateFSVo departDateFSVo = new DepartDateFSVo();
        departDateFSVo.setAdultCost(newDepartDate.getAffirmAdultPrice());
        departDateFSVo.setBabyCost(newDepartDate.getAffirmBabyPrice());
        departDateFSVo.setChildCost(newDepartDate.getAffirmChildPrice());
        departDateFSVo.setAdultCostRMB(newDepartDate.getAffirmAdultPrice());
        departDateFSVo.setChildCostRMB(newDepartDate.getAffirmChildPrice());
        departDateFSVo.setBabyCostRMB(newDepartDate.getAffirmBabyPrice());
        departDateFSVo.setAdultNum(newDepartDate.getAffirmAdultNum());
        departDateFSVo.setChildNum(newDepartDate.getAffirmChildNum());
        departDateFSVo.setBabyNum(newDepartDate.getAffirmBabyNum());
        departDateFSVo.setDepartDate(newDepartDate.getDate());
        List<DepartDateFSVo> departDates = new ArrayList<DepartDateFSVo>();
        departDates.add(departDateFSVo);
        DProFSVo dProFSVo = new DProFSVo();
        dProFSVo.setDepartDates(departDates);
        dProFSVo.setCostCurrencyType(8);
        dProFSVo.setdProductId(ordDealOrder.getProductId());
        dProFSVo.setdProductName(ordDealOrder.getProductName());
        dProFSVo.setdProductType(1111);
        dProFSVo.setProductLeaderId(proBaseOutputVo.getUserId());
        dProFSVo.setProductLeaderName(proBaseOutputVo.getName());
        dProFSVo.setStockType(StockTypeEnum.Free_Sale.getKey());
        dProFSVo.setVendorId(SystemConstants.vendorId);
        dProFSVo.setVendorName(SystemConstants.vendorName);
        Date date = new Date(System.currentTimeMillis() + 48 * 60 * 60 * 1000);
        dProFSVo.setReleaseTime(DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));
        List<DProFSVo> dProducts = new ArrayList<DProFSVo>();
        dProducts.add(dProFSVo);
        stockInputFSVo.setClientFlag(Constants.CLIENT_FLAG);
        stockInputFSVo.setSessionId(SessionUtil.createSessionId());
        stockInputFSVo.setOrderId(ordDealOrder.getId());
        stockInputFSVo.setBusinessType(1111);
        stockInputFSVo.setChannelId(1);
        stockInputFSVo.setChannelName("途牛旅游网");
        stockInputFSVo.setdProducts(dProducts);

    }

    @Override
    public ResponseVo brushDataForGrpSale(List<Integer> extOrderIds) {
        List<Map<Integer, String>> error = new ArrayList<Map<Integer, String>>();

        for (Integer extOrderId : extOrderIds) {
            try {
                SalesConfirmVo salesConfirmVo = new SalesConfirmVo();
                salesConfirmVo.setTenantId("tuniu");

                List<SaleVo> sales = new ArrayList<SaleVo>();
                List<OrdSalesOrder> ordSalesOrders = ordSalesOrderMapper
                        .getTransferedAOrderIds(Collections.singletonList(extOrderId));
                for (OrdSalesOrder ordSalesOrder : ordSalesOrders) {
                    SaleVo saleVo = new SaleVo();
                    saleVo.setOrderId(ordSalesOrder.getId());

                    OrdDealOrder ordDealOrder = ordDealOrderMapper.selectByOrderId(ordSalesOrder.getOrderId());

                    ExternalResponseObj<QueryGroupOutputVo> groupBaseInfo = ExternalRestUtil.getGroupBaseInfo(
                            ordDealOrder.getProductId(), new String[] { com.tuniu.operation.framework.base.time.DateFormatUtils
                                    .formatDate(ordDealOrder.getDepartDate()) });

                    if (groupBaseInfo.isSuccess() && groupBaseInfo.getData() != null
                            && CollectionUtils.isNotEmpty(groupBaseInfo.getData().getRows())) {
                        saleVo.setTourBasicId(groupBaseInfo.getData().getRows().get(0).getTourId());
                    }

                    saleVo.setStartDate(
                            com.tuniu.operation.framework.base.time.DateFormatUtils.formatDate(ordDealOrder.getDepartDate()));
                    // saleVo.setBackDate();

                    saleVo.setChannelId("1");
                    saleVo.setChannelOrderId(ordSalesOrder.getExtOrderId().toString());
                    saleVo.setAdultNum(ordSalesOrder.getAdultCount());
                    saleVo.setChildNum(ordSalesOrder.getChildCount());
                    saleVo.setAddNum(ordSalesOrder.getRoomAddNum());
                    saleVo.setAdultCost(ordSalesOrder.getAdultPrice());
                    saleVo.setChildCost(ordSalesOrder.getChildPrice());
                    saleVo.setAddPrice(ordSalesOrder.getRoomAddPrice());
                    saleVo.setTotalCost(ordSalesOrder.getTotalPrice());
                    saleVo.setCurrencyType("8");
                    // saleVo.setSpecialRemark();
                    // saleVo.setRemark();
                    // saleVo.setSupplyRemark();
                    // saleVo.setOtherResources();
                    // saleVo.setOrderRemark();
                    // saleVo.setUpgradeId(0);
                    // saleVo.setProductLineId();
                    // saleVo.setMemberId();
                    // saleVo.setMemberName();
                    saleVo.setMigrateFlag(0);

                    PeopleTouristVo peopleTouristVo = new PeopleTouristVo();
                    peopleTouristVo.setSellOrderIds(Collections.singletonList(ordSalesOrder.getId()));
                    List<OrdPeopleTourist> ordPeopleTourists = ordPeopleTouristMapper.selectByFields(peopleTouristVo);

                    List<ConsumerVo> consumers = new ArrayList<ConsumerVo>(ordPeopleTourists.size());

                    for (OrdPeopleTourist ordPeopleTourist : ordPeopleTourists) {
                        ConsumerVo consumerVo = new ConsumerVo();
                        consumerVo.setConsumerId(ordPeopleTourist.getId());
                        consumerVo.setConsumerName(ordPeopleTourist.getName());
                        consumerVo.setSex(Integer.valueOf(ordPeopleTourist.getSex()));
                        consumerVo.setConsumerAge(ordPeopleTourist.getAge());
                        // consumerVo.setConsumerBirthday(com.tuniu.operation.framework.base.time.DateFormatUtils.formatDate(ordPeopleTourist.getBirthOfDate()));
                        // consumerVo.setCardType(ordPeopleTourist.getPsptType());
                        // consumerVo.setConsumerCardNo(ordPeopleTourist.getPsptId());
                        consumerVo.setConsumerPhone(ordPeopleTourist.getTel());
                        consumerVo.setConsumerAgeSegment(Integer.valueOf(ordPeopleTourist.getTouristType()));
                        // consumerVo.setConsumerLevel();
                        if (ordPeopleTourist.getFabContactId().length() != 32) {
                            consumerVo.setFabId(Integer.valueOf(ordPeopleTourist.getFabContactId()));
                        }

                        consumers.add(consumerVo);
                    }

                    saleVo.setConsumers(consumers);

                    sales.add(saleVo);
                }

                salesConfirmVo.setSales(sales);
                ResponseVo grpRes = gRPProxy.sendSalesConfirm(salesConfirmVo);
                if (!grpRes.isSuccess()) {
                    throw new SaaSSystemException(grpRes.getMsg());
                }

            } catch (Exception e) {
                error.add(Collections.singletonMap(extOrderId, e.getMessage()));
            }

        }
        ResponseVo responseVo = new ResponseVo();
        if (CollectionUtils.isNotEmpty(error)) {
            responseVo.setSuccess(false);
            responseVo.setMsg(JsonUtil.toString(error));
        }

        return responseVo;
    }

}
