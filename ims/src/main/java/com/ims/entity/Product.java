package com.ims.entity;


import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;


/**
 * The persistent class for the Product database table.
 * 
 */
@NamedQuery( name="Product.getAll", query= "SELECT p FROM Product p" )
@AttributeOverride(name = "id", column = @Column(name = "product_id"))
@Entity
public class Product extends EntityAudit{
	
	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Column(name="PRODUCT",unique=true)
	private String productId;
	
	private String productImage;
	private String productDescription;
	
	

	@OneToMany(cascade=CascadeType.ALL)
	private List<ProductPrice> price; 

	public Product() {
	}


	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductImage() {
		return this.productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public List<ProductPrice> getPrice() {
		return price;
	}

	public void setPrice(List<ProductPrice> price) {
		this.price = price;
	}

}