package com.bean;

/**
 * ParaDtSSku entity. @author MyEclipse Persistence Tools
 */

public class ParaDtSSku implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer caseId;
	private String productCode;
	private String colo;
	private String cona;
	private String szid;
	private String skuCode;
	private Integer status;
	private Double salesNum;
	private Double stock;
	private String newOldFlag;
	private String SCaseAll;

	// Constructors

	/** default constructor */
	public ParaDtSSku() {
	}

	/** minimal constructor */
	public ParaDtSSku(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public ParaDtSSku(Integer id, Integer caseId, String productCode,
			String colo, String cona, String szid, String skuCode,
			Integer status, Double salesNum, Double stock, String newOldFlag,
			String SCaseAll) {
		this.id = id;
		this.caseId = caseId;
		this.productCode = productCode;
		this.colo = colo;
		this.cona = cona;
		this.szid = szid;
		this.skuCode = skuCode;
		this.status = status;
		this.salesNum = salesNum;
		this.stock = stock;
		this.newOldFlag = newOldFlag;
		this.SCaseAll = SCaseAll;
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

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getColo() {
		return this.colo;
	}

	public void setColo(String colo) {
		this.colo = colo;
	}

	public String getCona() {
		return this.cona;
	}

	public void setCona(String cona) {
		this.cona = cona;
	}

	public String getSzid() {
		return this.szid;
	}

	public void setSzid(String szid) {
		this.szid = szid;
	}

	public String getSkuCode() {
		return this.skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getSalesNum() {
		return this.salesNum;
	}

	public void setSalesNum(Double salesNum) {
		this.salesNum = salesNum;
	}

	public Double getStock() {
		return this.stock;
	}

	public void setStock(Double stock) {
		this.stock = stock;
	}

	public String getNewOldFlag() {
		return this.newOldFlag;
	}

	public void setNewOldFlag(String newOldFlag) {
		this.newOldFlag = newOldFlag;
	}

	public String getSCaseAll() {
		return this.SCaseAll;
	}

	public void setSCaseAll(String SCaseAll) {
		this.SCaseAll = SCaseAll;
	}

}