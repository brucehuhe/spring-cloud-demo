package org.spring.cloud.order.service.mapper;

import org.spring.cloud.order.service.model.OrderLogistcs;
import org.spring.cloud.order.service.vo.OrderLogistcsVo;

public interface OrderLogistcsMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrderLogistcs record);

    int insertSelective(OrderLogistcs record);

    OrderLogistcs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderLogistcs record);

    int updateByPrimaryKey(OrderLogistcs record);

	OrderLogistcs selectByAddress(OrderLogistcs orderLogistcs);
}