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
 * 顾客给导师打分
 *
 */
@Entity
@Table(name="q_score")
@NamedQuery(name="Score.findAll", query="SELECT n FROM Score n")
public class Score {
	
	@Id
	@Column(unique=true, nullable=false)
	private Integer id;
	
	/**
	 * 打分内容
	 */
	@Column(name = "content")
	private String content;
	
	/**
	 * 第一维度分数
	 */
	@Column(name = "one_score")
	private Integer oneScore;
	
	/**
	 * 第一维度分数
	 */
	@Column(name = "two_score")
	private Integer twoScore;
	
	/**
	 * 第一维度分数
	 */
	@Column(name = "three_score")
	private Integer threeScore;
	
	/**
	 * 第一维度分数
	 */
	@Column(name = "four_score")
	private Integer fourScore;
	
	@OneToOne
	@JoinColumn(name = "order_id")
	private Order qOrder;
	
	/**
	 * 添加时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "add_time")
	private Date addTime;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getOneScore() {
		return oneScore;
	}

	public void setOneScore(Integer oneScore) {
		this.oneScore = oneScore;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Order getqOrder() {
		return qOrder;
	}

	public void setqOrder(Order qOrder) {
		this.qOrder = qOrder;
	}

	public Integer getTwoScore() {
		return twoScore;
	}

	public void setTwoScore(Integer twoScore) {
		this.twoScore = twoScore;
	}

	public Integer getThreeScore() {
		return threeScore;
	}

	public void setThreeScore(Integer threeScore) {
		this.threeScore = threeScore;
	}

	public Integer getFourScore() {
		return fourScore;
	}

	public void setFourScore(Integer fourScore) {
		this.fourScore = fourScore;
	}
	
}
