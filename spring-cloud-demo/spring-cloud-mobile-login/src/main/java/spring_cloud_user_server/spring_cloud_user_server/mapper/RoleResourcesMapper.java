package spring_cloud_user_server.spring_cloud_user_server.mapper;

import spring_cloud_user_server.spring_cloud_user_server.bean.RoleResourcesKey;

public interface RoleResourcesMapper {
    int deleteByPrimaryKey(RoleResourcesKey key);

    int insert(RoleResourcesKey record);

    int insertSelective(RoleResourcesKey record);
}