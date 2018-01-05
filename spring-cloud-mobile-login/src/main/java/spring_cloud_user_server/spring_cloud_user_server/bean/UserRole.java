package spring_cloud_user_server.spring_cloud_user_server.bean;

import java.io.Serializable;

public class UserRole implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2985463183158959470L;

	private String userid;

    private String roleid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid == null ? null : roleid.trim();
    }
}