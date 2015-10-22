package com.actions;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bean.PRole;
import com.opensymphony.xwork2.ActionSupport;
import com.service.PRoleService;
import com.serviceimpl.UtilSupport;

@SuppressWarnings("serial")
public class PRoleAction extends ActionSupport {
	private PRoleService prbiz;
	private UtilSupport util;
	private PRole prole;
	private List<PRole> prlist;
	private int offset;			//当前页
	private int pageSize=10;
	private int totalcount;		// 总记录数
	private int totalpage; 		// 总页数

	private String rname;
	private String rdesc;

	private boolean flag;
	private String choose;
	private String msg;

	public PRoleService getPrbiz() {
		return prbiz;
	}
	public void setPrbiz(PRoleService prbiz) {
		this.prbiz = prbiz;
	}
	public UtilSupport getUtil() {
		return util;
	}
	public void setUtil(UtilSupport util) {
		this.util = util;
	}
	public PRole getProle() {
		return prole;
	}
	public void setProle(PRole prole) {
		this.prole = prole;
	}
	public List<PRole> getPrlist() {
		return prlist;
	}
	public void setPrlist(List<PRole> prlist) {
		this.prlist = prlist;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
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

	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getRdesc() {
		return rdesc;
	}
	public void setRdesc(String rdesc) {
		this.rdesc = rdesc;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
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
	 * 获取角色列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getRpLis(){
		try {
			HttpServletRequest request=ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			String ofst = request.getParameter("offset");
			if(ofst!=null){
				offset=Integer.valueOf(ofst);
			}else{
				offset=1;
			}
			totalcount = util.getTotalCount("from PRole");

			totalpage = totalcount % pageSize == 0 ? totalcount / pageSize
					: totalcount / pageSize + 1;
			if (offset < 1) {
				offset = 1;
			} else if (offset > totalpage) {
				offset = totalpage;
			}
			if(prlist!=null){
				prlist=null;
			}
			prlist=util.getPageList("from PRole", String.valueOf(offset), String.valueOf(pageSize));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "prshow";
	}

	/**
	 * 根据条件查询
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getRolisByOptions() throws Exception{
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

		String roleName = new String(request.getParameter("rname").trim().getBytes("ISO-8859-1"),"UTF-8");
		String roleDesc=new String(request.getParameter("rdesc").trim().getBytes("ISO-8859-1"),"UTF-8");
		if(roleName!=null){
			if(!roleName.equals("")){
				parms.put("roleName", roleName);
			}
		}
		if(roleDesc!=null){
			if(!roleDesc.equals("")){
				parms.put("roleDesc", roleDesc);
			}
		}
		prlist=util.getLisByOptions("PRole",String.valueOf(offset),String.valueOf(pageSize), parms," order by createDt desc");
		return "prshow";
	}
	/**
	 * 添加角色
	 * @return
	 */
	public String addRole(){
		prbiz.saveRole(prole);
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		String roleName=prole.getRoleName().trim();
		msg="角色 "+roleName+" ";
		session.setAttribute("msg", msg);
		return "add";
	}
	/**
	 * 判断角色名称是否已存在
	 * @return
	 */
	public String judgeName(){
		if(choose.equals("add")){
			prole = prbiz.findRoleByName(rname);
			if (null != prole) {
				flag = true;
			}else{
				flag=false;
			}
		}else{
			int roleid=prole.getRoleId();
			flag=prbiz.findByRidAndRname(roleid, rname.trim());
			if(flag){
				flag=false;
			}else{
				flag=true;
			}
		}
		return SUCCESS;
	}
	/**
	 * 删除角色信息
	 * @return
	 */
	public String delRole(){
		prole=prbiz.findRoleByName(rname.trim());
		int count=prbiz.deleteRole(prole);
		if (count >0) {
			flag = true;
		} else {
			flag = false;
		}
		return SUCCESS;
	}

	public String editInfo(){
		try {
			prole=prbiz.findRoleByName(new String(rname.trim().getBytes("iso-8859-1"),"utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "edit";
	}
	public String mergeRole(){
		prole=prbiz.mergeRole(prole);
		if(prole!=null){
			flag=true;
		}else{
			flag=false;
		}
		return SUCCESS;
	}
	
}
