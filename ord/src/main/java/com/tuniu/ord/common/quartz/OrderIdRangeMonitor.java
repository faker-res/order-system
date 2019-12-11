/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年8月6日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.common.quartz;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tuniu.operation.framework.base.json.JsonUtil;
import com.tuniu.ord.core.datasource.DataSourceEnum;
import com.tuniu.ord.core.datasource.DataSourceSwitch;
import com.tuniu.ord.service.IOrderIdServiceClient;
import com.tuniu.ord.vo.OrderIdIntervalVo;

/**
 * 【订单号容量监控器】
 * 
 * @author zhairongping
 *
 */
public class OrderIdRangeMonitor {
    private final static Logger LOG = LoggerFactory.getLogger(OrderIdRangeMonitor.class);

    @Resource
    private IOrderIdServiceClient iOrderIdServiceImplClient;

    /**
     * 刷新订单号区间
     * 
     * @param paramJson
     */
    public void execute(String paramJson) {
        OrderIdIntervalVo orderIdIntervalVo = JsonUtil.toBean(paramJson, OrderIdIntervalVo.class);
        String tenantId = DataSourceEnum.TUNIU.getTenantId();
        DataSourceEnum ds = DataSourceEnum.getDataSource(tenantId);
        DataSourceSwitch.set(ds.getMasterDataSourceBeanId());
        DataSourceSwitch.setTenantId(tenantId);
        long start = System.currentTimeMillis();
        LOG.info("【刷新订单号区间定时任务开始】");
        iOrderIdServiceImplClient.refreshOrderIdInterval(orderIdIntervalVo);
        LOG.info("【刷新订单号区间定时任务结束,总需要时间:{}秒】", (System.currentTimeMillis() - start) / 1000);
    }
}
