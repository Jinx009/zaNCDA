package common.helper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.models.NbAdminUser;

public class AdminSessionFilter implements Filter{
	protected String encoding = null;

	protected FilterConfig filterConfig = null;

	protected boolean ignore = false;

	protected String forwardPath = null;

	public void destroy(){
		this.encoding = null;
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException{

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		String servletPath = httpServletRequest.getServletPath();
		
		List<String> pathList = notNeedSessionCheck();
		
		// 通过检查session中的变量，过虑请求
		HttpSession session = httpServletRequest.getSession();
		NbAdminUser sessionUser = (NbAdminUser) session.getAttribute("admin_session_user");
		if(!pathList.contains(servletPath)){
			if(null==sessionUser){
				httpServletResponse.sendRedirect("/admin/login.html");
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException{
		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");
		this.forwardPath = filterConfig.getInitParameter("forwardpath");
		String value = filterConfig.getInitParameter("ignore");
		if (value == null)
			this.ignore = true;
		else if (value.equalsIgnoreCase("true"))
			this.ignore = true;
		else if (value.equalsIgnoreCase("yes"))
			this.ignore = true;
		else
			this.ignore = false;
	}

	private List<String> notNeedSessionCheck(){
		String[] paths = new String[]{ 
				"/admin/login.html", 
				"/admin/doLogin.html", 
		};

		return Arrays.asList(paths);
	}
}
