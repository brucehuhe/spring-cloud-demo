package com.tuozixuan.springbootmybatis.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tuozixuan.springbootmybatis.dao.UserDao;
import com.tuozixuan.springbootmybatis.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Transactional(rollbackFor=Exception.class)
	public void updateName() {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", 1);
		paramMap.put("name", "tuozixuan4");
		userDao.updateName(paramMap);
		
		paramMap = new HashMap<String, Object>();
		paramMap.put("id", 1);
		paramMap.put("name", "tuozixuan5");
		userDao.updateName(paramMap);
		int i = 1/0;
	}

}
