package spring_cloud_user_server.spring_cloud_user_server.mapper;

import java.util.Map;

import com.github.pagehelper.Page;

import spring_cloud_user_server.spring_cloud_user_server.bean.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(String[] id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

	Page<UserInfo> findUserInfoByCondition(Map<String, String> map);
    
    
}