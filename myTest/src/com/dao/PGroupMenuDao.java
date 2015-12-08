package com.dao;

import java.util.List;

import com.bean.PGroupMenu;
import com.bean.PGroupMenuId;

public interface PGroupMenuDao {

	public abstract void save(PGroupMenu transientInstance);

	public abstract void delete(PGroupMenu persistentInstance);

	public abstract PGroupMenu findById(com.bean.PGroupMenuId id);

	public abstract List findByExample(PGroupMenu instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findAll();

	public abstract PGroupMenu merge(PGroupMenu detachedInstance);

}