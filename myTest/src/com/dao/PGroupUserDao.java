package com.dao;

import java.util.List;

import com.bean.PGroup;
import com.bean.PGroupUser;
import com.bean.PUser;

public interface PGroupUserDao {

	public abstract void save(PGroupUser transientInstance);

	public abstract void delete(PGroupUser persistentInstance);

	public abstract PGroupUser findById(java.lang.Integer id);

	public abstract List findByExample(PGroupUser instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByGroupId(int groupId);

	public abstract List findByUserId(Object userId);

	public abstract List findAll();

	public abstract PGroupUser merge(PGroupUser detachedInstance);
	
	public abstract PGroup findGroupById(int gid);
	
	public abstract PUser findUserById(String uid);
	
	public abstract List findByOptions(String page, String rows, String userId,
			String userName, int groupId);

}