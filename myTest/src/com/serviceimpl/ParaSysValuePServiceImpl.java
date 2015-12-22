package com.serviceimpl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;

import com.bean.ParaSysValueP;
import com.dao.ParaSysValuePDao;
import com.service.ParaSysValuePService;

public class ParaSysValuePServiceImpl implements ParaSysValuePService {
	// ��װdao��������ķ���
	private ParaSysValuePDao paraSysValuePDao;
	// ��װ����
	private List<ParaSysValueP> allParaSysValueP;
	// ��װ����
	private ParaSysValueP paraSysValueP;
	private SessionFactory sessionFactory;

	public ParaSysValuePDao getParaSysValuePDao() {
		return paraSysValuePDao;
	}

	public void setParaSysValuePDao(ParaSysValuePDao paraSysValuePDao) {
		this.paraSysValuePDao = paraSysValuePDao;
	}

	public List<ParaSysValueP> getAllParaSysValueP() {
		return allParaSysValueP;
	}

	public void setAllParaSysValueP(List<ParaSysValueP> allParaSysValueP) {
		this.allParaSysValueP = allParaSysValueP;
	}

	public ParaSysValueP getParaSysValueP() {
		return paraSysValueP;
	}

	public void setParaSysValueP(ParaSysValueP paraSysValueP) {
		this.paraSysValueP = paraSysValueP;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * ��ȡ���м������
	 */
	@SuppressWarnings("unchecked")
	public List<ParaSysValueP> allParaSysValueP() {
		allParaSysValueP = paraSysValuePDao.findAll();
		return allParaSysValueP;
	}

	/**
	 * ����������
	 */
	public void saveParaSordataP(ParaSysValueP paraSysValueP) {
		paraSysValuePDao.save(paraSysValueP);

	}

	/**
	 * ͨ��Id��ȡ���������Ϣ
	 */
	public ParaSysValueP findParaSysValuePById(String tyna) {
		paraSysValueP = paraSysValuePDao.findById(tyna);
		return paraSysValueP;
	}

	/**
	 * ͨ��id��ɾ�������Ϣ
	 */
	public void delParaSysValuePById(ParaSysValueP tyna) {
		paraSysValuePDao.delete(tyna);
	}

	/**
	 * ͨ��id���޸Ĳ�����Ϣ
	 */
	public ParaSysValueP updateParaSysValueP(ParaSysValueP tyna) {
		paraSysValueP = paraSysValuePDao.merge(tyna);
		return paraSysValueP;
	}

	/**
	 * ��ȡÿҳ�����
	 */
	@SuppressWarnings("unchecked")
	public List<ParaSysValueP> getParaSysValuePPage(String page, String rows)
			throws Exception {
		// ��Ϊȱʡֵ��ʱ����и�ֵ
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);// �ڼ�ҳ
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);// ÿҳ������
		List<ParaSysValueP> li = this.sessionFactory.getCurrentSession()
				.createQuery("from ParaSysValueP order by sysDt desc")
				.setFirstResult((currentpage - 1) * pagesize)
				.setMaxResults(pagesize).list();
		return li;
	}

	/**
	 * ��ȡһ���ж��������
	 */
	@SuppressWarnings("deprecation")
	public int getParaSysValuePTotal() throws Exception {
		return this.sessionFactory.getCurrentSession()
				.find("from ParaSysValueP").size();
	}
}
