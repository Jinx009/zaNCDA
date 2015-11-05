package database.models;

import java.io.Serializable;

import javax.persistence.*;

import database.common.nbBaseModel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * The persistent class for the nb_teacher_score database table.
 * 
 */
@Entity
@Table(name="nb_teacher_score")
@NamedQuery(name="NbTeacherScore.findAll", query="SELECT n FROM NbTeacherScore n")
public class NbTeacherScore implements Serializable,nbBaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private Integer id;

	@Lob
	@Column(nullable=false)
	private String comments;

	@Column(nullable=false)
	private Integer score;

	@Temporal(TemporalType.DATE)
	@Column(name="submit_time", nullable=false)
	private Date submitTime;

	@Column(name = "order_id")
	private Integer orderId;

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public Integer getScore() {
		return score;
	}


	public void setScore(Integer score) {
		this.score = score;
	}


	public Date getSubmitTime() {
		return submitTime;
	}


	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}


	public Integer getOrderId() {
		return orderId;
	}


	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}


	@Override
	public Map<String, Object> modelToMap() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", id);
		data.put("comments", comments);
		data.put("score", score);
		data.put("submitTime", submitTime);
		return data;
	}

}