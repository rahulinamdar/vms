package com.ims.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.ims.beans.RegionBean;

public interface RegionService {

	List<Map<String, Object>> getRegions();

	void addRegion(RegionBean region) throws ParseException;

}
