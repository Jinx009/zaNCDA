package common.helper.tool.util;

import java.util.ArrayList;
import java.util.List;

public class ResultUtil {

	/**
	 * 返回工作年限区间
	 * @param value
	 * @return
	 */
	public static List<Integer> getWorkYears(Integer value){
		List<Integer> list = new ArrayList<Integer>();
		
		if(1==value){
			list.add(0);
			list.add(1);
		}else if(2==value){
			list.add(1);
			list.add(3);
		}else if(3==value){
			list.add(3);
			list.add(5);
		}else if(4==value){
			list.add(5);
			list.add(7);
		}else{
			list.add(7);
			list.add(100);
		}
		return list;
	}

	/**
	 * 返回约谈类型
	 * @param type
	 * @return
	 */
	public static String getType(Integer type) {
		if(1==type){
			return "face_status";
		}else if(2==type){
			return "mobile_status";
		}else{
			return "vedio_status";
		}
	}
	
	
}
