package com.dao;

import java.util.List;

import com.bean.ParaValueType;

public interface ParaValueTypeDao {

	// property constants
	public static final String VAL_TYPE_NAME = "valTypeName";
	public static final String VAL_TYPE_DESCRIPTION = "valTypeDescription";
	public static final String TAG = "tag";

	public abstract void save(ParaValueType transientInstance);

	public abstract void delete(ParaValueType persistentInstance);

	public abstract ParaValueType findById(java.lang.String id);

	public abstract List findByExample(ParaValueType instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByValTypeName(Object valTypeName);

	public abstract List findByValTypeDescription(Object valTypeDescription);

	public abstract List findByTag(Object tag);

	public abstract List findAll();

	public abstract ParaValueType merge(ParaValueType detachedInstance);

	public abstract void attachDirty(ParaValueType instance);

	public abstract void attachClean(ParaValueType instance);

}