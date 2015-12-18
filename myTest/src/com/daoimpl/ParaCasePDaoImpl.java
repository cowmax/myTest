package com.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.ParaCaseP;
import com.dao.ParaCasePDao;
import com.bean.Store;

/**
 * A data access object (DAO) providing persistence and search support for
 * ParaCaseP entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bean.ParaCaseP
 * @author MyEclipse Persistence Tools
 */
public class ParaCasePDaoImpl extends HibernateDaoSupport implements ParaCasePDao {
	private static final Logger log = LoggerFactory
			.getLogger(ParaCasePDaoImpl.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.ParaCasePDao#save(com.bean.ParaCaseP)
	 */
	public void save(ParaCaseP transientInstance) {
		try {
			Session session = getSession();  
			SQLQuery query=null;
			String sql="insert into para_case_p (case_code,case_name,chal_cd,case_level,pre_num,brde,num,c_type,sys_user_id,sys_dt) " +
					"values (:case_code,:case_name,:chal_cd,:case_level,:pre_num,:brde,:num,:c_type,:sys_user_id,:sys_dt)";
			query=session.createSQLQuery(sql);
			query.setString("case_code", transientInstance.getCaseCode());
			query.setString("case_name", transientInstance.getCaseName());
			query.setString("chal_cd", transientInstance.getChalCd().getCode());
			query.setString("case_level", transientInstance.getCaseLevel());
			query.setInteger("pre_num", transientInstance.getPreNum());
			query.setString("c_type", transientInstance.getCType());
			query.setString("sys_user_id", transientInstance.getSysUserId());
			query.setString("brde", transientInstance.getBrde());
			if(transientInstance.getNum()==null){
				query.setString("num", null);
			}else{
				query.setInteger("num", transientInstance.getNum());
			}
			query.setTimestamp("sys_dt", transientInstance.getSysDt());
			query.executeUpdate();  
			session.flush();    //Çå¿Õ»º´æ  
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.ParaCasePDao#delete(com.bean.ParaCaseP)
	 */
	public void delete(ParaCaseP persistentInstance) {
		log.debug("deleting ParaCaseP instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.ParaCasePDao#findById(com.bean.ParaCaseP)
	 */
	public ParaCaseP findById(java.lang.String id) {
		log.debug("getting ParaCaseP instance with id: " + id);
		try {
			ParaCaseP instance = null;
			SQLQuery query = getSession().createSQLQuery("select {a.*}, {b.*} from para_case_p a join Store b on a.chal_cd = b.code where a.case_code=:id");
			query.addEntity("a", ParaCaseP.class);
			query.addJoin("b", "a.chalCd");
			query.setString("id", id);
			List pcList = query.list();
			Object[] objs = (Object[])pcList.get(0);
			instance = (ParaCaseP)objs[0];
			instance.chalCd = (Store)objs[1];
			
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.ParaCasePDao#findByExample(com.bean.ParaCaseP)
	 */
	public List findByExample(ParaCaseP instance) {
		log.debug("finding ParaCaseP instance by example");
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
	 * @see com.daoimpl.ParaCasePDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ParaCaseP instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ParaCaseP as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.ParaCasePDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all ParaCaseP instances");
		try {
			List<ParaCaseP> PcPList=new ArrayList<ParaCaseP>();
			SQLQuery query = getSession().createSQLQuery("select {a.*}, {b.*} from para_case_p a inner join Store b on a.chal_cd=b.Code");
			query.addEntity("a", ParaCaseP.class);
			query.addEntity("b", Store.class);
			List<Object[]> resultSet=query.list(); 
			PcPList.clear();
			for (Object[] r : resultSet) 
			{
				ParaCaseP p = (ParaCaseP)r[0];
				p.setChalCd((Store)r[1]);
				PcPList.add(p);
			}
			return PcPList;

			//String queryString = "from ParaCaseP";
			//return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.ParaCasePDao#merge(com.bean.ParaCaseP)
	 */
	public int merge(ParaCaseP detachedInstance) {
		try {
			Session session = getSession();  
			Query query=null;
			String sql="update para_case_p set case_name=:case_name,chal_cd=:chal_cd,case_level=:case_level,pre_num=:pre_num,brde=:brde,num=:num,c_type=:c_type,sys_user_id=:sys_user_id,sys_dt=:sys_dt where case_code=:case_code" ;
			query=session.createSQLQuery(sql);
			query.setString("case_code", detachedInstance.getCaseCode());
			query.setString("case_name", detachedInstance.getCaseName());
			query.setString("chal_cd", detachedInstance.getChalCd().getCode());
		    query.setString("case_level", detachedInstance.getCaseLevel());
			query.setInteger("pre_num", detachedInstance.getPreNum());
			query.setString("c_type", detachedInstance.getCType());
			query.setString("sys_user_id", detachedInstance.getSysUserId());
			query.setString("brde", detachedInstance.getBrde());
		    query.setInteger("num", detachedInstance.getNum());
			query.setTimestamp("sys_dt", detachedInstance.getSysDt());
			int i=query.executeUpdate();  
			session.flush();    //Çå¿Õ»º´æ  
			return i ;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.ParaCasePDao#attachDirty(com.bean.ParaCaseP)
	 */
	public void attachDirty(ParaCaseP instance) {
		log.debug("attaching dirty ParaCaseP instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.ParaCasePDao#attachClean(com.bean.ParaCaseP)
	 */
	public void attachClean(ParaCaseP instance) {
		log.debug("attaching clean ParaCaseP instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ParaCasePDao getFromApplicationContext(ApplicationContext ctx) {
		return (ParaCasePDao) ctx.getBean("ParaCasePDao");
	}

}