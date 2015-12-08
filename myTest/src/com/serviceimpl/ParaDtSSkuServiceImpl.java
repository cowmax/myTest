package com.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;

import com.bean.ParaDtSSku;
import com.dao.ParaDtSSkuDao;
import com.service.ParaDtSService;
import com.service.ParaDtSSkuService;

public class ParaDtSSkuServiceImpl implements ParaDtSSkuService{
	private ParaDtSSkuDao paraDtSSkuDao;
	private SessionFactory sessionFactory;
	
	
	public ParaDtSSkuDao getParaDtSSkuDao() {
		return paraDtSSkuDao;
	}

	public void setParaDtSSkuDao(ParaDtSSkuDao paraDtSSkuDao) {
		this.paraDtSSkuDao = paraDtSSkuDao;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}



	/**
	 * 获取所有产品SKU明细
	 */
	public List getAllParaDtSSku() {
		List ls = new ArrayList();
		ls=paraDtSSkuDao.findAll();
		return ls;
	}
	
	/**
	 * 根据id获取数据caseId
	 */
	public List<ParaDtSSku> getCaseIdParaDtSSku(int caseId) {
		String sql="select * from para_dt_s_sku where 0=0and case_id = :case_id and status != 0 and status is not null ";
		SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setInteger("case_id", caseId);
		query.addEntity(ParaDtSSku.class);
		List<ParaDtSSku> list = query.list();
		return list;
	}
}
