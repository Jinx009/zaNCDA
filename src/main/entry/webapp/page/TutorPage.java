package main.entry.webapp.page;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import service.basicFunctions.TutorService;
import common.helper.ConstantUtil;
import common.helper.StringUtil;
import common.wechat.WechatData;
import common.wechat.WechatUtil;
import database.models.Tutor;

@Controller
public class TutorPage {

	@Autowired
	private TutorService tutorService;
	
	private Tutor tutor;
	
	/**
	 * 导师登陆页面
	 * @param request
	 * @param response
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@RequestMapping(value = "/tutor/login")
	public String login(HttpServletRequest request,HttpServletResponse response) throws ClientProtocolException, IOException{
		 String code = request.getParameter("code");
		 tutor = null;
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
		 request.getSession().setAttribute(ConstantUtil.TUTOR_SESSION,tutor);
		 return "/tutor/login";
	 }
	
	/**
	 * 导师首页
	 * @return
	 */
	@RequestMapping(value = "/tutor/page/index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		String realName = "";
		tutor = (Tutor) request.getSession().getAttribute(ConstantUtil.TUTOR_SESSION);
		if(StringUtil.isNotBlank(tutor.getRealName())){
			realName = tutor.getRealName();
		}
		request.setAttribute("tutor",realName);
		return "/tutor/index";
	}

	
	
	public Tutor getTutor() {
		return tutor;
	}
	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}
}
