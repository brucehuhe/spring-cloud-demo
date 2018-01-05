package org.spring.cloud.service.service;

import org.spring.cloud.service.util.Result;

public interface UserService {

	Result findByUserInfos(String param);

	Result saveUserInfo(String param);

	Result updateUserInfo(String param);

	Result deleteUserInfoById(String param);

	Result saveUserRole(String param);

	Result saveUserRoleResource(String param);

	Result findxUserRoleByUserId(String param);

	Result findxByRoleResources(String param);
}
