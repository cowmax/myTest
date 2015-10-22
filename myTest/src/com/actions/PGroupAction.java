package com.actions;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.junit.runner.Request;

import com.bean.PGroup;
import com.bean.PRole;
import com.opensymphony.xwork2.ActionSupport;
import com.service.PGroupService;
import com.service.PRoleService;
import com.serviceimpl.PRoleServiceImpl;
import com.serviceimpl.UtilSupport;

public class PGroupAction extends ActionSupport {
	private PGroupService pgbiz;
	private UtilSupport util;
	private boolean flag;
	private List<PGroup> pglis;
	private int roleId;
	private PGroup pgroup;
	private String gname;

	private int offset;			//当前页
	private int pageSize=10;
	private int totalcount;		// 总记录数
	private int totalpage; 		// 总页数

	private List<PRole> rolis;
	private PRoleService prbiz;
	private String choose;
	private String msg;

	public PGroupService getPgbiz() {
		return pgbiz;
	}

	public void setPgbiz(PGroupService pgbiz) {
		this.pgbiz = pgbiz;
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

	public List<PGroup> getPglis() {
		return pglis;
	}

	public void setPglis(List<PGroup> pglis) {
		this.pglis = pglis;
	}
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public PGroup getPgroup() {
		return pgroup;
	}
	public void setPgroup(PGroup pgroup) {
		this.pgroup = pgroup;
	}
	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
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

	public PRoleService getPrbiz() {
		return prbiz;
	}

	public void setPrbiz(PRoleService prbiz) {
		this.prbiz = prbiz;
	}

	public String getChoose() {
		return choose;
	}

	public void setChoose(String choose) {
		this.choose = choose;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * 根据roleId判断角色是否可以删除
	 * @return
	 */
	public String findByRoleId(){
		pglis=pgbiz.findByRoleId(roleId);
		if(pglis.size()>0){
			flag=true;
		}else{
			flag=false;
		}
		return SUCCESS;
	}

	public String getPglist(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			rolis=prbiz.rolelis();
			request.setAttribute("rolis", rolis);
			
			request.setCharacterEncoding("UTF-8");
			String ofst = request.getParameter("offset");
			if(ofst!=null){
				offset=Integer.valueOf(ofst);
			}else{
				offset=1;
			}
			totalcount = util.getTotalCount("from PGroup order by create_dt desc");

			totalpage = totalcount % pageSize == 0 ? totalcount / pageSize
					: totalcount / pageSize + 1;
			if (offset < 1) {
				offset = 1;
			} else if (offset > totalpage) {
				offset = totalpage;
			}
			if(pglis!=null){
				pglis=null;
			}
			pglis=util.getPageList("from PGroup", String.valueOf(offset), String.valueOf(pageSize));
			for (int i = 0; i < pglis.size(); i++) {
				int roleId=pglis.get(i).getRoleId().getRoleId();
				PRole role=pgbiz.findRoleById(roleId);
				pglis.get(i).setRoleId(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "pgshow";
	}

	/**
	 * 根据条件查询
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getGlisByOptions() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		rolis=prbiz.rolelis();
		request.setAttribute("rolis", rolis);
		
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

		String groupName = new String(request.getParameter("gname").trim().getBytes("ISO-8859-1"),"UTF-8");
		String groupDesc=new String(request.getParameter("gdesc").trim().getBytes("ISO-8859-1"),"UTF-8");
		roleId=Integer.parseInt(request.getParameter("roleId"));
		pglis=pgbiz.getLisByPage(String.valueOf(offset), String.valueOf(pageSize), groupName, groupDesc, roleId);
		for (int i = 0; i < pglis.size(); i++) {
			int roleId=pglis.get(i).getRoleId().getRoleId();
			PRole role=pgbiz.findRoleById(roleId);
			pglis.get(i).setRoleId(role);
		}
		return "pgshow";
	}
	/**
	 * 获取所有角色信息
	 * @return
	 */
	public String getAllRole(){
		HttpServletRequest request = ServletActionContext.getRequest();
		rolis=prbiz.rolelis();
		request.setAttribute("rolis", rolis);
		return "roles";
	}

	/**
	 * 异步验证角色组名称
	 * @return
	 */
	public String judgeGname(){
		if(choose.equals("add")){
			pgroup = pgbiz.findGroupByName(gname.trim());
			if (null != pgroup) {
				flag = true;
			}else{
				flag=false;
			}
		}else{
			int groupId=pgroup.getGroupId();
			flag=pgbiz.findByGidAndGname(groupId,gname.trim());
			if(flag){
				flag=false;
			}else{
				flag=true;
			}
		}
		return SUCCESS;
	}

	public String judgeGandRname(){
		if(choose.equals("add")){
			pgroup = pgbiz.findGroupByName(gname.trim());
			int roleinfo=pgroup.getRoleId().getRoleId();
			if(roleinfo==roleId){
				flag=false;
			}else{
				flag=false;
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 增加角色组信息
	 * @return
	 */
	public String addGroup(){
		Timestamp ts = new Timestamp(System.currentTimeMillis()); 
		Date date= new Date();//创建一个时间对象，获取到当前的时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间显示格式
		String str = sdf.format(date);//将当前时间格式化为需要的类型
		ts = Timestamp.valueOf(str); 
		
		pgroup.setCreateDt(ts);
		pgroup.setLastDt(ts);
		pgbiz.saveGroup(pgroup);
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		String groupName=pgroup.getGroupName();
		msg="角色 "+groupName+" ";
		session.setAttribute("msg", msg);
		return "add";
	}
	
	/**
	 * 删除用户组
	 * @return
	 */
	public String delGuoup(){
		pgroup=pgbiz.findGroupByName(gname.trim());
		int count=pgbiz.deleteGroup(pgroup);
		if (count >0) {
			flag = true;
		} else {
			flag = false;
		}
		return SUCCESS;
	}
	
	/**
	 * 编辑用户组信息
	 * @return
	 */
	public String editInfo(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			rolis=prbiz.rolelis();
			request.setAttribute("rolis", rolis);
			pgroup=pgbiz.findGroupByName(new String(gname.trim().getBytes("iso-8859-1"),"utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "edit";
	}
	
	/**
	 * 修改用户组信息
	 * @return
	 */
	public String mergeGroupInfo(){
		Timestamp ts = new Timestamp(System.currentTimeMillis()); 
		Date date= new Date();//创建一个时间对象，获取到当前的时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置时间显示格式
		String str = sdf.format(date);//将当前时间格式化为需要的类型
		ts = Timestamp.valueOf(str); 
		pgroup.setLastDt(ts);
		pgroup.setRoleId(pgbiz.findRoleById(roleId));
		pgroup=pgbiz.mergeGroup(pgroup);
		if(pgroup!=null){
			flag=true;
		}else{
			flag=false;
		}
		return SUCCESS;
	}
}
