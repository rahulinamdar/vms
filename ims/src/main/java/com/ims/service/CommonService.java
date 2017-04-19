/**
 * 
 */
package com.ims.service;

import java.util.List;
import java.util.Map;

import com.ims.entity.OrderType;
import com.ims.entity.ProductCategory;
import com.ims.entity.Status;
import com.ims.entity.Uom;

/**
 * @author rahul
 *
 */
public interface CommonService {

	List<Map<String, Object>> getOrderTypes();

	/**
	 * @author rahul
	
	 * @return
	 * List<Map<String,Object>>
	 *
	 */
	List<Map<String, Object>> getAllUom();

	/**
	 * @author rahul
	
	 * @return
	 * List<Map<String,Object>>
	 *
	 */
	List<Map<String, Object>> getAllStatus();

	/**
	 * @author rahul
	
	 * @return
	 * List<Map<String,Object>>
	 *
	 */
	List<Map<String, Object>> getAllCategories();

	/**
	 * @author rahul
	 * @param uom 
	
	 * @return
	 * List<Map<String,Object>>
	 *
	 */
	void addUom(Uom uom);

	/**
	 * @author rahul
	
	 * @param status
	 * @return
	 * List<Map<String,Object>>
	 *
	 */
	void addStatus(Status status);

	/**
	 * @author rahul
	
	 * @param category
	 * @return
	 * List<Map<String,Object>>
	 *
	 */
	void addCategory(ProductCategory category);

	/**
	 * @author rahul
	
	 * @param statusId
	 * void
	 *
	 */
	void deleteStatus(String statusId);

	/**
	 * @author rahul
	
	 * @param uom
	 * void
	 *
	 */
	void deleteUom(String uom);

	/**
	 * @author rahul
	
	 * @param category
	 * void
	 *
	 */
	void deleteCategory(String category);

	/**
	 * @author rahul
	
	 * @param regionId
	 * void
	 *
	 */
	void deleteRegion(String regionId);

	/**
	 * @author rahul
	
	 * @param status
	 * void
	 *
	 */
	void addOrderType(OrderType status);

}
