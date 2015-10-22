package com.dao;

import java.util.List;

import com.bean.PUser;

@SuppressWarnings("rawtypes")
public interface PUserDao {

	public abstract void save(PUser transientInstance);

	public abstract void delete(PUser persistentInstance);

	public abstract PUser findById(java.lang.String id);

	public abstract List findByExample(PUser instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByUserName(Object userName);

	public abstract List findByUserPwd(Object userPwd);

	public abstract List findByUserDesc(Object userDesc);

	public abstract List findAll();

	public abstract PUser merge(PUser detachedInstance);




}