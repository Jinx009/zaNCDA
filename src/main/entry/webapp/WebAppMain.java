package main.entry.webapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import common.helper.nbStringUtil;
import service.basicFunctions.OperationService;
import service.basicFunctions.OrderService;
import service.basicFunctions.StudentService;
import service.basicFunctions.TeacherService;



@Controller
public class WebAppMain {

	 @Autowired  
	 private StudentService studentService ;
	 
	 @Autowired  
	 private TeacherService teacherService ;
	 
	 @Autowired  
	 private OrderService orderService ;
	 
	 @Autowired  
	 private OperationService operationService ;
	 
	 
	@RequestMapping(value = "/index") 
    public ModelAndView index() throws Exception{  
        //创建模型跟视图，用于渲染页面。并且指定要返回的页面为home页面  
    	Map<String,Object> data = new HashMap<String,Object>();  
        data.put("helloWorld","helloWorld");
        
        ModelAndView mav = new ModelAndView("home",data);
        return mav;  
    }
	 
	@RequestMapping(value = "/index2") 
    public ModelAndView index2() throws Exception{  
		SimpleDateFormat dateformat1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatedDate=dateformat1.format(new Date());
		String text = "a2345678901234567890123456789012a2345678901234567890123456789012a2345678901234567890123456789012"+formatedDate;
		String ret = nbStringUtil.encryptMD5(text);
		Map<String,Object> data = new HashMap<String,Object>();  
        data.put("helloWorld", ret );
        ModelAndView mav = new ModelAndView("home",data);
        return mav;  
    }
	
}
