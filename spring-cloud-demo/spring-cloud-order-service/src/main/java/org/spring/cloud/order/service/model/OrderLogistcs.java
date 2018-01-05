package org.spring.cloud.order.service.model;

import java.util.Date;

public class OrderLogistcs {
    private String id;

    private String userAddress;

    private String userTel;

    private String logistcsName;

    private String logistcsId;

    private Long sreialNumber;

    private String remark;

    private String userId;

    private Date createTime;

    private String attr3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel == null ? null : userTel.trim();
    }

    public String getLogistcsName() {
        return logistcsName;
    }

    public void setLogistcsName(String logistcsName) {
        this.logistcsName = logistcsName == null ? null : logistcsName.trim();
    }

    public String getLogistcsId() {
        return logistcsId;
    }

    public void setLogistcsId(String logistcsId) {
        this.logistcsId = logistcsId == null ? null : logistcsId.trim();
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
        this.remark = remark == null ? null : remark.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAttr3() {
        return attr3;
    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3 == null ? null : attr3.trim();
    }
}