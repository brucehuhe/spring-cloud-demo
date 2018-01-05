package org.spring.cloud.order.service.mapper;

import org.spring.cloud.order.service.model.UserComment;

public interface UserCommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserComment record);

    int insertSelective(UserComment record);

    UserComment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserComment record);

    int updateByPrimaryKey(UserComment record);
}