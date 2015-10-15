package database.models;

import java.io.Serializable;

import javax.persistence.*;

import database.common.nbBaseModel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
	private int id;

	@Column(name="first_pay_flow_code", length=64)
	private String firstPayFlowCode;

	@Column(name="first_pay_is_done", nullable=false)
	private byte firstPayIsDone;

	@Column(name="first_pay_price")
	private int firstPayPrice;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="first_pay_time")
	private Date firstPayTime;

	@Column(name="full_price", nullable=false)
	private int fullPrice;

	@Column(name="second_pay_flow_code", length=64)
	private String secondPayFlowCode;

	@Column(name="second_pay_is_done", nullable=false)
	private byte secondPayIsDone;

	@Column(name="second_pay_price")
	private int secondPayPrice;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="second_pay_time")
	private Date secondPayTime;

	@Column(name="student_id", nullable=false)
	private int studentId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="student_prefered_date", nullable=false)
	private Date studentPreferedDate;

	@Column(name="teacher_id", nullable=false)
	private int teacherId;

	//bi-directional many-to-one association to NbCommentsFromTeacher
	@OneToMany(mappedBy="nbOrder")
	private List<NbCommentsFromTeacher> nbCommentsFromTeachers;

	//bi-directional many-to-one association to NbTeacherScore
	@OneToMany(mappedBy="nbOrder")
	private List<NbTeacherScore> nbTeacherScores;
	
	@Column(name="course_topic", nullable=false)
	private String courseTopic;

	public NbOrder() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstPayFlowCode() {
		return this.firstPayFlowCode;
	}

	public void setFirstPayFlowCode(String firstPayFlowCode) {
		this.firstPayFlowCode = firstPayFlowCode;
	}

	public byte getFirstPayIsDone() {
		return this.firstPayIsDone;
	}

	public void setFirstPayIsDone(byte firstPayIsDone) {
		this.firstPayIsDone = firstPayIsDone;
	}

	public int getFirstPayPrice() {
		return this.firstPayPrice;
	}

	public void setFirstPayPrice(int firstPayPrice) {
		this.firstPayPrice = firstPayPrice;
	}

	public Date getFirstPayTime() {
		return this.firstPayTime;
	}

	public void setFirstPayTime(Date firstPayTime) {
		this.firstPayTime = firstPayTime;
	}

	public int getFullPrice() {
		return this.fullPrice;
	}

	public void setFullPrice(int fullPrice) {
		this.fullPrice = fullPrice;
	}

	public String getSecondPayFlowCode() {
		return this.secondPayFlowCode;
	}

	public void setSecondPayFlowCode(String secondPayFlowCode) {
		this.secondPayFlowCode = secondPayFlowCode;
	}

	public byte getSecondPayIsDone() {
		return this.secondPayIsDone;
	}

	public void setSecondPayIsDone(byte secondPayIsDone) {
		this.secondPayIsDone = secondPayIsDone;
	}

	public int getSecondPayPrice() {
		return this.secondPayPrice;
	}

	public void setSecondPayPrice(int secondPayPrice) {
		this.secondPayPrice = secondPayPrice;
	}

	public Date getSecondPayTime() {
		return this.secondPayTime;
	}

	public void setSecondPayTime(Date secondPayTime) {
		this.secondPayTime = secondPayTime;
	}

	public int getStudentId() {
		return this.studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public Date getStudentPreferedDate() {
		return this.studentPreferedDate;
	}

	public void setStudentPreferedDate(Date studentPreferedDate) {
		this.studentPreferedDate = studentPreferedDate;
	}

	public int getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public List<NbCommentsFromTeacher> getNbCommentsFromTeachers() {
		return this.nbCommentsFromTeachers;
	}

	public void setNbCommentsFromTeachers(List<NbCommentsFromTeacher> nbCommentsFromTeachers) {
		this.nbCommentsFromTeachers = nbCommentsFromTeachers;
	}

	public NbCommentsFromTeacher addNbCommentsFromTeacher(NbCommentsFromTeacher nbCommentsFromTeacher) {
		getNbCommentsFromTeachers().add(nbCommentsFromTeacher);
		nbCommentsFromTeacher.setNbOrder(this);

		return nbCommentsFromTeacher;
	}

	public NbCommentsFromTeacher removeNbCommentsFromTeacher(NbCommentsFromTeacher nbCommentsFromTeacher) {
		getNbCommentsFromTeachers().remove(nbCommentsFromTeacher);
		nbCommentsFromTeacher.setNbOrder(null);

		return nbCommentsFromTeacher;
	}

	public List<NbTeacherScore> getNbTeacherScores() {
		return this.nbTeacherScores;
	}

	public void setNbTeacherScores(List<NbTeacherScore> nbTeacherScores) {
		this.nbTeacherScores = nbTeacherScores;
	}

	public NbTeacherScore addNbTeacherScore(NbTeacherScore nbTeacherScore) {
		getNbTeacherScores().add(nbTeacherScore);
		nbTeacherScore.setNbOrder(this);

		return nbTeacherScore;
	}

	public NbTeacherScore removeNbTeacherScore(NbTeacherScore nbTeacherScore) {
		getNbTeacherScores().remove(nbTeacherScore);
		nbTeacherScore.setNbOrder(null);

		return nbTeacherScore;
	}

	public String getCourseTopic() {
		return courseTopic;
	}

	public void setCourseTopic(String courseTopic) {
		this.courseTopic = courseTopic;
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
		data.put("studentId",studentId);
		data.put("studentPreferedDate",studentPreferedDate);
		data.put("teacherId",teacherId);
		data.put("nbCommentsFromTeachers",nbCommentsFromTeachers);
		data.put("nbTeacherScores",nbTeacherScores);
		data.put("courseTopic",courseTopic);
		return data;
	}

}