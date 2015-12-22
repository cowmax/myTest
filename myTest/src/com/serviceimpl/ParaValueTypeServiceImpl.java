package com.serviceimpl;

import java.util.List;

import org.hibernate.SessionFactory;

import com.bean.ParaValueType;
import com.dao.ParaValueTypeDao;
import com.service.ParaValueTypeService;

public class ParaValueTypeServiceImpl implements ParaValueTypeService {
	// ��װdao��������ķ���
	private ParaValueTypeDao paraSardataTypedao;
	// ��װ����
	private List<ParaValueType> allParaSardataType;
	// ��װ����
	private ParaValueType paraSardataType;
	private SessionFactory sessionFactory;

	public ParaValueTypeDao getParaSardataTypedao() {
		return paraSardataTypedao;
	}

	public void setParaSardataTypedao(ParaValueTypeDao paraSardataTypedao) {
		this.paraSardataTypedao = paraSardataTypedao;
	}

	public List<ParaValueType> getAllParaSardataType() {
		return allParaSardataType;
	}

	public void setAllParaSardataType(List<ParaValueType> allParaSardataType) {
		this.allParaSardataType = allParaSardataType;
	}

	public ParaValueType getParaSardataType() {
		return paraSardataType;
	}

	public void setParaSardataType(ParaValueType paraSardataType) {
		this.paraSardataType = paraSardataType;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * ��ѯ����
	 */
	@SuppressWarnings("unchecked")
	public List<ParaValueType> allParaSardataType() {
		allParaSardataType = paraSardataTypedao.findAll();
		return allParaSardataType;
	}

	/**
	 * ͨ��Id��ȡ������Ϣ
	 * 
	 * @return
	 */
	public ParaValueType findParaSardataTypeById(String valTypeId) {
		paraSardataType = paraSardataTypedao.findById(valTypeId);
		return paraSardataType;

	}

	/**
	 * ��Ӳ�����Ϣ
	 */
	public void saveParaSardataType(ParaValueType paraSardataType) {
		paraSardataTypedao.save(paraSardataType);

	}

}
