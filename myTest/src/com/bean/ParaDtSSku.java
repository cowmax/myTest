package com.bean;

/**
 * ParaDtSSku entity. @author MyEclipse Persistence Tools
 */

public class ParaDtSSku implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer caseId;
	private BProductP productCode;
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

	public ParaDtSSku(Integer id, Integer caseId, BProductP productCode,
			String colo, String cona, String szid, String skuCode,
			Integer status, Double salesNum, Double stock, String newOldFlag,
			String sCaseAll) {
		super();
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
		SCaseAll = sCaseAll;
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

	public BProductP getProductCode() {
		return productCode;
	}

	public void setProductCode(BProductP productCode) {
		this.productCode = productCode;
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

	public String getSzid() {
		return szid;
	}

	public void setSzid(String szid) {
		this.szid = szid;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getSalesNum() {
		return salesNum;
	}

	public void setSalesNum(Double salesNum) {
		this.salesNum = salesNum;
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

}