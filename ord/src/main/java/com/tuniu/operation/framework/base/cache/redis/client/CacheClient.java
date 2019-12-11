package com.tuniu.operation.framework.base.cache.redis.client;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Client;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.Tuple;

import com.tuniu.operation.framework.base.cache.redis.pool.CacheChangedEventListener;
import com.tuniu.operation.framework.base.cache.redis.pool.HostPair;

public interface CacheClient {

    String set(String key, String value);

    String get(String key);

    Set<String> keys(final String pattern);

    String mset(Map<String, String> keysvalues);

    String mset(String... keysvalues);

    List<String> mget(String... keys);

    Long del(String... key);

    Boolean exists(String key);

    Long expire(String key, int seconds);

    Long expireAt(String key, long unixTime);

    Long ttl(String key);

    Boolean setbit(String key, long offset, boolean value);

    Boolean getbit(String key, long offset);

    Long setrange(String key, long offset, String value);

    String getrange(String key, long startOffset, long endOffset);

    String getSet(String key, String value);

    Long setnx(String key, String value);

    String setex(String key, int seconds, String value);

    Long decrBy(String key, long integer);

    Long decr(String key);

    Long incrBy(String key, long integer);

    Long incr(String key);

    Long append(String key, String value);

    String substr(String key, int start, int end);

    Long hset(String key, String field, String value);

    String hget(String key, String field);

    Long hsetnx(String key, String field, String value);

    String hmset(String key, Map<String, String> hash);

    List<String> hmget(String key, String... fields);

    Long hincrBy(String key, String field, long value);

    Boolean hexists(String key, String field);

    Long hdel(String key, String... field);

    Long hlen(String key);

    Set<String> hkeys(String key);

    List<String> hvals(String key);

    Map<String, String> hgetAll(String key);

    Long rpush(String key, String... string);

    Long lpush(String key, String... string);

    Long llen(String key);

    List<String> lrange(String key, long start, long end);

    String ltrim(String key, long start, long end);

    String lindex(String key, long index);

    String lset(String key, long index, String value);

    Long lrem(String key, long count, String value);

    String lpop(String key);

    String rpop(String key);

    Long sadd(String key, String... member);

    Set<String> smembers(String key);

    Long srem(String key, String... member);

    String spop(String key);

    Long scard(String key);

    Boolean sismember(String key, String member);

    String srandmember(String key);

    Long zadd(String key, double score, String member);

    Long zadd(String key, Map<Double, String> scoreMembers);

    Set<String> zrange(String key, long start, long end);

    Long zrem(String key, String... member);

    Double zincrby(String key, double score, String member);

    Long zrank(String key, String member);

    Long zrevrank(String key, String member);

    Set<String> zrevrange(String key, long start, long end);

    Set<Tuple> zrangeWithScores(String key, long start, long end);

    Set<Tuple> zrevrangeWithScores(String key, long start, long end);

    Long zcard(String key);

    Double zscore(String key, String member);

    // List<String> sort(String key);

    List<String> sort(String key, SortingParams sortingParameters);

    Long zcount(String key, double min, double max);

    Long zcount(String key, String min, String max);

    Set<String> zrangeByScore(String key, double min, double max);

    Set<String> zrangeByScore(String key, String min, String max);

    Set<String> zrevrangeByScore(String key, double max, double min);

    Set<String> zrangeByScore(String key, double min, double max, int offset, int count);

    Set<String> zrevrangeByScore(String key, String max, String min);

