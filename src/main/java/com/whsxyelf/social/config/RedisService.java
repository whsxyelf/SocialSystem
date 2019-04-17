package com.whsxyelf.social.config;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.whsxyelf.social.bean.User;

@Service
public class RedisService {
	//日志
	private static final Logger logger = LoggerFactory.getLogger(RedisService.class);
	@Autowired
	private Redis redis;
	@Autowired
	private JedisCache jedisCache;
	
	public List<User> queryUserList(){
		/**
		 * 判断key是否存在
		 * if 命中 取缓存
		 * else 取DB , 然后放入缓存
		 */
		//String key = Constants.CITY;
		//这里的的Constants.CITY是 判断缓存中是否有key 的依据
		//可我参考的那个没给出constants包里面的键的命名；我不知道该怎样规范命名
		String key = "";
		boolean hasKey = jedisCache.existKey(key);
		if(hasKey) {
			List<User> list = (List<User>) jedisCache.get(key);
			logger.info("取缓存中的集合");
			return list;
		}
		List<User> list = redis.saveToRedis();
		jedisCache.set(key, list);
		logger.info("取DB中的值，并存入缓存，key:{}",key);
		return list;	
	}
	
}
