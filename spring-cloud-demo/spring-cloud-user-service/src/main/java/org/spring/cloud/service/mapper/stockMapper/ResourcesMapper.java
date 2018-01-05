package org.spring.cloud.service.mapper.stockMapper;

import java.util.List;
import java.util.Map;

import org.spring.cloud.service.model.stockPo.Resources;

import com.github.pagehelper.Page;

public interface ResourcesMapper {
    int deleteByPrimaryKey(String id);

    int insert(Resources record);

    int insertSelective(Resources record);

    Resources selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Resources record);

    int updateByPrimaryKey(Resources record);

	Page<Resources> selectResourceBtnByUserId(Map<String,String> map);

	String selectBtnByResource(Map<String,String> map);

	Page<Resources> selectResourceByAdmin(Map<String, String> map);

	void deleteByRoleResource(String id);

	Resources findByName(Resources rc);

	List<Resources> selectResourceBtnByType(Map<String, String> map);

	String findResourcesByRoleId(Map<String,String> map);
	
	List<Resources> selectBtnByUser(Map<String,String> map);
	
	List<Resources> selectBtnByAdmin(Map<String,String> map);

	List<Resources> selectResourceBtnByTypes(Map<String, String> map);

	int deleteByParentId(String str);
	
}