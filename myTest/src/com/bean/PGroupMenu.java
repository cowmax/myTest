package com.bean;

/**
 * PGroupMenu entity. @author MyEclipse Persistence Tools
 */

public class PGroupMenu implements java.io.Serializable {

	// Fields

	private PGroupMenuId id;

	// Constructors

	/** default constructor */
	public PGroupMenu() {
	}

	/** full constructor */
	public PGroupMenu(PGroupMenuId id) {
		this.id = id;
	}

	// Property accessors

	public PGroupMenuId getId() {
		return this.id;
	}

	public void setId(PGroupMenuId id) {
		this.id = id;
	}

}