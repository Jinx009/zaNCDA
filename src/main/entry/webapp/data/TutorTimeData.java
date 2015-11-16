package main.entry.webapp.data;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import common.helper.ConstantUtil;
import common.helper.HttpWebIOHelper;
import service.basicFunctions.TutorTimeService;
import database.models.TutorTime;

@Controller
public class TutorTimeData {

	private Map<String,Object> data;
	
	@Autowired
	private TutorTimeService tutorTimeService;
	
	/**
	 * 所选导师可选时间
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/customer/data/time")
	public void getTime(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Integer tutorId = Integer.valueOf(request.getParameter("tutorId"));
		
		List<TutorTime> list = tutorTimeService.getByTutorId(tutorId);
		
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,list);
		
		HttpWebIOHelper._printWebJson(data, response);
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
