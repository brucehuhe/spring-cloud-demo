package org.spring.cloud.order.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.cloud.order.service.service.PrintDeviceService;
import org.spring.cloud.order.service.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

/**
 * 订单控制访问层
 * @author Administrator
 * 2017年12月18日
 */
@RestController
public class PrintServerController {
	
	private Logger log = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private PrintDeviceService printDeviceService;
	
	/**
	 * 新增打印设备
	 * @author brucehu
	 * @param param
	 * @return
	 * Result
	 * 2017年12月18日
	 */
	@ApiOperation(value = "新增", notes = "简单的请求", consumes = "http://localhost:8085/swagger-ui.html")
	@RequestMapping(value = "/savePrintDevice", method = RequestMethod.POST)
	public Result savePrintDevice(@RequestParam String param) {
		log.info(param);
		return printDeviceService.savePrintDevice(param);
	}
	/**
	 * 修改打印设备
	 * @author brucehu
	 * @param param
	 * @return
	 * Result
	 * 2017年12月18日
	 */
	@ApiOperation(value = "修改", notes = "简单的请求", consumes = "http://localhost:8085/swagger-ui.html")
	@RequestMapping(value = "/updatePrintDevice", method = RequestMethod.POST)
	public Result updatePrintDevice(@RequestParam String param) {
		log.info(param);
		return printDeviceService.updatePrintDevice(param);
	}
	/**
	 * 删除打印设备
	 * @author brucehu
	 * @param param
	 * @return
	 * Result
	 * 2017年12月18日
	 */
	@ApiOperation(value = "删除", notes = "简单的请求", consumes = "http://localhost:8085/swagger-ui.html")
	@RequestMapping(value = "/deletePrintDevice", method = RequestMethod.POST)
	public Result deletePrintDevice(@RequestParam String param) {
		log.info(param);
		return printDeviceService.deletePrintDevice(param);
	}
	/**
	 * 查询打印设备
	 * @author brucehu
	 * @param param
	 * @return
	 * Result
	 * 2017年12月18日
	 */
	@ApiOperation(value = "查询", notes = "简单的请求", consumes = "http://localhost:8085/swagger-ui.html")
	@RequestMapping(value = "/findPrintDevice", method = RequestMethod.POST)
	public Result findPrintDevice(@RequestParam String param) {
		log.info(param);
		return printDeviceService.findPrintDevice(param);
	}
}
