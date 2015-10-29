package main.entry.webapp;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import common.helper.ConstantUtil;
import common.helper.HttpWebIOHelper;
import common.helper.MD5Util;
import common.helper.StringUtil;
import common.wechat.WechatData;
import common.wechat.WechatUtil;
import database.common.PageDataList;
import database.models.NbTeachersUser;
import service.basicFunctions.AreaNameService;
import service.basicFunctions.FixedTopicNameService;
import service.basicFunctions.JobNameService;
import service.basicFunctions.TeacherService;

@Controller
public class TeacherWebEntry {
	
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private JobNameService jobNameService;
	@Autowired
	private AreaNameService areaNameService;
	@Autowired
	private FixedTopicNameService fixedTopicNameService;
	
	private Map<String,Object> data;
	
	/**
	 * 上传图片
	 * 
	 * @return 图片路径
	 * @throws IOException
	 * if has error
	 */
	 @RequestMapping(value = "/uploadImg")  
	 public void upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model,HttpServletResponse response) throws IOException {  
	  
        System.out.println("开始上传");  
        
        long currentTime = Calendar.getInstance().getTimeInMillis();
        String path = request.getSession().getServletContext().getRealPath("/sp/upload/");  
        
        String fileName = file.getOriginalFilename();
        System.out.println(path);  
        File targetFile = new File(path,String.valueOf(currentTime)+fileName);  
	    if(!targetFile.exists()){  
	        targetFile.mkdirs();  
	    }  
	  
	   try{  
	         file.transferTo(targetFile);  
	    } catch (Exception e) {  
	         e.printStackTrace();  
	    }  
	  
	    data = new HashMap<String,Object>();
	    data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
	    data.put(ConstantUtil.ERROR_MSG,"/sp/upload/"+fileName);
			
		HttpWebIOHelper._printWebJson(data, response);
	}

	 /**
	  * 教师约谈
	  * @return
	  */
	 @RequestMapping(value="/tpage/comments")
	 public String pageComments(){
		 return "/tpage/comments";
	 }
	 
	 /**
	  * 教师信息
	  * @return
	  */
	 @RequestMapping(value="/tpage/info")
	 public String pageInfo(){
		 return "/tpage/info";
	 }
	 
	 /**
	  * 教师账户
	  * @return
	  */
	 @RequestMapping(value="/tpage/account")
	 public String pageAccount(){
		 return "/tpage/account";
	 }
	 
	 /**
	  * 后台编辑
	  * @param request
	  * @param response
	  * @param map
	  * @return
	  */
	 @RequestMapping(value="/admin/teacherEdit")
	 public String editPage(HttpServletRequest request,HttpServletResponse response,ModelMap map){
		 
	     map.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
	     map.put(ConstantUtil.ERROR_MSG,request.getParameter("id"));
	     map.put("teacher",teacherService.findById(Integer.valueOf(request.getParameter("id"))));
		 
		 return "/admin/teacherEdit";
	 }
	
	 /**
	  * 教师登陆
	  * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	  */
	 @RequestMapping(value = "/teacher/login")
	 public String teacherLogin(HttpServletRequest request,HttpServletResponse response) throws ClientProtocolException, IOException{
		 String code = request.getParameter("code");
		 NbTeachersUser nbTeachersUser = null;
		 String realOpenid = "",username = "";
		 int status = 0;
		 
		 if(StringUtil.isNotBlank(code)){
			 String openid = WechatUtil.getOauthOpenId(WechatData.APP_ID,WechatData.APP_SECRET,code);
			 if(StringUtil.isNotBlank(openid)){
				 realOpenid = openid;
				 nbTeachersUser = teacherService.findByOpenid(openid);
				 if(null!=nbTeachersUser){
					 if(StringUtil.isBlank(nbTeachersUser.getRealName())){
						 username = nbTeachersUser.getRealName();
					 }else{
						 username = nbTeachersUser.getUsername();
					 }
					 status = 1;
				 }
			 }
		 }
		 
		 request.setAttribute("status",status);
		 request.setAttribute("username",username);
		 request.setAttribute("openid",realOpenid);
		 request.setAttribute("url",WechatData.getTeacherOauthUrl());
		 request.getSession().setAttribute("teacher_session_user",nbTeachersUser);
		 return "/teacher/login";
	 }
	 
	 /**
	  * 教师登陆
	  * @param request
	  * @param response
	  * @throws IOException
	  */
	 @RequestMapping(value = "/teacher/doLogin",method = RequestMethod.POST)
	 public void teacherDoLogin(HttpServletRequest request,HttpServletResponse response) throws IOException{
		 String username = request.getParameter("username");
		 String password = request.getParameter("password");
		 String teacherCode = request.getParameter("code");
		 String openid = request.getParameter("openid");
		 
		 data = new HashMap<String, Object>();
		 data.put(ConstantUtil.RESULT,ConstantUtil.FAILURE);
		 
		 NbTeachersUser nbTeachersUser = new NbTeachersUser();
		 nbTeachersUser.setUsername(username);
		 nbTeachersUser.setPassword(MD5Util.toMD5(password));
		 
		 NbTeachersUser nbTeachersUser2 = teacherService.doLogin(nbTeachersUser);
		 
		 if(!teacherCode.equals(request.getSession().getAttribute("teacherCode").toString())||null==teacherCode){
	    	data.put(ConstantUtil.ERROR_MSG,"验证码不正确!");
	     }else if(null==nbTeachersUser2){
	    	data.put(ConstantUtil.ERROR_MSG,"账号或密码错误!");
	     }else {
	    	data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
	    	data.put(ConstantUtil.ERROR_MSG,"登陆成功!");
	    	
	    	if(StringUtil.isNotBlank(openid)){
	    		nbTeachersUser2.setOpenid(openid);
	    		nbTeachersUser2.setUpdateTime(new Date());
	    		teacherService.doUpdate(nbTeachersUser2);
	    	}
	    	request.getSession().setAttribute("teacher_session_user",nbTeachersUser2);
	    }
		 
		HttpWebIOHelper._printWebJson(data, response);
	 }
	
	/**
	 * 教师列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/teacher/list")
	public void getList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		int pageNum = Integer.valueOf(request.getParameter("pageNum"));
		String realName = request.getParameter("realName");
		String mobilePhone = request.getParameter("mobilePhone");
		
		NbTeachersUser nbTeachersUser = new NbTeachersUser();
		nbTeachersUser.setMobilePhone(mobilePhone);
		nbTeachersUser.setRealName(realName);
		
		data = new HashMap<String,Object>();
		PageDataList<NbTeachersUser> list = teacherService.findPageList(nbTeachersUser,pageNum,20);
		data.put("data",list);
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/teacher/delete")
	public void doDelete(HttpServletRequest request,HttpServletResponse response) throws IOException{
		int id = Integer.valueOf(request.getParameter("id"));
		
		teacherService.doDelete(id);
		
		data = new HashMap<String,Object>();
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,"删除成功!");
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 编辑
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/teacher/teacher")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) throws Exception{  
    	data = new HashMap<String,Object>();  
    	
    	int id = Integer.valueOf(request.getParameter("id"));
    	NbTeachersUser nbTeachersUser = teacherService.findById(id);
        data.put("data",nbTeachersUser);
        
        ModelAndView mav = new ModelAndView("/admin/teacher",data);
        return mav;  
    }

	/**
	 * 保存
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/teacher/save")
	public void doSave(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException{
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 更新
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/teacher/update")
	public void doUpdate(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException{
		int id = Integer.valueOf(request.getParameter("id"));
		NbTeachersUser nbTeachersUser = teacherService.findById(id);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int areaId1 = Integer.valueOf(request.getParameter("area1"));
		int areaId2 = Integer.valueOf(request.getParameter("area2"));
		int areaId3 = Integer.valueOf(request.getParameter("area3"));
		int jobId1 = Integer.valueOf(request.getParameter("job1"));
		int jobId2 = Integer.valueOf(request.getParameter("job2"));
		int jobId3 = Integer.valueOf(request.getParameter("job3"));
		int topicId = Integer.valueOf(request.getParameter("topicId"));
		
		nbTeachersUser.setBankAccount(request.getParameter("bankAccount"));
		nbTeachersUser.setBankName(request.getParameter("bankName"));
		nbTeachersUser.setBirth(sdf.parse(request.getParameter("birth")));
		nbTeachersUser.setEmail(request.getParameter("email"));
		nbTeachersUser.setGender(Byte.valueOf(request.getParameter("gender")));
		nbTeachersUser.setHighlight(request.getParameter("highlight"));
		nbTeachersUser.setIdCard(request.getParameter("idCard"));
		nbTeachersUser.setIsOnline(Byte.valueOf(request.getParameter("isOnline")));
		nbTeachersUser.setMobilePhone(request.getParameter("mobilePhone"));
		nbTeachersUser.setPhoto(request.getParameter("phone"));
		nbTeachersUser.setQq(request.getParameter("qq"));
		nbTeachersUser.setRealName(request.getParameter("realName"));
		nbTeachersUser.setSpecialDescription(request.getParameter("specialDescription"));
		nbTeachersUser.setStaticAreaName1(areaNameService.findById(areaId1));
		nbTeachersUser.setStaticAreaName2(areaNameService.findById(areaId2));
		nbTeachersUser.setStaticAreaName3(areaNameService.findById(areaId3));
		nbTeachersUser.setStaticJobName1(jobNameService.findById(jobId1));
		nbTeachersUser.setStaticJobName2(jobNameService.findById(jobId2));
		nbTeachersUser.setStaticJobName3(jobNameService.findById(jobId3));
		nbTeachersUser.setStaticFixedTopicName(fixedTopicNameService.getById(Integer.valueOf(topicId)));
		nbTeachersUser.setTalkFace2face((byte)Integer.valueOf(request.getParameter("faceToFace")).intValue());
		nbTeachersUser.setTalkPhoneCall((byte)Integer.valueOf(request.getParameter("talkPhoneCall")).intValue());
		nbTeachersUser.setTalkVideoChat((byte)Integer.valueOf(request.getParameter("talkVideoChat")).intValue());
		nbTeachersUser.setUnitPrice(Integer.valueOf(request.getParameter("unitPrice")));
		nbTeachersUser.setUsername(request.getParameter("username"));
		nbTeachersUser.setWorkStartYear(sdf.parse(request.getParameter("workStartYear")));
		nbTeachersUser.setOpenid(request.getParameter("openid"));

		teacherService.doUpdate(nbTeachersUser);
		
		data = new HashMap<String,Object>();
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,"更新成功!");
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 获取验证码
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/teacher/getCode",method = RequestMethod.GET) 
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws Exception{  
		Map<String,Object> data = new HashMap<String,Object>();
		int code = (int) (Math.random() * 9000 + 1000);
		data.put("teacherCode",code);
		System.out.println("teacherCode:"+code);
		request.getSession().setAttribute("teacherCode",code);
		HttpWebIOHelper._printWebJson(data, response);
    }
	
	
	/**
	 * getter & setter
	 * @return
	 */
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
