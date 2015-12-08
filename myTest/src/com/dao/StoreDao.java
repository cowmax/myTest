package com.dao;

import java.util.List;

import com.bean.Store;

public interface StoreDao {

	public abstract void save(Store transientInstance);

	public abstract void delete(Store persistentInstance);

	public abstract Store findById(com.bean.Store id);

	public abstract List findByExample(Store instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findAll();

	public abstract Store merge(Store detachedInstance);

	public abstract void attachDirty(Store instance);

	public abstract void attachClean(Store instance);

}