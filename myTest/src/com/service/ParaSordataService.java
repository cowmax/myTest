package com.service;

import java.util.List;

import com.bean.ParaSordata;
import com.bean.Para_Type;

public interface ParaSordataService {
	/**
	 * ��ȡ���в�Ʒ��Ϣ
	 * 
	 * @return ��Ʒ����
	 */
	public List<ParaSordata> allParaSordata();

	/**
	 * ͨ��Id��ȡ��Ʒ��Ϣ
	 * 
	 * @param valueType
	 * @return ParaSordata
	 */
	public ParaSordata findParaSordataById(com.bean.ParaSordataId id);

	/**
	 * ͨ��id��ɾ���Ʒ��Ϣ
	 */
	public void delParaSordata(ParaSordata valueType);

	/**
	 * ͨ��id���޸Ĳ�Ʒ��Ϣ
	 */
	public ParaSordata updateParaSordata(ParaSordata valueType);

	/**
	 * ��Ӳ�Ʒ��Ϣ
	 */
	public void saveParaSordata(ParaSordata paraSordata);

	/**
	 * ��ȡÿҳ�����
	 */
	public List<Para_Type> getPageParaSordata1(String page, String rows)
			throws Exception;

	/**
	 * ��ȡһ���ж��������
	 */
	public int getParaSordataTotal() throws Exception;
}
