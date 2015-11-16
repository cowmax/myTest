package com.actions;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bean.BProductP;
import com.bean.ParaSysValueP;
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
	private int rows;//�ܵ�����
	private int page;//ҳ��
	private int pageSize=10;//ÿҳ��ʾ������
	private int offset;//����jspҳ�洫����ҳ����
	private boolean flag;
	private String msg;
	private String tyna;//��Ʒ��Ŀ


	public ParaSysValuePService getParaSysValuePService() {
		return paraSysValuePService;
	}

	public void setParaSysValuePService(ParaSysValuePService paraSysValuePService) {
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

	/**
	 * ��ȡ���м��������Ϣ
	 * 
	 */
	public String getAll(){
		paraSysValuePList=paraSysValuePService.allParaSysValueP();
		return "allParaSysValueP";
	}
	
	/**
	 * �����û�ID��ȡ������Ϣ
	 * 
	 */
	public String getParaSysValuePId(){
		try {		
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpServletResponse response=ServletActionContext.getResponse();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			tyna=new String(tyna.getBytes("iso-8859-1"), "utf-8");
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
	 * ͨ��id��ɾ����Ʒ��Ϣ
	 */
	public String delParaSysValueP(){
		try {
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpServletResponse response=ServletActionContext.getResponse();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			tyna=new String(tyna.getBytes("iso-8859-1"), "utf-8");
			paraSysValueP=paraSysValuePService.findParaSysValuePById(tyna);
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
	public String updateParaSysValueP(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Date date= new Date();//����һ��ʱ����󣬻�ȡ����ǰ��ʱ��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//����ʱ����ʾ��ʽ
		String str = sdf.format(date);//����ǰʱ���ʽ��Ϊ��Ҫ������
		paraSysValueP.setSysDt(Timestamp.valueOf(str));
		paraSysValueP.setSysUserId(ParaSysValuePAction.getCurrentUserName());
		paraSysValuePService.updateParaSysValueP(paraSysValueP);
		return "updateParaSysValueP";
	}
	/**
	 * ��Ӳ�Ʒ��Ϣ
	 */
	public String savePSVP(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Date date= new Date();//����һ��ʱ����󣬻�ȡ����ǰ��ʱ��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//����ʱ����ʾ��ʽ
		String str = sdf.format(date);//����ǰʱ���ʽ��Ϊ��Ҫ������
		paraSysValueP.setSysDt(Timestamp.valueOf(str));
		paraSysValueP.setSysUserId(ParaSysValuePAction.getCurrentUserName());
		paraSysValuePService.saveParaSordataP(paraSysValueP);
		HttpSession session = request.getSession(false);
		String tyna=paraSysValueP.getTyna();
		msg=" ["+tyna+"] ";
		session.setAttribute("msg", msg);
		return "savePSVP";
	
	}
	
	/**
	 * ��ҳ��ѯ��
	 */
	public String getParaSysValuePAll(){
		try {
			tynalist=bProductPService.alltyna();
			HttpServletRequest request=ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			String ofst = request.getParameter("offset");
			if(ofst!=null){
				offset=Integer.valueOf(ofst);
			}else{
				offset=1;
			}
			rows = paraSysValuePService.getParaSysValuePTotal();
			page = rows % pageSize == 0 ? rows / pageSize
					: rows / pageSize + 1;
			if (offset < 1) {
				offset = 1;
			} else if (offset > page) {
				offset = page;
			}
			paraSysValuePList=paraSysValuePService.getParaSysValuePPage(String.valueOf(offset), String.valueOf(pageSize));
			request.setAttribute("paraSysValuePList", paraSysValuePList);
			request.setAttribute("tynalist", tynalist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * �ж�ID�Ƿ��Ѵ���
	 * 
	 */
	
	public String getPId(){
		try {
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpServletResponse response=ServletActionContext.getResponse();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			tyna=request.getParameter("tyna");
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
	
	public String loadSysValCombox(){
		tynalist=bProductPService.alltyna();
		return "getLoad";
	}
	
	/**
	 * ��ȡ��ǰ�û���
	 */
	public static String getCurrentUserName(){
		String a="������";	
		return a;	
	}
	/**
	 * ��ѯ
	 */
	public String queryParaSysValueP() throws Exception{
		Map<String, String>parms=new HashMap<String, String>();
		HttpServletRequest request=ServletActionContext.getRequest();
		String ofst = request.getParameter("offset");
		if(ofst!=null){
			offset=Integer.valueOf(ofst);
		}else{
			offset=1;
		}
		String tyna = new String(request.getParameter("tyna").getBytes("iso-8859-1"), "utf-8");
		if(tyna!=null){
			if(!tyna.equals("")){
				parms.put("id.tyna", tyna);
			}
		}
		if (offset < 1) {
			offset = 1;
		} else if (offset > page) {
			offset = page;
		}
		paraSysValuePList=util.getLisByOptions("ParaSysValueP",String.valueOf(offset), String.valueOf(pageSize), parms," order by sysDt desc");
		return "queryParaSysValueP";
	}
}
