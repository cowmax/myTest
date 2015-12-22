package com.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;

import com.bean.BProductP;
import com.bean.ParaDtSSku;
import com.dao.ParaDtSSkuDao;
import com.service.ParaDtSService;
import com.service.ParaDtSSkuService;

public class ParaDtSSkuServiceImpl implements ParaDtSSkuService {
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
	 * ��ȡ���в�ƷSKU��ϸ
	 */
	public List getAllParaDtSSku() {
		List ls = new ArrayList();
		ls = paraDtSSkuDao.findAll();
		return ls;
	}

	/**
	 * ���id��ȡ���caseId
	 */
	public List<ParaDtSSku> getCaseIdParaDtSSku(int caseId) {
		String sql = "select * from para_dt_s_sku s inner join b_product_p p on s.product_code = p.product_code "
				+ "where 0=0 and s.case_id = :case_id and ISNULL(s.status,2)!=0  ";
		SQLQuery query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql);
		query.setInteger("case_id", caseId);
		query.addEntity(ParaDtSSku.class);
		query.addEntity(BProductP.class);
		List<ParaDtSSku> list = query.list();
		return list;
	}
}
