package org.spring.cloud.order.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.cloud.order.service.service.OrderService;
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
public class OrderController {
	
	private Logger log = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 已付款后订单
	 * @author brucehu
	 * @param param
	 * @return
	 * Result
	 * 2017年12月18日
	 */
	@ApiOperation(value = "生成订单入库", notes = "简单的请求", consumes = "http://localhost:8085/swagger-ui.html")
	@RequestMapping(value = "/createOrders", method = RequestMethod.POST)
	public Result createOrders(@RequestParam String param) {
		return orderService.createOrders(param);
	}
	
	/**
	 * 收货确认
	 * @author brucehu
	 * @param param
	 * @return
	 * Result
	 * 2017年12月18日
	 */
	@ApiOperation(value = "收货确认", notes = "简单的请求", consumes = "http://localhost:8085/swagger-ui.html")
	@RequestMapping(value = "/receiveOrders", method = RequestMethod.POST)
	public Result receiveOrders(@RequestParam String param) {
		return orderService.receiveOrders(param);
	}
	
	/**
	 * 评论确认
	 * @author brucehu
	 * @param param
	 * @return
	 * Result
	 * 2017年12月18日
	 */
	@ApiOperation(value = "评论确认", notes = "简单的请求", consumes = "http://localhost:8085/swagger-ui.html")
	@RequestMapping(value = "/pengCommentOrders", method = RequestMethod.POST)
	public Result pengCommentOrders(@RequestParam String param) {
		return orderService.pengCommentOrders(param);
	}
	
	/**
	 * 存在物流时的地址
	 * @author brucehu
	 * @param param
	 * @return
	 * Result
	 * 2017年12月18日
	 */
	@ApiOperation(value = "物流地址入库", notes = "简单的请求", consumes = "http://localhost:8085/swagger-ui.html")
	@RequestMapping(value = "/saveLocgister", method = RequestMethod.POST)
	public Result saveLocgister(@RequestParam String param) {
		log.info(param);
		return orderService.saveLocgister(param);
	}

}
