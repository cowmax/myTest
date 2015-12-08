package com.bean;

import java.sql.Timestamp;

/**
 * PMenu entity. @author MyEclipse Persistence Tools
 */

public class PMenu implements java.io.Serializable {

	// Fields

	private String mid;
	private String pmid;
	private String mname;
	private String murl;
	private Timestamp sysDt;
	private String sysUserId;
	private PUser userId;
	private PGroup groupId;

	// Constructors

	/** default constructor */
	public PMenu() {
	}

	/** minimal constructor */
	public PMenu(String mid, String mname) {
		this.mid = mid;
		this.mname = mname;
	}

	/** full constructor */
	public PMenu(String mid, String pmid, String mname, String murl,
			Timestamp sysDt, String sysUserId) {
		this.mid = mid;
		this.pmid = pmid;
		this.mname = mname;
		this.murl = murl;
		this.sysDt = sysDt;
		this.sysUserId = sysUserId;
	}

	// Property accessors

	public String getMid() {
		return this.mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getPmid() {
		return this.pmid;
	}

	public void setPmid(String pmid) {
		this.pmid = pmid;
	}

	public String getMname() {
		return this.mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMurl() {
		return this.murl;
	}

	public void setMurl(String murl) {
		this.murl = murl;
	}

	public Timestamp getSysDt() {
		return this.sysDt;
	}

	public void setSysDt(Timestamp sysDt) {
		this.sysDt = sysDt;
	}

	public String getSysUserId() {
		return this.sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}

	public PUser getUserId() {
		return userId;
	}

	public void setUserId(PUser userId) {
		this.userId = userId;
	}

	public PGroup getGroupId() {
		return groupId;
	}

	public void setGroupId(PGroup groupId) {
		this.groupId = groupId;
	}

}