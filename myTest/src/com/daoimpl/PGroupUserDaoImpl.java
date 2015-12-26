package com.daoimpl;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.PGroup;
import com.bean.PGroupUser;
import com.bean.PUser;
import com.dao.PGroupUserDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * PGroupUser entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bean.PGroupUser
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes,")
public class PGroupUserDaoImpl extends HibernateDaoSupport implements PGroupUserDao {
	private static final Logger log = LoggerFactory
			.getLogger(PGroupUserDaoImpl.class);
	// property constants
	public static final String GROUP_ID = "groupId";
	public static final String USER_ID = "userId";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.bean.PGroupUserDao#save(com.bean.PGroupUser)
	 */
	public void save(int groupId,String userId) {
		Session session = getSession();  
		Query query=null;
		String sql="insert into p_group_user (group_id,user_id) " +
				"values (:group_id,:user_id)";
		query=session.createSQLQuery(sql);
		query.setInteger("group_id", groupId);
		query.setString("user_id",userId);
		query.executeUpdate();  
		session.flush();    //Çå¿Õ»º´æ  
		log.debug("save successful");
	}

	/* (non-Javadoc)
	 * @see com.bean.PGroupUserDao#delete(com.bean.PGroupUser)
	 */
	public void delete(int groupId,String userId) {
		Session session = getSession();  
		Query query=null;
		String sql="delete p_group_user where group_id=:group_id and user_id=:user_id";
		query=session.createSQLQuery(sql);
		query.setInteger("group_id", groupId);
		query.setString("user_id",userId);
		query.executeUpdate();  
		session.flush();    //Çå¿Õ»º´æ  
		log.debug("save successful");
	}

	/* (non-Javadoc)
	 * @see com.bean.PGroupUserDao#findById(java.lang.Integer)
	 */
	public PGroupUser findById(java.lang.Integer id) {
		log.debug("getting PGroupUser instance with id: " + id);
		try {
			PGroupUser instance = (PGroupUser) getHibernateTemplate().get(
					"com.bean.PGroupUser", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.PGroupUserDao#findByExample(com.bean.PGroupUser)
	 */
	public List findByExample(PGroupUser instance) {
		log.debug("finding PGroupUser instance by example");
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
	 * @see com.bean.PGroupUserDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding PGroupUser instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from PGroupUser as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.PGroupUserDao#findByGroupId(java.lang.Object)
	 */
	public List findByGroupId(int groupId) {
		List list=null;
		Session session = getSession();  
		Query query=null;
		String hql="from PGroupUser where groupId =:groupId"; 
		query=session.createQuery(hql);
		query.setInteger("groupId", groupId); 
		list=query.list();
		session.flush();    //Çå¿Õ»º´æ  
		return list;
	}

	/* (non-Javadoc)
	 * @see com.bean.PGroupUserDao#findByUserId(java.lang.Object)
	 */
	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	/* (non-Javadoc)
	 * @see com.bean.PGroupUserDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all PGroupUser instances");
		try {
			String queryString = "from PGroupUser";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.PGroupUserDao#merge(com.bean.PGroupUser)
	 */
	public PGroupUser merge(PGroupUser detachedInstance) {
		log.debug("merging PGroupUser instance");
		try {
			PGroupUser result = (PGroupUser) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PGroupUser instance) {
		log.debug("attaching dirty PGroupUser instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PGroupUser instance) {
		log.debug("attaching clean PGroupUser instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PGroupUserDao getFromApplicationContext(ApplicationContext ctx) {
		return (PGroupUserDao) ctx.getBean("pgudao");
	}

	public PGroup findGroupById(int gid) {
		log.debug("getting PGroup instance with id: " + gid);
		try {
			PGroup instance = (PGroup) getHibernateTemplate().get(
					"com.bean.PGroup", gid);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public PUser findUserById(String uid) {
		log.debug("getting PUser instance with id: " + uid);
		try {
			PUser instance = (PUser) getHibernateTemplate().get(
					"com.bean.PUser", uid);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}