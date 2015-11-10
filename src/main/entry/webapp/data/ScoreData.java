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
import service.basicFunctions.ScoreService;
import database.models.Score;

@Controller
public class ScoreData {

	private Map<String,Object> data;
	
	private Score score;
	
	private List<Score> list;
	
	@Autowired
	private ScoreService scoreService;
	
	/**
	 * 顾客评分
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/score/data/score" ,method = RequestMethod.POST)
	public void getScore(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String, Object>();
		
		Integer id =  Integer.valueOf(request.getParameter("id"));
		list = scoreService.getByAttr(id);
		
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

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public List<Score> getList() {
		return list;
	}

	public void setList(List<Score> list) {
		this.list = list;
	}
}
