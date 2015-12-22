package com.serviceimpl;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;

import com.bean.PGroup;
import com.bean.PMenu;
import com.bean.PUser;
import com.bean.ParaDtS;

@SuppressWarnings("rawtypes")
public class UtilSupport {
	private SessionFactory sessionFactory;
	private List list;
	private Query query;

	public SessionFactory getSessionFactory() {

		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// String hql �� ����ѯ����
	// String page �� ����Ӧ��ҳ����
	// String rows �� ���ҳ����ݼ�¼����
	// 2015-11-9 Revised by JSL:
	// ���㷵�ؽ��ʱ,���㹫ʽ�� (currentpage-1) * pagesize ��Ϊ (currentpage) * pagesize
	public List getPageList(String hql, String page, String rows) {
		// ��Ϊȱʡֵ��ʱ����и�ֵ
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);// �ڼ�ҳ
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);// ÿҳ������
		List list = this.sessionFactory.getCurrentSession().createQuery(hql)
				.setFirstResult((currentpage) * pagesize)
				.setMaxResults(pagesize).list();
		return list;
	}

	public class PageInfo {
		public List pageRows;
		public int pageIndex;
		public int pageSize;
		public int totalRows;
		public int totalPages;
	}

	// String hql �� ����ѯ����
	// String pageIndex �� ����Ӧ��ҳ����
	// String pageSize �� ���ҳ����ݼ�¼����
	// 2015-11-9 Revised by JSL:
	//
	public PageInfo getPageListEx(String hql, String pageIndex, String pageSize) {

		PageInfo pi = new PageInfo();
		pi.pageSize = (pageSize == null) ? 10 : Integer.parseInt(pageSize);
		pi.pageIndex = Math.abs((pageIndex == null) ? 0 : Integer
				.parseInt(pageIndex));

		Query qry = this.sessionFactory.getCurrentSession().createQuery(hql);
		List tmpList = qry.list();

		pi.totalRows = tmpList.size();
		pi.totalPages = (int) Math.ceil((float) pi.totalRows / pi.pageSize);
		pi.pageIndex = Math.min(pi.totalPages - 1, pi.pageIndex); // ����ҳ����ֵ

		int fromIndex = pi.pageIndex * pi.pageSize;
		int toIndex = Math.min(fromIndex + pi.pageSize, pi.totalRows);

		pi.pageRows = tmpList.subList(fromIndex, toIndex);

		return pi;
	}

	// String sql �� ��ݿ��ѯ��䣬����ʹ�ô� SQL ��ѯ��ݼ�
	// String nPage ��ָ���������ݼ��ĵ� n ҳ���
	// String rows ��ָ����ÿ���ҳ�ļ�¼��
	// 2015-11-9 Revised by JSL:
	//
	public List getPageListBySql(String sql, String pageIndex, String pageSize,
			Class[] resultSetTypes) {
		// ��Ϊȱʡֵ��ʱ����и�ֵ

		int psz = (pageSize == null) ? 10 : Integer.parseInt(pageSize);
		int idx = (pageIndex == null) ? 0 : Integer.parseInt(pageIndex);

		SQLQuery query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql);
		for (int i = 0; i < resultSetTypes.length; i++) {
			query.addEntity(resultSetTypes[i]);
		}

		List tmpList = query.list();

		int totalRows = tmpList.size();
		int totalPages = (int) Math.ceil((float) totalRows / psz);
		idx = Math.min(totalPages - 1, idx);

		int fromIndex = idx * psz;
		int toIndex = Math.min(fromIndex + psz, totalRows);

		if (tmpList.size() > 0) {
			list = tmpList.subList(fromIndex, toIndex);
		} else {
			list = new ArrayList();
		}
		return list;
	}

	// ͳ��һ���ж������
	public int getTotalCount(String sql) throws Exception {
		int count = this.sessionFactory.getCurrentSession().createSQLQuery(sql)
				.list().size();
		return count;
	}

	/**
	 * ���������ѯ
	 * 
	 * @param className
	 * @param parms
	 * @return
	 */
	public List getLisByOptions(String className, String page, String rows,
			Map<String, String> parms, String order) throws Exception {
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);// �ڼ�ҳ
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);// ÿҳ������
		StringBuffer hql = new StringBuffer("from " + className
				+ " where 0 = 0 ");
		Iterator it = parms.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String key = entry.getKey().toString();
			hql.append(" and " + key + " like ?");
		}
		query = this.sessionFactory.getCurrentSession().createQuery(
				hql.toString());
		if (order != null || order != "") {
			hql.append(order);
		}
		it = parms.entrySet().iterator();
		int i = 0;
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String val = entry.getValue().toString();
			query.setParameter(i, "%" + val + "%", Hibernate.STRING);
			i = i + 1;
		}
		list = query.setFirstResult((currentpage - 1) * pagesize)
				.setMaxResults(pagesize).list();
		return list;
	}

	/**
	 * ����û�ID��ȡ�˵�ѡ��
	 * 
	 * @param userId
	 * @return
	 */
	public List getNodesByUserId(String userId) {
		List<PMenu> menuLis = new ArrayList<PMenu>();
		String sql = "select a.pmid,a.mid,a.mName,a.mUrl,a.sys_dt,a.sys_user_id,b.group_id, b.user_id "
				+ "from p_menu a"
				+ " left join "
				+ "(select m.pmid, m.mid, m.mName, m.mUrl, gu.group_id, gu.user_id "
				+ "from (p_menu m inner join p_group_menu gm on m.mid=gm.mid)"
				+ "inner join p_group_user gu on gu.group_id=gm.group_id where gu.user_id=:gu.user_id)"
				+ "b on a.mid = b.mid ";
		SQLQuery query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql);
		query.setString("gu.user_id", userId);
		query.addScalar("pmid");
		query.addScalar("mid");
		query.addScalar("mName");
		query.addScalar("mUrl");
		query.addScalar("sys_dt");
		query.addScalar("sys_user_id");
		query.addScalar("group_id");
		query.addScalar("user_id");
		List<Object[]> resultSet = query.list();
		for (Object[] r : resultSet) {
			PMenu menu = new PMenu();
			menu.setPmid((String) r[0]);
			menu.setMid((String) r[1]);
			menu.setMname((String) r[2]);
			menu.setMurl((String) r[3]);
			menu.setSysDt((Timestamp) r[4]);
			menu.setSysUserId((String) r[5]);
			PGroup group = new PGroup();
			group.setGroupId((Integer) r[6]);
			menu.setGroupId(group);
			PUser user = new PUser();
			user.setUserId((String) r[7]);
			menu.setUserId(user);
			menuLis.add(menu);
		}
		return menuLis;
	}

	/**
	 * ����û�ID��ȡaction�ֶν��й��˴���
	 * 
	 * @param userId
	 * @return
	 */
	public List<String> getUrlsByUserId(String userId) {
		String sql = "select distinct action " + "from p_menu where mid in ( "
				+ "select m.mid from p_menu m  "
				+ "inner join p_group_menu gm on m.mid=gm.mid  "
				+ "inner join p_group_user gu on gu.group_id=gm.group_id "
				+ "where gu.user_id=:gu.user_id)";
		SQLQuery query = this.sessionFactory.getCurrentSession()
				.createSQLQuery(sql);
		query.setString("gu.user_id", userId);
		List<String> urlsList = query.list();
		return urlsList;
	}

	/**
	 * ���á�p_rt_case���洢���
	 */
	public void callPRtCase(String caseCode, int caseId) {
		String procdure = "{Call p_rt_case(?,?)}";
		CallableStatement cs;

		try {
			cs = this.sessionFactory.getCurrentSession().connection()
					.prepareCall(procdure);
			cs.setString(1, caseCode);
			cs.setInt(2, caseId);
			ResultSet rs = cs.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���á�sp_set_case_prdt_status���洢���
	 */
	public void setPrdtStatus(int caseId, int old_status, int new_status) {
		// ���ܴ洢����
		String procdure = "{Call sp_set_case_prdt_status(?,?,?)}";
		CallableStatement cs;

		try {
			cs = this.sessionFactory.getCurrentSession().connection()
					.prepareCall(procdure);
			cs.setInt(1, caseId);
			cs.setInt(2, 5);
			cs.setInt(3, new_status);
			ResultSet rs = cs.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����ģ��
	 */
	public String getTemplate(String[] Header, String name) throws Exception {
		/*
		 * ���ñ�ͷ����Excelÿ��ȡ��(��������ȡ����ݱ�д)
		 */
		String[] tableHeader = Header;

		/**
		 * ���ñ�ͷ�Ŀ��
		 */
		int[] headerWidths = new int[tableHeader.length];
		for (int i = 0; i < tableHeader.length; i++) {
			headerWidths[i] = tableHeader[i].length() * 2;
		}

		short cellNumber = (short) tableHeader.length;// �������
		XSSFWorkbook workbook = new XSSFWorkbook(); // ����һ��excel
		XSSFCell cell = null; // Excel����
		XSSFRow row = null; // Excel����
		XSSFCellStyle style = workbook.createCellStyle(); // ���ñ�ͷ������
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		XSSFCellStyle style1 = workbook.createCellStyle(); // �����������
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		XSSFFont font = workbook.createFont(); // ��������
		XSSFSheet sheet = workbook.createSheet("sheet1"); // ����һ��sheet
		Header header = sheet.getHeader();// ����sheet��ͷ

		/**
		 * ���ñ�����ʽ
		 */

		// ��������
		XSSFFont headfont = workbook.createFont();
		headfont.setFontName("����");
		headfont.setFontHeightInPoints((short) 22);// �����С
		headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// �Ӵ�

		// ������
		XSSFCellStyle headstyle = workbook.createCellStyle();
		headstyle.setFont(headfont);
		headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// ���Ҿ���
		headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// ���¾���
		headstyle.setLocked(true);
		headstyle.setWrapText(true);// �Զ�����

		// ������һ��
		XSSFRow row0 = sheet.createRow(0);
		// �����и�
		row0.setHeight((short) 900);
		// ������һ��
		XSSFCell cell0 = row0.createCell(0);
		cell0.setCellValue(name + "�����");
		cell0.setCellStyle(headstyle);

		// �ϲ���Ԫ��
		CellRangeAddress range = new CellRangeAddress(0, 0, 0, cellNumber);
		sheet.addMergedRegion(range);

		/**
		 * ����Ƿ�ȡ����ݣ�����header��Ϣ
		 * 
		 */
		row = sheet.createRow(1);
		row.setHeight((short) 400);
		for (int k = 0; k < cellNumber; k++) {
			cell = row.createCell(k);// ������0�е�k��
			cell.setCellValue(tableHeader[k]);// ���õ�0�е�k�е�ֵ
			sheet.setColumnWidth(k, headerWidths[k] * 256);// �����еĿ��
			font.setColor(HSSFFont.COLOR_NORMAL); // ���õ�Ԫ���������ɫ.
			font.setFontHeightInPoints((short) 11);// ���������С
			style1.setFont(font);// ����������
			style.setFont(font);// ����������
			cell.setCellStyle(style1);
		}

		/*
		 * ����Ŀ��Բ��ñ�д��ֱ�ӿ���
		 */
		HttpServletResponse response = null;// ����һ��HttpServletResponse����
		OutputStream out = null;// ����һ�����������
		try {
			response = ServletActionContext.getResponse();// ��ʼ��HttpServletResponse����
			out = response.getOutputStream();//
			response.setHeader("Content-disposition", "attachment; filename="
					+ "template.xlsx");// filename�����ص�xls�����������Ӣ��
			response.setContentType("application/msexcel;charset=UTF-8");// ��������
			response.setHeader("Pragma", "No-cache");// ����ͷ
			response.setHeader("Cache-Control", "no-cache");// ����ͷ
			response.setDateHeader("Expires", 0);// ��������ͷ
			workbook.write(out);
			out.flush();
			workbook.write(out);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {

				if (out != null) {
					out.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return null;
	}

	/**
	 * �ж��Ƿ�����������
	 */

	private boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	public boolean isMessyCode(String strName) {
		Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
		Matcher m = p.matcher(strName);
		String after = m.replaceAll("");
		String temp = after.replaceAll("\\p{P}", "");
		char[] ch = temp.trim().toCharArray();
		float chLength = 0;
		float count = 0;
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (!Character.isLetterOrDigit(c)) {
				if (!isChinese(c)) {
					count = count + 1;
				}
				chLength++;
			}
		}
		float result = count / chLength;
		if (result > 0.4) {
			return true;
		} else {
			return false;
		}
	}
}