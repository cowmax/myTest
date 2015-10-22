package com.bean;

import java.sql.Timestamp;

/**
 * BProductP entity. @author MyEclipse Persistence Tools
 */

public class BProductP implements java.io.Serializable {

	private String productCode;
	private String sena;
	private String spno;
	private String tyna;
	private String brde;
	private String twpr;
	private Timestamp jhdt;
	private Timestamp xjdt;
	private Double planQty;
	private Double doNum;
	private Integer prodCycle;
	private Integer isNew;
	private String productCd;
	private Integer flag;
	private Integer flag2;
	private String spno2;
	private String brdeFlag;
	private Double lspr;
	private Double txnPrice;
	private Integer activeFlag;
	private String focusFlag;
	
	public BProductP() {
		super();
	}
	public BProductP(String productCode, String sena, String spno, String tyna,
			String brde, String twpr, Timestamp jhdt, Timestamp xjdt,
			Double planQty, Double doNum, Integer prodCycle, Integer isNew,
			String productCd, Integer flag, Integer flag2, String spno2,
			String brdeFlag, Double lspr, Double txnPrice, Integer activeFlag,
			String focusFlag) {
		super();
		this.productCode = productCode;
		this.sena = sena;
		this.spno = spno;
		this.tyna = tyna;
		this.brde = brde;
		this.twpr = twpr;
		this.jhdt = jhdt;
		this.xjdt = xjdt;
		this.planQty = planQty;
		this.doNum = doNum;
		this.prodCycle = prodCycle;
		this.isNew = isNew;
		this.productCd = productCd;
		this.flag = flag;
		this.flag2 = flag2;
		this.spno2 = spno2;
		this.brdeFlag = brdeFlag;
		this.lspr = lspr;
		this.txnPrice = txnPrice;
		this.activeFlag = activeFlag;
		this.focusFlag = focusFlag;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getSena() {
		return sena;
	}
	public void setSena(String sena) {
		this.sena = sena;
	}
	public String getSpno() {
		return spno;
	}
	public void setSpno(String spno) {
		this.spno = spno;
	}
	public String getTyna() {
		return tyna;
	}
	public void setTyna(String tyna) {
		this.tyna = tyna;
	}
	public String getBrde() {
		return brde;
	}
	public void setBrde(String brde) {
		this.brde = brde;
	}
	public String getTwpr() {
		return twpr;
	}
	public void setTwpr(String twpr) {
		this.twpr = twpr;
	}
	public Timestamp getJhdt() {
		return jhdt;
	}
	public void setJhdt(Timestamp jhdt) {
		this.jhdt = jhdt;
	}
	public Timestamp getXjdt() {
		return xjdt;
	}
	public void setXjdt(Timestamp xjdt) {
		this.xjdt = xjdt;
	}
	public Double getPlanQty() {
		return planQty;
	}
	public void setPlanQty(Double planQty) {
		this.planQty = planQty;
	}
	public Double getDoNum() {
		return doNum;
	}
	public void setDoNum(Double doNum) {
		this.doNum = doNum;
	}
	public Integer getProdCycle() {
		return prodCycle;
	}
	public void setProdCycle(Integer prodCycle) {
		this.prodCycle = prodCycle;
	}
	public Integer getIsNew() {
		return isNew;
	}
	public void setIsNew(Integer isNew) {
		this.isNew = isNew;
	}
	public String getProductCd() {
		return productCd;
	}
	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Integer getFlag2() {
		return flag2;
	}
	public void setFlag2(Integer flag2) {
		this.flag2 = flag2;
	}
	public String getSpno2() {
		return spno2;
	}
	public void setSpno2(String spno2) {
		this.spno2 = spno2;
	}
	public String getBrdeFlag() {
		return brdeFlag;
	}
	public void setBrdeFlag(String brdeFlag) {
		this.brdeFlag = brdeFlag;
	}
	public Double getLspr() {
		return lspr;
	}
	public void setLspr(Double lspr) {
		this.lspr = lspr;
	}
	public Double getTxnPrice() {
		return txnPrice;
	}
	public void setTxnPrice(Double txnPrice) {
		this.txnPrice = txnPrice;
	}
	public Integer getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}
	public String getFocusFlag() {
		return focusFlag;
	}
	public void setFocusFlag(String focusFlag) {
		this.focusFlag = focusFlag;
	}
	

}