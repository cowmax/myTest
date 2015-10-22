package com.actions;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private MD5Util md5util;
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

	public MD5Util getMd5util() {
		return md5util;
	}
	public void setMd5util(MD5Util md5util) {
		this.md5util = md5util;
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
		uPwd=md5util.string2MD5(pu.getUserPwd().trim());  //md5加密
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
					uPwd=md5util.string2MD5(new String(uPwd.trim().getBytes("iso-8859-1"), "utf-8"));  //md5加密
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

	/**
	 * 分页显示
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findbypage(){
		try {
			HttpServletRequest request=ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			String ofst = request.getParameter("offset");
			if(ofst!=null){
				offset=Integer.valueOf(ofst);
			}else{
				offset=1;
			}
			totalcount = util.getTotalCount("from PUser");

			totalpage = totalcount % pageSize == 0 ? totalcount / pageSize
					: totalcount / pageSize + 1;
			if (offset < 1) {
				offset = 1;
			} else if (offset > totalpage) {
				offset = totalpage;
			}
			if(ulis!=null){
				ulis=null;
			}
			ulis=util.getPageList("from PUser order by createDt desc", String.valueOf(offset), String.valueOf(pageSize));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "all";
	}
	@SuppressWarnings("unchecked")
	public String getByOptions() throws Exception {
		Map<String, String>parms=new HashMap<String, String>();
		HttpServletRequest request=ServletActionContext.getRequest();
		String ofst = request.getParameter("offset");
		if(ofst!=null){
			offset=Integer.valueOf(ofst);
		}else{
			offset=1;
		}
		if (offset < 1) {
			offset = 1;
		} else if (offset > totalpage) {
			offset = totalpage;
		}
		
		String userId = new String(request.getParameter("quid").trim().getBytes("ISO-8859-1"),"UTF-8");
		String userName=new String(request.getParameter("quname").trim().getBytes("ISO-8859-1"),"UTF-8");
		if(userId!=null){
			if(!userId.equals("")){
				parms.put("userId", userId);
			}
		}
		if(userName!=null){
			if(!userName.equals("")){
				parms.put("userName", userName);
			}
		}
		ulis=util.getLisByOptions("PUser",String.valueOf(offset),String.valueOf(pageSize), parms," order by createDt desc");
		return "all";
	}
}
