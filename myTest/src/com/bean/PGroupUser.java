package com.bean;

/**
 * PGroupUser entity. @author MyEclipse Persistence Tools
 */

public class PGroupUser implements java.io.Serializable {

	// Fields

	private Integer id;
	private PGroup groupId;
	private PUser userId;

	// Constructors

	/** default constructor */
	public PGroupUser() {
	}

	public PGroupUser(Integer id, PGroup groupId, PUser userId) {
		super();
		this.id = id;
		this.groupId = groupId;
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PGroup getGroupId() {
		return groupId;
	}

	public void setGroupId(PGroup groupId) {
		this.groupId = groupId;
	}

	public PUser getUserId() {
		return userId;
	}

	public void setUserId(PUser userId) {
		this.userId = userId;
	}

	
}