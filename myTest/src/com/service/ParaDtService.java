package com.service;

import java.sql.Timestamp;
import java.util.List;

import com.bean.ParaDt;

public interface ParaDtService {
	/**
	 * ��ȡ���л��Ϣ
	 */
	public List<ParaDt> allParaDt();

	/**
	 * ͨ��code��ȡ���Ϣ
	 */
	public ParaDt findParaDtById(Integer caseCode);

	/**
	 * ��ӻ��Ϣ
	 */
	public void saveParaDt(ParaDt paraDt);

	/**
	 * ͨ��id��ɾ��
	 */
	public ParaDt delParaDtById(ParaDt paraDt);

	/**
	 * ͨ��id���޸Ļ
	 */
	public ParaDt updateParaDtImpl(ParaDt caseId);

	/**
	 * ��ȡһ���ж��������
	 */
	public int getTotalCount() throws Exception;

	/**
	 * ����Excel���
	 * 
	 */
	public void saveOneBoat(ParaDt paraDt);

	/**
	 * ���ص�ҳ���excel���
	 */

	/**
	 * ͨ�����ͻ�ȡʱ����бȽ�
	 */
	public int getCaseNameTime(String caseName, Timestamp caseSt,
			Timestamp caseEt);

}
