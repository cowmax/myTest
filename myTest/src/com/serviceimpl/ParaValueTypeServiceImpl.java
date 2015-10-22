package com.serviceimpl;

import java.util.List;

import org.hibernate.SessionFactory;

import com.bean.ParaValueType;
import com.dao.ParaValueTypeDao;
import com.service.ParaValueTypeService;

public class ParaValueTypeServiceImpl implements ParaValueTypeService {
	// 封装dao调用里面的方法
	private ParaValueTypeDao paraSardataTypedao;
	// 封装集合
	private List<ParaValueType> allParaSardataType;
	// 封装对象
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
	 * 查询所有
	 */
	@SuppressWarnings("unchecked")
	public List<ParaValueType> allParaSardataType() {
		allParaSardataType=paraSardataTypedao.findAll();
		return allParaSardataType;
	}
	/**
	 * 通过Id获取参数信息
	 * @return 
	 */
	public  ParaValueType findParaSardataTypeById(String valTypeId) {
		paraSardataType=paraSardataTypedao.findById(valTypeId);
		return paraSardataType;

	}
	/**
	 * 添加参数信息
	 */
	public void saveParaSardataType(ParaValueType paraSardataType) {
		paraSardataTypedao.save(paraSardataType);

	}

}
