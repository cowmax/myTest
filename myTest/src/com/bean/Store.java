package com.bean;

import java.sql.Timestamp;

/**
 * Store entity. @author MyEclipse Persistence Tools
 */

public class Store implements java.io.Serializable {

	// Fields

	private String storeId;
	private Timestamp recordDate;
	private Integer isLocked;
	public String code;
	private String name;
	private Integer status;
	private Integer sourceType;
	private Integer storeProperty;
	private String companyId;
	private String companyName;
	private String companyCode;
	private Integer isPush;

	public Store() {
		super();
	}

	public Store(String code) {
		super();
		this.code = code;
	}

	public Store(String storeId, Timestamp recordDate, Integer isLocked,
			String code, String name, Integer status, Integer sourceType,
			Integer storeProperty, String companyId, String companyName,
			String companyCode, Integer isPush) {
		super();
		this.storeId = storeId;
		this.recordDate = recordDate;
		this.isLocked = isLocked;
		this.code = code;
		this.name = name;
		this.status = status;
		this.sourceType = sourceType;
		this.storeProperty = storeProperty;
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyCode = companyCode;
		this.isPush = isPush;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public Timestamp getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Timestamp recordDate) {
		this.recordDate = recordDate;
	}

	public Integer getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(Integer isLocked) {
		this.isLocked = isLocked;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	public Integer getStoreProperty() {
		return storeProperty;
	}

	public void setStoreProperty(Integer storeProperty) {
		this.storeProperty = storeProperty;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public Integer getIsPush() {
		return isPush;
	}

	public void setIsPush(Integer isPush) {
		this.isPush = isPush;
	}

}