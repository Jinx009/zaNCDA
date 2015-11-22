package common.helper.filter;

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

import common.helper.ConstantUtil;
import common.helper.StringUtil;
import common.helper.tool.util.AgentUtil;
import common.wechat.WechatData;
import database.models.Customer;

public class CustomerSessionFilter implements Filter {
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
		HttpSession session = httpServletRequest.getSession();
		String servletPath = httpServletRequest.getServletPath();
		String queryString = httpServletRequest.getQueryString();
		List<String> pathList = notNeedSessionCheck();
		
		String redirectURL = servletPath;
		
		if (StringUtil.isNotBlank(queryString))
		{
			redirectURL = httpServletRequest.getContextPath()+servletPath + "?"+ StringUtil.isNull(queryString);
		}
		Customer  sessionUser = (Customer) session.getAttribute(ConstantUtil.CUSTOMER_SESSION);
		if(!pathList.contains(servletPath)){
			if(null==sessionUser){
				if(AgentUtil.judgeAgent(httpServletRequest)){
					httpServletResponse.sendRedirect(WechatData.getCustomerOauthUrl(redirectURL));
					return;
				}else{
					httpServletResponse.sendRedirect("/customer/login.html?redirectUrl="+java.net.URLEncoder.encode(redirectURL,"utf-8"));
					return;
				}
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
		String[] paths = new String[]{};

		return Arrays.asList(paths);
	}
}
