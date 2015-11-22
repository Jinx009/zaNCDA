package database.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="q_comments")
@NamedQuery(name="Comments.findAll", query="SELECT n FROM Comments n")
public class Comments {

	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "appeal_one")
	private String appealOne;
	
	@Column(name = "appeal_two")
	private String appealTwo;
	
	@Column(name = "appeal_three")
	private String appealThree;
	
	@Column(name = "question")
	private String question;
	
	@Column(name = "solve_status")
	private String solveStatus;
	
	@Column(name = "solve_tool")
	private String solveTool;
	
	@Column(name = "advice_one")
	private String adviceOne;
	
	@Column(name = "advice_two")
	private String adviceTwo;
	
	@Column(name = "advice_three")
	private String adviceThree;
	
	@Column(name = "solve_result")
	private String solveResult;
	
	@Column(name = "solve_assess")
	private String solveAssess;

	@Column(name = "add_time")
	private Date addTime;
	
	@OneToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppealOne() {
		return appealOne;
	}

	public void setAppealOne(String appealOne) {
		this.appealOne = appealOne;
	}

	public String getAppealTwo() {
		return appealTwo;
	}

	public void setAppealTwo(String appealTwo) {
		this.appealTwo = appealTwo;
	}

	public String getAppealThree() {
		return appealThree;
	}

	public void setAppealThree(String appealThree) {
		this.appealThree = appealThree;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getSolveStatus() {
		return solveStatus;
	}

	public void setSolveStatus(String solveStatus) {
		this.solveStatus = solveStatus;
	}

	public String getSolveTool() {
		return solveTool;
	}

	public void setSolveTool(String solveTool) {
		this.solveTool = solveTool;
	}

	public String getAdviceOne() {
		return adviceOne;
	}

	public void setAdviceOne(String adviceOne) {
		this.adviceOne = adviceOne;
	}

	public String getAdviceTwo() {
		return adviceTwo;
	}

	public void setAdviceTwo(String adviceTwo) {
		this.adviceTwo = adviceTwo;
	}

	public String getAdviceThree() {
		return adviceThree;
	}

	public void setAdviceThree(String adviceThree) {
		this.adviceThree = adviceThree;
	}

	public String getSolveResult() {
		return solveResult;
	}

	public void setSolveResult(String solveResult) {
		this.solveResult = solveResult;
	}

	public String getSolveAssess() {
		return solveAssess;
	}

	public void setSolveAssess(String solveAssess) {
		this.solveAssess = solveAssess;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
}
