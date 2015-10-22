package com.bean;

import java.sql.Timestamp;

/**
 * PUser entity. @author MyEclipse Persistence Tools
 */

public class PUser implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String userName;
	private String userPwd;
	private String userDesc;
	private Timestamp createDt;
	private Timestamp lastDt;

	// Constructors

	/** default constructor */
	public PUser() {
	}

	public PUser(String userId, String userName, String userPwd,
			String userDesc, Timestamp createDt, Timestamp lastDt) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userDesc = userDesc;
		this.createDt = createDt;
		this.lastDt = lastDt;
	}



	// Property accessors

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return this.userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserDesc() {
		return this.userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public Timestamp getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public Timestamp getLastDt() {
		return this.lastDt;
	}

	public void setLastDt(Timestamp lastDt) {
		this.lastDt = lastDt;
	}

}