package spring_cloud_user_server.spring_cloud_user_server.service;

import spring_cloud_user_server.spring_cloud_user_server.util.PageUtil;
import spring_cloud_user_server.spring_cloud_user_server.util.Result;

public interface UserManageService {
	
	Result saveOrUpdateUser(String param);
	
	Result deleteUser(String param);
	
	Result findByConditionUser(String param,PageUtil page);

}
