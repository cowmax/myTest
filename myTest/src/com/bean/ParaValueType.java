package com.bean;

/**
 * ParaValueType entity. @author MyEclipse Persistence Tools
 */

public class ParaValueType implements java.io.Serializable {

	// Fields

	private String valTypeId;
	private String valTypeName;
	private String valTypeDescription;
	private String tag;

	// Constructors

	/** default constructor */
	public ParaValueType() {
	}

	/** minimal constructor */
	public ParaValueType(String valTypeId, String valTypeName) {
		this.valTypeId = valTypeId;
		this.valTypeName = valTypeName;
	}

	/** full constructor */
	public ParaValueType(String valTypeId, String valTypeName,
			String valTypeDescription, String tag) {
		this.valTypeId = valTypeId;
		this.valTypeName = valTypeName;
		this.valTypeDescription = valTypeDescription;
		this.tag = tag;
	}

	// Property accessors

	public String getValTypeId() {
		return this.valTypeId;
	}

	public void setValTypeId(String valTypeId) {
		this.valTypeId = valTypeId;
	}

	public String getValTypeName() {
		return this.valTypeName;
	}

	public void setValTypeName(String valTypeName) {
		this.valTypeName = valTypeName;
	}

	public String getValTypeDescription() {
		return this.valTypeDescription;
	}

	public void setValTypeDescription(String valTypeDescription) {
		this.valTypeDescription = valTypeDescription;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}