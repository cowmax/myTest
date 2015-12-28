package com.service;

import java.sql.Timestamp;
import java.util.List;

import com.bean.ParaDt;
import com.bean.RefactorParaDt;


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
	 * ͨ��id��ɾ���
	 */
	public ParaDt delParaDtById(ParaDt paraDt);

	/**
	 * ͨ��id���޸Ļ
	 */
	public ParaDt updateParaDtImpl(ParaDt caseId);


	/**
	 * ��ȡһ���ж���������
	 */
	public int getTotalCount( ) throws Exception;
	
	/**
	 * ����Excel���
	 * 
	 */
	public boolean saveOneBoat(List<ParaDt> list,int batchSize);
	
	/**
	 * ���ص�ҳ����excel���
	 */
	
	/**
	 * ͨ������ͻ�ȡʱ����бȽ�
	 */
	public int getCaseNameTime(String caseName,Timestamp caseSt,Timestamp caseEt );
	
	public RefactorParaDt getRpdByCaseId(Integer caseId);
}
