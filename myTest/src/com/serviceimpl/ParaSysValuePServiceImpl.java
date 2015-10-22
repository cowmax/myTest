package com.serviceimpl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;

import com.bean.ParaSysValueP;
import com.dao.ParaSysValuePDao;
import com.service.ParaSysValuePService;

public class ParaSysValuePServiceImpl implements ParaSysValuePService {
	// 封装dao调用里面的方法
	private ParaSysValuePDao paraSysValuePDao;
	// 封装集合
	private List<ParaSysValueP> allParaSysValueP;
	// 封装对象
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
	 * 获取所有计算参数
	 */
	@SuppressWarnings("unchecked")
	public List<ParaSysValueP> allParaSysValueP() {
		allParaSysValueP=paraSysValuePDao.findAll();
		return allParaSysValueP;
	}

	/**
	 * 保存计算参数
	 */
	public void saveParaSordataP(ParaSysValueP paraSysValueP) {
		paraSysValuePDao.save(paraSysValueP);
		
	}
	
	/**
	 * 通过Id获取计算参数信息
	 */
	public ParaSysValueP findParaSysValuePById(String tyna) {
		paraSysValueP=paraSysValuePDao.findById(tyna);
		return paraSysValueP;
	}
	
	/**
	 * 通过id来删除参数信息
	 */
	public void delParaSysValuePById(ParaSysValueP tyna){
		paraSysValuePDao.delete(tyna);
	}
	/**
	 * 通过id来修改参数信息
	 */
	public ParaSysValueP updateParaSysValueP(ParaSysValueP tyna){
		paraSysValueP=paraSysValuePDao.merge(tyna);
		return paraSysValueP;
	}
	/**
	 * 获取每页的数据
	 */
	@SuppressWarnings("unchecked")
	public List<ParaSysValueP> getParaSysValuePPage(String page, String rows)
		throws Exception {
			//当为缺省值的时候进行赋值  
			int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页  
			int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行
			List<ParaSysValueP> li =  this.sessionFactory.getCurrentSession().createQuery("from ParaSysValueP order by sysDt desc").setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();	
			return li;
	}
	/**
	 * 获取一共有多少条数据
	 */
		@SuppressWarnings("deprecation")
		public int getParaSysValuePTotal() throws Exception {
			return this.sessionFactory.getCurrentSession().find("from ParaSysValueP").size();
		}	
}
