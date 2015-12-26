package com.daoimpl;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.PRole;
import com.dao.PRoleDao;

/**
 * A data access object (DAO) providing persistence and search support for PRole
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.bean.PRole
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class PRoleDaoImpl extends HibernateDaoSupport implements PRoleDao {
	private static final Logger log = LoggerFactory.getLogger(PRoleDaoImpl.class);
	// property constants
	public static final String ROLE_NAME = "roleName";
	public static final String ROLE_DESC = "roleDesc";
	public int count;

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.PRoleDao#save(com.bean.PRole)
	 */
	public void save(PRole transientInstance) {
		log.debug("saving PRole instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.PRoleDao#delete(com.bean.PRole)
	 */

	public int delete(PRole persistentInstance) {
		log.debug("deleting PRole instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
			count=1;
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			count=0;
			throw re;
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.PRoleDao#findById(java.lang.Integer)
	 */
	public PRole findById(java.lang.Integer id) {
		log.debug("getting PRole instance with id: " + id);
		try {
			PRole instance = (PRole) getHibernateTemplate().get(
					"com.bean.PRole", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.PRoleDao#findByExample(com.bean.PRole)
	 */
	public List findByExample(PRole instance) {
		log.debug("finding PRole instance by example");
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
	 * @see com.daoimpl.PRoleDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding PRole instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from PRole as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.PRoleDao#findByRoleName(java.lang.Object)
	 */
	public List findByRoleName(Object roleName) {
		return findByProperty(ROLE_NAME, roleName);
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.PRoleDao#findByRoleDesc(java.lang.Object)
	 */
	public List findByRoleDesc(Object roleDesc) {
		return findByProperty(ROLE_DESC, roleDesc);
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.PRoleDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all PRole instances");
		try {
			String queryString = "from PRole";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.PRoleDao#merge(com.bean.PRole)
	 */
	public PRole merge(PRole detachedInstance) {
		log.debug("merging PRole instance");
		try {
			PRole result = (PRole) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PRole instance) {
		log.debug("attaching dirty PRole instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PRole instance) {
		log.debug("attaching clean PRole instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PRoleDao getFromApplicationContext(ApplicationContext ctx) {
		return (PRoleDao) ctx.getBean("prdao");
	}

	public boolean findByRidAndRname(int roleId, String roleName) {
		List list=null;
		Session session = getSession();  
		Query query=null;
		String hql="from PRole where roleId =:roleId and roleName=:roleName"; 
		query=session.createQuery(hql);
		query.setInteger("roleId",roleId); 
		query.setString("roleName",roleName);
		list=query.list(); 
		if(list.size()>0){
			PRole role=(PRole)list.get(0);
			return true;
		}else{
			list=this.findByRoleName(roleName);
			if(list.size()<=0){
				return true;
			}
		}
		session.flush();    //Çå¿Õ»º´æ  
		return false;
	}
}