package main.entry.webapp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import common.helper.HttpWebIOHelper;
import database.common.PageDataList;
import database.models.NbTeachersUser;
import service.basicFunctions.TeacherService;

@Controller
public class TeacherWebEntry {
	
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping(value = "/teacher/list")
	public void getTeacherList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		int pageNum = Integer.valueOf(request.getParameter("pageNum"));
		String username = request.getParameter("userName");
		String mobilePhone = request.getParameter("mobilePhone");
		
		NbTeachersUser nbTeachersUser = new NbTeachersUser();
		nbTeachersUser.setUsername(username);
		nbTeachersUser.setMobilePhone(mobilePhone);
		
		Map<String,Object> map = new HashMap<String,Object>();
		PageDataList<NbTeachersUser> data = teacherService.findPageList(nbTeachersUser,pageNum,20);
		map.put("data",data);
		
		HttpWebIOHelper._printWebJson(data, response);
	}
}
