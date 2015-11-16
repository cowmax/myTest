package com.actions;

import java.io.UnsupportedEncodingException; 
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.SessionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import com.bean.PRole;
import com.bean.PUser;
import com.opensymphony.xwork2.ActionSupport;
import com.service.PUserService;
import com.serviceimpl.UtilSupport;
import com.util.MD5Util;

@SuppressWarnings("serial")
public class PUserAction extends ActionSupport {
	private UtilSupport util;
	private PUserService pubiz;
	private List<PUser> ulis;		//PUser集合
	private PUser pu;
	private String userId;
	private String msg;

	private int offset;			//当前页
	private int pageSize=5;
	private int totalcount;		// 总记录数
	private int totalpage; 		// 总页数

	private PUser euser;
	private boolean flag;
	private String uPwd;
	
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

	/**
	 * 获取所有用户信息
	 * @return
	 * @throws Exception 
	 */
	public String getAll() throws Exception{
		ulis=pubiz.allUsers();
		return "all";
	}

	/**
	 * 根据用户ID获取用户详细信息
	 * @return
	 */
	public String getDetail(){
		pu=pubiz.findUserById(userId);
		return "detail";
	}

	/**
	 * 根据用户ID删除用户信息
	 * @return
	 */
	public String delUser(){
		pu=pubiz.findUserById(userId);
		pubiz.delUser(pu);
		return "del";
	}
	/**
	 * 添加用户信息
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String addUser(){
		Timestamp ts = new Timestamp(System.currentTimeMillis()); 
		Date date= new Date();//创建一个时间对象，获取到当前的时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间显示格式
		String str = sdf.format(date);//将当前时间格式化为需要的类型
		ts = Timestamp.valueOf(str); 
		
		pu.setCreateDt(ts);
		pu.setLastDt(ts);
		uPwd=MD5Util.string2MD5(pu.getUserPwd().trim());  //md5加密
		pu.setUserPwd(uPwd);
		pubiz.saveUser(pu);
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		String userId=pu.getUserId();
		msg="用户 "+userId+" ";
		session.setAttribute("msg", msg);
		return "add";
	}
	/**
	 * 编辑用户信息
	 * @return
	 */
	public String editInfo(){
		pu=pubiz.findUserById(userId);
		return "edit";
	}

	/**
	 * 修改用户信息
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
					uPwd=MD5Util.string2MD5(new String(uPwd.trim().getBytes("iso-8859-1"), "utf-8"));  //md5加密
					pu.setUserPwd(uPwd);
				}
			}

			Timestamp ts = new Timestamp(System.currentTimeMillis()); 
			Date date= new Date();//创建一个时间对象，获取到当前的时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间显示格式
			String str = sdf.format(date);//将当前时间格式化为需要的类型
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
	 * 判断ID是否已存在
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
		String ofst = request.getParameter("offset");
		
		totalcount = util.getTotalCount("select * from p_user");

		totalpage = totalcount % pageSize == 0 ? totalcount / pageSize
				: totalcount / pageSize + 1;
		
		String userId = request.getParameter("quid");
		if(userId!=null){
			userId = new String(userId.trim().getBytes("ISO-8859-1"),"UTF-8");
		}
		String userName=request.getParameter("quname");
		if(userName!=null){
			userName=new String(userName.trim().getBytes("ISO-8859-1"),"UTF-8");
		}
		StringBuffer sql=new StringBuffer("select * from p_user where 0=0 ");
		if(userId!=null){
			if(!userId.equals("")){
				sql.append(" and user_id like '%"+userId+"%'");
			}
		}
		if(userName!=null){
			if(!userName.equals("")){
				sql.append(" and user_name like '%"+userName+"%'");
			}
		}
		sql.append(" order by create_dt desc");
		offset = getPageOffset();
		ulis=util.getPageListBySql(sql.toString(), String.valueOf(offset), String.valueOf(pageSize),new Class[]{PUser.class});
		return "all";
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
	 * 验证码验证
	 * @return
	 */
	public String checkCode(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String code=request.getParameter("code");
		if(code.equalsIgnoreCase((String)request.getSession().getAttribute("str"))){
			flag=true;
		}else{
			flag=false;
		}
		return SUCCESS;
	}
	
	/**
	 * 登录验证
	 * @return
	 */
	public String loginCheck(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		String userId=request.getParameter("userId");
		String pwd=request.getParameter("userPwd");
		pu=pubiz.userLogin(userId);
		if(pu!=null){
			String upwd=pu.getUserPwd();
			pwd=MD5Util.string2MD5(pwd);
			if(upwd.equals(pwd.trim())){
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
	 * 检测旧密码是否输入正确
	 * @return
	 */
	public String pwdCheck(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		String pwd=request.getParameter("oldPwd");
		String upwd=pu.getUserPwd();
		pwd=MD5Util.string2MD5(pwd);
		if(upwd.equals(pwd.trim())){
			flag=true;
		}else{
			flag=false;
		}
		return SUCCESS;
	}
	
	/**
	 * 修改登录用户密码
	 * @return
	 */
	public String editPwd(){
		try {
			HttpServletRequest request=ServletActionContext.getRequest();
			uPwd=new String(request.getParameter("newPwd").trim().getBytes("iso-8859-1"),"utf-8");
			uPwd=MD5Util.string2MD5(new String(uPwd.trim().getBytes("iso-8859-1"), "utf-8"));  //md5加密
			pu.setUserPwd(uPwd);

			Timestamp ts = new Timestamp(System.currentTimeMillis()); 
			Date date= new Date();//创建一个时间对象，获取到当前的时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间显示格式
			String str = sdf.format(date);//将当前时间格式化为需要的类型
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
}
