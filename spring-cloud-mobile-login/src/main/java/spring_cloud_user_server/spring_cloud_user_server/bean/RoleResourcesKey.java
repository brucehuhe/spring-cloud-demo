package spring_cloud_user_server.spring_cloud_user_server.bean;

import java.io.Serializable;

public class RoleResourcesKey implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7332180547662498058L;

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