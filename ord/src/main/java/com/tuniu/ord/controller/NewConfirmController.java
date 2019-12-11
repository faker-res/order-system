/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年9月26日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.controller;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.ord.common.util.EnvironmentUtil;
import com.tuniu.ord.common.util.RestUtil;
import com.tuniu.ord.core.datasource.DataSourceEnum;
import com.tuniu.ord.core.datasource.DataSourceSwitch;
import com.tuniu.ord.core.mail.client.APISys;
import com.tuniu.ord.core.mail.client.ORDMailClient;
import com.tuniu.ord.domain.OrdSalesOrder;
import com.tuniu.ord.persistence.OrdSalesOrderMapper;
import com.tuniu.ord.service.ChannelOrderService;
import com.tuniu.ord.service.NewConfirmService;
import com.tuniu.ord.vo.CancelConfirmInputVo;
import com.tuniu.ord.vo.ConfirmFeedBackInputVo;
import com.tuniu.ord.vo.ConfirmInputVo;
import com.tuniu.ord.vo.DealConfirmResult;
import com.tuniu.ord.vo.NewDepartDate;
import com.tuniu.ord.vo.RedoSendGroupVo;
import com.tuniu.ord.vo.ResponseVo;
import com.tuniu.ord.vo.ResultVo;
import com.tuniu.ord.vo.TuniuInfo;

/**
 * 【新确认管理-提供给API服务接口】
 * 
 * @author zhairongping
 *
 */
@Controller
@RequestMapping("/api/confirm")
public class NewConfirmController {
    private static final Logger LOG = LoggerFactory.getLogger(NewConfirmController.class);

    @Resource
    private NewConfirmService newConfirmServiceImpl;

    @Resource
    private ChannelOrderService channelOrderServiceImpl;

    @Resource
    private OrdSalesOrderMapper ordSalesOrderMapper;

    /**
     * 新的发起确认
     * 
     * @param request
     * @param response
     * @param confirmInputVo
     */
    @RequestMapping(value = "/initiateConfirm", method = RequestMethod.POST)
    public void newInitiateConfirm(HttpServletRequest request, HttpServletResponse response, ConfirmInputVo confirmInputVo) {
        ResponseVo responseVo = new ResponseVo();

        ResultVo rv = newConfirmServiceImpl.validateNewInitiateConfirm(confirmInputVo);
        LOG.info("【新确认管理-处理确认请求的校验结果】校验成功与否:" + rv.isSuccess() + ",提示信息:" + rv.getMsg() + "######请求入参:"
                + JsonUtil.toString(confirmInputVo));
        if (!rv.isSuccess()) {
            responseVo.setSuccess(false);
            responseVo.setMsg(rv.getMsg());
            RestUtil.write(response, responseVo);

            if (EnvironmentUtil.isPrdEnv()) {
                ORDMailClient.sendMailToSystem(
                        new APISys("发起确认失败原因:[" + rv.getMsg() + "],发起确认请求参数:[" + JsonUtil.toString(confirmInputVo) + "]"));
            }
            return;
        }

        if (!rv.isOpeFlag()) {
            // 位置数小于出游人数
            channelOrderServiceImpl.addOrdChannelOrder(confirmInputVo);
            RestUtil.write(response, responseVo);
            return;
        }

        String tenantId = DataSourceSwitch.getTenantId();
        List<NewDepartDate> newDepartDateList = newConfirmServiceImpl.rebuilt(confirmInputVo.getProducts().get(0));
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.submit(new NewExecutorConfirm(confirmInputVo, newDepartDateList, tenantId));
        RestUtil.write(response, responseVo);
    }

