package com.serviceimpl;

import com.bean.PCaseAudit;
import com.dao.PCaseAuditDao;
import com.service.PCaseAuditService;

/**
 * ���˽��serviceʵ����
 */

public class PCaseAuditServiceImpl implements PCaseAuditService {
	// ��װdao��������ķ���
	private PCaseAuditDao pCaseAuditDao;

	public PCaseAuditDao getpCaseAuditDao() {
		return pCaseAuditDao;
	}

	public void setpCaseAuditDao(PCaseAuditDao pCaseAuditDao) {
		this.pCaseAuditDao = pCaseAuditDao;
	}

	/**
	 * ������˽��
	 */
	public void savePCaseAudit(PCaseAudit pCaseAudit) {
		pCaseAuditDao.save(pCaseAudit);

	}

}
