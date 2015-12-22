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
	
	private ParaSysValuePService paraSysValuePService;// service对象
	private List<ParaSysValueP> paraSysValuePList;// ParaSysValueP集合
	private ParaSysValueP paraSysValueP;// 对象
	private BProductPService bProductPService;
	private List<BProductP> allBProductPList;
	private BProductP bProductP;
	private List tynalist;
	private UtilSupport util;
	private int rows;//总的条数
	private int page;//页数
	private int pageSize=10;//每页显示的条数
	private int offset;//接受jsp页面传来的页面数
	private boolean flag;
	private String msg;
	private String tyna;//产品类目

	private String refreshList;
	private String titleName;

	public ParaSysValuePAction() {
		paraSysValuePList=new ArrayList<ParaSysValueP>();
	}

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
	 * 获取所有计算参数信息
	 * 
	 */
	public String getAll(){
		paraSysValuePList=paraSysValuePService.allParaSysValueP();
		return "allParaSysValueP";
	}
	
	/**
	 * 根据用户ID获取参数信息
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
	 * 通过id来删除产品信息
	 */
	public String delParaSysValueP(){
		try {
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpServletResponse response=ServletActionContext.getResponse();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			//获取删除的id
			String tyn=request.getParameter("tyn");
			String ty=new String(tyn.getBytes("iso-8859-1"), "utf-8");
			
			//调用删除方法
			paraSysValueP=paraSysValuePService.findParaSysValuePById(ty);
			paraSysValuePService.delParaSysValuePById(paraSysValueP);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "delParaSysValueP";
	}
	
	/**
	 * 通过id来修改产品信息
	 */
	public String updateParaSysValueP(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Date date= new Date();//创建一个时间对象，获取到当前的时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间显示格式
		String str = sdf.format(date);//将当前时间格式化为需要的类型
		paraSysValueP.setSysDt(Timestamp.valueOf(str));
		paraSysValueP.setSysUserId(ParaSysValuePAction.getCurrentUserName());
		paraSysValuePService.updateParaSysValueP(paraSysValueP);
		return "updateParaSysValueP";
	}
	/**
	 * 添加产品信息
	 */
	public String savePSVP(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Date date= new Date();//创建一个时间对象，获取到当前的时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间显示格式
		String str = sdf.format(date);//将当前时间格式化为需要的类型
		paraSysValueP.setSysDt(Timestamp.valueOf(str));
		paraSysValueP.setSysUserId(ParaSysValuePAction.getCurrentUserName());
		paraSysValuePService.saveParaSordataP(paraSysValueP);
		HttpSession session = request.getSession(false);
		String tyna=paraSysValueP.getTyna();
		refreshList = "paraSysValuePqueryParaSysValueP";
		titleName = "查看计算参数信息";
		msg=" ["+tyna+"] ";
		session.setAttribute("msg", msg);
		return "savePSVP";
	
	}
	
	/**
	 * 判断ID是否已存在
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
	 * 获取当前用户名
	 */
	public static String getCurrentUserName(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		PUser loginuser=(PUser)session.getAttribute("pu");
		String name=loginuser.getUserName();
		return name;		
	}
	/**
	 * 查询
	 */
	public String queryParaSysValueP() throws Exception{
		tynalist=bProductPService.alltyna();
		HttpServletRequest request=ServletActionContext.getRequest();
		StringBuffer sql=new StringBuffer("select * from para_sys_value_p where 0=0");

		this.tyna=request.getParameter("tyna");
		if(tyna!=null&&!tyna.isEmpty()){
			tyna=new String(tyna.trim().getBytes("ISO-8859-1"),"UTF-8");
			sql.append(" and tyna like '%"+tyna+"%'");
		}
		sql.append(" order by sys_dt desc");
		
		rows = util.getTotalCount(sql.toString());

		page = rows % pageSize == 0 ? rows / pageSize : rows / pageSize + 1;

		offset = getPageOffset();
		
		paraSysValuePList = util.getPageListBySql(sql.toString(), String.valueOf(offset), String.valueOf(pageSize),new Class[]{ParaSysValueP.class});
		
		request.setAttribute("tynalist", tynalist);
		return "queryParaSysValueP";
	}

	
	// Added by JSL : 获取翻页偏移量(实际上是将要翻到的页面的页索引，页索引从 0 开始)
	private int getPageOffset() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String ofst = request.getParameter("offset");
		int idx = 0;
		if(ofst!=null){
			idx = Integer.valueOf(ofst);
			idx = idx < 0 ? 0 : idx;                        // 超过第一页时，不再翻页
			idx = idx >= page ? (page-1) : idx;	// 超过最后一页时，不再翻页		
		}
		return idx;
	}

}

