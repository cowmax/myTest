package com.bean;

import java.sql.Timestamp;

/**
 * ParaSysValueP entity. @author MyEclipse Persistence Tools
 */

public class ParaSysValueP implements java.io.Serializable {

	// Fields

	private String tyna;
	private Integer offDay;
	private Integer reNum;
	private Double ownerRatio;
	private String sysUserId;
	private Timestamp sysDt;

	// Constructors

	/** default constructor */
	public ParaSysValueP() {
	}

	/** minimal constructor */
	public ParaSysValueP(String tyna) {
		this.tyna = tyna;
	}

	/** full constructor */
	public ParaSysValueP(String tyna, Integer offDay, Integer reNum,
			Double ownerRatio, String sysUserId, Timestamp sysDt) {
		this.tyna = tyna;
		this.offDay = offDay;
		this.reNum = reNum;
		this.ownerRatio = ownerRatio;
		this.sysUserId = sysUserId;
		this.sysDt = sysDt;
	}

	// Property accessors

	public String getTyna() {
		return this.tyna;
	}

	public void setTyna(String tyna) {
		this.tyna = tyna;
	}

	public Integer getOffDay() {
		return this.offDay;
	}

	public void setOffDay(Integer offDay) {
		this.offDay = offDay;
	}

	public Integer getReNum() {
		return this.reNum;
	}

	public void setReNum(Integer reNum) {
		this.reNum = reNum;
	}

	public Double getOwnerRatio() {
		return this.ownerRatio;
	}

	public void setOwnerRatio(Double ownerRatio) {
		this.ownerRatio = ownerRatio;
	}

	public String getSysUserId() {
		return this.sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}

	public Timestamp getSysDt() {
		return this.sysDt;
	}

	public void setSysDt(Timestamp sysDt) {
		this.sysDt = sysDt;
	}

}