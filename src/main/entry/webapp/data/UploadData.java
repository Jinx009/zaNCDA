package main.entry.webapp.data;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import common.helper.ConstantUtil;
import common.helper.HttpWebIOHelper;

@Controller
public class UploadData {

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
        
        long currentTime = Calendar.getInstance().getTimeInMillis();
        String path = request.getSession().getServletContext().getRealPath("/sp/upload/");  
        String fileName = file.getOriginalFilename();
        System.out.println(path);  
        
        File targetFile = new File(path,String.valueOf(currentTime)+fileName);  
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
	    data.put(ConstantUtil.ERROR_MSG,"/sp/upload/"+String.valueOf(currentTime)+fileName);
		HttpWebIOHelper._printWebJson(data, response);
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