    Set<String> zrangeByScore(String key, String min, String max, int offset, int count);

    Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count);

    Set<Tuple> zrangeByScoreWithScores(String key, double min, double max);

    Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min);

    Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count);

    Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count);

    Set<Tuple> zrangeByScoreWithScores(String key, String min, String max);

    Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min);

    Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count);

    Set<Tuple> zrevrangeByScoreWithScores(String key, double min, double max, int offset, int count);

    Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count);

    Long zremrangeByRank(String key, long start, long end);

    Long zremrangeByScore(String key, double start, double end);

    Long zremrangeByScore(String key, String start, String end);

    Long linsert(String key, Client.LIST_POSITION where, String pivot, String value);

    Long lpushx(String key, String string);

    Long rpushx(String key, String string);

    String set(byte[] key, byte[] value);

    byte[] get(byte[] key);

    Set<byte[]> keys(final byte[] pattern);

    String mset(final byte[][] keysvalues);

    List<byte[]> mget(byte[]... keys);

    Long del(byte[]... key);

    Boolean exists(byte[] key);

    Long expire(byte[] key, int seconds);

    Long expireAt(byte[] key, long unixTime);

    Long ttl(byte[] key);

    Boolean getbit(byte[] key, long offset);

    Long setrange(byte[] key, long offset, byte[] value);

    String getrange(byte[] key, long startOffset, long endOffset);

    byte[] getSet(byte[] key, byte[] value);

    Long setnx(byte[] key, byte[] value);

    String setex(byte[] key, int seconds, byte[] value);

    Long decrBy(byte[] key, long integer);

    Long decr(byte[] key);

    Long incrBy(byte[] key, long integer);

    Long incr(byte[] key);

    Long append(byte[] key, byte[] value);

    byte[] substr(byte[] key, int start, int end);

    Long hset(byte[] key, byte[] field, byte[] value);

    byte[] hget(byte[] key, byte[] field);

    Long hsetnx(byte[] key, byte[] field, byte[] value);

    String hmset(byte[] key, Map<byte[], byte[]> hash);

    List<byte[]> hmget(byte[] key, byte[]... fields);

    Long hincrBy(byte[] key, byte[] field, long value);

    Boolean hexists(byte[] key, byte[] field);

    Long hdel(byte[] key, byte[]... field);

    Long hlen(byte[] key);

    Set<byte[]> hkeys(byte[] key);

    List<byte[]> hvals(byte[] key);

    Map<byte[], byte[]> hgetAll(byte[] key);

    Long rpush(byte[] key, byte[]... string);

    Long lpush(byte[] key, byte[]... string);

    Long llen(byte[] key);

    String lset(final byte[] key, final int index, final byte[] value);

    Long lrem(final byte[] key, final int count, final byte[] value);

    byte[] lpop(byte[] key);

    byte[] rpop(byte[] key);

    Long sadd(byte[] key, byte[]... member);

    Set<byte[]> smembers(byte[] key);

    Long srem(byte[] key, byte[]... member);

    byte[] spop(byte[] key);

    Long scard(byte[] key);

    Boolean sismember(byte[] key, byte[] member);

    byte[] srandmember(byte[] key);

    Long zadd(byte[] key, double score, byte[] member);

    Long zadd(byte[] key, Map<Double, byte[]> scoreMembers);

    Long zrem(byte[] key, byte[]... member);

    Double zincrby(byte[] key, double score, byte[] member);

    Long zrank(byte[] key, byte[] member);

    Long zrevrank(byte[] key, byte[] member);

    Long zcard(byte[] key);

    Double zscore(byte[] key, byte[] member);

    List<byte[]> sort(byte[] key);

    List<byte[]> sort(byte[] key, SortingParams sortingParameters);

    Long zcount(byte[] key, double min, double max);

    Long zcount(byte[] key, byte[] min, byte[] max);

    Set<byte[]> zrangeByScore(byte[] key, double min, double max);

    Set<byte[]> zrangeByScore(byte[] key, byte[] min, byte[] max);

    Set<byte[]> zrevrangeByScore(byte[] key, double max, double min);

    Set<byte[]> zrangeByScore(byte[] key, double min, double max, int offset, int count);

    Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min);

    Set<byte[]> zrangeByScore(byte[] key, byte[] min, byte[] max, int offset, int count);

    Set<byte[]> zrevrangeByScore(byte[] key, double max, double min, int offset, int count);

    Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max);

    Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min);

    Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max, int offset, int count);

    Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min, int offset, int count);

    Set<Tuple> zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max);

    Set<Tuple> zrevrangeByScoreWithScores(byte[] key, byte[] max, byte[] min);

    Set<Tuple> zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max, int offset, int count);

    Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double min, double max, int offset, int count);

    Set<Tuple> zrevrangeByScoreWithScores(byte[] key, byte[] max, byte[] min, int offset, int count);

    Long zremrangeByScore(byte[] key, double start, double end);

    Long zremrangeByScore(byte[] key, byte[] start, byte[] end);

    Long linsert(byte[] key, Client.LIST_POSITION where, byte[] pivot, byte[] value);

    Long lpushx(byte[] key, byte[] string);

    Long rpushx(byte[] key, byte[] string);

    // public String flushDB();

    void addCacheChangedEventListener(CacheChangedEventListener listener);

    boolean removeCacheChangedEventListener(CacheChangedEventListener listener);

    void addProxy(HostPair hp);

    void removeProxy(HostPair hp);
}
