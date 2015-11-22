package main.entry.webapp.data;

import java.io.IOException;
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

import service.basicFunctions.OrderService;
import service.basicFunctions.ScoreService;
import common.helper.ConstantUtil;
import common.helper.HttpWebIOHelper;
import database.models.Order;
import database.models.Score;

@Controller
public class ScoreData {

	private Map<String,Object> data;
	private List<Score> list;
	
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private OrderService orderService;
	
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

	/**
	 * 保存
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/score/data/save" ,method = RequestMethod.POST)
	public void saveScore(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String, Object>();
		
		Integer orderId =  Integer.valueOf(request.getParameter("orderId"));
		Integer oneScore = Integer.valueOf(request.getParameter("oneScore"));
		Integer twoScore = Integer.valueOf(request.getParameter("twoScore"));
		Integer threeScore = Integer.valueOf(request.getParameter("threeScore"));
		Integer fourScore = Integer.valueOf(request.getParameter("fourScore"));
		String content = request.getParameter("content");
		
		Order order = orderService.getById(orderId);
		
		Score score = new Score();
		score.setAddTime(new Date());
		score.setFourScore(fourScore);
		score.setOneScore(oneScore);
		score.setqOrder(order);
		score.setThreeScore(threeScore);
		score.setTwoScore(twoScore);
		score.setContent(content);
		
		scoreService.save(score);
		
		if(3==order.getStatus()){
			order.setStatus(4);
		}else{
			order.setStatus(2);
		}
		orderService.doUpdate(order);
		
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,"修改成功!");
		
		HttpWebIOHelper._printWebJson(data, response);
	}

	
	
	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public void setList(List<Score> list) {
		this.list = list;
	}

	
}
