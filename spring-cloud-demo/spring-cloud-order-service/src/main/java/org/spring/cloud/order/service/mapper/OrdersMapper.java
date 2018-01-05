package org.spring.cloud.order.service.mapper;

import java.util.List;

import org.spring.cloud.order.service.model.Orders;

public interface OrdersMapper {
    int deleteByPrimaryKey(String id);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

	Orders selectByOrderNumber(String orderNumber);

	List<Orders> selectByOrderNumbers(String orderNumber);
}