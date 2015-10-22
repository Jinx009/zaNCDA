package database.models;

import java.io.Serializable;

import javax.persistence.*;

import database.common.nbBaseModel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * The persistent class for the nb_students_user database table.
 * 
 */
@Entity
@Table(name="nb_students_user")
@NamedQuery(name="NbStudentsUser.findAll", query="SELECT n FROM NbStudentsUser n")
public class NbStudentsUser implements Serializable,nbBaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	@Column(length=32)
	private String email;

	@Column(name="family_member")
	private int familyMember;

	private byte gender;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="birth")
	private Date birth;

	@Column(name="job_title", length=32)
	private String jobTitle;

	@Column(nullable=false, length=64)
	private String password;

	@Column(length=32)
	private String qq;

	@Column(name="real_name", length=16)
	private String realName;

	@Column(nullable=false, length=32)
	private String username;

	@Column(name="wechat_id", length=32)
	private String wechatId;
	
	@Column(name="openId", length=64)
	private String openId;

	//uni-directional one-to-one association to StaticJobName
	@OneToOne
	@JoinColumn(name="job_area")
	private StaticJobName jobName;
	
	@Column(name = "mobile_phone")
	private String mobilePhone;

	
	
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public NbStudentsUser() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getFamilyMember() {
		return this.familyMember;
	}

	public void setFamilyMember(int familyMember) {
		this.familyMember = familyMember;
	}

	public byte getGender() {
		return this.gender;
	}

	public void setGender(byte gender) {
		this.gender = gender;
	}

	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public StaticJobName getStaticJobName() {
		return this.jobName;
	}

	public void setStaticJobName(StaticJobName jobName) {
		this.jobName = jobName;
	}
	
	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Override
	public Map<String, Object> modelToMap() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id",id);
		data.put("email",email);
		data.put("familyMember",familyMember);
		data.put("gender",gender);
		data.put("birth",birth);
		data.put("jobTitle",jobTitle);
		data.put("password",password);
		data.put("qq",qq);
		data.put("realName",realName);
		data.put("username",username);
		data.put("wechatId",wechatId);
		data.put("jobName",jobName);
		data.put("openId",openId);
		return data;
	}

}