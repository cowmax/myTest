package com.dao;

import java.util.List;

import com.bean.PGroup;
import com.bean.PGroupUser;
import com.bean.PUser;

public interface PGroupUserDao {

	public abstract void save(int groupId, String userId);

	public abstract void delete(int groupId, String userId);

	public abstract PGroupUser findById(java.lang.Integer id);

	public abstract List findByExample(PGroupUser instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByGroupId(int groupId);

	public abstract List findByUserId(Object userId);

	public abstract List findAll();

	public abstract PGroupUser merge(PGroupUser detachedInstance);

	public abstract PGroup findGroupById(int gid);

	public abstract PUser findUserById(String uid);

}