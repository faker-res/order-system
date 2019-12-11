/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2017 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2017年3月7日                                                      
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
 * @author zhairongping
 *
 */
public class ProxyAdvice implements MethodBeforeAdvice, AfterReturningAdvice {
    private static final Logger LOG = LoggerFactory.getLogger(ProxyAdvice.class);

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.aop.MethodBeforeAdvice#before(java.lang.reflect.Method, java.lang.Object[], java.lang.Object)
     */
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        LOG.info("【class】:{},【method】:{},【param】:{}", target.getClass().getName(), method.getName(), JsonUtil.toString(args));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.aop.AfterReturningAdvice#afterReturning(java.lang.Object, java.lang.reflect.Method,
     * java.lang.Object[], java.lang.Object)
     */
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        LOG.info("【class】:{},【method】:{},【result】:{}", target.getClass().getName(), method.getName(),
                JsonUtil.toString(returnValue));
    }

}
