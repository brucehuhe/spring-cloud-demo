package org.spring.cloud.service.mapper.stockMapper;

import java.util.List;

import org.spring.cloud.service.model.stockPo.User;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	List<User> selectAll();
}