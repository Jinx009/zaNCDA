package main.entry.webapp.page;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import service.basicFunctions.TutorService;
import common.helper.StringUtil;
import common.wechat.WechatData;
import common.wechat.WechatUtil;
import database.models.Tutor;

public class TutorPage {

	@Autowired
	private TutorService tutorService;
	
	/**
	 * 导师登陆页面
	 * @param request
	 * @param response
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@RequestMapping(value = "/tutoe/login")
	public String login(HttpServletRequest request,HttpServletResponse response) throws ClientProtocolException, IOException{
		 String code = request.getParameter("code");
		 Tutor tutor = null;
		 int status = 0;
		 
		 if(StringUtil.isNotBlank(code)){
			 String openid = WechatUtil.getOauthOpenId(WechatData.APP_ID,WechatData.APP_SECRET,code);
			 if(StringUtil.isNotBlank(openid)){
				 tutor = tutorService.getByOpenid(openid);
				 if(null!=tutor){
					 status = 1;
				 }
			 }
		 }
		 request.setAttribute("status",status);
		 request.setAttribute("url",WechatData.getTutorOauthUrl());
		 request.getSession().setAttribute("teacher_session_user",tutor);
		 return "/teacher/login";
	 }
}
