package org.spring.cloud.service.mapper.rxshopMapper;

import java.util.List;

import org.spring.cloud.service.model.rxshopPo.Users;

public interface UsersMapper {
    int deleteByPrimaryKey(String id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

	List<Users> selectAll();
}