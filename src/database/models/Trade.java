package database.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="q_trade")
@NamedQuery(name="Trade.findAll", query="SELECT n FROM Trade n")
public class Trade {

	@Id
	@Column(unique=true, nullable=false)
	private Integer id;
	
	@Column(name = "trade_name")
	private String tradeName;
	
	@Column(name = "parent_id")
	private Integer parentId;
	
	@Column(name = "show_status")
	private Integer showStatus;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(Integer showStatus) {
		this.showStatus = showStatus;
	}
	
}
