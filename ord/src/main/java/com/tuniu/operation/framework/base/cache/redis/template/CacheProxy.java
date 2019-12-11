package com.tuniu.operation.framework.base.cache.redis.template;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.tuniu.operation.framework.base.cache.redis.client.CacheClientException;

public class CacheProxy {
    private boolean enabled;

    private JedisPool pool;

    private JedisPoolConfig poolConfig;

    private String host;
    private String hostAddress;
    private final int timeOut;

    private int port = 6379;

    private String lastError;

    private final boolean transactional;

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheProxy.class);

    public CacheProxy(JedisPoolConfig poolConfig, String host, int port, int timeOut, boolean transactional) {
        this.transactional = transactional;
        this.poolConfig = poolConfig;
        this.host = host;
        this.port = port;
        this.timeOut = timeOut;
        enabled = false;
        pool = null;
    }

    public <T> T execute(CacheCallback<T> callback) {
        T ret = null;
        Jedis jedis = null;
        long ts = System.currentTimeMillis();
        // time stamp when retrieval ends
        long retrivalPeriod = 0l;
        // time stamp when execution ends
        long executionPeriod = 0l;

        try {
            jedis = pool.getResource();
            retrivalPeriod = System.currentTimeMillis() - ts;
            ret = callback.doInAction(jedis);
            pool.returnResource(jedis);
            executionPeriod = System.currentTimeMillis() - retrivalPeriod - ts;
        } catch (Exception e) {
            if (null != pool) {
                pool.returnBrokenResource(jedis);
            }
            enabled = false;
            pool = null;
            lastError = e.getMessage();

            String msg = MessageFormat.format("execution failed :{1}:{2}, {3},{4}", host, port, callback.getIdentity(),
                    lastError);
            LOGGER.warn(msg);
            if (transactional) {
                throw new CacheClientException(msg);
            }
        }
        if (retrivalPeriod > timeOut) {
            LOGGER.warn("retrival time {}ms exceed threshold {}ms :{}:{}, {}", retrivalPeriod, timeOut, host, port,
                    callback.getIdentity());
        }
        if (executionPeriod > timeOut) {
            LOGGER.warn("execution time {}ms exceed threshold {}ms :{}:{}, {}", executionPeriod, timeOut, host, port,
                    callback.getIdentity());
        }
        return ret;
    }

    public boolean test() {
        if (enabled && null != pool) {
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                if (!jedis.getClient().isConnected()) {
                    jedis.getClient().connect();
                }
                boolean res = jedis.getClient().getSocket() != null;
                pool.returnResource(jedis);
                return res;
            } catch (Exception e) {
                pool.returnBrokenResource(jedis);
                enabled = false;
                pool = null;
                return false;
            }
        }
        enabled = false;
        pool = null;
        return false;
    }

    public boolean activate() {
        try {
            hostAddress = InetAddress.getByName(host).getHostAddress();
            pool = new JedisPool(poolConfig, hostAddress, port);
            enabled = true;
            return test();
        } catch (UnknownHostException e) {
            enabled = false;
            pool = null;
            lastError = e.getMessage();
            LOGGER.warn("activation failed :{}:{}, {}", host, port, lastError);
            return false;
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public JedisPool getPool() {
        return pool;
    }

    public void setPool(JedisPool pool) {
        this.pool = pool;
    }

    public JedisPoolConfig getPoolConfig() {
        return poolConfig;
    }

    public void setPoolConfig(JedisPoolConfig poolConfig) {
        this.poolConfig = poolConfig;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getLastError() {
        return lastError;
    }

}
