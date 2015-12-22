package com.service;

import java.util.List;
import java.util.Map;

import com.bean.ParaDtS;

public interface ParaDtSService {

	/**
	 * ��ȡ���л��Ϣ
	 * 
	 * @return
	 */
	public List getAllParaDtList();

	/**
	 * �������
	 */
	public void saveOneBoat(List<ParaDtS> paraDtSList, int batchSize);

	/**
	 * ��ȡ�������
	 */
	public List getCaseIdParaDtS(int caseId);

	/**
	 * ��ݻID���һ��Ϣ
	 * 
	 * @param caseId
	 * @return
	 */
	public ParaDtS findParaDtSByCaseId(Integer caseId);

	/**
	 * ��ӻѡ���Ʒ
	 * 
	 * @param pds
	 */
	public void saveParaDtS(ParaDtS pds);

	/**
	 * ���IDɾ��ѡ���Ʒ
	 * 
	 * @param id
	 */
	public void deleteParaDtS(int id);

	/**
	 * ��ݻId��ȡ���/ɫ
	 * 
	 * @param case_id
	 * @param top
	 * @param del_status
	 * @return
	 */
	public Map<String, Integer> getCasePrdtSummary(int case_id, int top,
			int del_status);
}
