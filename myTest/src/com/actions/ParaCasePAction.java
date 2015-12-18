package com.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.hibernate.SessionFactory;

import com.bean.PUser;
import com.bean.ParaCaseP;
import com.bean.Store;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ParaCasePService;
import com.service.StoreService;
import com.serviceimpl.UtilSupport;

@SuppressWarnings("serial")
public class ParaCasePAction extends ActionSupport {

	private ParaCasePService paraCasePService;// service对象
	private List<ParaCaseP> paraCasePList;// paraCaseP集合
	private ParaCaseP paraCaseP;// 对象
	private UtilSupport util;
	private int rows;// 总的条数
	private int page;// 页数
	private int pageSize = 10;// 每页显示的条数
	private int offset;// 接受jsp页面传来的页面数
	private boolean flag;
	private String msg;
	private String caseCode;// 产品编号
	private StoreService storeService;//storeservice对象
	private List<Store> ListStore;
	private SessionFactory sessionFactory;
	List <ParaCaseP> pcpList=new ArrayList<ParaCaseP>();
	// myFile属性用来封装上传的文件
	private File myFile;
	// myFileContentType属性用来封装上传文件的类型
	private String myFileContentType;
	// myFileFileName属性用来封装上传文件的文件名
	private String myFileFileName;

	private String caseName;//产品名称
	private String chalCd;//渠道/店铺
	private String caseLevel;//活动级别
	private String brde;//活动品牌
	
	public ParaCasePAction() {
		paraCasePList = new ArrayList<ParaCaseP>();
	}

	public ParaCasePService getParaCasePService() {
		return paraCasePService;
	}

	public void setParaCasePService(ParaCasePService paraCasePService) {
		this.paraCasePService = paraCasePService;
	}

	public List<ParaCaseP> getParaCasePList() {
		return paraCasePList;
	}

	public void setParaCasePList(List<ParaCaseP> paraCasePList) {
		this.paraCasePList = paraCasePList;
	}

	public ParaCaseP getParaCaseP() {
		return paraCaseP;
	}

	public void setParaCaseP(ParaCaseP paraCaseP) {
		this.paraCaseP = paraCaseP;
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
	
	public String getChalCd() {
		return chalCd;
	}

	public void setChalCd(String chalCd) {
		this.chalCd = chalCd;
	}

	public String getCaseLevel() {
		return caseLevel;
	}

	public void setCaseLevel(String caseLevel) {
		this.caseLevel = caseLevel;
	}

	public String getBrde() {
		return brde;
	}

	public void setBrde(String brde) {
		this.brde = brde;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}


	public StoreService getStoreService() {
		return storeService;
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}


	public List<Store> getListStore() {
		return ListStore;
	}

	public void setListStore(List<Store> listStore) {
		ListStore = listStore;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
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

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	/**
	 * 获取所有活动信息
	 */
	public String getAll() {
		paraCasePList = paraCasePService.allParaCaseP();
		return "allParaCaseP";
	}

	/**
	 * 通过code获取活动信息
	 */
	public String getParaCasePId() {
		try {
			ListStore=storeService.getStoreList();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			request.setCharacterEncoding("UTF-8");
			caseCode = new String(caseCode.getBytes("iso-8859-1"), "utf-8");
			paraCaseP = paraCasePService.findParaCasePById(caseCode);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "detail";
	}

	/**
	 * 通过id来删除产品信息
	 */
	public String delParaCaseP() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			caseCode = new String(caseCode.getBytes("iso-8859-1"), "utf-8");
			paraCaseP = paraCasePService.findParaCasePById(caseCode);
			paraCasePService.delParaCasePById(paraCaseP);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "delParaCaseP";
	}

	/**
	 * 通过id来修改产品信息
	 */
	public String updateParaCaseP() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Date date = new Date();// 创建一个时间对象，获取到当前的时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置时间显示格式
		String str = sdf.format(date);// 将当前时间格式化为需要的类型
		paraCaseP.setSysDt(Timestamp.valueOf(str));
		paraCaseP.setSysUserId(ParaCasePAction.getCurrentUserName());
		paraCasePService.updateParaCaseP(paraCaseP);
		return "updateParaCaseP";
	}

	/**
	 * 添加产品信息
	 */
	public String saveParaCaseP() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Date date = new Date();// 创建一个时间对象，获取到当前的时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置时间显示格式
		String str = sdf.format(date);// 将当前时间格式化为需要的类型
		paraCaseP.setSysDt(Timestamp.valueOf(str));
		paraCaseP.setSysUserId(ParaCasePAction.getCurrentUserName());
		paraCasePService.saveParaCaseP(paraCaseP);
		HttpSession session = request.getSession(false);
		
		//获取名称，id
		String caseCode = paraCaseP.getCaseCode();
		String caseName=paraCaseP.getCaseName();
		
		//返回给Sussess.jsp
		msg = "活动类型   "+caseName+" 【 id=" + caseCode + "】 ";
		session.setAttribute("msg", msg);
		return "saveParaCaseP";

	}

