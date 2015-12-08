package com.util;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bean.PUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.serviceimpl.UtilSupport;

@SuppressWarnings("serial")
public class MyInterceptor implements Interceptor{
	private String result;
	private UtilSupport util;

	public UtilSupport getUtil() {
		return util;
	}

	public void setUtil(UtilSupport util) {
		this.util = util;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init() {
		// TODO Auto-generated method stub

	}

	/**
	 * 根据权限对方法进行拦截
	 */
	public String intercept(ActionInvocation invocation) throws Exception {
		//获取session对象
		HttpServletRequest request = ServletActionContext.getRequest();  
		HttpSession session = request.getSession(true);  
		//获取action
//		ActionSupport action = (ActionSupport) invocation.getAction();  
		String actionName = invocation.getInvocationContext().getName(); 
		boolean flag = false;
		
		//登录前及注销允许的操作
		if(actionName.equals("pusercheckCode")||actionName.equals("puserloginCheck")||actionName.equals("puserexit")){
			result= invocation.invoke();  
		}

		//通过session获取当前登录用户
		PUser loginuser=(PUser)session.getAttribute("pu");
		//判断通过session获取当前登录用户是否为空
		if(loginuser==null){
			result= "sessionTimeOut";
		}else{
			//获取当前用户ID
			String userId=loginuser.getUserId();
			//获取当前用户action
			List<String> urlsList=util.getUrlsByUserId(userId);

			//匹配菜单action
			Pattern p = Pattern.compile("^menu.*$");
			Matcher m=p.matcher(actionName);
			flag = m.matches();
			if(flag){
				result=invocation.invoke();
			}else{
				//匹配当前用户权限所有 方法
				for (Object o : urlsList) {

					p = Pattern.compile("^"+o+".*$");
					m = p.matcher(actionName);
					flag = m.matches();

					if(flag){
						result=invocation.invoke();
						break;
					}else{
						result= "login";
					}
				}
			}

		}
		return result;
	}
}
