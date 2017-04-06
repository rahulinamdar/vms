/**
 * 
 */
package com.ims.service;

import java.util.List;
import java.util.Map;

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

}
