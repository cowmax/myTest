package com.bean;

/**
 * ParaDtS entity. @author MyEclipse Persistence Tools
 */

public class ParaDtS implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer caseId;
	private BProductP productCd;
	private Integer status;
	private Double avgAmt;
	private Double stock;
	private String newOldFlag;
	private String SCaseAll;
	private String colo;
	private String cona;
	
	public ParaDtS() {
		super();
	}
	public ParaDtS(Integer id, Integer caseId, BProductP productCd,
			Integer status, Double avgAmt, Double stock, String newOldFlag,
			String sCaseAll, String colo, String cona) {
		super();
		this.id = id;
		this.caseId = caseId;
		this.productCd = productCd;
		this.status = status;
		this.avgAmt = avgAmt;
		this.stock = stock;
		this.newOldFlag = newOldFlag;
		SCaseAll = sCaseAll;
		this.colo = colo;
		this.cona = cona;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCaseId() {
		return caseId;
	}
	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}
	public BProductP getProductCd() {
		return productCd;
	}
	public void setProductCd(BProductP productCd) {
		this.productCd = productCd;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Double getAvgAmt() {
		return avgAmt;
	}
	public void setAvgAmt(Double avgAmt) {
		this.avgAmt = avgAmt;
	}
	public Double getStock() {
		return stock;
	}
	public void setStock(Double stock) {
		this.stock = stock;
	}
	public String getNewOldFlag() {
		return newOldFlag;
	}
	public void setNewOldFlag(String newOldFlag) {
		this.newOldFlag = newOldFlag;
	}
	public String getSCaseAll() {
		return SCaseAll;
	}
	public void setSCaseAll(String sCaseAll) {
		SCaseAll = sCaseAll;
	}
	public String getColo() {
		return colo;
	}
	public void setColo(String colo) {
		this.colo = colo;
	}
	public String getCona() {
		return cona;
	}
	public void setCona(String cona) {
		this.cona = cona;
	}

}