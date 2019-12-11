package com.tuniu.operation.framework.base.cache.redis.pool;

import java.text.MessageFormat;

public class CacheChangedEvent {

    private int activeProxyNumber;
    private String host;
    private int port;
    private String address;
    private boolean active;

    public CacheChangedEvent(int activeProxyNumber, String host, int port, String address, boolean active) {
        super();
        this.activeProxyNumber = activeProxyNumber;
        this.host = host;
        this.port = port;
        this.address = address;
        this.active = active;
    }

    @Override
    public String toString() {
        return MessageFormat.format("activeProxyNumber={0}, {1}:{2}, ({3}) is {4}", activeProxyNumber, host, port, address,
                active ? "activated" : "deactivated");

    }

    public int getActiveProxyNumber() {
        return activeProxyNumber;
    }

    public void setActiveProxyNumber(int activeProxyNumber) {
        this.activeProxyNumber = activeProxyNumber;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
