package spring_cloud_user_server.spring_cloud_user_server.mapper;

import spring_cloud_user_server.spring_cloud_user_server.bean.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}