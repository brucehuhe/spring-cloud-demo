package org.spring.cloud.service.model.stockPo;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2784698525484724699L;

	private String id;

    private String name;

    private String sex;

    private String conInfo;

    private String code;

    private String state;

    private Date createTime;

    private String userId;
    
    private String storeId;
    
    private String cnName;

    public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId == null ? null : storeId.trim();
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName == null ? null : cnName.trim();
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getConInfo() {
        return conInfo;
    }

    public void setConInfo(String conInfo) {
        this.conInfo = conInfo == null ? null : conInfo.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}