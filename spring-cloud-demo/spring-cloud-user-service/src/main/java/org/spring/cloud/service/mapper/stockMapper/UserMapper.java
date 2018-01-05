package org.spring.cloud.service.mapper.stockMapper;

import org.spring.cloud.service.model.stockPo.User;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	User selectByUser(User user);

	User selectByUserName(String username);
}