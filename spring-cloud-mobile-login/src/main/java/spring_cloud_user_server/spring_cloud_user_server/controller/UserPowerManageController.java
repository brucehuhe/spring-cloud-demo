package spring_cloud_user_server.spring_cloud_user_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.sf.json.JSONObject;
import spring_cloud_user_server.spring_cloud_user_server.service.UserPowerManageService;
import spring_cloud_user_server.spring_cloud_user_server.util.PageUtil;
import spring_cloud_user_server.spring_cloud_user_server.util.Result;

@RestController
@CrossOrigin
public class UserPowerManageController {
	
	@Autowired
	private UserPowerManageService userPowerManageService;
	
	@RequestMapping(value="/saveOrUpdateRole")
	@ResponseBody
	public Result saveOrUpdateRole(String param){
		return userPowerManageService.saveOrUpdateRole(param);
	}
	
	@RequestMapping(value="/deleteByRoleId")
	@ResponseBody
	public Result deleteByRoleId(String param){
		return userPowerManageService.deleteByRoleId(param);
	}
	
	@RequestMapping(value="/findRoleByCondition")
	@ResponseBody
	public Result findRoleByCondition(String param){
		PageUtil page = (PageUtil) JSONObject.toBean(JSONObject.fromObject(param), PageUtil.class);
		return userPowerManageService.findRoleByCondition(param,page);
	}


}
