package com.dao;

import java.util.List;

import com.bean.ParaDtSSku;

public interface ParaDtSSkuDao {

	public abstract void save(ParaDtSSku transientInstance);

	public abstract void delete(ParaDtSSku persistentInstance);

	public abstract ParaDtSSku findById(java.lang.Integer id);

	public abstract List findByExample(ParaDtSSku instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByCaseId(Object caseId);

	public abstract List findByProductCode(Object productCode);

	public abstract List findByColo(Object colo);

	public abstract List findByCona(Object cona);

	public abstract List findBySzid(Object szid);

	public abstract List findBySkuCode(Object skuCode);

	public abstract List findByStatus(Object status);

	public abstract List findBySalesNum(Object salesNum);

	public abstract List findByStock(Object stock);

	public abstract List findByNewOldFlag(Object newOldFlag);

	public abstract List findBySCaseAll(Object SCaseAll);

	public abstract List findAll();

	public abstract ParaDtSSku merge(ParaDtSSku detachedInstance);

	public abstract void attachDirty(ParaDtSSku instance);

	public abstract void attachClean(ParaDtSSku instance);

}