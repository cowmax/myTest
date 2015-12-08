package com.dao;

import java.util.List;

import com.bean.PMenu;

public interface PMenuDao {

	public abstract void save(PMenu transientInstance);

	public abstract void delete(PMenu persistentInstance);

	public abstract PMenu findById(java.lang.String id);

	public abstract List findByExample(PMenu instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByPmid(Object pmid);

	public abstract List findByMname(Object mname);

	public abstract List findByMurl(Object murl);

	public abstract List findBySysUserId(Object sysUserId);

	public abstract List findAll();

	public abstract PMenu merge(PMenu detachedInstance);

}