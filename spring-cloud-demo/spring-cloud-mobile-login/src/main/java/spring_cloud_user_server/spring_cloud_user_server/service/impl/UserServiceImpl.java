package spring_cloud_user_server.spring_cloud_user_server.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring_cloud_user_server.spring_cloud_user_server.bean.Resources;
import spring_cloud_user_server.spring_cloud_user_server.bean.User;
import spring_cloud_user_server.spring_cloud_user_server.mapper.ResourcesMapper;
import spring_cloud_user_server.spring_cloud_user_server.mapper.UserMapper;
import spring_cloud_user_server.spring_cloud_user_server.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private ResourcesMapper resourcesMapper;

	@Override
	public User findByUsername(String username) {
		return userMapper.findByUsername(username);
	}

	@Override
	public List<Resources> getResource(User user) {
		Map<String,String> map = new HashMap<String,String>();
		if(user.getId().equals("1")) {
			map.put("userid", null);
			return resourcesMapper.findByUser(map);
		}else {
			map.put("userid", user.getId());
			return resourcesMapper.findByUser(map);
		}
	}
}
