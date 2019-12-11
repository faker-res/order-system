package com.tuniu.operation.framework.base.json.resolver;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;

/**
 * @author fangzhongwei
 * 
 */
public interface JsonPath {

    boolean getBooleanValue(JsonContext jsonContext, String path);

    int getIntValue(JsonContext jsonContext, String path);

    long getLongValue(JsonContext jsonContext, String path);

    double getDoubleValue(JsonContext jsonContext, String path);

    BigDecimal getBigDecimalValue(JsonContext jsonContext, String path);

    String getStringValue(JsonContext jsonContext, String path);

    @SuppressWarnings("rawtypes")
    Map toMap(JsonContext jsonContext, String path);

    List<Object> toList(JsonContext jsonContext, String path);

    <T> List<T> toList(JsonContext jsonContext, String path, Class<T> clazz);

    <T> T toBean(JsonContext jsonContext, String path, Class<T> clazz);

    JsonNode toNativeJson(JsonContext jsonContext, String path);
}
