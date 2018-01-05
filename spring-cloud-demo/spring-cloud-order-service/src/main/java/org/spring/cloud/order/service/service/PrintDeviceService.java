package org.spring.cloud.order.service.service;

import org.spring.cloud.order.service.util.Result;

public interface PrintDeviceService {
	/**
	 * 新增打印设备
	 * @author brucehu
	 * @param param
	 * @return
	 * Result
	 * 2017年12月18日
	 */
	public Result savePrintDevice(String param);
	/**
	 * 修改打印设备
	 * @author brucehu
	 * @param param
	 * @return
	 * Result
	 * 2017年12月18日
	 */
	public Result updatePrintDevice(String param);
	/**
	 * 删除打印设备
	 * @author brucehu
	 * @param param
	 * @return
	 * Result
	 * 2017年12月18日
	 */
	public Result deletePrintDevice(String param);
	/**
	 * 查询打印设备
	 * @author brucehu
	 * @param param
	 * @return
	 * Result
	 * 2017年12月18日
	 */
	public Result findPrintDevice(String param);
}
