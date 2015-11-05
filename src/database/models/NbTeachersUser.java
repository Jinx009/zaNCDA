package database.models;

import java.io.Serializable;

import javax.persistence.*;

import database.common.nbBaseModel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * The persistent class for the nb_teachers_user database table.
 * 
 */
@Entity
@Table(name="nb_teachers_user")
@NamedQuery(name="NbTeachersUser.findAll", query="SELECT n FROM NbTeachersUser n")
public class NbTeachersUser implements Serializable,nbBaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="bank_account", length=32)
	private String bankAccount;

	@Column(name="bank_name", length=32)
	private String bankName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "birth")
	private Date birth;
	
	@Column(name = "mobile_phone")
	private String mobilePhone;

	@Column(length=32)
	private String email;

	private Byte gender;

	@Column(length=40)
	private String highlight;

	@Column(name="id_card", length=32)
	private String idCard;

	@Column(name="is_online", nullable=false)
	private Byte isOnline;

	@Column(length=128)
	private String openid;

	@Column(nullable=false, length=20)
	private String password;

	@Lob
	private String photo;

	@Column(length=32)
	private String qq;

	@Column(name="real_name", length=64)
	private String realName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="register_time", nullable=false)
	private Date registerTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time")
	private Date updateTime;

	@Lob
	private String resume;

	@Lob
	@Column(name="special_description")
	private String specialDescription;

	@Column(name="talk_face2face", nullable=false)
	private Byte talkFace2face;

	@Column(name="talk_phone_call", nullable=false)
	private Byte talkPhoneCall;

	@Column(name="talk_video_chat", nullable=false)
	private Byte talkVideoChat;

	@Column(name="unit_price")
	private Integer unitPrice;

	@Column(nullable=false, length=20,name = "username")
	private String username;

	@Column(name="wechat_id", length=32)
	private String wechatId;

	@Temporal(TemporalType.DATE)
	@Column(name="work_start_year")
	private Date workStartYear;

	//uni-directional one-to-one association to StaticAreaName
	@OneToOne
	@JoinColumn(name="area01")
	private StaticAreaName areaName1;

	//uni-directional one-to-one association to StaticAreaName
	@OneToOne
	@JoinColumn(name="area02")
	private StaticAreaName areaName2;

	//uni-directional one-to-one association to StaticAreaName
	@OneToOne
	@JoinColumn(name="area03")
	private StaticAreaName areaName3;

	//uni-directional one-to-one association to StaticFixedTopicName
	@OneToOne
	@JoinColumn(name="special_topic")
	private StaticFixedTopicName fixedTopicName;

	//uni-directional one-to-one association to StaticJobName
	@OneToOne
	@JoinColumn(name="professional01")
	private StaticJobName jobName1;

	//uni-directional one-to-one association to StaticJobName
	@OneToOne
	@JoinColumn(name="professional02")
	private StaticJobName jobName2;

	//uni-directional one-to-one association to StaticJobName
	@OneToOne
	@JoinColumn(name="professional03")
	private StaticJobName jobName3;

	//uni-directional one-to-one association to StaticLocationName
	@OneToOne
	@JoinColumn(name="prefered_location")
	private StaticLocationName locationName;

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getBankAccount() {
		return bankAccount;
	}


	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}


	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public Date getBirth() {
		return birth;
	}


	public void setBirth(Date birth) {
		this.birth = birth;
	}


	public String getMobilePhone() {
		return mobilePhone;
	}


	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Byte getGender() {
		return gender;
	}


	public void setGender(Byte gender) {
		this.gender = gender;
	}


	public String getHighlight() {
		return highlight;
	}


	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}


	public String getIdCard() {
		return idCard;
	}


	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}


	public Byte getIsOnline() {
		return isOnline;
	}


	public void setIsOnline(Byte isOnline) {
		this.isOnline = isOnline;
	}


	public String getOpenid() {
		return openid;
	}


	public void setOpenid(String openid) {
		this.openid = openid;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public String getQq() {
		return qq;
	}


	public void setQq(String qq) {
		this.qq = qq;
	}


	public String getRealName() {
		return realName;
	}


	public void setRealName(String realName) {
		this.realName = realName;
	}


	public Date getRegisterTime() {
		return registerTime;
	}


	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}


	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public String getResume() {
		return resume;
	}


	public void setResume(String resume) {
		this.resume = resume;
	}


	public String getSpecialDescription() {
		return specialDescription;
	}


	public void setSpecialDescription(String specialDescription) {
		this.specialDescription = specialDescription;
	}


	public Byte getTalkFace2face() {
		return talkFace2face;
	}


	public void setTalkFace2face(Byte talkFace2face) {
		this.talkFace2face = talkFace2face;
	}


	public Byte getTalkPhoneCall() {
		return talkPhoneCall;
	}


	public void setTalkPhoneCall(Byte talkPhoneCall) {
		this.talkPhoneCall = talkPhoneCall;
	}


	public Byte getTalkVideoChat() {
		return talkVideoChat;
	}


	public void setTalkVideoChat(Byte talkVideoChat) {
		this.talkVideoChat = talkVideoChat;
	}


	public Integer getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getWechatId() {
		return wechatId;
	}


	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}


	public Date getWorkStartYear() {
		return workStartYear;
	}


	public void setWorkStartYear(Date workStartYear) {
		this.workStartYear = workStartYear;
	}


	public StaticAreaName getAreaName1() {
		return areaName1;
	}


	public void setAreaName1(StaticAreaName areaName1) {
		this.areaName1 = areaName1;
	}


	public StaticAreaName getAreaName2() {
		return areaName2;
	}


	public void setAreaName2(StaticAreaName areaName2) {
		this.areaName2 = areaName2;
	}


	public StaticAreaName getAreaName3() {
		return areaName3;
	}


	public void setAreaName3(StaticAreaName areaName3) {
		this.areaName3 = areaName3;
	}


	public StaticFixedTopicName getFixedTopicName() {
		return fixedTopicName;
	}


	public void setFixedTopicName(StaticFixedTopicName fixedTopicName) {
		this.fixedTopicName = fixedTopicName;
	}


	public StaticJobName getJobName1() {
		return jobName1;
	}


	public void setJobName1(StaticJobName jobName1) {
		this.jobName1 = jobName1;
	}


	public StaticJobName getJobName2() {
		return jobName2;
	}


	public void setJobName2(StaticJobName jobName2) {
		this.jobName2 = jobName2;
	}


	public StaticJobName getJobName3() {
		return jobName3;
	}


	public void setJobName3(StaticJobName jobName3) {
		this.jobName3 = jobName3;
	}


	public StaticLocationName getLocationName() {
		return locationName;
	}


	public void setLocationName(StaticLocationName locationName) {
		this.locationName = locationName;
	}


	@Override
	public Map<String, Object> modelToMap() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("bankAccount",bankAccount);
		data.put("bankName",bankName);
		data.put("birth",birth);
		data.put("email",email);
		data.put("gender",gender);
		data.put("highlight",highlight);
		data.put("idCard",idCard);
		data.put("isOnline",isOnline);
		data.put("openid",openid);
		data.put("photo",photo);
		data.put("qq",qq);
		data.put("realName",realName);
		data.put("registerTime",registerTime);
		data.put("resume",resume);
		data.put("specialDescription",specialDescription);
		data.put("talkFace2face",talkFace2face);
		data.put("talkPhoneCall",talkPhoneCall);
		data.put("talkVideoChat",talkVideoChat);
		data.put("unitPrice",unitPrice);
		data.put("username",username);
		data.put("wechatId",wechatId);
		data.put("workStartYear",workStartYear);
		data.put("areaName1",areaName1);
		data.put("areaName2",areaName2);
		data.put("areaName3",areaName3);
		data.put("fixedTopicName",fixedTopicName);
		data.put("jobName1",jobName1);
		data.put("jobName2",jobName2);
		data.put("jobName3",jobName3);
		data.put("locationName",locationName);
		return data;
	}

}