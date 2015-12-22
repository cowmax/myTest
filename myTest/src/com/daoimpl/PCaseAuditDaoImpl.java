package com.daoimpl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.PCaseAudit;
import com.dao.PCaseAuditDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * PCaseAudit entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bean.PCaseAudit
 * @author MyEclipse Persistence Tools
 */
public class PCaseAuditDaoImpl extends HibernateDaoSupport implements
		PCaseAuditDao {
	private static final Logger log = LoggerFactory
			.getLogger(PCaseAuditDaoImpl.class);
	// property constants
	public static final String CASE_ID = "caseId";
	public static final String AUDIT_RESULT = "auditResult";
	public static final String AUDIT_TEXT = "auditText";
	public static final String SYS_USER_ID = "sysUserId";

	protected void initDao() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dao.PCaseAuditDao#save(com.bean.PCaseAudit)
	 */
	public void save(PCaseAudit transientInstance) {
		log.debug("saving PCaseAudit instance");
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
	 * @see com.dao.PCaseAuditDao#delete(com.bean.PCaseAudit)
	 */
	public void delete(PCaseAudit persistentInstance) {
		log.debug("deleting PCaseAudit instance");
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
	 * @see com.dao.PCaseAuditDao#findById(java.lang.Integer)
	 */
	public PCaseAudit findById(java.lang.Integer id) {
		log.debug("getting PCaseAudit instance with id: " + id);
		try {
			PCaseAudit instance = (PCaseAudit) getHibernateTemplate().get(
					"com.bean.PCaseAudit", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dao.PCaseAuditDao#findByExample(com.bean.PCaseAudit)
	 */
	public List findByExample(PCaseAudit instance) {
		log.debug("finding PCaseAudit instance by example");
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
	 * @see com.dao.PCaseAuditDao#findByProperty(java.lang.String,
	 * java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding PCaseAudit instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from PCaseAudit as model where model."
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
	 * @see com.dao.PCaseAuditDao#findByCaseId(java.lang.Object)
	 */
	public List findByCaseId(Object caseId) {
		return findByProperty(CASE_ID, caseId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dao.PCaseAuditDao#findByAuditResult(java.lang.Object)
	 */
	public List findByAuditResult(Object auditResult) {
		return findByProperty(AUDIT_RESULT, auditResult);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dao.PCaseAuditDao#findByAuditText(java.lang.Object)
	 */
	public List findByAuditText(Object auditText) {
		return findByProperty(AUDIT_TEXT, auditText);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dao.PCaseAuditDao#findBySysUserId(java.lang.Object)
	 */
	public List findBySysUserId(Object sysUserId) {
		return findByProperty(SYS_USER_ID, sysUserId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dao.PCaseAuditDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all PCaseAudit instances");
		try {
			String queryString = "from PCaseAudit";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dao.PCaseAuditDao#merge(com.bean.PCaseAudit)
	 */
	public PCaseAudit merge(PCaseAudit detachedInstance) {
		log.debug("merging PCaseAudit instance");
		try {
			PCaseAudit result = (PCaseAudit) getHibernateTemplate().merge(
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
	 * @see com.dao.PCaseAuditDao#attachDirty(com.bean.PCaseAudit)
	 */
	public void attachDirty(PCaseAudit instance) {
		log.debug("attaching dirty PCaseAudit instance");
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
	 * @see com.dao.PCaseAuditDao#attachClean(com.bean.PCaseAudit)
	 */
	public void attachClean(PCaseAudit instance) {
		log.debug("attaching clean PCaseAudit instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PCaseAuditDao getFromApplicationContext(ApplicationContext ctx) {
		return (PCaseAuditDao) ctx.getBean("pCaseAuditDao");
	}
}