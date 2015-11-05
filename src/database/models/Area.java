package database.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 地址选择
 *
 */
@Entity
@Table(name="q_area")
@NamedQuery(name="Area.findAll", query="SELECT n FROM Area n")
public class Area {

	@Id
	@Column(unique=true, nullable=false)
	private Integer id;
	
	@Column(name = "parent_id")
	private Integer parentId;
	
	@Column(name = "name")
	private String name;

	
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
