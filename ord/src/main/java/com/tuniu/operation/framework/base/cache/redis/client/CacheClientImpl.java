package com.tuniu.operation.framework.base.cache.redis.client;

import static com.tuniu.operation.framework.base.cache.redis.util.CacheClientUtils.bytesToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;

import com.tuniu.operation.framework.base.cache.redis.jmx.ProxyTemplate;
import com.tuniu.operation.framework.base.cache.redis.jmx.ProxyTemplateAggressive;
import com.tuniu.operation.framework.base.cache.redis.jmx.ProxyTemplateConsersive;
import com.tuniu.operation.framework.base.cache.redis.pool.CacheChangedEventListener;
import com.tuniu.operation.framework.base.cache.redis.pool.HostPair;
import com.tuniu.operation.framework.base.cache.redis.template.CacheCallback;
import com.tuniu.operation.framework.base.cache.redis.util.CacheClientUtils;
import com.tuniu.operation.framework.base.cache.redis.util.Commands;

public class CacheClientImpl implements CacheClient {

    private final ProxyTemplate template;

    public CacheClientImpl(JedisPoolConfig poolConfig, List<HostPair> hostPairs) {
        this(poolConfig, hostPairs, true, false);
    }

    public CacheClientImpl(JedisPoolConfig poolConfig, List<HostPair> hostPairs,
            List<? extends CacheChangedEventListener> listeners) {
        this(poolConfig, hostPairs, true, listeners);
    }

    public CacheClientImpl(JedisPoolConfig poolConfig, List<HostPair> hostPairs, boolean aggressive, boolean transactional) {
        template = (aggressive) ? new ProxyTemplateAggressive(poolConfig, hostPairs, transactional) : new ProxyTemplateConsersive(
                poolConfig, hostPairs, transactional);
    }

    public CacheClientImpl(JedisPoolConfig poolConfig, List<HostPair> hostPairs, boolean aggressive,
            List<? extends CacheChangedEventListener> listeners) {
        this(poolConfig, hostPairs, aggressive, false);
        if (null != listeners && listeners.size() > 0) {
            for (CacheChangedEventListener ccel : listeners) {
                template.addCacheChangedEventListener(ccel);
            }
        }
    }

    @Override
    public void addProxy(HostPair hp) {
        template.addProxy(hp);
    }

    @Override
    public void removeProxy(HostPair hp) {
        template.removeProxy(hp);
    }

    protected ProxyTemplate getProxyManager() {
        return template;
    }

    @Override
    public void addCacheChangedEventListener(CacheChangedEventListener listener) {
        template.addCacheChangedEventListener(listener);
    }

    @Override
    public boolean removeCacheChangedEventListener(CacheChangedEventListener listener) {
        return template.removeCacheChangedEventListener(listener);
    }

