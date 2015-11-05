package database.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import database.common.nbBaseModel;

/**
 * 微信资料
 * @author Jinx
 *
 */
@Entity
@Table(name="nb_admin_user")
@NamedQuery(name="NbAdminUser.findAll", query="SELECT n FROM NbAdminUser n")
public class NbAdminUser implements Serializable,nbBaseModel {
	private static final long serialVersionUID = -944349877542239644L;
	
	@Id
	@Column(unique=true, nullable=false)
	private Integer id;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "type")
	private Integer type;

	public Map<String, Object> modelToMap() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id",id);
		data.put("userName",userName);
		data.put("password",password);
		data.put("type",type);
		return data;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
