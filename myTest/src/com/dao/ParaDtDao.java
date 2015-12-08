package com.dao;

import java.util.List;

import com.bean.ParaDt;

public interface ParaDtDao {

	public abstract void save(ParaDt transientInstance);

	public abstract void delete(ParaDt persistentInstance);

	public abstract ParaDt findById(java.lang.Integer id);

	public abstract List findByExample(ParaDt instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByCaseName(Object caseName);

	public abstract List findByCaseDesc(Object caseDesc);

	public abstract List findBySysUserId(Object sysUserId);

	public abstract List findByStatus(Object status);

	public abstract List findByCaseCode(Object caseCode);

	public abstract List findByRatioNew(Object ratioNew);

	public abstract List findByNum(Object num);

	public abstract List findAll();

	public abstract ParaDt merge(ParaDt paraDt);

	public abstract void attachDirty(ParaDt instance);

	public abstract void attachClean(ParaDt instance);

}