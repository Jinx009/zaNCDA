package main.entry.webapp;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import common.helper.ConstantUtil;
import common.helper.HttpWebIOHelper;
import common.helper.StringUtil;
import database.common.PageDataList;
import database.models.AccountModel;
import database.models.NbCommentsFromTeacher;
import database.models.NbOrder;
import database.models.NbStudentsUser;
import database.models.NbTeacherScore;
import database.models.NbTeachersUser;
import service.basicFunctions.CommentsFromTeacherService;
import service.basicFunctions.OrderService;
import service.basicFunctions.TeacherScoreServie;

@Controller
public class OrderWebEntry {
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private TeacherScoreServie teacherScoreServie;
	@Autowired
	private CommentsFromTeacherService commentsFromTeacherService;
	
	private Map<String,Object> data;

	@RequestMapping(value="/order/list",method=RequestMethod.POST)
	public void dataList(HttpServletRequest request,HttpServletResponse response) throws ParseException, IOException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null,endDate = null;
		if(StringUtil.isNotBlank(request.getParameter("startDate"))){
			startDate = sdf.parse(request.getParameter("startDate"));
		}
		if(StringUtil.isNotBlank(request.getParameter("endDate"))){
			endDate = sdf.parse(request.getParameter("endDate"));
		}
		int pageNum = Integer.valueOf(request.getParameter("pageNum"));
		NbOrder nbOrder = new NbOrder();
		nbOrder.setFirstPayTime(startDate);
		nbOrder.setSecondPayTime(endDate);
		NbStudentsUser nbStudentsUser = new NbStudentsUser();
		nbStudentsUser.setMobilePhone(request.getParameter("studentPhone"));
		nbStudentsUser.setRealName(request.getParameter("studentName"));
		NbTeachersUser nbTeachersUser = new NbTeachersUser();
		nbTeachersUser.setRealName(request.getParameter("teacherName"));
		nbTeachersUser.setMobilePhone(request.getParameter("teacherPhone"));

		nbOrder.setNbStudentsUser(nbStudentsUser);
		nbOrder.setNbTeachersUser(nbTeachersUser);
		
		PageDataList<NbOrder> list = orderService.findPageList(nbOrder, pageNum, 20);
		
		data = new HashMap<String, Object>();
		data.put("data",list);
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 获取评论列表
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value = "/comments/list",method = RequestMethod.POST)
	public void getComments(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Integer orderId = Integer.valueOf(request.getParameter("orderId"));
		List<NbCommentsFromTeacher> studentList = commentsFromTeacherService.getByOrderId(orderId);
		List<NbTeacherScore> teacherList = teacherScoreServie.getByOrderId(orderId);
		
		data = new HashMap<String, Object>();
		data.put("teacher",teacherList);
		data.put("student",studentList);
		
		HttpWebIOHelper._printWebJson(data, response);
	}

	/**
	 * 教师约谈
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/tdata/comments",method = RequestMethod.POST)
	public void getTComments(HttpServletRequest request,HttpServletResponse response) throws IOException{
		NbTeachersUser nbTeachersUser = (NbTeachersUser) request.getSession().getAttribute("teacher_session_user");
		List<NbOrder> list = orderService.findByAttr("nbTeachersUser",nbTeachersUser.getId());
		
		data = new HashMap<String, Object>();
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,list);
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 教师个人信息
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping(value = "/tdata/info")
	public void getTInfo(HttpServletResponse response,HttpServletRequest request) throws IOException{
		NbTeachersUser nbTeachersUser = (NbTeachersUser) request.getSession().getAttribute("teacher_session_user");
		
		data = new HashMap<String, Object>();
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,nbTeachersUser);
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 教师个人账户
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping(value = "/tdata/account")
	public void getTAccount(HttpServletResponse response,HttpServletRequest request) throws IOException{
		NbTeachersUser nbTeachersUser = (NbTeachersUser) request.getSession().getAttribute("teacher_session_user");
		List<NbOrder> list = orderService.findByAttr("nbTeachersUser",nbTeachersUser.getId());
		AccountModel accountModel = new AccountModel();
		
		for(int i = 0;i<list.size();i++){
			NbOrder order = list.get(i);
			accountModel.setAllAccount(accountModel.getAllAccount()+order.getFullPrice());
			if(1==order.getFirstPayIsDone()){
				accountModel.setAllPayAccount(accountModel.getAllPayAccount()+order.getFirstPayPrice());
				accountModel.setPartOneAccount(accountModel.getPartOneAccount()+order.getFirstPayPrice());
			}else{
				accountModel.setAllUnPayAccount(accountModel.getAllUnPayAccount()+order.getFirstPayPrice());
				accountModel.setPartOneUnAccount(accountModel.getPartOneUnAccount()+order.getFirstPayPrice());
			}
			if(1==order.getSecondPayIsDone()){
				accountModel.setAllPayAccount(accountModel.getAllPayAccount()+order.getSecondPayPrice());
				accountModel.setPartTwoAccount(accountModel.getPartTwoAccount()+order.getSecondPayPrice());
			}else{
				accountModel.setAllUnPayAccount(accountModel.getAllUnPayAccount()+order.getSecondPayPrice());
				accountModel.setPartTwoUnAccount(accountModel.getPartTwoUnAccount()+order.getSecondPayPrice());
			}
		}
		
		data = new HashMap<String, Object>();
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,list);
		data.put(ConstantUtil.ACCOUNT_MODEL,accountModel);
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
