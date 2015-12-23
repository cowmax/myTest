package com.service;

import java.util.List;
import java.util.Map;

import com.bean.ParaDtS;


public interface ParaDtSService {
	
	/**
	 * 获取所有活动信息
	 * @return
	 */
	public List getAllParaDtList();
	

	/**
	 * 导入数据
	 */
	public void saveOneBoat(List<ParaDtS> paraDtSList,int batchSize);
	
	/**
	 * 调用“p_imp_case”存储过程
	 */
	public void setImpParaDtSSku(int imp_flag,String name);
	
	/**
	 * 获取导出数据
	 */
	public List getCaseIdParaDtS(int caseId);
	
	/**
	 * 根据活动ID查找活动信息
	 * @param caseId
	 * @return
	 */
	public ParaDtS findParaDtSByCaseId(Integer caseId);
	
	/**
	 * 添加活动选款产品
	 * @param pds
	 */
	public void saveParaDtS(ParaDtS pds);
	
	/**
	 * 根据ID删除活动选款产品
	 * @param id
	 */
	public void deleteParaDtS(int id);
	
	/**
	 * 根据活动Id获取活动款/色
	 * @param case_id
	 * @param top
	 * @param del_status
	 * @return
	 */
	public Map<String, Integer> getCasePrdtSummary(int case_id,int top,int del_status);
}
