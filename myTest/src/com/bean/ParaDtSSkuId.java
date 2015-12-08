package com.bean;

/**
 * ParaDtSSkuId entity. @author MyEclipse Persistence Tools
 */

public class ParaDtSSkuId implements java.io.Serializable {

	// Fields

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
	public ParaDtSSkuId() {
	}

	/** full constructor */
	public ParaDtSSkuId(Integer caseId, String productCode, String colo,
			String cona, String szid, String skuCode, Integer status,
			Double salesNum, Double stock, String newOldFlag, String SCaseAll) {
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ParaDtSSkuId))
			return false;
		ParaDtSSkuId castOther = (ParaDtSSkuId) other;

		return ((this.getCaseId() == castOther.getCaseId()) || (this
				.getCaseId() != null && castOther.getCaseId() != null && this
				.getCaseId().equals(castOther.getCaseId())))
				&& ((this.getProductCode() == castOther.getProductCode()) || (this
						.getProductCode() != null
						&& castOther.getProductCode() != null && this
						.getProductCode().equals(castOther.getProductCode())))
				&& ((this.getColo() == castOther.getColo()) || (this.getColo() != null
						&& castOther.getColo() != null && this.getColo()
						.equals(castOther.getColo())))
				&& ((this.getCona() == castOther.getCona()) || (this.getCona() != null
						&& castOther.getCona() != null && this.getCona()
						.equals(castOther.getCona())))
				&& ((this.getSzid() == castOther.getSzid()) || (this.getSzid() != null
						&& castOther.getSzid() != null && this.getSzid()
						.equals(castOther.getSzid())))
				&& ((this.getSkuCode() == castOther.getSkuCode()) || (this
						.getSkuCode() != null && castOther.getSkuCode() != null && this
						.getSkuCode().equals(castOther.getSkuCode())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())))
				&& ((this.getSalesNum() == castOther.getSalesNum()) || (this
						.getSalesNum() != null
						&& castOther.getSalesNum() != null && this
						.getSalesNum().equals(castOther.getSalesNum())))
				&& ((this.getStock() == castOther.getStock()) || (this
						.getStock() != null && castOther.getStock() != null && this
						.getStock().equals(castOther.getStock())))
				&& ((this.getNewOldFlag() == castOther.getNewOldFlag()) || (this
						.getNewOldFlag() != null
						&& castOther.getNewOldFlag() != null && this
						.getNewOldFlag().equals(castOther.getNewOldFlag())))
				&& ((this.getSCaseAll() == castOther.getSCaseAll()) || (this
						.getSCaseAll() != null
						&& castOther.getSCaseAll() != null && this
						.getSCaseAll().equals(castOther.getSCaseAll())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCaseId() == null ? 0 : this.getCaseId().hashCode());
		result = 37
				* result
				+ (getProductCode() == null ? 0 : this.getProductCode()
						.hashCode());
		result = 37 * result
				+ (getColo() == null ? 0 : this.getColo().hashCode());
		result = 37 * result
				+ (getCona() == null ? 0 : this.getCona().hashCode());
		result = 37 * result
				+ (getSzid() == null ? 0 : this.getSzid().hashCode());
		result = 37 * result
				+ (getSkuCode() == null ? 0 : this.getSkuCode().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37 * result
				+ (getSalesNum() == null ? 0 : this.getSalesNum().hashCode());
		result = 37 * result
				+ (getStock() == null ? 0 : this.getStock().hashCode());
		result = 37
				* result
				+ (getNewOldFlag() == null ? 0 : this.getNewOldFlag()
						.hashCode());
		result = 37 * result
				+ (getSCaseAll() == null ? 0 : this.getSCaseAll().hashCode());
		return result;
	}

}