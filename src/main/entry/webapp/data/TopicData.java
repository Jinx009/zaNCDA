package main.entry.webapp.data;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import common.helper.ConstantUtil;
import common.helper.HttpWebIOHelper;
import service.basicFunctions.TopicService;
import database.models.Topic;

@Controller
public class TopicData {

	private Map<String,Object> data;
	
	@Autowired
	private TopicService topicService;

	/**
	 * 获取主题
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/topic/data/list",method=RequestMethod.GET)
	public void getTopic(HttpServletResponse response) throws IOException{
		data = new HashMap<String, Object>();
		
		List<Topic> list = topicService.findAll();
		
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,list);
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
