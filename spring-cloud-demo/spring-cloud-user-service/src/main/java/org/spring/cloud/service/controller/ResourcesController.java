package org.spring.cloud.service.controller;

import org.spring.cloud.service.service.ResourcesService;
import org.spring.cloud.service.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
/**
 * 权限资源文件控制层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
public class ResourcesController {
	
	@Autowired
	private ResourcesService resourcesService;
	
	@ApiOperation(value = "添加资源", notes = "测试请求")  
	@RequestMapping(value="/saveResource")
	@ResponseBody
	public Result saveResource(String param){
		return resourcesService.saveResource(param);
	}
	
	@ApiOperation(value = "修改资源", notes = "测试请求")  
	@RequestMapping(value="/updateResource")
	@ResponseBody
	public Result updateResource(String param){
		return resourcesService.updateResource(param);
	}
	
	@ApiOperation(value = "删除资源", notes = "测试请求")  
	@RequestMapping(value="/delResource")
	@ResponseBody
	public Result delResource(String param){
		return resourcesService.delResource(param);
	}
	
	@ApiOperation(value = "根据用户ID查询对应的资源列表及权限按钮", notes = "测试请求")  
	@RequestMapping(value="/findResourcesByUserId")
	@ResponseBody
	public Result findResourcesByUserId(String param){
		return resourcesService.findResourcesByUserId(param);
	}
	
	@ApiOperation(value = "查询对应的用户权限", notes = "测试请求")  
	@RequestMapping(value="/findResourcesByRole")
	@ResponseBody
	public Result findResourcesByRole(String param){
		return resourcesService.findResourcesByRole(param);
	}
	
	@ApiOperation(value = "查询资源文件", notes = "测试请求")  
	@RequestMapping(value="/findResourcesByType")
	@ResponseBody
	public Result findResourcesByType(String param){
		return resourcesService.findResourcesByType(param);
	}
	
	@ApiOperation(value = "查询按钮", notes = "测试请求")  
	@RequestMapping(value="/selectBtnByResource")
	@ResponseBody
	public Result selectBtnByResource(String param){
		return resourcesService.selectBtnByResource(param);
	}
}
