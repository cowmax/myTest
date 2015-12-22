package com.service;

import java.util.List;
import com.bean.PGroup;
import com.bean.PRole;

public interface PGroupService {
	public List<PGroup> findByRoleId(int roleId);

	public void saveGroup(PGroup group);

	public int deleteGroup(PGroup group);

	public PGroup mergeGroup(PGroup group);

	public PRole findRoleById(int roleId);

	public PGroup findGroupByName(String gname);

	public boolean findByGidAndGname(int groupId, String groupName);

	public List<PGroup> getLisByPage(String page, String rows, String gname,
			String gdesc, int rid);

	public List findAllGlis();

	public List findGroupByUserId(String userId);

	public List getGroupExceptUgroup(String userId);
}
