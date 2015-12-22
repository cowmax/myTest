package com.daoimpl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.PGroup;
import com.bean.PGroupUser;
import com.bean.PUser;
import com.bean.ParaCaseP;
import com.bean.ParaDtS;
import com.bean.Store;
import com.dao.ParaDtSDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * ParaDtS entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bean.ParaDtS
 * @author MyEclipse Persistence Tools
 */
public class ParaDtSDaoImpl extends HibernateDaoSupport implements ParaDtSDao {
	private static final Logger log = LoggerFactory
			.getLogger(ParaDtSDaoImpl.class);
	// property constants
	public static final String CASE_ID = "caseId";
	public static final String PRODUCT_CD = "productCd";
	public static final String STATUS = "status";
	public static final String AVG_AMT = "avgAmt";
	public static final String STOCK = "stock";
	public static final String NEW_OLD_FLAG = "newOldFlag";
	public static final String _SCASE_ALL = "SCaseAll";
	public static final String COLO = "colo";
	public static final String CONA = "cona";

	protected void initDao() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaDtSDao#save(com.bean.ParaDtS)
	 */
	public void save(ParaDtS transientInstance) {
		log.debug("saving ParaDtS instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaDtSDao#delete(com.bean.ParaDtS)
	 */
	public void delete(ParaDtS persistentInstance) {
		log.debug("deleting ParaDtS instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaDtSDao#findById(java.lang.Integer)
	 */
	public ParaDtS findById(java.lang.Integer id) {
		log.debug("getting ParaDtS instance with id: " + id);
		try {
			ParaDtS instance = (ParaDtS) getHibernateTemplate().get(
					"com.bean.ParaDtS", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaDtSDao#findByExample(com.bean.ParaDtS)
	 */
	public List findByExample(ParaDtS instance) {
		log.debug("finding ParaDtS instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaDtSDao#findByProperty(java.lang.String,
	 * java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ParaDtS instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ParaDtS as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaDtSDao#findByCaseId(java.lang.Object)
	 */
	public List findByCaseId(Object caseId) {
		return findByProperty(CASE_ID, caseId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaDtSDao#findByProductCd(java.lang.Object)
	 */
	public List findByProductCd(Object productCd) {
		return findByProperty(PRODUCT_CD, productCd);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaDtSDao#findByStatus(java.lang.Object)
	 */
	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaDtSDao#findByAvgAmt(java.lang.Object)
	 */
	public List findByAvgAmt(Object avgAmt) {
		return findByProperty(AVG_AMT, avgAmt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaDtSDao#findByStock(java.lang.Object)
	 */
	public List findByStock(Object stock) {
		return findByProperty(STOCK, stock);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaDtSDao#findByNewOldFlag(java.lang.Object)
	 */
	public List findByNewOldFlag(Object newOldFlag) {
		return findByProperty(NEW_OLD_FLAG, newOldFlag);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaDtSDao#findBySCaseAll(java.lang.Object)
	 */
	public List findBySCaseAll(Object SCaseAll) {
		return findByProperty(_SCASE_ALL, SCaseAll);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaDtSDao#findByColo(java.lang.Object)
	 */
	public List findByColo(Object colo) {
		return findByProperty(COLO, colo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaDtSDao#findByCona(java.lang.Object)
	 */
	public List findByCona(Object cona) {
		return findByProperty(CONA, cona);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaDtSDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all ParaDtS instances");
		try {
			String queryString = "from ParaDtS where ";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaDtSDao#merge(com.bean.ParaDtS)
	 */
	public ParaDtS merge(ParaDtS detachedInstance) {
		log.debug("merging ParaDtS instance");
		try {
			ParaDtS result = (ParaDtS) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ParaDtS instance) {
		log.debug("attaching dirty ParaDtS instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ParaDtS instance) {
		log.debug("attaching clean ParaDtS instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ParaDtSDao getFromApplicationContext(ApplicationContext ctx) {
		return (ParaDtSDao) ctx.getBean("paraDtSDao");
	}

	public Map<String, Integer> getCasePrdtSummary(int case_id, int top,
			int del_status) {
		Map<String, Integer> casePrdtSummaryMap = new HashMap<String, Integer>();
		Session session = this.getSession();
		SQLQuery sqlquery;

		String sql = "select dbo.fn_get_prdt_count(?,?,?) as product_count, "
				+ "dbo.fn_get_prdt_colo_count(?,?,?) as colo_count,"
				+ "dbo.fn_get_tyna_count(?,?,?) as tyna_count ";

		sqlquery = session.createSQLQuery(sql);
		sqlquery.setInteger(0, case_id);
		sqlquery.setInteger(1, top);
		sqlquery.setInteger(2, del_status);
		sqlquery.setInteger(3, case_id);
		sqlquery.setInteger(4, top);
		sqlquery.setInteger(5, del_status);
		sqlquery.setInteger(6, case_id);
		sqlquery.setInteger(7, top);
		sqlquery.setInteger(8, del_status);

		List<Object[]> resultSet = sqlquery.list();

		Integer coloCount = null;
		Integer productCount = null;
		Integer tynaCount = null;
		for (Object[] r : resultSet) {
			productCount = (Integer) r[0];
			coloCount = (Integer) r[1];
			tynaCount = (Integer) r[2];
		}
		casePrdtSummaryMap.put("productCount", productCount);
		casePrdtSummaryMap.put("coloCount", coloCount);
		casePrdtSummaryMap.put("tynaCount", tynaCount);

		return casePrdtSummaryMap;
	}
}