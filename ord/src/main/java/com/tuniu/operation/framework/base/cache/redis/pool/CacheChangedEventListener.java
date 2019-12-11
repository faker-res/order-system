package com.tuniu.operation.framework.base.cache.redis.pool;

public interface CacheChangedEventListener {

    void listen(CacheChangedEvent event);

}
