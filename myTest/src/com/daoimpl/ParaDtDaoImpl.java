package com.daoimpl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.BProductP;
import com.bean.ParaCaseP;
import com.bean.ParaDt;
import com.bean.ParaDtS;
import com.bean.RefactorParaDt;
import com.bean.Store;
import com.dao.ParaDtDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * ParaDt entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bean.ParaDt
 * @author MyEclipse Persistence Tools
 */
public class ParaDtDaoImpl extends HibernateDaoSupport implements ParaDtDao {
	private static final Logger log = LoggerFactory.getLogger(ParaDtDaoImpl.class);
	// property constants
	public static final String CASE_NAME = "caseName";
	public static final String CASE_DESC = "caseDesc";
	public static final String SYS_USER_ID = "sysUserId";
	public static final String STATUS = "status";
	public static final String CASE_CODE = "caseCode";
	public static final String RATIO_NEW = "ratioNew";
	public static final String NUM = "num";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.ParaDtDao#save(com.bean.ParaDt)
	 */
	public void save(ParaDt transientInstance) {
		log.debug("saving ParaDt instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.ParaDtDao#delete(com.bean.ParaDt)
	 */
	public void delete(ParaDt persistentInstance) {
		log.debug("deleting ParaDt instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.ParaDtDao#findById(java.lang.Integer)
	 */
	public ParaDt findById(java.lang.Integer id) {
		log.debug("getting ParaDt instance with id: " + id);
		try {
			ParaDt instance = (ParaDt) getHibernateTemplate().get(
					"com.bean.ParaDt", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.ParaDtDao#findByExample(com.bean.ParaDt)
	 */
	public List findByExample(ParaDt instance) {
		log.debug("finding ParaDt instance by example");
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
	 * @see com.daoimpl.ParaDtDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ParaDt instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ParaDt as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.ParaDtDao#findByCaseName(java.lang.Object)
	 */
	public List findByCaseName(Object caseName) {
		return findByProperty(CASE_NAME, caseName);
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.ParaDtDao#findByCaseDesc(java.lang.Object)
	 */
	public List findByCaseDesc(Object caseDesc) {
		return findByProperty(CASE_DESC, caseDesc);
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.ParaDtDao#findBySysUserId(java.lang.Object)
	 */
	public List findBySysUserId(Object sysUserId) {
		return findByProperty(SYS_USER_ID, sysUserId);
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.ParaDtDao#findByStatus(java.lang.Object)
	 */
	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.ParaDtDao#findByCaseCode(java.lang.Object)
	 */
	public List findByCaseCode(Object caseCode) {
		return findByProperty(CASE_CODE, caseCode);
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.ParaDtDao#findByRatioNew(java.lang.Object)
	 */
	public List findByRatioNew(Object ratioNew) {
		return findByProperty(RATIO_NEW, ratioNew);
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.ParaDtDao#findByNum(java.lang.Object)
	 */
	public List findByNum(Object num) {
		return findByProperty(NUM, num);
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.ParaDtDao#findAll()
	 */
	public List findAll() {
		log.debug("finding all ParaDt instances");
		try {
			String queryString = "from ParaDt where status != 0";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.ParaDtDao#merge(com.bean.ParaDt)
	 */
	public ParaDt merge(ParaDt detachedInstance) {
		log.debug("merging ParaDt instance");
		try {
			ParaDt result = (ParaDt) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.ParaDtDao#attachDirty(com.bean.ParaDt)
	 */
	public void attachDirty(ParaDt instance) {
		log.debug("attaching dirty ParaDt instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoimpl.ParaDtDao#attachClean(com.bean.ParaDt)
	 */
	public void attachClean(ParaDt instance) {
		log.debug("attaching clean ParaDt instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ParaDtDao getFromApplicationContext(ApplicationContext ctx) {
		return (ParaDtDao) ctx.getBean("ParaDtDao");
	}
	
	public RefactorParaDt getRpdByCaseId(Integer caseId){
		Session session = this.getSession();  
		SQLQuery sqlquery ;
		RefactorParaDt rpd = new RefactorParaDt();
		String sql = "select * from para_dt a "
					+ "INNER JOIN para_case_p b on a.case_code=b.case_code "
					+ "INNER JOIN Store c ON b.chal_cd=c.Code "
					+ "where a.case_id = :caseId";
		
		sqlquery=session.createSQLQuery(sql);
		sqlquery.setInteger("caseId", caseId);
		sqlquery.addEntity(ParaDt.class);
		sqlquery.addEntity(ParaCaseP.class);
		sqlquery.addEntity(Store.class);
		
		List<Object[]> resultSet = sqlquery.list();
		
		if(resultSet.size()>0){
			for (Object[] r : resultSet) {

				ParaDt pd = (ParaDt) r[0];
				ParaCaseP pc = (ParaCaseP) r[1];
				Store s = (Store) r[2];
				
				rpd.setCaseId(pd.getCaseId());
				rpd.setCaseEt(pd.getCaseEt());
				rpd.setCaseSt(pd.getCaseSt());
				String desc = pd.getCaseDesc();
				if (desc != null) {
					if (desc.length() > 10) {
						desc = desc.substring(0, 10);
					}
				}
				rpd.setCaseDesc(desc);
				rpd.setNum(pd.getNum());
				rpd.setStatus(pd.getStatus());
				rpd.setSysDt(pd.getSysDt());
				rpd.setSysUserId(pd.getSysUserId());
				rpd.setCaseName(pd.getCaseName());
				rpd.setRatioNew(pd.getRatioNew());

				rpd.setBrde(pc.getBrde());
				rpd.setCaseLevel(pc.getCaseLevel());
				rpd.setPreNum(pc.getPreNum());

				rpd.setName(s.getName());

			}
			return rpd;
		}
		session.flush();    //Çå¿Õ»º´æ			
		return null;
	}
}