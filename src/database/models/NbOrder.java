package database.models;

import java.io.Serializable;

import javax.persistence.*;

import database.common.nbBaseModel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * The persistent class for the nb_order database table.
 * 
 */
@Entity
@Table(name="nb_order")
@NamedQuery(name="NbOrder.findAll", query="SELECT n FROM NbOrder n")
public class NbOrder implements Serializable,nbBaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="first_pay_flow_code", length=64)
	private String firstPayFlowCode;

	@Column(name="first_pay_is_done", nullable=false)
	private Byte firstPayIsDone;

	@Column(name="first_pay_price")
	private Double firstPayPrice;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="first_pay_time")
	private Date firstPayTime;

	@Column(name="full_price", nullable=false)
	private Double fullPrice;

	@Column(name="second_pay_flow_code", length=64)
	private String secondPayFlowCode;

	@Column(name="second_pay_is_done", nullable=false)
	private Byte secondPayIsDone;

	@Column(name="second_pay_price")
	private Double secondPayPrice;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="second_pay_time")
	private Date secondPayTime;

	@OneToOne
	@JoinColumn(name="student_id")
	private NbStudentsUser nbStudentsUser;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="student_prefered_date", nullable=false)
	private Date studentPreferedDate;

	@OneToOne
	@JoinColumn(name="teacher_id")
	private NbTeachersUser nbTeachersUser;
	
	@Column(name="course_topic", nullable=false)
	private String courseTopic;

	@Column(name = "comments_status")
	private Integer commentsStatus;
	
	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getFirstPayFlowCode() {
		return firstPayFlowCode;
	}



	public void setFirstPayFlowCode(String firstPayFlowCode) {
		this.firstPayFlowCode = firstPayFlowCode;
	}



	public Byte getFirstPayIsDone() {
		return firstPayIsDone;
	}



	public void setFirstPayIsDone(Byte firstPayIsDone) {
		this.firstPayIsDone = firstPayIsDone;
	}



	public Double getFirstPayPrice() {
		return firstPayPrice;
	}



	public void setFirstPayPrice(Double firstPayPrice) {
		this.firstPayPrice = firstPayPrice;
	}



	public Date getFirstPayTime() {
		return firstPayTime;
	}



	public void setFirstPayTime(Date firstPayTime) {
		this.firstPayTime = firstPayTime;
	}



	public Double getFullPrice() {
		return fullPrice;
	}



	public void setFullPrice(Double fullPrice) {
		this.fullPrice = fullPrice;
	}



	public String getSecondPayFlowCode() {
		return secondPayFlowCode;
	}



	public void setSecondPayFlowCode(String secondPayFlowCode) {
		this.secondPayFlowCode = secondPayFlowCode;
	}



	public Byte getSecondPayIsDone() {
		return secondPayIsDone;
	}



	public void setSecondPayIsDone(Byte secondPayIsDone) {
		this.secondPayIsDone = secondPayIsDone;
	}



	public Double getSecondPayPrice() {
		return secondPayPrice;
	}



	public void setSecondPayPrice(Double secondPayPrice) {
		this.secondPayPrice = secondPayPrice;
	}



	public Date getSecondPayTime() {
		return secondPayTime;
	}



	public void setSecondPayTime(Date secondPayTime) {
		this.secondPayTime = secondPayTime;
	}



	public NbStudentsUser getNbStudentsUser() {
		return nbStudentsUser;
	}



	public void setNbStudentsUser(NbStudentsUser nbStudentsUser) {
		this.nbStudentsUser = nbStudentsUser;
	}



	public Date getStudentPreferedDate() {
		return studentPreferedDate;
	}



	public void setStudentPreferedDate(Date studentPreferedDate) {
		this.studentPreferedDate = studentPreferedDate;
	}



	public NbTeachersUser getNbTeachersUser() {
		return nbTeachersUser;
	}



	public void setNbTeachersUser(NbTeachersUser nbTeachersUser) {
		this.nbTeachersUser = nbTeachersUser;
	}



	public String getCourseTopic() {
		return courseTopic;
	}



	public void setCourseTopic(String courseTopic) {
		this.courseTopic = courseTopic;
	}



	public Integer getCommentsStatus() {
		return commentsStatus;
	}



	public void setCommentsStatus(Integer commentsStatus) {
		this.commentsStatus = commentsStatus;
	}



	@Override
	public Map<String, Object> modelToMap() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id",id);
		data.put("firstPayFlowCode",firstPayFlowCode);
		data.put("firstPayIsDone",firstPayIsDone);
		data.put("firstPayPrice",firstPayPrice);
		data.put("firstPayTime",firstPayTime);
		data.put("fullPrice",fullPrice);
		data.put("secondPayFlowCode",secondPayFlowCode);
		data.put("secondPayIsDone",secondPayIsDone);
		data.put("secondPayPrice",secondPayPrice);
		data.put("secondPayTime",secondPayTime);
		data.put("student",nbStudentsUser);
		data.put("studentPreferedDate",studentPreferedDate);
		data.put("teacher",nbTeachersUser);
		data.put("courseTopic",courseTopic);
		return data;
	}

}