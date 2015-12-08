package com.service;

import java.sql.Timestamp;
import java.util.List;

import com.bean.ParaDt;


public interface ParaDtService {
	/**
	 * 获取所有活动信息
	 */
	public List<ParaDt> allParaDt();

	/**
	 * 通过code获取活动信息
	 */
	public ParaDt findParaDtById(Integer caseCode);

	/**
	 * 添加活动信息
	 */
	public void saveParaDt(ParaDt paraDt);

	/**
	 * 通过id来删除活动
	 */
	public ParaDt delParaDtById(ParaDt paraDt);

	/**
	 * 通过id来修改活动
	 */
	public ParaDt updateParaDtImpl(ParaDt caseId);


	/**
	 * 获取一共有多少条数据
	 */
	public int getTotalCount( ) throws Exception;
	
	/**
	 * 导入Excel表格
	 * 
	 */
	public void saveOneBoat(ParaDt paraDt);
	
	
	
	/**
	 * 下载当页数据excel表格
	 */
	
	/**
	 * 通过活动类型获取时间进行比较
	 */
	public int getCaseNameTime(String caseName,Timestamp caseSt,Timestamp caseEt );
	
	
}
