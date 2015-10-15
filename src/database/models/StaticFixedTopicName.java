package database.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

import database.common.nbBaseModel;


/**
 * The persistent class for the static_fixed_topic_name_list database table.
 * 
 */
@Entity
@Table(name="static_fixed_topic_name")
@NamedQuery(name="StaticFixedTopicNameList.findAll", query="SELECT s FROM StaticFixedTopicName s")
public class StaticFixedTopicName implements Serializable,nbBaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="parent_name", nullable=false, length=16)
	private String parentName;

	@Column(name="sub_name", nullable=false, length=32)
	private String subName;

	public StaticFixedTopicName() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getParentName() {
		return this.parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getSubName() {
		return this.subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	@Override
	public Map<String, Object> modelToMap() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", id);
		data.put("parentName", parentName);
		data.put("subName", subName);
		return data;
	}

}