package com.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;

import com.bean.BProductP;
import com.dao.BProductPDao;
import com.service.BProductPService;


public class BProductPServiceImpl implements BProductPService {
	// 封装dao调用里面的方法
	private BProductPDao bProductPadao;
	// 封装集合
	private List<BProductP> allBProductP;
	// 封装对象
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
	 * 获取所有产品信息
	 */
	@SuppressWarnings("unchecked")
	public List<BProductP> allBProductP() {
		List ls = new ArrayList();
		ls = bProductPadao.findAll();
		return ls;
	}
	/**
	 * 查询所有产品类目的
	 */
	public	List alltyna(){
		String sql="select distinct tyna from b_product_p where tyna is not null ";
		SQLQuery query =  this.sessionFactory.getCurrentSession().createSQLQuery(sql);
		List list = query.list();
		return list;
	}
}
