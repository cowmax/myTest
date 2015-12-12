package com.daoimpl;

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

import com.bean.BProductP;
import com.bean.ParaDt;
import com.bean.ParaDtS;
import com.bean.ParaDtSSku;
import com.dao.BProductPDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * BProductP entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bean.BProductP
 * @author MyEclipse Persistence Tools
 */
public class BProductPDaoImpl extends HibernateDaoSupport implements BProductPDao {
	private static final Logger log = LoggerFactory
			.getLogger(BProductPDaoImpl.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.BProductPDao#save(com.bean.BProductP)
	 */
	public void save(BProductP transientInstance) {
		log.debug("saving BProductP instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.BProductPDao#delete(com.bean.BProductP)
	 */
	public void delete(BProductP persistentInstance) {
		log.debug("deleting BProductP instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.BProductPDao#findById(com.bean.BProductPId)
	 */
	public BProductP findById(String id) {
		log.debug("getting BProductP instance with id: " + id);
		try {
			BProductP instance = (BProductP) getHibernateTemplate().get(
					"com.bean.BProductP", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.BProductPDao#findByExample(com.bean.BProductP)
	 */
	public List findByExample(BProductP instance) {
		log.debug("finding BProductP instance by example");
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
	 * @see com.daoimpl.BProductPDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding BProductP instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from BProductP as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.BProductPDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all BProductP instances");
		try {
			String queryString = "from BProductP";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.BProductPDao#merge(com.bean.BProductP)
	 */
	public BProductP merge(BProductP detachedInstance) {
		log.debug("merging BProductP instance");
		try {
			BProductP result = (BProductP) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.BProductPDao#attachDirty(com.bean.BProductP)
	 */
	public void attachDirty(BProductP instance) {
		log.debug("attaching dirty BProductP instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.BProductPDao#attachClean(com.bean.BProductP)
	 */
	public void attachClean(BProductP instance) {
		log.debug("attaching clean BProductP instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BProductPDao getFromApplicationContext(ApplicationContext ctx) {
		return (BProductPDao) ctx.getBean("BProductPDao");
	}

	public List findExceptByCaseId(Integer caseId) {
		Session session = getSession();  
		SQLQuery query=null;
		String sql="select * from b_product_p " +
						"where product_code not in " +
						"(select product_cd from para_dt_s where case_id=:case_id)";
		query=session.createSQLQuery(sql);
		query.setInteger("case_id", caseId);
		query.addEntity(BProductP.class);
		List<BProductP> bps=query.list(); 
		session.flush();    //Çå¿Õ»º´æ  
		return bps;
	}

	public Map<String, String> findColorByProductCd(String productCode) {
		Session session = getSession();  
		SQLQuery query=null;
		Map<String, String> colors=new HashMap<String, String>();
		
		String sql="select distinct colo,cona from b_product_vm where product_code=:productCode";
		query=session.createSQLQuery(sql);
		query.setString("productCode", productCode);
		List<Object[]> resultSet = query.list(); 
		
		for (Object[] r : resultSet) 
		{
			String key=(String)r[0];
			String value=(String)r[1];
			colors.put(key, value);
		}
		session.flush();    //Çå¿Õ»º´æ  
		return colors;
		
	}
}