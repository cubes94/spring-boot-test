package com.whc.service;

import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisListCommands.Position;
import org.springframework.data.redis.connection.RedisZSetCommands.Tuple;
import org.springframework.data.redis.connection.SortParameters;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisCacheService<K, V> {

	/**
	 * 设置key
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Boolean set(final String key, final String value);

	/**
	 * 根据key获取对象
	 * 
	 * @param key
	 * @return
	 */
	public String get(final String key);

	/**
	 * 根据key删除
	 * 
	 * @param key
	 * @return
	 */
	public Long del(final String key);

	/**
	 * 某段时间后执行
	 * 
	 * @param key
	 * @param expire
	 * @return
	 */
	public Boolean expire(final String key, final long expire);

	/**
	 * 在某个时间点失效
	 * 
	 * @param key
	 * @param expire
	 * @return
	 */
	public Boolean expireAt(final String key, final long expire);

	/**
	 * 查询剩余时间
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long ttl(final String key, final long value);

	/**
	 * 判断key是否存在
	 * 
	 * @param key
	 * @return
	 */
	public Boolean exists(final String key);

	/**
	 * 返回key所储存的值的类型
	 * 
	 * @param key
	 * @return
	 */
	public DataType type(final String key);

	/**
	 * 对key所储存的字符串值, 设置或清除指定偏移量上的位(bit)
	 * 
	 * @param key
	 * @param offset
	 * @param value
	 * @return
	 */
	public Boolean setBit(final String key, final long offset, final boolean value);

	/**
	 * 对key所储存的字符串值, 获取指定偏移量上的位(bit)
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Boolean getBit(final String key, final long value);

	/**
	 * 用value参数覆写(overwrite)给定 key所储存的字符串值, 从偏移量offset开始
	 * 
	 * @param key
	 * @param offset
	 * @param value
	 * @return
	 */
	public Boolean setRange(final String key, final Long offset, final String value);

	/**
	 * 返回key中字符串值的子字符串, 字符串的截取范围由start和end两个偏移量决定
	 * 
	 * @param key
	 * @param startOffset
	 * @param endOffset
	 * @return
	 */
	public byte[] getRange(final String key, final long startOffset, final long endOffset);

	/**
	 * 删除对象, 依赖key
	 * 
	 * @param key
	 */
	public void delete(String key);

	/**
	 * 删除集合, 依赖key集合
	 */
	public void delete(List<String> keys);

	/**
	 * 根据参数count的值, 移除列表中与参数value相等的元素
	 * 
	 * @param key
	 * @param count
	 * @param value
	 * @return
	 */
	public Long lrem(final String key, final long count, final String value);

	/**
	 * 将一个或多个值value插入到列表key的表头
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long lpush(final String key, final String value);

	public byte[] getSet(final String key, final String value);

	public Boolean setNX(final String key, final String value);

	public Boolean setEx(final String key, final String value, final long seconds);

	public Long decrBy(final String key, final long integer);

	public Long decr(final String key);

	public Long incrBy(final String key, final long integer);

	public Long incr(final String key);

	public Long append(final String key, final String value);

	public Boolean hSet(final String key, final String value, final String field);

	public byte[] hGet(final String key, final String field);

	public Boolean hSetNX(final String key, final String value, final String field);

	public Boolean hMSet(final String key, final Map<byte[], byte[]> hash);

	/**
	 * 返回哈希表key中, 一个或多个给定域的值
	 * 
	 * @param key
	 * @param fields
	 * @return
	 */
	public List<String> hMGet(final String key, final byte[]... fields);

	public Long hIncrBy(final String key, final long value, final String field);

	public Boolean hexists(final String key, final String field);

	public Long hDel(final String key, final String field);

	public Long hlen(final String key);

	public Set<String> hKeys(final String key);

	public List<String> hVals(final String key);

	public Map<byte[], byte[]> hGetAll(final String key);

	// ----------------------------- list -----------------------------
	// ------------------ l表示 list或 left, r表示right ------------------
	
	public Long rPush(final String key, final String value);

	public Long lLen(final String key);

	public List<String> lRange(final String key, final Long start, final Long end);

	public Boolean ltrim(final String key, final long start, final long end);

	public String lIndex(final String key, final long index);

	public Boolean lSet(final String key, final String value, final long index);

	public String lPop(final String key);

	public String rPop(final String key);

	public Long sAdd(final String key, final String member);

	public Set<String> sMembers(final String key);

	public Long sRem(final String key, final String member);

	public String sPop(final String key);

	public Long sCard(final String key);

	public Boolean sIsMember(final String key, final String member);

	public String sRandMember(final String key);

	public Boolean zAdd(final String key, final double score, final String member);

	public Set<String> zRange(final String key, final int start, final int end);

	public Long zRem(final String key, final String member);

	public Double zIncrBy(final String key, final double score, final String member);

	public Long zRank(final String key, final String member);

	public Long zRevRank(final String key, final String member);

	public Set<String> zRevRange(final String key, final int start, final int end);

	public Set<Tuple> zRangeWithScores(final String key, final int start, final int end);

	public Set<Tuple> zRevRangeWithScores(final String key, final int start, final int end);

	public Long zCard(final String key);

	public Double zScore(final String key, final String member);

	public List<String> sort(final String key, final SortParameters params);

	public Long zCount(final String key, final double min, final double max);

	public Set<String> zrevrangeByScore(final String key, final double max, final double min);

	public Set<String> zrevrangeByScore(final String key, final double max,
										final double min, final int offset, final int count);

	public Set<Tuple> zrangeByScoreWithScores(final String key, final double min, final double max);

	public Set<Tuple> zrevrangeByScoreWithScores(final String key, final double max, final double min);

	public Set<Tuple> zrangeByScoreWithScores(final String key,
											  final double min, final double max, final int offset, final int count);

	public Set<Tuple> zrevrangeByScoreWithScores(final String key,
												 final double max, final double min, final int offset, final int count);

	public Long zremrangeByScore(final String key, final double start, final double end);

	public Long linsert(final String key, final Position where,
						final String pivot, final String value);

	public Set<String> zRangeByScore(final String key, final double min, final double max);

	public Set<String> zRangeByScore(final String key, final double min,
									 final double max, final int offset, final int count);

	public Set<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max);

	public Set<Tuple> zRangeByScoreWithScores(final String key,
											  final double min, final double max, final int offset, final int count);

	public Set<String> zRevRangeByScore(final String key, final double max, final double min);

	public Set<String> zRevRangeByScore(final String key, final double max,
										final double min, final int offset, final int count);

	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final double max, final double min);

	public Set<Tuple> zRevRangeByScoreWithScores(final String key,
												 final double max, final double min, final int offset, final int count);

	public Long zRemRangeByScore(final String key, final double start, final double end);
	
	/**
     * 清空Redis所有数据
     * 
     * @return
     */
    public Boolean flushDB();
    
    /**
     * 查看Redis里有多少数据
     * 
     * @return
     */
    public Long dbSize();
    
    /**
     * 检查是否连接成功
     * 
     * @return
     */
    public String ping();
}