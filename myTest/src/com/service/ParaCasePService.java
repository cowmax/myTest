package com.service;

import java.util.List;

import com.bean.ParaCaseP;


public interface ParaCasePService {
	/**
	 * 获取所有活动信息
	 */
	public List<ParaCaseP> allParaCaseP();

	/**
	 * 通过code获取活动信息
	 */
	public ParaCaseP findParaCasePById(String caseCode);

	/**
	 * 添加活动信息
	 */
	public void saveParaCaseP(ParaCaseP paraCaseP);

	/**
	 * 通过id来删除活动
	 */
	public void delParaCasePById(ParaCaseP caseCode);

	/**
	 * 通过id来修改活动
	 */
	public int updateParaCaseP(ParaCaseP caseCode);


	/**
	 * 获取一共有多少条数据
	 * 
	 * @throws Exception
	 */
	public int getParaCasePTotal() throws Exception;
	
	public int getParaDtCaseCode(String caseCode);
	
	public boolean getNameBePCPRe(String caseCode ,String caseName);
	
	/**
	 * 导入Excel表格
	 * @return 
	 */
	public boolean addOneBoat(List<ParaCaseP> list,int batchSize);
	
	/**
	 * 通过名称获取活动信息
	 */
	public ParaCaseP getNameParaCaseP(String caseName);
	
}
