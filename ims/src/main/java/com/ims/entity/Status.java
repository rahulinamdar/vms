package com.ims.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="status")
@NamedQueries({
	@NamedQuery(name="Status.getAll", query="SELECT s FROM Status s"),
	@NamedQuery(name="Status.getStatus", query="SELECT s FROM Status s WHERE s.statusId = :statusId")
})

public class Status extends EntityAudit{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(unique=true)
	private String statusId;
	private String status;
	
	@OneToMany(mappedBy="status")
	private List<Order> orders;
	
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
