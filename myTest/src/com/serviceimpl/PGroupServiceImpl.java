package com.serviceimpl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.bean.PGroup;
import com.bean.PRole;
import com.dao.PGroupDao;
import com.service.PGroupService;

public class PGroupServiceImpl implements PGroupService {

	private PGroupDao pgdao;
	private List<PGroup> pglis;
	private PGroup pgroup;
	private SessionFactory sessionFactory;
	private Query query;
	
	public PGroupDao getPgdao() {
		return pgdao;
	}
	public void setPgdao(PGroupDao pgdao) {
		this.pgdao = pgdao;
	}
	public List<PGroup> getPglis() {
		return pglis;
	}
	public void setPglis(List<PGroup> pglis) {
		this.pglis = pglis;
	}
	
	public PGroup getPgroup() {
		return pgroup;
	}
	public void setPgroup(PGroup pgroup) {
		this.pgroup = pgroup;
	}
	
	/**
	 * 根据roleId获取PGroup集合
	 */
	public List<PGroup> findByRoleId(int roleId) {
		pglis=pgdao.findByRoleId(roleId);
		return pglis;
	}
	
	/**
	 * 保存用户组信息
	 */
	public void saveGroup(PGroup group) {
		pgdao.save(group);
	}
	
	/**
	 * 删除用户组信息
	 */
	public int deleteGroup(PGroup group) {
		int count=pgdao.delete(group);
		return count;
	}
	
	/**
	 * 修改用户组信息
	 */
	public PGroup mergeGroup(PGroup group) {
		pgroup=pgdao.merge(group);
		return pgroup;
	}
	
	/**
	 * 根据角色id获取角色信息
	 */
	public PRole findRoleById(int roleId) {
		PRole role=pgdao.findRoleById(roleId);
		return role;
	}
	
	/**
	 * 根绝用户组名称获取用户组信息
	 */
	public PGroup findGroupByName(String gname) {
		pglis=pgdao.findByProperty("groupName", gname);
		pgroup=null;
		if(pglis.size()>0){
			pgroup=pglis.get(0);
		}
		return pgroup;
	}
	
	/**
	 * 根据用户组信息获取用户组集合
	 * @param group
	 * @return
	 */
	public List findByExample(PGroup group) {
		pglis=pgdao.findByExample(group);
		return pglis;
	}
	
	/**
	 * 根据用户组Id和用户组名称判断是否用户组信息
	 */
	public boolean findByGidAndGname(int groupId,String groupName) {
		boolean flag=pgdao.findByGidAndGname(groupId,groupName);
		return flag;
	}
	
	/**
	 * 根据条件分页查询获取用户组集合
	 */
	public List<PGroup> getLisByPage(String page, String rows, String gname,
			String gdesc, int rid) {
		pglis=pgdao.getLisByPage(page, rows, gname, gdesc, rid);
		return pglis;
	}
	
	/**
	 * 获取所有用户组信息
	 */
	public List findAllGlis() {
		return pgdao.findAll();
	}
	
	/**
	 * 根据用户ID获取对应用户组集合
	 */
	public List findGroupByUserId(String userId) {
		return pgdao.findGroupByUserId(userId);
	}
	
	/**
	 * 获取除去用户ID所对应的用户组集合
	 */
	public List<PGroup> getGroupExceptUgroup(String userId) {
		return pgdao.getGroupExceptUgroup(userId);
	}

}
