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

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.bean.PUser;
import com.bean.ParaCaseP;
import com.bean.ParaDt;
import com.bean.RefactorParaDt;
import com.bean.Store;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ParaCasePService;
import com.service.ParaDtService;
import com.service.StoreService;
import com.serviceimpl.UtilSupport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@SuppressWarnings("serial")
public class ParaDtAction extends ActionSupport {
	private ParaCasePService paraCasePService;
	private ParaDtService paraDtService;// service对象
	private List<ParaDt> paraDtList;// ParaDt集合
	private ParaDt paraDt;// 对象
	private int rows;// 总的条数
	private int page;// 页数
	private int pageSize = 10;// 每页显示的条数
	private int offset;// 接受jsp页面传来的页面数
	private boolean flag;
	private String msg;
	private RefactorParaDt refactorParaDt;// 重构活动对象
	private List<RefactorParaDt> refactorParaDtList;// ParaSysValueP集合
	private UtilSupport util;// service对象
	private ParaCaseP paraCaseP;// Paracasep对象
	private List<ParaCaseP> listCaseName;
	private Map<String, Object> dataMap;
	private Map<String, Object> jsonResult;
	private List<Store> storeList; // 渠道集合
	private StoreService storeService;

	private Integer caseId;
	private String caseName;
	private String brde;
	private Timestamp caseSt;
	private Timestamp caseEt;
	private String caseDesc;
	private String chalCd;
	private Integer status;
	private List<ParaDt> intolist;

	// myFile属性用来封装上传的文件
	private File myFile;
	// myFileContentType属性用来封装上传文件的类型
	private String myFileContentType;
	// myFileFileName属性用来封装上传文件的文件名
	private String myFileFileName;

	private String sort;
	private String order;
	
	private String refreshList;
	private String titleName;

	public ParaDtAction() {
		refactorParaDtList = new ArrayList<RefactorParaDt>();
		intolist = new ArrayList<ParaDt>();
		storeList = new ArrayList<Store>();
	}

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

	public List<ParaDt> getParaDtList() {
		return paraDtList;
	}

	public void setParaDtList(List<ParaDt> paraDtList) {
		this.paraDtList = paraDtList;
	}

	public ParaDt getParaDt() {
		return paraDt;
	}

	public void setParaDt(ParaDt paraDt) {
		this.paraDt = paraDt;
	}

	public List<Store> getStoreList() {
		return storeList;
	}

	public void setStoreList(List<Store> storeList) {
		this.storeList = storeList;
	}

