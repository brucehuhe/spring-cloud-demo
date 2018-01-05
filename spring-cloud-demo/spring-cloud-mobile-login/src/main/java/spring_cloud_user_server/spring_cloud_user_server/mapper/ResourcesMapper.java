package spring_cloud_user_server.spring_cloud_user_server.mapper;

import java.util.List;
import java.util.Map;

import spring_cloud_user_server.spring_cloud_user_server.bean.Resources;

public interface ResourcesMapper {
    int deleteByPrimaryKey(String id);

    int insert(Resources record);

    int insertSelective(Resources record);

    Resources selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Resources record);

    int updateByPrimaryKey(Resources record);

//	List<Resources> findByUser(String id);

	List<Resources> findByUser(Map<String, String> map);
}