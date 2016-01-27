package main.entry.webapp.data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
		cell = row.createCell((short)2);
		cell.setCellValue("擅长行业1");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("擅长行业2");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("擅长行业3");
		cell.setCellStyle(style);
		cell = row.createCell((short) 5);
		cell.setCellValue("擅长领域1");
		cell.setCellStyle(style);
		cell = row.createCell((short) 6);
		cell.setCellValue("擅长领域2");
		cell.setCellStyle(style);
		cell = row.createCell((short) 7);
		cell.setCellValue("擅长领域3");
		cell.setCellStyle(style);
		cell = row.createCell((short) 8);
		cell.setCellValue("擅长主题");
		cell.setCellStyle(style);
		cell = row.createCell((short) 9);
		cell.setCellValue("约谈价格");
		cell.setCellStyle(style);
		int index = 1;
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(index);
			Tutor tutor = list.get(i);
			if(null!=tutor.getqTopic()){
				row.createCell((short) 0).setCellValue(tutor.getRealName());
				row.createCell((short) 1).setCellValue(tutor.getMobilePhone());
				row.createCell((short) 2).setCellValue(tutor.getTradeOne().getTradeName());
				row.createCell((short) 3).setCellValue(tutor.getTradeTwo().getTradeName());
				row.createCell((short) 4).setCellValue(tutor.getTradeThree().getTradeName());
				row.createCell((short) 5).setCellValue(tutor.getAreaOne().getTradeName());
				row.createCell((short) 6).setCellValue(tutor.getAreaTwo().getTradeName());
				row.createCell((short) 7).setCellValue(tutor.getAreaThree().getTradeName());
				row.createCell((short) 8).setCellValue(tutor.getqTopic().getName());
				row.createCell((short) 9).setCellValue(String.valueOf(tutor.getFacePrice()));
				
				index++;
			}
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
		HSSFSheet sheet = wb.createSheet("顾客表");
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		sheet.setDefaultColumnWidth(100);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("顾客姓名");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("顾客电话");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("顾客qq");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("顾客邮箱");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("顾客微信号");
		cell.setCellStyle(style);
		
		int index =1;
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(index);
			Customer customer = list.get(i);
			
			row.createCell((short) 0).setCellValue(customer.getRealName());
			row.createCell((short) 1).setCellValue(customer.getMobilePhone());
			row.createCell((short) 2).setCellValue(customer.getQq());
			row.createCell((short) 3).setCellValue(customer.getEmail());
			row.createCell((short) 4).setCellValue(customer.getWechatName());
			
			index++;
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
		cell.setCellValue("顾客姓名");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("导师姓名");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("约谈日期");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("约谈时间");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("约谈主题");
		cell.setCellStyle(style);
		cell = row.createCell((short) 5);
		cell.setCellValue("订单状态");
		cell.setCellStyle(style);
		cell = row.createCell((short) 6);
		cell.setCellValue("约谈详细主题");
		cell.setCellStyle(style);
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 1);
			Order order = list.get(i);
			row.createCell((short) 0).setCellValue(order.getqCustomer().getRealName());
			row.createCell((short) 1).setCellValue(order.getqTutor().getRealName());
			row.createCell((short) 2).setCellValue(sdf.format(order.getqTutorTime().getRealDate()));
			row.createCell((short) 3).setCellValue(order.getqTutorTime().getRealTime());
			if(null!=order.getTopic()){
				row.createCell((short) 4).setCellValue(order.getTopic().getName());
			}else{
				row.createCell((short) 4).setCellValue("");
			}
			row.createCell((short) 5).setCellValue(getStatus(order.getStatus()));
			row.createCell((short) 6).setCellValue(order.getTopicContent());
		}
		
		FileOutputStream fout = new FileOutputStream(path+"/"+currentTime+".xls");
		wb.write(fout);
		fout.close();
		
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,currentTime+".xls");
		
		HttpWebIOHelper._printWebJson(data, response);
	}

	public static String getStatus(Integer status){
		if(0==status){
			return "未支付";
		}
		if(1==status){
			return "约谈中";
		}
		if(2==status){
			return "导师已小结";
		}
		if(3==status){
			return "顾客已评价";
		}
		if(4==status){
			return "双方已互评";
		}
		if(5==status){
			return "订单已取消";
		}
		
		return null;
	}
	
	
	/**
	 * 下载顾客信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/excel/pay",method = RequestMethod.GET)
	public void payExcel(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String, Object>();
		long currentTime = Calendar.getInstance().getTimeInMillis();
        String path = request.getSession().getServletContext().getRealPath("/sp/excel/pay/");  
		List<Order> list = orderService.findAll();
		
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("兑付表");
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		sheet.setDefaultColumnWidth(100);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("顾客姓名");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("导师姓名");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("兑付状态");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("订单金额");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("实际金额");
		cell.setCellStyle(style);
		cell = row.createCell((short) 5);
		cell.setCellValue("手续比例");
		cell.setCellStyle(style);
		cell = row.createCell((short) 6);
		cell.setCellValue("银行卡号");
		cell.setCellStyle(style);
		
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 1);
			Order order = list.get(i);
			row.createCell((short) 0).setCellValue(order.getqCustomer().getRealName());
			row.createCell((short) 1).setCellValue(order.getqTutor().getRealName());
			if(1==order.getPayStatus()){
				row.createCell((short) 2).setCellValue("已兑付");
			}else{
				row.createCell((short) 2).setCellValue("未兑付");
			}
			row.createCell((short) 3).setCellValue(checkDouble(order.getPrice()));
			row.createCell((short) 4).setCellValue(checkDouble(order.getPayMoney()));
			row.createCell((short) 5).setCellValue(checkDouble(order.getProcedures()));
			row.createCell((short) 6).setCellValue(checkString(order.getBankName()));
		}
		
		FileOutputStream fout = new FileOutputStream(path+"/"+currentTime+".xls");
		wb.write(fout);
		fout.close();
		
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,currentTime+".xls");
		
		HttpWebIOHelper._printWebJson(data, response);
	}

	public String checkDouble(Double data){
		if(null!=data){
			return String.valueOf(data);
		}
		return "";
	}
	
	public String checkString(String data){
		if(null!=data){
			return String.valueOf(data);
		}
		return "";
	}


	
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
