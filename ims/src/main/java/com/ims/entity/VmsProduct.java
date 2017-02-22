package com.ims.entity;


import javax.persistence.*;


/**
 * The persistent class for the vms_product database table.
 * 
 */
@Entity(name="vms_product")
@Table
@AttributeOverride(name = "id", column = @Column(name = "product_id"))
public class VmsProduct extends EntityAudit{
	
	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;


	@Column(name="PRODUCT_IMAGE")
	private String productImage;

	@Column(name="PRODUCT_NAME")
	private String productName;

	public VmsProduct() {
	}


	public String getProductImage() {
		return this.productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}