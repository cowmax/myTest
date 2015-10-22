package com.bean;

/**
 * PRole entity. @author MyEclipse Persistence Tools
 */

public class PRole implements java.io.Serializable {

	// Fields

	private Integer roleId;
	private String roleName;
	private String roleDesc;

	// Constructors

	/** default constructor */
	public PRole() {
	}

	/** full constructor */
	public PRole(String roleName, String roleDesc) {
		this.roleName = roleName;
		this.roleDesc = roleDesc;
	}

	// Property accessors

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

}