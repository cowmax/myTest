package com.daoimpl;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.PMenu;
import com.dao.PMenuDao;

/**
 * A data access object (DAO) providing persistence and search support for PMenu
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.bean.PMenu
 * @author MyEclipse Persistence Tools
 */
public class PMenuDaoImpl extends HibernateDaoSupport implements PMenuDao {
	private static final Logger log = LoggerFactory.getLogger(PMenuDaoImpl.class);
	// property constants
	public static final String PMID = "pmid";
	public static final String MNAME = "mname";
	public static final String MURL = "murl";
	public static final String SYS_USER_ID = "sysUserId";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.bean.PMenuDao#save(com.bean.PMenu)
	 */
	public void save(PMenu transientInstance) {
		log.debug("saving PMenu instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.PMenuDao#delete(com.bean.PMenu)
	 */
	public void delete(PMenu persistentInstance) {
		log.debug("deleting PMenu instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.PMenuDao#findById(java.lang.String)
	 */
	public PMenu findById(java.lang.String id) {
		log.debug("getting PMenu instance with id: " + id);
		try {
			PMenu instance = (PMenu) getHibernateTemplate().get(
					"com.bean.PMenu", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.PMenuDao#findByExample(com.bean.PMenu)
	 */
	public List findByExample(PMenu instance) {
		log.debug("finding PMenu instance by example");
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

	/* (non-Javadoc)
	 * @see com.bean.PMenuDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding PMenu instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from PMenu as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.PMenuDao#findByPmid(java.lang.Object)
	 */
	public List findByPmid(Object pmid) {
		return findByProperty(PMID, pmid);
	}

	/* (non-Javadoc)
	 * @see com.bean.PMenuDao#findByMname(java.lang.Object)
	 */
	public List findByMname(Object mname) {
		return findByProperty(MNAME, mname);
	}

	/* (non-Javadoc)
	 * @see com.bean.PMenuDao#findByMurl(java.lang.Object)
	 */
	public List findByMurl(Object murl) {
		return findByProperty(MURL, murl);
	}

	/* (non-Javadoc)
	 * @see com.bean.PMenuDao#findBySysUserId(java.lang.Object)
	 */
	public List findBySysUserId(Object sysUserId) {
		return findByProperty(SYS_USER_ID, sysUserId);
	}

	/* (non-Javadoc)
	 * @see com.bean.PMenuDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all PMenu instances");
		try {
			String queryString = "from PMenu";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.PMenuDao#merge(com.bean.PMenu)
	 */
	public PMenu merge(PMenu detachedInstance) {
		log.debug("merging PMenu instance");
		try {
			PMenu result = (PMenu) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PMenu instance) {
		log.debug("attaching dirty PMenu instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PMenu instance) {
		log.debug("attaching clean PMenu instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PMenuDao getFromApplicationContext(ApplicationContext ctx) {
		return (PMenuDao) ctx.getBean("pmenuDao");
	}
}