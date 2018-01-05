package spring_cloud_user_server.spring_cloud_user_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.sf.json.JSONObject;
import spring_cloud_user_server.spring_cloud_user_server.service.UserManageService;
import spring_cloud_user_server.spring_cloud_user_server.util.PageUtil;
import spring_cloud_user_server.spring_cloud_user_server.util.Result;

@RestController
@CrossOrigin
public class UserManageController {
	
	@Autowired
	private UserManageService userManageService;
	
	@RequestMapping(value="/saveOrUpdateUser")
	@ResponseBody
	public Result saveOrUpdateUser(String param){
		return userManageService.saveOrUpdateUser(param);
	}
	
	@RequestMapping(value="/deleteUser")
	@ResponseBody
	public Result deleteUser(String param){
		return userManageService.deleteUser(param);
	}
	
	@RequestMapping(value="/findByConditionUser")
	@ResponseBody
	public Result findByConditionUser(String param){
		PageUtil page = (PageUtil) JSONObject.toBean(JSONObject.fromObject(param), PageUtil.class);
		return userManageService.findByConditionUser(param,page);
	}

}
