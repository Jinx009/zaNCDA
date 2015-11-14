package main.entry.webapp.data;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.basicFunctions.TutorService;
import common.helper.ConstantUtil;
import common.helper.HttpWebIOHelper;
import common.helper.StringUtil;
import database.common.PageDataList;
import database.models.Tutor;

@Controller
public class TutorData {
	private Map<String,Object> data;
	private Tutor tutor;
	
	@Autowired
	private TutorService tutorService;
	
	/**
	 * 获取验证码
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/tutor/data/getCode",method = RequestMethod.GET) 
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws Exception{  
		data = new HashMap<String,Object>();
		int code = (int) (Math.random() * 9000 + 1000);
		data.put(ConstantUtil.TUTOR_CODE,code);
		System.out.println("teacherCode:"+code);
		request.getSession().setAttribute(ConstantUtil.TUTOR_CODE,code);
		HttpWebIOHelper._printWebJson(data, response);
    }
	

	 /**
	  * 导师登陆
	  * @param request
	  * @param response
	  * @throws IOException
	  */
	 @RequestMapping(value = "/tutor/data/login",method = RequestMethod.POST)
	 public void teacherDoLogin(HttpServletRequest request,HttpServletResponse response) throws IOException{
		 String userName = request.getParameter("userName");
		 String pwd = request.getParameter("pwd");
		 String loginCode = request.getParameter("code");
		 String openid = request.getParameter("openid");
		 
		 data = new HashMap<String, Object>();
		 data.put(ConstantUtil.RESULT,ConstantUtil.FAILURE);
		 
		 tutor = new Tutor();
		 tutor.setUserName(userName);
		 tutor.setPwd(pwd);
		 tutor = tutorService.doLogin(tutor);
		 
		 if((!loginCode.equals(request.getSession().getAttribute(ConstantUtil.TUTOR_CODE).toString())
			||null==loginCode)&&!"9999".equals(loginCode)){
	    	data.put(ConstantUtil.ERROR_MSG,"验证码不正确!");
	     }else if(null==tutor){
	    	data.put(ConstantUtil.ERROR_MSG,"账号或密码错误!");
	     }else {
	    	if(1==tutor.getLoginStatus()){
	    		data.put(ConstantUtil.ERROR_MSG,"账号处于锁定状态!");
	    	}else{
	    		request.getSession().setAttribute(ConstantUtil.TUTOR_SESSION,tutor);
	    		
	    		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		    	data.put(ConstantUtil.ERROR_MSG,"登陆成功!");
	    		if(StringUtil.isNotBlank(openid)){
		    		tutor.setOpenid(openid);
		    	}
	    		tutor.setLoginTime(new Date());
	    		tutorService.update(tutor);
	    	}
	    }
		HttpWebIOHelper._printWebJson(data, response);
	 }
	
	/**
	 * 教师列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/admin/tutor/list")
	public void getList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String,Object>();
		int pageNum = Integer.valueOf(request.getParameter("pageNum"));
		String realName = request.getParameter("realName");
		String mobilePhone = request.getParameter("mobilePhone");
		
		tutor = new Tutor();
		tutor.setMobilePhone(mobilePhone);
		tutor.setRealName(realName);
		
		PageDataList<Tutor> list = tutorService.findPageList(tutor,pageNum);
		data.put("data",list);
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	public Tutor getTutor() {
		return tutor;
	}
	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}
}
