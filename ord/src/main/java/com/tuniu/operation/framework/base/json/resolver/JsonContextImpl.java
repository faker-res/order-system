package com.tuniu.operation.framework.base.json.resolver;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.NullNode;

import com.tuniu.operation.framework.base.json.JsonUtil;

/**
 * @author fangzhongwei
 * 
 */
public class JsonContextImpl implements JsonContext {

    private JsonNode jsonNode;

    public JsonContextImpl(String json) {
        if (StringUtils.isBlank(json)) {
            this.jsonNode = NullNode.getInstance();
        }
        this.jsonNode = JsonUtil.toBean(json, JsonNode.class);
        if (!StringUtils.isBlank(json) && null == jsonNode) {
            throw JsonPathException.withError("Invalid json String: " + json);
        }
    }

    @Override
    public JsonNode getContext() {
        return this.jsonNode;
    }

    public void setJsonNode(JsonNode jsonNode) {
        if (null == jsonNode) {
            this.jsonNode = NullNode.getInstance();
        } else {
            this.jsonNode = jsonNode;
        }
    }

}
