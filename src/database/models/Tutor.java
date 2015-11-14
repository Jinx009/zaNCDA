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

@Entity
@Table(name="q_tutor")
@NamedQuery(name="Tutor.findAll", query="SELECT n FROM Tutor n")
public class Tutor {

	@Id
	@Column(unique=true, nullable=false)
	private Integer id;
	/**
	 * 用户名
	 */
	@Column(name = "user_name")
	private String userName;
	/**
	 * 密码
	 */
	@Column(name = "pwd")
	private String pwd;
	/**
	 * 工作年限
	 */
	@Column(name = "work_years")
	private Double workYears;
	/**
	 * 个人介绍
	 */
	@Column(name = "personal_introduction")
	private String personalIntroduction;
	/**
	 * 真实姓名
	 */
	@Column(name = "real_name")
	private String realName;
	/**
	 * 擅长领域
	 */
	@OneToOne
	@JoinColumn(name="good_area_one")
	private Trade areaOne;
	/**
	 * 擅长领域
	 */
	@OneToOne
	@JoinColumn(name="good_area_two")
	private Trade areaTwo;
	/**
	 * 擅长领域
	 */
	@OneToOne
	@JoinColumn(name="good_area_three")
	private Trade areaThree;
	/**
	 * 擅长行业
	 */
	@OneToOne
	@JoinColumn(name="good_trade_one")
	private Trade tradeOne;
	/**
	 * 擅长行业
	 */
	@OneToOne
	@JoinColumn(name="good_trade_two")
	private Trade tradeTwo;
	/**
	 * 擅长行业
	 */
	@OneToOne
	@JoinColumn(name="good_trade_three")
	private Trade tradeThree;
	/**
	 * 认证资质
	 */
	@Column(name = "aptitude")
	private String aptitude;
	/**
	 * 擅长主题
	 */
	@OneToOne
	@JoinColumn(name="good_topic")
	private Topic qTopic;
	/**
	 * 邮箱
	 */
	@Column(name = "email")
	private String email;
	/**
	 * 微信号
	 */
	@Column(name = "wechat_name")
	private String wechatName;
	/**
	 * 身份证号
	 */
	@Column(name = "id_number")
	private String idNumber;
	/**
	 * 性别
	 */
	@Column(name = "sex")
	private String sex;
	/**
	 * 自己行业
	 */
	@OneToOne
	@JoinColumn(name="trade_id")
	private Trade qTrade;
	/**
	 * 课程价格
	 */
	@Column(name = "class_price")
	private String classPrice;
	/**
	 * 是否接受面谈
	 */
	@Column(name = "face_status")
	private Integer faceStatus;
	/**
	 * 电话约谈
	 */
	@Column(name = "mobile_status")
	private Integer mobileStatus;
	/**
	 * 视频面谈
	 */
	@Column(name = "video_status")
	private Integer videoStatus;
	/**
	 * 面谈价格
	 */
	@Column(name = "face_price")
	private Double facePrice;
	/**
	 * 电话约谈价格
	 */
	@Column(name = "mobile_price")
	private Double mobilePrice;
	/**
	 * 视频面谈
	 */
	@Column(name = "video_price")
	private Double videoPrice;
	/**
	 * 电话
	 */
	@Column(name = "mobile_phone")
	private String mobilePhone;
	/**
	 * openid
	 */
	@Column(name = "openid")
	private String openid;
	/**
	 * 住址
	 */
	@OneToOne
	@JoinColumn(name = "address_id")
	private Area address;
	/**
	 * 生日
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "birthday")
	private Date birthday;
	/**
	 * 银行卡
	 */
	@Column(name = "bank_card")
	private String bankCard;
	/**
	 * 银行名称
	 */
	@Column(name = "bank_name")
	private String bankName;
	/**
	 * 总评价平均分
	 */
	@Column(name = "comment_score")
	private Double commentScore;
	/**
	 * 添加时间
	 */
	@Column(name = "add_time")
	private Date addTime;
	/**
	 * 登录时间
	 */
	@Column(name = "login_time")
	private Date loginTime;
	
