package com.service;

import java.util.List;
import java.util.Map;

import com.bean.BProductP;


public interface BProductPService {
	/**
	 * 获取所有产品信息
	 *
	 */
	public List<BProductP> allBProductP();
	
  /**
   * 查询tyna
   */

	public List alltyna();
	
	/**
	 * 查询除某活动已存在的产品的信息
	 * @param caseId
	 * @return
	 */
	public List findExceptByCaseId(Integer caseId);
	
	/**
	 * 根据产品编码获取产品信息
	 * @param productCode
	 * @return
	 */
	public BProductP findById(String productCode);
	
	/**
	 * 根据产品编号获取产品颜色
	 * @param productCode
	 * @return
	 */
	public Map<String, String> findColorByProductCd(String productCode);
	
}
