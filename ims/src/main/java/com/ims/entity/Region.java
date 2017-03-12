package com.ims.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="region")
public class Region extends EntityAudit{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(unique=true)
	private String regionid;
	
	private String regionname;
	private String shopaddress;
	private double 	geo_lat;
	private double geo_long;
	
	@OneToMany(mappedBy="region")
	private Set<Stock> stocks; 
	
	public String getRegionid() {
		return regionid;
	}
	public void setRegionid(String regionid) {
		this.regionid = regionid;
	}
	public String getRegionname() {
		return regionname;
	}
	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}
	public String getShopaddress() {
		return shopaddress;
	}
	public void setShopaddress(String shopaddress) {
		this.shopaddress = shopaddress;
	}
	public double getGeo_lat() {
		return geo_lat;
	}
	public void setGeo_lat(double geo_lat) {
		this.geo_lat = geo_lat;
	}
	public double getGeo_long() {
		return geo_long;
	}
	public void setGeo_long(double geo_long) {
		this.geo_long = geo_long;
	}
}
