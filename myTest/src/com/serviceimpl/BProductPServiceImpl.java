package com.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;

import com.bean.BProductP;
import com.dao.BProductPDao;
import com.service.BProductPService;

public class BProductPServiceImpl implements BProductPService {
	// ��װdao��������ķ���
	private BProductPDao bProductPadao;
	// ��װ����
	private List<BProductP> allBProductP;
	// ��װ����
	private BProductP bProductP;
	private SessionFactory sessionFactory;

	public BProductPDao getbProductPadao() {
		return bProductPadao;
	}

	public void setbProductPadao(BProductPDao bProductPadao) {
		this.bProductPadao = bProductPadao;
	}

	public List<BProductP> getAllBProductP() {
		return allBProductP;
	}

	public void setAllBProductP(List<BProductP> allBProductP) {
		this.allBProductP = allBProductP;
	}

	public BProductP getbProductP() {
		return bProductP;
	}

	public void setbProductP(BProductP bProductP) {
		this.bProductP = bProductP;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*
	 * ��ȡ���в�Ʒ��Ϣ
	 */
	@SuppressWarnings("unchecked")
	public List<BProductP> allBProductP() {
		List ls = new ArrayList();
		ls = bProductPadao.findAll();
		return ls;
	}

	/**
	 * ��ѯ���в�Ʒ��Ŀ��
	 */
	public List alltyna() {
		String sql = "select distinct tyna from b_product_p where tyna is not null ";
		SQLQuery query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql);
		List list = query.list();
		return list;
	}

	/**
	 * ��ѯ���в�Ʒ��λ��Ϣ
	 */
	public List allSpno() {
		String sql = "select distinct spno from b_product_p where spno is not null ";
		SQLQuery query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql);
		List list = query.list();
		return list;
	}

	/**
	 * ��ѯ��ĳ��Ѵ��ڵĲ�Ʒ����Ϣ
	 * 
	 * @param caseId
	 * @return
	 */
	public List findExceptByCaseId(Integer caseId) {
		return bProductPadao.findExceptByCaseId(caseId);
	}

	public BProductP findById(String productCode) {
		return bProductPadao.findById(productCode);
	}

	public Map<String, String> findColorByProductCd(String productCode) {
		return bProductPadao.findColorByProductCd(productCode);
	}
}
