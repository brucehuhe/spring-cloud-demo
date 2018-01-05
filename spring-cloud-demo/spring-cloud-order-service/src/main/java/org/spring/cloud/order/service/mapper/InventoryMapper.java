package org.spring.cloud.order.service.mapper;

import org.spring.cloud.order.service.model.Inventory;

public interface InventoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(Inventory record);

    int insertSelective(Inventory record);

    Inventory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Inventory record);

    int updateByPrimaryKey(Inventory record);
}