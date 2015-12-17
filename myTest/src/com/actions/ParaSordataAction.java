package com.actions;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bean.BProductP;
import com.bean.PGroup;
import com.bean.PGroupUser;
import com.bean.PUser;
import com.bean.ParaSordata;
import com.bean.ParaSordataId;
import com.bean.ParaValueType;
import com.bean.Para_Type;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BProductPService;
import com.service.ParaValueTypeService;
import com.service.ParaSordataService;
import com.serviceimpl.UtilSupport;

@SuppressWarnings("serial")
public class ParaSordataAction extends ActionSupport {

	private ParaSordataService paraSordataService;// service对象
	private List<ParaSordata> paraSordataList;// ParaSordata集合
	private List<Para_Type> pList;// ParaSordata集合
	private ParaSordata paraSordata;// 对象
	private ParaSordataId paraSordataId;
	private String valueType;// 参数类型
	private int rows;//总的条数
	private int page;//页数
	private int pageSize=10;//每页显示的条数
	private int offset;//接受jsp页面传来的页面数
	private boolean flag;
	private String tyna;	//产品类目
	private UtilSupport util;	
	private String msg;
	private ParaValueTypeService paraSardataTypeService;
	private List<ParaValueType> paraSardataTypeList;
	private ParaValueType paraSardataType;
	private BProductPService bProductPService;
	private List<BProductP> allBProductPList;
	private BProductP bProductP;
	private List tynalist;

	public ParaSordataAction() {
		pList=new ArrayList<Para_Type>();
	}

	public List getTynalist() {
		return tynalist;
	}

	public void setTynalist(List tynalist) {
		this.tynalist = tynalist;
	}

	public ParaSordataId getParaSordataId() {
		return paraSordataId;
	}

	public void setParaSordataId(ParaSordataId paraSordataId) {
		this.paraSordataId = paraSordataId;
	}

	public String getTyna() {
		return tyna;
	}

	public void setTyna(String tyna) {
		this.tyna = tyna;
	}

	public ParaValueTypeService getParaSardataTypeService() {
		return paraSardataTypeService;
	}

	public void setParaSardataTypeService(
			ParaValueTypeService paraSardataTypeService) {
		this.paraSardataTypeService = paraSardataTypeService;
	}

	public List<ParaValueType> getParaSardataTypeList() {
		return paraSardataTypeList;
	}

	public void setParaSardataTypeList(List<ParaValueType> paraSardataTypeList) {
		this.paraSardataTypeList = paraSardataTypeList;
	}

	public ParaValueType getParaSardataType() {
		return paraSardataType;
	}

