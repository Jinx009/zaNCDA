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


	private Tutor tutor;
	
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
	@RequestMapping(value = "/tutor/login")
	public String login(HttpServletRequest request,HttpServletResponse response) throws ClientProtocolException, IOException{
		 String code = request.getParameter("code");
		 tutor = null;
		 int status = 0;
		 String  openid = "";
		 
		 if(StringUtil.isNotBlank(code)){
			 openid = WechatUtil.getOauthOpenId(WechatData.APP_ID,WechatData.APP_SECRET,code);
			 if(StringUtil.isNotBlank(openid)){
				 tutor = tutorService.getByOpenid(openid);
				 if(null!=tutor){
					 status = 1;
				 }
			 }
		 }
		 request.setAttribute("openid",openid);
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
	
	/**
	 * 导师信息
	 * @return
	 */
	@RequestMapping(value = "/tutor/page/infoOne")
	public String info(HttpServletRequest request,HttpServletResponse response){
		tutor = (Tutor) request.getSession().getAttribute(ConstantUtil.TUTOR_SESSION);
		tutor = tutorService.find(tutor.getId());
		if(null!=tutor.getqTrade()){
			request.setAttribute("trade",tutor.getqTrade().getId());
		}else{
			request.setAttribute("trade","");
		}
		request.setAttribute("tutor",tutor);
		return "/tutor/infoOne";
	}
	
	/**
	 * 完善信息二
	 * @return
	 */
	@RequestMapping(value = "/tutor/page/infoTwo")
	public String infoTwo(HttpServletRequest request,HttpServletResponse response){
		tutor = (Tutor) request.getSession().getAttribute(ConstantUtil.TUTOR_SESSION);
		tutor = tutorService.find(tutor.getId());
		request.setAttribute("tutor",tutor);
		return "/tutor/infoTwo";
	}

	/**
	 * 完善信息三
	 * @return
	 */
	@RequestMapping(value = "/tutor/page/infoThree")
	public String infoThree(HttpServletRequest request,HttpServletResponse response){
		tutor = (Tutor) request.getSession().getAttribute(ConstantUtil.TUTOR_SESSION);
		tutor = tutorService.find(tutor.getId());
		if(null!=tutor.getqTopic()){
			request.setAttribute("topicId",tutor.getqTopic().getId());
		}else{
			request.setAttribute("topicId",0);
		}
		if(null!=tutor.getTradeOne()){
			request.setAttribute("t1",tutor.getTradeOne().getId());
		}else{
			request.setAttribute("t1",0);
		}
		if(null!=tutor.getTradeTwo()){
			request.setAttribute("t2",tutor.getTradeTwo().getId());
		}else{
			request.setAttribute("t2",0);
		}
		if(null!=tutor.getTradeThree()){
			request.setAttribute("t3",tutor.getTradeThree().getId());
		}else{
			request.setAttribute("t3",0);
		}
		if(null!=tutor.getAreaOne()){
			request.setAttribute("a1",tutor.getAreaOne().getId());
		}else{
			request.setAttribute("a1",0);
		}
		if(null!=tutor.getAreaTwo()){
			request.setAttribute("a2",tutor.getAreaTwo().getId());
		}else{
			request.setAttribute("a2",0);
		}
		if(null!=tutor.getAreaThree()){
			request.setAttribute("a3",tutor.getAreaThree().getId());
		}else{
			request.setAttribute("a3",0);
		}
		request.setAttribute("tutor",tutor);
		return "/tutor/infoThree";
	}
	
	/**
	 * 完善信息三
	 * @return
	 */
	@RequestMapping(value = "/tutor/page/infoFour")
	public String infoFour(HttpServletRequest request,HttpServletResponse response){
		tutor = (Tutor) request.getSession().getAttribute(ConstantUtil.TUTOR_SESSION);
		tutor = tutorService.find(tutor.getId());
		request.setAttribute("tutor",tutor);
		return "/tutor/infoFour";
	}
	
	/**
	 * 跳转后台导师编辑页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/page/tutor/edit") 
    public String edit(HttpServletRequest request,HttpServletResponse response){
		Integer id = Integer.valueOf(request.getParameter("id"));
		tutor = tutorService.find(id);
		request.setAttribute("tutor",tutor);
        return "/admin/tutor/edit";  
    }

	
	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}
}
