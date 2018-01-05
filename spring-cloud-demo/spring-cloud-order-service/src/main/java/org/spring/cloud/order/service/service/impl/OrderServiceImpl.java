package org.spring.cloud.order.service.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.cloud.order.service.mapper.OrderLogistcsMapper;
import org.spring.cloud.order.service.mapper.OrdersMapper;
import org.spring.cloud.order.service.mapper.PrintDeviceMapper;
import org.spring.cloud.order.service.mapper.UserCommentMapper;
import org.spring.cloud.order.service.model.OrderLogistcs;
import org.spring.cloud.order.service.model.Orders;
import org.spring.cloud.order.service.model.PrintDevice;
import org.spring.cloud.order.service.model.UserComment;
import org.spring.cloud.order.service.service.OrderService;
import org.spring.cloud.order.service.util.PrinterServer;
import org.spring.cloud.order.service.util.Result;
import org.spring.cloud.order.service.util.Tools;
import org.spring.cloud.order.service.vo.OrderLogistcsVo;
import org.spring.cloud.order.service.vo.OrdersVo;
import org.spring.cloud.order.service.vo.UserCommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	// 保存日志
	private Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrdersMapper ordersMapper;

	@Autowired
	private OrderLogistcsMapper orderLogistcsMapper;

	@Autowired
	private UserCommentMapper userCommentMapper;
	
	// 调用数据库打印机接口
	@Autowired
	private PrintDeviceMapper printDeviceMapper;

	/**
	 * 创建部分订单
	 */
	@Override
	public Result createOrders(String param) {
		Result rs = new Result();
		String str = UUID.randomUUID().toString().replace("-", ""); // 生成主键ID
		// 将数据转化成json对象
		JSONObject json = JSONObject.fromObject(param);
		// 保存订单入库并返回成功或失败
		String strs = saveOrder(json, str);
		if (strs == "") {
			rs.setState(1);
			rs.setMessage("订单生成成功.");
			rs.setObj("订单生成成功.");
			log.info("=================订单生成成功.===============");
		} else {
			rs.setState(0);
			rs.setMessage("订单生成失败.");
			rs.setObj(param + "********失败");
			log.info("=================订单生成失败.===============");
		}
		return rs;
	}

	/**
	 * 保存订单数据入库
	 * 
	 * @author brucehu
	 * @param json
	 * @return int 2017年12月19日
	 */
	@SuppressWarnings({ "deprecation", "rawtypes" })
	private String saveOrder(JSONObject json, String id) {
		String rs = "";
		// 生成订单号
		String orderNumber = getOrderNumber();
		// 获取对象中的数组
		List listMDB = JSONArray.toList(JSONArray.fromObject(JSONArray.fromObject(json.get("orders"))));
		for (int i = 0; i < listMDB.size(); i++) {
			MorphDynaBean mdb = (MorphDynaBean) listMDB.get(i);
			Orders orders = new Orders();
			orders.setId(id);
			orders.setCreateTime(new Tools().getSortDate(new Date()));
			orders.setState("2"); // 状态：1--待收货，2--确认收货，3--待评价，4--完成
			// 前端传入的值进行处理
			orders.setGoodId(mdb.get("goodsId").toString());
			orders.setGoodName(mdb.get("goodName").toString());
			orders.setGoodNumber(Long.parseLong(mdb.get("goodNumber").toString()));
			orders.setStoreId(mdb.get("storeId").toString());
			// Inventory inventory = inventoryMapper.selectByPrimaryKey(orders.getGoodId());
			// //查询库存数量是否小于用户购买数量,这种情况页面可以直接处理
			// if(inventory.getStoreNum() <
			// Long.parseLong(mdb.get("goodNumber").toString())) {
			// rs += "["+mdb.get("goodName").toString() + "]," ; //将失败的商品名称返回给用户
			// continue ; //下一个循环
			// }
			orders.setGoodPrice(Float.parseFloat(mdb.get("goodPrice").toString()));
			if (mdb.get("logisticsId") == null) {
				orders.setOrderNumber(orderNumber);
			} else {
				// 生成单独的物流订单号
				orders.setOrderNumber(orderNumber + i);
				orders.setLogisticsId(mdb.get("logisticsId").toString());
			}
			int rss = ordersMapper.insertSelective(orders);
			if (rss == 0) {
				rs += "[" + mdb.get("goodName").toString() + "],"; // 将失败的商品名称返回给用户
				continue; // 下一个循环
			}
			// System.out.println(mdb.get("goodId"));
		}
		if (rs != "") {
			rs += "商品下单失败，请重新下单.";
		} else {
			// 调用打印机打印
			List<Orders> list = ordersMapper.selectByOrderNumbers(orderNumber);
			//查询对应的网络打印机SN
			
			String str = getPrinter(list);
			log.info("*****************调用打印机结果： " + str);
		}
		return rs;
	}

	/**
	 * 异步连接打印机打印
	 * 
	 * @author brucehu
	 * @param list
	 * @return String 2017年12月20日
	 */
	@Async
	private String getPrinter(List<Orders> list) {
		//查询可用打印机
		List<PrintDevice> listPrintDevice = printDeviceMapper.selectByStatePrint();
		if(listPrintDevice != null) {
			for(int i=0;i<listPrintDevice.size();i++) {
				PrinterServer ps = new PrinterServer(); //创建打印机联接打印
			}
		}
		return null;
	}

	/**
	 * 生成订单号
	 * 
	 * @author brucehu
	 * @return String 2017年12月19日
	 */
	private String getOrderNumber() {
		// 获取当前时间精确到毫秒
		String substring = (System.currentTimeMillis() + "").substring(7);
		// 产生六位数的随机数
		String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
		// 订单编号由时间加随机数组成
		String orderNumber = (substring + code);
		return orderNumber;
	}

	/**
	 * 存在物流时的地址
	 */
	@Override
	public Result saveLocgister(String param) {
		Result rs = new Result();
		String str = UUID.randomUUID().toString().replace("-", ""); // 生成主键ID
		// 将json数据转化成对象
		OrderLogistcsVo orderLogistcsVo = (OrderLogistcsVo) JSONObject.toBean(JSONObject.fromObject(param),
				OrderLogistcsVo.class);
		OrderLogistcs ol = new OrderLogistcs();
		ol.setUserAddress(orderLogistcsVo.getUserAddress());
		ol.setUserId(orderLogistcsVo.getUserId());
		ol.setUserTel(orderLogistcsVo.getUserTel());
		OrderLogistcs olv = orderLogistcsMapper.selectByAddress(ol);
		if (olv == null) {
			ol.setId(str);
			ol.setLogistcsId(orderLogistcsVo.getLogistcsId());
			ol.setLogistcsName(orderLogistcsVo.getLogistcsName());
			ol.setRemark(orderLogistcsVo.getRemark());
			// ol.setSreialNumber(Long.parseLong(orderLogistcsVo.getSreialNumber().toString()));
			ol.setCreateTime(new Tools().getSortDate(new Date()));
			int i = orderLogistcsMapper.insertSelective(ol);
			if (i == 1) {
				rs.setState(1);
				rs.setMessage("物流地址保存成功.");
				rs.setObj(ol);
				log.info("=================物流地址保存成功.===============");
			} else {
				rs.setState(0);
				rs.setMessage("物流地址保存成功.");
				rs.setObj(param + "********失败");
				log.info("=================物流地址保存失败.===============");
			}
		} else {
			olv.setCreateTime(new Tools().getSortDate(new Date()));
			olv.setUserAddress(orderLogistcsVo.getUserAddress());
			olv.setUserTel(orderLogistcsVo.getUserTel());
			olv.setUserId(orderLogistcsVo.getUserId());
			int i = orderLogistcsMapper.updateByPrimaryKeySelective(olv);
			if (i == 1) {
				rs.setState(1);
				rs.setMessage("物流地址修改成功.");
				rs.setObj(olv);
				log.info("=================物流地址保存成功.===============");
			} else {
				rs.setState(0);
				rs.setMessage("物流地址修改失败.");
				rs.setObj(param + "********失败");
				log.info("=================物流地址修改失败.===============");
			}
		}
		return rs;
	}

	/**
	 * 收货确认
	 */
	@Override
	public Result receiveOrders(String param) {
		Result rs = new Result();
		OrdersVo order = (OrdersVo) JSONObject.toBean(JSONObject.fromObject(param), OrdersVo.class);
		Orders orders = ordersMapper.selectByOrderNumber(order.getOrderNumber());
		orders.setState("3"); // 状态：1--待收货，2--确认收货，3--待评价，4--完成
		orders.setUpdateTime(new Tools().getSortDate(new Date()));
		int i = ordersMapper.updateByPrimaryKeySelective(orders);
		if (i == 1) {
			rs.setState(1);
			rs.setMessage("已收货.");
			rs.setObj("已收货.");
			log.info("=================已收货成功.===============");
		} else {
			rs.setState(0);
			rs.setMessage("已收货返回失败.");
			rs.setObj(param + "********失败");
			log.info("=================已收货返回失败.===============");
		}
		return rs;
	}

	/**
	 * 用户评论 UserComment userCommentMapper
	 */
	@Override
	public Result pengCommentOrders(String param) {
		Result rs = new Result();
		UserCommentVo userCommentVo = (UserCommentVo) JSONObject.toBean(JSONObject.fromObject(param),
				UserCommentVo.class);
		UserComment uc = new UserComment();
		uc.setComment(userCommentVo.getComment());
		uc.setCreateTime(new Tools().getSortDate(new Date()));
		uc.setOrderNumber(userCommentVo.getOrderNumber());
		uc.setPicPath(userCommentVo.getPicPath());
		uc.setState("1");
		uc.setUserId(userCommentVo.getUserId());
		int i = userCommentMapper.insertSelective(uc);
		if (i == 1) {
			rs.setState(1);
			rs.setMessage("评论成功.");
			rs.setObj("评论成功.");
			log.info("=================评论成功.===============");
		} else {
			rs.setState(0);
			rs.setMessage("评论失败.");
			rs.setObj(param + "********失败");
			log.info("=================评论失败.===============");
		}
		return rs;
	}

}
