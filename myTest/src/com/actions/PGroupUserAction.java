package com.actions;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.bean.PGroup;
import com.bean.PGroupUser;
import com.bean.PUser;
import com.opensymphony.xwork2.ActionSupport;
import com.service.PGroupService;
import com.service.PGroupUserService;
import com.serviceimpl.UtilSupport;


@SuppressWarnings("serial")
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

	// 类构造函数：初始化类成员
	public PGroupUserAction(){
		pgulis = new ArrayList<PGroupUser>();
	}

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

	// 填充 PGroupUser 对像 List
	private void fillPgList(List<Object[]> resultSet) {
		pgulis.clear();

		for (Object[] r : resultSet) 
		{
			PGroupUser gu = new PGroupUser();
			gu.setUserId((PUser)r[0]);
			gu.setGroupId((PGroup)r[1]);
			pgulis.add(gu);
		}
	}

	/**
	 * 根据条件查询
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findByOptions(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			pglis=pgbiz.findAllGlis();

			StringBuffer sql=new StringBuffer("select * from p_user u left join p_group_user gu on gu.user_id=u.user_id  left join p_group g on gu.group_id=g.group_id where 0=0 ");

			this.userId = request.getParameter("userId");
			if(userId!=null&&!userId.isEmpty()){
				userId = new String(userId.trim().getBytes("ISO-8859-1"),"UTF-8");
				sql.append(" and u.user_id like '%"+userId+"%'");
			}

			this.userName=request.getParameter("userName");
			if(userName!=null&&!userName.isEmpty()){
				userName=new String(userName.trim().getBytes("ISO-8859-1"),"UTF-8");
				sql.append(" and u.user_name like '%"+userName+"%'");
			}

			String group=request.getParameter("groupId");

			if(group!=null){
				this.groupId=Integer.parseInt(group);
			}else{
				this.groupId=-1;
			}

			if(groupId!=-1){
				sql.append(" and g.group_id = "+groupId+"");
			}

			totalcount = util.getTotalCount(sql.toString());

			totalpage = totalcount % pageSize == 0 ? totalcount / pageSize
					: totalcount / pageSize + 1;

			offset = getPageOffset();

			List<Object[]> resultSet = util.getPageListBySql(sql.toString(),String.valueOf(offset), String.valueOf(pageSize),new Class[]{PUser.class, PGroup.class});

			fillPgList(resultSet);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "pgushow";
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

	public String addGroupUserInfo(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			groupId=Integer.parseInt(request.getParameter("groupId"));
			userId = new String(request.getParameter("userId").trim().getBytes("ISO-8859-1"),"UTF-8");
			pgubiz.savePgu(groupId,userId);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "edit";
	}

	public String delGroupUserInfo(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			groupId=Integer.parseInt(request.getParameter("groupId"));
			userId = new String(request.getParameter("userId").trim().getBytes("ISO-8859-1"),"UTF-8");
			pgubiz.deletPgu(groupId,userId);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "edit";
	}
}
