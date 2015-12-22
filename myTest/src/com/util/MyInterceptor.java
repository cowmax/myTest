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
public class MyInterceptor implements Interceptor {
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
	 * ���Ȩ�޶Է�����������
	 */
	public String intercept(ActionInvocation invocation) throws Exception {
		// ��ȡsession����
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		// ��ȡaction
		// ActionSupport action = (ActionSupport) invocation.getAction();
		String actionName = invocation.getInvocationContext().getName();
		boolean flag = false;

		// ��¼ǰ��ע������Ĳ���
		if (actionName.equals("pusercheckCode")
				|| actionName.equals("puserloginCheck")
				|| actionName.equals("puserexit")) {
			result = invocation.invoke();
		}

		// ͨ��session��ȡ��ǰ��¼�û�
		PUser loginuser = (PUser) session.getAttribute("pu");
		// �ж�ͨ��session��ȡ��ǰ��¼�û��Ƿ�Ϊ��
		if (loginuser == null) {
			result = "sessionTimeOut";
		} else {
			// ��ȡ��ǰ�û�ID
			String userId = loginuser.getUserId();
			// ��ȡ��ǰ�û�action
			List<String> urlsList = util.getUrlsByUserId(userId);

			// ƥ��˵�action
			Pattern p = Pattern.compile("^menu.*$");
			Matcher m = p.matcher(actionName);
			flag = m.matches();
			if (flag) {
				result = invocation.invoke();
			} else {
				// ƥ�䵱ǰ�û�Ȩ������ ����
				for (Object o : urlsList) {

					p = Pattern.compile("^" + o + ".*$");
					m = p.matcher(actionName);
					flag = m.matches();

					if (flag) {
						result = invocation.invoke();
						break;
					} else {
						result = "login";
					}
				}
			}

		}
		return result;
	}
}
