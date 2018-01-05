package org.spring.cloud.service.controller;

import org.spring.cloud.service.service.UserService;
import org.spring.cloud.service.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

/**
 * 用户访问层 ，swagger2 访问地址：http://localhost:8080/swagger-ui.html
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserService userService ;
	
	@ApiOperation(value = "1.1 获取所有超市人员信息 ", notes = "简单的请求")  
	@RequestMapping(value="/findByUserInfos")
	@ResponseBody
	public Result findByUserInfos(String param){
		return userService.findByUserInfos(param);	
	}
	
	@ApiOperation(value = "1.2 新增超市人员信息", notes = "简单的请求")  
	@RequestMapping(value="/saveUserInfo")
	@ResponseBody
	public Result saveUserInfo(String param){
		return userService.saveUserInfo(param);		
	}

    
	@ApiOperation(value = "1.3 根据Id变更超市人员信息", notes = "简单的请求")  
	@RequestMapping(value="/updateUserInfo")
	@ResponseBody
	public Result updateUserInfo(String param){
		return userService.updateUserInfo(param)	;		
	}
	
	@ApiOperation(value = "1.4 根据Id删除超市人员信息", notes = "简单的请求")  
	@RequestMapping(value="/deleteUserInfoById")
	@ResponseBody
	public Result deleteUserInfoById(String param){
		return userService.deleteUserInfoById(param);
	}
	
	@ApiOperation(value = "1.5 添加分配用户角色关联", notes = "简单的请求")  
	@RequestMapping(value="/saveUserRole")
	@ResponseBody
	public Result saveUserRole(String param){
		return userService.saveUserRole(param);
	}
	
	@ApiOperation(value = "1.5 添加分配用户角色资源关联", notes = "简单的请求")  
	@RequestMapping(value="/saveUserRoleResource")
	@ResponseBody
	public Result saveUserRoleResource(String param){
		return userService.saveUserRoleResource(param);
	}
	
	@ApiOperation(value = "1.6 查询分配用户角色资源关联", notes = "简单的请求")  
	@RequestMapping(value="/findxUserRoleByUserId")
	@ResponseBody
	public Result findxUserRoleByUserId(String param){
		return userService.findxUserRoleByUserId(param);
	}
	
	@ApiOperation(value = "1.6 查询角色资源关联", notes = "简单的请求")  
	@RequestMapping(value="/findxByRoleResources")
	@ResponseBody
	public Result findxByRoleResources(String param){
		return userService.findxByRoleResources(param);
	}

}
