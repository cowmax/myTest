package com.actions;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bean.PCaseAudit;
import com.bean.PUser;
import com.bean.ParaCaseP;
import com.bean.ParaDt;
import com.bean.RefactorParaDt;
import com.bean.Store;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BProductPService;
import com.service.PCaseAuditService;
import com.service.ParaCasePService;
import com.service.ParaDtService;
import com.serviceimpl.UtilSupport;

/**
 * 活动审核结果action类
 */
public class PCaseAuditAction extends ActionSupport {
	private ParaCasePService paraCasePService;//活动类型的Service
	private ParaDtService paraDtService;//活动实例的service
	private PCaseAuditService pCaseAuditService;//活动实例的service
	private UtilSupport util;//公共service
	private List<ParaCaseP> listCaseName;//活动类型集合
	private List<RefactorParaDt> refactorParaDtList;// ParaSysValueP集合
	
	private RefactorParaDt refactorParaDt;//重构活动对象
	private ParaDt paraDt;//活动实例对象
	private PCaseAudit caseAudit=new PCaseAudit();//活动审核对象
	
	private int rows;//总的条数
	private int page;//页数
	private int pageSize=10;//每页显示的条数
	private int offset;//接受jsp页面传来的页面数
	private String caseName;//活动类型
	private String brde;//活动品牌
	private Timestamp caseSt;//活动开始时间
	private Timestamp caseEt;//活动结束时间
	private String caseDesc;//活动说明
	private String msg;//判断保存成功 
	
	
	
	/**
	 * 初始化集合
	 */
	public PCaseAuditAction() {
		refactorParaDtList=new ArrayList<RefactorParaDt>();
	}
	
	
	
	/**
	 * 封装
	 */
	public ParaCasePService getParaCasePService() {
		return paraCasePService;
	}

	public void setParaCasePService(ParaCasePService paraCasePService) {
		this.paraCasePService = paraCasePService;
	}

	public ParaDtService getParaDtService() {
		return paraDtService;
	}

	public void setParaDtService(ParaDtService paraDtService) {
		this.paraDtService = paraDtService;
	}

	public PCaseAuditService getpCaseAuditService() {
		return pCaseAuditService;
	}

	public void setpCaseAuditService(PCaseAuditService pCaseAuditService) {
		this.pCaseAuditService = pCaseAuditService;
	}

	public List<ParaCaseP> getListCaseName() {
		return listCaseName;
	}

	public void setListCaseName(List<ParaCaseP> listCaseName) {
		this.listCaseName = listCaseName;
	}

	public List<RefactorParaDt> getRefactorParaDtList() {
		return refactorParaDtList;
	}

	public void setRefactorParaDtList(List<RefactorParaDt> refactorParaDtList) {
		this.refactorParaDtList = refactorParaDtList;
	}

	public RefactorParaDt getRefactorParaDt() {
		return refactorParaDt;
	}

	public void setRefactorParaDt(RefactorParaDt refactorParaDt) {
		this.refactorParaDt = refactorParaDt;
	}

	public ParaDt getParaDt() {
		return paraDt;
	}

	public void setParaDt(ParaDt paraDt) {
		this.paraDt = paraDt;
	}


	public PCaseAudit getCaseAudit() {
		return caseAudit;
	}


	public void setCaseAudit(PCaseAudit caseAudit) {
		this.caseAudit = caseAudit;
	}



	public UtilSupport getUtil() {
		return util;
	}

