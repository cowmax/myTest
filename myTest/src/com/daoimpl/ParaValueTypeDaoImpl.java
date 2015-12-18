package com.daoimpl;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.ParaValueType;
import com.dao.ParaValueTypeDao;

public class ParaValueTypeDaoImpl extends HibernateDaoSupport implements ParaValueTypeDao {
	private static final Logger log = LoggerFactory
			.getLogger(ParaValueTypeDaoImpl.class);
	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.paraSardataTypeDao#save(com.bean.ParaSardataType)
	 */
	/* (non-Javadoc)
	 * @see com.daoimpl.ParaSardataTypeDao#save(com.bean.ParaSardataType)
	 */
	public void save(ParaValueType transientInstance) {
		log.debug("saving ParaValueType instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.paraSardataTypeDao#delete(com.bean.ParaSardataType)
	 */
	/* (non-Javadoc)
	 * @see com.daoimpl.ParaSardataTypeDao#delete(com.bean.ParaSardataType)
	 */
	public void delete(ParaValueType persistentInstance) {
		log.debug("deleting ParaValueType instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}


	public ParaValueType findById(java.lang.String id) {
		log.debug("getting ParaValueType instance with id: " + id);
		try {
			ParaValueType instance = (ParaValueType) getHibernateTemplate()
					.get("com.bean.ParaValueType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.paraSardataTypeDao#findByExample(com.bean.ParaSardataType)
	 */
	/* (non-Javadoc)
	 * @see com.daoimpl.ParaSardataTypeDao#findByExample(com.bean.ParaSardataType)
	 */
	public List findByExample(ParaValueType instance) {
		log.debug("finding ParaValueType instance by example");
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


	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ParaValueType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ParaValueType as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.paraSardataTypeDao#findByValTypeName(java.lang.Object)
	 */
	/* (non-Javadoc)
	 * @see com.daoimpl.ParaSardataTypeDao#findByValTypeName(java.lang.Object)
	 */
	public List findByValTypeName(Object valTypeName) {
		return findByProperty(VAL_TYPE_NAME, valTypeName);
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.paraSardataTypeDao#findByValTypeDescription(java.lang.Object)
	 */
	/* (non-Javadoc)
	 * @see com.daoimpl.ParaSardataTypeDao#findByValTypeDescription(java.lang.Object)
	 */
	public List findByValTypeDescription(Object valTypeDescription) {
		return findByProperty(VAL_TYPE_DESCRIPTION, valTypeDescription);
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.paraSardataTypeDao#findByTag(java.lang.Object)
	 */
	/* (non-Javadoc)
	 * @see com.daoimpl.ParaSardataTypeDao#findByTag(java.lang.Object)
	 */
	public List findByTag(Object tag) {
		return findByProperty(TAG, tag);
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.paraSardataTypeDao#findAll()
	 */
	/* (non-Javadoc)
	 * @see com.daoimpl.ParaSardataTypeDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all ParaValueType instances");
		try {
			String queryString = "from ParaValueType";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.paraSardataTypeDao#merge(com.bean.ParaSardataType)
	 */
	/* (non-Javadoc)
	 * @see com.daoimpl.ParaSardataTypeDao#merge(com.bean.ParaSardataType)
	 */
	public ParaValueType merge(ParaValueType detachedInstance) {
		log.debug("merging ParaValueType instance");
		try {
			ParaValueType result = (ParaValueType) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.paraSardataTypeDao#attachDirty(com.bean.ParaSardataType)
	 */
	/* (non-Javadoc)
	 * @see com.daoimpl.ParaSardataTypeDao#attachDirty(com.bean.ParaSardataType)
	 */
	public void attachDirty(ParaValueType instance) {
		log.debug("attaching dirty ParaValueType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.paraSardataTypeDao#attachClean(com.bean.ParaSardataType)
	 */
	/* (non-Javadoc)
	 * @see com.daoimpl.ParaSardataTypeDao#attachClean(com.bean.ParaSardataType)
	 */
	public void attachClean(ParaValueType instance) {
		log.debug("attaching clean ParaValueType instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ParaValueTypeDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (ParaValueTypeDao) ctx.getBean("ParaSardataTypeDAO");
	}
}