package com.dao;
/**
 * 活动审核结果dao的抽象类
 */
import java.util.List;

import com.bean.PCaseAudit;

public interface PCaseAuditDao {

	public abstract void save(PCaseAudit transientInstance);

	public abstract void delete(PCaseAudit persistentInstance);

	public abstract PCaseAudit findById(java.lang.Integer id);

	public abstract List findByExample(PCaseAudit instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByCaseId(Object caseId);

	public abstract List findByAuditResult(Object auditResult);

	public abstract List findByAuditText(Object auditText);

	public abstract List findBySysUserId(Object sysUserId);

	public abstract List findAll();

	public abstract PCaseAudit merge(PCaseAudit detachedInstance);

	public abstract void attachDirty(PCaseAudit instance);

	public abstract void attachClean(PCaseAudit instance);

}