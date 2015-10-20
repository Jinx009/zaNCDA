package main.entry.webapp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.basicFunctions.OperationService;
import service.basicFunctions.OrderService;
import service.basicFunctions.StudentService;
import service.basicFunctions.TeacherService;

@Controller
public class OperatorWebEntry {

	@Autowired  
	 private StudentService studentService ;
	 
	 @Autowired  
	 private TeacherService teacherService ;
	 
	 @Autowired  
	 private OrderService orderService ;
	 
	 @Autowired  
	 private OperationService operationService ;
	 
	 @RequestMapping(value = "/indexH") 
	    public ModelAndView index() throws Exception{  
	        //创建模型跟视图，用于渲染页面。并且指定要返回的页面为home页面  
	    	Map<String,Object> data = new HashMap<String,Object>();  
	        data.put("helloWorld","helloWorld");
	        
	        //前端渲染在home.jsp里实现。 ModelAndView的第一个参数就是jsp文件名
	        ModelAndView mav = new ModelAndView("home",data);
	        return mav;  
	    }
}
