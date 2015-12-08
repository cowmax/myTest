package com.bean;

import java.sql.Timestamp;

/**
 * ParaCaseP entity. @author MyEclipse Persistence Tools
 */

public class ParaCaseP implements java.io.Serializable {

	private String caseCode;//活动编码
	private String caseName;//活动名称
	public Store chalCd;//渠道/店铺
	private String caseLevel;//活动级别
	private Integer preNum;//活动前向影响时间
	private String brde;//品牌，取值为A：AMII， 取值为R：Redefined
	private Integer num;//参加活动的产品/产品SKU 的<缺省数量>
	private String CType;//活动的选款粒度，取值为P：按<款>选择参加活动的产品，取值S：按SKU 选择参加活动的产品
	private String sysUserId;//当前编辑用户ID
	private Timestamp sysDt;//数据记录保存（更新）时间
	private Integer OFlag;

	public ParaCaseP() {
		super();
	}

	public ParaCaseP(String caseCode, String caseName, Store chalCd,
			String caseLevel, Integer preNum, String brde, Integer num,
			String cType, String sysUserId, Timestamp sysDt, Integer oFlag) {
		super();
		this.caseCode = caseCode;
		this.caseName = caseName;
		this.chalCd = chalCd;
		this.caseLevel = caseLevel;
		this.preNum = preNum;
		this.brde = brde;
		this.num = num;
		CType = cType;
		this.sysUserId = sysUserId;
		this.sysDt = sysDt;
		OFlag = oFlag;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public Store getChalCd() {
		return chalCd;
	}

	public void setChalCd(Store chalCd) {
		this.chalCd = chalCd;
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

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getCType() {
		return CType;
	}

	public void setCType(String cType) {
		CType = cType;
	}

	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}

	public Timestamp getSysDt() {
		return sysDt;
	}

	public void setSysDt(Timestamp sysDt) {
		this.sysDt = sysDt;
	}

	public Integer getOFlag() {
		return OFlag;
	}

	public void setOFlag(Integer oFlag) {
		OFlag = oFlag;
	}

}