package com.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.hql.ast.tree.Statement;

import com.bean.ParaCaseP;
import com.bean.Store;
import com.dao.ParaCasePDao;
import com.service.ParaCasePService;

public class ParaCasePServiceImpl implements ParaCasePService {
	// 封装dao调用里面的方法
	private ParaCasePDao paraCasePDao;
	// 封装集合
	private List<ParaCaseP> allParaCaseP;
	// 封装对象
	private ParaCaseP paraCaseP;
	private SessionFactory sessionFactory;
	private boolean flage;
	// myFile属性用来封装上传的文件
	private File myFile;
	// myFileContentType属性用来封装上传文件的类型
	private String myFileContentType;
	// myFileFileName属性用来封装上传文件的文件名
	private String myFileFileName;

	public ParaCasePDao getParaCasePDao() {
		return paraCasePDao;
	}

	public void setParaCasePDao(ParaCasePDao paraCasePDao) {
		this.paraCasePDao = paraCasePDao;
	}

	public List<ParaCaseP> getAllParaCaseP() {
		return allParaCaseP;
	}

	public void setAllParaCaseP(List<ParaCaseP> allParaCaseP) {
		this.allParaCaseP = allParaCaseP;
	}

	public ParaCaseP getParaCaseP() {
		return paraCaseP;
	}

	public void setParaCaseP(ParaCaseP paraCaseP) {
		this.paraCaseP = paraCaseP;
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

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getMyFileContentType() {
		return myFileContentType;
	}

	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}

	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	/**
	 * 获取所有活动信息
	 */
	@SuppressWarnings("unchecked")
	public List<ParaCaseP> allParaCaseP() {
		allParaCaseP = paraCasePDao.findAll();
		return allParaCaseP;
	}

	/**
	 * 通过code获取活动信息
	 */
	public ParaCaseP findParaCasePById(String caseCode) {
		return paraCasePDao.findById(caseCode);
	}

	/**
	 * 添加活动信息
	 */
	public void saveParaCaseP(ParaCaseP paraCaseP) {
		paraCasePDao.save(paraCaseP);

	}

	/**
	 * 通过id来删除活动
	 */
	public void delParaCasePById(ParaCaseP caseCode) {
		paraCasePDao.delete(caseCode);
	}

	/**
	 * 通过id来修改活动
	 */
	public int updateParaCaseP(ParaCaseP caseCode) {
		return paraCasePDao.merge(caseCode);
	}

	/**
	 * 获取一共有多少条数据
	 * 
	 */
	@SuppressWarnings("deprecation")
	public int getParaCasePTotal() throws Exception {
		return this.sessionFactory.getCurrentSession().find("from ParaCaseP")
				.size();
	}

	/**
	 * 获取para_dt中是否有case_cd
	 */
	public int getParaDtCaseCode(String caseCode) {
		String sql = "select count(case_code) from para_dt where case_code = :case_code";
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(sql);
		query.setString("case_code", caseCode);
		int list = (Integer) query.list().get(0);
		return list;
	}

