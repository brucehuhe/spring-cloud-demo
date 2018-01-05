package org.spring.cloud.service.controller;

import java.util.List;

import org.spring.cloud.service.model.rxshopPo.Users;
import org.spring.cloud.service.model.stockPo.User;
import org.spring.cloud.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

/**
 * 用户访问层 ，swagger2 访问地址：http://localhost:8080/swagger-ui.html
 * @author Administrator
 *
 */
@RestController
public class UserController {
	
	@Autowired
	private UserService userService ;
	
//	@Autowired
//    RedisTemplate redisTemplate;
//    
//    @Autowired
//    StringRedisTemplate stringRedisTemplate;
	
	@ApiOperation(value = "获取库存用户", notes = "简单的请求")  
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public List<User> readUserInfo(){
		List<User> ls=userService.getUser();		
		return ls;
	}
	
	@ApiOperation(value = "获取rxshop用户", notes = "简单的请求")  
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public List<Users> readUsersInfo(){
		List<Users> ls=userService.getUsers();		
		return ls;
	}

}
