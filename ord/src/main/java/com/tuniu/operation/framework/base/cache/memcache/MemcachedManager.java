package com.tuniu.operation.framework.base.cache.memcache;

import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketAddress;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionObserver;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.transcoders.Transcoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

@SuppressWarnings("rawtypes")
public class MemcachedManager implements DisposableBean {

    private static final Logger LOG = LoggerFactory.getLogger(MemcachedManager.class);

    private String servers;
    private int timeOut = 5;
    private TimeUnit timeUnit = TimeUnit.SECONDS;
    private int expiration = 1;
    private boolean asyncget = false;
    private boolean compression = false;
    private Transcoder transcoder;
    private MemcachedClient memClient;

    public MemcachedClient getMemClient() {
        return memClient;
    }

    public void setMemClient(MemcachedClient memClient) {
        this.memClient = memClient;
    }

    public Transcoder getTranscoder() {
        return transcoder;
    }

    public void setTranscoder(Transcoder transcoder) {
        this.transcoder = transcoder;
    }

    public int getExpiration() {
        return expiration;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }

    public boolean isAsyncget() {
        return asyncget;
    }

    public void setAsyncget(boolean asyncget) {
        this.asyncget = asyncget;
    }

    public boolean isCompression() {
        return compression;
    }

    public void setCompression(boolean compression) {
        this.compression = compression;
    }

    public String getServers() {
        return servers;
    }

    public void setServers(String servers) {
        this.servers = servers;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    @Override
    public void destroy() throws Exception {
        memClient.shutdown();
    }

    public void connect() throws Exception {
        if (memClient != null) {
            return;
        }
        memClient = new MemcachedClient(AddrUtil.getAddresses(servers));
    }

    /**
     * 封装成公共类，大家可以共同使用，获取memcache连接，若失败，则重试3次
     * 
     * @return
     */
    public boolean connectToMemcach() {
        boolean isConnect = false;
        int tryTimes = 0;
        while (!isConnect && tryTimes < 3) {
            try {
                this.connect();
                isConnect = true;
                break;
            } catch (Exception e) {
                LOG.error("连接memcach异常，重新尝试连接。", e);
            }
            tryTimes++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
        return isConnect;
    }

    public void disConnect() {
        if (memClient == null) {
            return;
        }
        memClient.shutdown();
    }

    public void addObserver(ConnectionObserver obs) {
        memClient.addObserver(obs);
    }

    public void removeObserver(ConnectionObserver obs) {
        memClient.removeObserver(obs);
    }

    // ---- Basic Operation Start ----//
    public boolean set(String key, Object value, int expire) {
        Future<Boolean> f = memClient.set(key, expire, value);
        return getBooleanValue(f);
    }

    public Object get(String key) {
        return memClient.get(key);
    }

    public Object asyncGet(String key) {
        Object obj = null;
        Future<Object> f = memClient.asyncGet(key);
        try {
            obj = f.get(this.timeOut, this.timeUnit);
        } catch (Exception e) {
            f.cancel(false);
        }
        return obj;
    }

    public boolean add(String key, Object value, int expire) {
        Future<Boolean> f = memClient.add(key, expire, value);
        return getBooleanValue(f);
    }

    public boolean replace(String key, Object value, int expire) {
        Future<Boolean> f = memClient.replace(key, expire, value);
        return getBooleanValue(f);
    }

    public boolean delete(String key) {
        Future<Boolean> f = memClient.delete(key);
        return getBooleanValue(f);
    }

    public boolean flush() {
        Future<Boolean> f = memClient.flush();
        return getBooleanValue(f);
    }

    public Map<String, Object> getMulti(Collection<String> keys) {
        return memClient.getBulk(keys);
    }

    public Map<String, Object> getMulti(String[] keys) {
        return memClient.getBulk(keys);
    }

    public Map<String, Object> asyncGetMulti(Collection<String> keys) {
        Map<String, Object> map = null;
        Future<Map<String, Object>> f = memClient.asyncGetBulk(keys);
        try {
            map = f.get(this.timeOut, this.timeUnit);
        } catch (Exception e) {
            f.cancel(false);
        }
        return map;
    }

    public Map<String, Object> asyncGetMulti(String keys[]) {
        Map<String, Object> map = null;
        Future<Map<String, Object>> f = memClient.asyncGetBulk(keys);
        try {
            map = f.get(this.timeOut, this.timeUnit);
        } catch (Exception e) {
            f.cancel(false);
        }
        return map;
    }

    // ---- Basic Operation End ----//

    // ---- increment & decrement Start ----//
    public long increment(String key, int by, long defaultValue, int expire) {
        return memClient.incr(key, by, defaultValue, expire);
    }

    public long increment(String key, int by) {
        return memClient.incr(key, by);
    }

    public long decrement(String key, int by, long defaultValue, int expire) {
        return memClient.decr(key, by, defaultValue, expire);
    }

    public long decrement(String key, int by) {
        return memClient.decr(key, by);
    }

    public long asyncIncrement(String key, int by) {
        Future<Long> f = memClient.asyncIncr(key, by);
        return getLongValue(f);
    }

    public long asyncDecrement(String key, int by) {
        Future<Long> f = memClient.asyncDecr(key, by);
        return getLongValue(f);
    }

    // ---- increment & decrement End ----//

    public void printStats() throws IOException {
        printStats(null);
    }

    public void printStats(OutputStream stream) throws IOException {
        Map<SocketAddress, Map<String, String>> statMap = memClient.getStats();
        if (stream == null) {
            stream = System.out;
        }
        StringBuffer buf = new StringBuffer();
        Set<SocketAddress> addrSet = statMap.keySet();
        Iterator<SocketAddress> iter = addrSet.iterator();
        while (iter.hasNext()) {
            SocketAddress addr = iter.next();
            buf.append(addr.toString() + "/n");
            Map<String, String> stat = statMap.get(addr);
            Set<String> keys = stat.keySet();
            Iterator<String> keyIter = keys.iterator();
            while (keyIter.hasNext()) {
                String key = keyIter.next();
                String value = stat.get(key);
                buf.append("  key=" + key + ";value=" + value + "/n");
            }
            buf.append("/n");
        }
        stream.write(buf.toString().getBytes("UTF-8"));
        stream.flush();
    }

    private long getLongValue(Future<Long> f) {
        try {
            Long l = f.get(this.timeOut, this.timeUnit);
            return l.longValue();
        } catch (Exception e) {
            f.cancel(false);
        }
        return -1;
    }

    private boolean getBooleanValue(Future<Boolean> f) {
        try {
            Boolean bool = f.get(this.timeOut, this.timeUnit);
            return bool.booleanValue();
        } catch (Exception e) {
            f.cancel(false);
            return false;
        }
    }
}
