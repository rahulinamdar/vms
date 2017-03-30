/**
 * 
 */
package com.ims.beans;

import java.util.Date;

import com.ims.entity.Region;
import com.ims.entity.Uom;

/**
 * @author rahul
 *
 */
public class StockBean {

	private String productId;
	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * @return the stock
	 */
	public Double getStock() {
		return stock;
	}
	/**
	 * @param stock the stock to set
	 */
	public void setStock(Double stock) {
		this.stock = stock;
	}
	
	
	/**
	 * @return the stockDate
	 */
	public Date getStockDate() {
		return stockDate;
	}
	/**
	 * @param stockDate the stockDate to set
	 */
	public void setStockDate(Date stockDate) {
		this.stockDate = stockDate;
	}
	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}
	/**
	 * @return the uom
	 */
	public String getUom() {
		return uom;
	}
	/**
	 * @param uom the uom to set
	 */
	public void setUom(String uom) {
		this.uom = uom;
	}
	/**
	 * @return the dump
	 */
	public Double getDump() {
		return dump;
	}
	/**
	 * @param dump the dump to set
	 */
	public void setDump(Double dump) {
		this.dump = dump;
	}
	private Double dump;
	private Double stock;
	private String region;
	private String uom;
	private Date stockDate;
	
}
