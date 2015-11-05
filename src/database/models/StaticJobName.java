package database.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

import database.common.nbBaseModel;


/**
 * The persistent class for the static_job_name_list database table.
 * 
 */
@Entity
@Table(name="static_job_name")
@NamedQuery(name="StaticJobNameList.findAll", query="SELECT s FROM StaticJobName s")
public class StaticJobName implements Serializable,nbBaseModel {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="job_name", nullable=false, length=32)
	private String jobName;

	public StaticJobName() {
	}

	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getJobName() {
		return jobName;
	}



	public void setJobName(String jobName) {
		this.jobName = jobName;
	}



	@Override
	public Map<String, Object> modelToMap() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", id);
		data.put("jobName", jobName);
		return data;
	}

}