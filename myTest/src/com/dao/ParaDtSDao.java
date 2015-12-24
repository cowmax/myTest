package com.dao;

import java.util.List;
import java.util.Map;

import com.bean.ParaDtS;

public interface ParaDtSDao {

	public abstract void save(ParaDtS transientInstance);

	public abstract void delete(ParaDtS persistentInstance);

	public abstract ParaDtS findById(java.lang.Integer id);

	public abstract List findByExample(ParaDtS instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByCaseId(Object caseId);

	public abstract List findByProductCd(Object productCd);

	public abstract List findByStatus(Object status);

	public abstract List findByAvgAmt(Object avgAmt);

	public abstract List findByStock(Object stock);

	public abstract List findByNewOldFlag(Object newOldFlag);

	public abstract List findBySCaseAll(Object SCaseAll);

	public abstract List findByColo(Object colo);

	public abstract List findByCona(Object cona);

	public abstract List findAll();

	public abstract ParaDtS merge(ParaDtS detachedInstance);
	
	public abstract Map<String, Integer> getCasePrdtSummary(int case_id,int top,int del_status);
	
	public abstract void setImpParaDtSSku(final int imp_flag,final String name);
}