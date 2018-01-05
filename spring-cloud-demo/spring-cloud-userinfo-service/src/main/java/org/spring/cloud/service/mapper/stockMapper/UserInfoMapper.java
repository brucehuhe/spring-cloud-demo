package org.spring.cloud.service.mapper.stockMapper;

import java.util.Map;

import org.spring.cloud.service.model.stockPo.UserInfo;

import com.github.pagehelper.Page;

public interface UserInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

	Page<UserInfo> findByUsers(UserInfo ui);

	UserInfo selectByUser(Map<String, String> map);

	int deleteByUserRole(String id);
}