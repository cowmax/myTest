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
	// ��װdao��������ķ���
	private ParaCasePDao paraCasePDao;
	// ��װ����
	private List<ParaCaseP> allParaCaseP;
	// ��װ����
	private ParaCaseP paraCaseP;
	private SessionFactory sessionFactory;
	private boolean flage;
	// myFile����������װ�ϴ����ļ�
	private File myFile;
	// myFileContentType����������װ�ϴ��ļ�������
	private String myFileContentType;
	// myFileFileName����������װ�ϴ��ļ����ļ���
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
	 * ��ȡ���л��Ϣ
	 */
	@SuppressWarnings("unchecked")
	public List<ParaCaseP> allParaCaseP() {
		allParaCaseP = paraCasePDao.findAll();
		return allParaCaseP;
	}

	/**
	 * ͨ��code��ȡ���Ϣ
	 */
	public ParaCaseP findParaCasePById(String caseCode) {
		return paraCasePDao.findById(caseCode);
	}

	/**
	 * ��ӻ��Ϣ
	 */
	public void saveParaCaseP(ParaCaseP paraCaseP) {
		paraCasePDao.save(paraCaseP);

	}

	/**
	 * ͨ��id��ɾ��
	 */
	public void delParaCasePById(ParaCaseP caseCode) {
		paraCasePDao.delete(caseCode);
	}

	/**
	 * ͨ��id���޸Ļ
	 */
	public int updateParaCaseP(ParaCaseP caseCode) {
		return paraCasePDao.merge(caseCode);
	}

	/**
	 * ��ȡһ���ж��������
	 * 
	 */
	@SuppressWarnings("deprecation")
	public int getParaCasePTotal() throws Exception {
		return this.sessionFactory.getCurrentSession().find("from ParaCaseP")
				.size();
	}

	/**
	 * ��ȡpara_dt���Ƿ���case_cd
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
	 * �жϻ����Ƿ��Ѵ���
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
		session.flush(); // ��ջ���
		return false;
	}

	/**
	 * ����Excel���
	 */
	public void addOneBoat(ParaCaseP paraCaseP) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		String sql = "insert into para_case_p (case_code,case_name,chal_cd,case_level,pre_num,brde,num,c_type,sys_user_id,sys_dt) "
				+ "values ('"
				+ paraCaseP.getCaseCode()
				+ "','"
				+ paraCaseP.getCaseName()
				+ "',"
				+ "'"
				+ paraCaseP.getChalCd().getCode()
				+ "',"
				+ "'"
				+ paraCaseP.getCaseLevel()
				+ "',"
				+ ""
				+ paraCaseP.getPreNum()
				+ ","
				+ "'"
				+ paraCaseP.getBrde()
				+ "',"
				+ ""
				+ paraCaseP.getNum()
				+ ","
				+ "'"
				+ paraCaseP.getCType()
				+ "','"
				+ paraCaseP.getSysUserId() + "',GETDATE())";
		Query query2 = this.sessionFactory.getCurrentSession().createSQLQuery(
				sql);
		try {
			query2.executeUpdate();
			session.getTransaction().commit();
			session.flush();
		} catch (Exception e) {
			session.getTransaction().rollback();
		}
	}

	/**
	 * ͨ����ƻ�ȡ���Ϣ
	 */
	public ParaCaseP getNameParaCaseP(String caseName) {
		String sql = "select {a.*}, {b.*} from para_case_p a join Store b on a.chal_cd = b.code where a.case_name =:case_name";
		Session session = this.sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity("a", ParaCaseP.class);
		query.addEntity("b", Store.class);
		query.setString("case_name", caseName);
		List pcList = query.list();
		Object[] objs = (Object[]) pcList.get(0);
		paraCaseP = (ParaCaseP) objs[0];
		paraCaseP.chalCd = (Store) objs[1];
		return paraCaseP;

	}

}
