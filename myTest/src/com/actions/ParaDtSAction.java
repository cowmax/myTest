package com.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.bean.BProductP;
import com.bean.ParaCaseP;
import com.bean.ParaDt;
import com.bean.ParaDtS;
import com.bean.ParaDtSSku;
import com.bean.RefactorParaDt;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BProductPService;
import com.service.ParaCasePService;
import com.service.ParaDtSService;
import com.service.ParaDtSSkuService;
import com.service.ParaDtService;
import com.serviceimpl.UtilSupport;

@SuppressWarnings("serial")
public class ParaDtSAction extends ActionSupport {

	private ParaDtS pds;
	private ParaDt pd;
	private RefactorParaDt refParaDtsPd;	//选款活动
	private ParaCaseP pcp ;
	private List<ParaDtS> paraDtsList;
	private List<ParaDt> allParaDtList; //所有活动类型名称
	private List<ParaDt> paraDtList;
	private List<BProductP> loadBPList;	//产品信息集合
	private Map<String, String> colorMap;	//产品对应颜色Map集合
	private String chooseCountMsg;			//选款结果条数显示

	private ParaDtService paraDtService;
	private ParaDtSService paraDtSBiz;
	private UtilSupport util;
	private BProductPService bProductPService;
	private ParaCasePService paraCasePService;
	private boolean flag;
	
	private String impflag;

	private int offset;			//当前页
	private int pageSize=10;
	private int totalcount;		// 总记录数
	private int totalpage; 		// 总页数

	//查询条件字段
	private String productCd;	//产品编码
	private Integer caseId;		//活动ID
	private String caseName;
	private Integer caseStatus;
	private String sena; 		//季节
	private String spno;   		//产品定位
	private Timestamp jhdt;  	//上架时间
	private Timestamp xjdt;		//下架时间
	private String brde;		//品牌

	private String avgAmt;      //产品的平均销量

	private List<ParaDtSSku> allParaDtSSkuList; //获取所有产品SKU明细
	private ParaDtSSkuService paraDtSSkuService;//产品SKU明细的实现类
	private List spnoList ;//所有产品定位

	// myFile属性用来封装上传的文件
	private File myFile;
	// myFileContentType属性用来封装上传文件的类型
	private String myFileContentType;
	// myFileFileName属性用来封装上传文件的文件名
	private String myFileFileName;

	private List<ParaDtS> TempList;
	private Integer status;
	
	private String refreshList;
	private String titleName;
	private String msg;
	private boolean impSuccess;
	private boolean setSuccess;
	private int errCount;

	public ParaDtSAction() {
		loadBPList =new ArrayList<BProductP>();
		paraDtsList = new ArrayList<ParaDtS>();
		allParaDtList = new ArrayList<ParaDt>();
		allParaDtSSkuList= new ArrayList<ParaDtSSku>();
		TempList = new ArrayList();
		spnoList=new ArrayList();
	}

