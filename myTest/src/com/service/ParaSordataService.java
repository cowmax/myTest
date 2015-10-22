package com.service;

import java.util.List;

import com.bean.ParaSordata;
import com.bean.Para_Type;

public interface ParaSordataService {
	/**
	 * 获取所有产品信息
	 * @return 产品集合
	 */
	public List<ParaSordata> allParaSordata();
	
	/**
	 * 通过Id获取产品信息
	 * @param valueType
	 * @return ParaSordata
	 */
	public ParaSordata findParaSordataById(com.bean.ParaSordataId id);
	
	/**
	 * 通过id来删除产品信息
	 */
	public  void delParaSordata(ParaSordata valueType);
	
	/**
	 * 通过id来修改产品信息
	 */
	public ParaSordata updateParaSordata(ParaSordata valueType);
	/**
	 * 添加产品信息
	 */
	public void saveParaSordata (ParaSordata paraSordata);
	/**
	 * 获取每页的数据
	 */
	public List<Para_Type> getPageParaSordata1(String page,String rows) throws Exception;
	/**
	 * 获取一共有多少条数据
	 */
	public int getParaSordataTotal() throws Exception;
}
