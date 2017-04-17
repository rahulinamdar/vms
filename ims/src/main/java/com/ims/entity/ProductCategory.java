package com.ims.entity;

import com.ims.entity.EntityAudit;
import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ProductCategory
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name="ProductCategory.getAll", query="SELECT p FROM ProductCategory p"),
	@NamedQuery(name="ProductCategory.getCategory", query="SELECT p FROM ProductCategory p WHERE p.categoryId = :categoryId")
})
public class ProductCategory extends EntityAudit implements Serializable {

	
	private String categoryId;
	private String category;
	private static final long serialVersionUID = 1L;

	public ProductCategory() {
		super();
	}   
	public String getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}   
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
   
}
