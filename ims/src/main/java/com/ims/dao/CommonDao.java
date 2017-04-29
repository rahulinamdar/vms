/**
 * 
 */
package com.ims.dao;

import java.util.List;

import com.ims.entity.OrderType;
import com.ims.entity.ProductCategory;
import com.ims.entity.Status;
import com.ims.entity.Uom;

/**
 * @author rahul
 *
 */
public interface CommonDao {

	public List<OrderType> getOrderTypes() ;

	/**
	 * @author rahul
	
	 * void
	 *
	 */
	public List<Uom> getAllUom();

	/**
	 * @author rahul
	
	 * void
	 *
	 */
	public List<Status> getAllStatus();

	/**
	 * @author rahul
	
	 * @return
	 * List<ProductCategory>
	 *
	 */
	public List<ProductCategory> getAllCategories();

	/**
	 * @author rahul
	
	 * void
	 * @param uom 
	 *
	 */
	public void addUom(Uom uom);

	/**
	 * @author rahul
	
	 * @param status
	 * void
	 *
	 */
	public void addStatus(Status status);

	/**
	 * @author rahul
	
	 * @param category
	 * void
	 *
	 */
	public void addCategory(ProductCategory category);

	/**
	 * @author rahul
	
	 * @param statusId
	 * void
	 *
	 */
	public void deleteStatus(String statusId);

	/**
	 * @author rahul
	
	 * @param uom
	 * void
	 *
	 */
	public void deleteUom(String uom);

	/**
	 * @author rahul
	
	 * @param category
	 * void
	 *
	 */
	public void deleteCategory(String category);

	/**
	 * @author rahul
	
	 * @param regionId
	 * void
	 *
	 */
	public void deleteRegion(String regionId);

	/**
	 * @author rahul
	
	 * @param oType
	 * void
	 *
	 */
	public void addOrderType(OrderType oType);

}
