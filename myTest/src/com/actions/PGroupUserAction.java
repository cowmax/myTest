package com.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.bean.PGroup;
import com.bean.PGroupUser;
import com.bean.PRole;
import com.bean.PUser;
import com.opensymphony.xwork2.ActionSupport;
import com.service.PGroupService;
import com.service.PGroupUserService;
import com.serviceimpl.UtilSupport;

public class PGroupUserAction extends ActionSupport {

	private PGroupUserService pgubiz;
	private PGroupService pgbiz;
	private UtilSupport util;

	private List<PGroupUser> pgulis;
	private List<PGroup> pglis;
	private PGroupUser pgu;
	private int gid;
	private boolean flag;

	private int offset;			//当前页
	private int pageSize=10;
	private int totalcount;		// 总记录数
	private int totalpage; 		// 总页数

	private int groupId;
	private String userId;
	private String userName;

	public PGroupUserService getPgubiz() {
		return pgubiz;
	}

	public void setPgubiz(PGroupUserService pgubiz) {
		this.pgubiz = pgubiz;
	}

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

	public List<PGroupUser> getPgulis() {
		return pgulis;
	}

	public void setPgulis(List<PGroupUser> pgulis) {
		this.pgulis = pgulis;
	}

	public List<PGroup> getPglis() {
		return pglis;
	}

	public void setPglis(List<PGroup> pglis) {
		this.pglis = pglis;
	}

	public PGroupUser getPgu() {
		return pgu;
	}

	public void setPgu(PGroupUser pgu) {
		this.pgu = pgu;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
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

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 通过用户组Id获取信息
	 * @return
	 */
	public String findByGroupId(){
		List list=pgubiz.findPguByGid(gid);
		if(list.size()>0){
			flag=true;
		}else{
			flag=false;
		}
		return SUCCESS;
	}

	/**
	 * 分页显示
	 * @return
	 */
	public String findByPage(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			pglis=pgbiz.findAllGlis();
			
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
			if(pgulis!=null){
				pgulis=null;
			}
			pgulis=util.getPageList("from PGroupUser", String.valueOf(offset), String.valueOf(pageSize));
			for (int i = 0; i < pgulis.size(); i++) {
				int groupId=pgulis.get(i).getGroupId().getGroupId();
				String userId=pgulis.get(i).getUserId().getUserId();
				PGroup group=pgubiz.findGroupById(groupId);
				PUser user=pgubiz.findUserById(userId);
				pgulis.get(i).setGroupId(group);
				pgulis.get(i).setUserId(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "pgushow";
	}

	/**
	 * 根据条件查询
	 * @return
	 */
	public String findByOptions(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();

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

			String userId = new String(request.getParameter("userId").trim().getBytes("ISO-8859-1"),"UTF-8");
			String userName=new String(request.getParameter("userName").trim().getBytes("ISO-8859-1"),"UTF-8");
			groupId=Integer.parseInt(request.getParameter("groupId"));
			pgulis=pgubiz.findByOptions(String.valueOf(offset), String.valueOf(pageSize), userId, userName, groupId);
			for (int i = 0; i < pgulis.size(); i++) {
				int groupId=pgulis.get(i).getGroupId().getGroupId();
				String uid=pgulis.get(i).getUserId().getUserId();
				PGroup group=pgubiz.findGroupById(groupId);
				PUser user=pgubiz.findUserById(uid);
				pgulis.get(i).setGroupId(group);
				pgulis.get(i).setUserId(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "pgushow";
	}
	
	public String addGroupUser(){
		return "add";
	}
}
