package com.pfasc.web.db.domain;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * @version 1.1 $
 * @author cg48910 $
 *
 */

@XmlRootElement
public class Login {

	private int userid;
	private String loginName;
	private boolean valid;
	private int roleid;
	private String userName;
	private String roleDesc;
	
	/**
	 * 
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return Returns the loginName.
	 */
	public String getLoginName() {
		return loginName;
	}
	/**
	 * @param loginName The loginName to set.
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**
	 * @return Returns the roleid.
	 */
	public int getRoleid() {
		return roleid;
	}
	/**
	 * @param roleid The roleid to set.
	 */
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	/**
	 * @return Returns the userid.
	 */
	public int getUserid() {
		return userid;
	}
	/**
	 * @param userid The userid to set.
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}
	/**
	 * @return Returns the valid.
	 */
	public boolean isValid() {
		return valid;
	}
	/**
	 * @param valid The valid to set.
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	/**
	 * @return Returns the userName.
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName The userName to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * @return Returns the roleDesc.
	 */
	public String getRoleDesc() {
		return roleDesc;
	}
	/**
	 * @param roleDesc The roleDesc to set.
	 */
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
}

