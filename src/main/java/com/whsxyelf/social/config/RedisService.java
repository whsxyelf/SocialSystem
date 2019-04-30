package com.whsxyelf.social.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.whsxyelf.social.bean.User;
import com.whsxyelf.social.constants.Constants;
import com.whsxyelf.social.mapper.UserMapper;

@Service
public class RedisService {

	private static final Logger logger = LoggerFactory.getLogger(RedisService.class);
	@Autowired
	private UserMapper mapper;
	@Autowired
	private JedisCache jedisCache;
	
	public User findByUserNo(int userNo) {
		/**
		 * 判断key是否存在
		 * if 命中 取缓存
		 * else 取DB , 然后放入缓存
		 */
		String key = Constants.USER + userNo;
		boolean hasKey = jedisCache.existKey(key);
		if(hasKey) {
			User user = (User) jedisCache.get(key);
			logger.info("取缓存中的值！");
			return user;
		}
		User user = mapper.saveToRedis(userNo);
		jedisCache.set(key, user);
		logger.info("取DB中的值，存在缓存；key:{}",key);
		return user;
	}
	
}
