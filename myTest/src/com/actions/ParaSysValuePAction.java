package com.actions;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bean.BProductP;
import com.bean.PUser;
import com.bean.ParaCaseP;
import com.bean.ParaDt;
import com.bean.ParaSordata;
import com.bean.ParaSysValueP;
import com.bean.ParaValueType;
import com.bean.Para_Type;
import com.bean.Store;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BProductPService;
import com.service.ParaSysValuePService;
import com.serviceimpl.UtilSupport;

@SuppressWarnings("serial")
public class ParaSysValuePAction extends ActionSupport {

	private ParaSysValuePService paraSysValuePService;// service����
	private List<ParaSysValueP> paraSysValuePList;// ParaSysValueP����
	private ParaSysValueP paraSysValueP;// ����
	private BProductPService bProductPService;
	private List<BProductP> allBProductPList;
	private BProductP bProductP;
	private List tynalist;
	private UtilSupport util;
	private int rows;// �ܵ�����
	private int page;// ҳ��
	private int pageSize = 10;// ÿҳ��ʾ������
	private int offset;// ����jspҳ�洫����ҳ����
	private boolean flag;
	private String msg;
	private String tyna;// ��Ʒ��Ŀ
	
	private String refreshList;
	private String titleName;

	public ParaSysValuePAction() {
		paraSysValuePList = new ArrayList<ParaSysValueP>();
	}

	public ParaSysValuePService getParaSysValuePService() {
		return paraSysValuePService;
	}

	public void setParaSysValuePService(
			ParaSysValuePService paraSysValuePService) {
		this.paraSysValuePService = paraSysValuePService;
	}

	public List<ParaSysValueP> getParaSysValuePList() {
		return paraSysValuePList;
	}

	public void setParaSysValuePList(List<ParaSysValueP> paraSysValuePList) {
		this.paraSysValuePList = paraSysValuePList;
	}

	public ParaSysValueP getParaSysValueP() {
		return paraSysValueP;
	}

	public void setParaSysValueP(ParaSysValueP paraSysValueP) {
		this.paraSysValueP = paraSysValueP;
	}

	public BProductPService getbProductPService() {
		return bProductPService;
	}

	public void setbProductPService(BProductPService bProductPService) {
		this.bProductPService = bProductPService;
	}

	public List<BProductP> getAllBProductPList() {
		return allBProductPList;
	}

	public void setAllBProductPList(List<BProductP> allBProductPList) {
		this.allBProductPList = allBProductPList;
	}

	public BProductP getbProductP() {
		return bProductP;
	}

	public void setbProductP(BProductP bProductP) {
		this.bProductP = bProductP;
	}

	public List getTynalist() {
		return tynalist;
	}

	public void setTynalist(List tynalist) {
		this.tynalist = tynalist;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTyna() {
		return tyna;
	}

	public void setTyna(String tyna) {
		this.tyna = tyna;
	}

	public UtilSupport getUtil() {
		return util;
	}

	public void setUtil(UtilSupport util) {
		this.util = util;
	}

	public String getRefreshList() {
		return refreshList;
	}

	public void setRefreshList(String refreshList) {
		this.refreshList = refreshList;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	/**
	 * ��ȡ���м��������Ϣ
	 * 
	 */
	public String getAll() {
		paraSysValuePList = paraSysValuePService.allParaSysValueP();
		return "allParaSysValueP";
	}

	/**
	 * ����û�ID��ȡ������Ϣ
	 * 
	 */
	public String getParaSysValuePId() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			tyna = new String(tyna.getBytes("iso-8859-1"), "utf-8");
			System.out.println(tyna);
			paraSysValueP = paraSysValuePService.findParaSysValuePById(tyna);
			request.setAttribute("paraSysValueP", paraSysValueP);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "detail";
	}

	/**
	 * ͨ��id��ɾ���Ʒ��Ϣ
	 */
	public String delParaSysValueP() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");

			// ��ȡɾ���id
			String tyn = request.getParameter("tyn");
			String ty = new String(tyn.getBytes("iso-8859-1"), "utf-8");

			// ����ɾ��
			paraSysValueP = paraSysValuePService.findParaSysValuePById(ty);
			paraSysValuePService.delParaSysValuePById(paraSysValueP);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "delParaSysValueP";
	}

