package com.bean;

import java.sql.Timestamp;
import java.util.Set;

/**
 * ParaSordata entity. @author MyEclipse Persistence Tools
 */
public class ParaSordata implements java.io.Serializable {

	// Fields

	private ParaSordataId id;
	private Double valueRatio;
	private Double valueMin;
	private Double valueMax;
	private String valueDesc;
	private Timestamp sysDt;
	private String sysUserId;

	/** default constructor */
	public ParaSordata() {
	}

	/** minimal constructor */
	public ParaSordata(ParaSordataId id) {
		this.id = id;
	}

	/** full constructor */
	public ParaSordata(ParaSordataId id, Double valueRatio, Double valueMin,
			Double valueMax, String valueDesc, Timestamp sysDt, String sysUserId) {
		this.id = id;
		this.valueRatio = valueRatio;
		this.valueMin = valueMin;
		this.valueMax = valueMax;
		this.valueDesc = valueDesc;
		this.sysDt = sysDt;
		this.sysUserId = sysUserId;
	}

	// Property accessors

	public ParaSordataId getId() {
		return this.id;
	}

	public void setId(ParaSordataId id) {
		this.id = id;
	}

	public Double getValueRatio() {
		return this.valueRatio;
	}

	public void setValueRatio(Double valueRatio) {
		this.valueRatio = valueRatio;
	}

	public Double getValueMin() {
		return this.valueMin;
	}

	public void setValueMin(Double valueMin) {
		this.valueMin = valueMin;
	}

	public Double getValueMax() {
		return this.valueMax;
	}

	public void setValueMax(Double valueMax) {
		this.valueMax = valueMax;
	}

	public String getValueDesc() {
		return this.valueDesc;
	}

	public void setValueDesc(String valueDesc) {
		this.valueDesc = valueDesc;
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

}