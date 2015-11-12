package main.entry.webapp.data;

import java.io.IOException;
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
import service.basicFunctions.CommentsService;
import database.models.Comments;

@Controller
public class CommentsData {

	private Map<String,Object> data;
	
	private List<Comments> list;
	
	@Autowired
	private CommentsService commentsService;

	/**
	 * 导师小结
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/comments/data/comments",method=RequestMethod.POST)
	public void commentsData(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Integer id = Integer.valueOf(request.getParameter("id"));
		
		list = commentsService.getByOrderId(id);
		
		data = new HashMap<String, Object>();
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
	public List<Comments> getList() {
		return list;
	}
	public void setList(List<Comments> list) {
		this.list = list;
	}
}
