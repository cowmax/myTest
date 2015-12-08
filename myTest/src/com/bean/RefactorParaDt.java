package com.bean;

import java.sql.Timestamp;

public class RefactorParaDt {
	private Integer caseId;		// 活动id
	private String caseName;	// 活动名称
	private Timestamp caseSt;	// 活动开始时间
	private Timestamp caseEt;	// 活动结束时间
	private Timestamp sysDt;	// 修改时间
	private String sysUserId;	// 操作用户
	private Integer status;		// 活动状态
	private Double ratioNew;	// 是新款占比
	private Integer num;		// 活动参与产品数量
	private String name;		// 渠道
	private String caseLevel;	// 活动级别
	private Integer preNum;		// 参考周期
	private String brde;		// 品牌
	private String caseDesc; 	//活动说明

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
