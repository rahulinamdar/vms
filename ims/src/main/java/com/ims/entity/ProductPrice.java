package com.ims.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Entity implementation class for Entity: ProductPrice
 *
 */
@Entity
@AttributeOverride(name = "id", column = @Column(name = "productPrice_id"))
public class ProductPrice extends EntityAudit implements Serializable {
	
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Column(name="PRODUCT",unique=true)
	private String productId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date pricingDate;
	
	private double price;
	
	@OneToOne
	private Uom uom;
	
	public ProductPrice() {
		super();
	}   
	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}   
	public Date getPricingDate() {
		return this.pricingDate;
	}

	public void setPricingDate(Date pricingDate) {
		this.pricingDate = pricingDate;
	}   
	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}   
	public Uom getUom() {
		return this.uom;
	}

	public void setUom(Uom uom) {
		this.uom = uom;
	}
   
}
