package com.bean;

import java.sql.Timestamp;

/**
 * 活动审核结果表
 */

public class PCaseAudit implements java.io.Serializable {

	// Fields

	private Integer id;//主键id
	private Integer caseId;//活动ID
	private Integer auditResult;//审核结果,0-表示"退回"，1-表示"同意"
	private String auditText;//审核意见
	private Timestamp sysDt;//审核时间
	private Integer sysUserId;//审核用户ID

	// Constructors

	/** default constructor */
	public PCaseAudit() {
	}

	/** minimal constructor */
	public PCaseAudit(Integer id, Integer caseId) {
		this.id = id;
		this.caseId = caseId;
	}

	/** full constructor */
	public PCaseAudit(Integer id, Integer caseId, Integer auditResult,
			String auditText, Timestamp sysDt, Integer sysUserId) {
		this.id = id;
		this.caseId = caseId;
		this.auditResult = auditResult;
		this.auditText = auditText;
		this.sysDt = sysDt;
		this.sysUserId = sysUserId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCaseId() {
		return this.caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public Integer getAuditResult() {
		return this.auditResult;
	}

	public void setAuditResult(Integer auditResult) {
		this.auditResult = auditResult;
	}

	public String getAuditText() {
		return this.auditText;
	}

	public void setAuditText(String auditText) {
		this.auditText = auditText;
	}

	public Timestamp getSysDt() {
		return this.sysDt;
	}

	public void setSysDt(Timestamp sysDt) {
		this.sysDt = sysDt;
	}

	public Integer getSysUserId() {
		return this.sysUserId;
	}

	public void setSysUserId(Integer sysUserId) {
		this.sysUserId = sysUserId;
	}

}