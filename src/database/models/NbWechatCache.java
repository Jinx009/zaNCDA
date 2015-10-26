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

@Entity
@Table(name="nb_wechat_cache")
@NamedQuery(name="NbWechatCache.findAll", query="SELECT n FROM NbWechatCache n")
public class NbWechatCache implements Serializable,nbBaseModel {
	private static final long serialVersionUID = -944349877542239644L;
	
	@Id
	@Column(unique=true, nullable=false)
	private int id;
	
	@Column(name = "cache_name")
	private String cacheName;
	
	@Column(name = "cache_value")
	private String cacheValue;
	
	@Column(name = "update_time")
	private Integer updateTime;
	
	@Column(name = "app_id")
	private int appId;

	public Map<String, Object> modelToMap() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id",id);
		data.put("cacheName",cacheName);
		data.put("cacheValue",cacheValue);
		data.put("updateTime",updateTime);
		data.put("appId",appId);
		return data;
	}

	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCacheName() {
		return cacheName;
	}
	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}
	public String getCacheValue() {
		return cacheValue;
	}
	public void setCacheValue(String cacheValue) {
		this.cacheValue = cacheValue;
	}
	public Integer getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Integer updateTime) {
		this.updateTime = updateTime;
	}
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
}
