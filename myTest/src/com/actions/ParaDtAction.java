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

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.struts2.ServletActionContext;

import com.bean.PUser;
import com.bean.ParaCaseP;
import com.bean.ParaDt;
import com.bean.RefactorParaDt;
import com.bean.Store;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ParaCasePService;
import com.service.ParaDtService;
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
	private ParaDtService paraDtService;// service����
	private List<ParaDt> paraDtList;// ParaDt����
	private ParaDt paraDt;// ����
	private int rows;//�ܵ�����
	private int page;//ҳ��
	private int pageSize=10;//ÿҳ��ʾ������
	private int offset;//����jspҳ�洫����ҳ����
	private boolean flag;
	private String msg;
	private RefactorParaDt refactorParaDt;//�ع������
	private List<RefactorParaDt> refactorParaDtList;// ParaSysValueP����
	private UtilSupport util;// service����
	private ParaCaseP paraCaseP;//Paracasep����
	private List<ParaCaseP> listCaseName;
	private Map<String,Object> dataMap; 
	private Map<String,Object> jsonResult;

	private Integer caseId;
	private String caseName;
	private String brde;
	private Timestamp caseSt;
	private Timestamp caseEt;
	private String caseDesc;

	private List<ParaDt> intolist;

	// myFile����������װ�ϴ����ļ�
	private File myFile;
	// myFileContentType����������װ�ϴ��ļ�������
	private String myFileContentType;
	// myFileFileName����������װ�ϴ��ļ����ļ���
	private String myFileFileName;

	private String sort;
	private String order;

	public ParaDtAction() {
		refactorParaDtList=new ArrayList<RefactorParaDt>();
		intolist=new ArrayList<ParaDt>();
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

	/**
	 * ��ȡ��������
	 */
	public String getAllName(){
		//�������Ϸ�װ���е�paracasep
		listCaseName=new ArrayList<ParaCaseP>();

		listCaseName=paraCasePService.allParaCaseP();
		return "getAllName";
	}

	/**
	 * ͨ�����ƻ�ȡ�����
	 */
	public String getNamePCP(){
		try {
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpServletResponse response=ServletActionContext.getResponse();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");

			//��ȡ�����
			String caseName = request.getParameter("caseName");
			paraCaseP=paraCasePService.getNameParaCaseP(caseName);

			//����map���Ϸ�װҳ��Ҫ��ʾ������
			dataMap=new HashMap<String, Object>();
			dataMap.put("caseCode", paraCaseP.getCaseCode());
			dataMap.put("CType", paraCaseP.getCType());
			dataMap.put("num", paraCaseP.getNum());

			//��map����ת����json��������
			jsonResult = dataMap;

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "jsonResult";
	}

	/**
	 * ͨ������ͻ�ȡʱ����бȽ�
	 */
	public String judgeNameRepeat(){
		try {
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpServletResponse response=ServletActionContext.getResponse();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");

			//��ȡ����� ��ʼʱ�� ����ʱ��
			String caseName = request.getParameter("caseName");
			String caseSt=  request.getParameter("caseSt");
			String caseEt=  request.getParameter("caseEt");

			//ת��ΪdateTime
			Timestamp startTime= Timestamp.valueOf(caseSt);
			Timestamp endTime=Timestamp.valueOf(caseEt);
			
			int spaceTime=paraDtService.getCaseNameTime(caseName, startTime, endTime);
			if(spaceTime>0){
				flag=true;
			}else if(spaceTime==0){
				flag=false;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SUCCESS;
	}


	/**
	 * ��ȡ���м��������Ϣ
	 */
	public String getAll(){
		paraDtList=paraDtService.allParaDt();
		return "allParaDt";
	}

	/**
	 *ͨ��id��ȡ��ϸ��Ϣ 
	 */
	public String getParaDtId(){
		try {
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpServletResponse response=ServletActionContext.getResponse();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");

			paraDt=paraDtService.findParaDtById(caseId);

			//ͨ������ƻ�ȡ��Ӧ������
			paraCaseP=paraCasePService.getNameParaCaseP(paraDt.getCaseName());

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "getParaDtId";
	}

	/**
	 * �޸Ļ��Ϣ
	 */
	public String updateParaDt() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Date date = new Date();// ����һ��ʱ����󣬻�ȡ����ǰ��ʱ��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ����ʱ����ʾ��ʽ
		String str = sdf.format(date);// ����ǰʱ���ʽ��Ϊ��Ҫ������

		paraDt.setSysDt(Timestamp.valueOf(str));
		paraDt.setSysUserId(ParaCasePAction.getCurrentUserName());
		paraDtService.updateParaDtImpl(paraDt);

		//�޸ĳɹ��󣬹����̨����֪ͨ BIϵͳִ�лѡ�
		util.callPRtCase(paraDt.getCaseCode(), paraDt.getCaseId());
		return "all";
	}


	/**
	 * ��ӻ��Ϣ
	 */
	public String savePCD(){
		HttpServletRequest request=ServletActionContext.getRequest();
		Date date= new Date();//����һ��ʱ����󣬻�ȡ����ǰ��ʱ��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//����ʱ����ʾ��ʽ
		String str = sdf.format(date);//����ǰʱ���ʽ��Ϊ��Ҫ������

		//��װ����
		paraDt.setCaseName(paraCaseP.getCaseName());
		paraDt.setCaseCode(paraCaseP.getCaseCode());
		paraDt.setStatus(2);
		paraDt.setSysDt(Timestamp.valueOf(str));
		paraDt.setSysUserId(ParaDtAction.getCurrentUserName());

		//�ж�ѡ����
		//int styleNum=paraDt.getNum();
		if(paraDt.getNum()!=0){
			paraDtService.saveParaDt(paraDt);
		}else{
			paraDt.setNum(paraCaseP.getNum());
			paraDtService.saveParaDt(paraDt);
		}

		//��ӳɹ��󣬹����̨����֪ͨ BIϵͳִ�лѡ�
		util.callPRtCase(paraDt.getCaseCode(), paraDt.getCaseId());

		HttpSession session = request.getSession(false);
		String caseName=paraCaseP.getCaseName();
		msg=" ["+caseName+"] ";
		session.setAttribute("msg", msg);

		return "savePCD";

	}
	
	private List getParaDtInfo(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");

			//��ȡ�����
			listCaseName=new ArrayList<ParaCaseP>();
			listCaseName=paraCasePService.allParaCaseP();

			StringBuffer sql=new StringBuffer(
					"select * from para_dt a " +
							"INNER JOIN para_case_p b on a.case_code=b.case_code " +
							"INNER JOIN Store c ON b.chal_cd=c.Code " +
					"where a.status!=0 ");

			this.caseName = request.getParameter("caseName");
			if(caseName!=null&&!caseName.isEmpty()){
				caseName=new String(caseName.trim().getBytes("ISO-8859-1"),"UTF-8");
				sql.append(" and a.case_name = '"+caseName+"'");
			}

			this.brde = request.getParameter("brde");
			if(brde!=null&&!brde.isEmpty()){
				if(!brde.equals("���Ʒ��")){
					brde=new String(brde.trim().getBytes("ISO-8859-1"),"UTF-8");
					sql.append(" and b.brde = '"+brde+"'");
				}
			}

			this.caseDesc = request.getParameter("caseDesc");
			if(caseDesc!=null&&!caseDesc.isEmpty()){
				caseDesc = new String(caseDesc.trim().getBytes("ISO-8859-1"),"UTF-8");
				sql.append(" and a.case_desc like '%"+caseDesc+"%'");
			}

			String caseStime=request.getParameter("caseSt");
			String caseEtime=request.getParameter("caseEt");
			Calendar cal = Calendar.getInstance();

			if(caseStime!=null&&!caseStime.isEmpty()){
				this.caseSt=Timestamp.valueOf(caseStime);
				if(caseEtime!=null&&!caseEtime.isEmpty()){
					this.caseEt=Timestamp.valueOf(caseEtime);
				}else{
					cal.setTime(caseSt);
					int day =  cal.get(Calendar.DATE); 
					int month =cal.get(Calendar.MONTH); 
					int year = cal.get(Calendar.YEAR) ;

					cal.set(year+5, month, day);
					year = cal.get(Calendar.YEAR) ;
					this.caseEt=new Timestamp(cal.getTimeInMillis());
				}
				sql.append(" and ((a.case_st >= '"+caseSt+"' and a.case_et <= '"+caseEt+"') " +
						" or( a.case_st >= '"+caseSt+"' and ('"+caseEt+"' between a.case_st and a.case_et))" +
						" or(('"+caseSt+"' between a.case_st and a.case_et ) and ('"+caseEt+"' between a.case_st and a.case_et))" +
						" or(('"+caseSt+"' between a.case_st and a.case_et ) and a.case_et <= '"+caseEt+"'))");
			}else{
				if(caseEtime!=null&&!caseEtime.isEmpty()){
					this.caseEt=Timestamp.valueOf(caseEtime);
					cal.setTime(caseEt);
					int day =  cal.get(Calendar.DATE); 
					int month =cal.get(Calendar.MONTH); 
					int year = cal.get(Calendar.YEAR) ;

					cal.set(year-5, month, day);
					this.caseSt=new Timestamp(cal.getTimeInMillis());

					sql.append(" and ((a.case_st >= '"+caseSt+"' and a.case_et <= '"+caseEt+"') " +
							" or( a.case_st >= '"+caseSt+"' and ('"+caseEt+"' between a.case_st and a.case_et))" +
							" or(('"+caseSt+"' between a.case_st and a.case_et ) and ('"+caseEt+"' between a.case_st and a.case_et))" +
							" or(('"+caseSt+"' between a.case_st and a.case_et ) and a.case_et <= '"+caseEt+"'))");
				}
			}

			if(sort==null||sort==""){
				//��ȡ ��ʼʱ�� ����ʱ��  ���� ���ǽ���
				this.sort = request.getParameter("sort");
				this.order=  request.getParameter("order");
			}

			if(sort!=null&&!sort.isEmpty()){
				sql.append(" order by "+sort+" "+order);
			}else{
				sql.append(" order by a.sys_dt desc");
			}

			rows = util.getTotalCount(sql.toString());

			page = rows % pageSize == 0 ? rows / pageSize : rows / pageSize + 1;

			offset = getPageOffset();

			List<Object[]> resultSet = util.getPageListBySql(sql.toString(),
					String.valueOf(offset), String.valueOf(pageSize),
					new Class[] {ParaDt.class, ParaCaseP.class, Store.class });

			// �ѽ����ת�浽��Ա���� pgulis ��
			fillPcpList(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return refactorParaDtList;
	}
	
	/**
	 * ��ҳ��ʾ
	 */
	@SuppressWarnings("unchecked")
	public String getParaDtAll() {
		this.getParaDtInfo();
		return "all";
	}

	public String chooseParaDt(){
		this.getParaDtInfo();
		return "chooseParaDt";
	}

	// Added by JSL : ��ȡ��ҳƫ����(ʵ�����ǽ�Ҫ������ҳ���ҳ������ҳ������ 0 ��ʼ)
	private int getPageOffset() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String ofst = request.getParameter("offset");
		int idx = 0;
		if(ofst!=null){
			idx = Integer.valueOf(ofst);
			idx = idx < 0 ? 0 : idx;                        // ������һҳʱ�����ٷ�ҳ
			idx = idx >= page ? (page-1) : idx;	// �������һҳʱ�����ٷ�ҳ		
		}
		return idx;
	}

	private void fillPcpList(List<Object[]> resultSet) {
		refactorParaDtList.clear();
		for (Object[] r : resultSet) {
			RefactorParaDt pcp =new  RefactorParaDt();
			ParaDt pd=(ParaDt) r[0];
			ParaCaseP pc=(ParaCaseP) r[1];
			Store s=(Store) r[2];

			pcp.setCaseId(pd.getCaseId());
			pcp.setCaseEt(pd.getCaseEt());
			pcp.setCaseSt(pd.getCaseSt());
			String desc=pd.getCaseDesc();
			if(desc!=null){
				if(desc.length()>10){
					desc=desc.substring(0,10);
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
	 * ɾ���
	 * @return
	 */
	public String delParaDt(){
		//���ݻID���ҵ����Ϣ
		ParaDt pdt = paraDtService.findParaDtById(caseId);
		//�޸Ļ״̬Ϊ��ɾ��
		pdt.setStatus(0);   
		paraDtService.delParaDtById(pdt);
		return "all";
	}

	/**
	 * ��ȡ��ǰ�û���
	 */
	public static String getCurrentUserName() {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		PUser loginuser=(PUser)session.getAttribute("pu");
		String name=loginuser.getUserName();
		return name;
	}

	/*
	 *�ϴ��ļ�
	 */
	public String shangchan() throws Exception {
		// ����myFile����һ���ļ�������
		InputStream is = new FileInputStream(myFile);
		// �����ϴ��ļ�Ŀ¼
		String uploadPath = ServletActionContext.getServletContext()
				.getRealPath("/upload");
		// ����Ŀ���ļ�
		File toFile = new File(uploadPath, this.getMyFileFileName());
		// ����һ�������
		File toDir = new File(uploadPath);
		if (!toDir.exists()) {
			toDir.mkdir();
		}
		if (!toFile.exists()) {
			toFile.createNewFile();
		}
		OutputStream os = new FileOutputStream(toFile);
		// ���û���
		byte[] buffer = new byte[1024];
		int length = 0;
		// ��ȡmyFile�ļ������toFile�ļ���
		while ((length = is.read(buffer)) > 0) {
			os.write(buffer, 0, length);
		}
		// �ر�������
		is.close();
		// �ر������
		os.close();
		return "upload";
	}

	/**
	 * ����Excel���
	 */
	@SuppressWarnings("unused")
	public String intoDB()throws IOException {
		String uploadPath = ServletActionContext.getServletContext()
				.getRealPath("/upload");
		// ����myFile����һ���ļ�������
		InputStream is = new FileInputStream(myFile);
		// ����Ŀ���ļ�
		File toFile = new File(uploadPath, this.getMyFileFileName());

		String caseName = null;//�����
		String caseDesc= null;//�����
		Timestamp caseSt= null;//���ʼʱ��
		Timestamp caseEt= null;//�����ʱ��
		String sysUserId= null;//����/�޸��û�ID
		Timestamp sysDt= null;//�޸�ʱ��
		Integer status= null;//�״̬
		String caseCode= null;//�����
		Double ratioNew= null;//�¿�ռ��
		Integer num= null;//�������
		Date orgCaseSt= null;//��ע��ʼʱ��
		Date orgCaseEt= null;//��ע����ʱ��

		/**
		 * 2007��Ķ�ȡ���� 
		 */	
		int k=0; 
		int flag = 0;   //ָʾָ�������ʵ�λ�� 
		if(myFile!=null) {
			try {
				Workbook workbook = WorkbookFactory.create(toFile);
				// Workbook  workbook = new XSSFWorkbook(is);//��ʼ��workbook���� 
				for (int numSheets = 0; numSheets < workbook.getNumberOfSheets(); numSheets++) {  //��ȡÿһ��sheet  
					if (null != workbook.getSheetAt(numSheets)) {    
						XSSFSheet aSheet = (XSSFSheet)workbook.getSheetAt(numSheets);//����Sheet���� 

						for (int rowNumOfSheet = 0; rowNumOfSheet <= aSheet.getLastRowNum(); rowNumOfSheet++) {  
							//���뵱ǰsheet���е�ѭ��   
							if (null != aSheet.getRow(rowNumOfSheet)) { 
								XSSFRow  aRow = aSheet.getRow(rowNumOfSheet);//�����У�����ֵ 
								for (int cellNumOfRow = 0; cellNumOfRow <= aRow.getLastCellNum(); cellNumOfRow++){
									intolist.clear();
									//��ȡrowNumOfSheetֵ����Ӧ�е����� 
									XSSFCell  xCell = aRow.getCell(cellNumOfRow); //����е�����	//�����ֵ  
									//System.out.println("type="+xCell.getCellType()); 
									if (null != aRow.getCell(cellNumOfRow)){ 
										// ���rowNumOfSheet��ֵΪ0�����ȡ��ͷ���ж�excel�ĸ�ʽ��Ԥ����ʽ�Ƿ����       
										if(rowNumOfSheet == 0){	     
											if(xCell.getCellType() == XSSFCell .CELL_TYPE_STRING){ 
												/**
												 * һ�¸��ݴ�Excel�ĸ��������Ƿ����Ҫ��������ƥ�䣺����ƣ�����������ʼʱ�䣬
												 *          �����ʱ��,�����û�,�޸�ʱ��,�״̬,�����,�¿�ռ��,�������,��ע��ʼʱ��,��ע����ʱ��
												 */
												if(cellNumOfRow == 0){	
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("�����")){ 
														flag++; 
													}else{ 
														System.out.println("���󣺵�һ�еĻ���Ʋ�����Լ����ʽ"); 
													} 
												}else if(cellNumOfRow == 1){ 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("�����")){ 
														flag++; 
													}else{ 
														System.out.println("���󣺵�һ�еĻ����������Լ����ʽ"); 
													}         
												}else if(cellNumOfRow == 2){ 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("���ʼʱ��")){ 
														flag++;      
													}else{ 
														System.out.println("���󣺵�һ�еĻ��ʼʱ�䲻����Լ����ʽ"); 
													} 
												}else if (cellNumOfRow == 3) { 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("�����ʱ��")){ 
														flag++; 
													}else{ 
														System.out.println("���󣺵�һ�еĻ����ʱ�䲻����Լ����ʽ"); 
													} 
												}else if (cellNumOfRow == 4){ 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("�����û�")){ 
														flag++; 
													}else{ 
														System.out.println("��һ�еĲ����û�������Լ����ʽ"); 
													} 
												}else if (cellNumOfRow == 5){ 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("�޸�ʱ��")){ 
														flag++; 
													}else{ 
														System.out.println("��һ�е��޸�ʱ�䲻����Լ����ʽ"); 
													}  
												}else if (cellNumOfRow == 6){ 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("�״̬")){ 
														flag++; 
													}else{ 
														System.out.println("��һ�еĻ״̬������Լ����ʽ"); 
													} 
												}else if (cellNumOfRow == 7){ 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("�����")){ 
														flag++; 
													}else{ 
														System.out.println("��һ�еĻ���벻����Լ����ʽ"); 
													} 
												}else if (cellNumOfRow == 8){ 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("�¿�ռ��")){ 
														flag++; 
													}else{ 
														System.out.println("��һ�е��¿�ռ�Ȳ�����Լ����ʽ"); 
													} 
												}else if (cellNumOfRow == 9){ 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("�������")){ 
														flag++; 
													}else{ 
														System.out.println("��һ�еĲ������������Լ����ʽ"); 
													} 
												}else if (cellNumOfRow == 10){ 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("��ע��ʼʱ��")){ 
														flag++; 
													}else{ 
														System.out.println("��һ�еı�ע��ʼʱ�䲻����Լ����ʽ"); 
													} 
												}else if (cellNumOfRow == 11){ 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("��ע����ʱ��")){ 
														flag++; 
													}else{ 
														System.out.println("��һ�еı�ע����ʱ�䲻����Լ����ʽ"); 
													} 
												}
											}
										}else {																												
											//rowNumOfSheet != 0 ����ʼ��ӡ���� 
											//��ȡexcel��ÿ�е�ֵ����������Ӧ�ı��������µĸ�ֵ��ID��name,sex, Dormitory,sept; 
											if(xCell.getCellType() == XSSFCell .CELL_TYPE_NUMERIC){	//Ϊ��ֵ��	
												System.out.println("===============����XSSFCell .CELL_TYPE_NUMERICģ��============");
												switch(cellNumOfRow){
												case 2:
													Date St = (Date) xCell.getDateCellValue();    //�����ڴ���  
													caseSt=new Timestamp(St.getTime());	
													break;  
												case 3:
													Date Et = (Date) xCell.getDateCellValue();    //�����ڴ���
													caseEt=new Timestamp(Et.getTime());
													break;
												case 5:
													Date Dt = (Date) xCell.getDateCellValue();    //�����ڴ���
													sysDt=new Timestamp(Dt.getTime());		
													break;
												case 6:
													status=(int) xCell.getNumericCellValue();	
													break;
												case 8:
													ratioNew=xCell.getNumericCellValue();	
													break;
												case 9:
													num=(int) xCell.getNumericCellValue();	
													break;
												case 10:
													orgCaseSt=(Date) xCell.getDateCellValue();		
													break;
												case 11:
													orgCaseEt=(Date) xCell.getDateCellValue();
													break;
												}
											}else if(xCell.getCellType() == XSSFCell .CELL_TYPE_STRING){  //Ϊ�ַ�����  
												System.out.println("===============����XSSFCell .CELL_TYPE_STRINGģ��============"); 
												switch(cellNumOfRow){
												case 0:
													caseName=xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();	
													break;
												case 1:
													caseDesc=xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();	
													break;
												case 4: 
													sysUserId=xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();	
													break;
												case 7:
													caseCode=xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();	
													break;

												}
											}else if (xCell.getCellType() == XSSFCell .CELL_TYPE_BLANK) { 
												System.out.println("��ʾ����Sheet"+(numSheets+1)+"�еĵ�"+(rowNumOfSheet+1)+"�еĵ�"+(cellNumOfRow+1)+"�е�ֵΪ�գ���鿴�˶��Ƿ����Լ��Ҫ��");
												switch(cellNumOfRow){
												case 0:
													caseName="";	break;
												case 1:
													caseDesc="";	break;
												case 2:
													caseSt=null;	break;  
												case 3:
													caseEt=null;	break;
												case 4: 
													sysUserId="";	break;
												case 5:
													sysDt=null;		break;
												case 6:
													status=null;	break;
												case 7:
													caseCode="";	break;
												case 8:
													ratioNew=null;	break;
												case 9:
													num=null;	break;
												case 10:
													orgCaseSt=null;	break;
												case 11:
													orgCaseEt=null;break;
												}
											} 
										}
									}
								}
								// �жϸ���Ԫ�ر���ֵ,����������ݿ⣬��ֱ��ʹ�����ݵĲ���ĺ����Ϳ����ˡ�
								if (aRow.getRowNum()!=0){ 
									System.out.println("��˶Ժ�����"); 
									ParaDt pd=new ParaDt();
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
									pd.setOrgCaseEt(orgCaseEt);
									pd.setOrgCaseSt(orgCaseSt);
									pd.setCaseCode(caseCode);

									intolist.add(pd);
									System.out
									.println(intolist.size());
								}
							} //���һ�У�����ȡÿһ��
						}
						//��ȡÿһ��sheet 
					} 
				}
				if(intolist.size()>0){
					for (int i = 0; i < intolist.size(); i++) {
						System.out.println(i+"size"+intolist.size());
						paraDtService.saveOneBoat(intolist.get(i));
					}
				}
			}catch (Exception e) {                 
				e.printStackTrace(); 
			}
		} 

		return "intoDB"; 
	}
}
