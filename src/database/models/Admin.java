package database.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * 后台账户
 *
 */
@Entity
@Table(name="q_admin_user")
@NamedQuery(name="Admin.findAll", query="SELECT n FROM Admin n")
public class Admin implements Serializable {
	private static final long serialVersionUID = -944349877542239644L;
	
	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "pwd")
	private String pwd;
	
	@Column(name = "status")
	private Integer status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "login_time")
	private Date loginTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "add_time")
	private Date addTime;


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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	
}
