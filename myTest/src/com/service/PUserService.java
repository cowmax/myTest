package com.service;

import java.util.List;

import com.bean.PUser;

public interface PUserService {
	/**
	 * ��ȡ�����û���Ϣ
	 * 
	 * @return �û�����
	 */
	public List<PUser> allUsers();

	/**
	 * ͨ��userId��ȡ�û���Ϣ
	 * 
	 * @param userId
	 * @return PUser
	 */
	public PUser findUserById(String userId);

	/**
	 * ɾ���û���Ϣ
	 * 
	 * @param pu
	 */
	public void delUser(PUser pu);

	/**
	 * �޸��û���Ϣ
	 * 
	 * @param pu
	 * @return
	 */
	public PUser mergeUser(PUser pu);

	/**
	 * ����û���Ϣ
	 * 
	 * @param pu
	 */
	public void saveUser(PUser pu);

	/**
	 * �û���¼��֤
	 * 
	 * @param userName
	 * @param userPwd
	 * @return
	 */
	public PUser userLogin(String userId);

}
