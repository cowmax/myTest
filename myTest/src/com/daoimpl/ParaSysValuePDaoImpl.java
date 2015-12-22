package com.daoimpl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.ParaSysValueP;
import com.dao.ParaSysValuePDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * ParaSysValueP entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bean.ParaSysValueP
 * @author MyEclipse Persistence Tools
 */
public class ParaSysValuePDaoImpl extends HibernateDaoSupport implements
		ParaSysValuePDao {
	private static final Logger log = LoggerFactory
			.getLogger(ParaSysValuePDaoImpl.class);
	// property constants
	public static final String OFF_DAY = "offDay";
	public static final String RE_NUM = "reNum";
	public static final String OWNER_RATIO = "ownerRatio";
	public static final String SYS_USER_ID = "sysUserId";

	protected void initDao() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaSysValuepDao#save(com.bean.ParaSysValueP)
	 */
	public void save(ParaSysValueP transientInstance) {
		log.debug("saving ParaSysValueP instance");
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
	 * @see com.daoimpl.ParaSysValuepDao#delete(com.bean.ParaSysValueP)
	 */
	public void delete(ParaSysValueP persistentInstance) {
		log.debug("deleting ParaSysValueP instance");
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
	 * @see com.daoimpl.ParaSysValuepDao#findById(java.lang.String)
	 */
	public ParaSysValueP findById(java.lang.String id) {
		log.debug("getting ParaSysValueP instance with id: " + id);
		try {
			ParaSysValueP instance = (ParaSysValueP) getHibernateTemplate()
					.get("com.bean.ParaSysValueP", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaSysValuepDao#findByExample(com.bean.ParaSysValueP)
	 */
	public List findByExample(ParaSysValueP instance) {
		log.debug("finding ParaSysValueP instance by example");
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
	 * @see com.daoimpl.ParaSysValuepDao#findByProperty(java.lang.String,
	 * java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ParaSysValueP instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ParaSysValueP as model where model."
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
	 * @see com.daoimpl.ParaSysValuepDao#findByOffDay(java.lang.Object)
	 */
	public List findByOffDay(Object offDay) {
		return findByProperty(OFF_DAY, offDay);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaSysValuepDao#findByReNum(java.lang.Object)
	 */
	public List findByReNum(Object reNum) {
		return findByProperty(RE_NUM, reNum);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaSysValuepDao#findByOwnerRatio(java.lang.Object)
	 */
	public List findByOwnerRatio(Object ownerRatio) {
		return findByProperty(OWNER_RATIO, ownerRatio);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaSysValuepDao#findBySysUserId(java.lang.Object)
	 */
	public List findBySysUserId(Object sysUserId) {
		return findByProperty(SYS_USER_ID, sysUserId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaSysValuepDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all ParaSysValueP instances");
		try {
			String queryString = "from ParaSysValueP";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.daoimpl.ParaSysValuepDao#merge(com.bean.ParaSysValueP)
	 */
	public ParaSysValueP merge(ParaSysValueP detachedInstance) {
		log.debug("merging ParaSysValueP instance");
		try {
			ParaSysValueP result = (ParaSysValueP) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ParaSysValueP instance) {
		log.debug("attaching dirty ParaSysValueP instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ParaSysValueP instance) {
		log.debug("attaching clean ParaSysValueP instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ParaSysValuePDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (ParaSysValuePDao) ctx.getBean("paraSysValuePDao");
	}
}