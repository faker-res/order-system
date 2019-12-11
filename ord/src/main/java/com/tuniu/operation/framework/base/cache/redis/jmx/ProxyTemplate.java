package com.tuniu.operation.framework.base.cache.redis.jmx;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPoolConfig;

import com.tuniu.operation.framework.base.cache.redis.pool.CacheChangedEvent;
import com.tuniu.operation.framework.base.cache.redis.pool.CacheChangedEventListener;
import com.tuniu.operation.framework.base.cache.redis.pool.HostPair;
import com.tuniu.operation.framework.base.cache.redis.template.CacheCallback;
import com.tuniu.operation.framework.base.cache.redis.template.CacheProxy;

public abstract class ProxyTemplate {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProxyTemplate.class);

    private final List<CacheProxy> proxies;
    protected List<CacheProxy> activeProxies;

    private final Collection<CacheChangedEventListener> listeners;

    protected ReadWriteLock lock;

    private ScheduledExecutorService executorService;

    private final AtomicInteger index;

    private String malProxyList;

    private String activeProxyList;

    private final JedisPoolConfig config;

    protected boolean transactional;

    public ProxyTemplate(JedisPoolConfig config, List<HostPair> hostPairs, boolean transactional) {

        this.transactional = transactional;

        lock = new ReentrantReadWriteLock();
        this.config = config;
        index = new AtomicInteger();
        proxies = new ArrayList<CacheProxy>(hostPairs.size());
        for (HostPair hp : hostPairs) {
            proxies.add(new CacheProxy(config, hp.getHost(), hp.getPort(), hp.getTimeout(), transactional));
        }
        activeProxies = new ArrayList<CacheProxy>(hostPairs.size());
        resume();
        report();
        listeners = new ConcurrentLinkedQueue<CacheChangedEventListener>();
        initialize();
    }

    public void addProxy(HostPair hp) {
        if (null != hp && StringUtils.isEmpty(hp.getHost()) && hp.getPort() > 0) {
            lock.writeLock().lock();
            try {
                proxies.add(new CacheProxy(config, hp.getHost(), hp.getPort(), hp.getTimeout(), transactional));
                report();
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    public void removeProxy(HostPair hp) {
        if (null != hp && StringUtils.isEmpty(hp.getHost()) && hp.getPort() > 0) {
            lock.writeLock().lock();
            try {
                Iterator<CacheProxy> iterator = proxies.iterator();

                while (iterator.hasNext()) {
                    CacheProxy proxy = iterator.next();
                    if (proxy.getHost().equalsIgnoreCase(hp.getHost()) && proxy.getPort() == hp.getPort()) {
                        iterator.remove();
                    }
                }
                report();

            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    public void addCacheChangedEventListener(CacheChangedEventListener listener) {
        listeners.add(listener);
    }

    public boolean removeCacheChangedEventListener(CacheChangedEventListener listener) {
        return listeners.add(listener);
    }

    private void initialize() {

        executorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName(ProxyTemplate.class.getName() + "-Resumer");
                thread.setDaemon(true);
                return thread;
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                if (executorService != null) {
                    try {
                        executorService.shutdown();
                        executorService.awaitTermination(5, TimeUnit.MINUTES);
                    } catch (InterruptedException e) {
                        LOGGER.error("CacheClient shutdown abnormal: {}", e.getMessage());
                    }
                }
            }
        });

        // 连接清理
        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                resume();
            }
        }, 0, 60, TimeUnit.SECONDS);
    }

    protected void report() {
        if (null != proxies && proxies.size() > 0) {
            StringBuffer sb = new StringBuffer();
            StringBuffer sc = new StringBuffer();
            for (CacheProxy cp : proxies) {
                if (!activeProxies.contains(cp)) {
                    sb.append(cp.getHost()).append(':').append(cp.getPort()).append(';');
                } else {
                    sc.append(cp.getHost()).append(':').append(cp.getPort()).append(';');
                }
            }
            activeProxyList = sc.toString();
            malProxyList = sb.toString();

            LOGGER.info("active proxies: {}, malfunctioned proxies: {}", activeProxyList, malProxyList);

        } else {
            malProxyList = null;

            LOGGER.info("nor active proxies: {}", activeProxyList);
        }
    }

    protected CacheProxy getProxy() {
        CacheProxy proxy = null;
        lock.readLock().lock();
        try {
            if (activeProxies.size() > 0) {
                int pNumber = index.getAndIncrement() % activeProxies.size();
                proxy = activeProxies.get(pNumber);
            }
        } finally {
            lock.readLock().unlock();
        }
        return proxy;
    }

    protected void disableProxy(CacheProxy proxy) {
        lock.writeLock().lock();
        try {
            activeProxies.remove(proxy);
            informListeners(proxy, false);
            report();
        } finally {
            lock.writeLock().unlock();
        }
    }

    protected void informListeners(CacheProxy proxy, boolean active) {
        CacheChangedEvent event = new CacheChangedEvent(activeProxies.size(), proxy.getHost(), proxy.getPort(),
                proxy.getHostAddress(), active);
        if (null != listeners && listeners.size() > 0) {
            for (CacheChangedEventListener listener : listeners) {
                listener.listen(event);
            }
        } else {
            LOGGER.info("CacheChangedEvent: {}", event);
        }
    }

    private void resume() {
        lock.writeLock().lock();
        try {
            boolean resumed = false;
            for (CacheProxy cp : proxies) {
                if (cp.activate()) {
                    if (!activeProxies.contains(cp)) {
                        activeProxies.add(cp);
                        informListeners(cp, true);
                        resumed = true;
                    }
                }
            }
            if (resumed) {
                report();
            }
        } finally {
            lock.writeLock().unlock();
        }
    };

    public abstract <T> T execute(CacheCallback<T> callback);

    public String getMalProxyList() {
        return malProxyList;
    }

    public void setMalProxyList(String malProxyList) {
        this.malProxyList = malProxyList;
    }
}
