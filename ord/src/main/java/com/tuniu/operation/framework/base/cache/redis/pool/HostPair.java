package com.tuniu.operation.framework.base.cache.redis.pool;

public class HostPair {
    private final String host;

    private int port = 6379;

    private int timeout = 2000;

    public HostPair(String host, int port, int timeout) {
        super();
        this.host = host;
        this.port = port;
        this.timeout = timeout;
    }

    public HostPair(String host, int port) {
        super();
        this.host = host;
        this.port = port;
    }

    @Override
    public String toString() {
        return "HostPair [host=" + host + ", port=" + port + ", timeout=" + timeout + "]";
    }

    public HostPair(String host) {
        super();
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public int getTimeout() {
        return timeout;
    }
}
