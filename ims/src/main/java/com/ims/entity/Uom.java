package com.ims.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="uom")
public class Uom {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(unique=true)
	private String uomid;
	
	private String uom;
	//TODO Can be done by extending commomn entity --@Rahul
	
	/*private String modifiedby;
	private String createdby;
	private Date modifiedon;
	private Date createdon;*/
	
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
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
