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
	private int id;

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

	private byte gender;

	@Column(length=40)
	private String highlight;

	@Column(name="id_card", length=32)
	private String idCard;

	@Column(name="is_online", nullable=false)
	private byte isOnline;

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
	private byte talkFace2face;

	@Column(name="talk_phone_call", nullable=false)
	private byte talkPhoneCall;

	@Column(name="talk_video_chat", nullable=false)
	private byte talkVideoChat;

	@Column(name="unit_price")
	private int unitPrice;

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

	public NbTeachersUser() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBankAccount() {
		return this.bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Date getBirth() {
		return this.birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte getGender() {
		return this.gender;
	}

	public void setGender(byte gender) {
		this.gender = gender;
	}

	public String getHighlight() {
		return this.highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public byte getIsOnline() {
		return this.isOnline;
	}

	public void setIsOnline(byte isOnline) {
		this.isOnline = isOnline;
	}

	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Date getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getResume() {
		return this.resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getSpecialDescription() {
		return this.specialDescription;
	}

	public void setSpecialDescription(String specialDescription) {
		this.specialDescription = specialDescription;
	}

	public byte getTalkFace2face() {
		return this.talkFace2face;
	}

	public void setTalkFace2face(byte talkFace2face) {
		this.talkFace2face = talkFace2face;
	}

	public byte getTalkPhoneCall() {
		return this.talkPhoneCall;
	}

	public void setTalkPhoneCall(byte talkPhoneCall) {
		this.talkPhoneCall = talkPhoneCall;
	}

	public byte getTalkVideoChat() {
		return this.talkVideoChat;
	}

	public void setTalkVideoChat(byte talkVideoChat) {
		this.talkVideoChat = talkVideoChat;
	}

	public int getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getWechatId() {
		return this.wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	public Date getWorkStartYear() {
		return this.workStartYear;
	}

	public void setWorkStartYear(Date workStartYear) {
		this.workStartYear = workStartYear;
	}

	public StaticAreaName getStaticAreaName1() {
		return this.areaName1;
	}

	public void setStaticAreaName1(StaticAreaName areaName1) {
		this.areaName1 = areaName1;
	}

	public StaticAreaName getStaticAreaName2() {
		return this.areaName2;
	}

	public void setStaticAreaName2(StaticAreaName areaName2) {
		this.areaName2 = areaName2;
	}

	public StaticAreaName getStaticAreaName3() {
		return this.areaName3;
	}

	public void setStaticAreaName3(StaticAreaName areaName3) {
		this.areaName3 = areaName3;
	}

	public StaticFixedTopicName getStaticFixedTopicName() {
		return this.fixedTopicName;
	}

	public void setStaticFixedTopicName(StaticFixedTopicName fixedTopicName) {
		this.fixedTopicName = fixedTopicName;
	}

	public StaticJobName getStaticJobName1() {
		return this.jobName1;
	}

	public void setStaticJobName1(StaticJobName jobName1) {
		this.jobName1 = jobName1;
	}

	public StaticJobName getStaticJobName2() {
		return this.jobName2;
	}

	public void setStaticJobName2(StaticJobName jobName2) {
		this.jobName2 = jobName2;
	}

	public StaticJobName getStaticJobName3() {
		return this.jobName3;
	}

	public void setStaticJobName3(StaticJobName jobName3) {
		this.jobName3 = jobName3;
	}

	public StaticLocationName getStaticLocationName() {
		return this.locationName;
	}

	public void setStaticLocationName(StaticLocationName locationName) {
		this.locationName = locationName;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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