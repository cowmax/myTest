package com.serviceimpl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;

@SuppressWarnings("rawtypes")
public class UtilSupport{
	private SessionFactory sessionFactory;
	private List list;
	private Query query;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List getPageList(String hql, String page, String rows) {  
		//当为缺省值的时候进行赋值  
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页  
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行
		List list = this.sessionFactory.getCurrentSession().createQuery(hql)  
				.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();  
		return list;  
	}  
	// String sql   ： 数据库查询语句，函数使用此 SQL 查询数据集
	// String nPage ：指定函数返回数据集的第 n 页数据
	// String rows  ：指定定每数据页的记录数
	public List getPageListBySql(String sql, String nPage, String pageSize, Class[] resultSetTypes) {  
		//当为缺省值的时候进行赋值  

		int currentpage = Integer.parseInt((nPage == null || nPage == "0") ? "1": nPage);//第几页  
		int pagesize = Integer.parseInt((pageSize == null || pageSize == "0") ? "10": pageSize);//每页多少行
		
		SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
		for(int i = 0; i < resultSetTypes.length; i++){
			query.addEntity(resultSetTypes[i]);
		}
		
		List list =  query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();  
		
		return list;  
	}

	// 统计一共有多少数据  
	@SuppressWarnings("deprecation")
	public int getTotalCount(String hql) throws Exception {  
		return this.sessionFactory.getCurrentSession().find(hql).size();  
	}  
	/**
	 * 根据条件查询
	 * @param className
	 * @param parms
	 * @return
	 */
	public List getLisByOptions(String className,String page, String rows,Map<String, String> parms,String order) throws Exception{
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页  
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行
		StringBuffer hql=new StringBuffer("from "+className+" where 0 = 0 ");
		Iterator it = parms.entrySet().iterator() ; 
		while (it.hasNext()) 
		{ 
			Map.Entry entry = (Map.Entry) it.next() ; 
			String key = entry.getKey().toString() ; 
			hql.append(" and "+key+" like ?");
		}
		query=this.sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(order!=null||order!=""){
			hql.append(order);
		}
		it = parms.entrySet().iterator() ; 
		int i=0;
		while (it.hasNext()) 
		{ 
			Map.Entry entry = (Map.Entry) it.next() ; 
			String val = entry.getValue().toString() ; 
			query.setParameter(i, "%"+val+"%",Hibernate.STRING);
			i=i+1;
		}
		System.out.println(hql);
		list=query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
		return list;
	}

}
