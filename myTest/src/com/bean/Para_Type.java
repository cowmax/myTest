package com.bean;

import java.sql.Timestamp;

public class Para_Type {
	private String valueType;
	private String tyna;
	private String valueTypeName;
	private Double valueRatio;
	private Double valueMin;
	private Double valueMax;
	private String valueDesc;
	private Timestamp sysDt;
	private String sysUserId;

	public Para_Type() {
		super();
	}

	public Para_Type(String valueType, String tyna, String valueTypeName,
			Double valueRatio, Double valueMin, Double valueMax,
			String valueDesc, Timestamp sysDt, String sysUserId) {
		super();
		this.valueType = valueType;
		this.tyna = tyna;
		this.valueTypeName = valueTypeName;
		this.valueRatio = valueRatio;
		this.valueMin = valueMin;
		this.valueMax = valueMax;
		this.valueDesc = valueDesc;
		this.sysDt = sysDt;
		this.sysUserId = sysUserId;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public String getTyna() {
		return tyna;
	}

	public void setTyna(String tyna) {
		this.tyna = tyna;
	}

	public String getValueTypeName() {
		return valueTypeName;
	}

	public void setValueTypeName(String valueTypeName) {
		this.valueTypeName = valueTypeName;
	}

	public Double getValueRatio() {
		return valueRatio;
	}

	public void setValueRatio(Double valueRatio) {
		this.valueRatio = valueRatio;
	}

	public Double getValueMin() {
		return valueMin;
	}

	public void setValueMin(Double valueMin) {
		this.valueMin = valueMin;
	}

	public Double getValueMax() {
		return valueMax;
	}

	public void setValueMax(Double valueMax) {
		this.valueMax = valueMax;
	}

	public String getValueDesc() {
		return valueDesc;
	}

	public void setValueDesc(String valueDesc) {
		this.valueDesc = valueDesc;
	}

	public Timestamp getSysDt() {
		return sysDt;
	}

	public void setSysDt(Timestamp sysDt) {
		this.sysDt = sysDt;
	}

	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}

}
