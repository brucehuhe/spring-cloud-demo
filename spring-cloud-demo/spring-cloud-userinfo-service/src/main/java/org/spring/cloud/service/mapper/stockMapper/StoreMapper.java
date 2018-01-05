package org.spring.cloud.service.mapper.stockMapper;

import org.spring.cloud.service.model.stockPo.Store;

import com.github.pagehelper.Page;

public interface StoreMapper {
    int deleteByPrimaryKey(String id);

    int insert(Store record);

    int insertSelective(Store record);

    Store selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Store record);

    int updateByPrimaryKey(Store record);

	Page<Store> findByStore(Store store);
}