	public void setParaSardataType(ParaValueType paraSardataType) {
		this.paraSardataType = paraSardataType;
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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public UtilSupport getUtil() {
		return util;
	}

	public void setUtil(UtilSupport util) {
		this.util = util;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public ParaSordataService getParaSordataService() {
		return paraSordataService;
	}

	public void setParaSordataService(ParaSordataService paraSordataService) {
		this.paraSordataService = paraSordataService;
	}

	public List<ParaSordata> getParaSordataList() {
		return paraSordataList;
	}

	public void setParaSordataList(List<ParaSordata> paraSordataList) {
		this.paraSordataList = paraSordataList;
	}

	public ParaSordata getParaSordata() {
		return paraSordata;
	}

	public void setParaSordata(ParaSordata paraSordata) {
		this.paraSordata = paraSordata;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
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

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获取所有用户信息
	 * @return
	 */
	public String getAll(){
		paraSordataList=paraSordataService.allParaSordata();
		return "all";
	}

	/**
	 * 根据用户ID获取用户详细信息
	 * @return
	 */
	public String getDetail(){
		try {

			HttpServletRequest request=ServletActionContext.getRequest();
			HttpServletResponse response=ServletActionContext.getResponse();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			valueType=new String(valueType.getBytes("iso-8859-1"), "utf-8");
			tyna=new String(tyna.getBytes("iso-8859-1"), "utf-8");
			paraSordataId=new ParaSordataId(valueType,tyna);
			paraSordata = paraSordataService.findParaSordataById(paraSordataId);
			paraSardataType=paraSardataTypeService.findParaSardataTypeById(valueType);
			request.setAttribute("paraSordata", paraSordata);
			request.setAttribute("paraSardataType", paraSardataType);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "detail";
	}

	/**
	 * 通过id来删除产品信息
	 */
	public String delParaSordata(){
		try {
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpServletResponse response=ServletActionContext.getResponse();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			//获取传过来的值
			String tyna=request.getParameter("tyna");
			String valueType=request.getParameter("valueType");
			String vT=new String(valueType.getBytes("iso-8859-1"), "utf-8");
			String ta=new String(tyna.getBytes("iso-8859-1"), "utf-8");
			
			//传值调用方法
			paraSordataId=new ParaSordataId(vT,ta);
			paraSordata=paraSordataService.findParaSordataById(paraSordataId);
			paraSordataService.delParaSordata(paraSordata);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "del";
	}

	/**
	 * 通过id来修改产品信息
	 */
	public String updateParaSordata(){
		paraSordataService.updateParaSordata(paraSordata);
		return "updateParaSordata";
	}
	/**
	 * 添加产品信息
	 */
	public String saveParaSordata(){
		HttpServletRequest request=ServletActionContext.getRequest();
		paraSordata.setId(paraSordataId);
		Date date= new Date();//创建一个时间对象，获取到当前的时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间显示格式
		String str = sdf.format(date);//将当前时间格式化为需要的类型
		paraSordata.setSysDt(Timestamp.valueOf(str));
		paraSordata.setSysUserId(ParaSordataAction.getCurrentUserName());
		paraSordataService.saveParaSordata(paraSordata);
		HttpSession session = request.getSession(false);
		String paraSordataId=paraSordata.getId().getTyna();
		String paraSordatanaypd=paraSordata.getId().getValueType();
		paraSardataType=paraSardataTypeService.findParaSardataTypeById(paraSordatanaypd);
		String typename=paraSardataType.getValTypeName();
		msg=" ["+paraSordataId+"] 的  ["+typename+"] ";
		session.setAttribute("msg", msg);
		return "saveParaSordata";

	}

	/**
	 * 判断ID是否已存在
	 * @return
	 */

	public String judgeId(){
		try {
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpServletResponse response=ServletActionContext.getResponse();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			tyna=request.getParameter("tyna");
			valueType=request.getParameter("valueType");
			paraSordataId=new ParaSordataId(valueType,tyna);
			paraSordata = paraSordataService.findParaSordataById(paraSordataId);
			if (null != paraSordata) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "judgeId";
	}

	/**
	 * 联合查询
	 */
	@SuppressWarnings("unchecked")
	public String getByOptions() throws Exception{

		HttpServletRequest request=ServletActionContext.getRequest();

		paraSardataTypeList=paraSardataTypeService.allParaSardataType();
		tynalist=bProductPService.alltyna();

		StringBuffer sql=new StringBuffer("select * from para_sordata_p a inner join para_value_type b on a.value_type = b.valTypeId where 0=0");

		this.tyna=request.getParameter("tyna");
		if(tyna!=null&&!tyna.isEmpty()){
			tyna=new String(tyna.trim().getBytes("ISO-8859-1"),"UTF-8");
			sql.append(" and tyna like '%"+tyna+"%'");
		}

		this.valueType=request.getParameter("valueType");
		if(valueType!=null&&!valueType.isEmpty()){
			valueType=new String(valueType.trim().getBytes("ISO-8859-1"),"UTF-8");
			sql.append(" and value_type like '%"+valueType+"%'");
		}

		sql.append(" order by sys_dt desc");

		rows = util.getTotalCount(sql.toString());

		page = rows % pageSize == 0 ? rows / pageSize : rows / pageSize + 1;

		offset = getPageOffset();

		List<Object[]> resultSet = util.getPageListBySql(sql.toString(), String.valueOf(offset), String.valueOf(pageSize),new Class[]{ParaSordata.class,ParaValueType.class});

		fillPgList(resultSet);

		request.setAttribute("pList", pList);
		return "getByOptions";
	}

	// 填充 PGroupUser 对像 List
	private void fillPgList(List<Object[]> resultSet) {
		pList.clear();

		for (Object[] r : resultSet) 
		{
			Para_Type para_Type = new Para_Type();
			ParaSordata ps = (ParaSordata)r[0];
			ParaValueType pvt = (ParaValueType)r[1];

			para_Type.setValueTypeName(pvt.getValTypeName());
			para_Type.setValueType(pvt.getValTypeId());
			para_Type.setTyna(ps.getId().getTyna());
			para_Type.setSysDt(ps.getSysDt());
			para_Type.setSysUserId(ps.getSysUserId());
			para_Type.setValueDesc(ps.getValueDesc());
			para_Type.setValueMax(ps.getValueMax());
			para_Type.setValueMin(ps.getValueMin());
			para_Type.setValueRatio(ps.getValueRatio());

			pList.add(para_Type);
		}
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

	public String comboxLoad(){
		paraSardataTypeList=paraSardataTypeService.allParaSardataType();
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

}
