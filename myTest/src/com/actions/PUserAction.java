package com.actions;

import java.io.UnsupportedEncodingException; 
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import com.bean.PUser;
import com.opensymphony.xwork2.ActionSupport;
import com.service.PUserService;
import com.serviceimpl.UtilSupport;
import com.util.MD5Util;

@SuppressWarnings("serial")
public class PUserAction extends ActionSupport {
	private UtilSupport util;
	private PUserService pubiz;
	private List<PUser> ulis;		//PUser����
	private PUser pu;
	private String userId;
	private String msg;

	private int offset;			//��ǰҳ
	private int pageSize=10;
	private int totalcount;		// �ܼ�¼��
	private int totalpage; 		// ��ҳ��

	private PUser euser;
	private boolean flag;
	private String uPwd;
	
	//��ѯ����
	private String quid;
	private String quname;
	
	private String refreshList;
	private String titleName;
	
	private String code;
	private String userPwd;

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
	
	public String getuPwd() {
		return uPwd;
	}
	
	public void setuPwd(String uPwd) {
		this.uPwd = uPwd;
	}
	
	public boolean isFlag() {
		return flag;
	}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public PUser getEuser() {
		return euser;
	}
	
	public void setEuser(PUser euser) {
		this.euser = euser;
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
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public PUser getPu() {
		return pu;
	}
	
	public void setPu(PUser pu) {
		this.pu = pu;
	}
	
	public PUserService getPubiz() {
		return pubiz;
	}
	
	public void setPubiz(PUserService pubiz) {
		this.pubiz = pubiz;
	}
	
	public List<PUser> getUlis() {
		return ulis;
	}
	
	public void setUlis(List<PUser> ulis) {
		this.ulis = ulis;
	}
	
	public String getQuid() {
		return quid;
	}
	
	public void setQuid(String quid) {
		this.quid = quid;
	}
	
	public String getQuname() {
		return quname;
	}
	
	public void setQuname(String quname) {
		this.quname = quname;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	/**
	 * ��ȡ�����û���Ϣ
	 * @return
	 * @throws Exception 
	 */
	public String getAll() throws Exception{
		ulis=pubiz.allUsers();
		return "all";
	}

	/**
	 * �����û�ID��ȡ�û���ϸ��Ϣ
	 * @return
	 */
	public String getDetail(){
		pu=pubiz.findUserById(userId);
		return "detail";
	}

	/**
	 * �����û�IDɾ���û���Ϣ
	 * @return
	 */
	public String delUser(){
		pu=pubiz.findUserById(userId);
		pubiz.delUser(pu);
		return "del";
	}
	/**
	 * ����û���Ϣ
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String addUser(){
		Timestamp ts = new Timestamp(System.currentTimeMillis()); 
		Date date= new Date();//����һ��ʱ����󣬻�ȡ����ǰ��ʱ��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//����ʱ����ʾ��ʽ
		String str = sdf.format(date);//����ǰʱ���ʽ��Ϊ��Ҫ������
		ts = Timestamp.valueOf(str); 

		pu.setCreateDt(ts);
		pu.setLastDt(ts);
		uPwd=MD5Util.string2MD5(pu.getUserPwd().trim());  //md5����
		pu.setUserPwd(uPwd);
		pubiz.saveUser(pu);
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		String userId=pu.getUserId();
		msg="�û� "+userId+" ";
		session.setAttribute("msg", msg);
		refreshList = "pusergetByOptions";
		titleName = "�û���Ϣ";
		return "add";
	}
	/**
	 * �༭�û���Ϣ
	 * @return
	 */
	public String editInfo(){
		pu=pubiz.findUserById(userId);
		return "edit";
	}

	/**
	 * �޸��û���Ϣ
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String mergeUser(){
		try {
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpServletResponse response=ServletActionContext.getResponse();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			uPwd=new String(request.getParameter("uPwd").trim().getBytes("iso-8859-1"),"utf-8");
			//			uPwd=new String(uPwd.getBytes("iso-8859-1"),"utf-8");
			if(uPwd!=null){

				if(uPwd.equals("******")){
					pu.setUserPwd(new String(pu.getUserPwd().trim().getBytes("iso-8859-1"), "utf-8"));
				}else{
					uPwd=MD5Util.string2MD5(new String(uPwd.trim().getBytes("iso-8859-1"), "utf-8"));  //md5����
					pu.setUserPwd(uPwd);
				}
			}

			Timestamp ts = new Timestamp(System.currentTimeMillis()); 
			Date date= new Date();//����һ��ʱ����󣬻�ȡ����ǰ��ʱ��
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//����ʱ����ʾ��ʽ
			String str = sdf.format(date);//����ǰʱ���ʽ��Ϊ��Ҫ������
			ts = Timestamp.valueOf(str); 
			pu.setLastDt(ts);
			pubiz.mergeUser(pu);
			flag=true;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			flag=false;
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * �ж�ID�Ƿ��Ѵ���
	 * @return
	 */
	public String judgeId(){
		pu = pubiz.findUserById(userId);
		if (null != pu) {
			flag = true;
		} else {
			flag = false;
		}
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getByOptions() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();

		StringBuffer sql=new StringBuffer("select * from p_user where 0=0 ");
		
		this.quid = request.getParameter("quid");
		if(quid!=null&&!quid.isEmpty()){
			quid = new String(quid.trim().getBytes("ISO-8859-1"),"UTF-8");
			sql.append(" and user_id like '%"+quid+"%'");
		}
		this.quname=request.getParameter("quname");
		if(quname!=null&&!quname.isEmpty()){
			quname=new String(quname.trim().getBytes("ISO-8859-1"),"UTF-8");
			sql.append(" and user_name like '%"+quname+"%'");
		}
	
		sql.append(" order by create_dt desc");

		totalcount = util.getTotalCount(sql.toString());

		totalpage = totalcount % pageSize == 0 ? totalcount / pageSize
				: totalcount / pageSize + 1;
		
		offset = getPageOffset();
		
		ulis=util.getPageListBySql(sql.toString(), String.valueOf(offset), String.valueOf(pageSize),new Class[]{PUser.class});
		return "all";
	}

	// Added by JSL : ��ȡ��ҳƫ����(ʵ�����ǽ�Ҫ������ҳ���ҳ������ҳ������ 0 ��ʼ)
	private int getPageOffset() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String ofst = request.getParameter("offset");
		int idx = 0;
		if(ofst!=null){
			idx = Integer.valueOf(ofst);
			idx = idx < 0 ? 0 : idx;                        // ������һҳʱ�����ٷ�ҳ
			idx = idx >= totalpage ? (totalpage-1) : idx;	// �������һҳʱ�����ٷ�ҳ		
		}
		return idx;
	}

	/**
	 * ��֤����֤
	 * @return
	 */
	public String checkCode(){
		HttpServletRequest request=ServletActionContext.getRequest();
		code=request.getParameter("code");
		
		if(code.equalsIgnoreCase((String)request.getSession().getAttribute("str"))){
			flag=true;
		}else{
			flag=false;
		}
		return SUCCESS;
	}

	/**
	 * ��¼��֤
	 * @return
	 */
	public String loginCheck(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		//��ȡ�û�ID������
		String userId=request.getParameter("userId");
		userPwd=request.getParameter("userPwd");
		
		//�����û�ID��ȡ�û���Ϣ
		pu=pubiz.userLogin(userId);
		
		//�ж��û��Ƿ���ڲ����ж��û������������û������Ƿ�һ��
		if(pu!=null){
			String upwd=pu.getUserPwd();
			userPwd=MD5Util.string2MD5(userPwd);
			if(upwd.equals(userPwd.trim())){
				flag=true;
				session.setAttribute("pu", pu);
			}else{
				flag=false;
			}
		}else{
			flag=false;
		}
		return SUCCESS;
	}
	/**
	 * ���������Ƿ�������ȷ
	 * @return
	 */
	public String pwdCheck(){
		HttpServletRequest request=ServletActionContext.getRequest();

		//��ȡҳ������ľ�����
		String pwd=request.getParameter("oldPwd");
		//�����û�ҳ������ľ�����
		pwd=MD5Util.string2MD5(pwd);
		//��ȡ�û��Լ���������
		String upwd=pu.getUserPwd();

		//�жϾ����ܺ�ҳ������ľ��������û����ݿ��е�����
		if(upwd.equals(pwd.trim())){
			flag=true;
		}else{
			flag=false;
		}
		return SUCCESS;
	}

	/**
	 * �޸ĵ�¼�û�����
	 * @return
	 */
	public String editPwd(){
		try {
			HttpServletRequest request=ServletActionContext.getRequest();
			uPwd=new String(request.getParameter("newPwd").trim().getBytes("iso-8859-1"),"utf-8");
			uPwd=MD5Util.string2MD5(new String(uPwd.trim().getBytes("iso-8859-1"), "utf-8"));  //md5����
			pu.setUserPwd(uPwd);

			Timestamp ts = new Timestamp(System.currentTimeMillis()); 
			Date date= new Date();//����һ��ʱ����󣬻�ȡ����ǰ��ʱ��
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//����ʱ����ʾ��ʽ
			String str = sdf.format(date);//����ǰʱ���ʽ��Ϊ��Ҫ������
			ts = Timestamp.valueOf(str); 
			pu.setLastDt(ts);
			pubiz.mergeUser(pu);
			flag=true;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			flag=false;
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * ע����ǰ�û�
	 * @return
	 */
	public String exit(){
		//��ȡsession����
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		PUser loginuser=(PUser)session.getAttribute("pu");
		
		//���session�����õ�pu����
		session.removeAttribute("pu");
		//�ر�session
		session.invalidate();
		return "login";
	}
}
