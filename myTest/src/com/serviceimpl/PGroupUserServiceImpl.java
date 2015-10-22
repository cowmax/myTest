package com.serviceimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.bean.PGroup;
import com.bean.PGroupUser;
import com.bean.PUser;
import com.dao.PGroupUserDao;
import com.service.PGroupUserService;

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
		List list=pgudao.findByGroupId(gid);
		return list;
	}

	public PGroup findGroupById(int gid) {
		return pgudao.findGroupById(gid);
	}

	public PUser findUserById(String uid) {
		return pgudao.findUserById(uid);
	}

	public List findByOptions(String page, String rows, String userId,
			String userName, int groupId) {
		return pgudao.findByOptions(page, rows, userId, userName, groupId);
	}
	
}
