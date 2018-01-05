package org.spring.cloud.order.service.mapper;

import java.util.List;

import org.spring.cloud.order.service.model.PrintDevice;

public interface PrintDeviceMapper {
    int deleteByPrimaryKey(String id);

    int insert(PrintDevice record);

    int insertSelective(PrintDevice record);

    PrintDevice selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PrintDevice record);

    int updateByPrimaryKey(PrintDevice record);

	PrintDevice selectBySn(String printSn);

	List<PrintDevice> findByPrintDeviceList(PrintDevice pdv);

	List<PrintDevice> selectByStatePrint();
}