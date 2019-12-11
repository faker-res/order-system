package com.tuniu.operation.framework.base.json;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fangzhongwei
 * 
 */
public class JsonUtil extends JsonFormatter {

    private static final Logger LOG = LoggerFactory.getLogger(JsonUtil.class);

    private JsonUtil() {
    }

    public static boolean getBooleanValue(Map<String, Object> srcMap, String key) {
        try {
            return Boolean.parseBoolean(srcMap.get(key).toString());
        } catch (Exception e) {
            LOG.error("Can not get boolean value from Map: " + srcMap + ", key: " + key);
            return false;
        }
    }

    public static int getIntValue(Map<String, Object> srcMap, String key) {
        try {
            return Integer.parseInt(srcMap.get(key).toString());
        } catch (Exception e) {
            LOG.error("Can not get int value from Map: " + srcMap + ", key: " + key);
            return 0;
        }
    }

    public static long getLongValue(Map<String, Object> srcMap, String key) {
        try {
            return Long.parseLong(srcMap.get(key).toString());
        } catch (Exception e) {
            LOG.error("Can not get long value from Map: " + srcMap + ", key: " + key);
            return 0;
        }
    }

    public static double getDoubleValue(Map<String, Object> srcMap, String key) {
        try {
            return Double.parseDouble(srcMap.get(key).toString());
        } catch (Exception e) {
            LOG.error("Can not get double value from Map: " + srcMap + ", key: " + key);
            return 0.0;
        }
    }

    public static String getStringValue(Map<String, Object> srcMap, String key) {
        try {
            return srcMap.get(key).toString();
        } catch (Exception e) {
            LOG.error("Can not get String value from Map: " + srcMap + ", key: " + key);
            return null;
        }
    }

    public static <T> T toBean(String src, Class<T> clazz) {
        if (null == src) {
            return null;
        }
        try {
            return toObject(src, clazz);
        } catch (IOException e) {
            LOG.error("Can not convert String: " + src + ", to class: " + clazz, e);
            return null;
        }
    }

    public static <T> T toBean(String src, Class<?> collectionClass, Class<?>... elementClasses) {
        if (null == src) {
            return null;
        }
        try {
            return toObject(src, collectionClass, elementClasses);
        } catch (IOException e) {
            LOG.error("Can not convert String: " + src + ", to class: " + collectionClass, e);
            return null;
        }
    }

    public static String toString(Object src) {
        if (null == src) {
            return null;
        }
        if (src instanceof String) {
            return (String) src;
        }
        try {
            return toJsonAsString(src);
        } catch (IOException e) {
            LOG.error("Can not convert Object: " + src + ", to String.", e);
            return null;
        }
    }

    @SuppressWarnings("rawtypes")
    public static <T> List<T> toList(String src, Class<T> clazz) {
        if (null == src) {
            return null;
        }

        List tmpList = toBean(src, List.class);

        if (null != tmpList && tmpList.size() > 0) {
            List<T> resultList = new LinkedList<T>();
            for (Object obj : tmpList) {
                T t = JsonUtil.toBean(JsonUtil.toString(obj), clazz);
                if (null != t) {
                    resultList.add(t);
                }
            }
            return resultList;
        }
        return null;
    }

}
