package com.bean;

import java.sql.Timestamp;

public class RefactorParaDt {
	private Integer caseId; // �id
	private String caseName; // ����
	private Timestamp caseSt; // ���ʼʱ��
	private Timestamp caseEt; // �����ʱ��
	private Timestamp sysDt; // �޸�ʱ��
	private String sysUserId; // �����û�
	private Integer status; // �״̬
	private Double ratioNew; // ���¿�ռ��
	private Integer num; // ������Ʒ����
	private String name; // ����
	private String caseLevel; // �����
	private Integer preNum; // �ο�����
	private String brde; // Ʒ��
	private String caseDesc; // �˵��

	public RefactorParaDt() {
		super();
	}

	public RefactorParaDt(Integer caseId, String caseName, Timestamp caseSt,
			Timestamp caseEt, Timestamp sysDt, String sysUserId,
			Integer status, Double ratioNew, Integer num, String name,
			String caseLevel, Integer preNum, String brde, String caseDesc) {
		super();
		this.caseId = caseId;
		this.caseName = caseName;
		this.caseSt = caseSt;
		this.caseEt = caseEt;
		this.sysDt = sysDt;
		this.sysUserId = sysUserId;
		this.status = status;
		this.ratioNew = ratioNew;
		this.num = num;
		this.name = name;
		this.caseLevel = caseLevel;
		this.preNum = preNum;
		this.brde = brde;
		this.caseDesc = caseDesc;
	}

	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public Timestamp getCaseSt() {
		return caseSt;
	}

	public void setCaseSt(Timestamp caseSt) {
		this.caseSt = caseSt;
	}

	public Timestamp getCaseEt() {
		return caseEt;
	}

	public void setCaseEt(Timestamp caseEt) {
		this.caseEt = caseEt;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getRatioNew() {
		return ratioNew;
	}

	public void setRatioNew(Double ratioNew) {
		this.ratioNew = ratioNew;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCaseLevel() {
		return caseLevel;
	}

	public void setCaseLevel(String caseLevel) {
		this.caseLevel = caseLevel;
	}

	public Integer getPreNum() {
		return preNum;
	}

	public void setPreNum(Integer preNum) {
		this.preNum = preNum;
	}

	public String getBrde() {
		return brde;
	}

	public void setBrde(String brde) {
		this.brde = brde;
	}

	public String getCaseDesc() {
		return caseDesc;
	}

	public void setCaseDesc(String caseDesc) {
		this.caseDesc = caseDesc;
	}

}
