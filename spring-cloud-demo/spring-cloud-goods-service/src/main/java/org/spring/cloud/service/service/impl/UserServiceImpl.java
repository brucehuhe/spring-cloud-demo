package org.spring.cloud.service.service.impl;

import java.util.List;

import org.spring.cloud.service.mapper.rxshopMapper.UsersMapper;
import org.spring.cloud.service.model.rxshopPo.Users;
import org.spring.cloud.service.mapper.stockMapper.UserMapper;
import org.spring.cloud.service.model.stockPo.User;
import org.spring.cloud.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	UsersMapper usersMapper;

	@SuppressWarnings("rawtypes")
	@Autowired
	RedisTemplate redisTemplate;

	public List<User> getUser() {
		List<User> listUsers = userMapper.selectAll();
		return listUsers;
	}
	/**
	 * 加入缓存测试，注意由于redis中已加入序列，当要使用时，对应的实体类要加入序列号
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Users> getUsers() {
		List<Users> listUsers = usersMapper.selectAll();
		ValueOperations vo = redisTemplate.opsForValue();
		vo.set("tts", listUsers);
		List<Users> list = (List<Users>) vo.get("tts");
		return list;
	}

}