	public StoreService getStoreService() {
		return storeService;
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
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

	public RefactorParaDt getRefactorParaDt() {
		return refactorParaDt;
	}

	public void setRefactorParaDt(RefactorParaDt refactorParaDt) {
		this.refactorParaDt = refactorParaDt;
	}

	public List<RefactorParaDt> getRefactorParaDtList() {
		return refactorParaDtList;
	}

	public void setRefactorParaDtList(List<RefactorParaDt> refactorParaDtList) {
		this.refactorParaDtList = refactorParaDtList;
	}

	public UtilSupport getUtil() {
		return util;
	}

	public void setUtil(UtilSupport util) {
		this.util = util;
	}

	public ParaCaseP getParaCaseP() {
		return paraCaseP;
	}

	public void setParaCaseP(ParaCaseP paraCaseP) {
		this.paraCaseP = paraCaseP;
	}

	public List<ParaCaseP> getListCaseName() {
		return listCaseName;
	}

	public void setListCaseName(List<ParaCaseP> listCaseName) {
		this.listCaseName = listCaseName;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public Map<String, Object> getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(Map<String, Object> jsonResult) {
		this.jsonResult = jsonResult;
	}

	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getChalCd() {
		return chalCd;
	}

	public void setChalCd(String chalCd) {
		this.chalCd = chalCd;
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

	public List<ParaDt> getIntolist() {
		return intolist;
	}

	public void setIntolist(List<ParaDt> intolist) {
		this.intolist = intolist;
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

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取所有名称
	 */
	public String getAllName() {
		// 创建集合封装所有的paracasep
		listCaseName = new ArrayList<ParaCaseP>();

		listCaseName = paraCasePService.allParaCaseP();
		return "getAllName";
	}

	/**
	 * 通过名称获取活动类型
	 */
	public String getNamePCP() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");

			// 获取活动类型
			String caseName = request.getParameter("caseName");
			paraCaseP = paraCasePService.getNameParaCaseP(caseName);

			// 创建map集合封装页面要显示的数据
			dataMap = new HashMap<String, Object>();
			dataMap.put("caseCode", paraCaseP.getCaseCode());
			dataMap.put("CType", paraCaseP.getCType());
			dataMap.put("num", paraCaseP.getNum());

			// 将map对象转换成json类型数据
			jsonResult = dataMap;

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "jsonResult";
	}

	/**
	 * 通过活动类型获取时间进行比较
	 */
	public String judgeNameRepeat() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");

			// 获取活动类型 开始时间 结束时间
			String caseName = request.getParameter("caseName");
			String caseStime = request.getParameter("caseSt");
			String caseEtime = request.getParameter("caseEt");

			// 转化为dateTime
			Timestamp startTime = Timestamp.valueOf(caseStime);
			Timestamp endTime = Timestamp.valueOf(caseEtime);

			int spaceTime = paraDtService.getCaseNameTime(caseName, startTime,
					endTime);
			if (spaceTime > 0) {
				flag = true;
			} else if (spaceTime == 0) {
				flag = false;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SUCCESS;
	}

	/**
	 * 获取所有计算参数信息
	 */
	public String getAll() {
		paraDtList = paraDtService.allParaDt();
		return "allParaDt";
	}

	/**
	 * 通过id获取详细信息
	 */
	public String getParaDtId() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");

			paraDt = paraDtService.findParaDtById(caseId);

			// 通过活动名称获取相应的数据
			paraCaseP = paraCasePService.getNameParaCaseP(paraDt.getCaseName());

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "getParaDtId";
	}

	/**
	 * 修改活动信息
	 */
	public String updateParaDt() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Date date = new Date();// 创建一个时间对象，获取到当前的时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置时间显示格式
		String str = sdf.format(date);// 将当前时间格式化为需要的类型

		paraDt.setSysDt(Timestamp.valueOf(str));
		paraDt.setSysUserId(ParaCasePAction.getCurrentUserName());

		// 判断选款数
		// int styleNum=paraDt.getNum();
		if (paraDt.getNum() == 0 || paraDt.getNum() == null) {
			paraDt.setNum(paraCaseP.getNum());
		}
		paraDtService.updateParaDtImpl(paraDt);

		// 修改成功后，管理后台程序通知 BI系统执行活动选款。
		util.callPRtCase(paraDt.getCaseCode(), paraDt.getCaseId());
		return "all";
	}
	
	/**
	 * 提交审核
	 * @throws UnsupportedEncodingException 
	 */
	public String commitAudit() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		//获得活动id和状态
		String Id = request.getParameter("caseId");
		Integer caseId=Integer.valueOf(Id);
		
		//获取活动状态
		String stu = request.getParameter("status");
		Integer status=Integer.valueOf(stu);
		
		//获取活动名称
		String caseName = request.getParameter("caseName");
		
		//更改选款结果对应的SKU明细的状态
		util.setPrdtStatus(caseId, status, 5);
		
		//保存成功返回
		HttpSession session = request.getSession(false);
		msg =caseName+ " 【 活动id=" + caseId + "】 已经提交审核";
		session.setAttribute("msg", msg);
		return "commitSucceed";
	}
	
	/**
	 * 添加活动信息
	 */
	public String savePCD() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Date date = new Date();// 创建一个时间对象，获取到当前的时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置时间显示格式
		String str = sdf.format(date);// 将当前时间格式化为需要的类型

		// 封装数据
		paraDt.setCaseName(paraCaseP.getCaseName());
		paraDt.setCaseCode(paraCaseP.getCaseCode());
		paraDt.setStatus(2);
		paraDt.setSysDt(Timestamp.valueOf(str));
		paraDt.setSysUserId(ParaDtAction.getCurrentUserName());

		// 判断选款数
		// int styleNum=paraDt.getNum();
		if (paraDt.getNum() != 0) {
			paraDtService.saveParaDt(paraDt);
		} else {
			paraDt.setNum(paraCaseP.getNum());
			paraDtService.saveParaDt(paraDt);
		}

		// 添加成功后，管理后台程序通知 BI系统执行活动选款。
		util.callPRtCase(paraDt.getCaseCode(), paraDt.getCaseId());

		HttpSession session = request.getSession(false);
		String caseName = paraCaseP.getCaseName();
		
		refreshList = "paraCaseDtgetParaDtAll";
		titleName = "营销活动实例";
		msg = " [" + caseName + "] ";
		session.setAttribute("msg", msg);

