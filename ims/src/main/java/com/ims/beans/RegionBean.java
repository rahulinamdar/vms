/**
 * 
 */
package com.ims.beans;

/**
 * @author rahul
 *
 */
public class RegionBean {
	private String regionid;
	
	private String regionname;
	private String shopaddress;
	private double 	geo_lat;
	private double geo_long;
	/**
	 * @return the regionid
	 */
	public String getRegionid() {
		return regionid;
	}
	/**
	 * @param regionid the regionid to set
	 */
	public void setRegionid(String regionid) {
		this.regionid = regionid;
	}
	/**
	 * @return the regionname
	 */
	public String getRegionname() {
		return regionname;
	}
	/**
	 * @param regionname the regionname to set
	 */
	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}
	/**
	 * @return the shopaddress
	 */
	public String getShopaddress() {
		return shopaddress;
	}
	/**
	 * @param shopaddress the shopaddress to set
	 */
	public void setShopaddress(String shopaddress) {
		this.shopaddress = shopaddress;
	}
	/**
	 * @return the geo_lat
	 */
	public double getGeo_lat() {
		return geo_lat;
	}
	/**
	 * @param geo_lat the geo_lat to set
	 */
	public void setGeo_lat(double geo_lat) {
		this.geo_lat = geo_lat;
	}
	/**
	 * @return the geo_long
	 */
	public double getGeo_long() {
		return geo_long;
	}
	/**
	 * @param geo_long the geo_long to set
	 */
	public void setGeo_long(double geo_long) {
		this.geo_long = geo_long;
	}
	
	
}
