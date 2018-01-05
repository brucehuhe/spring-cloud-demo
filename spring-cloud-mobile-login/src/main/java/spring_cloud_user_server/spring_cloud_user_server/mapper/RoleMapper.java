package spring_cloud_user_server.spring_cloud_user_server.mapper;

import java.util.Map;

import com.github.pagehelper.Page;

import spring_cloud_user_server.spring_cloud_user_server.bean.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(String[] strings);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

	Page<Role> findRoleByCondition(Map<String, String> map);
}