    @Override
    public String set(final String key, final String value) {
        return template.execute(new CacheCallback<String>() {
            @Override
            public String doInAction(Jedis jedis) {
                return jedis.set(key, value);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.SET, key);
            }
        });
    }

    @Override
    public String get(final String key) {
        return template.execute(new CacheCallback<String>() {
            @Override
            public String doInAction(Jedis jedis) {
                return jedis.get(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.GET, key);
            }
        });
    }

    @Override
    public Boolean exists(final String key) {
        return template.execute(new CacheCallback<Boolean>() {
            @Override
            public Boolean doInAction(Jedis jedis) {
                return jedis.exists(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.EXISTS, key);
            }
        });
    }

    @Override
    public Long expire(final String key, final int seconds) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.expire(key, seconds);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.EXISTS, key);
            }
        });
    }

    @Override
    public Long expireAt(final String key, final long unixTime) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.expireAt(key, unixTime);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.EXPIREAT, key);
            }
        });
    }

    @Override
    public Long ttl(final String key) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.ttl(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.TTL, key);
            }
        });
    }

    @Override
    public Boolean setbit(final String key, final long offset, final boolean value) {
        return template.execute(new CacheCallback<Boolean>() {
            @Override
            public Boolean doInAction(Jedis jedis) {
                return jedis.setbit(key, offset, value);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.SETBIT, key);
            }
        });
    }

    @Override
    public Boolean getbit(final String key, final long offset) {
        return template.execute(new CacheCallback<Boolean>() {
            @Override
            public Boolean doInAction(Jedis jedis) {
                return jedis.getbit(key, offset);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.GETBIT, key);
            }
        });
    }

    @Override
    public Long setrange(final String key, final long offset, final String value) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.setrange(key, offset, value);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.SETRANGE, key);
            }
        });

    }

    @Override
    public String getrange(final String key, final long startOffset, final long endOffset) {
        return template.execute(new CacheCallback<String>() {
            @Override
            public String doInAction(Jedis jedis) {
                return jedis.getrange(key, startOffset, endOffset);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.GETRANGE, key);
            }
        });
    }

    @Override
    public String getSet(final String key, final String value) {
        return template.execute(new CacheCallback<String>() {
            @Override
            public String doInAction(Jedis jedis) {
                return jedis.getSet(key, value);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.GETSET, key);
            }
        });
    }

    @Override
    public Long setnx(final String key, final String value) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.setnx(key, value);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.SETNX, key);
            }
        });
    }

    @Override
    public String setex(final String key, final int seconds, final String value) {
        return template.execute(new CacheCallback<String>() {
            @Override
            public String doInAction(Jedis jedis) {
                return jedis.setex(key, seconds, value);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.SETEX, key);
            }
        });
    }

    @Override
    public Long decrBy(final String key, final long integer) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.decrBy(key, integer);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.DECRBY, key);
            }
        });
    }

    @Override
    public Long decr(final String key) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.decr(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.DECR, key);
            }
        });
    }

    @Override
    public Long incrBy(final String key, final long integer) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.incrBy(key, integer);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.INCRBY, key);
            }
        });
    }

    @Override
    public Long incr(final String key) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.incr(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.INCR, key);
            }
        });
    }

    @Override
    public Long append(final String key, final String value) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.append(key, value);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.APPEND, key);
            }
        });
    }

    @Override
    public String substr(final String key, final int start, final int end) {
        return template.execute(new CacheCallback<String>() {
            @Override
            public String doInAction(Jedis jedis) {
                return jedis.substr(key, start, end);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.SUBSTR, key);
            }
        });
    }

    @Override
    public Long hset(final String key, final String field, final String value) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.hset(key, field, value);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.HSET, key);
            }
        });
    }

    @Override
    public String hget(final String key, final String field) {
        return template.execute(new CacheCallback<String>() {
            @Override
            public String doInAction(Jedis jedis) {
                return jedis.hget(key, field);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.HGET, key);
            }
        });
    }

    @Override
    public Long hsetnx(final String key, final String field, final String value) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.hsetnx(key, field, value);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.HSETNX, key);
            }
        });
    }

    @Override
    public String hmset(final String key, final Map<String, String> hash) {
        return template.execute(new CacheCallback<String>() {
            @Override
            public String doInAction(Jedis jedis) {
                return jedis.hmset(key, hash);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.HMSET, key);
            }
        });
    }

    @Override
    public List<String> hmget(final String key, final String... fields) {
        return template.execute(new CacheCallback<List<String>>() {
            @Override
            public List<String> doInAction(Jedis jedis) {
                return jedis.hmget(key, fields);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.HMGET, key);
            }
        });
    }

    @Override
    public Long hincrBy(final String key, final String field, final long value) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.hincrBy(key, field, value);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.HINCRBY, key);
            }
        });
    }

    @Override
    public Boolean hexists(final String key, final String field) {
        return template.execute(new CacheCallback<Boolean>() {
            @Override
            public Boolean doInAction(Jedis jedis) {
                return jedis.hexists(key, field);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.HEXISTS, key);
            }
        });
    }

    @Override
    public Long hdel(final String key, final String... field) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.hdel(key, field);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.HDEL, key);
            }
        });
    }

    @Override
    public Long hlen(final String key) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.hlen(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.HLEN, key);
            }
        });
    }

    @Override
    public Set<String> hkeys(final String key) {
        return template.execute(new CacheCallback<Set<String>>() {
            @Override
            public Set<String> doInAction(Jedis jedis) {
                return jedis.hkeys(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.HKEYS, key);
            }
        });
    }

    @Override
    public List<String> hvals(final String key) {
        return template.execute(new CacheCallback<List<String>>() {
            @Override
            public List<String> doInAction(Jedis jedis) {
                return jedis.hvals(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.HVALS, key);
            }
        });
    }

    @Override
    public Map<String, String> hgetAll(final String key) {
        return template.execute(new CacheCallback<Map<String, String>>() {
            @Override
            public Map<String, String> doInAction(Jedis jedis) {
                return jedis.hgetAll(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.HGETALL, key);
            }
        });
    }

    @Override
    public Long rpush(final String key, final String... string) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.rpush(key, string);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.RPUSH, key);
            }
        });
    }

    @Override
    public Long lpush(final String key, final String... string) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.lpush(key, string);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.LPUSH, key);
            }
        });
    }

    @Override
    public Long llen(final String key) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.llen(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.LLEN, key);
            }
        });
    }

    @Override
    public List<String> lrange(final String key, final long start, final long end) {
        return template.execute(new CacheCallback<List<String>>() {
            @Override
            public List<String> doInAction(Jedis jedis) {
                return jedis.lrange(key, start, end);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.LRANGE, key);
            }
        });
    }

    @Override
    public String ltrim(final String key, final long start, final long end) {
        return template.execute(new CacheCallback<String>() {
            @Override
            public String doInAction(Jedis jedis) {
                return jedis.ltrim(key, start, end);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.LTRIM, key);
            }
        });
    }

    @Override
    public String lindex(final String key, final long index) {
        return template.execute(new CacheCallback<String>() {
            @Override
            public String doInAction(Jedis jedis) {
                return jedis.lindex(key, index);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.LINDEX, key);
            }
        });
    }

    @Override
    public String lset(final String key, final long index, final String value) {
        return template.execute(new CacheCallback<String>() {
            @Override
            public String doInAction(Jedis jedis) {
                return jedis.lset(key, index, value);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.LSET, key);
            }
        });
    }

    @Override
    public Long lrem(final String key, final long count, final String value) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.lrem(key, count, value);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.LREM, key);
            }
        });
    }

    @Override
    public String lpop(final String key) {
        return template.execute(new CacheCallback<String>() {
            @Override
            public String doInAction(Jedis jedis) {
                return jedis.lpop(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.LPOP, key);
            }
        });
    }

    @Override
    public String rpop(final String key) {
        return template.execute(new CacheCallback<String>() {
            @Override
            public String doInAction(Jedis jedis) {
                return jedis.rpop(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.RPOP, key);
            }
        });
    }

    @Override
    public Long sadd(final String key, final String... member) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.sadd(key, member);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.SADD, key);
            }
        });
    }

    @Override
    public Set<String> smembers(final String key) {
        return template.execute(new CacheCallback<Set<String>>() {
            @Override
            public Set<String> doInAction(Jedis jedis) {
                return jedis.smembers(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.SMEMBERS, key);
            }
        });
    }

    @Override
    public Long srem(final String key, final String... member) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.srem(key, member);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.SREM, key);
            }
        });
    }

    @Override
    public String spop(final String key) {
        return template.execute(new CacheCallback<String>() {
            @Override
            public String doInAction(Jedis jedis) {
                return jedis.spop(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.SPOP, key);
            }
        });
    }

    @Override
    public Long scard(final String key) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.scard(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.SCARD, key);
            }
        });
    }

    @Override
    public Boolean sismember(final String key, final String member) {
        return template.execute(new CacheCallback<Boolean>() {
            @Override
            public Boolean doInAction(Jedis jedis) {
                return jedis.sismember(key, member);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.SISMEMBER, key);
            }
        });
    }

    @Override
    public String srandmember(final String key) {
        return template.execute(new CacheCallback<String>() {
            @Override
            public String doInAction(Jedis jedis) {
                return jedis.srandmember(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.SRANDMEMBER, key);
            }
        });
    }

    @Override
    public Long zadd(final String key, final double score, final String member) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.zadd(key, score, member);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZADD, key);
            }
        });
    }

    @Override
    public Long zadd(final String key, final Map<Double, String> scoreMembers) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.zadd(key, scoreMembers);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZADD, key);
            }
        });
    }

    @Override
    public Set<String> zrange(final String key, final long start, final long end) {
        return template.execute(new CacheCallback<Set<String>>() {
            @Override
            public Set<String> doInAction(Jedis jedis) {
                return jedis.zrange(key, start, end);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZRANGE, key);
            }
        });
    }

    @Override
    public Long zrem(final String key, final String... member) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.zrem(key, member);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZREM, key);
            }
        });
    }

    @Override
    public Double zincrby(final String key, final double score, final String member) {
        return template.execute(new CacheCallback<Double>() {
            @Override
            public Double doInAction(Jedis jedis) {
                return jedis.zincrby(key, score, member);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZINCRBY, key);
            }
        });
    }

    @Override
    public Long zrank(final String key, final String member) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.zrank(key, member);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZRANK, key);
            }
        });
    }

    @Override
    public Long zrevrank(final String key, final String member) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.zrevrank(key, member);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZREVRANK, key);
            }
        });
    }

    @Override
    public Set<String> zrevrange(final String key, final long start, final long end) {
        return template.execute(new CacheCallback<Set<String>>() {
            @Override
            public Set<String> doInAction(Jedis jedis) {
                return jedis.zrevrange(key, start, end);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZREVRANGE, key);
            }
        });
    }

    @Override
    public Set<Tuple> zrangeWithScores(final String key, final long start, final long end) {
        return template.execute(new CacheCallback<Set<Tuple>>() {
            @Override
            public Set<Tuple> doInAction(Jedis jedis) {
                return jedis.zrangeWithScores(key, start, end);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZRANGEWITHSCORES, key);
            }
        });
    }

    @Override
    public Set<Tuple> zrevrangeWithScores(final String key, final long start, final long end) {
        return template.execute(new CacheCallback<Set<Tuple>>() {
            @Override
            public Set<Tuple> doInAction(Jedis jedis) {
                return jedis.zrevrangeWithScores(key, start, end);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZREVRANGEWITHSCORES, key);
            }
        });
    }

    @Override
    public Long zcard(final String key) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.zcard(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZCARD, key);
            }
        });
    }

    @Override
    public Double zscore(final String key, final String member) {
        return template.execute(new CacheCallback<Double>() {
            @Override
            public Double doInAction(Jedis jedis) {
                return jedis.zscore(key, member);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZSCORE, key);
            }
        });
    }

    @Override
    public List<String> sort(final String key, final SortingParams sortingParameters) {
        return template.execute(new CacheCallback<List<String>>() {
            @Override
            public List<String> doInAction(Jedis jedis) {
                return jedis.sort(key, sortingParameters);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.SORT, key);
            }
        });
    }

    @Override
    public Long zcount(final String key, final double min, final double max) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.zcount(key, min, max);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZCOUNT, key);
            }
        });
    }

    @Override
    public Long zcount(final String key, final String min, final String max) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.zcount(key, min, max);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZCOUNT, key);
            }
        });
    }

    @Override
    public Set<String> zrangeByScore(final String key, final double min, final double max) {
        return template.execute(new CacheCallback<Set<String>>() {
            @Override
            public Set<String> doInAction(Jedis jedis) {
                return jedis.zrangeByScore(key, min, max);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZRANGEBYSCORE, key);
            }
        });
    }

    @Override
    public Set<String> zrangeByScore(final String key, final String min, final String max) {
        return template.execute(new CacheCallback<Set<String>>() {
            @Override
            public Set<String> doInAction(Jedis jedis) {
                return jedis.zrangeByScore(key, min, max);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZRANGEBYSCORE, key);
            }
        });
    }

    @Override
    public Set<String> zrevrangeByScore(final String key, final double min, final double max) {
        return template.execute(new CacheCallback<Set<String>>() {
            @Override
            public Set<String> doInAction(Jedis jedis) {
                return jedis.zrevrangeByScore(key, min, max);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZREVRANGEBYSCORE, key);
            }
        });
    }

    @Override
    public Set<String> zrangeByScore(final String key, final double min, final double max, final int offset, final int count) {
        return template.execute(new CacheCallback<Set<String>>() {
            @Override
            public Set<String> doInAction(Jedis jedis) {
                return jedis.zrangeByScore(key, min, max, offset, count);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZRANGEBYSCORE, key);
            }
        });
    }

    @Override
    public Set<String> zrevrangeByScore(final String key, final String min, final String max) {
        return template.execute(new CacheCallback<Set<String>>() {
            @Override
            public Set<String> doInAction(Jedis jedis) {
                return jedis.zrevrangeByScore(key, min, max);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZREVRANGEBYSCORE, key);
            }
        });
    }

    @Override
    public Set<String> zrangeByScore(final String key, final String min, final String max, final int offset, final int count) {
        return template.execute(new CacheCallback<Set<String>>() {
            @Override
            public Set<String> doInAction(Jedis jedis) {
                return jedis.zrangeByScore(key, min, max, offset, count);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZRANGEBYSCORE, key);
            }
        });
    }

    @Override
    public Set<String> zrevrangeByScore(final String key, final double min, final double max, final int offset, final int count) {
        return template.execute(new CacheCallback<Set<String>>() {
            @Override
            public Set<String> doInAction(Jedis jedis) {
                return jedis.zrevrangeByScore(key, max, min, offset, count);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZREVRANGEBYSCORE, key);
            }
        });
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(final String key, final double min, final double max) {
        return template.execute(new CacheCallback<Set<Tuple>>() {
            @Override
            public Set<Tuple> doInAction(Jedis jedis) {
                return jedis.zrangeByScoreWithScores(key, min, max);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZRANGEBYSCOREWITHSCORES, key);
            }
        });
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(final String key, final double min, final double max) {
        return template.execute(new CacheCallback<Set<Tuple>>() {
            @Override
            public Set<Tuple> doInAction(Jedis jedis) {
                return jedis.zrevrangeByScoreWithScores(key, max, min);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZREVRANGEBYSCOREWITHSCORES, key);
            }

        });
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(final String key, final double min, final double max, final int offset,
            final int count) {
        return template.execute(new CacheCallback<Set<Tuple>>() {
            @Override
            public Set<Tuple> doInAction(Jedis jedis) {
                return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZRANGEBYSCOREWITHSCORES, key);
            }
        });
    }

    @Override
    public Set<String> zrevrangeByScore(final String key, final String min, final String max, final int offset, final int count) {
        return template.execute(new CacheCallback<Set<String>>() {
            @Override
            public Set<String> doInAction(Jedis jedis) {
                return jedis.zrevrangeByScore(key, min, max, offset, count);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZREVRANGEBYSCORE, key);
            }
        });
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(final String key, final String min, final String max) {
        return template.execute(new CacheCallback<Set<Tuple>>() {
            @Override
            public Set<Tuple> doInAction(Jedis jedis) {
                return jedis.zrangeByScoreWithScores(key, min, max);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZRANGEBYSCOREWITHSCORES, key);
            }
        });
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(final String key, final String min, final String max) {
        return template.execute(new CacheCallback<Set<Tuple>>() {
            @Override
            public Set<Tuple> doInAction(Jedis jedis) {
                return jedis.zrevrangeByScoreWithScores(key, min, max);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZREVRANGEBYSCOREWITHSCORES, key);
            }
        });
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(final String key, final String min, final String max, final int offset,
            final int count) {
        return template.execute(new CacheCallback<Set<Tuple>>() {
            @Override
            public Set<Tuple> doInAction(Jedis jedis) {
                return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZRANGEBYSCOREWITHSCORES, key);
            }
        });
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(final String key, final double min, final double max, final int offset,
            final int count) {
        return template.execute(new CacheCallback<Set<Tuple>>() {
            @Override
            public Set<Tuple> doInAction(Jedis jedis) {
                return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZREVRANGEBYSCOREWITHSCORES, key);
            }
        });
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(final String key, final String min, final String max, final int offset,
            final int count) {
        return template.execute(new CacheCallback<Set<Tuple>>() {
            @Override
            public Set<Tuple> doInAction(Jedis jedis) {
                return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZREVRANGEBYSCOREWITHSCORES, key);
            }
        });
    }

    @Override
    public Long zremrangeByRank(final String key, final long start, final long end) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.zremrangeByRank(key, start, end);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZREMRANGEBYRANK, key);
            }
        });
    }

    @Override
    public Long zremrangeByScore(final String key, final double start, final double end) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.zremrangeByScore(key, start, end);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZREMRANGEBYSCORE, key);
            }
        });
    }

    @Override
    public Long zremrangeByScore(final String key, final String start, final String end) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.zremrangeByScore(key, start, end);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZREMRANGEBYSCORE, key);
            }
        });
    }

    @Override
    public Long linsert(final String key, final LIST_POSITION where, final String pivot, final String value) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.linsert(key, where, pivot, value);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.LINSERT, key);
            }
        });
    }

    @Override
    public Long lpushx(final String key, final String string) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.lpushx(key, string);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.LPUSHX, key);
            }
        });
    }

    @Override
    public Long rpushx(final String key, final String string) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.rpushx(key, string);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.RPUSHX, key);
            }
        });
    }

    @Override
    public Long del(final String... keys) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                Map<Jedis, List<String>> groupJedis = new HashMap<Jedis, List<String>>();
                for (String key : keys) {
                    if (!groupJedis.containsKey(jedis)) {
                        groupJedis.put(jedis, new ArrayList<String>());
                    }
                    groupJedis.get(jedis).add(key);
                }
                long result = 0L;
                for (Entry<Jedis, List<String>> entry : groupJedis.entrySet()) {
                    result += entry.getKey().del(entry.getValue().toArray(new String[entry.getValue().size()]));
                }
                return result;
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.DEL, keys);
            }
        });
    }

    @Override
    public String mset(final Map<String, String> keysvalues) {
        return template.execute(new CacheCallback<String>() {
            @Override
            public String doInAction(Jedis jedis) {
                Map<Jedis, List<String>> groupJedis = new HashMap<Jedis, List<String>>();
                for (Entry<String, String> entry : keysvalues.entrySet()) {
                    if (!groupJedis.containsKey(jedis)) {
                        groupJedis.put(jedis, new ArrayList<String>());
                    }
                    groupJedis.get(jedis).add(entry.getKey());
                    groupJedis.get(jedis).add(entry.getValue());
                }

                StringBuffer buffer = new StringBuffer();
                for (Entry<Jedis, List<String>> entry : groupJedis.entrySet()) {
                    buffer.append((entry.getKey().mset(entry.getValue().toArray(new String[entry.getValue().size()])))).append(
                            ",");
                }
                return buffer.toString();
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.MSET, keysvalues.keySet().toArray());
            }
        });
    }

    @Override
    public String mset(final String... keysvalues) {
        return template.execute(new CacheCallback<String>() {
            @Override
            public String doInAction(Jedis jedis) {
                return jedis.mset(keysvalues);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.MSET, keysvalues);
            }
        });
    }

    @Override
    public List<String> mget(final String... keys) {
        return template.execute(new CacheCallback<List<String>>() {
            @Override
            public List<String> doInAction(Jedis jedis) {
                Map<Jedis, List<String>> groupJedis = new HashMap<Jedis, List<String>>();
                for (String key : keys) {
                    if (!groupJedis.containsKey(jedis)) {
                        groupJedis.put(jedis, new ArrayList<String>());
                    }
                    groupJedis.get(jedis).add(key);
                }
                List<String> result = new ArrayList<String>();
                for (Entry<Jedis, List<String>> entry : groupJedis.entrySet()) {
                    result.addAll(entry.getKey().mget(entry.getValue().toArray(new String[entry.getValue().size()])));
                }
                return result;
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.MGET, keys);
            }
        });
    }

    @Override
    public Set<String> keys(final String pattern) {
        return template.execute(new CacheCallback<Set<String>>() {
            @Override
            public Set<String> doInAction(Jedis jedis) {
                return jedis.keys(pattern);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.KEYS, pattern);
            }
        });
    }

    @Override
    public String set(final byte[] key, final byte[] value) {
        return template.execute(new CacheCallback<String>() {
            @Override
            public String doInAction(Jedis jedis) {
                return jedis.set(key, value);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.SET, bytesToString(key));
            }
        });
    }

    @Override
    public byte[] get(final byte[] key) {
        return template.execute(new CacheCallback<byte[]>() {
            @Override
            public byte[] doInAction(Jedis jedis) {
                return jedis.get(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.GET, bytesToString(key));
            }
        });
    }

    @Override
    public Boolean exists(final byte[] key) {
        return template.execute(new CacheCallback<Boolean>() {
            @Override
            public Boolean doInAction(Jedis jedis) {
                return jedis.exists(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.EXISTS, bytesToString(key));
            }
        });
    }

    @Override
    public Long expire(final byte[] key, final int seconds) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.expire(key, seconds);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.EXISTS, bytesToString(key));
            }
        });
    }

    @Override
    public Long expireAt(final byte[] key, final long unixTime) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.expireAt(key, unixTime);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.EXPIREAT, bytesToString(key));
            }
        });
    }

    @Override
    public Long ttl(final byte[] key) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.ttl(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.TTL, bytesToString(key));
            }
        });
    }

    @Override
    public Boolean getbit(final byte[] key, final long offset) {
        return template.execute(new CacheCallback<Boolean>() {
            @Override
            public Boolean doInAction(Jedis jedis) {
                return jedis.getbit(key, offset);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.GETBIT, bytesToString(key));
            }
        });
    }

    @Override
    public Long setrange(final byte[] key, final long offset, final byte[] value) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.setrange(key, offset, value);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.SETRANGE, bytesToString(key));
            }
        });

    }

    @Override
    public String getrange(final byte[] key, final long startOffset, final long endOffset) {
        return template.execute(new CacheCallback<String>() {
            @Override
            public String doInAction(Jedis jedis) {
                return jedis.getrange(key, startOffset, endOffset);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.GETRANGE, bytesToString(key));
            }
        });
    }

    @Override
    public byte[] getSet(final byte[] key, final byte[] value) {
        return template.execute(new CacheCallback<byte[]>() {
            @Override
            public byte[] doInAction(Jedis jedis) {
                return jedis.getSet(key, value);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.GETSET, bytesToString(key));
            }
        });
    }

    @Override
    public Long setnx(final byte[] key, final byte[] value) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.setnx(key, value);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.SETNX, bytesToString(key));
            }
        });
    }

    @Override
    public String setex(final byte[] key, final int seconds, final byte[] value) {
        return template.execute(new CacheCallback<String>() {
            @Override
            public String doInAction(Jedis jedis) {
                return jedis.setex(key, seconds, value);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.SETEX, bytesToString(key));
            }
        });
    }

    @Override
    public Long decrBy(final byte[] key, final long integer) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.decrBy(key, integer);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.DECRBY, bytesToString(key));
            }
        });
    }

    @Override
    public Long decr(final byte[] key) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.decr(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.DECR, bytesToString(key));
            }
        });
    }

    @Override
    public Long incrBy(final byte[] key, final long integer) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.incrBy(key, integer);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.INCRBY, bytesToString(key));
            }
        });
    }

    @Override
    public Long incr(final byte[] key) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.incr(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.INCR, bytesToString(key));
            }
        });
    }

    @Override
    public Long append(final byte[] key, final byte[] value) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.append(key, value);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.APPEND, bytesToString(key));
            }
        });
    }

    @Override
    public byte[] substr(final byte[] key, final int start, final int end) {
        return template.execute(new CacheCallback<byte[]>() {
            @Override
            public byte[] doInAction(Jedis jedis) {
                return jedis.substr(key, start, end);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.SUBSTR, bytesToString(key));
            }
        });
    }

    @Override
    public Long hset(final byte[] key, final byte[] field, final byte[] value) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.hset(key, field, value);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.HSET, bytesToString(key));
            }
        });
    }

    @Override
    public byte[] hget(final byte[] key, final byte[] field) {
        return template.execute(new CacheCallback<byte[]>() {
            @Override
            public byte[] doInAction(Jedis jedis) {
                return jedis.hget(key, field);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.HGET, bytesToString(key));
            }
        });
    }

    @Override
    public Long hsetnx(final byte[] key, final byte[] field, final byte[] value) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.hsetnx(key, field, value);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.HSETNX, bytesToString(key));
            }
        });
    }

    @Override
    public String hmset(final byte[] key, final Map<byte[], byte[]> hash) {
        return template.execute(new CacheCallback<String>() {
            @Override
            public String doInAction(Jedis jedis) {
                return jedis.hmset(key, hash);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.HMSET, bytesToString(key));
            }
        });
    }

    @Override
    public List<byte[]> hmget(final byte[] key, final byte[]... fields) {
        return template.execute(new CacheCallback<List<byte[]>>() {
            @Override
            public List<byte[]> doInAction(Jedis jedis) {
                return jedis.hmget(key, fields);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.HMGET, bytesToString(key));
            }
        });
    }

    @Override
    public Long hincrBy(final byte[] key, final byte[] field, final long value) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.hincrBy(key, field, value);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.HINCRBY, bytesToString(key));
            }
        });
    }

    @Override
    public Boolean hexists(final byte[] key, final byte[] field) {
        return template.execute(new CacheCallback<Boolean>() {
            @Override
            public Boolean doInAction(Jedis jedis) {
                return jedis.hexists(key, field);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.HEXISTS, bytesToString(key));
            }
        });
    }

    @Override
    public Long hdel(final byte[] key, final byte[]... field) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.hdel(key, field);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.HDEL, bytesToString(key));
            }
        });
    }

    @Override
    public Long hlen(final byte[] key) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.hlen(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.HLEN, bytesToString(key));
            }
        });
    }

    @Override
    public Set<byte[]> hkeys(final byte[] key) {
        return template.execute(new CacheCallback<Set<byte[]>>() {
            @Override
            public Set<byte[]> doInAction(Jedis jedis) {
                return jedis.hkeys(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.HKEYS, bytesToString(key));
            }
        });
    }

    @Override
    public List<byte[]> hvals(final byte[] key) {
        return template.execute(new CacheCallback<List<byte[]>>() {
            @Override
            public List<byte[]> doInAction(Jedis jedis) {
                return jedis.hvals(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.HVALS, bytesToString(key));
            }
        });
    }

    @Override
    public Map<byte[], byte[]> hgetAll(final byte[] key) {
        return template.execute(new CacheCallback<Map<byte[], byte[]>>() {
            @Override
            public Map<byte[], byte[]> doInAction(Jedis jedis) {
                return jedis.hgetAll(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.HGETALL, bytesToString(key));
            }
        });
    }

    @Override
    public Long rpush(final byte[] key, final byte[]... string) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.rpush(key, string);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.RPUSH, bytesToString(key));
            }
        });
    }

    @Override
    public Long lpush(final byte[] key, final byte[]... string) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.lpush(key, string);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.LPUSH, bytesToString(key));
            }
        });
    }

    @Override
    public Long llen(final byte[] key) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.llen(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.LLEN, bytesToString(key));
            }
        });
    }

    @Override
    public String lset(final byte[] key, final int index, final byte[] value) {
        return template.execute(new CacheCallback<String>() {
            @Override
            public String doInAction(Jedis jedis) {
                return jedis.lset(key, index, value);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.LSET, bytesToString(key));
            }
        });
    }

    @Override
    public Long lrem(final byte[] key, final int count, final byte[] value) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.lrem(key, count, value);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.LREM, bytesToString(key));
            }
        });
    }

    @Override
    public byte[] lpop(final byte[] key) {
        return template.execute(new CacheCallback<byte[]>() {
            @Override
            public byte[] doInAction(Jedis jedis) {
                return jedis.lpop(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.LPOP, bytesToString(key));
            }
        });
    }

    @Override
    public byte[] rpop(final byte[] key) {
        return template.execute(new CacheCallback<byte[]>() {
            @Override
            public byte[] doInAction(Jedis jedis) {
                return jedis.rpop(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.RPOP, bytesToString(key));
            }
        });
    }

    @Override
    public Long sadd(final byte[] key, final byte[]... member) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.sadd(key, member);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.SADD, bytesToString(key));
            }
        });
    }

    @Override
    public Set<byte[]> smembers(final byte[] key) {
        return template.execute(new CacheCallback<Set<byte[]>>() {
            @Override
            public Set<byte[]> doInAction(Jedis jedis) {
                return jedis.smembers(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.SMEMBERS, bytesToString(key));
            }
        });
    }

    @Override
    public Long srem(final byte[] key, final byte[]... member) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.srem(key, member);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.SREM, bytesToString(key));
            }
        });
    }

    @Override
    public byte[] spop(final byte[] key) {
        return template.execute(new CacheCallback<byte[]>() {
            @Override
            public byte[] doInAction(Jedis jedis) {
                return jedis.spop(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.SPOP, bytesToString(key));
            }
        });
    }

    @Override
    public Long scard(final byte[] key) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.scard(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.SCARD, bytesToString(key));
            }
        });
    }

    @Override
    public Boolean sismember(final byte[] key, final byte[] member) {
        return template.execute(new CacheCallback<Boolean>() {
            @Override
            public Boolean doInAction(Jedis jedis) {
                return jedis.sismember(key, member);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.SISMEMBER, key);
            }
        });
    }

    @Override
    public byte[] srandmember(final byte[] key) {
        return template.execute(new CacheCallback<byte[]>() {
            @Override
            public byte[] doInAction(Jedis jedis) {
                return jedis.srandmember(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.SRANDMEMBER, key);
            }
        });
    }

    @Override
    public Long zadd(final byte[] key, final double score, final byte[] member) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.zadd(key, score, member);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZADD, bytesToString(key));
            }
        });
    }

    @Override
    public Long zadd(final byte[] key, final Map<Double, byte[]> scoreMembers) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.zadd(key, scoreMembers);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZADD, bytesToString(key));
            }
        });
    }

    @Override
    public Long zrem(final byte[] key, final byte[]... member) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.zrem(key, member);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZREM, bytesToString(key));
            }
        });
    }

    @Override
    public Double zincrby(final byte[] key, final double score, final byte[] member) {
        return template.execute(new CacheCallback<Double>() {
            @Override
            public Double doInAction(Jedis jedis) {
                return jedis.zincrby(key, score, member);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZINCRBY, bytesToString(key));
            }
        });
    }

    @Override
    public Long zrank(final byte[] key, final byte[] member) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.zrank(key, member);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZRANK, bytesToString(key));
            }
        });
    }

    @Override
    public Long zrevrank(final byte[] key, final byte[] member) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.zrevrank(key, member);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZREVRANK, bytesToString(key));
            }
        });
    }

    @Override
    public Long zcard(final byte[] key) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.zcard(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZCARD, bytesToString(key));
            }
        });
    }

    @Override
    public Double zscore(final byte[] key, final byte[] member) {
        return template.execute(new CacheCallback<Double>() {
            @Override
            public Double doInAction(Jedis jedis) {
                return jedis.zscore(key, member);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZSCORE, bytesToString(key));
            }
        });
    }

    @Override
    public List<byte[]> sort(final byte[] key) {
        return template.execute(new CacheCallback<List<byte[]>>() {
            @Override
            public List<byte[]> doInAction(Jedis jedis) {
                return jedis.sort(key);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.SORT, bytesToString(key));
            }
        });
    }

    @Override
    public List<byte[]> sort(final byte[] key, final SortingParams sortingParameters) {
        return template.execute(new CacheCallback<List<byte[]>>() {
            @Override
            public List<byte[]> doInAction(Jedis jedis) {
                return jedis.sort(key, sortingParameters);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.SORT, bytesToString(key));
            }
        });
    }

    @Override
    public Long zcount(final byte[] key, final double min, final double max) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.zcount(key, min, max);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZCOUNT, bytesToString(key));
            }
        });
    }

    @Override
    public Long zcount(final byte[] key, final byte[] min, final byte[] max) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.zcount(key, min, max);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZCOUNT, bytesToString(key));
            }
        });
    }

    @Override
    public Set<byte[]> zrangeByScore(final byte[] key, final double min, final double max) {
        return template.execute(new CacheCallback<Set<byte[]>>() {
            @Override
            public Set<byte[]> doInAction(Jedis jedis) {
                return jedis.zrangeByScore(key, min, max);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZRANGEBYSCORE, bytesToString(key));
            }
        });
    }

    @Override
    public Set<byte[]> zrangeByScore(final byte[] key, final byte[] min, final byte[] max) {
        return template.execute(new CacheCallback<Set<byte[]>>() {
            @Override
            public Set<byte[]> doInAction(Jedis jedis) {
                return jedis.zrangeByScore(key, min, max);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZRANGEBYSCORE, bytesToString(key));
            }
        });
    }

    @Override
    public Set<byte[]> zrevrangeByScore(final byte[] key, final double min, final double max) {
        return template.execute(new CacheCallback<Set<byte[]>>() {
            @Override
            public Set<byte[]> doInAction(Jedis jedis) {
                return jedis.zrevrangeByScore(key, min, max);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZREVRANGEBYSCORE, bytesToString(key));
            }
        });
    }

    @Override
    public Set<byte[]> zrangeByScore(final byte[] key, final double min, final double max, final int offset, final int count) {
        return template.execute(new CacheCallback<Set<byte[]>>() {
            @Override
            public Set<byte[]> doInAction(Jedis jedis) {
                return jedis.zrangeByScore(key, min, max, offset, count);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZRANGEBYSCORE, bytesToString(key));
            }
        });
    }

    @Override
    public Set<byte[]> zrevrangeByScore(final byte[] key, final byte[] min, final byte[] max) {
        return template.execute(new CacheCallback<Set<byte[]>>() {
            @Override
            public Set<byte[]> doInAction(Jedis jedis) {
                return jedis.zrevrangeByScore(key, min, max);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZREVRANGEBYSCORE, bytesToString(key));
            }
        });
    }

    @Override
    public Set<byte[]> zrangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset, final int count) {
        return template.execute(new CacheCallback<Set<byte[]>>() {
            @Override
            public Set<byte[]> doInAction(Jedis jedis) {
                return jedis.zrangeByScore(key, min, max, offset, count);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZRANGEBYSCORE, bytesToString(key));
            }
        });
    }

    @Override
    public Set<byte[]> zrevrangeByScore(final byte[] key, final double min, final double max, final int offset, final int count) {
        return template.execute(new CacheCallback<Set<byte[]>>() {
            @Override
            public Set<byte[]> doInAction(Jedis jedis) {
                return jedis.zrevrangeByScore(key, max, min, offset, count);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZREVRANGEBYSCORE, bytesToString(key));
            }
        });
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(final byte[] key, final double min, final double max) {
        return template.execute(new CacheCallback<Set<Tuple>>() {
            @Override
            public Set<Tuple> doInAction(Jedis jedis) {
                return jedis.zrangeByScoreWithScores(key, min, max);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZRANGEBYSCOREWITHSCORES, bytesToString(key));
            }
        });
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(final byte[] key, final double min, final double max) {
        return template.execute(new CacheCallback<Set<Tuple>>() {
            @Override
            public Set<Tuple> doInAction(Jedis jedis) {
                return jedis.zrevrangeByScoreWithScores(key, max, min);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZREVRANGEBYSCOREWITHSCORES, bytesToString(key));
            }

        });
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset,
            final int count) {
        return template.execute(new CacheCallback<Set<Tuple>>() {
            @Override
            public Set<Tuple> doInAction(Jedis jedis) {
                return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZRANGEBYSCOREWITHSCORES, bytesToString(key));
            }
        });
    }

    @Override
    public Set<byte[]> zrevrangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset, final int count) {
        return template.execute(new CacheCallback<Set<byte[]>>() {
            @Override
            public Set<byte[]> doInAction(Jedis jedis) {
                return jedis.zrevrangeByScore(key, min, max, offset, count);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZREVRANGEBYSCORE, bytesToString(key));
            }
        });
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max) {
        return template.execute(new CacheCallback<Set<Tuple>>() {
            @Override
            public Set<Tuple> doInAction(Jedis jedis) {
                return jedis.zrangeByScoreWithScores(key, min, max);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZRANGEBYSCOREWITHSCORES, bytesToString(key));
            }
        });
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max) {
        return template.execute(new CacheCallback<Set<Tuple>>() {
            @Override
            public Set<Tuple> doInAction(Jedis jedis) {
                return jedis.zrevrangeByScoreWithScores(key, min, max);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZREVRANGEBYSCOREWITHSCORES, bytesToString(key));
            }
        });
    }

    @Override
    public Set<Tuple> zrangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max, final int offset,
            final int count) {
        return template.execute(new CacheCallback<Set<Tuple>>() {
            @Override
            public Set<Tuple> doInAction(Jedis jedis) {
                return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZRANGEBYSCOREWITHSCORES, bytesToString(key));
            }
        });
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset,
            final int count) {
        return template.execute(new CacheCallback<Set<Tuple>>() {
            @Override
            public Set<Tuple> doInAction(Jedis jedis) {
                return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZREVRANGEBYSCOREWITHSCORES, bytesToString(key));
            }
        });
    }

    @Override
    public Set<Tuple> zrevrangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max, final int offset,
            final int count) {
        return template.execute(new CacheCallback<Set<Tuple>>() {
            @Override
            public Set<Tuple> doInAction(Jedis jedis) {
                return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZREVRANGEBYSCOREWITHSCORES, bytesToString(key));
            }
        });
    }

    @Override
    public Long zremrangeByScore(final byte[] key, final double start, final double end) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.zremrangeByScore(key, start, end);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZREMRANGEBYSCORE, bytesToString(key));
            }
        });
    }

    @Override
    public Long zremrangeByScore(final byte[] key, final byte[] start, final byte[] end) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.zremrangeByScore(key, start, end);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.ZREMRANGEBYSCORE, bytesToString(key));
            }
        });
    }

    @Override
    public Long linsert(final byte[] key, final LIST_POSITION where, final byte[] pivot, final byte[] value) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.linsert(key, where, pivot, value);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.LINSERT, bytesToString(key));
            }
        });
    }

    @Override
    public Long lpushx(final byte[] key, final byte[] string) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.lpushx(key, string);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.LPUSHX, bytesToString(key));
            }
        });
    }

    @Override
    public Long rpushx(final byte[] key, final byte[] string) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.rpushx(key, string);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.RPUSHX, bytesToString(key));
            }
        });
    }

    @Override
    public Long del(final byte[]... keys) {
        return template.execute(new CacheCallback<Long>() {
            @Override
            public Long doInAction(Jedis jedis) {
                return jedis.del(keys);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.DEL, bytesToString(keys));
            }
        });
    }

    @Override
    public String mset(final byte[][] keysvalues) {
        return template.execute(new CacheCallback<String>() {
            @Override
            public String doInAction(Jedis jedis) {
                return jedis.mset(keysvalues);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.MSET, bytesToString(keysvalues));
            }
        });
    }

    @Override
    public List<byte[]> mget(final byte[]... keys) {
        return template.execute(new CacheCallback<List<byte[]>>() {
            @Override
            public List<byte[]> doInAction(Jedis jedis) {
                return jedis.mget(keys);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.MGET, bytesToString(keys));
            }
        });
    }

    @Override
    public Set<byte[]> keys(final byte[] pattern) {
        return template.execute(new CacheCallback<Set<byte[]>>() {
            @Override
            public Set<byte[]> doInAction(Jedis jedis) {
                return jedis.keys(pattern);
            }

            @Override
            public String getIdentity() {
                return CacheClientUtils.generateIdentity(Commands.KEYS, pattern);
            }
        });
    }
}
