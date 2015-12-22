package com.dao;

import java.util.List;

import com.bean.PGroup;
import com.bean.PRole;

public interface PGroupDao {

	public abstract void save(PGroup transientInstance);

	public abstract int delete(PGroup persistentInstance);

	public abstract PGroup findById(java.lang.Integer id);

	public abstract List findByExample(PGroup instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByGroupName(Object groupName);

	public abstract List findByGroupDesc(Object groupDesc);

	public abstract List findByRoleId(Object roleId);

	public abstract List findAll();

	public abstract PGroup merge(PGroup detachedInstance);

	public abstract PRole findRoleById(int roleId);

	public abstract boolean findByGidAndGname(int groupId, String groupName);

	public abstract List<PGroup> getLisByPage(String page, String rows,
			String gname, String gdesc, int rid);

	public abstract List findGroupByUserId(String userId);

	public abstract List getGroupExceptUgroup(String userId);

}