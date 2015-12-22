package com.service;

import java.util.List;
import java.util.Map;

import com.bean.BProductP;

public interface BProductPService {
	/**
	 * ��ȡ���в�Ʒ��Ϣ
	 * 
	 */
	public List<BProductP> allBProductP();

	/**
	 * ��ѯtyna
	 */

	public List alltyna();

	/**
	 * ��ѯ���в�Ʒ��λ��Ϣ
	 */
	public List allSpno();

	/**
	 * ��ѯ��ĳ��Ѵ��ڵĲ�Ʒ����Ϣ
	 * 
	 * @param caseId
	 * @return
	 */
	public List findExceptByCaseId(Integer caseId);

	/**
	 * ��ݲ�Ʒ�����ȡ��Ʒ��Ϣ
	 * 
	 * @param productCode
	 * @return
	 */
	public BProductP findById(String productCode);

	/**
	 * ��ݲ�Ʒ��Ż�ȡ��Ʒ��ɫ
	 * 
	 * @param productCode
	 * @return
	 */
	public Map<String, String> findColorByProductCd(String productCode);

}
