package com.daoimpl;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.PGroupMenu;
import com.bean.PGroupMenuId;
import com.dao.PGroupMenuDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * PGroupMenu entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bean.PGroupMenu
 * @author MyEclipse Persistence Tools
 */
public class PGroupMenuDaoImpl extends HibernateDaoSupport implements
		PGroupMenuDao {
	private static final Logger log = LoggerFactory
			.getLogger(PGroupMenuDaoImpl.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bean.PGroupMenuDao#save(com.bean.PGroupMenu)
	 */
	public void save(PGroupMenu transientInstance) {
		log.debug("saving PGroupMenu instance");
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
	 * @see com.bean.PGroupMenuDao#delete(com.bean.PGroupMenu)
	 */
	public void delete(PGroupMenu persistentInstance) {
		log.debug("deleting PGroupMenu instance");
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
	 * @see com.bean.PGroupMenuDao#findById(com.bean.PGroupMenuId)
	 */
	public PGroupMenu findById(com.bean.PGroupMenuId id) {
		log.debug("getting PGroupMenu instance with id: " + id);
		try {
			PGroupMenu instance = (PGroupMenu) getHibernateTemplate().get(
					"com.bean.PGroupMenu", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bean.PGroupMenuDao#findByExample(com.bean.PGroupMenu)
	 */
	public List findByExample(PGroupMenu instance) {
		log.debug("finding PGroupMenu instance by example");
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
	 * @see com.bean.PGroupMenuDao#findByProperty(java.lang.String,
	 * java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding PGroupMenu instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from PGroupMenu as model where model."
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
	 * @see com.bean.PGroupMenuDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all PGroupMenu instances");
		try {
			String queryString = "from PGroupMenu";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bean.PGroupMenuDao#merge(com.bean.PGroupMenu)
	 */
	public PGroupMenu merge(PGroupMenu detachedInstance) {
		log.debug("merging PGroupMenu instance");
		try {
			PGroupMenu result = (PGroupMenu) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PGroupMenu instance) {
		log.debug("attaching dirty PGroupMenu instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PGroupMenu instance) {
		log.debug("attaching clean PGroupMenu instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PGroupMenuDao getFromApplicationContext(ApplicationContext ctx) {
		return (PGroupMenuDao) ctx.getBean("pgroupMenuDao");
	}
}