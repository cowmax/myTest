package com.dao;

import java.util.List;

import com.bean.ParaSordata;

public interface ParaSordataDao {

	public abstract void save(ParaSordata transientInstance);

	public abstract void delete(ParaSordata persistentInstance);

	public abstract ParaSordata findById(com.bean.ParaSordataId id);

	public abstract List findByExample(ParaSordata instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByValueRatio(Object valueRatio);

	public abstract List findByValueMin(Object valueMin);

	public abstract List findByValueMax(Object valueMax);

	public abstract List findByValueDesc(Object valueDesc);

	public abstract List findBySysUserId(Object sysUserId);

	public abstract List findAll();

	public abstract ParaSordata merge(ParaSordata detachedInstance);

	public abstract void attachDirty(ParaSordata instance);

	public abstract void attachClean(ParaSordata instance);

}