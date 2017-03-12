package com.ims.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ProductStock
 *
 */
@Entity

public class ProductStock extends EntityAudit implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private String productId;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Double getStock() {
		return stock;
	}
	public void setStock(Double stock) {
		this.stock = stock;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public Uom getUom() {
		return uom;
	}
	public void setUom(Uom uom) {
		this.uom = uom;
	}
	public Date getStockDate() {
		return stockDate;
	}
	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
	}
	private Double stock;
	@OneToOne
	private Region region;
	@OneToOne
	private Uom uom;
	@Temporal(TemporalType.TIMESTAMP)
	private Date stockDate;
	public ProductStock() {
		super();
	}
	public ProductStock(String productId, Region region, Date today) {
		this.productId = productId;
		this.region = region;
		this.stockDate = today;
	}
   
}
