package com.bean;

import java.sql.Timestamp;

/**
 * ����ʵ����
 */

public class PCaseAudit implements java.io.Serializable {

	// Fields

	private Integer id;// ����
	private Integer caseId;// �ID
	private Integer auditResult;// ��˽��0-��ʾ"�˻�"��1-��ʾ"ͬ��"
	private String auditText;// ������
	private Timestamp sysDt;// ���ʱ��
	private String sysUserId;// ����û�ID

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
			String auditText, Timestamp sysDt, String sysUserId) {
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

	public String getSysUserId() {
		return this.sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}

}