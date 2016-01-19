package database.models;

import org.springframework.beans.BeanUtils;

public class TutorModel extends Tutor{

	public static TutorModel instance(Tutor tutor){
		TutorModel model = new TutorModel();
		BeanUtils.copyProperties(tutor, model);
		return model;
	}
	
	private String t1;
	private String t2;
	private String t3;
	private String a1;
	private String a2;
	private String a3;
	public String getT1() {
		return t1;
	}
	public void setT1(String t1) {
		this.t1 = t1;
	}
	public String getT2() {
		return t2;
	}
	public void setT2(String t2) {
		this.t2 = t2;
	}
	public String getT3() {
		return t3;
	}
	public void setT3(String t3) {
		this.t3 = t3;
	}
	public String getA1() {
		return a1;
	}
	public void setA1(String a1) {
		this.a1 = a1;
	}
	public String getA2() {
		return a2;
	}
	public void setA2(String a2) {
		this.a2 = a2;
	}
	public String getA3() {
		return a3;
	}
	public void setA3(String a3) {
		this.a3 = a3;
	}
	
	
	
	
}
