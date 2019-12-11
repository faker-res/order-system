package com.tuniu.operation.framework.base.cache.redis.jmx;

import java.util.List;

import redis.clients.jedis.JedisPoolConfig;

import com.tuniu.operation.framework.base.cache.redis.pool.HostPair;
import com.tuniu.operation.framework.base.cache.redis.template.CacheCallback;
import com.tuniu.operation.framework.base.cache.redis.template.CacheProxy;

public class ProxyTemplateAggressive extends ProxyTemplate {

    public ProxyTemplateAggressive(JedisPoolConfig config, List<HostPair> hostPairs, boolean transactional) {
        super(config, hostPairs, transactional);
    }

    public <T> T execute(CacheCallback<T> callback) {
        T res = null;
        CacheProxy proxy = getProxy();
        try {
            if (null != proxy) {
                res = proxy.execute(callback);
            }
        } finally {
            if (null != proxy && !proxy.isEnabled()) {
                disableProxy(proxy);
            }
        }
        return res;
    }
}
