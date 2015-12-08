package com.dao;

import java.util.List;

import com.bean.ParaCaseP;

public interface ParaCasePDao {

	public abstract void save(ParaCaseP transientInstance);

	public abstract void delete(ParaCaseP persistentInstance);

	public abstract ParaCaseP findById(String id);

	public abstract List findByExample(ParaCaseP instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findAll();

	public abstract int merge(ParaCaseP detachedInstance);

	public abstract void attachDirty(ParaCaseP instance);

	public abstract void attachClean(ParaCaseP instance);

}