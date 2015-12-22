package com.dao;

import java.util.List;
import java.util.Map;

import com.bean.BProductP;

public interface BProductPDao {

	public abstract void save(BProductP transientInstance);

	public abstract void delete(BProductP persistentInstance);

	public abstract BProductP findById(String id);

	public abstract List findByExample(BProductP instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findAll();

	public abstract BProductP merge(BProductP detachedInstance);

	public abstract void attachDirty(BProductP instance);

	public abstract void attachClean(BProductP instance);

	public abstract List findExceptByCaseId(Integer caseId);

	public abstract Map<String, String> findColorByProductCd(String productCode);

}