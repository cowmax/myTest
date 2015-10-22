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
	
	private ParaSordataService paraSordataService;// service����
	private List<ParaSordata> paraSordataList;// ParaSordata����
	private List<Para_Type> pList;// ParaSordata����
	private ParaSordata paraSordata;// ����
	private ParaSordataId paraSordataId;
	private String valueType;// ��������
	private int rows;//�ܵ�����
	private int page;//ҳ��
	private int pageSize=5;//ÿҳ��ʾ������
	private int offset;//����jspҳ�洫����ҳ����
	private boolean flag;
	private String tyna;	//��Ʒ��Ŀ
	private UtilSupport util;	
	private String msg;
	private ParaValueTypeService paraSardataTypeService;
	private List<ParaValueType> paraSardataTypeList;
	private ParaValueType paraSardataType;
	private BProductPService bProductPService;
	private List<BProductP> allBProductPList;
	private BProductP bProductP;
	private List tynalist;

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
	 * ��ȡ�����û���Ϣ
	 * @return
	 */
	public String getAll(){
		paraSordataList=paraSordataService.allParaSordata();
		return "all";
	}
	
	/**
	 * �����û�ID��ȡ�û���ϸ��Ϣ
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
	 * ͨ��id��ɾ����Ʒ��Ϣ
	 */
	public String delParaSordata(){
		try {
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpServletResponse response=ServletActionContext.getResponse();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			valueType=new String(valueType.getBytes("iso-8859-1"), "utf-8");
			tyna=new String(tyna.getBytes("iso-8859-1"), "utf-8");
			paraSordataId=new ParaSordataId(valueType,tyna);
			paraSordata=paraSordataService.findParaSordataById(paraSordataId);
			paraSordataService.delParaSordata(paraSordata);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "delParaSordata";
	}
	
	/**
	 * ͨ��id���޸Ĳ�Ʒ��Ϣ
	 */
	public String updateParaSordata(){
		paraSordataService.updateParaSordata(paraSordata);
		return "updateParaSordata";
	}
	/**
	 * ��Ӳ�Ʒ��Ϣ
	 */
	public String saveParaSordata(){
		HttpServletRequest request=ServletActionContext.getRequest();
		paraSordata.setId(paraSordataId);
		Date date= new Date();//����һ��ʱ����󣬻�ȡ����ǰ��ʱ��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//����ʱ����ʾ��ʽ
		String str = sdf.format(date);//����ǰʱ���ʽ��Ϊ��Ҫ������
		paraSordata.setSysDt(Timestamp.valueOf(str));
		paraSordata.setSysUserId(ParaSordataAction.getCurrentUserName());
		paraSordataService.saveParaSordata(paraSordata);
		HttpSession session = request.getSession(false);
		String paraSordataId=paraSordata.getId().getTyna();
		String paraSordatanaypd=paraSordata.getId().getValueType();
		paraSardataType=paraSardataTypeService.findParaSardataTypeById(paraSordatanaypd);
		String typename=paraSardataType.getValTypeName();
		msg=" ["+paraSordataId+"] ��  ["+typename+"] ";
		session.setAttribute("msg", msg);
		return "saveParaSordata";
	
	}
	
	/**
	 * ��ҳ��ѯ��
	 */
	public String getPageParaSordata(){
		try {
			paraSardataTypeList=paraSardataTypeService.allParaSardataType();
			tynalist=bProductPService.alltyna();
			HttpServletRequest request=ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			String ofst = request.getParameter("offset");
			if(ofst!=null){
				offset=Integer.valueOf(ofst);
			}else{
				offset=1;
			}
			//String sql="select valTypeName,value_ratio,value_min,value_max,value_desc,sys_dt,sys_user_id,tyna from para_sordata a left join para_value_type b on a.value_type = b.valTypeId GROUP BY valTypeName,value_ratio,value_min,value_max,value_desc,sys_dt,sys_user_id,tyna";
			rows = paraSordataService.getParaSordataTotal();
			page = rows % pageSize == 0 ? rows / pageSize
					: rows / pageSize + 1;
			if (offset < 1) {
				offset = 1;
			} else if (offset > page) {
				offset = page;
			}
			pList=paraSordataService.getPageParaSordata1(String.valueOf(offset), String.valueOf(pageSize));
//			for (int i = 0; i < pList.size(); i++) {
//				System.out.println("aaa"+pList.get(i).getTyna());
//			}
			request.setAttribute("pList", pList);
			request.setAttribute("tynalist", tynalist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * �ж�ID�Ƿ��Ѵ���
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
	 * ���ϲ�ѯ
	 */
	@SuppressWarnings("unchecked")
	public String getByOptions() throws Exception{
		Map<String, String>parms=new HashMap<String, String>();
		HttpServletRequest request=ServletActionContext.getRequest();
		String ofst = request.getParameter("offset");
		if(ofst!=null){
			offset=Integer.valueOf(ofst);
		}else{
			offset=1;
		}
		String tyna = new String(request.getParameter("tyna").getBytes("iso-8859-1"), "utf-8");
		String valueType=new String(request.getParameter("valueType").getBytes("iso-8859-1"), "utf-8");
		if(tyna!=null){
			if(!tyna.equals("")){
				parms.put("id.tyna", tyna);
			}
		}
		if(valueType!=null){
			if(!valueType.equals("")){
				parms.put("id.valueType", valueType);
			}
		}
		if (offset < 1) {
			offset = 1;
		} else if (offset > page) {
			offset = page;
		}
		paraSordataList=util.getLisByOptions("ParaSordata",String.valueOf(offset), String.valueOf(pageSize), parms," order by sysDt desc");
		pList=new ArrayList<Para_Type>();
		for (int i = 0; i < paraSordataList.size(); i++) {
			Para_Type para_Type=new Para_Type();
			paraSardataType=paraSardataTypeService.findParaSardataTypeById(paraSordataList.get(i).getId().getValueType());
			para_Type.setValueTypeName(paraSardataType.getValTypeName());
			para_Type.setValueType(paraSardataType.getValTypeId());
			para_Type.setTyna(paraSordataList.get(i).getId().getTyna());
			para_Type.setSysDt(paraSordataList.get(i).getSysDt());
			para_Type.setSysUserId(paraSordataList.get(i).getSysUserId());
			para_Type.setValueDesc(paraSordataList.get(i).getValueDesc());
			para_Type.setValueMax(paraSordataList.get(i).getValueMax());
			para_Type.setValueMin(paraSordataList.get(i).getValueMin());
			para_Type.setValueRatio(paraSordataList.get(i).getValueRatio());
			System.out.println(para_Type.getValueTypeName());
			System.out.println(para_Type.getValueType());
			pList.add(para_Type);
		}
		request.setAttribute("pList", pList);
		return "getByOptions";
	}
	public String comboxLoad(){
		paraSardataTypeList=paraSardataTypeService.allParaSardataType();
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
	
}
