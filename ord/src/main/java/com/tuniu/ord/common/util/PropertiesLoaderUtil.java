/*******************************************************************************
 *                                                                              
 *  COPYRIGHT (C) 2016 Tuniu Limited - ALL RIGHTS RESERVED.                  
 *                                                                                                                                 
 *  Creation Date: 2016年5月3日                                                      
 *                                                                              
 *******************************************************************************/

package com.tuniu.ord.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fangzhongwei
 * 
 */
public final class PropertiesLoaderUtil {

    private static final Logger LOG = LoggerFactory.getLogger(PropertiesLoaderUtil.class);

    private PropertiesLoaderUtil() {
    }

    public static <T> Properties load(String path, Class<T> clazz) {
        InputStream inputStream = clazz.getResourceAsStream(path);
        Properties props = new Properties();
        try {
            props.load(inputStream);
        } catch (IOException e) {
            LOG.error("read properties file [" + path + "] failed.", e);
        }
        return props;
    }
}
