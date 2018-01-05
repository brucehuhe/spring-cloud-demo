package org.spring.cloud.order.service.vo;

import java.util.Date;

public class OrderLogistcsVo {
	private String id;

    private String userAddress;

    private String userTel;

    private String logistcsName;

    private String logistcsId;

    private Long sreialNumber;

    private String remark;
    
    private String userId;
    
    private String createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getLogistcsName() {
		return logistcsName;
	}

	public void setLogistcsName(String logistcsName) {
		this.logistcsName = logistcsName;
	}

	public String getLogistcsId() {
		return logistcsId;
	}

	public void setLogistcsId(String logistcsId) {
		this.logistcsId = logistcsId;
	}

	public Long getSreialNumber() {
		return sreialNumber;
	}

	public void setSreialNumber(Long sreialNumber) {
		this.sreialNumber = sreialNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
    
}
