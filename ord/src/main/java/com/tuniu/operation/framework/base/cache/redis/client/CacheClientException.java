package com.tuniu.operation.framework.base.cache.redis.client;

import redis.clients.jedis.exceptions.JedisException;

public class CacheClientException extends JedisException {

    private static final long serialVersionUID = -4278326594468755981L;

    public CacheClientException(String message) {
        super(message);
    }

}
