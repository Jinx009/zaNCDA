package main.entry.webapp;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import service.basicFunctions.FixedTopicNameService;
import common.helper.ConstantUtil;
import common.helper.HttpWebIOHelper;
import database.models.StaticFixedTopicName;

@Controller
public class FixedTopicNameWebEntry {

	@Autowired
	private FixedTopicNameService fixedTopicNameService;
	
	private Map<String,Object> data;
	
	/**
	 * 父类列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/fixedTopicName/list")
	public void getFatherList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String, Object>();
		
		String hql = " from StaticFixedTopicName  group by parentName ";
		List<StaticFixedTopicName> list = fixedTopicNameService.getByHql(hql);
		
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,list);
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	
	/**
	 * 全部列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/fixedTopicName/allList")
	public void getData(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String, Object>();
		List<StaticFixedTopicName> list = fixedTopicNameService.findAll();
		
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,list);
		HttpWebIOHelper._printWebJson(data, response);
	}
}
