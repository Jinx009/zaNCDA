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
import database.models.NbAdminUser;
import database.models.StaticAreaName;
import service.basicFunctions.AreaNameService;

@Controller
public class AreaNameWebEntry {
	
	@Autowired
	private AreaNameService areaNameService;
	
private Map<String,Object> data;

	/**
	 * 擅长领域
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/areaname") 
	public String areaname(HttpServletRequest request, HttpServletResponse response) throws Exception{  
		NbAdminUser nbAdminUser = (NbAdminUser) request.getSession().getAttribute("admin_session_user");
		if(null!=nbAdminUser){
			 return "/admin/areaname";  
		}
		return "/admin/login";  
	}

	/**
	 * 擅长领域列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/areaname/list")
	public void getJobName(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String, Object>();
		List<StaticAreaName> list = areaNameService.findAll();
		data.put("data",list);
		HttpWebIOHelper._printWebJson(data, response);
	}

	/**
	 * 修改
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/areaname/update")
	public void updateJobName(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String, Object>();
		
		StaticAreaName staticAreaName = areaNameService.findById(Integer.valueOf(request.getParameter("id")));
		staticAreaName.setAreaName(request.getParameter("areaName"));
		
		areaNameService.updateAreaName(staticAreaName);
		
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
	@RequestMapping(value = "/areaname/delete")
	public void deleteJobName(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String, Object>();
		
		areaNameService.deleteAreaName(Integer.valueOf(request.getParameter("id")));
		
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
	@RequestMapping(value = "/areaname/save")
	public void saveJobName(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String, Object>();
		
		StaticAreaName staticAreaName = new StaticAreaName();
		staticAreaName.setAreaName(request.getParameter("areaName"));
		
		areaNameService.saveAreaName(staticAreaName);
		
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