	/**
	 * 根据条件查询
	 */
	@SuppressWarnings("unchecked")
	public String getByOptionsPCP() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();

		StringBuffer sql=new StringBuffer("select * from para_case_p pcp inner join Store s on pcp.chal_cd=s.Code where 0=0 ");

		this.caseCode=request.getParameter("caseCode");
		if(caseCode!=null&&!caseCode.isEmpty()){
			caseCode=new String(caseCode.trim().getBytes("ISO-8859-1"),"UTF-8");
			sql.append(" and pcp.case_code like '%"+caseCode+"%'");
		}

		this.caseName=request.getParameter("caseName");
		if(caseName!=null&&!caseName.isEmpty()){
			caseName=new String(caseName.trim().getBytes("ISO-8859-1"),"UTF-8");
			sql.append(" and pcp.case_name like '%"+caseName+"%'");
		}
		
		this.chalCd=request.getParameter("chalCd");
		if(chalCd!=null&&!chalCd.isEmpty()){
			chalCd=new String(chalCd.trim().getBytes("ISO-8859-1"),"UTF-8");
			if(!chalCd.equals("渠道/店铺")){
				sql.append(" and pcp.chal_cd like '%"+chalCd+"%'");
			}
		}
		
		this.caseLevel=request.getParameter("caseLevel");
		if(caseLevel!=null&&!caseLevel.isEmpty()){
			caseLevel=new String(caseLevel.trim().getBytes("ISO-8859-1"),"UTF-8");
			if(!caseLevel.equals("活动级别")){
			sql.append(" and pcp.case_level like '%"+caseLevel+"%'");
			}
		}

		this.brde=request.getParameter("brde");
		if(brde!=null&&!brde.isEmpty()){
			brde=new String(brde.trim().getBytes("ISO-8859-1"),"UTF-8");
			if(!brde.equals("活动品牌")){
			sql.append(" and pcp.brde like '%"+brde+"%'");
			}
		}
		
		sql.append(" order by sys_dt desc");
		rows = util.getTotalCount(sql.toString());

		page = rows % pageSize == 0 ? rows / pageSize : rows / pageSize + 1;

		offset = getPageOffset();
		
		List<Object[]> resultSet=util.getPageListBySql(sql.toString(),String.valueOf(offset), String.valueOf(pageSize),new Class[] { ParaCaseP.class, Store.class } );
		fillPcpList(resultSet);
		
		ListStore=storeService.getStoreList();
		
		return "getByOptionsPCP";
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


	private void fillPcpList(List<Object[]> resultSet) {
		paraCasePList.clear();
		for (Object[] r : resultSet) {
			ParaCaseP pcp = (ParaCaseP) r[0];
			pcp.setChalCd((Store) r[1]);
			paraCasePList.add(pcp);
		}
	}

	/**
	 * 判断活动名称是否已存在
	 * 
	 */