	/**
	 * 判断活动名称是否已存在
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public boolean getNameBePCPRe(String caseCode, String caseName) {
		List list = null;
		String sql = "select * from para_case_p where case_code = :case_code and case_name =:case_name";
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery(sql);
		query.setString("case_code", caseCode);
		query.setString("case_name", caseName);
		list = query.list();
		if (list.size() > 0) {
			return true;
		} else {
			String sqlName = "select * from para_case_p where case_name =:case_name";
			Query query1 = this.sessionFactory.getCurrentSession()
					.createSQLQuery(sqlName);
			query1.setString("case_name", caseName);
			list = query1.list();
			if (list.size() > 0) {
				return true;
			}
		}
		session.flush(); // 清空缓存
		return false;
	}

	/**
	 * 导入Excel表格
	 */
	public boolean addOneBoat(List<ParaCaseP> list,int batchSize){ 
		//接收集合
		List<ParaCaseP> listExcle=list;
		
		boolean isImpSuccess = true;
		Session session = this.sessionFactory.getCurrentSession(); 
		session.beginTransaction();
		
		try {
			int count=listExcle.size()%batchSize==0?listExcle.size()/batchSize:listExcle.size()/batchSize+1;
			int insertCount=1;
			int maxCount;
			for (int i = 0; i < count; i++) {
				maxCount=insertCount*batchSize;
				StringBuffer sql=new StringBuffer("insert into para_case_p (case_code,case_name,chal_cd,case_level,pre_num,brde,num,c_type,sys_user_id,sys_dt) values ");
				if(maxCount>listExcle.size()){
					maxCount=listExcle.size();
				}
				for (int j = (insertCount-1)*batchSize; j < maxCount; j++) {
					if(insertCount==count){
						if((count-1)*batchSize<=j&&j<listExcle.size()-1){
							sql.append("('"+listExcle.get(j).getCaseCode() +"','"+listExcle.get(j).getCaseName()+"','"+listExcle.get(j).getChalCd().getCode()+"',"
									+ "'"+listExcle.get(j).getCaseLevel()+"',"+listExcle.get(j).getPreNum()+",'"+listExcle.get(j).getBrde()+"',"
											+ ""+listExcle.get(j).getNum()+",'"+listExcle.get(j).getCType()+"','"+listExcle.get(j).getSysUserId()+"','"+listExcle.get(j).getSysDt()+"'),");
						}else{
							sql.append("('"+listExcle.get(j).getCaseCode() +"','"+listExcle.get(j).getCaseName()+"','"+listExcle.get(j).getChalCd().getCode()+"',"
									+ "'"+listExcle.get(j).getCaseLevel()+"',"+listExcle.get(j).getPreNum()+",'"+listExcle.get(j).getBrde()+"',"
											+ ""+listExcle.get(j).getNum()+",'"+listExcle.get(j).getCType()+"','"+listExcle.get(j).getSysUserId()+"','"+listExcle.get(j).getSysDt()+"');");
						}
					}else{
						if((insertCount-1)*batchSize-1<=j&&j<insertCount*batchSize-1){
							sql.append("('"+listExcle.get(j).getCaseCode() +"','"+listExcle.get(j).getCaseName()+"','"+listExcle.get(j).getChalCd().getCode()+"',"
									+ "'"+listExcle.get(j).getCaseLevel()+"',"+listExcle.get(j).getPreNum()+",'"+listExcle.get(j).getBrde()+"',"
											+ ""+listExcle.get(j).getNum()+",'"+listExcle.get(j).getCType()+"','"+listExcle.get(j).getSysUserId()+"','"+listExcle.get(j).getSysDt()+"'),");
						}else{
							sql.append("('"+listExcle.get(j).getCaseCode() +"','"+listExcle.get(j).getCaseName()+"','"+listExcle.get(j).getChalCd().getCode()+"',"
									+ "'"+listExcle.get(j).getCaseLevel()+"',"+listExcle.get(j).getPreNum()+",'"+listExcle.get(j).getBrde()+"',"
											+ ""+listExcle.get(j).getNum()+",'"+listExcle.get(j).getCType()+"','"+listExcle.get(j).getSysUserId()+"','"+listExcle.get(j).getSysDt()+"');");
						}
					}
				}
				
				Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
				query.executeUpdate(); 
				insertCount++;
			}
				//提交事物
				session.getTransaction().commit();
				session.flush(); 
				isImpSuccess = true;
		} catch (Exception e) {
			isImpSuccess = false;
			session.getTransaction().rollback();
		}
		return isImpSuccess;
	}

	/**
	 * 通过名称获取活动信息
	 */
	public ParaCaseP getNameParaCaseP(String caseName) {
		String sql = "select {a.*}, {b.*} from para_case_p a join Store b on a.chal_cd = b.code where a.case_name =:case_name";
		Session session = this.sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity("a", ParaCaseP.class);
		query.addEntity("b", Store.class);
		query.setString("case_name", caseName);
		List pcList = query.list();
		Object[] objs = (Object[])pcList.get(0);
		paraCaseP = (ParaCaseP)objs[0];
		paraCaseP.chalCd = (Store)objs[1];
		return paraCaseP;
	
	}
	
	
}
