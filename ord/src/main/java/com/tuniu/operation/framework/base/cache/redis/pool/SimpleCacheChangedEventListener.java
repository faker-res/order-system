package com.tuniu.operation.framework.base.cache.redis.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleCacheChangedEventListener implements CacheChangedEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleCacheChangedEventListener.class);

    @Override
    public void listen(CacheChangedEvent event) {
        LOGGER.warn("CacheChangedEvent occured: {}", event);
    }

}
