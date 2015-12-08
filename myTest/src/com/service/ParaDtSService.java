package com.service;

import java.util.List;

import com.bean.ParaDtS;


public interface ParaDtSService {
	
	/**
	 * ��ȡ���л��Ϣ
	 * @return
	 */
	public List getAllParaDtList();
	

	/**
	 * ��������
	 */
	public void saveOneBoat(List<ParaDtS> paraDtSList,int batchSize);
	
	/**
	 * ��ȡ��������
	 */
	public List getCaseIdParaDtS(int caseId);
	
	/**
	 * ���ݻID���һ��Ϣ
	 * @param caseId
	 * @return
	 */
	public ParaDtS findParaDtSByCaseId(Integer caseId);
	
	/**
	 * ��ӻѡ���Ʒ
	 * @param pds
	 */
	public void saveParaDtS(ParaDtS pds);
	
	/**
	 * ����IDɾ���ѡ���Ʒ
	 * @param id
	 */
	public void deleteParaDtS(int id);
}
