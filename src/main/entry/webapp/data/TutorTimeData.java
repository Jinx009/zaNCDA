package main.entry.webapp.data;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import service.basicFunctions.TutorService;
import service.basicFunctions.TutorTimeService;
import database.models.Tutor;
import database.models.TutorTime;

@Controller
public class TutorTimeData {

	private Map<String,Object> data;
	
	@Autowired
	private TutorTimeService tutorTimeService;
	@Autowired
	private TutorService tutorService;
	
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
	
	/**
	 * 导师已选时间
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/tutor/data/time",method = RequestMethod.GET)
	public void getMyTime(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Tutor tutor = (Tutor) request.getSession().getAttribute(ConstantUtil.TUTOR_SESSION);
		
		List<TutorTime> list = tutorTimeService.getByTutorId(tutor.getId());
		
		data = new HashMap<String, Object>();
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,list);
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 后台导师时间
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/admin/data/time",method = RequestMethod.GET)
	public void getAdminTime(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Integer id = Integer.valueOf(request.getParameter("id"));
		List<TutorTime> list = tutorTimeService.getByTutorId(id);
		
		data = new HashMap<String, Object>();
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,list);
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	
	/**
	 * 删除时间
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/tutor/data/deleteTime",method = RequestMethod.POST)
	public void deleteTime(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Integer id = Integer.valueOf(request.getParameter("id"));
		tutorTimeService.delete(id);
		
		data = new HashMap<String, Object>();
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,"删除成功!");
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 导师端自己添加时间
	 * @param response
	 * @param request
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "/tutor/data/addTime",method = RequestMethod.POST)
	public void addTime(HttpServletResponse response,HttpServletRequest request) throws ParseException, IOException{
		Tutor tutor = (Tutor) request.getSession().getAttribute(ConstantUtil.TUTOR_SESSION);
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		data = new HashMap<String, Object>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if("1".equals(time)){
			String[] str = new String[]{"8:00-9:00","9:00-10:00","10:00-11:00","11:00-12:00"};
			for(int i = 0;i<str.length;i++){
				TutorTime tutorTime = new TutorTime();
				tutorTime.setqTutor(tutor);
				tutorTime.setRealDate(sdf.parse(date));
				tutorTime.setRealTime(str[i]);
				tutorTime.setStatus(0);
				boolean status = tutorTimeService.checkByDate(date,str[i],tutor.getId());
				if(!status){
					tutorTimeService.save(tutorTime);
				}
				data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
			}
		}else if("2".equals(time)){
			String[] str = new String[]{"12:00-13:00","13:00-14:00","14:00-15:00","15:00-16:00"};
			for(int i = 0;i<str.length;i++){
				TutorTime tutorTime = new TutorTime();
				tutorTime.setqTutor(tutor);
				tutorTime.setRealDate(sdf.parse(date));
				tutorTime.setRealTime(str[i]);
				tutorTime.setStatus(0);
				boolean status = tutorTimeService.checkByDate(date,str[i],tutor.getId());
				if(!status){
					tutorTimeService.save(tutorTime);
				}
				data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
			}
		}else if("3".equals(time)){
			String[] str = new String[]{"16:00-17:00","17:00-18:00","18:00-19:00","19:00-20:00"};
			for(int i = 0;i<str.length;i++){
				TutorTime tutorTime = new TutorTime();
				tutorTime.setqTutor(tutor);
				tutorTime.setRealDate(sdf.parse(date));
				tutorTime.setRealTime(str[i]);
				tutorTime.setStatus(0);
				boolean status = tutorTimeService.checkByDate(date,str[i],tutor.getId());
				if(!status){
					tutorTimeService.save(tutorTime);
				}
				data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
			}
		}else if("4".equals(time)){
			String[] str = new String[]{"8:00-9:00","9:00-10:00","10:00-11:00","11:00-12:00","12:00-13:00","13:00-14:00","14:00-15:00","15:00-16:00","16:00-17:00","17:00-18:00","18:00-19:00","19:00-20:00"};
			for(int i = 0;i<str.length;i++){
				TutorTime tutorTime = new TutorTime();
				tutorTime.setqTutor(tutor);
				tutorTime.setRealDate(sdf.parse(date));
				tutorTime.setRealTime(str[i]);
				tutorTime.setStatus(0);
				boolean status = tutorTimeService.checkByDate(date,str[i],tutor.getId());
				if(!status){
					tutorTimeService.save(tutorTime);
				}
				data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
			}
		}else{
			TutorTime tutorTime = new TutorTime();
			tutorTime.setqTutor(tutor);
			tutorTime.setRealDate(sdf.parse(date));
			tutorTime.setRealTime(time);
			tutorTime.setStatus(0);
			boolean status = tutorTimeService.checkByDate(date,time,tutor.getId());
			if(status){
				data.put(ConstantUtil.RESULT,ConstantUtil.FAILURE);
			}else{
				data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
				tutorTimeService.save(tutorTime);
			}
		}
		data.put(ConstantUtil.ERROR_MSG,"保存成功!");
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 检查导师时间是否占用
	 * @param req
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value = "/customer/data/checkTutorTime",method = RequestMethod.POST)
	public void checkTime(HttpServletRequest req,HttpServletResponse response) throws IOException{
		Integer timeId = Integer.valueOf(req.getParameter("time"));
		TutorTime tutorTime = tutorTimeService.getById(timeId);
		data = new HashMap<String, Object>();
		if(1==tutorTime.getStatus()){
			data.put(ConstantUtil.RESULT,ConstantUtil.FAILURE);
		}else{
			data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		}
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 后台添加导师时间
	 * @param response
	 * @param request
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "/admin/data/addTime",method = RequestMethod.POST)
	public void addAdminTime(HttpServletResponse response,HttpServletRequest request) throws ParseException, IOException{
		Integer id = Integer.valueOf(request.getParameter("id"));
		Tutor tutor = tutorService.find(id);
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		data = new HashMap<String, Object>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if("1".equals(time)){
			String[] str = new String[]{"8:00-9:00","9:00-10:00","10:00-11:00","11:00-12:00"};
			for(int i = 0;i<str.length;i++){
				TutorTime tutorTime = new TutorTime();
				tutorTime.setqTutor(tutor);
				tutorTime.setRealDate(sdf.parse(date));
				tutorTime.setRealTime(str[i]);
				tutorTime.setStatus(0);
				boolean status = tutorTimeService.checkByDate(date,str[i],tutor.getId());
				if(!status){
					tutorTimeService.save(tutorTime);
				}
				data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
			}
		}else if("2".equals(time)){
			String[] str = new String[]{"12:00-13:00","13:00-14:00","14:00-15:00","15:00-16:00"};
			for(int i = 0;i<str.length;i++){
				TutorTime tutorTime = new TutorTime();
				tutorTime.setqTutor(tutor);
				tutorTime.setRealDate(sdf.parse(date));
				tutorTime.setRealTime(str[i]);
				tutorTime.setStatus(0);
				boolean status = tutorTimeService.checkByDate(date,str[i],tutor.getId());
				if(!status){
					tutorTimeService.save(tutorTime);
				}
				data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
			}
		}else if("3".equals(time)){
			String[] str = new String[]{"16:00-17:00","17:00-18:00","18:00-19:00","19:00-20:00"};
			for(int i = 0;i<str.length;i++){
				TutorTime tutorTime = new TutorTime();
				tutorTime.setqTutor(tutor);
				tutorTime.setRealDate(sdf.parse(date));
				tutorTime.setRealTime(str[i]);
				tutorTime.setStatus(0);
				boolean status = tutorTimeService.checkByDate(date,str[i],tutor.getId());
				if(!status){
					tutorTimeService.save(tutorTime);
				}
				data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
			}
		}else if("4".equals(time)){
			String[] str = new String[]{"8:00-9:00","9:00-10:00","10:00-11:00","11:00-12:00","12:00-13:00","13:00-14:00","14:00-15:00","15:00-16:00","16:00-17:00","17:00-18:00","18:00-19:00","19:00-20:00"};
			for(int i = 0;i<str.length;i++){
				TutorTime tutorTime = new TutorTime();
				tutorTime.setqTutor(tutor);
				tutorTime.setRealDate(sdf.parse(date));
				tutorTime.setRealTime(str[i]);
				tutorTime.setStatus(0);
				boolean status = tutorTimeService.checkByDate(date,str[i],tutor.getId());
				if(!status){
					tutorTimeService.save(tutorTime);
				}
				data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
			}
		}else{
			TutorTime tutorTime = new TutorTime();
			tutorTime.setqTutor(tutor);
			tutorTime.setRealDate(sdf.parse(date));
			tutorTime.setRealTime(time);
			tutorTime.setStatus(0);
			boolean status = tutorTimeService.checkByDate(date,time,tutor.getId());
			if(status){
				data.put(ConstantUtil.RESULT,ConstantUtil.FAILURE);
			}else{
				data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
				tutorTimeService.save(tutorTime);
			}
		}
		
		data.put(ConstantUtil.ERROR_MSG,"保存成功!");
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 获取订单老师时间
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/customer/data/orderDate",method = RequestMethod.POST)
	public void getDate(HttpServletRequest  request,HttpServletResponse response) throws IOException{
		Integer tutorId =Integer.valueOf(request.getParameter("tutorId"));
		
		List<TutorTime> list = tutorTimeService.getDate(tutorId);
		
		data = new HashMap<String, Object>();
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,list);
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 获取订单老师时间
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/customer/data/orderTime",method = RequestMethod.POST)
	public void getOrderTime(HttpServletRequest  request,HttpServletResponse response) throws IOException{
		Integer tutorId =Integer.valueOf(request.getParameter("tutorId"));
		String realDate = request.getParameter("realDate");
		
		List<TutorTime> list = tutorTimeService.getTime(tutorId,realDate);
		
		data = new HashMap<String, Object>();
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,list);
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
