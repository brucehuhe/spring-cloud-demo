package spring_cloud_user_server.spring_cloud_user_server.service;

import spring_cloud_user_server.spring_cloud_user_server.util.PageUtil;
import spring_cloud_user_server.spring_cloud_user_server.util.Result;

public interface UserPowerManageService {
	/**保存修改角色**/
	Result saveOrUpdateRole(String param);
	/**删除角色**/
	Result deleteByRoleId(String param);
	/**根据条件查询角色**/
	Result findRoleByCondition(String param,PageUtil page);
	/**给用户分配角色**/
	Result saveUserRole(String param);
	/**保存修改资源**/
	Result saveOrUpdateResource(String param);
	/**删除资源**/
	Result deleteByResourceId(String param);
	/**根据条件查询对应资源**/
	Result findResourceByCondition(String param,PageUtil page);
	/**给角色分配资源**/
	Result saveRoleResource(String param);

}
