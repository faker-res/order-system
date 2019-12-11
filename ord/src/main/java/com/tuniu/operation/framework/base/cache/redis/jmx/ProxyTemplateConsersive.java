package com.tuniu.operation.framework.base.cache.redis.jmx;

import java.util.List;

import redis.clients.jedis.JedisPoolConfig;

import com.tuniu.operation.framework.base.cache.redis.pool.HostPair;
import com.tuniu.operation.framework.base.cache.redis.template.CacheCallback;
import com.tuniu.operation.framework.base.cache.redis.template.CacheProxy;

public class ProxyTemplateConsersive extends ProxyTemplate {

    public ProxyTemplateConsersive(JedisPoolConfig config, List<HostPair> hostPairs, boolean transactional) {
        super(config, hostPairs, transactional);
    }

    @Override
    public <T> T execute(CacheCallback<T> callback) {
        T res = null;
        CacheProxy proxy = getProxy();
        while (null != proxy && !proxy.test()) {
            disableProxy(proxy);
            proxy = getProxy();
        }
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
