package com.serviceimpl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bean.ParaCaseP;
import com.bean.ParaDt;
import com.bean.ParaDtS;
import com.bean.RefactorParaDt;
import com.dao.ParaDtDao;
import com.service.ParaDtService;


public class ParaDtServiceImpl implements ParaDtService {
	// ��װdao��������ķ���
	private ParaDtDao paraDtDao;
	// ��װ����
	private List<ParaDt> allParaDt;
	// ��װ����
	private ParaDt paraDt;
	private SessionFactory sessionFactory;
	private boolean flage;
	private UtilSupport util;// service����

	public ParaDtDao getParaDtDao() {
		return paraDtDao;
	}

	public void setParaDtDao(ParaDtDao paraDtDao) {
		this.paraDtDao = paraDtDao;
	}

	public List<ParaDt> getAllParaDt() {
		return allParaDt;
	}

	public void setAllParaDt(List<ParaDt> allParaDt) {
		this.allParaDt = allParaDt;
	}

	public ParaDt getParaDt() {
		return paraDt;
	}

	public void setParaDt(ParaDt paraDt) {
		this.paraDt = paraDt;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean isFlage() {
		return flage;
	}

	public void setFlage(boolean flage) {
		this.flage = flage;
	}

	public UtilSupport getUtil() {
		return util;
	}

	public void setUtil(UtilSupport util) {
		this.util = util;
	}

	/**
	 * ��ȡ���л��Ϣ
	 */
	@SuppressWarnings("unchecked")
	public List<ParaDt> allParaDt() {
		allParaDt = paraDtDao.findAll();
		return allParaDt;
	}

	/**
	 * ͨ��code��ȡ���Ϣ
	 */
	public ParaDt findParaDtById(Integer id) {
		return paraDtDao.findById(id);
	}

	/**
	 * ��ӻ��Ϣ
	 */
	public void saveParaDt(ParaDt paraDt) {
		paraDtDao.save(paraDt);

	}

	/**
	 * ͨ��id��ɾ���
	 */
	public ParaDt delParaDtById(ParaDt paraDt) {
		return paraDtDao.merge(paraDt);
	}

	/**
	 * ͨ��id���޸Ļ
	 */
	public ParaDt updateParaDtImpl(ParaDt caseCode) {
		return paraDtDao.merge(caseCode);
	}

	/**
	 * ����Excel���
	 */
	public boolean addOneBoat(List<ParaDt> list,int batchSize) {

		//���ռ���
		List<ParaDt> listExcle=list;

		boolean isImpSuccess = true;
		Session session = this.sessionFactory.getCurrentSession(); 
		Transaction trans = session.beginTransaction();
		try {
			int count=listExcle.size()%batchSize==0?listExcle.size()/batchSize:listExcle.size()/batchSize+1;
			int insertCount=1;
			int maxCount;

			for (int i = 0; i < count; i++) {
				maxCount=insertCount*batchSize;
				StringBuffer sql=new StringBuffer("insert into para_dt (case_name,case_desc,case_st,case_et,sys_dt,sys_user_id,status,case_code,ratio_new,num) values ");
				if(maxCount>listExcle.size()){
					maxCount=listExcle.size();
				}
				for (int j = (insertCount-1)*batchSize; j < maxCount; j++) {
					if(insertCount==count){
						if((count-1)*batchSize<=j&&j<listExcle.size()-1){
							sql.append("('"+listExcle.get(j).getCaseName() +"','"+listExcle.get(j).getCaseDesc()+"','"+listExcle.get(j).getCaseSt()+"',"
									+ "'"+listExcle.get(j).getCaseEt()+"','"+listExcle.get(j).getSysDt()+"','"+listExcle.get(j).getSysUserId()+"',"
									+ ""+listExcle.get(j).getStatus()+",'"+listExcle.get(j).getCaseCode()+"',"+listExcle.get(j).getRatioNew()+","+listExcle.get(j).getNum()+"),");
						}else{
							sql.append("('"+listExcle.get(j).getCaseName() +"','"+listExcle.get(j).getCaseDesc()+"','"+listExcle.get(j).getCaseSt()+"',"
									+ "'"+listExcle.get(j).getCaseEt()+"','"+listExcle.get(j).getSysDt()+"','"+listExcle.get(j).getSysUserId()+"',"
									+ ""+listExcle.get(j).getStatus()+",'"+listExcle.get(j).getCaseCode()+"',"+listExcle.get(j).getRatioNew()+","+listExcle.get(j).getNum()+");");
						}
					}else{
						if((insertCount-1)*batchSize-1<=j&&j<insertCount*batchSize-1){
							sql.append("('"+listExcle.get(j).getCaseName() +"','"+listExcle.get(j).getCaseDesc()+"','"+listExcle.get(j).getCaseSt()+"',"
									+ "'"+listExcle.get(j).getCaseEt()+"','"+listExcle.get(j).getSysDt()+"','"+listExcle.get(j).getSysUserId()+"',"
									+ ""+listExcle.get(j).getStatus()+",'"+listExcle.get(j).getCaseCode()+"',"+listExcle.get(j).getRatioNew()+","+listExcle.get(j).getNum()+"),");
						}else{
							sql.append("('"+listExcle.get(j).getCaseName() +"','"+listExcle.get(j).getCaseDesc()+"','"+listExcle.get(j).getCaseSt()+"',"
									+ "'"+listExcle.get(j).getCaseEt()+"','"+listExcle.get(j).getSysDt()+"','"+listExcle.get(j).getSysUserId()+"',"
									+ ""+listExcle.get(j).getStatus()+",'"+listExcle.get(j).getCaseCode()+"',"+listExcle.get(j).getRatioNew()+","+listExcle.get(j).getNum()+");");
						}
					}
				}

				Query query2 = this.sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
				query2.executeUpdate(); 

				insertCount++;
			}
			//�ύ����
			trans.commit();
			session.flush(); 
			isImpSuccess = true;
		} catch (Exception e) {
			isImpSuccess = false;
			trans.rollback();
		}
		return isImpSuccess;
	}	

	
	/*
	 * һ�ܶ���������
	 */
	public int getTotalCount( ) throws Exception {  
		Session ssn = this.sessionFactory.getCurrentSession();
	 	Query qry = ssn.createQuery("from ParaDt");
	 	int cnt = qry.list().size();
		return cnt;   
	}  
	
	/**
	 * ͨ������ͻ�ȡʱ����бȽ�
	 */
	public int getCaseNameTime(String caseName,Timestamp caseSt,Timestamp caseEt){
		String sql="select * from para_dt "
					+ "where case_name=:caseName "
					+ "and status!=0 "
					+ "and ("
						+ "(case_st < :caseEt and case_st > :caseSt ) "
						+ "or (case_et > :caseSt  and case_et < :caseEt  ) "
						+ "or (case_st < :caseSt and case_et > :caseEt )"
					+ ")";
		
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setString("caseName", caseName);
		query.setTimestamp("caseSt", caseSt);
		query.setTimestamp("caseEt", caseEt);
		return query.list().size();
	}

	public RefactorParaDt getRpdByCaseId(Integer caseId) {
		return paraDtDao.getRpdByCaseId(caseId);
	}

	
}
