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

import common.helper.ConstantUtil;
import common.helper.HttpWebIOHelper;
import database.models.StaticJobName;
import service.basicFunctions.JobNameService;

@Controller
public class JobNameWebEntry {

	@Autowired
	private JobNameService jobNameService;
	
	private Map<String,Object> data;
	
	/**
	 * 列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/jobname/list")
	public void getJobName(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String, Object>();
		List<StaticJobName> list = jobNameService.findAll();
		data.put("data",list);
		HttpWebIOHelper._printWebJson(data, response);
	}

	/**
	 * 修改
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/jobname/update")
	public void updateJobName(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String, Object>();
		
		StaticJobName staticJobName = jobNameService.findById(Integer.valueOf(request.getParameter("id")));
		staticJobName.setJobName(request.getParameter("jobName"));
		
		jobNameService.updateJobName(staticJobName);
		
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,"修改成功!");
		
		data.put("data",data);
		HttpWebIOHelper._printWebJson(data, response);
	}

	/**
	 * 删除
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/jobname/delete")
	public void deleteJobName(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String, Object>();
		
		jobNameService.deleteJobName(Integer.valueOf(request.getParameter("id")));
		
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,"删除成功!");
		
		data.put("data",data);
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 保存
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/jobname/save")
	public void saveJobName(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String, Object>();
		
		StaticJobName staticJobName = new StaticJobName();
		staticJobName.setJobName(request.getParameter("jobName"));
		
		jobNameService.saveJobName(staticJobName);
		
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,"保存成功!");
		
		data.put("data",data);
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * getter&setter
	 * @return
	 */
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
}
