package org.spring.cloud.service.mapper.stockMapper;

import org.spring.cloud.service.model.stockPo.Role;

import com.github.pagehelper.Page;

public interface RoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

	void deleteByUserRole(String id);

	void deleteByRoleResources(String id);

	Page<Role> findByPageRole(String roleDesc);

	Role findByName(String roledesc);
}