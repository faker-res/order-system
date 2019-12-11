/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年2月9日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.core.datasource;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import com.tuniu.operation.platform.tsg.base.core.utils.JsonUtil;

/**
 * 生成订单号服务切面
 * 
 * @author zhairongping
 *
 */
public class OrderNumAdvice implements MethodBeforeAdvice, AfterReturningAdvice {
    private static final Logger LOG = LoggerFactory.getLogger(OrderNumAdvice.class);

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.aop.MethodBeforeAdvice#before(java.lang.reflect.Method, java.lang.Object[], java.lang.Object)
     */
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        LOG.info("【OrderNumAdvice-before】 class:{},method:{},param:{}", target.getClass().getName(), method.getName(),
                JsonUtil.toString(args));
        DataSourceSwitch.set(DataSourceEnum.TUNIU.getMasterDataSourceBeanId());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.aop.AfterReturningAdvice#afterReturning(java.lang.Object, java.lang.reflect.Method,
     * java.lang.Object[], java.lang.Object)
     */
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        LOG.info("【OrderNumAdvice-afterReturning】class:{},method:{},result:{}", target.getClass().getName(), method.getName(),
                JsonUtil.toString(returnValue));
        DataSourceEnum ds = DataSourceEnum.getDataSource(DataSourceSwitch.getTenantId());
        DataSourceSwitch.set(ds.getMasterDataSourceBeanId());
    }

}
