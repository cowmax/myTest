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

	private ParaSordataService paraSordataService;// service����
	private List<ParaSordata> paraSordataList;// ParaSordata����
	private List<Para_Type> pList;// ParaSordata����
	private ParaSordata paraSordata;// ����
	private ParaSordataId paraSordataId;
	private String valueType;// ��������
	private int rows;// �ܵ�����
	private int page;// ҳ��
	private int pageSize = 10;// ÿҳ��ʾ������
	private int offset;// ����jspҳ�洫����ҳ����
	private boolean flag;
	private String tyna; // ��Ʒ��Ŀ
	private UtilSupport util;
	private String msg;
	private ParaValueTypeService paraSardataTypeService;
	private List<ParaValueType> paraSardataTypeList;
	private ParaValueType paraSardataType;
	private BProductPService bProductPService;
	private List<BProductP> allBProductPList;
	private BProductP bProductP;
	private List tynalist;
	
	private String refreshList;
	private String titleName;

	public ParaSordataAction() {
		pList = new ArrayList<Para_Type>();
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
	 * ��ȡ�����û���Ϣ
	 * 
	 * @return
	 */
	public String getAll() {
		paraSordataList = paraSordataService.allParaSordata();
		return "all";
	}

	/**
	 * ����û�ID��ȡ�û���ϸ��Ϣ
	 * 
	 * @return
	 */
	public String getDetail() {
		try {

			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			valueType = new String(valueType.getBytes("iso-8859-1"), "utf-8");
			tyna = new String(tyna.getBytes("iso-8859-1"), "utf-8");
			paraSordataId = new ParaSordataId(valueType, tyna);
			paraSordata = paraSordataService.findParaSordataById(paraSordataId);

			paraSardataType = paraSardataTypeService
					.findParaSardataTypeById(valueType);
			request.setAttribute("paraSordata", paraSordata);
			request.setAttribute("paraSardataType", paraSardataType);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "detail";
	}

	/**
	 * ͨ��id��ɾ���Ʒ��Ϣ
	 */
	public String delParaSordata() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");

			// ��ȡ��������ֵ
			String tyna = request.getParameter("tyna");
			String valueType = request.getParameter("valueType");
			String vT = new String(valueType.getBytes("iso-8859-1"), "utf-8");
			String ta = new String(tyna.getBytes("iso-8859-1"), "utf-8");

			// ��ֵ���÷���
			paraSordataId = new ParaSordataId(vT, ta);
			paraSordata = paraSordataService.findParaSordataById(paraSordataId);
			paraSordataService.delParaSordata(paraSordata);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "del";
	}

	/**
	 * ͨ��id���޸Ĳ�Ʒ��Ϣ
	 */
	public String updateParaSordata() {
		paraSordataService.updateParaSordata(paraSordata);
		return "updateParaSordata";
	}

	/**
	 * ��Ӳ�Ʒ��Ϣ
	 */
	public String saveParaSordata() {
		HttpServletRequest request = ServletActionContext.getRequest();
		paraSordata.setId(paraSordataId);
		Date date = new Date();// ����һ��ʱ����󣬻�ȡ����ǰ��ʱ��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ����ʱ����ʾ��ʽ
		String str = sdf.format(date);// ����ǰʱ���ʽ��Ϊ��Ҫ������
		paraSordata.setSysDt(Timestamp.valueOf(str));
		paraSordata.setSysUserId(ParaSordataAction.getCurrentUserName());
		paraSordataService.saveParaSordata(paraSordata);
		HttpSession session = request.getSession(false);
		String paraSordataId = paraSordata.getId().getTyna();
		String paraSordatanaypd = paraSordata.getId().getValueType();
		paraSardataType = paraSardataTypeService
				.findParaSardataTypeById(paraSordatanaypd);
		String typename = paraSardataType.getValTypeName();
		
		refreshList = "paraSordatagetByOptions";
		titleName = "查看生产参数信息";
		msg = " [" + paraSordataId + "] ��  [" + typename + "] ";
		session.setAttribute("msg", msg);
		return "saveParaSordata";

	}

	/**
	 * �ж�ID�Ƿ��Ѵ���
	 * 
	 * @return
	 */

	public String judgeId() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			tyna = request.getParameter("tyna");
			valueType = request.getParameter("valueType");
			paraSordataId = new ParaSordataId(valueType, tyna);
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
	public String getByOptions() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();

		paraSardataTypeList = paraSardataTypeService.allParaSardataType();
		tynalist = bProductPService.alltyna();

		StringBuffer sql = new StringBuffer(
				"select * from para_sordata_p a inner join para_value_type b on a.value_type = b.valTypeId where 0=0");

		this.tyna = request.getParameter("tyna");
		if (tyna != null && !tyna.isEmpty()) {
			tyna = new String(tyna.trim().getBytes("ISO-8859-1"), "UTF-8");
			sql.append(" and tyna like '%" + tyna + "%'");
		}

		this.valueType = request.getParameter("valueType");
		if (valueType != null && !valueType.isEmpty()) {
			valueType = new String(valueType.trim().getBytes("ISO-8859-1"),
					"UTF-8");
			sql.append(" and value_type like '%" + valueType + "%'");
		}

		sql.append(" order by sys_dt desc");

		rows = util.getTotalCount(sql.toString());

		page = rows % pageSize == 0 ? rows / pageSize : rows / pageSize + 1;

		offset = getPageOffset();

		List<Object[]> resultSet = util.getPageListBySql(sql.toString(),
				String.valueOf(offset), String.valueOf(pageSize), new Class[] {
						ParaSordata.class, ParaValueType.class });

		fillPgList(resultSet);

		request.setAttribute("pList", pList);
		return "getByOptions";
	}

	// ��� PGroupUser ���� List
	private void fillPgList(List<Object[]> resultSet) {
		pList.clear();

		for (Object[] r : resultSet) {
			Para_Type para_Type = new Para_Type();
			ParaSordata ps = (ParaSordata) r[0];
			ParaValueType pvt = (ParaValueType) r[1];

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

	public String comboxLoad() {
		paraSardataTypeList = paraSardataTypeService.allParaSardataType();
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

}
