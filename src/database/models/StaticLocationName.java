package database.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

import database.common.nbBaseModel;


/**
 * The persistent class for the static_location_name_list database table.
 * 
 */
@Entity
@Table(name="static_location_name")
@NamedQuery(name="StaticLocationNameList.findAll", query="SELECT s FROM StaticLocationName s")
public class StaticLocationName implements Serializable,nbBaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="location_name_top", nullable=false, length=10)
	private String locationNameTop;

	@Column(name="location_sub", nullable=false, length=10)
	private String locationSub;

	@Column(name="location_sub_type", nullable=false, length=10)
	private String locationSubType;

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getLocationNameTop() {
		return locationNameTop;
	}


	public void setLocationNameTop(String locationNameTop) {
		this.locationNameTop = locationNameTop;
	}


	public String getLocationSub() {
		return locationSub;
	}


	public void setLocationSub(String locationSub) {
		this.locationSub = locationSub;
	}


	public String getLocationSubType() {
		return locationSubType;
	}


	public void setLocationSubType(String locationSubType) {
		this.locationSubType = locationSubType;
	}


	@Override
	public Map<String, Object> modelToMap() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", id);
		data.put("locationNameTop", locationNameTop);
		data.put("locationSub", locationSub);
		data.put("locationSubType", locationSubType);
		return data;
	}

}