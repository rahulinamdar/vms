package com.ims.entity;

import com.ims.entity.EntityAudit;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: OrderItem
 *
 */
@Entity
@Table(name="orderitem_table")
public class OrderItem extends EntityAudit implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne()
	@JoinColumn(name="order_id")
	private Order order;
	
	@OneToOne
	private Product product;
	
	/**
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the quantity
	 */
	public Double getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the totalPrice
	 */
	public Double getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	private Double quantity;
	
	private Double totalPrice;
	
	public OrderItem() {
		super();
	}
   
}
