package com.daoimpl;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.Store;
import com.dao.StoreDao;

/**
 * A data access object (DAO) providing persistence and search support for Store
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.bean.Store
 * @author MyEclipse Persistence Tools
 */
public class StoreDaoImpl extends HibernateDaoSupport implements StoreDao {
	private static final Logger log = LoggerFactory.getLogger(StoreDaoImpl.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.StoreDao#save(com.bean.Store)
	 */
	public void save(Store transientInstance) {
		log.debug("saving Store instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.StoreDao#delete(com.bean.Store)
	 */
	public void delete(Store persistentInstance) {
		log.debug("deleting Store instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.StoreDao#findById(com.bean.Store)
	 */
	public Store findById(com.bean.Store id) {
		log.debug("getting Store instance with id: " + id);
		try {
			Store instance = (Store) getHibernateTemplate().get(
					"com.bean.Store", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.StoreDao#findByExample(com.bean.Store)
	 */
	public List findByExample(Store instance) {
		log.debug("finding Store instance by example");
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
	 * @see com.daoimpl.StoreDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Store instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Store as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.StoreDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all Store instances");
		try {
			String queryString = "from Store order by name";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.StoreDao#merge(com.bean.Store)
	 */
	public Store merge(Store detachedInstance) {
		log.debug("merging Store instance");
		try {
			Store result = (Store) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.StoreDao#attachDirty(com.bean.Store)
	 */
	public void attachDirty(Store instance) {
		log.debug("attaching dirty Store instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.StoreDao#attachClean(com.bean.Store)
	 */
	public void attachClean(Store instance) {
		log.debug("attaching clean Store instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static StoreDao getFromApplicationContext(ApplicationContext ctx) {
		return (StoreDao) ctx.getBean("StoreDao");
	}
}