package main.entry.webapp;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import common.helper.ConstantUtil;
import common.helper.HttpWebIOHelper;
import database.common.PageDataList;
import database.models.NbTeachersUser;
import service.basicFunctions.AreaNameService;
import service.basicFunctions.JobNameService;
import service.basicFunctions.TeacherService;

@Controller
public class TeacherWebEntry {
	
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private JobNameService jobNameService;
	@Autowired
	private AreaNameService areaNameService;
	
	private Map<String,Object> data;
	
	/**
	 * 上传图片
	 * 
	 * @return 图片路径
	 * @throws IOException
	 * if has error
	 */
	 @RequestMapping(value = "/uploadImg")  
	 public void upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, ModelMap model,HttpServletResponse response) throws IOException {  
	  
        System.out.println("开始上传");  
        String path = request.getSession().getServletContext().getRealPath("/sp/upload");  
        String fileName = file.getOriginalFilename();  
        System.out.println(path);  
        File targetFile = new File(path, fileName);  
	    if(!targetFile.exists()){  
	        targetFile.mkdirs();  
	    }  
	  
	   try{  
	         file.transferTo(targetFile);  
	    } catch (Exception e) {  
	         e.printStackTrace();  
	    }  
	  
	    data = new HashMap<String,Object>();
	    data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
	    data.put(ConstantUtil.ERROR_MSG,"/sp/upload/"+fileName);
			
		HttpWebIOHelper._printWebJson(data, response);
	}

	 
	
	
	/**
	 * 教师列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/teacher/list")
	public void getList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		int pageNum = Integer.valueOf(request.getParameter("pageNum"));
		String username = request.getParameter("userName");
		String mobilePhone = request.getParameter("mobilePhone");
		
		NbTeachersUser nbTeachersUser = new NbTeachersUser();
		nbTeachersUser.setUsername(username);
		nbTeachersUser.setMobilePhone(mobilePhone);
		
		data = new HashMap<String,Object>();
		PageDataList<NbTeachersUser> list = teacherService.findPageList(nbTeachersUser,pageNum,20);
		data.put("data",list);
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/teacher/delete")
	public void doDelete(HttpServletRequest request,HttpServletResponse response) throws IOException{
		int id = Integer.valueOf(request.getParameter("id"));
		
		teacherService.doDelete(id);
		
		data = new HashMap<String,Object>();
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,"删除成功!");
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 编辑
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/teacher/teacher")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) throws Exception{  
    	data = new HashMap<String,Object>();  
    	
    	int id = Integer.valueOf(request.getParameter("id"));
    	NbTeachersUser nbTeachersUser = teacherService.findById(id);
        data.put("data",nbTeachersUser);
        
        ModelAndView mav = new ModelAndView("/admin/teacher",data);
        return mav;  
    }

	/**
	 * 保存
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/teacher/save")
	public void doSave(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException{
		
		NbTeachersUser nbTeachersUser = new NbTeachersUser();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int areaId1 = Integer.valueOf(request.getParameter("area1"));
		int areaId2 = Integer.valueOf(request.getParameter("area2"));
		int areaId3 = Integer.valueOf(request.getParameter("area3"));
		int jobId1 = Integer.valueOf(request.getParameter("job1"));
		int jobId2 = Integer.valueOf(request.getParameter("job2"));
		int jobId3 = Integer.valueOf(request.getParameter("job3"));
		
		nbTeachersUser.setBankAccount(request.getParameter("bankAccount"));
		nbTeachersUser.setBankName(request.getParameter("bankName"));
		nbTeachersUser.setBirth(sdf.parse(request.getParameter("birth")));
		nbTeachersUser.setEmail(request.getParameter("email"));
		nbTeachersUser.setGender(Byte.valueOf(request.getParameter("gender")));
		nbTeachersUser.setHighlight(request.getParameter("highlight"));
		nbTeachersUser.setIdCard(request.getParameter("idCard"));
		nbTeachersUser.setIsOnline(Byte.valueOf(request.getParameter("isOnline")));
		nbTeachersUser.setMobilePhone(request.getParameter("mobilePhone"));
		nbTeachersUser.setPhoto(request.getParameter("phone"));
		nbTeachersUser.setQq(request.getParameter("qq"));
		nbTeachersUser.setRealName(request.getParameter("realName"));
		nbTeachersUser.setSpecialDescription(request.getParameter("specialDescription"));
		nbTeachersUser.setStaticAreaName1(areaNameService.findById(areaId1));
		nbTeachersUser.setStaticAreaName2(areaNameService.findById(areaId2));
		nbTeachersUser.setStaticAreaName3(areaNameService.findById(areaId3));
		nbTeachersUser.setStaticJobName1(jobNameService.findById(jobId1));
		nbTeachersUser.setStaticJobName2(jobNameService.findById(jobId2));
		nbTeachersUser.setStaticJobName3(jobNameService.findById(jobId3));
		//nbTeachersUser.setStaticFixedTopicName(null);
		nbTeachersUser.setTalkFace2face((byte)Integer.valueOf(request.getParameter("faceToFace")).intValue());
		nbTeachersUser.setTalkPhoneCall((byte)Integer.valueOf(request.getParameter("talkPhoneCall")).intValue());
		nbTeachersUser.setTalkVideoChat((byte)Integer.valueOf(request.getParameter("talkVideoChat")).intValue());
		nbTeachersUser.setUnitPrice(Integer.valueOf(request.getParameter("unitPrice")));
		nbTeachersUser.setUsername(request.getParameter("username"));
		nbTeachersUser.setWorkStartYear(sdf.parse(request.getParameter("workStartYear")));

		teacherService.doSave(nbTeachersUser);
		
		data = new HashMap<String,Object>();
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,"保存成功!");
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 更新
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/teacher/update")
	public void doUpdate(HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException{
		int id = Integer.valueOf(request.getParameter("id"));
		NbTeachersUser nbTeachersUser = teacherService.findById(id);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int areaId1 = Integer.valueOf(request.getParameter("area1"));
		int areaId2 = Integer.valueOf(request.getParameter("area2"));
		int areaId3 = Integer.valueOf(request.getParameter("area3"));
		int jobId1 = Integer.valueOf(request.getParameter("job1"));
		int jobId2 = Integer.valueOf(request.getParameter("job2"));
		int jobId3 = Integer.valueOf(request.getParameter("job3"));
		int topicId = Integer.valueOf(request.getParameter("topicId"));
		
		nbTeachersUser.setBankAccount(request.getParameter("bankAccount"));
		nbTeachersUser.setBankName(request.getParameter("bankName"));
		nbTeachersUser.setBirth(sdf.parse(request.getParameter("birth")));
		nbTeachersUser.setEmail(request.getParameter("email"));
		nbTeachersUser.setGender(Byte.valueOf(request.getParameter("gender")));
		nbTeachersUser.setHighlight(request.getParameter("highlight"));
		nbTeachersUser.setIdCard(request.getParameter("idCard"));
		nbTeachersUser.setIsOnline(Byte.valueOf(request.getParameter("isOnline")));
		nbTeachersUser.setMobilePhone(request.getParameter("mobilePhone"));
		nbTeachersUser.setPhoto(request.getParameter("phone"));
		nbTeachersUser.setQq(request.getParameter("qq"));
		nbTeachersUser.setRealName(request.getParameter("realName"));
		nbTeachersUser.setSpecialDescription(request.getParameter("specialDescription"));
		nbTeachersUser.setStaticAreaName1(areaNameService.findById(areaId1));
		nbTeachersUser.setStaticAreaName2(areaNameService.findById(areaId2));
		nbTeachersUser.setStaticAreaName3(areaNameService.findById(areaId3));
		nbTeachersUser.setStaticJobName1(jobNameService.findById(jobId1));
		nbTeachersUser.setStaticJobName2(jobNameService.findById(jobId2));
		nbTeachersUser.setStaticJobName3(jobNameService.findById(jobId3));
		//nbTeachersUser.setStaticFixedTopicName(null);
		nbTeachersUser.setTalkFace2face((byte)Integer.valueOf(request.getParameter("faceToFace")).intValue());
		nbTeachersUser.setTalkPhoneCall((byte)Integer.valueOf(request.getParameter("talkPhoneCall")).intValue());
		nbTeachersUser.setTalkVideoChat((byte)Integer.valueOf(request.getParameter("talkVideoChat")).intValue());
		nbTeachersUser.setUnitPrice(Integer.valueOf(request.getParameter("unitPrice")));
		nbTeachersUser.setUsername(request.getParameter("username"));
		nbTeachersUser.setWorkStartYear(sdf.parse(request.getParameter("workStartYear")));

		teacherService.doUpdate(nbTeachersUser);
		
		data = new HashMap<String,Object>();
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,"更新成功!");
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	
	/**
	 * getter & setter
	 * @return
	 */
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
