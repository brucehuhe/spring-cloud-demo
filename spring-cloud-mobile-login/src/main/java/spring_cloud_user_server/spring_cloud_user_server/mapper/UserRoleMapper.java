package spring_cloud_user_server.spring_cloud_user_server.mapper;

import java.util.List;

import spring_cloud_user_server.spring_cloud_user_server.bean.UserRole;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);

	List<UserRole> findByUserId(String userid);

	int deleteByUserId(String userid);
}