package org.spring.cloud.service.model.rxshopPo;

import java.io.Serializable;

public class Users implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1633801526089795507L;

	private String id;

    private String userRankId;

    private String friendId;

    private String pic;

    private String nikename;

    private String name;

    private String idCard;

    private String six;

    private String birthday;

    private String email;

    private String password;

    private String userName;

    private String createTime;

    private String isUsed;

    private String state;

    private String paypasswordType;

    private String paycountType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserRankId() {
        return userRankId;
    }

    public void setUserRankId(String userRankId) {
        this.userRankId = userRankId == null ? null : userRankId.trim();
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId == null ? null : friendId.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getNikename() {
        return nikename;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename == null ? null : nikename.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getSix() {
        return six;
    }

    public void setSix(String six) {
        this.six = six == null ? null : six.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed == null ? null : isUsed.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getPaypasswordType() {
        return paypasswordType;
    }

    public void setPaypasswordType(String paypasswordType) {
        this.paypasswordType = paypasswordType == null ? null : paypasswordType.trim();
    }

    public String getPaycountType() {
        return paycountType;
    }

    public void setPaycountType(String paycountType) {
        this.paycountType = paycountType == null ? null : paycountType.trim();
    }
}