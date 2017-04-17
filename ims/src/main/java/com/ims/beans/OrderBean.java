/**
 * 
 */
package com.ims.beans;

import java.util.List;

import com.ims.entity.Delivery;

/**
 * @author rahul
 *
 */
public class OrderBean {
	private String orderType;
	private List<OrderItemBean> items;
	private String status;
	private Double netValue;
	private Delivery delivery;
	private String payment;
	private String region;
	private Double discount;
	
	/**
	 * @return the items
	 */
	public List<OrderItemBean> getItems() {
		return items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(List<OrderItemBean> items) {
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
	
	/**
	 * @return the payment
	 */
	public String getPayment() {
		return payment;
	}
	/**
	 * @param payment the payment to set
	 */
	public void setPayment(String payment) {
		this.payment = payment;
	}
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
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
	 * @return the orderType
	 */
	public String getOrderType() {
		return orderType;
	}
	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	
	
}
