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
		msg=" ["+tyna+"] ";
		session.setAttribute("msg", msg);
		return "savePSVP";
	
	}
	
	/**
	 * 分页查询的
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
		String a="周生财";	
		return a;	
	}
	/**
	 * 查询
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
