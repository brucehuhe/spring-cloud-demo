package org.spring.cloud.order.service.model;

import java.io.Serializable;
import java.util.Date;

public class PrintDevice implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5872767315151983951L;

	private String id;

    private String printSn;

    private Date createTime;

    private String state;
    
    private String storeId;
    
    private String printWin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPrintSn() {
        return printSn;
    }

    public void setPrintSn(String printSn) {
        this.printSn = printSn == null ? null : printSn.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getPrintWin() {
		return printWin;
	}

	public void setPrintWin(String printWin) {
		this.printWin = printWin;
	}
}