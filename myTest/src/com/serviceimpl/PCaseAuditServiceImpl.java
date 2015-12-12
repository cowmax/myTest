package com.serviceimpl;

import com.bean.PCaseAudit;
import com.dao.PCaseAuditDao;
import com.service.PCaseAuditService;
/**
 * 活动审核结果service实现类
 */

public class PCaseAuditServiceImpl implements PCaseAuditService {
	// 封装dao调用里面的方法
	private PCaseAuditDao pCaseAuditDao;
	
	
	public PCaseAuditDao getpCaseAuditDao() {
		return pCaseAuditDao;
	}


	public void setpCaseAuditDao(PCaseAuditDao pCaseAuditDao) {
		this.pCaseAuditDao = pCaseAuditDao;
	}


	/**
	 * 保存活动审核结果
	 */
	public void savePCaseAudit(PCaseAudit pCaseAudit) {
		pCaseAuditDao.save(pCaseAudit);
		
	}
	
}
