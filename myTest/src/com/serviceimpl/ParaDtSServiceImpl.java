package com.serviceimpl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bean.BProductP;
import com.bean.ParaDt;
import com.bean.ParaDtS;
import com.dao.ParaDtDao;
import com.dao.ParaDtSDao;
import com.dao.ParaDtSSkuDao;
import com.service.ParaDtSService;

public class ParaDtSServiceImpl implements ParaDtSService {

	private ParaDtSDao paraDtSDao;
	private ParaDtDao paraDtDao;
	
	private ParaDtSSkuDao paraDtSSkuDao;
	private SessionFactory sessionFactory;

	public ParaDtSDao getParaDtSDao() {
		return paraDtSDao;
	}

	public void setParaDtSDao(ParaDtSDao paraDtSDao) {
		this.paraDtSDao = paraDtSDao;
	}
	
	public ParaDtDao getParaDtDao() {
		return paraDtDao;
	}

	public void setParaDtDao(ParaDtDao paraDtDao) {
		this.paraDtDao = paraDtDao;
	}

	public List getAllParaDtList(){
		return paraDtDao.findAll();
	}
	
	
	public ParaDtSSkuDao getParaDtSSkuDao() {
		return paraDtSSkuDao;
	}

	public void setParaDtSSkuDao(ParaDtSSkuDao paraDtSSkuDao) {
		this.paraDtSSkuDao = paraDtSSkuDao;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 导入数据
	 */
	public void saveOneBoat(List<ParaDtS> paraDtSList,int batchSize){
		Session session = this.sessionFactory.getCurrentSession(); 
		int count=paraDtSList.size()%batchSize==0?paraDtSList.size()/batchSize:paraDtSList.size()/batchSize+1;
		int insertCount=1;
		int maxCount;
		for (int i = 0; i < count; i++) {
			maxCount=insertCount*batchSize;
			StringBuffer sql=new StringBuffer("insert into imp_para_dt_s_sku (case_id,sku_code) values ");
			if(maxCount>paraDtSList.size()){
				maxCount=paraDtSList.size();
			}
			for (int j = (insertCount-1)*batchSize; j < maxCount; j++) {
				if(insertCount==count){
					if((count-1)*batchSize<=j&&j<paraDtSList.size()-1){
						sql.append("("+paraDtSList.get(j).getCaseId() +",'"+paraDtSList.get(j).getProductCd().getProductCode()+"'),");
					}else{
						sql.append("("+paraDtSList.get(j).getCaseId() +",'"+paraDtSList.get(j).getProductCd().getProductCode()+"');");
					}
				}else{
					if((insertCount-1)*batchSize-1<=j&&j<insertCount*batchSize-1){
						sql.append("("+paraDtSList.get(j).getCaseId() +",'"+paraDtSList.get(j).getProductCd().getProductCode()+"'),");
					}else{
						sql.append("("+paraDtSList.get(j).getCaseId() +",'"+paraDtSList.get(j).getProductCd().getProductCode()+"');");
					}
				}
			}
			
			Query query2 = this.sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
			query2.executeUpdate(); 
			insertCount++;
		}
		
		session.flush(); 
	}
	
	/**
	 * 调用“p_imp_case”存储过程
	 */
	public void setImpParaDtSSku(int imp_flag,String name){
		//接受存储函数
//		String procdure = "{Call p_imp_case(?,?)}"; 
//		
//		CallableStatement cs;
//		try {
//			Session session =this.sessionFactory.getCurrentSession();
//			
//			cs = session.connection().prepareCall(procdure);
//			cs.setInt(1, imp_flag);
//			cs.setString(2, name);
//			
//			cs.execute();
//			session.connection().close();
//			session.flush();
//			session.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
		 
//		SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery("{Call p_imp_case(?,?)}");
//		query.setInteger(0, imp_flag);
//		query.setString(1, name);
//		query.executeUpdate();
		paraDtSDao.setImpParaDtSSku(imp_flag, name);
	}
	
	/**
	 * 获取导出数据
	 */
	public List getCaseIdParaDtS(int caseId) {
		String sql="select * from para_dt_s s inner join b_product_p p on s.product_cd = p.product_code" +
				" where 0=0 and s.case_id = :case_id and ISNULL(s.status,2)!=0 ";
		SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setInteger("case_id", caseId);
		query.addEntity(ParaDtS.class);
		query.addEntity(BProductP.class);
		List list=query.list();
		return list;
	}

	/**
	 * 根据活动ID查找活动信息
	 */
	public ParaDtS findParaDtSByCaseId(Integer caseId) {
		List<ParaDtS> list=paraDtSDao.findByCaseId(caseId);
		ParaDtS pds=new ParaDtS();
		if(list.size()>0){
			pds=list.get(0);
		}
		return pds;
	}

	/**
	 * 添加活动选款产品
	 * @param pds
	 */
	public void saveParaDtS(ParaDtS pds) {
		paraDtSDao.save(pds);
	}

	public void deleteParaDtS(int id) {
		ParaDtS pds=paraDtSDao.findById(id);
		pds.setStatus(0);
		paraDtSDao.merge(pds);
	}

	public Map<String, Integer> getCasePrdtSummary(int case_id, int top,
			int del_status) {
		return paraDtSDao.getCasePrdtSummary(case_id,top,del_status);
	}
}
