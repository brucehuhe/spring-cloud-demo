package org.spring.cloud.order.service.vo;

public class OrdersVo {
	private String id; //生成的UUID

	private String orderNumber; //订单号

	private String goodId;  //商品ID

	private String goodName; //商品名称

	private String goodNumber; //商品数量

	private String goodPrice; //单种商品总价格

	private String goodMprice; //商品原价

	private String userId; //购买用户ID

	private String logisticsId; //是否走物流

	private String state;  //状态：1--已支付，2--待发货，3--待收货，4--待评价，5--完成

	private String createTime; //下单时间
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getGoodId() {
		return goodId;
	}

	public void setGoodId(String goodId) {
		this.goodId = goodId;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public String getGoodNumber() {
		return goodNumber;
	}

	public void setGoodNumber(String goodNumber) {
		this.goodNumber = goodNumber;
	}

	public String getGoodPrice() {
		return goodPrice;
	}

	public void setGoodPrice(String goodPrice) {
		this.goodPrice = goodPrice;
	}

	public String getGoodMprice() {
		return goodMprice;
	}

	public void setGoodMprice(String goodMprice) {
		this.goodMprice = goodMprice;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLogisticsId() {
		return logisticsId;
	}

	public void setLogisticsId(String logisticsId) {
		this.logisticsId = logisticsId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
