package com.service;

import java.util.List;

import com.bean.PGroup;
import com.bean.PGroupUser;
import com.bean.PUser;

public interface PGroupUserService {

	public List findPguByGid(int gid);
	
	public PGroup findGroupById(int gid);
	
	public PUser findUserById(String uid);
	
	public void savePgu(int groupId,String userId);
	
	public void deletPgu(int groupId,String userId);
	
}
