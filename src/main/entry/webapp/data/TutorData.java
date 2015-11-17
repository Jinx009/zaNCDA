package main.entry.webapp.data;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.basicFunctions.TopicService;
import service.basicFunctions.TradeService;
import service.basicFunctions.TutorService;
import common.helper.ConstantUtil;
import common.helper.HttpWebIOHelper;
import common.helper.StringUtil;
import database.common.PageDataList;
import database.models.Topic;
import database.models.Trade;
import database.models.Tutor;

@Controller
public class TutorData {
	private Map<String,Object> data;
	private Tutor tutor;
	private Trade trade;
	private Trade trade2;
	private Topic topic;
	
	@Autowired
	private TutorService tutorService;
	
	@Autowired
	private TradeService tradeService;
	
	@Autowired
	private TopicService topicService;
	
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
	 * 保存第一步
	 * @param response
	 * @param request
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "/tutor/data/saveInfoOne",method = RequestMethod.POST)
	public void saveInfoOne(HttpServletResponse response,HttpServletRequest request) throws ParseException, IOException{
		data = new HashMap<String, Object>();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Integer tradeId = Integer.valueOf(request.getParameter("tradeId"));
		trade = tradeService.find(tradeId);
		
		tutor = (Tutor) request.getSession().getAttribute(ConstantUtil.TUTOR_SESSION);
		tutor.setBankCard(request.getParameter("bankCard"));
		tutor.setBankName(request.getParameter("bankName"));
		tutor.setBirthday(sdf.parse(request.getParameter("birthday")));
		tutor.setEmail(request.getParameter("email"));
		tutor.setSex(request.getParameter("sex"));
		tutor.setqTrade(trade);
		tutor.setQq(request.getParameter("qq"));
		tutor.setWechatName(request.getParameter("wechatName"));
		tutor.setIdNumber(request.getParameter("idNumber"));

		tutorService.update(tutor);
		
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,"修改成功!");
		
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
	
	/**
	 * 教师列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/tutor/data/list")
	public void getCommonList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String,Object>();
		List<Tutor> list = tutorService.findAll();
		List<Tutor> result = new ArrayList<Tutor>();
		for(int i = 0;i<list.size();i++){
			tutor = list.get(i);
			tutor.setqTrade(tradeService.find(11111));
			result.add(tutor);
		}
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,result);
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 教师列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/tutpr/data/selectList")
	public void getSelectList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String,Object>();
		
		Integer topicId = Integer.valueOf(request.getParameter("id"));
		Integer tradeId = Integer.valueOf(request.getParameter("trade"));
		Integer areaId = Integer.valueOf(request.getParameter("area"));
		Integer workYears = Integer.valueOf(request.getParameter("workYears"));
		Integer type = Integer.valueOf(request.getParameter("type"));
		
		trade = tradeService.find(tradeId);
		trade2 = tradeService.find(areaId);
		topic =topicService.find(topicId);
		
		tutor = new Tutor();
		tutor.setAreaOne(trade2);
		tutor.setTradeOne(trade);
		tutor.setqTopic(topic);
		
		
		List<Tutor> list = tutorService.findSelectList(tutor,type,workYears);
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,list);
		
		HttpWebIOHelper._printWebJson(data, response);
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}

	public void setTrade2(Trade trade2) {
		this.trade2 = trade2;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}
}