	public void setUtil(UtilSupport util) {
		this.util = util;
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

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getBrde() {
		return brde;
	}

	public void setBrde(String brde) {
		this.brde = brde;
	}

	public Timestamp getCaseSt() {
		return caseSt;
	}

	public void setCaseSt(Timestamp caseSt) {
		this.caseSt = caseSt;
	}

	public Timestamp getCaseEt() {
		return caseEt;
	}

	public void setCaseEt(Timestamp caseEt) {
		this.caseEt = caseEt;
	}

	public String getCaseDesc() {
		return caseDesc;
	}

	public void setCaseDesc(String caseDesc) {
		this.caseDesc = caseDesc;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}



	/**
	 * 提交审核
	 */
	public String addPCaseAudit() {
		//获取页面传来的值
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			
			//获取当前时间
			Date date= new Date();//创建一个时间对象
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间显示格式
			String str = sdf.format(date);//将当前时间格式化为需要的类型
			
			//获取caseid
			String sCaseId = request.getParameter("caseId");
			Integer caseId = Integer.parseInt(sCaseId);
			
			//获取caseName
			String Name=request.getParameter("caseName");
			String caseName= new String(Name.getBytes("ISO-8859-1"),"UTF-8");
			
			//获取auditResult
			Integer auditResult=caseAudit.getAuditResult();
			
			//添加到对象中
			caseAudit.setCaseId(caseId);
			caseAudit.setSysDt(Timestamp.valueOf(str));
			caseAudit.setSysUserId(PCaseAuditAction.getCurrentUserName());
			pCaseAuditService.savePCaseAudit(caseAudit);
			
			//判断状态
			if(auditResult==1){
				
				//更改选款结果对应的SKU明细的状态
				util.setPrdtStatus(caseId, 5, auditResult);
				
				//保存成功返回
				HttpSession session = request.getSession(false);
				msg =caseName+ " 【 活动id=" + caseId + "】 已经审核通过";
				session.setAttribute("msg", msg);
			}else{
				
				HttpSession session = request.getSession(false);
				msg = caseName+" 【活动id=" + caseId + "】 已经成功退回";
				session.setAttribute("msg", msg);
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "addPCaseAudit";
	}
	
	/**
	 * 获取当前用户名
	 */
	public static String getCurrentUserName() {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		PUser loginuser=(PUser)session.getAttribute("pu");
		String name=loginuser.getUserName();
		return name;
	}
	
	/**
	 * 显示待审核活动
	 */
	public String showPCaseAudit() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");

			// 获取活动类型
			listCaseName = new ArrayList<ParaCaseP>();
			listCaseName = paraCasePService.allParaCaseP();

			StringBuffer sql = new StringBuffer("select * from para_dt a "
					+ "INNER JOIN para_case_p b on a.case_code=b.case_code "
					+ "INNER JOIN Store c ON b.chal_cd=c.Code "
					+ "where a.status=5 ");

			this.caseName = request.getParameter("caseName");
			if (caseName != null && !caseName.isEmpty()) {
				caseName = new String(caseName.trim().getBytes("ISO-8859-1"),
						"UTF-8");
				sql.append(" and a.case_name = '" + caseName + "'");
			}

			this.brde = request.getParameter("brde");
			if (brde != null && !brde.isEmpty()) {
				if (!brde.equals("活动的品牌")) {
					brde = new String(brde.trim().getBytes("ISO-8859-1"),
							"UTF-8");
					sql.append(" and b.brde = '" + brde + "'");
				}
			}

			this.caseDesc = request.getParameter("caseDesc");
			if (caseDesc != null && !caseDesc.isEmpty()) {
				caseDesc = new String(caseDesc.trim().getBytes("ISO-8859-1"),
						"UTF-8");
				sql.append(" and a.case_desc like '%" + caseDesc + "%'");
			}

			String caseStime = request.getParameter("caseSt");
			String caseEtime = request.getParameter("caseEt");
			Calendar cal = Calendar.getInstance();

			if (caseStime != null && !caseStime.isEmpty()) {
				this.caseSt = Timestamp.valueOf(caseStime);
				if (caseEtime != null && !caseEtime.isEmpty()) {
					this.caseEt = Timestamp.valueOf(caseEtime);
				} else {
					cal.setTime(caseSt);
					int day = cal.get(Calendar.DATE);
					int month = cal.get(Calendar.MONTH);
					int year = cal.get(Calendar.YEAR);

					cal.set(year + 5, month, day);
					year = cal.get(Calendar.YEAR);
					this.caseEt = new Timestamp(cal.getTimeInMillis());
				}
				sql.append(" and ((a.case_st >= '"
						+ caseSt
						+ "' and a.case_et <= '"
						+ caseEt
						+ "') "
						+ " or( a.case_st >= '"
						+ caseSt
						+ "' and ('"
						+ caseEt
						+ "' between a.case_st and a.case_et))"
						+ " or(('"
						+ caseSt
						+ "' between a.case_st and a.case_et ) and ('"
						+ caseEt
						+ "' between a.case_st and a.case_et))"
						+ " or(('"
						+ caseSt
						+ "' between a.case_st and a.case_et ) and a.case_et <= '"
						+ caseEt + "'))");
			} else {
				if (caseEtime != null && !caseEtime.isEmpty()) {
					this.caseEt = Timestamp.valueOf(caseEtime);
					cal.setTime(caseEt);
					int day = cal.get(Calendar.DATE);
					int month = cal.get(Calendar.MONTH);
					int year = cal.get(Calendar.YEAR);

					cal.set(year - 5, month, day);
					this.caseSt = new Timestamp(cal.getTimeInMillis());

					sql.append(" and ((a.case_st >= '"
							+ caseSt
							+ "' and a.case_et <= '"
							+ caseEt
							+ "') "
							+ " or( a.case_st >= '"
							+ caseSt
							+ "' and ('"
							+ caseEt
							+ "' between a.case_st and a.case_et))"
							+ " or(('"
							+ caseSt
							+ "' between a.case_st and a.case_et ) and ('"
							+ caseEt
							+ "' between a.case_st and a.case_et))"
							+ " or(('"
							+ caseSt
							+ "' between a.case_st and a.case_et ) and a.case_et <= '"
							+ caseEt + "'))");
				}
			}

			
			 sql.append(" order by a.case_st desc");
			
			rows = util.getTotalCount(sql.toString());

			page = rows % pageSize == 0 ? rows / pageSize : rows / pageSize + 1;

			offset = getPageOffset();

			List<Object[]> resultSet = util.getPageListBySql(sql.toString(),
					String.valueOf(offset), String.valueOf(pageSize),
					new Class[] { ParaDt.class, ParaCaseP.class, Store.class });

			// 把结果集转存到成员变理 pgulis 中
			fillPcpList(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}


	// Added by JSL : 获取翻页偏移量(实际上是将要翻到的页面的页索引，页索引从 0 开始)
	private int getPageOffset() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String ofst = request.getParameter("offset");
		int idx = 0;
		if (ofst != null) {
			idx = Integer.valueOf(ofst);
			idx = idx < 0 ? 0 : idx; // 超过第一页时，不再翻页
			idx = idx >= page ? (page - 1) : idx; // 超过最后一页时，不再翻页
		}
		return idx;
	}

	//封装到对象中
	private void fillPcpList(List<Object[]> resultSet) {
		refactorParaDtList.clear();
		for (Object[] r : resultSet) {
			RefactorParaDt pcp = new RefactorParaDt();
			ParaDt pd = (ParaDt) r[0];
			ParaCaseP pc = (ParaCaseP) r[1];
			Store s = (Store) r[2];

			pcp.setCaseId(pd.getCaseId());
			pcp.setCaseEt(pd.getCaseEt());
			pcp.setCaseSt(pd.getCaseSt());
			String desc = pd.getCaseDesc();
			if (desc != null) {
				if (desc.length() > 10) {
					desc = desc.substring(0, 10);
				}
			}
			pcp.setCaseDesc(desc);
			pcp.setNum(pd.getNum());
			pcp.setStatus(pd.getStatus());
			pcp.setSysDt(pd.getSysDt());
			pcp.setSysUserId(pd.getSysUserId());
			pcp.setCaseName(pd.getCaseName());
			pcp.setRatioNew(pd.getRatioNew());

			pcp.setBrde(pc.getBrde());
			pcp.setCaseLevel(pc.getCaseLevel());
			pcp.setPreNum(pc.getPreNum());

			pcp.setName(s.getName());

			refactorParaDtList.add(pcp);
		}
	}

}
