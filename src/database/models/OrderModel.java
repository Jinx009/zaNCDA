package database.models;

import org.springframework.beans.BeanUtils;

public class OrderModel extends Order{
	public static OrderModel instance(Order order){
		OrderModel model = new OrderModel();
		BeanUtils.copyProperties(order, model);
		return model;
	}
	
	private String tName;
	private String cName;
	private String qName;
	private String photoPath;
	
	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getqName() {
		return qName;
	}
	public void setqName(String qName) {
		this.qName = qName;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
	
}
