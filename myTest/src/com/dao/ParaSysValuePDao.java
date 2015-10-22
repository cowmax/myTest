package com.dao;

import java.util.List;

import com.bean.ParaSysValueP;

public interface ParaSysValuePDao {

	public abstract void save(ParaSysValueP transientInstance);

	public abstract void delete(ParaSysValueP persistentInstance);

	public abstract ParaSysValueP findById(java.lang.String id);

	public abstract List findByExample(ParaSysValueP instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByOffDay(Object offDay);

	public abstract List findByReNum(Object reNum);

	public abstract List findByOwnerRatio(Object ownerRatio);

	public abstract List findBySysUserId(Object sysUserId);

	public abstract List findAll();

	public abstract ParaSysValueP merge(ParaSysValueP detachedInstance);

}