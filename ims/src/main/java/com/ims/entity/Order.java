package com.ims.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Order
 *
 */
@Entity
@Table(name="order_table")
@NamedQueries({
	@NamedQuery(name="Order.getAll", query="SELECT o FROM Order o"),
	@NamedQuery(name="Order.getAllForRegion", query="SELECT o FROM Order o WHERE o.region.id = :regionId"),
})

public class Order extends EntityAudit implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	private OrderType orderType;
	
	@OneToMany(mappedBy="order",fetch=FetchType.EAGER)
	private List<OrderItem> items;
	
	@ManyToOne
	@JoinColumn(name="status")
	private Status status;
	
	private Double netValue;
	
	@OneToOne
	private Delivery delivery;
	
	@ManyToOne
	@JoinColumn(name="region")
	private Region region;
	
	@ManyToOne
	private PaymentMethod paymentMethod;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the delivery
	 */
	public Delivery getDelivery() {
		return delivery;
	}

	/**
	 * @param delivery the delivery to set
	 */
	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	/**
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(Region region) {
		this.region = region;
	}

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

	/**
	 * @return the paymentMethod
	 */
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
   
}
