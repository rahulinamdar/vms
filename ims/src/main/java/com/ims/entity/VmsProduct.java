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

	/*@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="product_id")
	private long product_id;*/
	
	/*public long getProduct_id() {
		return product_id;
	}


	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}*/

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