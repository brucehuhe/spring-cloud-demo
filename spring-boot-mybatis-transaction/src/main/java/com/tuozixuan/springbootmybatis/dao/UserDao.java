package com.tuozixuan.springbootmybatis.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
	
	/**
	 * 更新用户名称
	 * 
	 * @param paramMap 参数Map,id-主键 name-名称
	 * @return int 更新结果
	 */
	int updateName(Map<String, Object> paramMap);
}
