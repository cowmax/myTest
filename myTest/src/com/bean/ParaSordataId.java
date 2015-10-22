package com.bean;

/**
 * ParaSordataId entity. @author MyEclipse Persistence Tools
 */

public class ParaSordataId implements java.io.Serializable {

	// Fields

	private String valueType;
	private String tyna;

	// Constructors

	/** default constructor */
	public ParaSordataId() {
	}

	/** full constructor */
	public ParaSordataId(String valueType, String tyna) {
		this.valueType = valueType;
		this.tyna = tyna;
	}

	// Property accessors

	public String getValueType() {
		return this.valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public String getTyna() {
		return this.tyna;
	}

	public void setTyna(String tyna) {
		this.tyna = tyna;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ParaSordataId))
			return false;
		ParaSordataId castOther = (ParaSordataId) other;

		return ((this.getValueType() == castOther.getValueType()) || (this
				.getValueType() != null && castOther.getValueType() != null && this
				.getValueType().equals(castOther.getValueType())))
				&& ((this.getTyna() == castOther.getTyna()) || (this.getTyna() != null
						&& castOther.getTyna() != null && this.getTyna()
						.equals(castOther.getTyna())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getValueType() == null ? 0 : this.getValueType().hashCode());
		result = 37 * result
				+ (getTyna() == null ? 0 : this.getTyna().hashCode());
		return result;
	}

}