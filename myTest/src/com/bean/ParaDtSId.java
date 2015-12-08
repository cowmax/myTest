package com.bean;

/**
 * ParaDtSId entity. @author MyEclipse Persistence Tools
 */

public class ParaDtSId implements java.io.Serializable {

	// Fields

	private Integer caseId;
	private String productCd;
	private Integer status;
	private Double avgAmt;
	private Double stock;
	private String newOldFlag;
	private String SCaseAll;
	private String colo;
	private String cona;

	// Constructors

	/** default constructor */
	public ParaDtSId() {
	}

	/** full constructor */
	public ParaDtSId(Integer caseId, String productCd, Integer status,
			Double avgAmt, Double stock, String newOldFlag, String SCaseAll,
			String colo, String cona) {
		this.caseId = caseId;
		this.productCd = productCd;
		this.status = status;
		this.avgAmt = avgAmt;
		this.stock = stock;
		this.newOldFlag = newOldFlag;
		this.SCaseAll = SCaseAll;
		this.colo = colo;
		this.cona = cona;
	}

	// Property accessors

	public Integer getCaseId() {
		return this.caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public String getProductCd() {
		return this.productCd;
	}

	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getAvgAmt() {
		return this.avgAmt;
	}

	public void setAvgAmt(Double avgAmt) {
		this.avgAmt = avgAmt;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ParaDtSId))
			return false;
		ParaDtSId castOther = (ParaDtSId) other;

		return ((this.getCaseId() == castOther.getCaseId()) || (this
				.getCaseId() != null && castOther.getCaseId() != null && this
				.getCaseId().equals(castOther.getCaseId())))
				&& ((this.getProductCd() == castOther.getProductCd()) || (this
						.getProductCd() != null
						&& castOther.getProductCd() != null && this
						.getProductCd().equals(castOther.getProductCd())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null && castOther.getStatus() != null && this
						.getStatus().equals(castOther.getStatus())))
				&& ((this.getAvgAmt() == castOther.getAvgAmt()) || (this
						.getAvgAmt() != null && castOther.getAvgAmt() != null && this
						.getAvgAmt().equals(castOther.getAvgAmt())))
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
						.getSCaseAll().equals(castOther.getSCaseAll())))
				&& ((this.getColo() == castOther.getColo()) || (this.getColo() != null
						&& castOther.getColo() != null && this.getColo()
						.equals(castOther.getColo())))
				&& ((this.getCona() == castOther.getCona()) || (this.getCona() != null
						&& castOther.getCona() != null && this.getCona()
						.equals(castOther.getCona())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCaseId() == null ? 0 : this.getCaseId().hashCode());
		result = 37 * result
				+ (getProductCd() == null ? 0 : this.getProductCd().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37 * result
				+ (getAvgAmt() == null ? 0 : this.getAvgAmt().hashCode());
		result = 37 * result
				+ (getStock() == null ? 0 : this.getStock().hashCode());
		result = 37
				* result
				+ (getNewOldFlag() == null ? 0 : this.getNewOldFlag()
						.hashCode());
		result = 37 * result
				+ (getSCaseAll() == null ? 0 : this.getSCaseAll().hashCode());
		result = 37 * result
				+ (getColo() == null ? 0 : this.getColo().hashCode());
		result = 37 * result
				+ (getCona() == null ? 0 : this.getCona().hashCode());
		return result;
	}

}