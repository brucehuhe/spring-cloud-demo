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
	
	/**
	 * 保存仓库用户信息
	 */
	@ApiOperation(value = "保存仓库用户信息", notes = "测试请求")  
	@RequestMapping(value="/saveUser")
	@ResponseBody
	public Result saveUser(String param){
//		String para = "";
//		try {
//			para = URLDecoder.decode(param, "utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		para = para.substring(6, para.length());
		return userService.saveUser(param);		
	}
	
	/**
	 * 仓库用户登录信息
	 */
	@ApiOperation(value = "仓库用户登录信息", notes = "测试请求")  
	@RequestMapping(value="/loginUser")
	@ResponseBody
	public Result loginUser(String param){
//		String para = "";
//		try {
//			para = URLDecoder.decode(param, "utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		para = para.substring(6, para.length());
		return userService.loginUser(param);	
	}
	
	/**
	 * 仓库用户退出登录
	 */
	@ApiOperation(value = "仓库用户退出登录", notes = "测试请求")  
	@RequestMapping(value="/loginOut")
	@ResponseBody
	public Result loginOut(String param){
//		String para = "";
//		try {
//			para = URLDecoder.decode(param, "utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		para = para.substring(6, para.length());
		return userService.loginOut(param);	
	}
	
	/**
	 * 仓库用户重新设置密码
	 */
	@ApiOperation(value = "仓库用户重新设置密码", notes = "测试请求")  
	@RequestMapping(value="/setPassword")
	@ResponseBody
	public Result setPassword(String param){
//		String para = "";
//		try {
//			para = URLDecoder.decode(param, "utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		para = para.substring(6, para.length());
		return userService.setPassword(param);	
	}

}