    /**
     * 新的取消确认
     * 
     * @param request
     * @param response
     * @param cancelConfirmInputVo
     */
    @RequestMapping(value = "/cancelConfirm", method = RequestMethod.POST)
    public void newCancelConfirm(HttpServletRequest request, HttpServletResponse response,
            CancelConfirmInputVo cancelConfirmInputVo) {
        ResponseVo responseVo = new ResponseVo();
        ResultVo resultVo = newConfirmServiceImpl.validateNewCancelConfirm(cancelConfirmInputVo);
        LOG.info("【新确认管理-处理取消确认请求的校验结果】校验成功与否:" + resultVo.isSuccess() + ",提示信息:" + resultVo.getMsg() + "######请求入参:"
                + JsonUtil.toString(cancelConfirmInputVo));
        if (!resultVo.isSuccess()) {
            responseVo.setSuccess(true);
            responseVo.setMsg(resultVo.getMsg());
            RestUtil.write(response, responseVo);

            if (EnvironmentUtil.isPrdEnv()) {
                ORDMailClient.sendMailToSystem(new APISys(
                        "取消确认失败原因:[" + resultVo.getMsg() + "],取消确认请求参数:[" + JsonUtil.toString(cancelConfirmInputVo) + "]"));
            }
            return;
        }

        newConfirmServiceImpl.dealCancelConfirm(cancelConfirmInputVo);
        responseVo.setErrorCode(241000);
        responseVo.setMsg("成功");
        RestUtil.write(response, responseVo);
    }

    /**
     * 异步线程处理发起确认逻辑【内部类】
     * 
     * @author zhairongping
     *
     */
    private class NewExecutorConfirm implements Callable {
        public final ConfirmInputVo confirmInputVo;
        public final List<NewDepartDate> newDepartDateList;
        public final String tenantId;

        public NewExecutorConfirm(ConfirmInputVo confirmInputVo, List<NewDepartDate> newDepartDateList, String tenantId) {
            super();
            this.confirmInputVo = confirmInputVo;
            this.newDepartDateList = newDepartDateList;
            this.tenantId = tenantId;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.util.concurrent.Callable#call()
         */
        @Override
        public Object call() throws Exception {
            DataSourceEnum ds = DataSourceEnum.getDataSource(tenantId);
            DataSourceSwitch.set(ds.getMasterDataSourceBeanId());
            DataSourceSwitch.setTenantId(tenantId);

            try {
                Thread.sleep(1000 * 5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TuniuInfo tuniuInfo = confirmInputVo.getProducts().get(0).getTuniuInfo();
            DealConfirmResult dealConfirmResult = new DealConfirmResult();
            dealConfirmResult.setTuniuOrderId(tuniuInfo.getTuniuOrderId());
            dealConfirmResult.setRequirementId(tuniuInfo.getRequirementId());
            // 多批次处理发起确认逻辑
            for (NewDepartDate newDepartDate : newDepartDateList) {
                newConfirmServiceImpl.dealConfirm(newDepartDate, confirmInputVo, dealConfirmResult);
            }

            // 异步反馈结果
            ConfirmFeedBackInputVo confirmFeedBackInputVo = new ConfirmFeedBackInputVo();
            newConfirmServiceImpl.initConfirmFeedBackInputParam(confirmFeedBackInputVo, newDepartDateList, confirmInputVo);
            newConfirmServiceImpl.confirmFeedBack(confirmFeedBackInputVo, dealConfirmResult);

            // 统计一次确认请求的操作结果
            LOG.info("【新确认管理-处理确认请求的操作结果】" + JsonUtil.toString(dealConfirmResult) + "######请求入参:"
                    + JsonUtil.toString(confirmInputVo));
            return null;
        }
    }

    @RequestMapping(value = "/redo/sendGroup", method = RequestMethod.POST)
    public void sendGroup(HttpServletRequest request, HttpServletResponse response, RedoSendGroupVo redoSendGroupVo) {
        ResponseVo responseVo = newConfirmServiceImpl.brushDataForGrpSale(redoSendGroupVo.getExtOrderIds());
        RestUtil.write(response, responseVo);
    }

    @RequestMapping(value = "/ordSaleOrder", method = RequestMethod.POST)
    public void sendGroup(HttpServletRequest request, HttpServletResponse response, OrdSalesOrder ordSalesOrder) {
        ResponseVo responseVo = new ResponseVo();
        try {
            ordSalesOrderMapper.updateByPrimaryKeySelective(ordSalesOrder);
        } catch (Exception e) {
            responseVo.setSuccess(false);
            responseVo.setMsg(e.getMessage());
        }
        RestUtil.write(response, responseVo);
    }
}
