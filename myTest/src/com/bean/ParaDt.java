package com.bean;

import java.sql.Timestamp;
import java.util.Date;

/**
 * ParaDt entity. @author MyEclipse Persistence Tools
 */

public class ParaDt implements java.io.Serializable {

	// Fields

	private Integer caseId;
	private String caseName;
	private String caseDesc;
	private Timestamp caseSt;
	private Timestamp caseEt;
	private Timestamp sysDt;
	private String sysUserId;
	private Integer status;
	private String caseCode;
	private Double ratioNew;
	private Integer num;
	private Date orgCaseSt;
	private Date orgCaseEt;

	// Constructors

	/** default constructor */
	public ParaDt() {
	}

	/** minimal constructor */
	public ParaDt(Integer caseId) {
		this.caseId = caseId;
	}

	/** full constructor */
	public ParaDt(Integer caseId, String caseName, String caseDesc,
			Timestamp caseSt, Timestamp caseEt, Timestamp sysDt,
			String sysUserId, Integer status, String caseCode, Double ratioNew,
			Integer num, Date orgCaseSt, Date orgCaseEt) {
		this.caseId = caseId;
		this.caseName = caseName;
		this.caseDesc = caseDesc;
		this.caseSt = caseSt;
		this.caseEt = caseEt;
		this.sysDt = sysDt;
		this.sysUserId = sysUserId;
		this.status = status;
		this.caseCode = caseCode;
		this.ratioNew = ratioNew;
		this.num = num;
		this.orgCaseSt = orgCaseSt;
		this.orgCaseEt = orgCaseEt;
	}

	// Property accessors

	public Integer getCaseId() {
		return this.caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public String getCaseName() {
		return this.caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getCaseDesc() {
		return this.caseDesc;
	}

	public void setCaseDesc(String caseDesc) {
		this.caseDesc = caseDesc;
	}

	public Timestamp getCaseSt() {
		return this.caseSt;
	}

	public void setCaseSt(Timestamp caseSt) {
		this.caseSt = caseSt;
	}

	public Timestamp getCaseEt() {
		return this.caseEt;
	}

	public void setCaseEt(Timestamp caseEt) {
		this.caseEt = caseEt;
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

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCaseCode() {
		return this.caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public Double getRatioNew() {
		return this.ratioNew;
	}

	public void setRatioNew(Double ratioNew) {
		this.ratioNew = ratioNew;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Date getOrgCaseSt() {
		return this.orgCaseSt;
	}

	public void setOrgCaseSt(Date orgCaseSt) {
		this.orgCaseSt = orgCaseSt;
	}

	public Date getOrgCaseEt() {
		return this.orgCaseEt;
	}

	public void setOrgCaseEt(Date orgCaseEt) {
		this.orgCaseEt = orgCaseEt;
	}

}