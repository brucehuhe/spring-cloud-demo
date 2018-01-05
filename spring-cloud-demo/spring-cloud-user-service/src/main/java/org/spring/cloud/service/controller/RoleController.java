package org.spring.cloud.service.controller;

import org.spring.cloud.service.service.RoleService;
import org.spring.cloud.service.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@ApiOperation(value = "添加角色", notes = "测试请求")  
	@RequestMapping(value="/saveRole")
	@ResponseBody
	public Result saveRole(String param){
		return roleService.saveRole(param);
	}
	
	@ApiOperation(value = "查询角色", notes = "测试请求")  
	@RequestMapping(value="/findByRole")
	@ResponseBody
	public Result findByRole(String param){
		return roleService.findByRole(param);
	}
	
	@ApiOperation(value = "修改角色", notes = "测试请求")  
	@RequestMapping(value="/updateByRole")
	@ResponseBody
	public Result updateByRole(String param){
		return roleService.updateByRole(param);
	}
	
	@ApiOperation(value = "删除角色", notes = "测试请求")  
	@RequestMapping(value="/delByRole")
	@ResponseBody
	public Result delByRole(String param){
		return roleService.delByRole(param);
	}
	
}
