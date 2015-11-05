package database.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 导师时间
 *
 */
@Entity
@Table(name="q_tutoe_time")
@NamedQuery(name="TutorTime.findAll", query="SELECT n FROM TutorTime n")
public class TutorTime {

	@Id
	@Column(unique=true, nullable=false)
	private Integer id;
	
	@Column(name = "status")
	private Integer status;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "real_date")
	private Date realDate;
	
	@Column(name = "real_time")
	private String realTime;
	
	@OneToOne
	@JoinColumn(name = "tutor_id")
	private Tutor qTutor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getRealDate() {
		return realDate;
	}

	public void setRealDate(Date realDate) {
		this.realDate = realDate;
	}

	public String getRealTime() {
		return realTime;
	}

	public void setRealTime(String realTime) {
		this.realTime = realTime;
	}

	public Tutor getqTutor() {
		return qTutor;
	}

	public void setqTutor(Tutor qTutor) {
		this.qTutor = qTutor;
	}
	
	
	
}
