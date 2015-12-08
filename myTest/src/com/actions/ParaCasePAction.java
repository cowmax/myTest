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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.taglibs.standard.tag.common.core.UrlSupport;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bean.ParaCaseP;
import com.bean.Para_Type;
import com.bean.Store;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ParaCasePService;
import com.service.StoreService;
import com.serviceimpl.UtilSupport;

@SuppressWarnings("serial")
public class ParaCasePAction extends ActionSupport {

	private ParaCasePService paraCasePService;// service����
	private List<ParaCaseP> paraCasePList;// paraCaseP����
	private ParaCaseP paraCaseP;// ����
	private UtilSupport util;
	private int rows;// �ܵ�����
	private int page;// ҳ��
	private int pageSize = 10;// ÿҳ��ʾ������
	private int offset;// ����jspҳ�洫����ҳ����
	private boolean flag;
	private String msg;
	private String caseCode;// ��Ʒ���
	private StoreService storeService;//storeservice����
	private List<Store> ListStore;
	private SessionFactory sessionFactory;
	List <ParaCaseP> pcpList=new ArrayList<ParaCaseP>();
	// myFile����������װ�ϴ����ļ�
	private File myFile;
	// myFileContentType����������װ�ϴ��ļ�������
	private String myFileContentType;
	// myFileFileName����������װ�ϴ��ļ����ļ���
	private String myFileFileName;

	private String caseName;//��Ʒ����
	private String chalCd;//����/����
	private String caseLevel;//�����
	private String brde;//�Ʒ��
	
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
	 * ��ȡ���л��Ϣ
	 */
	public String getAll() {
		paraCasePList = paraCasePService.allParaCaseP();
		return "allParaCaseP";
	}

