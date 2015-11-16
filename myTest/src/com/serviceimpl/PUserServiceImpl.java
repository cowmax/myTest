package com.serviceimpl;

import java.util.List;

import org.hibernate.SessionFactory;

import com.bean.PUser;
import com.dao.PUserDao;
import com.service.PUserService;

@SuppressWarnings("unchecked")
public class PUserServiceImpl implements PUserService {
	private PUserDao pudao;
	private List<PUser> allusers;
	private PUser u;
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public PUser getU() {
		return u;
	}

	public void setU(PUser u) {
		this.u = u;
	}

	public PUserDao getPudao() {
		return pudao;
	}

	public void setPudao(PUserDao pudao) {
		this.pudao = pudao;
	}

	public List<PUser> getAllusers() {
		return allusers;
	}

	public void setAllusers(List<PUser> allusers) {
		this.allusers = allusers;
	}

	/**
	 * 获取所有用户信息
	 */
	public List<PUser> allUsers() {
		allusers=pudao.findAll();
		return allusers;
	}

	public PUser findUserById(String userId) {
		u=pudao.findById(userId);
		return u;
	}

	/**
	 * 删除用户信息
	 */
	public void delUser(PUser pu) {
		pudao.delete(pu);
	}

	/**
	 * 修改用户信息
	 */
	public PUser mergeUser(PUser pu) {
		u=pudao.merge(pu);
		return u;
	}

	/**
	 * 添加用户信息
	 */
	public void saveUser(PUser pu) {
		pudao.save(pu);
	}
	
	/**
	 * 用户登录验证
	 * @param userName
	 * @param userPwd
	 * @return
	 */
	public PUser userLogin(String userId) {
		u=pudao.findById(userId);
		return u;
	}

}
