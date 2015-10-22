package com.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;

import com.bean.ParaSordata;
import com.bean.ParaSordataId;
import com.bean.ParaValueType;
import com.bean.Para_Type;
import com.dao.ParaSordataDao;
import com.opensymphony.xwork2.Result;
import com.service.ParaSordataService;

@SuppressWarnings("unused")
public class ParaSordataServiceImpl implements ParaSordataService{
	//封装dao调用里面的方法
	private ParaSordataDao paraSordatadao;
	//封装集合
	private List<ParaSordata> allParaSordata;
	//封装对象
	private ParaSordata paraSordata;
	private SessionFactory sessionFactory;
	private List<ParaSordata> list;
	private List<Para_Type> allPara_Type;
	private Query query;


	public List<Para_Type> getAllPara_Type() {
		return allPara_Type;
	}

	public void setAllPara_Type(List<Para_Type> allPara_Type) {
		this.allPara_Type = allPara_Type;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public ParaSordataDao getParaSordatadao() {
		return paraSordatadao;
	}

	public void setParaSordatadao(ParaSordataDao paraSordatadao) {
		this.paraSordatadao = paraSordatadao;
	}

	public List<ParaSordata> getAllParaSordata() {
		return allParaSordata;
	}

	public void setAllParaSordata(List<ParaSordata> allParaSordata) {
		this.allParaSordata = allParaSordata;
	}

	public ParaSordata getParaSordata() {
		return paraSordata;
	}

	public void setParaSordata(ParaSordata paraSordata) {
		this.paraSordata = paraSordata;
	}

	/**
	 * 获取所有产品信息
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<ParaSordata> allParaSordata() {
		allParaSordata=paraSordatadao.findAll();
		return allParaSordata;
	}

	/**
	 * 根据产品类型查找
	 */
	public ParaSordata findParaSordataById(com.bean.ParaSordataId id) {
		paraSordata=paraSordatadao.findById(id);
		return paraSordata;
	}

	/**
	 * 通过id来删除产品信息
	 */
	public void delParaSordata(ParaSordata valueType) {
		paraSordatadao.delete(valueType);

	}

	/**
	 * 通过id来修改产品信息
	 */
	public ParaSordata updateParaSordata(ParaSordata ps) {
		ps=paraSordata;
		paraSordata=paraSordatadao.merge(paraSordata);
		return paraSordata;
	}

	/**
	 * 添加产品信息
	 */
	public void saveParaSordata(ParaSordata paraSordata) {
		paraSordatadao.save(paraSordata);
	}
	/**
	 * 获取每页的数据
	 */
	@SuppressWarnings("unchecked")
	public List<Para_Type> getPageParaSordata1(String page, String rows)
			throws Exception {
		//当为缺省值的时候进行赋值  
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页  
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行
		String sql = "select {a.*}, {b.*} from para_sordata_p a inner join para_value_type b on a.value_type = b.valTypeId order by sys_dt desc";

		SQLQuery query =  this.sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.addEntity("a", ParaSordata.class);
		query.addEntity("b", ParaValueType.class);
		query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
		// query.addJoin("ParaValueType", "ParaValueType.valTypeId");

		List<Object[]> list = query.list();
		allPara_Type=new ArrayList<Para_Type>();
		for (int i = 0; i < list.size(); i++) {
			ParaSordata p = (ParaSordata)list.get(i)[0];
			ParaValueType t = (ParaValueType)list.get(i)[1];	
			Para_Type para_Type=new Para_Type();
			para_Type.setValueType(p.getId().getValueType());
			para_Type.setTyna(p.getId().getTyna());
			para_Type.setValueTypeName(t.getValTypeName());
			para_Type.setSysDt(p.getSysDt());
			para_Type.setSysUserId(p.getSysUserId());
			para_Type.setValueDesc(p.getValueDesc());
			para_Type.setValueMax(p.getValueMax());
			para_Type.setValueMin(p.getValueMin());
			para_Type.setValueRatio(p.getValueRatio());
			allPara_Type.add(para_Type);
		}
//		for (int i = 0; i < allPara_Type.size(); i++) {
//			System.out.println(allPara_Type.get(i).getTyna());
//		}
		return allPara_Type;

	}

	/**
	 * 获取一共有多少条数据
	 */

	public int getParaSordataTotal() throws Exception {
		String sql="select count(*) from para_sordata_p a inner join para_value_type b on a.value_type=b.valTypeId";
		query=this.sessionFactory.getCurrentSession().createSQLQuery(sql);
		int count= (Integer) query.uniqueResult();
		return count;
	}

}