		return "savePCDSucceed";

	}

	private List getParaDtInfo() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");

			// 获取活动类型
			listCaseName = new ArrayList<ParaCaseP>();
			listCaseName = paraCasePService.allParaCaseP();
			storeList = storeService.getStoreList();

			StringBuffer sql = new StringBuffer("select * from para_dt a "
					+ "INNER JOIN para_case_p b on a.case_code=b.case_code "
					+ "INNER JOIN Store c ON b.chal_cd=c.Code "
					+ "where a.status!=0 ");

			this.caseName = request.getParameter("caseName");
			if (caseName != null && !caseName.isEmpty()) {
				caseName = new String(caseName.trim().getBytes("ISO-8859-1"),
						"UTF-8");
				sql.append(" and a.case_name = '" + caseName + "'");
			}

			this.chalCd = request.getParameter("chalCd");
			if (chalCd != null && !chalCd.isEmpty()) {
				chalCd = new String(chalCd.trim().getBytes("ISO-8859-1"),
						"UTF-8");
				sql.append(" and c.name = '" + chalCd + "'");
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
				} else {
					this.caseSt = null;
					this.caseEt = null;
				}
			}

			if (sort == null || sort == "") {
				// 获取 开始时间 结束时间 升序 还是降序
				this.sort = request.getParameter("sort");
				this.order = request.getParameter("order");
			}

			if (sort != null && !sort.isEmpty()) {
				sql.append(" order by " + sort + " " + order);
			} else {
				sql.append(" order by a.sys_dt desc");
			}

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
		return refactorParaDtList;
	}

	/**
	 * 分页显示
	 */
	@SuppressWarnings("unchecked")
	public String getParaDtAll() {
		this.getParaDtInfo();
		return "all";
	}

	public String chooseParaDt() {

		this.getParaDtInfo();
		return "chooseParaDt";
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

	/**
	 * 删除活动
	 * 
	 * @return
	 */
	public String delParaDt() {
		// 根据活动ID查找到活动信息
		ParaDt pdt = paraDtService.findParaDtById(caseId);
		// 修改活动状态为已删除
		pdt.setStatus(0);
		paraDtService.delParaDtById(pdt);
		return "all";
	}

	/**
	 * 获取当前用户名
	 */
	public static String getCurrentUserName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		PUser loginuser = (PUser) session.getAttribute("pu");
		String name = loginuser.getUserName();
		return name;
	}

	/*
	 * 上传文件
	 */
	public String shangchan() throws Exception {
		// 基于myFile创建一个文件输入流
		InputStream is = new FileInputStream(myFile);
		// 设置上传文件目录
		String uploadPath = ServletActionContext.getServletContext()
				.getRealPath("/upload");
		// 设置目标文件
		File toFile = new File(uploadPath, this.getMyFileFileName());
		// 创建一个输出流
		File toDir = new File(uploadPath);
		if (!toDir.exists()) {
			toDir.mkdir();
		}
		if (!toFile.exists()) {
			toFile.createNewFile();
		}
		OutputStream os = new FileOutputStream(toFile);
		// 设置缓存
		byte[] buffer = new byte[1024];
		int length = 0;
		// 读取myFile文件输出到toFile文件中
		while ((length = is.read(buffer)) > 0) {
			os.write(buffer, 0, length);
		}
		// 关闭输入流
		is.close();
		// 关闭输出流
		os.close();
		return "upload";
	}

	/**
	 * 导入Excel表格
	 */
	@SuppressWarnings("unused")
	public String intoDB() throws IOException {
		String uploadPath = ServletActionContext.getServletContext()
				.getRealPath("/upload");
		// 基于myFile创建一个文件输入流
		InputStream is = new FileInputStream(myFile);
		// 设置目标文件
		File toFile = new File(uploadPath, this.getMyFileFileName());

		String caseName = null;// 活动名称
		String caseDesc = null;// 活动描述
		Timestamp caseSt = null;// 活动开始时间
		Timestamp caseEt = null;// 活动结束时间
		String sysUserId = null;// 创建/修改用户ID
		Timestamp sysDt = null;// 修改时间
		Integer status = null;// 活动状态
		String caseCode = null;// 活动编码
		Double ratioNew = null;// 新款占比
		Integer num = null;// 参与款数

		Date date = new Date();// 创建一个时间对象，获取到当前的时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置时间显示格式
		String str = sdf.format(date);// 将当前时间格式化为需要的类型
		sysDt = Timestamp.valueOf(str);
		sysUserId = ParaCasePAction.getCurrentUserName();

		/**
		 * 2007版的读取方法
		 */
		int k = 0;
		int flag = 0; // 指示指针所访问的位置
		if (myFile != null) {
			try {
				Workbook workbook = WorkbookFactory.create(toFile);
				// Workbook workbook = new XSSFWorkbook(is);//初始化workbook对象
				for (int numSheets = 0; numSheets < workbook
						.getNumberOfSheets(); numSheets++) { // 读取每一个sheet
					if (null != workbook.getSheetAt(numSheets)) {
						XSSFSheet aSheet = (XSSFSheet) workbook
								.getSheetAt(numSheets);// 定义Sheet对象

						for (int rowNumOfSheet = 1; rowNumOfSheet <= aSheet
								.getLastRowNum(); rowNumOfSheet++) {
							// 进入当前sheet的行的循环
							if (null != aSheet.getRow(rowNumOfSheet)) {
								XSSFRow aRow = aSheet.getRow(rowNumOfSheet);// 定义行，并赋值
								for (int cellNumOfRow = 0; cellNumOfRow <= aRow
										.getLastCellNum(); cellNumOfRow++) {
									intolist.clear();
									// 读取rowNumOfSheet值所对应行的数据
									XSSFCell xCell = aRow.getCell(cellNumOfRow); // 获得行的列数
																					// //获得列值
									// System.out.println("type="+xCell.getCellType());
									if (null != aRow.getCell(cellNumOfRow)) {
										// 如果rowNumOfSheet的值为0，则读取表头，判断excel的格式和预定格式是否相符
										if (rowNumOfSheet == 1) {
											if (xCell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
												/**
												 * 一下根据从Excel的各列命名是否符合要求：如下面匹配：
												 * 活动名称，活动描述，活动开始时间，
												 * 活动结束时间,操作用户,
												 * 修改时间,活动状态,活动编码,新款占比
												 * ,参与款数,备注开始时间,备注结束时间
												 */
												if (cellNumOfRow == 0) {
													if (xCell
															.getStringCellValue()
															.replace('\t', ' ')
															.replace('\n', ' ')
															.replace('\r', ' ')
															.trim()
															.equals("活动名称")) {
														flag++;
													} else {
														System.out
																.println("错误：第一行的活动名称不符合约定格式");
													}
												} else if (cellNumOfRow == 1) {
													if (xCell
															.getStringCellValue()
															.replace('\t', ' ')
															.replace('\n', ' ')
															.replace('\r', ' ')
															.trim()
															.equals("活动描述")) {
														flag++;
													} else {
														System.out
																.println("错误：第一行的活动描述不符合约定格式");
													}
												} else if (cellNumOfRow == 2) {
													if (xCell
															.getStringCellValue()
															.replace('\t', ' ')
															.replace('\n', ' ')
															.replace('\r', ' ')
															.trim()
															.equals("活动开始时间")) {
														flag++;
													} else {
														System.out
																.println("错误：第一行的活动开始时间不符合约定格式");
													}
												} else if (cellNumOfRow == 3) {
													if (xCell
															.getStringCellValue()
															.replace('\t', ' ')
															.replace('\n', ' ')
															.replace('\r', ' ')
															.trim()
															.equals("活动结束时间")) {
														flag++;
													} else {
														System.out
																.println("错误：第一行的活动结束时间不符合约定格式");
													}
												} else if (cellNumOfRow == 4) {
													if (xCell
															.getStringCellValue()
															.replace('\t', ' ')
															.replace('\n', ' ')
															.replace('\r', ' ')
															.trim()
															.equals("活动状态")) {
														flag++;
													} else {
														System.out
																.println("第一行的活动状态不符合约定格式");
													}
												} else if (cellNumOfRow == 5) {
													if (xCell
															.getStringCellValue()
															.replace('\t', ' ')
															.replace('\n', ' ')
															.replace('\r', ' ')
															.trim()
															.equals("活动编码")) {
														flag++;
													} else {
														System.out
																.println("第一行的活动编码不符合约定格式");
													}
												} else if (cellNumOfRow == 6) {
													if (xCell
															.getStringCellValue()
															.replace('\t', ' ')
															.replace('\n', ' ')
															.replace('\r', ' ')
															.trim()
															.equals("新款占比")) {
														flag++;
													} else {
														System.out
																.println("第一行的新款占比不符合约定格式");
													}
												} else if (cellNumOfRow == 7) {
													if (xCell
															.getStringCellValue()
															.replace('\t', ' ')
															.replace('\n', ' ')
															.replace('\r', ' ')
															.trim()
															.equals("参与款数")) {
														flag++;
													} else {
														System.out
																.println("第一行的参与款数不符合约定格式");
													}
												}
											}
										} else {
											// rowNumOfSheet != 0 即开始打印内容
											// 获取excel中每列的值，并赋予相应的变量，如下的赋值的ID，name,sex,
											// Dormitory,sept;
											if (xCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) { // 为数值型
												System.out
														.println("===============进入XSSFCell .CELL_TYPE_NUMERIC模块============");
												switch (cellNumOfRow) {
												case 2:
													Date St = (Date) xCell
															.getDateCellValue(); // 对日期处理
													caseSt = new Timestamp(
															St.getTime());
													break;
												case 3:
													Date Et = (Date) xCell
															.getDateCellValue(); // 对日期处理
													caseEt = new Timestamp(
															Et.getTime());
													break;
												case 4:
													status = (int) xCell
															.getNumericCellValue();
													break;
												case 6:
													ratioNew = xCell
															.getNumericCellValue();
													break;
												case 7:
													num = (int) xCell
															.getNumericCellValue();
													break;
												}
											} else if (xCell.getCellType() == XSSFCell.CELL_TYPE_STRING) { // 为字符串型
												System.out
														.println("===============进入XSSFCell .CELL_TYPE_STRING模块============");
												switch (cellNumOfRow) {
												case 0:
													caseName = xCell
															.getStringCellValue()
															.replace('\t', ' ')
															.replace('\n', ' ')
															.replace('\r', ' ')
															.trim();
													break;
												case 1:
													caseDesc = xCell
															.getStringCellValue()
															.replace('\t', ' ')
															.replace('\n', ' ')
															.replace('\r', ' ')
															.trim();
													break;
												case 5:
													caseCode = xCell
															.getStringCellValue()
															.replace('\t', ' ')
															.replace('\n', ' ')
															.replace('\r', ' ')
															.trim();
													break;

												}
											} else if (xCell.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
												System.out
														.println("提示：在Sheet"
																+ (numSheets + 1)
																+ "中的第"
																+ (rowNumOfSheet + 1)
																+ "行的第"
																+ (cellNumOfRow + 1)
																+ "列的值为空，请查看核对是否符合约定要求");
												switch (cellNumOfRow) {
												case 0:
													caseName = "";
													break;
												case 1:
													caseDesc = "";
													break;
												case 2:
													caseSt = null;
													break;
												case 3:
													caseEt = null;
													break;
												case 4:
													status = null;
													break;
												case 5:
													caseCode = "";
													break;
												}
											}
										}
									}
								}
								// 判断各个元素被赋值,如果放入数据库，就直接使用数据的插入的函数就可以了。
								if (aRow.getRowNum() > 1) {
									System.out.println("请核对后重试");
									ParaDt pd = new ParaDt();
									pd.setCaseName(caseName);
									pd.setCaseDesc(caseDesc);
									pd.setCaseEt(caseEt);
									pd.setCaseSt(caseSt);
									pd.setSysUserId(sysUserId);
									pd.setSysDt(sysDt);
									pd.setStatus(status);
									pd.setCaseCode(caseCode);
									pd.setRatioNew(ratioNew);
									pd.setNum(num);
									pd.setCaseCode(caseCode);

									intolist.add(pd);

								}
							} // 获得一行，即读取每一行
						}
						// 读取每一个sheet
					}
				}
				if (intolist.size() > 0) {
					for (int i = 0; i < intolist.size(); i++) {
						System.out.println(i + "size" + intolist.size());
						paraDtService.saveOneBoat(intolist.get(i));
						// 修改成功后，管理后台程序通知 BI系统执行活动选款。
						util.callPRtCase(intolist.get(i).getCaseCode(),
								intolist.get(i).getCaseId());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "importExcel";
	}

	/**
	 * 下载导入的模板
	 */
	public String importTemplate() throws Exception {
		/*
		 * 设置表头：对Excel每列取名(必须根据你取的数据编写)
		 */
		String[] tableHeader = { "活动名称", "活动描述", "活动开始时间", "活动结束时间", "活动状态",
				"活动编码", "新款占比", "参与款数" };
		util.getTemplate(tableHeader, "营销活动实例");
		return null;
	}
}
