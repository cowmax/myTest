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
	 * ���roleId��ȡPGroup����
	 */
	public List<PGroup> findByRoleId(int roleId) {
		pglis = pgdao.findByRoleId(roleId);
		return pglis;
	}

	/**
	 * �����û�����Ϣ
	 */
	public void saveGroup(PGroup group) {
		pgdao.save(group);
	}

	/**
	 * ɾ���û�����Ϣ
	 */
	public int deleteGroup(PGroup group) {
		int count = pgdao.delete(group);
		return count;
	}

	/**
	 * �޸��û�����Ϣ
	 */
	public PGroup mergeGroup(PGroup group) {
		pgroup = pgdao.merge(group);
		return pgroup;
	}

	/**
	 * ��ݽ�ɫid��ȡ��ɫ��Ϣ
	 */
	public PRole findRoleById(int roleId) {
		PRole role = pgdao.findRoleById(roleId);
		return role;
	}

	/**
	 * ����û�����ƻ�ȡ�û�����Ϣ
	 */
	public PGroup findGroupByName(String gname) {
		pglis = pgdao.findByProperty("groupName", gname);
		pgroup = null;
		if (pglis.size() > 0) {
			pgroup = pglis.get(0);
		}
		return pgroup;
	}

	/**
	 * ����û�����Ϣ��ȡ�û��鼯��
	 * 
	 * @param group
	 * @return
	 */
	public List findByExample(PGroup group) {
		pglis = pgdao.findByExample(group);
		return pglis;
	}

	/**
	 * ����û���Id���û�������ж��Ƿ��û�����Ϣ
	 */
	public boolean findByGidAndGname(int groupId, String groupName) {
		boolean flag = pgdao.findByGidAndGname(groupId, groupName);
		return flag;
	}

	/**
	 * ���������ҳ��ѯ��ȡ�û��鼯��
	 */
	public List<PGroup> getLisByPage(String page, String rows, String gname,
			String gdesc, int rid) {
		pglis = pgdao.getLisByPage(page, rows, gname, gdesc, rid);
		return pglis;
	}

	/**
	 * ��ȡ�����û�����Ϣ
	 */
	public List findAllGlis() {
		return pgdao.findAll();
	}

	/**
	 * ����û�ID��ȡ��Ӧ�û��鼯��
	 */
	public List findGroupByUserId(String userId) {
		return pgdao.findGroupByUserId(userId);
	}

	/**
	 * ��ȡ��ȥ�û�ID���Ӧ���û��鼯��
	 */
	public List<PGroup> getGroupExceptUgroup(String userId) {
		return pgdao.getGroupExceptUgroup(userId);
	}

}