	/**
	 * ͨ��code��ȡ���Ϣ
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
	 * ͨ��id��ɾ����Ʒ��Ϣ
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
	 * ͨ��id���޸Ĳ�Ʒ��Ϣ
	 */
	public String updateParaCaseP() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Date date = new Date();// ����һ��ʱ����󣬻�ȡ����ǰ��ʱ��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ����ʱ����ʾ��ʽ
		String str = sdf.format(date);// ����ǰʱ���ʽ��Ϊ��Ҫ������
		paraCaseP.setSysDt(Timestamp.valueOf(str));
		paraCaseP.setSysUserId(ParaCasePAction.getCurrentUserName());
		paraCasePService.updateParaCaseP(paraCaseP);
		return "updateParaCaseP";
	}

	/**
	 * ��Ӳ�Ʒ��Ϣ
	 */
	public String saveParaCaseP() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Date date = new Date();// ����һ��ʱ����󣬻�ȡ����ǰ��ʱ��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ����ʱ����ʾ��ʽ
		String str = sdf.format(date);// ����ǰʱ���ʽ��Ϊ��Ҫ������
		paraCaseP.setSysDt(Timestamp.valueOf(str));
		paraCaseP.setSysUserId(ParaCasePAction.getCurrentUserName());
		paraCasePService.saveParaCaseP(paraCaseP);
		HttpSession session = request.getSession(false);
		String caseCode = paraCaseP.getCaseCode();
		msg = " [" + caseCode + "] ";
		session.setAttribute("msg", msg);
		return "saveParaCaseP";

	}

	/**
	 * ����������ѯ
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
			if(!chalCd.equals("����/����")){
				sql.append(" and pcp.chal_cd like '%"+chalCd+"%'");
			}
		}
		
		this.caseLevel=request.getParameter("caseLevel");
		if(caseLevel!=null&&!caseLevel.isEmpty()){
			caseLevel=new String(caseLevel.trim().getBytes("ISO-8859-1"),"UTF-8");
			if(!caseLevel.equals("�����")){
			sql.append(" and pcp.case_level like '%"+caseLevel+"%'");
			}
		}

		this.brde=request.getParameter("brde");
		if(brde!=null&&!brde.isEmpty()){
			brde=new String(brde.trim().getBytes("ISO-8859-1"),"UTF-8");
			if(!brde.equals("�Ʒ��")){
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
		paraCasePList.clear();
		for (Object[] r : resultSet) {
			ParaCaseP pcp = (ParaCaseP) r[0];
			pcp.setChalCd((Store) r[1]);
			paraCasePList.add(pcp);
		}
	}

	/**
	 * �жϻ�����Ƿ��Ѵ���
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
	 * �жϻ�����Ƿ��Ѵ���
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
	 * ��ȡ��ǰ�û���
	 */
	public static String getCurrentUserName() {
		String a = "������";
		return a;
	}

	/**
	 * ��ѯstore
	 */
	public String allStorecode(){
		ListStore=storeService.getStoreList();
		return "ListStore";
	}
	/**
	 * ��ѯ���para_dt���û�б�ռ��
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
	 *�ϴ��ļ�
	 */
	public String execute() throws Exception {
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
		return SUCCESS;
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
									//��ȡrowNumOfSheetֵ����Ӧ�е����� 
									XSSFCell  xCell = aRow.getCell(cellNumOfRow); //����е�����	//�����ֵ   
									//System.out.println("type="+xCell.getCellType()); 
									if (null != aRow.getCell(cellNumOfRow)){ 	
										// ���rowNumOfSheet��ֵΪ0�����ȡ��ͷ���ж�excel�ĸ�ʽ��Ԥ����ʽ�Ƿ����       
										if(rowNumOfSheet == 0){	     
											if(xCell.getCellType() == XSSFCell .CELL_TYPE_STRING){ 
												//һ�¸��ݴ�Excel�ĸ��������Ƿ����Ҫ��������ƥ�䣺����룬����ƣ�����/���̣������ǰ��Ӱ��ʱ�䣬Ʒ�� ����Ʒ������ѡ������,�����û�,�޸�ʱ��
												if(cellNumOfRow == 0){	
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("�����")){ 
														flag++; 
													}else{ 
														System.out.println("���󣺵�һ�еĻ���벻����Լ����ʽ"); 
													} 
												}else if(cellNumOfRow == 1){ 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("�����")){ 
														flag++; 
													}else{ 
														System.out.println("���󣺵�һ�еĻ���Ʋ�����Լ����ʽ"); 
													}         
												}else if(cellNumOfRow == 2){ 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("����/����")){ 
														flag++;      
													}else{ 
														System.out.println("���󣺵�һ�е�����/���̲�����Լ����ʽ"); 
													} 
												}else if (cellNumOfRow == 3) { 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("�����")){ 
														flag++; 
													}else{ 
														System.out.println("���󣺵�һ�еĻ���𲻷���Լ����ʽ"); 
													} 
												}else if (cellNumOfRow == 4){ 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("ǰ��Ӱ��ʱ��")){ 
														flag++; 
													}else{ 
														System.out.println("��һ�е�ǰ��Ӱ��ʱ�䲻����Լ����ʽ"); 
													} 
												}else if (cellNumOfRow == 5){ 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("Ʒ��")){ 
														flag++; 
													}else{ 
														System.out.println("��һ�е�Ʒ�Ʋ�����Լ����ʽ"); 
													}  
												}else if (cellNumOfRow == 6){ 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("��Ʒ����")){ 
														flag++; 
													}else{ 
														System.out.println("��һ�еĲ�Ʒ����������Լ����ʽ"); 
													} 
												}else if (cellNumOfRow == 7){ 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("ѡ������")){ 
														flag++; 
													}else{ 
														System.out.println("��һ�е�ѡ�����Ȳ�����Լ����ʽ"); 
													} 
												}else if (cellNumOfRow == 8){ 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("�޸�ʱ��")){ 
														flag++; 
													}else{ 
														System.out.println("��һ�еĲ����û�������Լ����ʽ"); 
													} 
												}else if (cellNumOfRow == 9){ 
													if(xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim().equals("�����û�")){ 
														flag++; 
													}else{ 
														System.out.println("��һ�е��޸�ʱ�䲻����Լ����ʽ"); 
													} 
												}
											}
										}else {																												
											//rowNumOfSheet != 0 ����ʼ��ӡ���� 
											//��ȡexcel��ÿ�е�ֵ����������Ӧ�ı��������µĸ�ֵ��ID��name,sex, Dormitory,sept; 
											if(xCell.getCellType() == XSSFCell .CELL_TYPE_NUMERIC){	//Ϊ��ֵ��	
												System.out.println("===============����XSSFCell .CELL_TYPE_NUMERICģ��============");
												if(cellNumOfRow == 2){	
													chalCd = String.valueOf(Math.round(xCell.getNumericCellValue()));
													if(chalCd == null){ 
														System.out.println("������Sheet"+(numSheets+1)+"�еĵ�"+(rowNumOfSheet+1)+"�еĵ�"+(cellNumOfRow+1)+"�е�����/���̲���Ϊ��"); 
													}	 
												}else if (cellNumOfRow == 4){
													Integer d = (int) xCell.getNumericCellValue();
													preNum = d;
													if(preNum ==null){ 
														System.out.println("������Sheet"+(numSheets+1)+"�еĵ�"+(rowNumOfSheet+1)+"�еĵ�"+(cellNumOfRow+1)+"�е�ǰ��Ӱ��ʱ�䲻��Ϊ��");
													}
												}else if (cellNumOfRow == 6){	
													num = (int)xCell.getNumericCellValue(); 
													if(num==null){ 
														System.out.println("������Sheet"+(numSheets+1)+"�еĵ�"+(rowNumOfSheet+1)+"�еĵ�"+(cellNumOfRow+1)+"�еĲ�Ʒ��������Ϊ��");
													}
												}else if (cellNumOfRow == 8){	
													Date d = (Date) xCell.getDateCellValue();    //�����ڴ���  
													Timestamp startTime = new Timestamp(d.getTime());
													sysDt= startTime;
													if(sysDt == null){	                     
														System.out.println("������Sheet"+(numSheets+1)+"�еĵ�"+(rowNumOfSheet+1)+"�еĵ�"+(cellNumOfRow+1)+"�е��޸�ʱ�䲻��Ϊ��"); 
													} 
												}                
											}else if(xCell.getCellType() == XSSFCell .CELL_TYPE_STRING){  //Ϊ�ַ�����  
												System.out.println("===============����XSSFCell .CELL_TYPE_STRINGģ��============"); 
												if(cellNumOfRow == 0){ 
													caseCode = xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim(); 
													if(caseCode == null){ 
														System.out.println("������Sheet"+(numSheets+1)+"�еĵ�"+(rowNumOfSheet+1)+"�еĵ�"+(cellNumOfRow+1)+"�еĻ��Ų���Ϊ��"); 
													} 
												}else if(cellNumOfRow == 1){	
													caseName =xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
													if(caseName == null){ 
														System.out.println("������Sheet"+(numSheets+1)+"�еĵ�"+(rowNumOfSheet+1)+"�еĵ�"+(cellNumOfRow+1)+"�еĻ���Ʋ���Ϊ��"); 
													} 
												}else if(cellNumOfRow == 2){	
													chalCd =xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
													if(chalCd == null){ 
														System.out.println("������Sheet"+(numSheets+1)+"�еĵ�"+(rowNumOfSheet+1)+"�еĵ�"+(cellNumOfRow+1)+"�е�����/���̲���Ϊ��"); 
													}	                            
												}else if (cellNumOfRow == 3){	
													caseLevel =xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
													if(caseLevel == null){ 
														System.out.println("������Sheet"+(numSheets+1)+"�еĵ�"+(rowNumOfSheet+1)+"�еĵ�"+(cellNumOfRow+1)+"�еĻ������Ϊ��");
													}
												}else if (cellNumOfRow == 5){	
													brde = xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim(); 
													if(brde == null){ 
														System.out.println("������Sheet"+(numSheets+1)+"�еĵ�"+(rowNumOfSheet+1)+"�еĵ�"+(cellNumOfRow+1)+"�е�Ʒ�Ʋ���Ϊ��"); 
													}
												}else if (cellNumOfRow == 7){	
													CType =xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim(); 
													if(CType == null){ 
														System.out.println("������Sheet"+(numSheets+1)+"�еĵ�"+(rowNumOfSheet+1)+"�еĵ�"+(cellNumOfRow+1)+"�е�ѡ�����Ȳ���Ϊ��"); 
													}
												}else if (cellNumOfRow == 9){	
													sysUserId = xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim(); 
													if(sysUserId == null){	                     
														System.out.println("������Sheet"+(numSheets+1)+"�еĵ�"+(rowNumOfSheet+1)+"�еĵ�"+(cellNumOfRow+1)+"�е��޸�ʱ�䲻��Ϊ��"); 
													}   
												}
											}else if (xCell.getCellType() == XSSFCell .CELL_TYPE_BLANK) { 
												System.out.println("��ʾ����Sheet"+(numSheets+1)+"�еĵ�"+(rowNumOfSheet+1)+"�еĵ�"+(cellNumOfRow+1)+"�е�ֵΪ�գ���鿴�˶��Ƿ����Լ��Ҫ��"); 
											} 
										} 
									}	  
								} 
								if (flag!=10){ 
									System.out.println("��˶Ժ�����"); 
								} 

								// �жϸ���Ԫ�ر���ֵ�Ƿ�Ϊ�գ������Ϊ�վͷ��뵽stuList������������ݿ⣬��ֱ��ʹ�����ݵĲ���ĺ����Ϳ����ˡ�
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
							} //���һ�У�����ȡÿһ��   
						}   
						//��ȡÿһ��sheet 
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
	 * ������������excel���
	 */
	public String getexport() throws Exception {

		paraCasePList = paraCasePService.allParaCaseP();
		/*
		 * ���ñ�ͷ����Excelÿ��ȡ��(���������ȡ�����ݱ�д)
		 */
		String[] tableHeader = { "�����", "�����", "����/����", "�����", "ǰ��Ӱ��ʱ��",
				"Ʒ��", "ȱʡ����", "ѡ������", "�޸�ʱ��", "�����û�" };
		/*
		 * ����Ķ����Կ������ñ�д
		 */
		short cellNumber = (short) tableHeader.length;// �������
		XSSFWorkbook workbook = new XSSFWorkbook(); // ����һ��excel
		XSSFCell cell = null; // Excel����
		XSSFRow row = null; // Excel����
		XSSFCellStyle style = workbook.createCellStyle(); // ���ñ�ͷ������
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		XSSFCellStyle style1 = workbook.createCellStyle(); // ������������
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		XSSFFont font = workbook.createFont(); // ��������
		XSSFSheet sheet = workbook.createSheet("sheet1"); // ����һ��sheet
		Header header = sheet.getHeader();// ����sheet��ͷ
		try {
			/**
			 * �����Ƿ�ȡ�����ݣ�����header��Ϣ
			 * 
			 */
			if (paraCasePList.size() < 1) {
				header.setCenter("��������");
			} else {
				header.setCenter("����ͱ�");
				row = sheet.createRow(0);
				row.setHeight((short) 400);
				for (int k = 0; k < cellNumber; k++) {
					cell = row.createCell(k);// ������0�е�k��
					cell.setCellValue(tableHeader[k]);// ���õ�0�е�k�е�ֵ
					sheet.setColumnWidth(k, 8000);// �����еĿ��
					font.setColor(HSSFFont.COLOR_NORMAL); // ���õ�Ԫ���������ɫ.
					font.setFontHeight((short) 350); // ���õ�Ԫ����߶�
					style1.setFont(font);// ����������
					cell.setCellStyle(style1);
				}
				/*
				 * ��excel�������������Ҫ��д
				 */
				for (int i = 0; i < paraCasePList.size(); i++) {
					ParaCaseP paraCaseP = (ParaCaseP) paraCasePList.get(i);// ��ȡstudent����
					row = sheet.createRow((short) (i + 1));// ������i+1��
					row.setHeight((short) 400);// �����и�

					if (paraCaseP.getCaseCode() != null) {
						cell = row.createCell(0);// ������i+1�е�0��
						cell.setCellValue(paraCaseP.getCaseCode());// ���õ�i+1�е�0�е�ֵ
						cell.setCellStyle(style);// ���÷��
					}
					if (paraCaseP.getCaseName() != null) {
						cell = row.createCell(1); // ������i+1�е�1��
						cell.setCellValue(paraCaseP.getCaseName());// ���õ�i+1�е�1�е�ֵ
						cell.setCellStyle(style); // ���÷��
					}
					if (paraCaseP.getChalCd().getCode() != null) {
						cell = row.createCell(2); // ������i+1�е�2��
						String aaa=paraCaseP.getChalCd().getCode();
						System.out.println(aaa);
						cell.setCellValue(aaa);// ���õ�i+1�е�2�е�ֵ
						cell.setCellStyle(style); // ���÷��
					}
					if (paraCaseP.getCaseLevel() != null) {
						cell = row.createCell(3); // ������i+1�е�3��
						cell.setCellValue(paraCaseP.getCaseLevel());// ���õ�i+1�е�3�е�ֵ
						cell.setCellStyle(style); // ���÷��
					}
					if (paraCaseP.getPreNum() != null) {
						cell = row.createCell(4); // ������i+1�е�4��
						cell.setCellValue(paraCaseP.getPreNum());// ���õ�i+1�е�4�е�ֵ
						cell.setCellStyle(style); // ���÷��
					}
					if (paraCaseP.getBrde() != null) {
						cell = row.createCell(5); // ������i+1�е�5��
						cell.setCellValue(paraCaseP.getBrde());// ���õ�i+1�е�5�е�ֵ
						cell.setCellStyle(style); // ���÷��
					}
					if (paraCaseP.getNum() != null) {
						cell = row.createCell(6); // ������i+1�е�7��
						cell.setCellValue(paraCaseP.getNum());// ���õ�i+1�е�7�е�ֵ
						cell.setCellStyle(style); // ���÷��
					}
					if (paraCaseP.getCType() != null) {
						cell = row.createCell(7); // ������i+1�е�6��
						cell.setCellValue(paraCaseP.getCType());// ���õ�i+1�е�6�е�ֵ
						cell.setCellStyle(style); // ���÷��
					}
					if (paraCaseP.getSysDt() != null) {
						cell = row.createCell(8); // ������i+1�е�8��
						cell.setCellValue(paraCaseP.getSysDt());// ���õ�i+1�е�8�е�ֵ
						cell.setCellStyle(style); // ���÷��
					}
					if (paraCaseP.getSysUserId() != null) {
						cell = row.createCell(9); // ������i+1�е�9��
						cell.setCellValue(paraCaseP.getSysUserId());// ���õ�i+1�е�9�е�ֵ
						cell.setCellStyle(style); // ���÷��
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * ����Ŀ��Բ��ñ�д��ֱ�ӿ���
		 */
		HttpServletResponse response = null;// ����һ��HttpServletResponse����
		OutputStream out = null;// ����һ�����������
		try {
			response = ServletActionContext.getResponse();// ��ʼ��HttpServletResponse����
			out = response.getOutputStream();//
			response.setHeader("Content-disposition", "attachment; filename="
					+ "paracase.xlsx");// filename�����ص�xls���������������Ӣ��
			response.setContentType("application/msexcel;charset=UTF-8");// ��������
			response.setHeader("Pragma", "No-cache");// ����ͷ
			response.setHeader("Cache-Control", "no-cache");// ����ͷ
			response.setDateHeader("Expires", 0);// ��������ͷ
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

}
