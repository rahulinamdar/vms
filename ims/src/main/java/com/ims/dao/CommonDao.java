/**
 * 
 */
package com.ims.dao;

import java.util.List;

import com.ims.entity.OrderType;
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

}
