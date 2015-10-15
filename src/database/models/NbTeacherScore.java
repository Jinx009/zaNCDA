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
	private int id;

	@Lob
	@Column(nullable=false)
	private String comments;

	@Column(nullable=false)
	private int score;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="submit_time", nullable=false)
	private Date submitTime;

	//bi-directional many-to-one association to NbOrder
	@ManyToOne
	@JoinColumn(name="order_id", nullable=false)
	private NbOrder nbOrder;

	public NbTeacherScore() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getSubmitTime() {
		return this.submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public NbOrder getNbOrder() {
		return this.nbOrder;
	}

	public void setNbOrder(NbOrder nbOrder) {
		this.nbOrder = nbOrder;
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