package com.bean;

import java.sql.Timestamp;

/**
 * ParaCaseP entity. @author MyEclipse Persistence Tools
 */

public class ParaCaseP implements java.io.Serializable {

	private String caseCode;// �����
	private String caseName;// ����
	public Store chalCd;// ����/����
	private String caseLevel;// �����
	private Integer preNum;// �ǰ��Ӱ��ʱ��
	private String brde;// Ʒ�ƣ�ȡֵΪA��AMII�� ȡֵΪR��Redefined
	private Integer num;// �μӻ�Ĳ�Ʒ/��ƷSKU ��<ȱʡ����>
	private String CType;// ���ѡ�����ȣ�ȡֵΪP����<��>ѡ��μӻ�Ĳ�Ʒ��ȡֵS����SKU
							// ѡ��μӻ�Ĳ�Ʒ
	private String sysUserId;// ��ǰ�༭�û�ID
	private Timestamp sysDt;// ��ݼ�¼���棨���£�ʱ��
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