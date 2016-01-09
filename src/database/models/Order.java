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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 *订单 约谈
 */
@Entity
@Table(name="q_order")
@NamedQuery(name="Order.findAll", query="SELECT n FROM Order n")
public class Order {

	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "add_time")
	private Date addTime;
	
	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer qCustomer;
	
	@OneToOne
	@JoinColumn(name = "tutor_id")
	private Tutor qTutor;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "pay_status")
	private Integer payStatus;
	
	@Column(name = "topic_content")
	private String topicContent;
	
	/**
	 * 手续费
	 */
	@Column(name = "procedures")
	private Double procedures;
	
	@Column(name = "pay_time")
	private Date payTime;
	
	@Column(name = "status")
	private Integer status;
	
	@OneToOne
	@JoinColumn(name = "time_id")
	private TutorTime qTutorTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time")
	private Date updateTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cacle_date")
	private Date CacleDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pay_ok_date")
	private Date payOkDate;
	
	@OneToOne
	@JoinColumn(name = "topic")
	private Topic topic;
	
	@Column(name = "bank")
	private String bank;
	
	@Column(name = "bank_name")
	private String bankName;
	
	@Column(name = "pay_money")
	private Double payMoney;
	
	@Column(name = "reason")
	private String reason;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Customer getqCustomer() {
		return qCustomer;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setqCustomer(Customer qCustomer) {
		this.qCustomer = qCustomer;
	}

	public Tutor getqTutor() {
		return qTutor;
	}

	public void setqTutor(Tutor qTutor) {
		this.qTutor = qTutor;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public String getTopicContent() {
		return topicContent;
	}

	public void setTopicContent(String topicContent) {
		this.topicContent = topicContent;
	}

	public Double getProcedures() {
		return procedures;
	}

	public void setProcedures(Double procedures) {
		this.procedures = procedures;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public TutorTime getqTutorTime() {
		return qTutorTime;
	}

	public void setqTutorTime(TutorTime qTutorTime) {
		this.qTutorTime = qTutorTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}

	public Date getCacleDate() {
		return CacleDate;
	}

	public void setCacleDate(Date cacleDate) {
		CacleDate = cacleDate;
	}

	public Date getPayOkDate() {
		return payOkDate;
	}

	public void setPayOkDate(Date payOkDate) {
		this.payOkDate = payOkDate;
	}
	
	
}
