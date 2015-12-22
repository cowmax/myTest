package com.service;

import java.util.List;

import com.bean.ParaCaseP;

public interface ParaCasePService {
	/**
	 * ��ȡ���л��Ϣ
	 */
	public List<ParaCaseP> allParaCaseP();

	/**
	 * ͨ��code��ȡ���Ϣ
	 */
	public ParaCaseP findParaCasePById(String caseCode);

	/**
	 * ��ӻ��Ϣ
	 */
	public void saveParaCaseP(ParaCaseP paraCaseP);

	/**
	 * ͨ��id��ɾ��
	 */
	public void delParaCasePById(ParaCaseP caseCode);

	/**
	 * ͨ��id���޸Ļ
	 */
	public int updateParaCaseP(ParaCaseP caseCode);

	/**
	 * ��ȡһ���ж��������
	 * 
	 * @throws Exception
	 */
	public int getParaCasePTotal() throws Exception;

	public int getParaDtCaseCode(String caseCode);

	public boolean getNameBePCPRe(String caseCode, String caseName);

	/**
	 * ����Excel���
	 * 
	 * @return
	 */
	public void addOneBoat(ParaCaseP paraCaseP);

	/**
	 * ͨ����ƻ�ȡ���Ϣ
	 */
	public ParaCaseP getNameParaCaseP(String caseName);

}
