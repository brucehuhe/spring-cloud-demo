package org.spring.cloud.service.mapper.stockMapper;

import org.spring.cloud.service.model.stockPo.UserRole;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);

	int deleteByUserId(String userid);

	UserRole findByUserRole(String userid);
}