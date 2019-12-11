/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年6月16日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.operation.framework.base.time.DateUtils;
import com.tuniu.ord.common.util.EnvironmentUtil;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.common.util.UserSessionUtil;
import com.tuniu.ord.core.datasource.DataSourceEnum;
import com.tuniu.ord.core.datasource.DataSourceSwitch;
import com.tuniu.ord.core.mail.client.ORDMailClient;
import com.tuniu.ord.domain.OrdTransferOrder;
import com.tuniu.ord.enums.TransferOrderStatusEnum;
import com.tuniu.ord.persistence.OrdTransferOrderMapper;
import com.tuniu.ord.service.SimpleConfirmService;
import com.tuniu.ord.vo.EDMInputVo;
import com.tuniu.ord.vo.PlaceOrderInputVo;
import com.tuniu.ord.vo.ResponseVo;
import com.tuniu.ord.vo.SimpleOrderInfoVo;
import com.tuniu.ord.vo.Tourist;
import com.tuniu.ord.vo.TransferedAOrderIdsInputAndOutputVo;
import com.tuniu.ord.vo.ValidateRes;

/**
 * 【简化版下单-数据迁移】
 * 
 * @author zhairongping
 *
 */
@Controller
@RequestMapping("/api/grp")
public class SimpleConfirmController {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleConfirmController.class);

    private static final Integer MAX_QUERY_CATACITY = 500 * 200;

    private static final Integer MAX_TRANSFER_ORDER_CATACITY = 500 * 10;

    @Resource
    private SimpleConfirmService simpleConfirmServiceImpl;

    @Resource
    private OrdTransferOrderMapper ordTransferOrderMapper;

    /**
     * 批量执行数据迁移任务
     * 
     * @param request
     * @param response
     * @param placeOrderInputVo
     */
    @RequestMapping(value = "/place-order", method = RequestMethod.POST)
    public void placeOrder(HttpServletRequest request, HttpServletResponse response, PlaceOrderInputVo placeOrderInputVo) {
        ResponseVo responseVo = new ResponseVo();
        /* 数据校验操作 */
        ValidateRes vr = validate(placeOrderInputVo);
        LOG.info("数据迁移校验结果:[{}],提示信息:[{}],请求入参:[{}]", vr.isSuccess(), JsonUtil.toString(vr.getData()),
                JsonUtil.toString(placeOrderInputVo));
        if (false == vr.isSuccess()) {
            responseVo.setSuccess(false);
            responseVo.setMsg(JsonUtil.toString(vr.getData()));
            RestUtil.write(response, responseVo);
            return;
        }

        /* 订单过滤操作 */
        filter(placeOrderInputVo);

        ValidateRes vr2 = validate(placeOrderInputVo);
        if (false == vr2.isSuccess()) {
            responseVo.setSuccess(false);
            responseVo.setMsg(JsonUtil.toString(vr2.getData()));
            RestUtil.write(response, responseVo);
            return;
        }

        /* 批量执行数据迁移任务 */
        new Thread(new BatchExecuteTransfer(placeOrderInputVo, DataSourceSwitch.getTenantId())).start();
        responseVo.setMsg("已经接收到批量数据迁移任务");
        RestUtil.write(response, responseVo);
    }

    /**
     * 查询数据迁移成功的A类订单
     * 
     * @param request
     * @param response
     * @param transferedAOrderIdsInputAndOutputVo
     */
    @RequestMapping(value = "/get-transfered-aOrder-ids", method = RequestMethod.GET)
    public void getTransferedAOrderIds(HttpServletRequest request, HttpServletResponse response,
            TransferedAOrderIdsInputAndOutputVo transferedAOrderIdsInputAndOutputVo) {
        ResponseVo responseVo = new ResponseVo();
        // 数据校验操作
        ValidateRes valRes = validateTransferedAOrderIds(transferedAOrderIdsInputAndOutputVo);
        if (valRes.isSuccess() == false) {
            responseVo.setSuccess(false);
            responseVo.setMsg(valRes.getData().get(0));
            RestUtil.write(response, responseVo);
            return;
        }
        // 查询数据迁移成功的A类订单
        simpleConfirmServiceImpl.getTransferedAOrderIds(transferedAOrderIdsInputAndOutputVo);
        responseVo.setData(transferedAOrderIdsInputAndOutputVo);
        RestUtil.write(response, responseVo);
    }

    /**
     * 数据校验-查询数据迁移成功的A类订单
     * 
     * @param transferedAOrderIdsInputAndOutputVo
     * @return
     */
    private ValidateRes validateTransferedAOrderIds(TransferedAOrderIdsInputAndOutputVo transferedAOrderIdsInputAndOutputVo) {
        ValidateRes validateRes = new ValidateRes();
        if (transferedAOrderIdsInputAndOutputVo.getOrderIds() == null
                || transferedAOrderIdsInputAndOutputVo.getOrderIds().size() == 0) {
            validateRes.setSuccess(false);
            validateRes.getData().add("A类订单不能为空");
            return validateRes;
        }
        if (transferedAOrderIdsInputAndOutputVo.getOrderIds().size() > MAX_QUERY_CATACITY.intValue()) {
            validateRes.setSuccess(false);
            validateRes.getData().add("查询A类订单的容量不能超过" + MAX_QUERY_CATACITY.intValue());
            return validateRes;
        }
        return validateRes;
    }

    /**
     * 数据校验-执行数据迁移任务
     * 
     * @param placeOrderInputVo
     * @return
     */
    private ValidateRes validate(PlaceOrderInputVo placeOrderInputVo) {
        ValidateRes validateRes = new ValidateRes();
        // 简单版下单数据校验规则: 1.订单-订单列表不能为空 迁移数据量限制 途牛订单号不能为空;2.出游人-游客姓名、证件类型和ID必填
        if (placeOrderInputVo.getOrderInfoList() == null || placeOrderInputVo.getOrderInfoList().size() == 0) {
            validateRes.setSuccess(false);
            validateRes.getData().add("订单列表不能为空");
            return validateRes;
        }
        if (placeOrderInputVo.getOrderInfoList().size() > MAX_TRANSFER_ORDER_CATACITY) {
            validateRes.setSuccess(false);
            validateRes.getData().add("一次迁移数据量不能超过" + MAX_TRANSFER_ORDER_CATACITY);
            return validateRes;
        }
        for (SimpleOrderInfoVo soi : placeOrderInputVo.getOrderInfoList()) {
            if (soi.getTuniuOrderId() == null) {
                validateRes.setSuccess(false);
                validateRes.getData().add("订单列表中途牛订单号不能为空");
                return validateRes;
            }
        }
        for (SimpleOrderInfoVo soi : placeOrderInputVo.getOrderInfoList()) {
            if (soi.getdProductId() == null || soi.getdProductId().intValue() == 0) {
                validateRes.setSuccess(false);
                validateRes.getData().add("订单列表中途牛订单号" + soi.getTuniuOrderId() + "下D类产品ID不能为空");
                return validateRes;
            }
        }
        for (SimpleOrderInfoVo soi : placeOrderInputVo.getOrderInfoList()) {
            if (soi.getDate() == null || "".equals(soi.getDate())) {
                validateRes.setSuccess(false);
                validateRes.getData().add("订单列表中途牛订单号" + soi.getTuniuOrderId() + "下 出游日期不能为空");
                return validateRes;
            }
        }
        for (SimpleOrderInfoVo soi : placeOrderInputVo.getOrderInfoList()) {
            if (soi.getTouristInfoList() == null || soi.getTouristInfoList().size() == 0) {
                validateRes.setSuccess(false);
                validateRes.getData().add("途牛订单号" + soi.getTuniuOrderId() + "下出游人列表不能为空");
                return validateRes;
            }
        }
        for (SimpleOrderInfoVo soi : placeOrderInputVo.getOrderInfoList()) {
            for (Tourist t : soi.getTouristInfoList()) {
                if (t.getTouristId() == null || "".equals(t.getTouristId())) {
                    validateRes.setSuccess(false);
                    validateRes.getData().add("途牛订单号" + soi.getTuniuOrderId() + "下出游人ID不能为空");
                    return validateRes;
                }
                if (t.getTouristName() == null || "".equals(t.getTouristName())) {
                    validateRes.setSuccess(false);
                    validateRes.getData().add("途牛订单号" + soi.getTuniuOrderId() + "下出游人姓名不能为空");
                    return validateRes;
                }
                if (t.getPsptType() == null) {
                    validateRes.setSuccess(false);
                    validateRes.getData().add("途牛订单号" + soi.getTuniuOrderId() + "下出游人证件类型不能为空");
                    return validateRes;
                }
            }
        }
        return validateRes;
    }

    /**
     * 订单过滤操作
     * 
     * @param placeOrderInputVo
     */
    private void filter(PlaceOrderInputVo placeOrderInputVo) {
        List<Integer> orderIds = new ArrayList<Integer>();
        for (SimpleOrderInfoVo simpleOrderInfoVo : placeOrderInputVo.getOrderInfoList()) {
            orderIds.add(simpleOrderInfoVo.getTuniuOrderId());
        }
        List<Integer> transferedAOrderIdLists = ordTransferOrderMapper.getTransferedAOrderIdLists(orderIds);
        orderIds.removeAll(transferedAOrderIdLists);
        List<SimpleOrderInfoVo> list = new ArrayList<SimpleOrderInfoVo>();
        for (SimpleOrderInfoVo simpleOrderInfoVo : placeOrderInputVo.getOrderInfoList()) {
            if (orderIds.contains(simpleOrderInfoVo.getTuniuOrderId())) {
                list.add(simpleOrderInfoVo);
            }
        }
        placeOrderInputVo.setOrderInfoList(list);
    }

    private class BatchExecuteTransfer implements Runnable {
        private final Logger LOG = LoggerFactory.getLogger(BatchExecuteTransfer.class);

        private PlaceOrderInputVo placeOrderInputVo;
        private String tenantId;

        public BatchExecuteTransfer(PlaceOrderInputVo placeOrderInputVo, String tenantId) {
            super();
            this.placeOrderInputVo = placeOrderInputVo;
            this.tenantId = tenantId;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LOG.error(e.getMessage());
            }
            DataSourceEnum ds = DataSourceEnum.getDataSource(tenantId);
            DataSourceSwitch.set(ds.getMasterDataSourceBeanId());
            DataSourceSwitch.setTenantId(tenantId);

            long start = System.currentTimeMillis();

            // 存储迁移失败的订单,统一发邮件提醒
            List<String> transferFailedMsg = new ArrayList<String>();
            /* 批量执行数据迁移任务 */
            for (SimpleOrderInfoVo simpleOrderInfoVo : placeOrderInputVo.getOrderInfoList()) {
                OrdTransferOrder transferOrder = new OrdTransferOrder();
                try {
                    // 添加数据迁移订单记录
                    initOrdTransferOrder(transferOrder, simpleOrderInfoVo.getTuniuOrderId());
                    ordTransferOrderMapper.insertSelective(transferOrder);

                    simpleConfirmServiceImpl.savePlaceOrder(simpleOrderInfoVo);

                    // 修改数据迁移订单记录
                    transferOrder.setStatus(TransferOrderStatusEnum.TRANSFERSUCCESS.getKey().toString());
                    transferOrder.setUpdateTime(DateUtils.now());
                    ordTransferOrderMapper.updateByPrimaryKeySelective(transferOrder);
                } catch (Exception e) {
                    LOG.info("数据迁移统计结果:[{}]", e.getMessage());
                    transferFailedMsg.add(e.getMessage());

                    // 修改数据迁移订单记录
                    transferOrder.setStatus(TransferOrderStatusEnum.TRANSFERFAILED.getKey().toString());
                    transferOrder.setUpdateTime(DateUtils.now());
                    ordTransferOrderMapper.updateByPrimaryKeySelective(transferOrder);
                }
            }

            // 邮件提醒功能
            if (CollectionUtils.isNotEmpty(transferFailedMsg)) {
                if (EnvironmentUtil.isPrdEnv()) {
                    EDMInputVo eDMInputVo = new EDMInputVo();
                    String title = "【数据迁移失败邮件提醒】";
                    initEDMInputVo(eDMInputVo, JsonUtil.toString(transferFailedMsg), placeOrderInputVo.getRecipientEmail(),
                            title);
                    ORDMailClient.sendMail(eDMInputVo);
                }
            } else {
                if (EnvironmentUtil.isPrdEnv()) {
                    List<Integer> orderIdList = new ArrayList<Integer>();
                    for (SimpleOrderInfoVo simpleOrderInfoVo : placeOrderInputVo.getOrderInfoList()) {
                        orderIdList.add(simpleOrderInfoVo.getTuniuOrderId());
                    }
                    EDMInputVo eDMInputVo = new EDMInputVo();
                    String title = "【数据迁移成功邮件提醒】";
                    initEDMInputVo(eDMInputVo, "数据" + JsonUtil.toString(orderIdList) + "迁移成功",
                            placeOrderInputVo.getRecipientEmail(), title);
                    ORDMailClient.sendMail(eDMInputVo);
                }
            }

            LOG.info("完成批量执行数据迁移任务需要时间是:[{}]毫秒,请求入参:[{}]", (System.currentTimeMillis() - start),
                    JsonUtil.toString(placeOrderInputVo));
        }
    }

    /**
     * 初始化迁移订单
     * 
     * @param transferOrder
     * @param tuniuOrderId
     */
    private void initOrdTransferOrder(OrdTransferOrder transferOrder, Integer tuniuOrderId) {
        transferOrder.setOrderId(tuniuOrderId);
        transferOrder.setStatus(TransferOrderStatusEnum.TRANSFERING.getKey().toString());
        Date date = DateUtils.now();
        transferOrder.setAddUid(new Integer(UserSessionUtil.getUid()));
        transferOrder.setAddUname(UserSessionUtil.getNickname());
        transferOrder.setAddTime(date);
        transferOrder.setUpdateUid(new Integer(UserSessionUtil.getUid()));
        transferOrder.setUpdateUname(UserSessionUtil.getNickname());
        transferOrder.setUpdateTime(date);
    }

    /**
     * 初始化邮件实例
     * 
     * @param eDMInputVo
     * @param context
     * @param recipientEmail
     */
    public void initEDMInputVo(EDMInputVo eDMInputVo, String context, String recipientEmail, String title) {
        List<String> recipientEmails = new ArrayList<String>();
        List<String> ccEmails = new ArrayList<String>();
        if (recipientEmail != null && !"".equals(recipientEmail)) {
            recipientEmails.add(recipientEmail);
        }
        recipientEmails.add("xiaohua@tuniu.com");
        recipientEmails.add("zhoujie8@tuniu.com");
        recipientEmails.add("zhairongping@tuniu.com");
        recipientEmails.add("zhangxiang8@tuniu.com");
        ccEmails.add("lingchuanzhi@tuniu.com");
        eDMInputVo.setRecipientEmails(recipientEmails);
        eDMInputVo.setCcEmails(ccEmails);
        eDMInputVo.setSubject(title);
        eDMInputVo.setMsgText(context);
    }

}
