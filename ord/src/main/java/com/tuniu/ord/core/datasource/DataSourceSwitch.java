package com.tuniu.ord.core.datasource;

import org.apache.commons.lang3.StringUtils;

import com.tuniu.ord.core.exception.IllegalArgumentException;

/**
 * @author fangzhongwei
 * 
 */
public class DataSourceSwitch {

    private static final ThreadLocal<String> DATASOURCE_TL = new ThreadLocal<String>();
    private static final ThreadLocal<String> TENANT_ID_TL = new ThreadLocal<String>();

    private DataSourceSwitch() {
    }

    public static void setTenantId(String tenantId) {
        TENANT_ID_TL.set(tenantId);
    }

    public static String getTenantId() {
        return TENANT_ID_TL.get();
    }

    public static void set(String dsKey) {
        DATASOURCE_TL.set(dsKey);
    }

    public static String get() {
        String dsKey = DATASOURCE_TL.get();
        if (StringUtils.isEmpty(dsKey)) {
            throw new IllegalArgumentException("Cannot get datasource tenant key.");
        }
        return dsKey;
    }
}
