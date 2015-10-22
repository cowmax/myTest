package com.dao;

import java.util.List;

import com.bean.PRole;

public interface PRoleDao {

	public abstract void save(PRole transientInstance);

	public abstract int delete(PRole persistentInstance);

	public abstract PRole findById(java.lang.Integer id);

	public abstract List findByExample(PRole instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByRoleName(Object roleName);

	public abstract List findByRoleDesc(Object roleDesc);

	public abstract List findAll();

	public abstract PRole merge(PRole detachedInstance);
	
	public abstract boolean findByRidAndRname(int roleId,String roleName);

}