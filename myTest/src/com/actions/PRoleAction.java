package com.actions;

import java.io.UnsupportedEncodingException;
import java.util.List;

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
	private int offset;			//��ǰҳ
	private int pageSize=10;
	private int totalcount;		// �ܼ�¼��
	private int totalpage; 		// ��ҳ��

	private String rname;
	private String rdesc;

	private boolean flag;
	private String choose;
	private String msg;
	
	private String refreshList;
	private String titleName;

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

	/**
	 * ����������ѯ
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getRolisByOptions() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		StringBuffer sql=new StringBuffer("select * from p_role where 0=0 ");
		
		this.rname = request.getParameter("rname");
		if(rname!=null&&!rname.isEmpty()){
			rname = new String(rname.trim().getBytes("ISO-8859-1"),"UTF-8");
			sql.append(" and role_name like '%"+rname+"%'");
		}
		
		this.rdesc=request.getParameter("rdesc");
		if(rdesc!=null&&!rdesc.isEmpty()){
			rdesc=new String(rdesc.trim().getBytes("ISO-8859-1"),"UTF-8");
			sql.append(" and role_desc like '%"+rdesc+"%'");
		}
		
		totalcount = util.getTotalCount(sql.toString());

		totalpage = totalcount % pageSize == 0 ? totalcount / pageSize
				: totalcount / pageSize + 1;
		offset = getPageOffset();
		
		prlist=util.getPageListBySql(sql.toString(), String.valueOf(offset), String.valueOf(pageSize),new Class[]{PRole.class});
		
		return "prshow";
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
	 * ��ӽ�ɫ
	 * @return
	 */
	public String addRole(){
		prbiz.saveRole(prole);
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		String roleName = prole.getRoleName().trim();
		
		refreshList = "prolegetRolisByOptions"; 
		titleName = "�û���ɫ";
		
		msg = "��ɫ " + roleName + " ";
		session.setAttribute("msg", msg);
		return "add";
	}
	/**
	 * �жϽ�ɫ�����Ƿ��Ѵ���
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
	 * ɾ����ɫ��Ϣ
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
