package org.spring.cloud.service.model.stockPo;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7715609251806387920L;

	private String id;

    private String roledesc;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc == null ? null : roledesc.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}