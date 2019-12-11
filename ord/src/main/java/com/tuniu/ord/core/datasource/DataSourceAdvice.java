package com.tuniu.ord.core.datasource;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import com.tuniu.operation.platform.tsg.base.core.utils.JsonUtil;

/**
 * 主从数据库切换
 * 
 * @author zhairongping
 * 
 */
public class DataSourceAdvice implements MethodBeforeAdvice, AfterReturningAdvice {
    private static final Logger LOG = LoggerFactory.getLogger(DataSourceAdvice.class);

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        LOG.info("【DataSourceAdvice-before】 class:{},method:{},param:{}", target.getClass().getName(), method.getName(),
                JsonUtil.toString(args));
        DataSourceEnum ds = DataSourceEnum.getDataSource(DataSourceSwitch.getTenantId());
        if (MethodEnum.isOnlyRead(method.getName())) {
            DataSourceSwitch.set(ds.getSlaveDataSourceBeanId());
        } else {
            DataSourceSwitch.set(ds.getMasterDataSourceBeanId());
        }
    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        LOG.info("【DataSourceAdvice-afterReturning】class:{},method:{},result:{}", target.getClass().getName(), method.getName(),
                JsonUtil.toString(returnValue));
        DataSourceEnum ds = DataSourceEnum.getDataSource(DataSourceSwitch.getTenantId());
        DataSourceSwitch.set(ds.getMasterDataSourceBeanId());
    }
}
