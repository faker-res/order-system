package com.tuniu.ord.core.mail.template.context;

import java.util.Map;

/**
 * @author fangzhongwei
 * 
 */
public class Context implements IContext {

    private Map<String, Object> contextMap;

    @Override
    public Object getValue(String key) {
        return contextMap.get(key);
    }

    /**
     * @return the contextMap
     */
    public Map<String, Object> getContextMap() {
        return contextMap;
    }

    /**
     * @param contextMap
     *            the contextMap to set
     */
    public void setContextMap(Map<String, Object> contextMap) {
        this.contextMap = contextMap;
    }

}
