package main.entry.webapp.data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import common.helper.ConstantUtil;
import common.helper.HttpWebIOHelper;
import database.models.Customer;
import database.models.Order;
import database.models.Tutor;
import service.basicFunctions.CustomerService;
import service.basicFunctions.OrderService;
import service.basicFunctions.TutorService;

@Controller
public class ExcelData {

	private Map<String,Object> data;
	
	@Autowired
	private TutorService tutorService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 下载导师信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/excel/tutor",method = RequestMethod.GET)
	public void tutorExcel(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String, Object>();
		long currentTime = Calendar.getInstance().getTimeInMillis();
        String path = request.getSession().getServletContext().getRealPath("/sp/excel/tutor/");  
		List<Tutor> list = tutorService.findAll();
		
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("导师表");
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		sheet.setDefaultColumnWidth(100);

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("导师姓名");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("导师电话");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);

		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 1);
			Tutor tutor = list.get(i);
			row.createCell((short) 0).setCellValue(tutor.getRealName());
			row.createCell((short) 1).setCellValue(tutor.getMobilePhone());
		}
		
		FileOutputStream fout = new FileOutputStream(path+"/"+currentTime+".xls");
		wb.write(fout);
		fout.close();
		
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,currentTime+".xls");
		
		HttpWebIOHelper._printWebJson(data, response);
	}

	/**
	 * 下载顾客信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/excel/customer",method = RequestMethod.GET)
	public void customerExcel(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String, Object>();
		long currentTime = Calendar.getInstance().getTimeInMillis();
        String path = request.getSession().getServletContext().getRealPath("/sp/excel/customer/");  
		List<Customer> list = customerService.findAll();
		
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("学生表");
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		sheet.setDefaultColumnWidth(100);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("学生姓名");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("学生电话");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);

		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 1);
			Customer customer = list.get(i);
			row.createCell((short) 0).setCellValue(customer.getRealName());
			row.createCell((short) 1).setCellValue(customer.getMobilePhone());
		}
		
		FileOutputStream fout = new FileOutputStream(path+"/"+currentTime+".xls");
		wb.write(fout);
		fout.close();
		
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,currentTime+".xls");
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 下载顾客信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/excel/order",method = RequestMethod.GET)
	public void orderExcel(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String, Object>();
		long currentTime = Calendar.getInstance().getTimeInMillis();
        String path = request.getSession().getServletContext().getRealPath("/sp/excel/order/");  
		List<Order> list = orderService.findAll();
		
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("约谈表");
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		sheet.setDefaultColumnWidth(100);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("学生姓名");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("导师姓名");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);

		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 1);
			Order order = list.get(i);
			row.createCell((short) 0).setCellValue(order.getqCustomer().getRealName());
			row.createCell((short) 1).setCellValue(order.getqTutor().getRealName());
		}
		
		FileOutputStream fout = new FileOutputStream(path+"/"+currentTime+".xls");
		wb.write(fout);
		fout.close();
		
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,currentTime+".xls");
		
		HttpWebIOHelper._printWebJson(data, response);
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
