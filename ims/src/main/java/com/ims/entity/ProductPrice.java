package com.ims.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
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
@NamedQuery(name="ProductPrice.getPrice", query="SELECT p FROM ProductPrice p WHERE p.product.productId = :productId AND p.pricingDate =:date")
public class ProductPrice extends EntityAudit implements Serializable {
	
	@Transient
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="product_id",nullable=false)
	private Product product;
	
	@Temporal(TemporalType.DATE)
	private Date pricingDate;
	
	private double price;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Uom uom;
	
	public ProductPrice() {
		super();
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
