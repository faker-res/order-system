package com.tuniu.ord.core.init;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tuniu.ord.common.util.PropertiesLoaderUtil;

/**
 * @author fangzhongwei
 * 
 */
public class PropertiesUtil {

    private static final Logger LOG = LoggerFactory.getLogger(PropertiesUtil.class);

    public static <T> void load(String path, String innerPath, Class<T> clazz) {
        if (StringUtils.isEmpty(path) || StringUtils.isEmpty(innerPath) || null == clazz) {
            throw new IllegalArgumentException("Path or innerPath or clazz cannot be empty.");
        }

        Properties props = PropertiesLoaderUtil.load(path, clazz);
        Properties innerProps = PropertiesLoaderUtil.load(innerPath, clazz);
        try {
            Field[] fields = clazz.getDeclaredFields();
            for (Object key : props.keySet()) {
                for (Field field : fields) {
                    if (field.getName().equals(key)) {
                        Method method = getSetMethod(field, clazz);
                        String value = props.getProperty((String) key);
                        if (value.contains("[") && value.contains("]")) {
                            int beginIndex = value.indexOf("[");
                            int endIndex = value.indexOf("]");
                            String innerKey = value.substring(beginIndex + 1, endIndex);
                            String innerValue = innerProps.getProperty(innerKey);
                            value = value.replace("[" + innerKey + "]", innerValue);
                        }
                        setPropVlaue(method, clazz, value, field.getType());
                        break;
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("Resolve init properties file failed.", e);
        }
    }

    private static <T> Method getSetMethod(Field field, Class<T> clazz) throws NoSuchMethodException, SecurityException {
        String setMethod = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
        Method method = clazz.getDeclaredMethod(setMethod, field.getType());
        return method;
    }

    private static <T> void setPropVlaue(Method method, Class<T> clazz, String value, Class<?> type)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
        String name = type.getName();
        if (name.equalsIgnoreCase("java.lang.String")) {
            method.invoke(clazz.newInstance(), value);
        } else if (name.equalsIgnoreCase("boolean") || name.equalsIgnoreCase("java.lang.Boolean")) {
            method.invoke(clazz.newInstance(), Boolean.valueOf(value));
        } else if (name.equalsIgnoreCase("java.math.BigDecimal")) {
            method.invoke(clazz.newInstance(), BigDecimal.valueOf(Double.valueOf(value).doubleValue()));
        } else if (name.equalsIgnoreCase("double") || name.equalsIgnoreCase("java.lang.Double")) {
            method.invoke(clazz.newInstance(), Double.valueOf(value));
        } else if (name.equalsIgnoreCase("int") || name.equalsIgnoreCase("java.lang.Integer")) {
            method.invoke(clazz.newInstance(), Integer.valueOf(value));
        }
    }
}
