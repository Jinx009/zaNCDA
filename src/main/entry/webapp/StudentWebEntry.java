package main.entry.webapp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import common.helper.HttpWebIOHelper;
import common.helper.StringUtil;
import common.wechat.WechatData;
import common.wechat.WechatUtil;
import service.basicFunctions.StudentService;
import database.common.PageDataList;
import database.models.NbStudentsUser;


@Controller
public class StudentWebEntry {

	@Autowired
	private StudentService studentService;
	
	private Map<String,Object> data;

	/**
	 * 获取顾客列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/student/list",method=RequestMethod.POST)
	public void dataList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String, Object>();
		
		int pageNum = Integer.valueOf(request.getParameter("pageNum"));
		String realName = request.getParameter("realName");
		String mobilePhone = request.getParameter("mobilePhone");
		
		NbStudentsUser nbStudentsUser = new NbStudentsUser();
		nbStudentsUser.setMobilePhone(mobilePhone);
		nbStudentsUser.setRealName(realName);
		
		PageDataList<NbStudentsUser> list = studentService.findPageList(nbStudentsUser,pageNum, 20);
		data.put("data",list);
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 顾客登陆
	 * @param request
	 * @param response
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@RequestMapping(value="/student/login")
	public String login(HttpServletRequest request,HttpServletResponse response) throws ClientProtocolException, IOException{
		
		 String code = request.getParameter("code");
		 NbStudentsUser nbStudentsUser = null;
		 String realOpenid = "",username = "";
		 int status = 0;
		 
		 if(StringUtil.isNotBlank(code)){
			 String openid = WechatUtil.getOauthOpenId(WechatData.APP_ID,WechatData.APP_SECRET,code);
			 if(StringUtil.isNotBlank(openid)){
				 realOpenid = openid;
				 nbStudentsUser = studentService.findByOpenid(openid);
				 if(null!=nbStudentsUser){
					 if(StringUtil.isBlank(nbStudentsUser.getRealName())){
						 username = nbStudentsUser.getRealName();
					 }else{
						 username = nbStudentsUser.getUsername();
					 }
					 status = 1;
				 }
			 }
		 }
		 
		 request.setAttribute("status",status);
		 request.setAttribute("username",username);
		 request.setAttribute("openid",realOpenid);
		 request.setAttribute("url",WechatData.getTeacherOauthUrl());
		 request.getSession().setAttribute("student_session_user",nbStudentsUser);
		 return "/student/login";
	}
	
	@RequestMapping(value = "/sdata/findTeacher")
	public void findTeacher(){
		
	}
	
	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
