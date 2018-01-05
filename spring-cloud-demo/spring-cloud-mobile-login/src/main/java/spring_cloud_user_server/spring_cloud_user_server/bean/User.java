package spring_cloud_user_server.spring_cloud_user_server.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1716933014353771023L;

	private String id;

    private String username;

    private String password;

    private Integer enable;

    private Date loginTime;

    private String isUse;

    private String loginState;

    private String userPic;
    
    private List<Resources> list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse == null ? null : isUse.trim();
    }

    public String getLoginState() {
        return loginState;
    }

    public void setLoginState(String loginState) {
        this.loginState = loginState == null ? null : loginState.trim();
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic == null ? null : userPic.trim();
    }

	public List<Resources> getList() {
		return list;
	}

	public void setList(List<Resources> list) {
		this.list = list;
	}
}