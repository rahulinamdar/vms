package com.ims.entity;

import com.ims.entity.BaseEntity;
import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Order
 *
 */
@Entity

public class Order extends EntityAudit implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	private OrderType orderType;
	
	@OneToMany(mappedBy="order")
	private List<OrderItem> items;
	
	private Double netValue;
	
	/**
	 * @return the orderType
	 */
	public OrderType getOrderType() {
		return orderType;
	}

	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

	/**
	 * @return the items
	 */
	public List<OrderItem> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	/**
	 * @return the netValue
	 */
	public Double getNetValue() {
		return netValue;
	}

	/**
	 * @param netValue the netValue to set
	 */
	public void setNetValue(Double netValue) {
		this.netValue = netValue;
	}

	public Order() {
		super();
	}
   
}
