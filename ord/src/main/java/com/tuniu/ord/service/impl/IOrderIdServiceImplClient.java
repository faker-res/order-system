/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年8月5日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tuniu.ngsp.pos.client.monitor.OrderIdCapacityMonitor;
import com.tuniu.ngsp.pos.client.service.IOrderIdService;
import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.ord.service.IOrderIdServiceClient;
import com.tuniu.ord.vo.OrderIdIntervalVo;

/**
 * @author zhairongping
 *
 */
@Service
public class IOrderIdServiceImplClient implements IOrderIdServiceClient {
    private static final Logger LOG = LoggerFactory.getLogger(IOrderIdServiceImplClient.class);

    @Resource(name = "posLocalOrderIdServiceImpl")
    private IOrderIdService orderIdService;

    @Resource
    private OrderIdCapacityMonitor orderIdCapacityMonitor;

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.IOrderIdServiceClient#getOrderNum(java.lang.Integer)
     */
    @Override
    public Long getOrderNum(Integer intervalTypeId) {
        try {
            return orderIdService.getOrderNum(intervalTypeId);
        } catch (Exception e) {
            LOG.info("【IOrderIdServiceImplClient-getOrderNum】本地生成订单号失败,从POS服务器申请订单号,POS服务器出现异常:{}", e.getMessage());
            return new Long(0);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tuniu.ord.service.IOrderIdServiceClient#refreshOrderIdInterval(com.tuniu.ord.vo.OrderIdIntervalVo)
     */
    @Override
    public void refreshOrderIdInterval(OrderIdIntervalVo input) {
        String paramJson = JsonUtil.toString(input);
        try {
            orderIdCapacityMonitor.refreshOrderIdInterval(paramJson);
        } catch (Exception e) {
            LOG.info("【refreshOrderIdInterval】刷新订单号区间异常:{}", e.getMessage());
        }
    }

}
