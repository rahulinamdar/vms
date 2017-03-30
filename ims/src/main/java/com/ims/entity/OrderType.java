package com.ims.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="ordertype")
@NamedQueries({
	@NamedQuery(name="OrderType.getAll", query="SELECT ot FROM OrderType ot"),
	@NamedQuery(name="OrderType.getOrderType", query="SELECT ot FROM OrderType ot WHERE ot.orderTypeId = :orderTypeId")
})
public class OrderType extends EntityAudit{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(unique=true)
	private String orderTypeId;
	private String orderTypeDesc;
	
	
	public String getOrderTypeId() {
		return orderTypeId;
	}
	public void setOrderTypeId(String orderTypeId) {
		this.orderTypeId = orderTypeId;
	}
	public String getOrderTypeDesc() {
		return orderTypeDesc;
	}
	public void setOrderTypeDesc(String orderTypeDesc) {
		this.orderTypeDesc = orderTypeDesc;
	}
}
