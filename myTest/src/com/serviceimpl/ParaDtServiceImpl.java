package com.serviceimpl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bean.ParaDt;
import com.bean.ParaDtS;
import com.dao.ParaDtDao;
import com.service.ParaDtService;

public class ParaDtServiceImpl implements ParaDtService {
	// ��װdao��������ķ���
	private ParaDtDao paraDtDao;
	// ��װ����
	private List<ParaDt> allParaDt;
	// ��װ����
	private ParaDt paraDt;
	private SessionFactory sessionFactory;
	private boolean flage;

	public ParaDtDao getParaDtDao() {
		return paraDtDao;
	}

	public void setParaDtDao(ParaDtDao paraDtDao) {
		this.paraDtDao = paraDtDao;
	}

	public List<ParaDt> getAllParaDt() {
		return allParaDt;
	}

	public void setAllParaDt(List<ParaDt> allParaDt) {
		this.allParaDt = allParaDt;
	}

	public ParaDt getParaDt() {
		return paraDt;
	}

	public void setParaDt(ParaDt paraDt) {
		this.paraDt = paraDt;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean isFlage() {
		return flage;
	}

	public void setFlage(boolean flage) {
		this.flage = flage;
	}

	/**
	 * ��ȡ���л��Ϣ
	 */
	@SuppressWarnings("unchecked")
	public List<ParaDt> allParaDt() {
		allParaDt = paraDtDao.findAll();
		return allParaDt;
	}

	/**
	 * ͨ��code��ȡ���Ϣ
	 */
	public ParaDt findParaDtById(Integer id) {
		return paraDtDao.findById(id);
	}

	/**
	 * ��ӻ��Ϣ
	 */
	public void saveParaDt(ParaDt paraDt) {
		paraDtDao.save(paraDt);

	}

	/**
	 * ͨ��id��ɾ��
	 */
	public ParaDt delParaDtById(ParaDt paraDt) {
		return paraDtDao.merge(paraDt);
	}

	/**
	 * ͨ��id���޸Ļ
	 */
	public ParaDt updateParaDtImpl(ParaDt caseCode) {
		return paraDtDao.merge(caseCode);
	}

	/**
	 * ����Excel���
	 */
	public void saveOneBoat(ParaDt paraDt) {
		paraDtDao.save(paraDt);
	}

	/*
	 * һ�ܶ��������
	 */
	public int getTotalCount() throws Exception {
		Session ssn = this.sessionFactory.getCurrentSession();
		Query qry = ssn.createQuery("from ParaDt");
		int cnt = qry.list().size();
		return cnt;
	}

	/**
	 * ͨ�����ͻ�ȡʱ����бȽ�
	 */
	public int getCaseNameTime(String caseName, Timestamp caseSt,
			Timestamp caseEt) {
		String sql = "select * from para_dt " + "where case_name=:caseName "
				+ "and status!=0 " + "and ("
				+ "(case_st < :caseEt and case_st > :caseSt ) "
				+ "or (case_et > :caseSt  and case_et < :caseEt  ) "
				+ "or (case_st < :caseSt and case_et > :caseEt )" + ")";

		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(
				sql);
		query.setString("caseName", caseName);
		query.setTimestamp("caseSt", caseSt);
		query.setTimestamp("caseEt", caseEt);
		return query.list().size();
	}

}
