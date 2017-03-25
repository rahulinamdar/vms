package com.ims.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ProductStock
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name="ProductStock.getStock", query="SELECT p FROM ProductStock p WHERE p.product.productId = :productId AND p.stockDate =:date AND p.region.regionid = :regionId"),
	@NamedQuery(name="ProductStock.getStock.ByDate", query="SELECT p FROM ProductStock p WHERE p.product.productId = :productId AND p.stockDate =:date")
})

public class ProductStock extends EntityAudit implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="product_id",nullable=false)
	private Product product;

	private Double stock;
	
	@ManyToOne
	@JoinColumn(name="region_id",insertable=true,nullable=false)
	private Region region;
	
	@OneToOne
	private Uom uom;
	
	@Temporal(TemporalType.DATE)
	@Column(name="stock_date")
	private Date stockDate;
	
	
	public ProductStock() {
		super();
	}
	public ProductStock(Product product, Region region, Date today) {
		this.product = product;
		this.region = region;
		this.stockDate = today;
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
   
}
