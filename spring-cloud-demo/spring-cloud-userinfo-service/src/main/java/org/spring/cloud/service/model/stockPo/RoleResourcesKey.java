package org.spring.cloud.service.model.stockPo;

import java.io.Serializable;

public class RoleResourcesKey implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3858681791840843379L;

	private String roleid;

    private String resourcesid;

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }

    public String getResourcesid() {
        return resourcesid;
    }

    public void setResourcesid(String resourcesid) {
        this.resourcesid = resourcesid == null ? null : resourcesid.trim();
    }
}