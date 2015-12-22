package com.service;

import java.util.List;

public interface ParaDtSSkuService {

	/**
	 * ��ȡ���в�ƷSKU��ϸ
	 */
	public List getAllParaDtSSku();

	/**
	 * ���id��ȡ���caseId
	 */
	public List getCaseIdParaDtSSku(int caseId);
}
