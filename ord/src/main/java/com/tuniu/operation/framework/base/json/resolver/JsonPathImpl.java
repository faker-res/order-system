package com.tuniu.operation.framework.base.json.resolver;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tuniu.operation.framework.base.json.JsonUtil;

/**
 * @author fangzhongwei
 * 
 */
public class JsonPathImpl extends AbstractJsonPath {

    private static final Logger LOG = LoggerFactory.getLogger(JsonPathImpl.class);

    @Override
    public boolean getBooleanValue(JsonContext jsonContext, String path) {
        checkPath(path);
        JsonNode jsonNode = findJsonNode(jsonContext.getContext(), path);

        if (null == jsonNode) {
            return false;
        }

        if (jsonNode.isBoolean()) {
            return jsonNode.getBooleanValue();
        }

        return false;
    }

    @Override
    public int getIntValue(JsonContext jsonContext, String path) {
        checkPath(path);
        JsonNode jsonNode = findJsonNode(jsonContext.getContext(), path);

        if (null == jsonNode) {
            return 0;
        }

        if (jsonNode.isInt() || jsonNode.isIntegralNumber()) {
            return jsonNode.getIntValue();
        }
        if (jsonNode.isBigInteger()) {
            return jsonNode.getBigIntegerValue().intValue();
        }
        if (jsonNode.isNumber()) {
            return jsonNode.getNumberValue().intValue();
        }
        try {
            return Integer.valueOf(jsonNode.getTextValue()).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public long getLongValue(JsonContext jsonContext, String path) {
        checkPath(path);
        JsonNode jsonNode = findJsonNode(jsonContext.getContext(), path);

        if (null == jsonNode) {
            return 0;
        }

        if (jsonNode.isLong()) {
            return jsonNode.getLongValue();
        }
        if (jsonNode.isBigInteger()) {
            return jsonNode.getBigIntegerValue().longValue();
        }
        if (jsonNode.isNumber()) {
            return jsonNode.getNumberValue().longValue();
        }
        try {
            return Long.valueOf(jsonNode.getTextValue()).longValue();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public double getDoubleValue(JsonContext jsonContext, String path) {
        checkPath(path);
        JsonNode jsonNode = findJsonNode(jsonContext.getContext(), path);

        if (null == jsonNode) {
            return 0;
        }
        if (jsonNode.isDouble()) {
            return jsonNode.getDoubleValue();
        }
        if (jsonNode.isNumber()) {
            return jsonNode.getNumberValue().doubleValue();
        }
        try {
            return Double.valueOf(jsonNode.getTextValue()).doubleValue();
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public BigDecimal getBigDecimalValue(JsonContext jsonContext, String path) {
        checkPath(path);
        JsonNode jsonNode = findJsonNode(jsonContext.getContext(), path);

        if (null == jsonNode) {
            return BigDecimal.ZERO;
        }
        if (jsonNode.isBigDecimal()) {
            return jsonNode.getDecimalValue();
        }
        if (jsonNode.isDouble()) {
            return BigDecimal.valueOf(jsonNode.getDoubleValue());
        }

        return BigDecimal.ZERO;
    }

    @Override
    public String getStringValue(JsonContext jsonContext, String path) {
        checkPath(path);
        JsonNode jsonNode = findJsonNode(jsonContext.getContext(), path);

        if (null == jsonNode) {
            return null;
        }
        return jsonNode.getTextValue();
    }

    @Override
    public <T> T toBean(JsonContext jsonContext, String path, Class<T> clazz) {
        checkPath(path);
        JsonNode jsonNode = findJsonNode(jsonContext.getContext(), path);
        if (null == jsonNode) {
            return null;
        }
        return JsonUtil.toBean(jsonNode.toString(), clazz);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Map toMap(JsonContext jsonContext, String path) {

        return toBean(jsonContext, path, Map.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object> toList(JsonContext jsonContext, String path) {
        return toBean(jsonContext, path, List.class);
    }

    @Override
    public <T> List<T> toList(JsonContext jsonContext, String path, Class<T> clazz) {
        checkPath(path);
        JsonNode jsonNode = findJsonNode(jsonContext.getContext(), path);
        if (null == jsonNode) {
            return null;
        }
        return JsonUtil.toList(jsonNode.toString(), clazz);
    }

    @Override
    public JsonNode toNativeJson(JsonContext jsonContext, String path) {
        checkPath(path);
        JsonNode jsonNode = findJsonNode(jsonContext.getContext(), path);

        return jsonNode;
    }

    private JsonNode findJsonNode(JsonNode jsonNode, String jsonPath) {
        JsonNode targetNode = jsonNode;
        // 非当前节点
        if (!"/".equals(jsonPath)) {
            Iterator<String> iterator = new JsonPathIterator(jsonPath);

            while (iterator.hasNext()) {
                String subPath = iterator.next();
                // 支持数组索引匹配
                if (subPath.endsWith("]")) {
                    int index = subPath.indexOf("[");
                    if (-1 == index) {
                        LOG.error("Invalid sub json path: " + subPath);
                        throw JsonPathException.withError("Invalid sub json path: " + subPath);
                    }
                    String prefixSubPathName = subPath.substring(0, index);
                    if (!"".equals(prefixSubPathName)) {
                        targetNode = targetNode.get(prefixSubPathName);
                        if (null == targetNode || targetNode.isNull()) {
                            return null;
                        }
                        if (!targetNode.isArray()) {
                            LOG.error("JsonNode is not a array: " + targetNode.toString());
                            throw JsonPathException.withError("JsonNode is not a array: " + targetNode.toString());
                        }
                    }

                    String arrIndexStr = null;
                    try {
                        arrIndexStr = subPath.substring(index + 1, subPath.length() - 1);
                        int arrIndex = Integer.valueOf(arrIndexStr).intValue();
                        if (arrIndex >= targetNode.size()) {
                            LOG.error("Array index out of bound.index: " + arrIndex + ", actual: " + targetNode.size());
                            throw JsonPathException.withError("Array index out of bound.index: " + arrIndex + ", actual: "
                                    + targetNode.size());
                        }
                        targetNode = targetNode.get(arrIndex);
                    } catch (NumberFormatException e) {
                        LOG.error("Invalid array index of sub json path: " + arrIndexStr);
                        throw JsonPathException.withError("Invalid array index of sub json path: " + arrIndexStr);
                    }
                } else {
                    targetNode = targetNode.get(subPath);
                }
                if (null == targetNode || targetNode.isNull()) {
                    return null;
                }
            }
        }

        return targetNode;
    }

}
