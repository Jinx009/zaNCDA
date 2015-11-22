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

@Entity
@Table(name="q_customer")
@NamedQuery(name="Customer.findAll", query="SELECT n FROM Customer n")
public class Customer {

	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "pwd")
	private String pwd;
	
	@Column(name = "real_name")
	private String realName;
	
	@Column(name = "sex")
	private String sex;
	
	@Column(name = "wechat_name")
	private String wechatName;
	
	@Column(name = "openid")
	private String openid;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "add_time")
	private Date addTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "login_time")
	private Date loginTime;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "mobile_phone")
	private String mobilePhone;
	
	@Column(name = "qq")
	private String qq;
	
	@OneToOne
	@JoinColumn(name = "area_id")
	private Area qArea;
	
	@OneToOne
	@JoinColumn(name = "trade_id")
	private Trade trade;
	
	@OneToOne
	@JoinColumn(name = "job_id")
	private Trade job;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "birthday")
	private Date birthday;
	
	@Column(name = "familly_number")
	private Integer famillyNumber;
	
	@Column(name = "id_number")
	private String idNumber;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getWechatName() {
		return wechatName;
	}

	public void setWechatName(String wechatName) {
		this.wechatName = wechatName;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Area getqArea() {
		return qArea;
	}

	public void setqArea(Area qArea) {
		this.qArea = qArea;
	}

	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}

	public Trade getJob() {
		return job;
	}

	public void setJob(Trade job) {
		this.job = job;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getFamillyNumber() {
		return famillyNumber;
	}

	public void setFamillyNumber(Integer famillyNumber) {
		this.famillyNumber = famillyNumber;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	
	
	
	
}
