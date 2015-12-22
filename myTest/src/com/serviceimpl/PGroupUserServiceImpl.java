package com.serviceimpl;

import java.util.List;

import com.bean.PGroup;
import com.bean.PGroupUser;
import com.bean.PUser;
import com.dao.PGroupUserDao;
import com.service.PGroupUserService;

@SuppressWarnings("rawtypes")
public class PGroupUserServiceImpl implements PGroupUserService {

	private PGroupUserDao pgudao;
	private PGroupUser pgu;

	public PGroupUserDao getPgudao() {
		return pgudao;
	}

	public void setPgudao(PGroupUserDao pgudao) {
		this.pgudao = pgudao;
	}

	public PGroupUser getPgu() {
		return pgu;
	}

	public void setPgu(PGroupUser pgu) {
		this.pgu = pgu;
	}

	public List findPguByGid(int gid) {
		List list = pgudao.findByGroupId(gid);
		return list;
	}

	public PGroup findGroupById(int gid) {
		return pgudao.findGroupById(gid);
	}

	public PUser findUserById(String uid) {
		return pgudao.findUserById(uid);
	}

	public void savePgu(int groupId, String userId) {
		pgudao.save(groupId, userId);
	}

	public void deletPgu(int groupId, String userId) {
		pgudao.delete(groupId, userId);
	}

}
