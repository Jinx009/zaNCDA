package test.manualTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.basicFunctions.OperationService;
import service.basicFunctions.OrderService;
import service.basicFunctions.StudentService;
import service.basicFunctions.TeacherService;

import common.helper.HttpWebIOHelper;
import common.helper.nbReturn;
import common.helper.nbStringUtil;


@Controller
public class ManualTest {

	 @Autowired  
	 private StudentService studentService ;
	 @Autowired  
	 private TeacherService teacherService ;
	 @Autowired  
	 private OrderService orderService ;
	 @Autowired  
	 private OperationService operationService ;
	 
	 
	
	@RequestMapping(value = "/test/rantest") 
	public ModelAndView checkToken(){
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("hello", "hellow world!");
		
		ModelAndView mav = new ModelAndView("home",data);
		return mav;
	}
//	
//	@RequestMapping(value = "/testRegisterUser") 
//	public ModelAndView registerUser() throws Exception{
//		Map<String,Object> data = new HashMap<String,Object>();
//		nbReturn verifyResult = userInfoService.RegisterUser("syh", "123456", null, null, "syhAppID");
//		
//		if( verifyResult.isSuccess() ){
//			data.put("verifyRet", ((NbUser)(verifyResult.getObject())).modelToMap());
//		}else{
//			data.put("verifyRet", verifyResult.getRetString() );
//		}
//		
//		ModelAndView mav = new ModelAndView("home",data);
//		return mav;
//	}
//	
//	@RequestMapping(value = "/testVerifyUser") 
//	public void testVerifyUser(HttpServletResponse response,HttpServletRequest request) throws Exception{  
//       //创建模型跟视图，用于渲染页面。并且指定要返回的页面为home页面  
//
//		Boolean needToken = true;
//		nbReturn verifyResult = userInfoService.verifyUser("syh", 
//       												       "123456", 
//       												       "syhAppID",
//       												       "localhost_test",
//       												       null,
//       												       needToken);
////		if( verifyResult.isSuccess() ){
////			if( needToken){
////				data.put("verifyRet", ((NbTokenPublisher)(verifyResult.getObject())).modelToMap() );
////			}else{
////				data.put("verifyRet", ((NbUser)(verifyResult.getObject())).modelToMap() );
////			}
////		}else{
////			data.put("verifyRet", verifyResult.getRetString() );
////		}
//		
//		HttpWebIOHelper.printReturnJson(verifyResult, response);
//	}
//	
	@RequestMapping(value = "/testMD5") 
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
	
	@RequestMapping(value = "/testJsonOutput") 
	public void jsonOutput(HttpServletResponse response,HttpServletRequest request) throws Exception{  
		
		nbReturn ret = new nbReturn();
		Map<String, Object> test = new HashMap<String, Object>();
		String output = "asdf";
		test.put("1", output);
		test.put("2", output);
		ret.setObject(test);
		
		HttpWebIOHelper.printReturnJson(ret, response);
		}
}
