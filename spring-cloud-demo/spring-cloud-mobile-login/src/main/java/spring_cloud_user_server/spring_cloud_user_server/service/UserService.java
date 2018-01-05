package spring_cloud_user_server.spring_cloud_user_server.service;

import java.util.List;

import spring_cloud_user_server.spring_cloud_user_server.bean.Resources;
import spring_cloud_user_server.spring_cloud_user_server.bean.User;

public interface UserService {
	
	/**通过username查找用户信息;*/
	User findByUsername(String username);
	/**查询资源文件**/
	List<Resources> getResource(User user);
	
}
