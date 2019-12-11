package com.tuniu.operation.framework.base.json.resolver;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fangzhongwei
 * 
 */
public abstract class AbstractJsonPath implements JsonPath {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractJsonPath.class);

    protected void checkPath(String path) {
        if (!StringUtils.startsWith(path, "/")) {
            LOG.error("json path must start with char '/'.");
            throw JsonPathException.withError("json path must start with char '/'.");
        }
    }

}
