package com.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;

import com.bean.Store;
import com.dao.StoreDao;
import com.service.StoreService;



public class StoreServiceImpl implements StoreService {
	// 封装dao调用里面的方法
	private StoreDao storeDao;
	// 封装集合
	private List<Store> allStore;
	// 封装对象
	private Store store;
	private SessionFactory sessionFactory;

	public StoreDao getStoreDao() {
		return storeDao;
	}


	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
	}


	public List<Store> getAllStore() {
		return allStore;
	}


	public void setAllStore(List<Store> allStore) {
		this.allStore = allStore;
	}


	public Store getStore() {
		return store;
	}


	public void setStore(Store store) {
		this.store = store;
	}


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	/*
	 * 获取所有store
	 */
	@SuppressWarnings("unchecked")
	public List<Store> getStoreList() {
		allStore = storeDao.findAll();
		return allStore;
	}
	
}