	public String getProductCd() {
		return productCd;
	}

	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}

	public ParaDt getPd() {
		return pd;
	}

	public void setPd(ParaDt pd) {
		this.pd = pd;
	}

	public RefactorParaDt getRefParaDtsPd() {
		return refParaDtsPd;
	}

	public void setRefParaDtsPd(RefactorParaDt refParaDtsPd) {
		this.refParaDtsPd = refParaDtsPd;
	}

	public List<ParaDt> getAllParaDtList() {
		return allParaDtList;
	}

	public ParaDtS getPds() {
		return pds;
	}

	public void setPds(ParaDtS pds) {
		this.pds = pds;
	}

	public ParaCaseP getPcp() {
		return pcp;
	}

	public void setPcp(ParaCaseP pcp) {
		this.pcp = pcp;
	}

	public List<ParaDtS> getParaDtsList() {
		return paraDtsList;
	}

	public void setParaDtsList(List<ParaDtS> paraDtsList) {
		this.paraDtsList = paraDtsList;
	}

	public void setAllParaDtList(List<ParaDt> allParaDtList) {
		this.allParaDtList = allParaDtList;
	}

	public List<ParaDt> getParaDtList() {
		return paraDtList;
	}

	public void setParaDtList(List<ParaDt> paraDtList) {
		this.paraDtList = paraDtList;
	}

	public List<BProductP> getLoadBPList() {
		return loadBPList;
	}

	public void setLoadBPList(List<BProductP> loadBPList) {
		this.loadBPList = loadBPList;
	}

	public ParaDtService getParaDtService() {
		return paraDtService;
	}

	public Map<String, String> getColorMap() {
		return colorMap;
	}

	public void setColorMap(Map<String, String> colorMap) {
		this.colorMap = colorMap;
	}

	public String getChooseCountMsg() {
		return chooseCountMsg;
	}

	public void setChooseCountMsg(String chooseCountMsg) {
		this.chooseCountMsg = chooseCountMsg;
	}

	public void setParaDtService(ParaDtService paraDtService) {
		this.paraDtService = paraDtService;
	}

	public ParaDtSService getParaDtSBiz() {
		return paraDtSBiz;
	}

	public void setParaDtSBiz(ParaDtSService paraDtSBiz) {
		this.paraDtSBiz = paraDtSBiz;
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

	public BProductPService getbProductPService() {
		return bProductPService;
	}

	public void setbProductPService(BProductPService bProductPService) {
		this.bProductPService = bProductPService;
	}

	public ParaCasePService getParaCasePService() {
		return paraCasePService;
	}

	public void setParaCasePService(ParaCasePService paraCasePService) {
		this.paraCasePService = paraCasePService;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public Integer getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(Integer caseStatus) {
		this.caseStatus = caseStatus;
	}

	public String getSena() {
		return sena;
	}

	public void setSena(String sena) {
		this.sena = sena;
	}

	public String getSpno() {
		return spno;
	}

	public void setSpno(String spno) {
		this.spno = spno;
	}

	public Timestamp getJhdt() {
		return jhdt;
	}

	public void setJhdt(Timestamp jhdt) {
		this.jhdt = jhdt;
	}

	public Timestamp getXjdt() {
		return xjdt;
	}

	public void setXjdt(Timestamp xjdt) {
		this.xjdt = xjdt;
	}

	public List<ParaDtSSku> getAllParaDtSSkuList() {
		return allParaDtSSkuList;
	}

	public void setAllParaDtSSkuList(List<ParaDtSSku> allParaDtSSkuList) {
		this.allParaDtSSkuList = allParaDtSSkuList;
	}

	public ParaDtSSkuService getParaDtSSkuService() {
		return paraDtSSkuService;
	}

	public void setParaDtSSkuService(ParaDtSSkuService paraDtSSkuService) {
		this.paraDtSSkuService = paraDtSSkuService;
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

	public List<ParaDtS> getTempList() {
		return TempList;
	}

	public void setTempList(List<ParaDtS> tempList) {
		TempList = tempList;
	}

	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}

	public String getBrde() {
		return brde;
	}

	public void setBrde(String brde) {
		this.brde = brde;
	}

	public String getAvgAmt() {
		return avgAmt;
	}

	public void setAvgAmt(String avgAmt) {
		this.avgAmt = avgAmt;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getImpflag() {
		return impflag;
	}

	public void setImpflag(String impflag) {
		this.impflag = impflag;
	}

	public List getSpnoList() {
		return spnoList;
	}

	public void setSpnoList(List spnoList) {
		this.spnoList = spnoList;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isImpSuccess() {
		return impSuccess;
	}

	public void setImpSuccess(boolean impSuccess) {
		this.impSuccess = impSuccess;
	}

	public boolean isSetSuccess() {
		return setSuccess;
	}

	public void setSetSuccess(boolean setSuccess) {
		this.setSuccess = setSuccess;
	}

	public int getErrCount() {
		return errCount;
	}

	public void setErrCount(int errCount) {
		this.errCount = errCount;
	}

	// 填充 PGroupUser 对像 List
	private void fillPdtsList(List<Object[]> resultSet) {
		paraDtsList.clear();

		for (Object[] r : resultSet) {

			ParaDtS pds = (ParaDtS)r[0];

			pd = (ParaDt)r[1];
			BProductP bpp = (BProductP)r[2];
			pcp=(ParaCaseP)r[3];
			pds.setProductCd(bpp);

			paraDtsList.add(pds);
		}
	}
	/**
	 * 分页显示选款结果
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void getList() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		
		//获取所有产品定位
		spnoList=bProductPService.allSpno();
		
		if(allParaDtList==null){
			allParaDtList = paraDtSBiz.getAllParaDtList();
		}
		String ofst = request.getParameter("offset");
		if(ofst!=null){
			offset=Integer.valueOf(ofst);
		}else{
			offset=1;
		}

		StringBuffer sql=new StringBuffer("select * from para_dt_s s " +
				"inner join para_dt d on s.case_id = d.case_id " +
				"inner join b_product_p p on s.product_cd = p.product_code " +
				"inner join para_case_p c on d.case_code = c.case_code " +
				"where ISNULL(s.status,2)!=0 ");

		if(this.caseId==null){
			String cid = request.getParameter("caseId");
			if(cid!=null&&!cid.isEmpty()){
				this.caseId = Integer.parseInt(cid);
			}else{
				sql.append(" and s.case_id = "+caseId+"");
			}
		}else{
			sql.append(" and s.case_id = "+caseId+"");
		}

		refParaDtsPd = paraDtService.getRpdByCaseId(caseId);
		if(this.caseName==null){
			this.caseName=request.getParameter("caseName");
			if(caseName!=null&&!caseName.isEmpty()){
				caseName=new String(caseName.trim().getBytes("ISO-8859-1"),"UTF-8");
			}
		}else{
			boolean isMessyCode = util.isMessyCode(caseName);
			if(isMessyCode){  
	            try {  
	            	caseName =  new String(caseName.getBytes("ISO8859-1"), "UTF-8");  
	            } catch (Exception e) {  
	            }  
	        }  
		}
		
		if(this.caseStatus==null){
			String cstatus=request.getParameter("caseStatus");
			if(cstatus!=null&&!cstatus.isEmpty()){
				this.caseStatus = Integer.parseInt(cstatus);
			}
		}

		this.productCd = request.getParameter("productCd");
		if(productCd!=null&&!productCd.isEmpty()){
			productCd=new String(productCd.trim().getBytes("ISO-8859-1"),"UTF-8");
			sql.append(" and s.product_cd like '%"+productCd+"%'");
		}

		this.sena = request.getParameter("sena");
		if(sena!=null&&!sena.isEmpty()){
			sena=new String(sena.trim().getBytes("ISO-8859-1"),"UTF-8");
			sql.append(" and p.sena = '"+sena+"'");
		}

		this.spno = request.getParameter("spno");
		if(spno!=null&&!spno.isEmpty()){
			spno=new String(spno.trim().getBytes("ISO-8859-1"),"UTF-8");
			sql.append(" and p.spno = '"+spno+"'");
		}

		String jhdate=request.getParameter("jhdt");
		String xjdate=request.getParameter("xjdt");
		Calendar cal = Calendar.getInstance();

		if(jhdate!=null&&!jhdate.isEmpty()){
			this.jhdt=Timestamp.valueOf(jhdate);
			if(xjdate!=null&&!xjdate.isEmpty()){
				this.xjdt=Timestamp.valueOf(xjdate);
			}else{
				cal.setTime(jhdt);
				int day =  cal.get(Calendar.DATE); 
				int month =cal.get(Calendar.MONTH); 
				int year = cal.get(Calendar.YEAR) ;

				cal.set(year+5, month, day);
				year = cal.get(Calendar.YEAR) ;
				this.xjdt=new Timestamp(cal.getTimeInMillis());
			}
			sql.append(" and ((p.jhdt >= '"+jhdt+"' and p.xjdt <= '"+xjdt+"') " +
					" or( p.jhdt >= '"+jhdt+"' and ('"+xjdt+"' between p.jhdt and p.xjdt))" +
					" or(('"+jhdt+"' between p.jhdt and p.xjdt ) and ('"+xjdt+"' between p.jhdt and p.xjdt))" +
					" or(('"+jhdt+"' between p.jhdt and p.xjdt ) and p.xjdt <= '"+xjdt+"'))");
		}else{
			if(xjdate!=null&&!xjdate.isEmpty()){
				this.xjdt=Timestamp.valueOf(xjdate);

				cal.setTime(xjdt);
				int day =  cal.get(Calendar.DATE); 
				int month =cal.get(Calendar.MONTH); 
				int year = cal.get(Calendar.YEAR) ;

				cal.set(year-5, month, day);
				this.jhdt=new Timestamp(cal.getTimeInMillis());

				sql.append(" and ((p.jhdt >= '"+jhdt+"' and p.xjdt <= '"+xjdt+"') " +
						" or( p.jhdt >= '"+jhdt+"' and ('"+xjdt+"' between p.jhdt and p.xjdt))" +
						" or(('"+jhdt+"' between p.jhdt and p.xjdt ) and ('"+xjdt+"' between p.jhdt and p.xjdt))" +
						" or(('"+jhdt+"' between p.jhdt and p.xjdt ) and p.xjdt <= '"+xjdt+"'))");
			}
		}


		totalcount = util.getTotalCount(sql.toString());

		totalpage = totalcount % pageSize == 0 ? totalcount / pageSize
				: totalcount / pageSize + 1;

		offset = getPageOffset();

		List<Object[]> resultSet = util.getPageListBySql(sql.toString(),String.valueOf(offset),String.valueOf(pageSize),new Class[]{ParaDtS.class,ParaDt.class,BProductP.class,ParaCaseP.class});
		fillPdtsList(resultSet);
	}

	public String getParaDtSList() throws Exception{
		this.getList();
		chooseCountMsg=this.getPrdtSummaryByCaseId(caseId);
		return "show";
	}

	/**
	 * 活动审核的选款详情
	 */
	public String getPcaPdsList() throws Exception{
		this.getList();
		return "pcaPdsList";
	}
	
	// Added by JSL : 获取翻页偏移量(实际上是将要翻到的页面的页索引，页索引从 0 开始)
	private int getPageOffset() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String ofst = request.getParameter("offset");
		int idx = 0;
		if(ofst!=null){
			idx = Integer.valueOf(ofst);
			idx = idx < 0 ? 0 : idx;                        // 超过第一页时，不再翻页
			idx = idx >= totalpage ? (totalpage-1) : idx;	// 超过最后一页时，不再翻页		
		}
		return idx;
	}

	/**
	 * 加载数据
	 * @return
	 */
	public String reload(){
		allParaDtList = paraDtSBiz.getAllParaDtList();
		return "choose";
	}

	/*
	 *上传文件
	 */
	public String uploadFiles() throws Exception {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		impflag=request.getParameter("impflag");
		
		//基于myFile创建一个文件输入流
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
		return "uploadFiles";
	}


	/**
	 * 导入Excel表格
	 */
	@SuppressWarnings("unused")
	public String intoDB()throws IOException {
		String uploadPath = ServletActionContext.getServletContext()
				.getRealPath("/upload");
		impSuccess = false;	//判断数据导入是否成功
		errCount = 0;//判断导入数据模板是否对应
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// 基于myFile创建一个文件输入流
		InputStream is = new FileInputStream(myFile);
		// 设置目标文件
		File toFile = new File(uploadPath, this.getMyFileFileName());

		Integer caseId = null;//活动ID
		String productCd= null;//活动参与选款sku
		Integer status=null;//状态

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
						TempList.clear();
						for (int rowNumOfSheet = 1; rowNumOfSheet <= aSheet
								.getLastRowNum(); rowNumOfSheet++) {
							// 进入当前sheet的行的循环
							if (null != aSheet.getRow(rowNumOfSheet)) {
								XSSFRow aRow = aSheet.getRow(rowNumOfSheet);// 定义行，并赋值
								for (int cellNumOfRow = 0; cellNumOfRow <= aRow
										.getLastCellNum(); cellNumOfRow++) {
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
												 * 活动ID，活动描述，活动开始时间，
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
															.equals("活动ID")) {
														flag++;
													} else {
														errCount++;
													}
												} else if (cellNumOfRow == 1) {
													if (xCell
															.getStringCellValue()
															.replace('\t', ' ')
															.replace('\n', ' ')
															.replace('\r', ' ')
															.trim()
															.equals("活动参与产品SKU")) {
														flag++;
													} else {
														errCount++;
													}
												} else if (cellNumOfRow == 2) {
													if (xCell
															.getStringCellValue()
															.replace('\t', ' ')
															.replace('\n', ' ')
															.replace('\r', ' ')
															.trim()
															.equals("活动状态")) {
														flag++;
													} else {
														errCount++;
													}
												}
											}
										} else {
											// rowNumOfSheet != 0 即开始打印内容
											// 获取excel中每列的值，并赋予相应的变量，如下的赋值的ID，name,sex,
											// Dormitory,sept;
											if (xCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) { // 为数值型
												// System.out.println("===============进入XSSFCell .CELL_TYPE_NUMERIC模块============");
												switch (cellNumOfRow) {
												case 0:
													caseId = (int) xCell
													.getNumericCellValue();
													; // 对日期处理
													break;
												case 2:
													status = (int) xCell
													.getNumericCellValue();
													; // 对日期处理
													break;
												}
											} else if (xCell.getCellType() == XSSFCell.CELL_TYPE_STRING) { // 为字符串型
												// System.out.println("===============进入XSSFCell .CELL_TYPE_STRING模块============");
												switch (cellNumOfRow) {
												case 1:
													productCd = xCell
													.getStringCellValue()
													.replace('\t', ' ')
													.replace('\n', ' ')
													.replace('\r', ' ')
													.trim();
													break;

												}
											} else if (xCell.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
												// System.out.println("提示：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的值为空，请查看核对是否符合约定要求");
												switch (cellNumOfRow) {
												case 0:
													caseId = null;
													break;
												case 1:
													productCd = "";
													break;
												case 2:
													status = null;
													break;
												}
											}
										}
									}
								}
								// 判断各个元素被赋值,如果放入数据库，就直接使用数据的插入的函数就可以了。
								if (aRow.getRowNum() > 1) {
									ParaDtS PdS = new ParaDtS();
									PdS.setCaseId(caseId);
									BProductP bp = new BProductP();
									bp.setProductCode(productCd);
									PdS.setProductCd(bp);

									TempList.add(PdS);

								}
							} // 获得一行，即读取每一行
						}
						// 读取每一个sheet
					}
				}
				if(errCount>0){
					msg = "导入数据与模板不符，导入失败！";
				}else if (TempList.size() > 0) {
					int imp_flag = Integer.valueOf(impflag);
					String name = ParaCasePAction.getCurrentUserName();
					impSuccess = paraDtSBiz.addOneBoat(TempList, 500,caseId,imp_flag, name);
					if(impSuccess){
						msg = "活动选款导入成功！";
					}else{
						msg = "活动选款导入失败！";
					}
				}
				
				refreshList = "paraCaseSgetParaDtSList";
				titleName = "营销活动选款";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "importExcel"; 
	}

	// 填充 PGroupUser 对像 List
	private void fillLoadSku(List<Object[]> resultSet) {
		allParaDtSSkuList.clear();

		for (Object[] r : resultSet) {
			ParaDtSSku pdsSku = (ParaDtSSku) r[0];
			BProductP bpp = (BProductP) r[1];
			pdsSku.setProductCode(bpp);

			allParaDtSSkuList.add(pdsSku);
		}
	}
	/**
	 * 导出产品SKU
	 */
	@SuppressWarnings("unchecked")
	public String getparaDtSSkuexport() throws Exception {
		List<Object[]> resultSet = paraDtSSkuService.getCaseIdParaDtSSku(caseId);
		fillLoadSku(resultSet);
		/*
		 * 设置表头：对Excel每列取名(必须根据你取的数据编写)
		 */
		String[] tableHeader = { "活动ID", "产品编码", "SKU编码","颜色编号", "颜色名称", "季节",
				"产品定位", "类目","子类目","上架时间","下架时间","计划销量","最小起做量","生产周期",
				"零售价", "状态","产品销量","可用库存", "已参与活动"};

		/**
		 * 设置表头的宽度
		 */
		int[] headerWidths = new int[tableHeader.length];
		for (int i = 0; i < tableHeader.length; i++) {
			headerWidths[i] = tableHeader[i].length() * 2;
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

			// 设置行
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
			cell0.setCellValue("营销活动选[SKU]明细表");
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
			if (allParaDtSSkuList.size() < 1) {
				header.setCenter("查无资料");
			} else {
				header.setCenter("产品SKU表");
				row = sheet.createRow(1);
				row.setHeight((short) 400);
				for (int k = 0; k < cellNumber; k++) {
					cell = row.createCell(k);// 创建第0行第k列
					cell.setCellValue(tableHeader[k]);// 设置第0行第k列的值
					sheet.setColumnWidth(k, headerWidths[k] * 256);// 设置列的宽度
					font.setColor(HSSFFont.COLOR_NORMAL); // 设置单元格字体的颜色.
					font.setFontHeightInPoints((short) 10);// 设置字体大小
					style1.setFont(font);// 设置字体风格
					style.setFont(font);// 设置字体风格
					cell.setCellStyle(style1);
				}

				/*
				 * 给excel填充数据这里需要编写
				 */
				for (int i = 1; i <allParaDtSSkuList.size()+1; i++) {
					ParaDtSSku paraDtSSku = (ParaDtSSku) allParaDtSSkuList
							.get(i-1);// 获取ParaDtSSku对象
					row = sheet.createRow((short) (i + 1));// 创建第i+1行
					row.setHeight((short) 400);// 设置行高

					if (paraDtSSku.getCaseId() != null) {
						cell = row.createCell(0);// 创建第i+1行第0列
						cell.setCellValue(paraDtSSku.getCaseId());// 设置第i+1行第0列的值
						cell.setCellStyle(style);// 设置风格
					}
					if (paraDtSSku.getProductCode().getProductCode() != null) {
						cell = row.createCell(1); // 创建第i+1行第1列
						cell.setCellValue(paraDtSSku.getProductCode()
								.getProductCode());// 设置第i+1行第1列的值
						cell.setCellStyle(style); // 设置风格
					}
					
					if (paraDtSSku.getSkuCode() != null) {
						cell = row.createCell(2); // 创建第i+1行第1列
						cell.setCellValue(paraDtSSku.getSkuCode());// 设置第i+1行第1列的值
						cell.setCellStyle(style); // 设置风格
					}
					
					if (paraDtSSku.getColo() != null) {
						cell = row.createCell(3); // 创建第i+1行第7列
						cell.setCellValue(paraDtSSku.getColo());// 设置第i+1行第7列的值
						cell.setCellStyle(style); // 设置风格
					}
					if (paraDtSSku.getCona() != null) {
						cell = row.createCell(4); // 创建第i+1行第8列
						cell.setCellValue(paraDtSSku.getCona());// 设置第i+1行第8列的值
						cell.setCellStyle(style); // 设置风格
					}

					//季节
					if (paraDtSSku.getProductCode().getSena() != null) {
						cell = row.createCell(5); // 创建第i+1行第2列
						cell.setCellValue(paraDtSSku.getProductCode().getSena());// 设置第i+1行第2列的值
						cell.setCellStyle(style); // 设置风格
					}
					//产品定位

					if (paraDtSSku.getProductCode().getSpno() != null) {
						cell = row.createCell(6); // 创建第i+1行第2列
						cell.setCellValue(paraDtSSku.getProductCode().getSpno());// 设置第i+1行第2列的值
						cell.setCellStyle(style); // 设置风格
					}
					//类目
					if (paraDtSSku.getProductCode().getTwpr() != null) {
						cell = row.createCell(7); // 创建第i+1行第2列
						cell.setCellValue(paraDtSSku.getProductCode().getTwpr());// 设置第i+1行第2列的值
						cell.setCellStyle(style); // 设置风格
					}
					
					//子类目
					if (paraDtSSku.getProductCode().getTyna() != null) {
						cell = row.createCell(8); // 创建第i+1行第2列
						cell.setCellValue(paraDtSSku.getProductCode().getTyna());// 设置第i+1行第2列的值
						cell.setCellStyle(style); // 设置风格
					}
				     //上架时间
					if (paraDtSSku.getProductCode().getJhdt() != null) {
						cell = row.createCell(9); // 创建第i+1行第2列
						cell.setCellValue(paraDtSSku.getProductCode().getJhdt());// 设置第i+1行第2列的值
						cell.setCellStyle(style); // 设置风格
					}
				     //下架时间
					if (paraDtSSku.getProductCode().getXjdt() != null) {
						cell = row.createCell(10); // 创建第i+1行第2列
						cell.setCellValue(paraDtSSku.getProductCode().getXjdt());// 设置第i+1行第2列的值
						cell.setCellStyle(style); // 设置风格
					}
				     //计划销量
					if (paraDtSSku.getProductCode().getPlanQty() != null) {
						cell = row.createCell(11); // 创建第i+1行第2列
						cell.setCellValue(paraDtSSku.getProductCode().getPlanQty());// 设置第i+1行第2列的值
						cell.setCellStyle(style); // 设置风格
					}
				     //最小起做量
					if (paraDtSSku.getProductCode().getDoNum() != null) {
						cell = row.createCell(12); // 创建第i+1行第2列
						cell.setCellValue(paraDtSSku.getProductCode().getDoNum());// 设置第i+1行第2列的值
						cell.setCellStyle(style); // 设置风格
					}
				     //生产周期
					if (paraDtSSku.getProductCode().getProdCycle() != null) {
						cell = row.createCell(13); // 创建第i+1行第2列
						cell.setCellValue(paraDtSSku.getProductCode().getProdCycle());// 设置第i+1行第2列的值
						cell.setCellStyle(style); // 设置风格
					}
				     //零售价
					if (paraDtSSku.getProductCode().getLspr() != null) {
						cell = row.createCell(14); // 创建第i+1行第2列
						cell.setCellValue(paraDtSSku.getProductCode().getLspr());// 设置第i+1行第2列的值
						cell.setCellStyle(style); // 设置风格
					}
				     //产品款状态
					if (paraDtSSku.getStatus() != null) {
						cell = row.createCell(15); // 创建第i+1行第2列
						cell.setCellValue(paraDtSSku.getStatus());// 设置第i+1行第2列的值
						cell.setCellStyle(style); // 设置风格
					}
				     //预测销量
					if (paraDtSSku.getSalesNum() != null) {
						cell = row.createCell(16); // 创建第i+1行第2列
						cell.setCellValue(paraDtSSku.getSalesNum());// 设置第i+1行第2列的值
						cell.setCellStyle(style); // 设置风格
					}
				     //可用库存
					if (paraDtSSku.getStock() != null) {
						cell = row.createCell(17); // 创建第i+1行第4列
						cell.setCellValue(paraDtSSku.getStock());// 设置第i+1行第4列的值
						cell.setCellStyle(style); // 设置风格
					}
				     //已参与活动
					if (paraDtSSku.getSCaseAll() != null) {
						cell = row.createCell(18); // 创建第i+1行第6列
						cell.setCellValue(paraDtSSku.getSCaseAll());// 设置第i+1行第6列的值
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
					+ "paraSKU.xlsx");// filename是下载的xls的名，建议最好用英文
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

	// 填充 PGroupUser 对像 List
	private void fillLoadPdtsList(List<Object[]> resultSet) {
		paraDtsList.clear();

		for (Object[] r : resultSet) {
			ParaDtS pds = (ParaDtS) r[0];
			BProductP bpp = (BProductP) r[1];
			pds.setProductCd(bpp);

			paraDtsList.add(pds);
		}
	}

	/**
	 * 导出产品款
	 */
	public String getparaDtSexport() throws Exception {
		List<Object[]> resultSet = paraDtSBiz.getCaseIdParaDtS(caseId);
		fillLoadPdtsList(resultSet);
		/*
		 * 设置表头：对Excel每列取名(必须根据你取的数据编写)
		 */
		String[] tableHeader = { "活动ID", "产品编码","颜色编号","颜色名称","季节","产品定位",
				"类目","子类目","上架时间","下架时间","计划销量","最小起做量","生产周期","零售价",
			     "产品款状态", "预测销量","可用库存","已参与活动" };

		/**
		 * 设置表头的宽度
		 */
		int[] headerWidths = new int[tableHeader.length];
		for (int i = 0; i < tableHeader.length; i++) {
			headerWidths[i] = tableHeader[i].length() * 2;
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

			// 设置行
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
			cell0.setCellValue("营销活动选[款]明细表");
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
			if (paraDtsList.size() < 1) {
				header.setCenter("查无资料");
				
			} else {
				header.setCenter("产品款表");
				row = sheet.createRow(1);
				row.setHeight((short) 400);
				for (int k = 0; k < cellNumber; k++) {
					cell = row.createCell(k);// 创建第0行第k列
					cell.setCellValue(tableHeader[k]);// 设置第0行第k列的值
					sheet.setColumnWidth(k, headerWidths[k] * 256);// 设置列的宽度
					font.setColor(HSSFFont.COLOR_NORMAL); // 设置单元格字体的颜色.
					font.setFontHeightInPoints((short) 10);// 设置字体大小
					style1.setFont(font);// 设置字体风格
					style.setFont(font);// 设置字体风格
					cell.setCellStyle(style1);
				}

				/*
				 * 给excel填充数据这里需要编写
				 */
				
				
				for (int i = 1; i < paraDtsList.size()+1; i++) {
					ParaDtS paraDtS = (ParaDtS) paraDtsList.get(i-1);// 获取ParaDtSSku对象
					row = sheet.createRow((short) (i + 1));// 创建第i+1行
					row.setHeight((short) 400);// 设置行高
					if (paraDtS.getCaseId() != null) {
						cell = row.createCell(0);// 创建第i+1行第0列
						cell.setCellValue(paraDtS.getCaseId());// 设置第i+1行第0列的值
						cell.setCellStyle(style);// 设置风格
					}
					if (paraDtS.getProductCd().getProductCode() != null) {
						cell = row.createCell(1); // 创建第i+1行第1列
						cell.setCellValue(paraDtS.getProductCd()
								.getProductCode());// 设置第i+1行第1列的值
						cell.setCellStyle(style); // 设置风格
					}
					
					if (paraDtS.getColo() != null) {
						cell = row.createCell(2); // 创建第i+1行第7列
						cell.setCellValue(paraDtS.getColo());// 设置第i+1行第7列的值
						cell.setCellStyle(style); // 设置风格
					}
					if (paraDtS.getCona() != null) {
						cell = row.createCell(3); // 创建第i+1行第8列
						cell.setCellValue(paraDtS.getCona());// 设置第i+1行第8列的值
						cell.setCellStyle(style); // 设置风格
					}

					//季节
					if (paraDtS.getProductCd().getSena() != null) {
						cell = row.createCell(4); // 创建第i+1行第2列
						cell.setCellValue(paraDtS.getProductCd().getSena());// 设置第i+1行第2列的值
						cell.setCellStyle(style); // 设置风格
					}
					//产品定位

					if (paraDtS.getProductCd().getSpno() != null) {
						cell = row.createCell(5); // 创建第i+1行第2列
						cell.setCellValue(paraDtS.getProductCd().getSpno());// 设置第i+1行第2列的值
						cell.setCellStyle(style); // 设置风格
					}
					//类目
					if (paraDtS.getProductCd().getTwpr() != null) {
						cell = row.createCell(6); // 创建第i+1行第2列
						cell.setCellValue(paraDtS.getProductCd().getTwpr());// 设置第i+1行第2列的值
						cell.setCellStyle(style); // 设置风格
					}
					
					//子类目
					if (paraDtS.getProductCd().getTyna() != null) {
						cell = row.createCell(7); // 创建第i+1行第2列
						cell.setCellValue(paraDtS.getProductCd().getTyna());// 设置第i+1行第2列的值
						cell.setCellStyle(style); // 设置风格
					}
				     //上架时间
					if (paraDtS.getProductCd().getJhdt() != null) {
						cell = row.createCell(8); // 创建第i+1行第2列
						cell.setCellValue(paraDtS.getProductCd().getJhdt());// 设置第i+1行第2列的值
						cell.setCellStyle(style); // 设置风格
					}
				     //下架时间
					if (paraDtS.getProductCd().getXjdt() != null) {
						cell = row.createCell(9); // 创建第i+1行第2列
						cell.setCellValue(paraDtS.getProductCd().getXjdt());// 设置第i+1行第2列的值
						cell.setCellStyle(style); // 设置风格
					}
				     //计划销量
					if (paraDtS.getProductCd().getPlanQty() != null) {
						cell = row.createCell(10); // 创建第i+1行第2列
						cell.setCellValue(paraDtS.getProductCd().getPlanQty());// 设置第i+1行第2列的值
						cell.setCellStyle(style); // 设置风格
					}
				     //最小起做量
					if (paraDtS.getProductCd().getDoNum() != null) {
						cell = row.createCell(11); // 创建第i+1行第2列
						cell.setCellValue(paraDtS.getProductCd().getDoNum());// 设置第i+1行第2列的值
						cell.setCellStyle(style); // 设置风格
					}
				     //生产周期
					if (paraDtS.getProductCd().getProdCycle() != null) {
						cell = row.createCell(12); // 创建第i+1行第2列
						cell.setCellValue(paraDtS.getProductCd().getProdCycle());// 设置第i+1行第2列的值
						cell.setCellStyle(style); // 设置风格
					}
				     //零售价
					if (paraDtS.getProductCd().getLspr() != null) {
						cell = row.createCell(13); // 创建第i+1行第2列
						cell.setCellValue(paraDtS.getProductCd().getLspr());// 设置第i+1行第2列的值
						cell.setCellStyle(style); // 设置风格
					}
				     //产品款状态
					if (paraDtS.getStatus() != null) {
						cell = row.createCell(14); // 创建第i+1行第2列
						cell.setCellValue(paraDtS.getStatus());// 设置第i+1行第2列的值
						cell.setCellStyle(style); // 设置风格
					}
				     //预测销量
					if (paraDtS.getAvgAmt() != null) {
						cell = row.createCell(15); // 创建第i+1行第2列
						cell.setCellValue(paraDtS.getAvgAmt());// 设置第i+1行第2列的值
						cell.setCellStyle(style); // 设置风格
					}
				     //可用库存
					if (paraDtS.getStock() != null) {
						cell = row.createCell(16); // 创建第i+1行第4列
						cell.setCellValue(paraDtS.getStock());// 设置第i+1行第4列的值
						cell.setCellStyle(style); // 设置风格
					}
				     //已参与活动
					if (paraDtS.getSCaseAll() != null) {
						cell = row.createCell(17); // 创建第i+1行第6列
						cell.setCellValue(paraDtS.getSCaseAll());// 设置第i+1行第6列的值
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
					+ "bProductPDetail.xlsx");// filename是下载的xls的名，建议最好用英文
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

	/**
	 * 添加活动选款
	 * @return
	 */
	public String caseAddBProductP(){
		ParaDt pd=new ParaDt();

		if(caseId!=null){

			pd=paraDtService.findParaDtById(caseId);
			pds=new ParaDtS();
			pds.setCaseId(pd.getCaseId());
			pds.setStatus(pd.getStatus());
		}

		return "caseAddBProductP";
	}


	/**
	 * 根据活动ID获取可选择的产品信息
	 * @return
	 * @throws Exception
	 */
	public String loadBProductPList() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();

		StringBuffer sql=new StringBuffer("select * from b_product_p where 0=0 ") ;

		this.productCd = request.getParameter("productCd");
		if(productCd!=null&&!productCd.isEmpty()){
			productCd=new String(productCd.trim().getBytes("ISO-8859-1"),"UTF-8");
			sql.append(" and product_code like '%"+productCd+"%'");
		}

		this.brde = request.getParameter("brde");
		if(brde!=null&&!brde.isEmpty()){
			brde=new String(brde.trim().getBytes("ISO-8859-1"),"UTF-8");
			sql.append(" and brde = '"+productCd+"'");
		}

		this.sena = request.getParameter("sena");
		if(sena!=null&&!sena.isEmpty()){
			sena=new String(sena.trim().getBytes("ISO-8859-1"),"UTF-8");
			sql.append(" and sena = '"+sena+"'");
		}

		this.spno = request.getParameter("spno");
		if(spno!=null&&!spno.isEmpty()){
			spno=new String(spno.trim().getBytes("ISO-8859-1"),"UTF-8");
			sql.append(" and spno = '"+spno+"'");
		}

		String jhdate=request.getParameter("jhdt");
		String xjdate=request.getParameter("xjdt");
		Calendar cal = Calendar.getInstance();

		if(jhdate!=null&&!jhdate.isEmpty()){
			this.jhdt=Timestamp.valueOf(jhdate);
			if(xjdate!=null&&!xjdate.isEmpty()){
				this.xjdt=Timestamp.valueOf(xjdate);
			}else{
				cal.setTime(jhdt);
				int day =  cal.get(Calendar.DATE); 
				int month =cal.get(Calendar.MONTH); 
				int year = cal.get(Calendar.YEAR) ;

				cal.set(year+5, month, day);
				year = cal.get(Calendar.YEAR) ;
				this.xjdt=new Timestamp(cal.getTimeInMillis());
			}
			sql.append(" and ((p.jhdt >= '"+jhdt+"' and p.xjdt <= '"+xjdt+"') " +
					" or( jhdt >= '"+jhdt+"' and ('"+xjdt+"' between jhdt and xjdt))" +
					" or(('"+jhdt+"' between jhdt and xjdt ) and ('"+xjdt+"' between jhdt and xjdt))" +
					" or(('"+jhdt+"' between jhdt and xjdt ) and xjdt <= '"+xjdt+"'))");
		}else{
			if(xjdate!=null&&!xjdate.isEmpty()){
				this.xjdt=Timestamp.valueOf(xjdate);

				cal.setTime(xjdt);
				int day =  cal.get(Calendar.DATE); 
				int month =cal.get(Calendar.MONTH); 
				int year = cal.get(Calendar.YEAR) ;

				cal.set(year-5, month, day);
				this.jhdt=new Timestamp(cal.getTimeInMillis());

				sql.append(" and ((jhdt >= '"+jhdt+"' and xjdt <= '"+xjdt+"') " +
						" or( jhdt >= '"+jhdt+"' and ('"+xjdt+"' between jhdt and xjdt))" +
						" or(('"+jhdt+"' between jhdt and xjdt ) and ('"+xjdt+"' between jhdt and xjdt))" +
						" or(('"+jhdt+"' between jhdt and xjdt ) and xjdt <= '"+xjdt+"'))");
			}
		}


		totalcount = util.getTotalCount(sql.toString());

		totalpage = totalcount % pageSize == 0 ? totalcount / pageSize
				: totalcount / pageSize + 1;

		offset = getPageOffset();

		loadBPList = util.getPageListBySql(sql.toString(),String.valueOf(offset),String.valueOf(pageSize),new Class[]{BProductP.class});

		return "loadBpList";
	}

	/**
	 * 根据产品编号获取产品信息
	 * @return
	 */
	public String getBProductPCode(){
		BProductP bp=bProductPService.findById(productCd);
		pds.setProductCd(bp);
		colorMap = bProductPService.findColorByProductCd(productCd);
		return "caseAddBProductP";
	}

	/**
	 * 手动添加选款结果
	 * @return
	 */
	public String saveParaDtS(){
		HttpServletRequest request = ServletActionContext.getRequest();

		String pdsColo = request.getParameter("colo");
		String pdsCona = request.getParameter("cona");
		String newOldFlag = request.getParameter("newOldFlag");
		String avgAmt = request.getParameter("avgAmt");

		pds.setColo(pdsColo);
		pds.setCona(pdsCona);
		pds.setNewOldFlag(newOldFlag);

		if(avgAmt != null){
			pds.setAvgAmt(Double.valueOf(avgAmt));
		}

		try {
			paraDtSBiz.saveParaDtS(pds);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}


		return SUCCESS;
	}

	/**
	 * 删除活动选款结果
	 * @return
	 */
	public String delParaDts(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String paraDtDId = request.getParameter("paraDtDId");

		if(paraDtDId!=null&&!paraDtDId.isEmpty()){
			int id = Integer.parseInt(paraDtDId);
			paraDtSBiz.deleteParaDtS(id);
		}
		return "del";
	}

	/**
	 * 导出的模板
	 */
	public String importTemplate() throws Exception {
		/*
		 * 设置表头：对Excel每列取名(必须根据你取的数据编写)
		 */
		String[] tableHeader = { "活动ID", "活动参与产品SKU", "活动状态" };
		util.getTemplate(tableHeader, "营销活动选款");
		return null;
	}

	/**
	 * 获取活动款及色的数量
	 * @param case_id
	 * @param top
	 */
	private String getPrdtSummaryByCaseId (int caseId){
		int top = -1;
		int del_status = 0;

		//产品对应的款/色
		Map<String, Integer> casePrdtSummaryMap = paraDtSBiz.getCasePrdtSummary(caseId,top,del_status);
		Set<String> keyset=casePrdtSummaryMap.keySet();
		//唯品会(R系列)[活动ID=208]，需选 para_case_p.num (款+色)，BI建议  fn_get_prdt_colo_count(..)  (款+色)，共 fn_get_prdt_count(...) 款，fn_get_tyna_count(...)个类目
		//天猫双12 [活动ID = 215]，需选 para_case_p.num 款，BI建议fn_get_prdt_colo_count(..)款，n_get_tyna_count(...)个类目
		StringBuffer msg;
		if(pd==null){
			msg=new StringBuffer(caseName+" [活动ID = "+caseId+"]");
		}else{
			msg=new StringBuffer(caseName+" [活动ID = "+caseId+"]，需选"+pd.getNum());
			int productCount = casePrdtSummaryMap.get("productCount");
			int tynaCount = casePrdtSummaryMap.get("tynaCount");
			int coloCount = casePrdtSummaryMap.get("coloCount");
			
			if(pcp.getCType().equals("p")){
				msg.append("款，BI建议"+coloCount+"款，");
				msg.append(tynaCount+"个类目 ");
			}else{
				msg.append("（款+色），BI建议"+coloCount+"（款+色），共 "+productCount+"款，");
				msg.append(tynaCount+"个类目 ");
			}
		}
		return msg.toString();
	}

	/**
	 * 提交审核
	 * @throws UnsupportedEncodingException 
	 */
	public String changeStatus() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		//获得活动id和状态
		String Id = request.getParameter("caseId");
		caseId=Integer.valueOf(Id);
		
		//获取活动状态
		String stu = request.getParameter("status");
		caseStatus=Integer.valueOf(stu);
		
		if(this.caseName==null){
			this.caseName=request.getParameter("caseName");
			if(caseName!=null&&!caseName.isEmpty()){
				caseName=new String(caseName.trim().getBytes("ISO-8859-1"),"UTF-8");
			}
		}else{
			boolean isMessyCode = util.isMessyCode(caseName);
			if(isMessyCode){  
	            try {  
	            	caseName =  new String(caseName.getBytes("ISO8859-1"), "UTF-8");  
	            } catch (Exception e) {  
	            }  
	        }  
		}
		
		//更改选款结果对应的SKU明细的状态
		util.setPrdtStatus(caseId, caseStatus, 5);
		
		
		//保存成功返回
		refreshList = "paraCaseSgetParaDtSList";
		titleName = "营销活动选款";

		HttpSession session = request.getSession(false);
		msg =caseName+ " 【 活动id=" + caseId + "】 已经提交审核";
		session.setAttribute("msg", msg);
		
		return "ststusChange";
	}

}
