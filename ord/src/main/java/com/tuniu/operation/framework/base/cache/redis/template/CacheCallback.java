package com.tuniu.operation.framework.base.cache.redis.template;

import redis.clients.jedis.Jedis;

public interface CacheCallback<T> {
    public T doInAction(Jedis jedis);

    public String getIdentity();
}
