package com.bean;

/**
 * PGroupMenuId entity. @author MyEclipse Persistence Tools
 */

public class PGroupMenuId implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer mid;
	private Integer groupId;

	// Constructors

	/** default constructor */
	public PGroupMenuId() {
	}

	/** minimal constructor */
	public PGroupMenuId(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public PGroupMenuId(Integer id, Integer mid, Integer groupId) {
		this.id = id;
		this.mid = mid;
		this.groupId = groupId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMid() {
		return this.mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public Integer getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PGroupMenuId))
			return false;
		PGroupMenuId castOther = (PGroupMenuId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getMid() == castOther.getMid()) || (this.getMid() != null
						&& castOther.getMid() != null && this.getMid().equals(
						castOther.getMid())))
				&& ((this.getGroupId() == castOther.getGroupId()) || (this
						.getGroupId() != null && castOther.getGroupId() != null && this
						.getGroupId().equals(castOther.getGroupId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getMid() == null ? 0 : this.getMid().hashCode());
		result = 37 * result
				+ (getGroupId() == null ? 0 : this.getGroupId().hashCode());
		return result;
	}

}