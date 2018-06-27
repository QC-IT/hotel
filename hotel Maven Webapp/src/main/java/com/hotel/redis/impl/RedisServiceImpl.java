package com.hotel.redis.impl;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.hotel.redis.RedisService;  

public class RedisServiceImpl implements RedisService{
private static final Logger logger=LoggerFactory.getLogger(RedisServiceImpl.class);
	private RedisTemplate<Serializable, Object> redisTemplate;
    /**
     * 批量删除对应的value
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
        	logger.debug("redis remove "+key);
            remove(key);
        }
    }

    /**
     * 批量删除value
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    /**
     * 删除对应的value
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
        	logger.debug("redis remove "+key);
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     * @param key
     * @return
     */
    public boolean exists(final String key) {
    	 boolean flag=redisTemplate.hasKey(key);
  
    		logger.debug("redis not exists "+flag);
        return flag;
    }

    /**
     * 读取缓存
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        logger.debug("get redis key:"+key+" value:"+result);
        return result;
    }

    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
            logger.debug("set redis key:"+key+" value:"+value);
        } catch (Exception e) {
        	logger.error("set cache error", e);
        }
        return result;
    }

    /**
     * 写入缓存
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
            logger.debug("set redis key:"+key+" value:"+value+" expireTime:"+expireTime);
        } catch (Exception e) {
        	logger.error("set cache error", e);
        }
        return result;
    }
    
    public long increment(final String key , long delta){
         return redisTemplate.opsForValue().increment(key, delta);
    }

    public RedisTemplate<Serializable, Object> getRedisTemplate() {
	return redisTemplate;
}

public void setRedisTemplate(RedisTemplate<Serializable, Object> redisTemplate) {
	this.redisTemplate = redisTemplate;
}




}