	/**
	 * ͨ��id���޸Ĳ�Ʒ��Ϣ
	 */
	public String updateParaSysValueP() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Date date = new Date();// ����һ��ʱ����󣬻�ȡ����ǰ��ʱ��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ����ʱ����ʾ��ʽ
		String str = sdf.format(date);// ����ǰʱ���ʽ��Ϊ��Ҫ������
		paraSysValueP.setSysDt(Timestamp.valueOf(str));
		paraSysValueP.setSysUserId(ParaSysValuePAction.getCurrentUserName());
		paraSysValuePService.updateParaSysValueP(paraSysValueP);
		return "updateParaSysValueP";
	}

	/**
	 * ��Ӳ�Ʒ��Ϣ
	 */
	public String savePSVP() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Date date = new Date();// ����һ��ʱ����󣬻�ȡ����ǰ��ʱ��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ����ʱ����ʾ��ʽ
		String str = sdf.format(date);// ����ǰʱ���ʽ��Ϊ��Ҫ������
		paraSysValueP.setSysDt(Timestamp.valueOf(str));
		paraSysValueP.setSysUserId(ParaSysValuePAction.getCurrentUserName());
		paraSysValuePService.saveParaSordataP(paraSysValueP);
		HttpSession session = request.getSession(false);
		String tyna = paraSysValueP.getTyna();
		
		refreshList = "paraSysValuePqueryParaSysValueP";
		titleName = "查看计算参数信息";
		msg = " [" + tyna + "] ";
		session.setAttribute("msg", msg);
		return "savePSVP";

	}

	/**
	 * �ж�ID�Ƿ��Ѵ���
	 * 
	 */

	public String getPId() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			tyna = request.getParameter("tyna");
			paraSysValueP = paraSysValuePService.findParaSysValuePById(tyna);
			if (null != paraSysValueP) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "getPId";
	}

	public String loadSysValCombox() {
		tynalist = bProductPService.alltyna();
		return "getLoad";
	}

	/**
	 * ��ȡ��ǰ�û���
	 */
	public static String getCurrentUserName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		PUser loginuser = (PUser) session.getAttribute("pu");
		String name = loginuser.getUserName();
		return name;
	}

	/**
	 * ��ѯ
	 */
	public String queryParaSysValueP() throws Exception {
		tynalist = bProductPService.alltyna();
		HttpServletRequest request = ServletActionContext.getRequest();
		StringBuffer sql = new StringBuffer(
				"select * from para_sys_value_p where 0=0");

		this.tyna = request.getParameter("tyna");
		if (tyna != null && !tyna.isEmpty()) {
			tyna = new String(tyna.trim().getBytes("ISO-8859-1"), "UTF-8");
			sql.append(" and tyna like '%" + tyna + "%'");
		}
		sql.append(" order by sys_dt desc");

		rows = util.getTotalCount(sql.toString());

		page = rows % pageSize == 0 ? rows / pageSize : rows / pageSize + 1;

		offset = getPageOffset();

		paraSysValuePList = util.getPageListBySql(sql.toString(),
				String.valueOf(offset), String.valueOf(pageSize),
				new Class[] { ParaSysValueP.class });

		request.setAttribute("tynalist", tynalist);
		return "queryParaSysValueP";
	}

	// Added by JSL : ��ȡ��ҳƫ����(ʵ�����ǽ�Ҫ������ҳ���ҳ����ҳ����� 0 ��ʼ)
	private int getPageOffset() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ofst = request.getParameter("offset");
		int idx = 0;
		if (ofst != null) {
			idx = Integer.valueOf(ofst);
			idx = idx < 0 ? 0 : idx; // �����һҳʱ�����ٷ�ҳ
			idx = idx >= page ? (page - 1) : idx; // �������һҳʱ�����ٷ�ҳ
		}
		return idx;
	}

}