	public String getNameBePCP() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			String caseCode = request.getParameter("caseCode");
			String caseName = request.getParameter("caseName");
			flag = paraCasePService.getNameBePCPRe(caseCode, caseName);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "getNameBePCP";
	}

	/**
	 * 判断活动编码是否已存在
	 * 
	 */
	public String getIdBePCP() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			caseCode = request.getParameter("caseCode");
			paraCaseP = paraCasePService.findParaCasePById(caseCode);
			if (null != paraCaseP) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "getIdBePCP";
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
	 * 查询store
	 */
	public String allStorecode(){
		ListStore=storeService.getStoreList();
		return "ListStore";
	}
	
	
	/**
	 * 查询活动表para_dt活动有没有被占用
	 */
	public String getCaseCodeBeParaDt() {
		HttpServletRequest request = ServletActionContext.getRequest();
		caseCode = request.getParameter("caseCode");
		int casecCodeSize=paraCasePService.getParaDtCaseCode(caseCode);		
		if(casecCodeSize>0){
			flag = true;
		}else{
			flag = false;
		}

		return "getCaseCodeBeParaDt";
	}
	/*
	 *上传文件
	 */
	public String execute() throws Exception {
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
		return SUCCESS;
	}
	/**
	 * 导入Excel表格
	 */
	@SuppressWarnings("unused")
	public String intoDB()throws IOException {
		String uploadPath = ServletActionContext.getServletContext()
				.getRealPath("/upload");
		// 基于myFile创建一个文件输入流
		InputStream is = new FileInputStream(myFile);
		// 设置目标文件
		File toFile = new File(uploadPath, this.getMyFileFileName());

		String caseCode = null;
		String caseName= null;
		String  chalCd= null;
		String caseLevel= null;
		Integer preNum= null;
		String brde= null;
		Integer num= null;
		String CType= null;
		String sysUserId= null;
		Timestamp sysDt= null;

		/**
		 * 2007版的读取方法 
		 */	
		int k=0; 
		int flag = 0;   //指示指针所访问的位置 
		if(myFile!=null) {
			try {
				Workbook workbook = WorkbookFactory.create(toFile);
				// Workbook  workbook = new XSSFWorkbook(is);//初始化workbook对象 
				for (int numSheets = 0; numSheets < workbook.getNumberOfSheets(); numSheets++) {  //读取每一个sheet  
					if (null != workbook.getSheetAt(numSheets)) {    
						XSSFSheet aSheet = (XSSFSheet)workbook.getSheetAt(numSheets);//定义Sheet对象 
						for (int rowNumOfSheet = 1; rowNumOfSheet <= aSheet.getLastRowNum(); rowNumOfSheet++) {  
							//进入当前sheet的行的循环   
							if (null != aSheet.getRow(rowNumOfSheet)) { 
								XSSFRow  aRow = aSheet.getRow(rowNumOfSheet);//定义行，并赋值  
								for (int cellNumOfRow = 0; cellNumOfRow <= aRow.getLastCellNum(); cellNumOfRow++){
									//读取rowNumOfSheet值所对应行的数据 
									XSSFCell  xCell = aRow.getCell(cellNumOfRow); //获得行的列数	//获得列值   
									//System.out.println("type="+xCell.getCellType()); 
									if (null != aRow.getCell(cellNumOfRow)){ 	
										// 如果rowNumOfSheet的值为0，则读取表头，判断excel的格式和预定格式是否相符       
										if(rowNumOfSheet == 1){	     
											if(xCell.getCellType() == XSSFCell .CELL_TYPE_STRING){ 
												//一下根据从Excel的各列命名是否符合要求：如下面匹配：活动编码，活动名称，渠道/店铺，活动级别，前向影响时间，品牌 ，产品数量，选款粒度,操作用户,修改时间
												if(cellNumOfRow == 1){	
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("活动编码")){ 
														flag++; 
													}else{ 
														System.out.println("错误：第一行的活动编码不符合约定格式"); 
													} 
												}else if(cellNumOfRow == 1){ 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("活动名称")){ 
														flag++; 
													}else{ 
														System.out.println("错误：第一行的活动名称不符合约定格式"); 
													}         
												}else if(cellNumOfRow == 2){ 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("渠道/店铺")){ 
														flag++;      
													}else{ 
														System.out.println("错误：第一行的渠道/店铺不符合约定格式"); 
													} 
												}else if (cellNumOfRow == 3) { 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("活动级别")){ 
														flag++; 
													}else{ 
														System.out.println("错误：第一行的活动级别不符合约定格式"); 
													} 
												}else if (cellNumOfRow == 4){ 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("前向影响时间")){ 
														flag++; 
													}else{ 
														System.out.println("第一行的前向影响时间不符合约定格式"); 
													} 
												}else if (cellNumOfRow == 5){ 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("品牌")){ 
														flag++; 
													}else{ 
														System.out.println("第一行的品牌不符合约定格式"); 
													}  
												}else if (cellNumOfRow == 6){ 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("产品数量")){ 
														flag++; 
													}else{ 
														System.out.println("第一行的产品数量不符合约定格式"); 
													} 
												}else if (cellNumOfRow == 7){ 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("选款粒度")){ 
														flag++; 
													}else{ 
														System.out.println("第一行的选款粒度不符合约定格式"); 
													} 
												}else if (cellNumOfRow == 8){ 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("修改时间")){ 
														flag++; 
													}else{ 
														System.out.println("第一行的操作用户不符合约定格式"); 
													} 
												}else if (cellNumOfRow == 9){ 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("操作用户")){ 
														flag++; 
													}else{ 
														System.out.println("第一行的修改时间不符合约定格式"); 
													} 
												}
											}
										}else {																												
											//rowNumOfSheet != 1 即开始打印内容 
											//获取excel中每列的值，并赋予相应的变量，如下的赋值的ID，name,sex, Dormitory,sept; 
											if(xCell.getCellType() == XSSFCell .CELL_TYPE_NUMERIC){	//为数值型	
												System.out.println("===============进入XSSFCell .CELL_TYPE_NUMERIC模块============");
												if(cellNumOfRow == 2){	
													chalCd = String.valueOf(Math.round(xCell.getNumericCellValue()));
													if(chalCd == null){ 
														System.out.println("错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的渠道/店铺不能为空"); 
													}	 
												}else if (cellNumOfRow == 4){
													Integer d = (int) xCell.getNumericCellValue();
													preNum = d;
													if(preNum ==null){ 
														System.out.println("错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的前向影响时间不能为空");
													}
												}else if (cellNumOfRow == 6){	
													num = (int)xCell.getNumericCellValue(); 
													if(num==null){ 
														System.out.println("错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的产品数量不能为空");
													}
												}else if (cellNumOfRow == 8){	
													Date d = (Date) xCell.getDateCellValue();    //对日期处理  
													Timestamp startTime = new Timestamp(d.getTime());
													sysDt= startTime;
													if(sysDt == null){	                     
														System.out.println("错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的修改时间不能为空"); 
													} 
												}                
											}else if(xCell.getCellType() == XSSFCell .CELL_TYPE_STRING){  //为字符串型  
												System.out.println("===============进入XSSFCell .CELL_TYPE_STRING模块============"); 
												if(cellNumOfRow == 0){ 
													caseCode = xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim(); 
													if(caseCode == null){ 
														System.out.println("错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的活动编号不能为空"); 
													} 
												}else if(cellNumOfRow == 1){	
													caseName =xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
													if(caseName == null){ 
														System.out.println("错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的活动名称不能为空"); 
													} 
												}else if(cellNumOfRow == 2){	
													chalCd =xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
													if(chalCd == null){ 
														System.out.println("错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的渠道/店铺不能为空"); 
													}	                            
												}else if (cellNumOfRow == 3){	
													caseLevel =xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
													if(caseLevel == null){ 
														System.out.println("错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的活动级别不能为空");
													}
												}else if (cellNumOfRow == 5){	
													brde = xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim(); 
													if(brde == null){ 
														System.out.println("错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的品牌不能为空"); 
													}
												}else if (cellNumOfRow == 7){	
													CType =xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim(); 
													if(CType == null){ 
														System.out.println("错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的选款粒度不能为空"); 
													}
												}else if (cellNumOfRow == 9){	
													sysUserId = xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim(); 
													if(sysUserId == null){	                     
														System.out.println("错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的修改时间不能为空"); 
													}   
												}
											}else if (xCell.getCellType() == XSSFCell .CELL_TYPE_BLANK) { 
												System.out.println("提示：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的值为空，请查看核对是否符合约定要求"); 
											} 
										} 
									}	  
								} 
								if (flag!=10){ 
									System.out.println("请核对后重试"); 
								} 

								// 判断各个元素被赋值是否为空，如果不为空就放入到stuList，如果放入数据库，就直接使用数据的插入的函数就可以了。
								if(caseCode != null && caseName != null && chalCd != null && caseLevel != null && preNum != null&& brde != null&& num != null&& CType != null&& sysUserId != null&& sysDt != null ){ 
									ParaCaseP pcp=new ParaCaseP();
									pcp.setCaseCode(caseCode);
									pcp.setCaseName(caseName);
									pcp.setChalCd(new Store());
									pcp.getChalCd().setCode(chalCd);
									pcp.setCaseLevel(caseLevel);
									pcp.setPreNum(preNum);
									pcp.setBrde(brde);
									pcp.setCType(CType);
									pcp.setNum(num);
									pcp.setSysUserId(sysUserId);
									pcp.setSysDt(sysDt);
									pcpList.add(pcp);

									k++; 
								} 
							} //获得一行，即读取每一行   
						}   
						//读取每一个sheet 
					}
				} 
				if(pcpList.size()>0){
					for (int i = 0; i < pcpList.size(); i++) {
						paraCasePService.addOneBoat(pcpList.get(i));
					}
				}
			}catch (Exception e) {                 
				e.printStackTrace(); 
			}
		} 

		return "intoDB"; 
	}

	/**
	 * 下载所有数据excel表格
	 */
	public String getexport() throws Exception {

		paraCasePList = paraCasePService.allParaCaseP();
		/*
		 * 设置表头：对Excel每列取名(必须根据你取的数据编写)
		 */
		String[] tableHeader = { "活动编码", "活动名称", "渠道/店铺", "活动级别", "前向影响时间",
				"品牌", "缺省数量", "选款粒度", "修改时间", "操作用户" };
		/**
		 * 设置表头的宽度
		 */
		int[] headerWidths = new int[tableHeader.length];
		for(int i=0; i < tableHeader.length; i++){
		    headerWidths[i] = tableHeader[i].length()*2;
		}
		/*
		 * 下面的都可以拷贝不用编写
		 */
		short cellNumber = (short) tableHeader.length;// 表的列数
		XSSFWorkbook workbook = new XSSFWorkbook(); // 创建一个excel
		XSSFCell cell = null; // Excel的列
		XSSFRow row = null; // Excel的行
		XSSFCellStyle style = workbook.createCellStyle(); // 设置表头的类型
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		XSSFCellStyle style1 = workbook.createCellStyle(); // 设置数据类型
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		XSSFFont font = workbook.createFont(); // 设置字体
		XSSFSheet sheet = workbook.createSheet("sheet1"); // 创建一个sheet
		Header header = sheet.getHeader();// 设置sheet的头
		
		try {
			/**
			 * 设置标题样式
			 */
			
			    // 设置字体  
		    	XSSFFont headfont = workbook.createFont();  
		    	headfont.setFontName("黑体");  
		    	headfont.setFontHeightInPoints((short) 22);// 字体大小  
		    	headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		    	
		    	//设置行
		    	XSSFCellStyle headstyle = workbook.createCellStyle();  
		    	headstyle.setFont(headfont);  
		    	headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中  
		    	headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中  
		    	headstyle.setLocked(true);  
		    	headstyle.setWrapText(true);// 自动换行 
				
		    	// 创建第一行  
				XSSFRow row0 = sheet.createRow(0);
				// 设置行高  
		        row0.setHeight((short) 900);  
		        // 创建第一列  
		        XSSFCell cell0 = row0.createCell(0);  
		        cell0.setCellValue("营销活动类型管理表");  
		        cell0.setCellStyle(headstyle);  
		     
		        /** 
		         * 合并单元格 
		         */  
		        CellRangeAddress range = new CellRangeAddress(0, 0, 0, cellNumber);  
		        sheet.addMergedRegion(range);  
			
			
			/**
			 * 根据是否取出数据，设置header信息
			 * 
			 */
			if (paraCasePList.size() < 1) {
				header.setCenter("查无资料");
			} else {
				header.setCenter("营销活动类型表");
				row = sheet.createRow(1);
				row.setHeight((short) 400);
				for (int k = 0; k < cellNumber; k++) {
					cell = row.createCell(k);// 创建第0行第k列
					cell.setCellValue(tableHeader[k]);// 设置第0行第k列的值
					sheet.setColumnWidth(k, headerWidths[k]*256);// 设置列的宽度
					font.setColor(HSSFFont.COLOR_NORMAL); // 设置单元格字体的颜色.
					font.setFontHeightInPoints((short) 10);// 设置字体大小
					style1.setFont(font);// 设置字体风格
					style.setFont(font);// 设置字体风格
					cell.setCellStyle(style1);
				}
				
				
				/*
				 * 给excel填充数据这里需要编写
				 */
				for (int i = 0; i < paraCasePList.size(); i++) {
					ParaCaseP paraCaseP = (ParaCaseP) paraCasePList.get(i);// 获取student对象
					row = sheet.createRow((short) (i + 2));// 创建第i+1行
					row.setHeight((short) 400);// 设置行高

					if (paraCaseP.getCaseCode() != null) {
						cell = row.createCell(0);// 创建第i+1行第0列
						cell.setCellValue(paraCaseP.getCaseCode());// 设置第i+1行第0列的值
						cell.setCellStyle(style);// 设置风格
					}
					if (paraCaseP.getCaseName() != null) {
						cell = row.createCell(1); // 创建第i+1行第1列
						cell.setCellValue(paraCaseP.getCaseName());// 设置第i+1行第1列的值
						cell.setCellStyle(style); // 设置风格
					}
					if (paraCaseP.getChalCd().getCode() != null) {
						cell = row.createCell(2); // 创建第i+1行第2列
						String aaa=paraCaseP.getChalCd().getCode();
						System.out.println(aaa);
						cell.setCellValue(aaa);// 设置第i+1行第2列的值
						cell.setCellStyle(style); // 设置风格
					}
					if (paraCaseP.getCaseLevel() != null) {
						cell = row.createCell(3); // 创建第i+1行第3列
						cell.setCellValue(paraCaseP.getCaseLevel());// 设置第i+1行第3列的值
						cell.setCellStyle(style); // 设置风格
					}
					if (paraCaseP.getPreNum() != null) {
						cell = row.createCell(4); // 创建第i+1行第4列
						cell.setCellValue(paraCaseP.getPreNum());// 设置第i+1行第4列的值
						cell.setCellStyle(style); // 设置风格
					}
					if (paraCaseP.getBrde() != null) {
						cell = row.createCell(5); // 创建第i+1行第5列
						cell.setCellValue(paraCaseP.getBrde());// 设置第i+1行第5列的值
						cell.setCellStyle(style); // 设置风格
					}
					if (paraCaseP.getNum() != null) {
						cell = row.createCell(6); // 创建第i+1行第7列
						cell.setCellValue(paraCaseP.getNum());// 设置第i+1行第7列的值
						cell.setCellStyle(style); // 设置风格
					}
					if (paraCaseP.getCType() != null) {
						cell = row.createCell(7); // 创建第i+1行第6列
						cell.setCellValue(paraCaseP.getCType());// 设置第i+1行第6列的值
						cell.setCellStyle(style); // 设置风格
					}
					if (paraCaseP.getSysDt() != null) {
						cell = row.createCell(8); // 创建第i+1行第8列
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式
						String time =df.format(paraCaseP.getSysDt());
						cell.setCellValue(time);// 设置第i+1行第8列的值
						cell.setCellStyle(style); // 设置风格
					}
					if (paraCaseP.getSysUserId() != null) {
						cell = row.createCell(9); // 创建第i+1行第9列
						cell.setCellValue(paraCaseP.getSysUserId());// 设置第i+1行第9列的值
						cell.setCellStyle(style); // 设置风格
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * 下面的可以不用编写，直接拷贝
		 */
		HttpServletResponse response = null;// 创建一个HttpServletResponse对象
		OutputStream out = null;// 创建一个输出流对象
		try {
			response = ServletActionContext.getResponse();// 初始化HttpServletResponse对象
			out = response.getOutputStream();//
			response.setHeader("Content-disposition", "attachment; filename="
					+"paraCaseP.xlsx");// filename是下载的xls的名，建议最好用英文
			response.setContentType("application/msexcel;charset=UTF-8");// 设置类型
			response.setHeader("Pragma", "No-cache");// 设置头
			response.setHeader("Cache-Control", "no-cache");// 设置头
			response.setDateHeader("Expires", 0);// 设置日期头
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
	

	private void SimpleDateFormat(String string) {
		// TODO Auto-generated method stub
		
	}
	

	
	/**
	 * 下载导入的模板
	 */
	public String importTemplate() throws Exception {
		/*
		 * 设置表头：对Excel每列取名(必须根据你取的数据编写)
		 */
		String[] tableHeader = { "活动编码", "活动名称", "渠道/店铺", "活动级别", "前向影响时间",
				"品牌", "缺省数量", "选款粒度" };
		util.getTemplate(tableHeader,"营销活动类型");
		return null;
	}
	
}
