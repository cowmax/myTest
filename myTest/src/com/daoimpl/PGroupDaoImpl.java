package com.daoimpl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.PGroup;
import com.bean.PGroupUser;
import com.bean.PRole;
import com.bean.PUser;
import com.dao.PGroupDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * PGroup entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bean.PGroup
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("rawtypes")
public class PGroupDaoImpl extends HibernateDaoSupport implements PGroupDao {
	private static final Logger log = LoggerFactory.getLogger(PGroupDaoImpl.class);
	// property constants
	public static final String GROUP_NAME = "groupName";
	public static final String GROUP_DESC = "groupDesc";
	public static final String ROLE_ID = "roleId";
	public int count;
	private SessionFactory sessionFactory;
	private Query query;
	private List<PGroup> pglis;

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.PGroupDao#save(com.bean.PGroup)
	 */
	public void save(PGroup transientInstance) {
		log.debug("saving PGroup instance");
		try {
			Session session = getSession();  
			Query query=null;
			String sql="insert into p_group (group_name,group_desc,role_id,create_dt,last_dt) " +
					"values (:group_name,:group_desc,:role_id,:create_dt,:last_dt)";
			query=session.createSQLQuery(sql);
			query.setString("group_name", transientInstance.getGroupName());
			query.setString("group_desc", transientInstance.getGroupDesc());
			query.setInteger("role_id", transientInstance.getRoleId().getRoleId());
			query.setTimestamp("create_dt", transientInstance.getCreateDt());
			query.setTimestamp("last_dt", transientInstance.getLastDt());
			query.executeUpdate();  
			session.flush();    //清空缓存  
			log.debug("save successful");

		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.PGroupDao#delete(com.bean.PGroup)
	 */
	public int delete(PGroup persistentInstance) {
		log.debug("deleting PGroup instance");
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
	 * @see com.daoimpl.PGroupDao#findById(java.lang.Integer)
	 */
	public PGroup findById(java.lang.Integer id) {
		log.debug("getting PGroup instance with id: " + id);
		try {
			PGroup instance = (PGroup) getHibernateTemplate().get(
					"com.bean.PGroup", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.PGroupDao#findByExample(com.bean.PGroup)
	 */
	public List findByExample(PGroup instance) {
		log.debug("finding PGroup instance by example");
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
	 * @see com.daoimpl.PGroupDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding PGroup instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from PGroup as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.PGroupDao#findByGroupName(java.lang.Object)
	 */
	public List findByGroupName(Object groupName) {
		return findByProperty(GROUP_NAME, groupName);
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.PGroupDao#findByGroupDesc(java.lang.Object)
	 */
	public List findByGroupDesc(Object groupDesc) {
		return findByProperty(GROUP_DESC, groupDesc);
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.PGroupDao#findByRoleId(java.lang.Object)
	 */
	public List findByRoleId(Object roleId) {
		List list=null;
		Session session = getSession();  
		Query query=null;
		String sql="select * from p_group where role_id = " + roleId;
		query=session.createSQLQuery(sql);
		list=query.list();  
		session.flush();    //清空缓存  
		return list;
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.PGroupDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all PGroup instances");
		try {
			String queryString = "from PGroup";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.PGroupDao#merge(com.bean.PGroup)
	 */
	public PGroup merge(PGroup detachedInstance) {
		log.debug("merging PGroup instance");
		try {
			PGroup result = (PGroup) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PGroup instance) {
		log.debug("attaching dirty PGroup instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PGroup instance) {
		log.debug("attaching clean PGroup instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PGroupDao getFromApplicationContext(ApplicationContext ctx) {
		return (PGroupDao) ctx.getBean("pgdao");
	}
	/* (non-Javadoc)
	 * @see com.daoimpl.PGroupDao#findRoleById(int)
	 */
	public PRole findRoleById(int roleId) {
		log.debug("getting PRole instance with id: " + roleId);
		try {
			PRole instance = (PRole) getHibernateTemplate().get(
					"com.bean.PRole", roleId);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public boolean findByGidAndGname(int groupId,String groupName) {
		List list=null;
		Session session = getSession();  
		Query query=null;
		String hql="from PGroup where groupId =:groupId and groupName=:groupName"; 
		query=session.createQuery(hql);
		query.setInteger("groupId",groupId); 
		query.setString("groupName",groupName);
		list=query.list(); 
		if(list.size()>0){
			return true;
		}else{
			list=this.findByGroupName(groupName);
			if(list.size()<=0){
				return true;
			}
		}
		session.flush();    //清空缓存  
		return false;
	}

	public List<PGroup> getLisByPage(String page, String rows, String gname,
			String gdesc, int rid) {
		Session session = getSession();  
		Query query=null;
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页  
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行
		StringBuffer sql=new StringBuffer("from PGroup where 0 = 0 ");
		
		if(!gname.equals("")){
			sql.append(" and groupName like :groupName");
		}
		if(!gdesc.equals("")){
			sql.append(" and groupDesc like :groupDesc");
		}
		if(rid!=-1){
			sql.append(" and roleId = :roleId");
		}
		sql.append(" order by createDt desc");
		query=session.createQuery(sql.toString());
		if(!gname.equals("")){
			query.setString("groupName","%"+gname+"%");
		}
		if(!gdesc.equals("")){
			query.setString("groupDesc","%"+gdesc+"%");
		}
		if(rid!=-1){
			query.setInteger("roleId",rid);
		}
		pglis=query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
		return pglis;
	}

	public List findGroupByUserId(String userId) {
		List<PGroup> list=new ArrayList<PGroup>();
		Session session = getSession();  
		Query query=null;
		String sql="select * from p_group g inner join p_role r on g.role_id=r.role_id where g.group_id in (select group_id from p_group_user where user_id=:user_id)"; 
		query=session.createSQLQuery(sql).addEntity(PGroup.class).addEntity(PRole.class);
		query.setString("user_id",userId); 
		List<Object[]> resultSet=query.list(); 
		list.clear();

		for (Object[] r : resultSet) 
		{
			PGroup g = (PGroup)r[0];
			g.setRoleId((PRole)r[1]);
			list.add(g);
		}
		return list;
	}

	public List getGroupExceptUgroup(String userId) {
		List list=null;
		Session session = getSession();  
		Query query=null;
		String sql="select * from p_group where group_id not in (select group_id from p_group_user where user_id=:user_id)"; 
		query=session.createSQLQuery(sql).addEntity(PGroup.class);
		query.setString("user_id",userId); 
		list=query.list(); 
		return list;
	}
}