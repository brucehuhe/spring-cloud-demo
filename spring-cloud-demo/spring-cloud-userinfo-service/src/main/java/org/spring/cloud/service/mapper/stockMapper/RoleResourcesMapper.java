package org.spring.cloud.service.mapper.stockMapper;

import java.util.List;

import org.spring.cloud.service.model.stockPo.RoleResourcesKey;

public interface RoleResourcesMapper {
    int deleteByPrimaryKey(RoleResourcesKey key);

    int insert(RoleResourcesKey record);

    int insertSelective(RoleResourcesKey record);

	List<RoleResourcesKey> findByRoleId(String roleid);

	int deleteByRoleId(String roleid);
}