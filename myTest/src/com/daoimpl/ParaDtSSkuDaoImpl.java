package com.daoimpl;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.ParaDtSSku;
import com.dao.ParaDtSSkuDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * ParaDtSSku entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bean.ParaDtSSku
 * @author MyEclipse Persistence Tools
 */
public class ParaDtSSkuDaoImpl extends HibernateDaoSupport implements ParaDtSSkuDao {
	private static final Logger log = LoggerFactory
			.getLogger(ParaDtSSkuDaoImpl.class);
	// property constants
	public static final String CASE_ID = "caseId";
	public static final String PRODUCT_CODE = "productCode";
	public static final String COLO = "colo";
	public static final String CONA = "cona";
	public static final String SZID = "szid";
	public static final String SKU_CODE = "skuCode";
	public static final String STATUS = "status";
	public static final String SALES_NUM = "salesNum";
	public static final String STOCK = "stock";
	public static final String NEW_OLD_FLAG = "newOldFlag";
	public static final String _SCASE_ALL = "SCaseAll";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.dao.ParaDtSSkuDao#save(com.bean.ParaDtSSku)
	 */
	public void save(ParaDtSSku transientInstance) {
		log.debug("saving ParaDtSSku instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.ParaDtSSkuDao#delete(com.bean.ParaDtSSku)
	 */
	public void delete(ParaDtSSku persistentInstance) {
		log.debug("deleting ParaDtSSku instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.ParaDtSSkuDao#findById(java.lang.Integer)
	 */
	public ParaDtSSku findById(java.lang.Integer id) {
		log.debug("getting ParaDtSSku instance with id: " + id);
		try {
			ParaDtSSku instance = (ParaDtSSku) getHibernateTemplate().get(
					"com.bean.ParaDtSSku", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.ParaDtSSkuDao#findByExample(com.bean.ParaDtSSku)
	 */
	public List findByExample(ParaDtSSku instance) {
		log.debug("finding ParaDtSSku instance by example");
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
	 * @see com.dao.ParaDtSSkuDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ParaDtSSku instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ParaDtSSku as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.ParaDtSSkuDao#findByCaseId(java.lang.Object)
	 */
	public List findByCaseId(Object caseId) {
		return findByProperty(CASE_ID, caseId);
	}

	/* (non-Javadoc)
	 * @see com.dao.ParaDtSSkuDao#findByProductCode(java.lang.Object)
	 */
	public List findByProductCode(Object productCode) {
		return findByProperty(PRODUCT_CODE, productCode);
	}

	/* (non-Javadoc)
	 * @see com.dao.ParaDtSSkuDao#findByColo(java.lang.Object)
	 */
	public List findByColo(Object colo) {
		return findByProperty(COLO, colo);
	}

	/* (non-Javadoc)
	 * @see com.dao.ParaDtSSkuDao#findByCona(java.lang.Object)
	 */
	public List findByCona(Object cona) {
		return findByProperty(CONA, cona);
	}

	/* (non-Javadoc)
	 * @see com.dao.ParaDtSSkuDao#findBySzid(java.lang.Object)
	 */
	public List findBySzid(Object szid) {
		return findByProperty(SZID, szid);
	}

	/* (non-Javadoc)
	 * @see com.dao.ParaDtSSkuDao#findBySkuCode(java.lang.Object)
	 */
	public List findBySkuCode(Object skuCode) {
		return findByProperty(SKU_CODE, skuCode);
	}

	/* (non-Javadoc)
	 * @see com.dao.ParaDtSSkuDao#findByStatus(java.lang.Object)
	 */
	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	/* (non-Javadoc)
	 * @see com.dao.ParaDtSSkuDao#findBySalesNum(java.lang.Object)
	 */
	public List findBySalesNum(Object salesNum) {
		return findByProperty(SALES_NUM, salesNum);
	}

	/* (non-Javadoc)
	 * @see com.dao.ParaDtSSkuDao#findByStock(java.lang.Object)
	 */
	public List findByStock(Object stock) {
		return findByProperty(STOCK, stock);
	}

	/* (non-Javadoc)
	 * @see com.dao.ParaDtSSkuDao#findByNewOldFlag(java.lang.Object)
	 */
	public List findByNewOldFlag(Object newOldFlag) {
		return findByProperty(NEW_OLD_FLAG, newOldFlag);
	}

	/* (non-Javadoc)
	 * @see com.dao.ParaDtSSkuDao#findBySCaseAll(java.lang.Object)
	 */
	public List findBySCaseAll(Object SCaseAll) {
		return findByProperty(_SCASE_ALL, SCaseAll);
	}

	/* (non-Javadoc)
	 * @see com.dao.ParaDtSSkuDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all ParaDtSSku instances");
		try {
			String queryString = "from ParaDtSSku";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.ParaDtSSkuDao#merge(com.bean.ParaDtSSku)
	 */
	public ParaDtSSku merge(ParaDtSSku detachedInstance) {
		log.debug("merging ParaDtSSku instance");
		try {
			ParaDtSSku result = (ParaDtSSku) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.ParaDtSSkuDao#attachDirty(com.bean.ParaDtSSku)
	 */
	public void attachDirty(ParaDtSSku instance) {
		log.debug("attaching dirty ParaDtSSku instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.dao.ParaDtSSkuDao#attachClean(com.bean.ParaDtSSku)
	 */
	public void attachClean(ParaDtSSku instance) {
		log.debug("attaching clean ParaDtSSku instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ParaDtSSkuDao getFromApplicationContext(ApplicationContext ctx) {
		return (ParaDtSSkuDao) ctx.getBean("paraDtSSkuDao");
	}
}