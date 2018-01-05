package org.spring.cloud.order.service.service;

import org.spring.cloud.order.service.util.Result;

public interface OrderService {
	/**
	 * 保存订单商品
	 * @author brucehu
	 * @param param
	 * @return
	 * Result
	 * 2017年12月19日
	 */
	public Result createOrders(String param);
	/**
	 * 存在物流时的地址
	 * @author brucehu
	 * @param param
	 * @return
	 * Result
	 * 2017年12月19日
	 */
	public Result saveLocgister(String param);
	/**
	 * 订单收货确认
	 * @author brucehu
	 * @param param
	 * @return
	 * Result
	 * 2017年12月21日
	 */
	public Result receiveOrders(String param);
	/**
	 * 评论
	 * @author brucehu
	 * @param param
	 * @return
	 * Result
	 * 2017年12月21日
	 */
	public Result pengCommentOrders(String param);
}
