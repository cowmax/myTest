package com.service;

import java.util.List;

import com.bean.ParaSysValueP;

public interface ParaSysValuePService {
	/**
	 * ��ȡ������Ŀ��Ϣ
	 */
	public List<ParaSysValueP> allParaSysValueP();

	/**
	 * ͨ��Id��ȡ��Ŀ��Ϣ
	 */
	public ParaSysValueP findParaSysValuePById(String tyna);

	/**
	 * �����Ŀ��Ϣ
	 */
	public void saveParaSordataP(ParaSysValueP paraSysValueP);

	/**
	 * ͨ��id��ɾ��������Ϣ
	 */
	public void delParaSysValuePById(ParaSysValueP tyna);

	/**
	 * ͨ��id���޸Ĳ�����Ϣ
	 */
	public ParaSysValueP updateParaSysValueP(ParaSysValueP tyna);

	/**
	 * ��ȡÿҳ������
	 */
	public List<ParaSysValueP> getParaSysValuePPage(String page, String rows)
			throws Exception;

	/**
	 * ��ȡһ���ж���������
	 * 
	 * @throws Exception
	 */
	public int getParaSysValuePTotal() throws Exception;
}
