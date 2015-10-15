package database.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

import database.common.nbBaseModel;


/**
 * The persistent class for the static_area_name_list database table.
 * 
 */
@Entity
@Table(name="static_area_name")
@NamedQuery(name="StaticAreaNameList.findAll", query="SELECT s FROM StaticAreaName s")
public class StaticAreaName implements Serializable,nbBaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="area_name", nullable=false, length=32)
	private String areaName;

	public StaticAreaName() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Override
	public Map<String, Object> modelToMap() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", id);
		data.put("areaName", areaName);
		return data;
	}

}