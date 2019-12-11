package com.tuniu.operation.framework.base.json.resolver;

import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;

/**
 * @author fangzhongwei
 * 
 */
public class JsonPathIterator implements Iterator<String> {

    private String[] jsonPaths;
    private int count = 0;

    public JsonPathIterator(String path) {
        if (path.endsWith("/")) {
            this.jsonPaths = StringUtils.split(path.substring(1, path.length() - 1), "/");
        } else {
            this.jsonPaths = StringUtils.split(path.substring(1), "/");
        }
    }

    @Override
    public boolean hasNext() {
        return count < jsonPaths.length;
    }

    @Override
    public String next() {
        return jsonPaths[count++];
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
