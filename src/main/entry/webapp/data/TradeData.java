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

import common.helper.ConstantUtil;
import common.helper.HttpWebIOHelper;
import service.basicFunctions.TradeService;
import database.models.Trade;

@Controller
public class TradeData {

	private Map<String,Object> data;
	private List<Trade> list;
	
	@Autowired
	private TradeService tradeService;
	
	/**
	 * 所有行业信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/trade/data/list")
	public void tradeList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String, Object>();
		
		list = tradeService.findList();
		
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,list);
		
		HttpWebIOHelper._printWebJson(data, response);
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public void setList(List<Trade> list) {
		this.list = list;
	}
}
