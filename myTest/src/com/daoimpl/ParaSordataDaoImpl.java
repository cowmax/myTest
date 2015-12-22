package com.daoimpl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.ParaSordata;
import com.bean.ParaSordataId;
import com.dao.ParaSordataDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * ParaSordata entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bean.ParaSordata
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("unused")
public class ParaSordataDaoImpl extends HibernateDaoSupport implements
		ParaSordataDao {
	private static final Logger log = LoggerFactory
			.getLogger(ParaSordataDaoImpl.class);
	// property constants
	public static final String VALUE_RATIO = "valueRatio";
	public static final String VALUE_MIN = "valueMin";
	public static final String VALUE_MAX = "valueMax";
	public static final String VALUE_DESC = "valueDesc";
	public static final String SYS_USER_ID = "sysUserId";

	protected void initDao() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaSordataDao#save(com.bean.ParaSordata)
	 */
	public void save(ParaSordata transientInstance) {
		log.debug("saving ParaSordata instance");
		try {
			Session session = getSession();
			Query query = null;
			String sql = "insert into para_sordata_p (tyna,value_type,value_ratio,value_min,value_max,value_desc,sys_dt,sys_user_id) values ('"
					+ transientInstance.getId().getTyna()
					+ "','"
					+ transientInstance.getId().getValueType()
					+ "','"
					+ transientInstance.getValueRatio()
					+ "','"
					+ transientInstance.getValueMin()
					+ "','"
					+ transientInstance.getValueMax()
					+ "','"
					+ transientInstance.getValueDesc()
					+ "','"
					+ transientInstance.getSysDt()
					+ "','"
					+ transientInstance.getSysUserId() + "')";
			System.out.println("sql:  " + sql);
			query = session.createSQLQuery(sql);
			int i = query.executeUpdate();
			session.flush(); // ��ջ���
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaSordataDao#delete(com.bean.ParaSordata)
	 */
	public void delete(ParaSordata persistentInstance) {
		log.debug("deleting ParaSordata instance");
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
	 * @see com.daoimpl.ParaSordataDao#findById(com.bean.ParaSordataId)
	 */
	public ParaSordata findById(com.bean.ParaSordataId id) {
		log.debug("getting ParaSordata instance with id: " + id);
		try {
			ParaSordata instance = (ParaSordata) getHibernateTemplate().get(
					"com.bean.ParaSordata", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaSordataDao#findByExample(com.bean.ParaSordata)
	 */
	@SuppressWarnings("rawtypes")
	public List findByExample(ParaSordata instance) {
		log.debug("finding ParaSordata instance by example");
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
	 * @see com.daoimpl.ParaSordataDao#findByProperty(java.lang.String,
	 * java.lang.Object)
	 */
	@SuppressWarnings("rawtypes")
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ParaSordata instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ParaSordata as model where model."
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
	 * @see com.daoimpl.ParaSordataDao#findByValueRatio(java.lang.Object)
	 */
	@SuppressWarnings("rawtypes")
	public List findByValueRatio(Object valueRatio) {
		return findByProperty(VALUE_RATIO, valueRatio);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaSordataDao#findByValueMin(java.lang.Object)
	 */
	@SuppressWarnings("rawtypes")
	public List findByValueMin(Object valueMin) {
		return findByProperty(VALUE_MIN, valueMin);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaSordataDao#findByValueMax(java.lang.Object)
	 */
	@SuppressWarnings("rawtypes")
	public List findByValueMax(Object valueMax) {
		return findByProperty(VALUE_MAX, valueMax);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaSordataDao#findByValueDesc(java.lang.Object)
	 */
	@SuppressWarnings("rawtypes")
	public List findByValueDesc(Object valueDesc) {
		return findByProperty(VALUE_DESC, valueDesc);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaSordataDao#findBySysUserId(java.lang.Object)
	 */
	@SuppressWarnings("rawtypes")
	public List findBySysUserId(Object sysUserId) {
		return findByProperty(SYS_USER_ID, sysUserId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaSordataDao#findAll()
	 */
	@SuppressWarnings("rawtypes")
	public List findAll() {
		log.debug("finding all ParaSordata instances");
		try {
			String queryString = "from ParaSordata";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaSordataDao#merge(com.bean.ParaSordata)
	 */
	public ParaSordata merge(ParaSordata detachedInstance) {
		log.debug("merging ParaSordata instance");
		try {
			ParaSordata result = (ParaSordata) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaSordataDao#attachDirty(com.bean.ParaSordata)
	 */
	public void attachDirty(ParaSordata instance) {
		log.debug("attaching dirty ParaSordata instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaSordataDao#attachClean(com.bean.ParaSordata)
	 */
	public void attachClean(ParaSordata instance) {
		log.debug("attaching clean ParaSordata instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ParaSordataDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (ParaSordataDao) ctx.getBean("ParaSordataDAO");
	}
}