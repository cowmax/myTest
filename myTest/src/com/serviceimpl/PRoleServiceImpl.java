package com.serviceimpl;

import java.util.List;

import com.bean.PRole;
import com.dao.PRoleDao;
import com.service.PRoleService;

/**
 * 角色管理
 * @author Administrator
 *
 */
public class PRoleServiceImpl implements PRoleService {
	private PRoleDao prdao;
	private List<PRole> rolelis;
	private PRole prole;
	private int count;
	
	public PRole getProle() {
		return prole;
	}

	public void setProle(PRole prole) {
		this.prole = prole;
	}

	public List<PRole> getRolelis() {
		return rolelis;
	}

	public void setRolelis(List<PRole> rolelis) {
		this.rolelis = rolelis;
	}

	public PRoleDao getPrdao() {
		return prdao;
	}

	public void setPrdao(PRoleDao prdao) {
		this.prdao = prdao;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * 添加角色
	 */
	public void saveRole(PRole role) {
		prdao.save(role);
	}

	/**
	 * 删除角色
	 */
	public int deleteRole(PRole role) {
		count=prdao.delete(role);
		return count;
	}

	/**
	 * 修改角色
	 */
	public PRole mergeRole(PRole role) {
		prole=prdao.merge(role);
		return prole;
	}

	@SuppressWarnings("rawtypes")
	public PRole findRoleByName(String roleName) {
		prole=null;
		List prlis=prdao.findByRoleName(roleName);
		if(prlis.size()>0){
			for (int i = 0; i < prlis.size(); i++) {
				prole=(PRole)prlis.get(i);
			}
		}
		return prole;
	}

	public PRole findRoleById(int roleId) {
		prole=prdao.findById(roleId);
		return prole;
	}

	public List<PRole> rolelis() {
		rolelis=prdao.findAll();
		return rolelis;
	}

	public boolean findByRidAndRname(int roleId, String roleName) {
		boolean flag=prdao.findByRidAndRname(roleId,roleName);
		return flag;
	}

}
