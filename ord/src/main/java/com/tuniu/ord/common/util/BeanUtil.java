package com.tuniu.ord.common.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;

import com.tuniu.operation.framework.base.json.JsonUtil;

public class BeanUtil {
    private static final Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    /**
     * 封装Open Declaration org.springframework.beans.BeanUtils拷贝方法
     * 
     * @param source
     *            源对象
     * @param target
     *            目标对象
     */
    public static void copyBeanProperties(Object source, Object target) {
        try {
            copyProperties(source, target, null, null);
        } catch (BeansException e) {

        }
    }

    public static void copyBean(Object aft, Object bef) {
        try {
            Class<?> befClass = bef.getClass();
            Class<?> aftClass = aft.getClass();
            Field[] befFields = befClass.getDeclaredFields();
            Field[] aftFields = aftClass.getDeclaredFields();
            for (Field befField : befFields) {
                for (Field aftField : aftFields) {
                    System.out.println("befField:" + befField.getName());
                    System.out.println("aftField:" + aftField.getName());
                    System.out.println("===============================");
                    if (befField.getName().equals(aftField.getName())) {
                        PropertyDescriptor befpd = new PropertyDescriptor(befField.getName(), befClass);
                        PropertyDescriptor aftpd = new PropertyDescriptor(aftField.getName(), aftClass);
                        Method befMethod = befpd.getWriteMethod();
                        Method aftMethod = aftpd.getReadMethod();
                        Object o = aftMethod.invoke(aft);
                        System.out.println(o);
                        if (null != o && !o.toString().equals("0")) {
                            befMethod.invoke(bef, o);
                        }
                        break;
                    } else {
                        continue;
                    }
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * Copy the property values of the given source bean into the given target bean.
     * <p>
     * Note: The source and target classes do not have to match or even be derived from each other, as long as the properties
     * match. Any bean properties that the source bean exposes but the target bean does not will silently be ignored.
     * 
     * @param source
     *            the source bean
     * @param target
     *            the target bean
     * @param editable
     *            the class (or interface) to restrict property setting to
     * @param ignoreProperties
     *            array of property names to ignore
     * @throws BeansException
     *             if the copying failed
     * @see BeanWrapper
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static void copyProperties(Object source, Object target, Class<?> editable, String[] ignoreProperties)
            throws BeansException {

        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        if (target instanceof Map) {
            PropertyDescriptor[] sourcePropertyDescArr = BeanUtils.getPropertyDescriptors(source.getClass());
            for (PropertyDescriptor sourcePd : sourcePropertyDescArr) {
                try {
                    if (sourcePd != null && sourcePd.getReadMethod() != null) {
                        Method readMethod = sourcePd.getReadMethod();
                        if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                            readMethod.setAccessible(true);
                        }
                        ((Map) target).put(sourcePd.getName(), readMethod.invoke(source));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {

            Class<?> actualEditable = target.getClass();
            if (editable != null) {
                if (!editable.isInstance(target)) {
                    throw new IllegalArgumentException("Target class [" + target.getClass().getName()
                            + "] not assignable to Editable class [" + editable.getName() + "]");
                }
                actualEditable = editable;
            }

            PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
            List<String> ignoreList = (ignoreProperties != null) ? Arrays.asList(ignoreProperties) : null;

            for (PropertyDescriptor targetPd : targetPds) {
                if (targetPd.getWriteMethod() != null
                        && (ignoreProperties == null || (null != ignoreList && ignoreList.contains(targetPd.getName())))) {
                    PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
                    if (sourcePd != null && sourcePd.getReadMethod() != null) {
                        try {
                            Method readMethod = sourcePd.getReadMethod();
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }
                            Object value = readMethod.invoke(source);
                            Method writeMethod = targetPd.getWriteMethod();
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            writeMethod.invoke(target, value);
                        } catch (Throwable ex) {
                            if (logger.isDebugEnabled()) {
                                logger.debug(
                                        "copyProperties(Object, Object, Class<?>, String[]) - ex={}" + sourcePd.getReadMethod(),
                                        ex);
                            }
                            throw new FatalBeanException("Could not copy properties : " + sourcePd.getName(), ex);
                        }
                    }
                }
            }
        }
    }

    /**
     * 清空map中value值为null的键值对
     * 
     * @param key
     * @param orgMap
     * @param newMap
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Map<String, Object> clearNullMap(String key, Map<String, Object> orgMap, Map<String, Object> newMap) {

        // 遍历传来的map中的key
        for (String str : orgMap.keySet()) {

            // 如果value！=null 则进入下一步操作
            if (null != orgMap.get(str)) {

                // 如果value属于map 将新map迭代进改方法继续同样的操作并返回一个新的map，如果不为空或者长度大于0 则在新map中添加
                if (orgMap.get(str) instanceof Map) {
                    HashMap<String, Object> hashMap = new HashMap<String, Object>();
                    Map<String, Object> map = clearNullMap(str, (Map) orgMap.get(str), hashMap);
                    if (!(null == map || map.size() < 1)) {
                        newMap.put(str, map);
                    }
                }

                // 如果value属于list操作
                else if (orgMap.get(str) instanceof List) {
                    List<Map> list = new ArrayList<Map>();
                    for (int i = 0; i < ((List) orgMap.get(str)).size(); i++) {
                        if (((List) orgMap.get(str)).get(i) instanceof Map) {
                            HashMap<String, Object> hashMap = new HashMap<String, Object>();
                            list.add(clearNullMap(str, (Map) ((List) orgMap.get(str)).get(i), hashMap));
                        }
                    }
                    newMap.put(str, list);
                }

                // 如果value属于String操作
                else if (orgMap.get(str) instanceof String) {
                    if (!orgMap.get(str).equals("")) {
                        newMap.put(str, orgMap.get(str));
                    }
                } else {
                    newMap.put(str, orgMap.get(str));
                    continue;
                }
            }

        }
        return newMap;
    }

    /**
     * 通过map获取签名
     * 
     * @param map
     * @return
     */
    public static String getSignByMap(Map<String, Object> map) {
        StringBuffer sb = new StringBuffer();
        for (String str : map.keySet()) {
            if (map.get(str) instanceof List || map.get(str) instanceof Map) {
                try {
                    sb.append(str + JsonUtil.toJsonAsString(map.get(str)));
                } catch (JsonGenerationException e) {
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                sb.append(str + map.get(str));
            }
        }

        return sb.toString();
    }

    public static String Md5Encode(String str) throws NoSuchAlgorithmException {
        StringBuilder sign = new StringBuilder();

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bytes = md.digest(str.getBytes());

        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    public static String getSignature(JSONObject data, String secretKey) throws Exception {
        // 第一步：获取所有值非空的参数，忽略签名
        List<String> keyList = new ArrayList<String>();
        for (Object key : data.keySet()) {
            // key为null或者key是签名
            if (key == null || "sign".equalsIgnoreCase(key.toString())) {
                continue;
            }
            // value为null或者为空
            if (data.get(key) == null || StringUtils.isBlank(data.get(key).toString())) {
                continue;
            }
            keyList.add(key.toString());
        }

        // 第二步：按名称排序并拼接成字符串
        String[] arrayToSort = keyList.toArray(new String[keyList.size()]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);

        StringBuilder sb = new StringBuilder(secretKey);
        for (String key : arrayToSort) {
            sb.append(key);
            sb.append(data.get(key));
        }
        sb.append(secretKey);

        // LOGGER.info("获取签名方法，生成签名第二步结果为" + sb.toString());

        // 第三步：MD5加密并转换成大写的16进制
        MessageDigest md = MessageDigest.getInstance("MD5");
        return byte2hex(md.digest(sb.toString().getBytes("utf-8")));
    }

    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

}
