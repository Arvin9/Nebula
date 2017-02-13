package site.nebulas.beans;


import java.io.Serializable;

/**
 * @author CaiHonghui
 * @since 20160808
 */
public class User implements Serializable {
    private String userId;
    private String userAccount;
    private String password;
    private String salt;
    private String addTime;
    private Integer isLock;
    
    public String getCredentialsSalt() {
        return userAccount + salt;
    }
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Integer getIsLock() {
		return isLock;
	}
	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}
	
}