	@Column(name = "login_status")
	private Integer loginStatus;
	/**
	 * qq
	 */
	@Column(name = "qq")
	private String qq;
	/**
	 * 自身领域
	 */
	@OneToOne
	@JoinColumn(name = "job_id")
	private Trade job;
	/**
	 * 头像路径
	 */
	@Column(name = "photo_path")
	private String photoPath;

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

	public Double getWorkYears() {
		return workYears;
	}

	public void setWorkYears(Double workYears) {
		this.workYears = workYears;
	}

	public String getPersonalIntroduction() {
		return personalIntroduction;
	}

	public void setPersonalIntroduction(String personalIntroduction) {
		this.personalIntroduction = personalIntroduction;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Trade getAreaOne() {
		return areaOne;
	}

	public void setAreaOne(Trade areaOne) {
		this.areaOne = areaOne;
	}

	public Trade getAreaTwo() {
		return areaTwo;
	}

	public void setAreaTwo(Trade areaTwo) {
		this.areaTwo = areaTwo;
	}

	public Trade getAreaThree() {
		return areaThree;
	}

	public void setAreaThree(Trade areaThree) {
		this.areaThree = areaThree;
	}

	public Trade getTradeOne() {
		return tradeOne;
	}

	public void setTradeOne(Trade tradeOne) {
		this.tradeOne = tradeOne;
	}

	public Trade getTradeTwo() {
		return tradeTwo;
	}

	public void setTradeTwo(Trade tradeTwo) {
		this.tradeTwo = tradeTwo;
	}

	public Trade getTradeThree() {
		return tradeThree;
	}

	public void setTradeThree(Trade tradeThree) {
		this.tradeThree = tradeThree;
	}

	public String getAptitude() {
		return aptitude;
	}

	public void setAptitude(String aptitude) {
		this.aptitude = aptitude;
	}

	public Topic getqTopic() {
		return qTopic;
	}

	public void setqTopic(Topic qTopic) {
		this.qTopic = qTopic;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWechatName() {
		return wechatName;
	}

	public void setWechatName(String wechatName) {
		this.wechatName = wechatName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Trade getqTrade() {
		return qTrade;
	}

	public void setqTrade(Trade qTrade) {
		this.qTrade = qTrade;
	}
	
	public String getClassPrice() {
		return classPrice;
	}

	public void setClassPrice(String classPrice) {
		this.classPrice = classPrice;
	}

	public Integer getFaceStatus() {
		return faceStatus;
	}

	public void setFaceStatus(Integer faceStatus) {
		this.faceStatus = faceStatus;
	}

	public Integer getMobileStatus() {
		return mobileStatus;
	}

	public void setMobileStatus(Integer mobileStatus) {
		this.mobileStatus = mobileStatus;
	}

	public Integer getVideoStatus() {
		return videoStatus;
	}

	public void setVideoStatus(Integer videoStatus) {
		this.videoStatus = videoStatus;
	}

	public Double getFacePrice() {
		return facePrice;
	}

	public void setFacePrice(Double facePrice) {
		this.facePrice = facePrice;
	}

	public Double getMobilePrice() {
		return mobilePrice;
	}

	public void setMobilePrice(Double mobilePrice) {
		this.mobilePrice = mobilePrice;
	}

	public Double getVideoPrice() {
		return videoPrice;
	}

	public void setVideoPrice(Double videoPrice) {
		this.videoPrice = videoPrice;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Area getAddress() {
		return address;
	}

	public void setAddress(Area address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Double getCommentScore() {
		return commentScore;
	}

	public void setCommentScore(Double commentScore) {
		this.commentScore = commentScore;
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

	public Integer getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(Integer loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Trade getJob() {
		return job;
	}

	public void setJob(Trade job) {
		this.job = job;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
	
}
