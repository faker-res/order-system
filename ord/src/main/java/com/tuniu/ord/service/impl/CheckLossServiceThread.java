/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年6月8日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tuniu.ord.core.datasource.DataSourceEnum;
import com.tuniu.ord.core.datasource.DataSourceSwitch;
import com.tuniu.ord.service.CheckLossService;
import com.tuniu.ord.vo.CheckLossReqVo;

/**
 * @author guojun3
 * 
 */
public class CheckLossServiceThread implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(CheckLossServiceImpl.class);
    private final CheckLossService checkLossServiceImpl;
    private final CheckLossReqVo checkLossReqVo;
    private final String tenantId;

    public CheckLossServiceThread(CheckLossService checkLossServiceImpl, CheckLossReqVo checkLossReqVo, String tenantId) {
        this.checkLossServiceImpl = checkLossServiceImpl;
        this.checkLossReqVo = checkLossReqVo;
        this.tenantId = tenantId;
    }

    @Override
    public void run() {
        logger.info("CheckLossServiceThread:run()");
        try {
            DataSourceEnum ds = DataSourceEnum.getDataSource(tenantId);
            DataSourceSwitch.set(ds.getMasterDataSourceBeanId());
            DataSourceSwitch.setTenantId(tenantId);
            checkLossServiceImpl.addCheckLossByAPI(checkLossReqVo);
        } catch (Exception e) {
            logger.error("处理核损信息发生错误", e);
        }
    }
}
