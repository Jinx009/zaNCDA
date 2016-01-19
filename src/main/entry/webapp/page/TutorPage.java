package main.entry.webapp.page;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	 * 导师端更改密码
	 * @return
	 */
	@RequestMapping(value = "/tutor/changePwd")
	public String changePwd(){
		return "/tutor/changePwd";
	}
	
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
					 status = 0;
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
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		if(null!=tutor.getBirthday()){
			request.setAttribute("birthday",df.format(tutor.getBirthday()));
		}else{
			request.setAttribute("birthday",df.format(new Date()));
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
		if(null!=tutor.getTopic2()){
			request.setAttribute("topic2",tutor.getTopic2().getId());
		}else{
			request.setAttribute("topic2",0);
		}
		if(null!=tutor.getTopic3()){
			request.setAttribute("topic3",tutor.getTopic3().getId());
		}else{
			request.setAttribute("topic3",0);
		}
		if(null!=tutor.getTopic4()){
			request.setAttribute("topic4",tutor.getTopic4().getId());
		}else{
			request.setAttribute("topic4",0);
		}
		if(null!=tutor.getTopic5()){
			request.setAttribute("topic5",tutor.getTopic5().getId());
		}else{
			request.setAttribute("topic5",0);
		}
		if(null!=tutor.getTopic6()){
			request.setAttribute("topic6",tutor.getTopic6().getId());
		}else{
			request.setAttribute("topic6",0);
		}
		if(null!=tutor.getTopic7()){
			request.setAttribute("topic7",tutor.getTopic7().getId());
		}else{
			request.setAttribute("topic7",0);
		}
		if(null!=tutor.getTopic8()){
			request.setAttribute("topic8",tutor.getTopic8().getId());
		}else{
			request.setAttribute("topic8",0);
		}
		if(null!=tutor.getTopic9()){
			request.setAttribute("topic9",tutor.getTopic9().getId());
		}else{
			request.setAttribute("topic9",0);
		}
		if(null!=tutor.getTopic10()){
			request.setAttribute("topic10",tutor.getTopic10().getId());
		}else{
			request.setAttribute("topic10",0);
		}
		if(null!=tutor.getTopic11()){
			request.setAttribute("topic11",tutor.getTopic11().getId());
		}else{
			request.setAttribute("topic11",0);
		}
		if(null!=tutor.getTopic12()){
			request.setAttribute("topic12",tutor.getTopic12().getId());
		}else{
			request.setAttribute("topic12",0);
		}
		if(null!=tutor.getTopic13()){
			request.setAttribute("topic13",tutor.getTopic13().getId());
		}else{
			request.setAttribute("topic13",0);
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
	 * 新建导师
	 * @return
	 */
	@RequestMapping(value = "/admin/page/tutor/new")
	public String newTutor(){
		return "/admin/tutor/new";
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
		if(null!=tutor.getqTopic()){
			request.setAttribute("topicId",tutor.getqTopic().getId());
		}else{
			request.setAttribute("topicId","");
		}
		if(null!=tutor.getTopic2()){
			request.setAttribute("topicId2",tutor.getTopic2().getId());
		}else{
			request.setAttribute("topicId2","");
		}
		if(null!=tutor.getTopic3()){
			request.setAttribute("topicId3",tutor.getTopic3().getId());
		}else{
			request.setAttribute("topicId3","");
		}
		if(null!=tutor.getTopic4()){
			request.setAttribute("topicId4",tutor.getTopic4().getId());
		}else{
			request.setAttribute("topicId4","");
		}
		if(null!=tutor.getTopic5()){
			request.setAttribute("topicId5",tutor.getTopic5().getId());
		}else{
			request.setAttribute("topicId5","");
		}
		if(null!=tutor.getTopic6()){
			request.setAttribute("topicId6",tutor.getTopic6().getId());
		}else{
			request.setAttribute("topicId6","");
		}
		if(null!=tutor.getTopic7()){
			request.setAttribute("topicId7",tutor.getTopic7().getId());
		}else{
			request.setAttribute("topicId7","");
		}
		if(null!=tutor.getTopic8()){
			request.setAttribute("topicId8",tutor.getTopic8().getId());
		}else{
			request.setAttribute("topicId8","");
		}
		if(null!=tutor.getTopic9()){
			request.setAttribute("topicId9",tutor.getTopic9().getId());
		}else{
			request.setAttribute("topicId9","");
		}
		if(null!=tutor.getTopic10()){
			request.setAttribute("topicId10",tutor.getTopic10().getId());
		}else{
			request.setAttribute("topicId10","");
		}
		if(null!=tutor.getTopic11()){
			request.setAttribute("topicId11",tutor.getTopic11().getId());
		}else{
			request.setAttribute("topicId11","");
		}
		if(null!=tutor.getTopic12()){
			request.setAttribute("topicId12",tutor.getTopic12().getId());
		}else{
			request.setAttribute("topicId12","");
		}
		if(null!=tutor.getTopic13()){
			request.setAttribute("topicId13",tutor.getTopic13().getId());
		}else{
			request.setAttribute("topicId13","");
		}
		if(null!=tutor.getTopic14()){
			request.setAttribute("topicId14",tutor.getTopic14().getId());
		}else{
			request.setAttribute("topicId14","");
		}
		
		if(null!=tutor.getTradeOne()){
			request.setAttribute("trade1",tutor.getTradeOne().getId());
		}else{
			request.setAttribute("trade1","");
		}
		if(null!=tutor.getTradeThree()){
			request.setAttribute("trade2",tutor.getTradeTwo().getId());
		}else{
			request.setAttribute("trade2","");
		}
		if(null!=tutor.getTradeThree()){
			request.setAttribute("trade3",tutor.getTradeThree().getId());
		}else{
			request.setAttribute("trade3","");
		}
		if(null!=tutor.getAreaOne()){
			request.setAttribute("area1",tutor.getAreaOne().getId());
		}else{
			request.setAttribute("area1","");
		}
		if(null!=tutor.getAreaTwo()){
			request.setAttribute("area2",tutor.getAreaTwo().getId());
		}else{
			request.setAttribute("area2","");
		}
		if(null!=tutor.getAreaThree()){
			request.setAttribute("area3",tutor.getAreaThree().getId());
		}else{
			request.setAttribute("area3","");
		}
        return "/admin/tutor/edit";  
    }

	
	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}
}
