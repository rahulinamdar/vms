/**
 * 
 */
package com.ims.dao;

import java.text.ParseException;
import java.util.List;

import com.ims.beans.RegionBean;
import com.ims.entity.Region;

/**
 * @author rahul
 *
 */
public interface RegionDao {

	List<Region> getRegions();

	void addRegion(RegionBean region) throws ParseException;

}
