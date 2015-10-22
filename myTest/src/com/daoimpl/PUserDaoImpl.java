package com.daoimpl;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.PUser;
import com.dao.PUserDao;

/**
 * A data access object (DAO) providing persistence and search support for PUser
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.bean.PUser
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class PUserDaoImpl extends HibernateDaoSupport implements PUserDao {
	private static final Logger log = LoggerFactory.getLogger(PUserDaoImpl.class);
	// property constants
	public static final String USER_NAME = "userName";
	public static final String USER_PWD = "userPwd";
	public static final String USER_DESC = "userDesc";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.bean.PUserDao#save(com.bean.PUser)
	 */
	public void save(PUser transientInstance) {
		log.debug("saving PUser instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.PUserDao#delete(com.bean.PUser)
	 */
	public void delete(PUser persistentInstance) {
		log.debug("deleting PUser instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.PUserDao#findById(java.lang.String)
	 */
	public PUser findById(java.lang.String id) {
		log.debug("getting PUser instance with id: " + id);
		try {
			PUser instance = (PUser) getHibernateTemplate().get(
					"com.bean.PUser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.PUserDao#findByExample(com.bean.PUser)
	 */
	public List findByExample(PUser instance) {
		log.debug("finding PUser instance by example");
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
	 * @see com.bean.PUserDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding PUser instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from PUser as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.PUserDao#findByUserName(java.lang.Object)
	 */
	public List findByUserName(Object userName) {
		return findByProperty(USER_NAME, userName);
	}

	/* (non-Javadoc)
	 * @see com.bean.PUserDao#findByUserPwd(java.lang.Object)
	 */
	public List findByUserPwd(Object userPwd) {
		return findByProperty(USER_PWD, userPwd);
	}

	/* (non-Javadoc)
	 * @see com.bean.PUserDao#findByUserDesc(java.lang.Object)
	 */
	public List findByUserDesc(Object userDesc) {
		return findByProperty(USER_DESC, userDesc);
	}

	/* (non-Javadoc)
	 * @see com.bean.PUserDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all PUser instances");
		try {
			String queryString = "from PUser";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.PUserDao#merge(com.bean.PUser)
	 */
	public PUser merge(PUser detachedInstance) {
		log.debug("merging PUser instance");
		try {
			PUser result = (PUser) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PUser instance) {
		log.debug("attaching dirty PUser instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PUser instance) {
		log.debug("attaching clean PUser instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PUserDao getFromApplicationContext(ApplicationContext ctx) {
		return (PUserDao) ctx.getBean("pudao");
	}
	
}