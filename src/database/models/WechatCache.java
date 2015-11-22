package database.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 微信数据
 *
 */
@Entity
@Table(name="q_wechat_cache")
@NamedQuery(name="WechatCache.findAll", query="SELECT n FROM WechatCache n")
public class WechatCache implements Serializable {
	private static final long serialVersionUID = -944349877542239644L;
	
	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "cache_name")
	private String cacheName;
	
	@Column(name = "cache_value")
	private String cacheValue;
	
	@Column(name = "update_time")
	private Integer updateTime;
	
	@Column(name = "app_id")
	private String appId;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	
	
	
}
