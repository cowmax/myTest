package com.service;

import java.util.List;

import com.bean.PRole;

public interface PRoleService {
	public void saveRole(PRole role);

	public int deleteRole(PRole role);

	public PRole mergeRole(PRole role);

	public PRole findRoleByName(String roleName);

	public PRole findRoleById(int roleId);

	public List<PRole> rolelis();

	public boolean findByRidAndRname(int roleId, String roleName);
}
