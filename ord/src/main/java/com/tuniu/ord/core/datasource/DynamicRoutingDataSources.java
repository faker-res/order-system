package com.tuniu.ord.core.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author fangzhongwei
 * 
 */
public class DynamicRoutingDataSources extends AbstractRoutingDataSource {
    private final static Logger LOG = LoggerFactory.getLogger(DynamicRoutingDataSources.class);

    @Override
    protected Object determineCurrentLookupKey() {
        Object beanId = DataSourceSwitch.get();
        //LOG.info("++++++++++DynamicRoutingDataSources-determineCurrentLookupKey:[{}]+++++++++++", beanId);
        return beanId;
    }

}
