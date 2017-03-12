package com.ims.entity;



import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the stock database table.
 * 
 */

@AttributeOverride(name = "id", column = @Column(name = "stock_id"))
@Entity
public class Stock extends EntityAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="product_id",nullable=false)
	private Product product;

	private int quantity;
	
	@ManyToOne
	@JoinColumn(name="region_id",insertable=true,nullable=false)
	private Region region;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="stock_date")
	private Date stockDate;

	public Stock() {
	}


	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getStockDate() {
		return this.stockDate;
	}

	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

}