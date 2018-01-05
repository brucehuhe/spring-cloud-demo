package spring_cloud_user_server.spring_cloud_user_server.mapper;

import spring_cloud_user_server.spring_cloud_user_server.bean.UserRole;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);
}