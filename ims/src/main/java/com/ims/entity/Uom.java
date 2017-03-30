package com.ims.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="uom")
@NamedQueries({
	@NamedQuery(name="Uom.getUom" , query = "SELECT u FROM Uom u WHERE u.uomid = :uomId"),
	@NamedQuery(name="Uom.getAll" , query = "SELECT u FROM Uom u")
	
})

public class Uom extends EntityAudit{

	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(unique=true)
	private String uomid;
	
	private String uom;
	
	
	public String getUomid() {
		return uomid;
	}
	public void setUomid(String uomid) {
		this.uomid = uomid;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	
}
