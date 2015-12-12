package com.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.bean.PUser;
import com.serviceimpl.UtilSupport;

public class PowerFilter implements Filter {

	private String permitUrls[] = null; 
	private String gotoUrl = null; 
	private UtilSupport util;

	public UtilSupport getUtil() {
		return util;
	}

	public void setUtil(UtilSupport util) {
		this.util = util;
	}

	public void destroy() {
		// TODO Auto-generated method stub
		permitUrls = null; 
		gotoUrl = null; 
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest res=(HttpServletRequest) request; 
        HttpServletResponse resp=(HttpServletResponse)response; 
        if(!isPermitUrl(request)){ 
            if(filterCurrUrl(request)){ 
                System.out.println("--->请登录"); 
                resp.sendRedirect(res.getContextPath()+gotoUrl); 
                return; 
            } 
        } 
        System.out.println("--->允许访问"); 
        chain.doFilter(request, response); 
	}
	
	public boolean filterCurrUrl(ServletRequest request){ 
        boolean filter=false; 
        HttpServletRequest res=(HttpServletRequest) request; 
        PUser user=(PUser)res.getSession().getAttribute("pu");
        if(null==user) 
            filter=true; 
        return filter;  

    }       
    public boolean isPermitUrl(ServletRequest request) { 
        boolean isPermit = false; 
        String currentUrl = currentUrl(request); 
        if (permitUrls != null && permitUrls.length > 0) { 
            for (int i = 0; i < permitUrls.length; i++) { 
                if (permitUrls[i].equals(currentUrl)) { 
                    isPermit = true; 
                    break; 
                } 
            } 
        } 
        return isPermit; 
    }        
    //请求地址 
    public String currentUrl(ServletRequest request) {   
        HttpServletRequest res = (HttpServletRequest) request; 
        String task = request.getParameter("task"); 
        String path = res.getContextPath(); 
        String uri = res.getRequestURI(); 
        if (task != null) {// uri格式 xx/ser 
            uri = uri.substring(path.length(), uri.length()) + "?" + "task="
                    + task; 
        } else { 
            uri = uri.substring(path.length(), uri.length()); 
        } 
        System.out.println("当前请求地址:" + uri); 
        return uri; 
    } 

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest res=ServletActionContext.getRequest() ; 
		PUser loginuser=(PUser)res.getSession().getAttribute("pu");
		String userId=loginuser.getUserId();
		List<String> urlsList=util.getUrlsByUserId(userId);
		
        String gotoUrl = filterConfig.getInitParameter("gotoUrl"); 
        
        this.gotoUrl = gotoUrl; 
  
        if (urlsList != null && urlsList.size() > 0) { 
        	for (int i = 0; i < urlsList.size(); i++) {
				this.permitUrls[i]=urlsList.get(i);
			}
        } 
	}

}
