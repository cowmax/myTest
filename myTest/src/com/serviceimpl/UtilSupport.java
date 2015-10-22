package com.serviceimpl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.Query;
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
		//��Ϊȱʡֵ��ʱ����и�ֵ  
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//�ڼ�ҳ  
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//ÿҳ������
		List list = this.sessionFactory.getCurrentSession().createQuery(hql)  
				.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();  
		return list;  
	}  

	// ͳ��һ���ж�������  
	@SuppressWarnings("deprecation")
	public int getTotalCount(String hql) throws Exception {  
		return this.sessionFactory.getCurrentSession().find(hql).size();  
	}  
	/**
	 * ����������ѯ
	 * @param className
	 * @param parms
	 * @return
	 */
	public List getLisByOptions(String className,String page, String rows,Map<String, String> parms,String order) throws Exception{
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//�ڼ�ҳ  
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//ÿҳ������
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
