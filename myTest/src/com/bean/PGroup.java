package com.bean;

import java.sql.Timestamp;

/**
 * PGroup entity. @author MyEclipse Persistence Tools
 */

public class PGroup implements java.io.Serializable {

	// Fields

	private Integer groupId;
	private String groupName;
	private String groupDesc;
	private PRole roleId;
	private Timestamp createDt;
	private Timestamp lastDt;

	public PGroup() {
		super();
	}

	public PGroup(Integer groupId, String groupName, String groupDesc,
			PRole roleId, Timestamp createDt, Timestamp lastDt) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.groupDesc = groupDesc;
		this.roleId = roleId;
		this.createDt = createDt;
		this.lastDt = lastDt;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public PRole getRoleId() {
		return roleId;
	}

	public void setRoleId(PRole roleId) {
		this.roleId = roleId;
	}

	public Timestamp getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Timestamp createDt) {
		this.createDt = createDt;
	}

	public Timestamp getLastDt() {
		return lastDt;
	}

	public void setLastDt(Timestamp lastDt) {
		this.lastDt = lastDt;
	}

}