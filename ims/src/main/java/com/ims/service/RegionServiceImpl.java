package com.ims.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.beans.RegionBean;
import com.ims.dao.RegionDao;
import com.ims.entity.Region;

@Transactional
@Service
public class RegionServiceImpl implements RegionService {

	
	@Autowired
	RegionDao regionDao;
	
	@Override
	public List<Map<String, Object>> getRegions() {
		// TODO Auto-generated method stub
		List<Region> regions = regionDao.getRegions();
		List<Map<String, Object>> regionsMap = new ArrayList<>();
		
		
		
		Iterator<Region> itr = regions.iterator();
		while(itr.hasNext()){
			Region reg = itr.next();
			Map<String,Object> region = new HashMap<>();
			region.put("regionId", reg.getRegionid());
			region.put("regionName", reg.getRegionname());
			region.put("lat", reg.getGeo_lat());	
			region.put("long", reg.getGeo_long());
			region.put("address", reg.getShopaddress());	
			regionsMap.add(region);
		}
		
		return regionsMap;
	}

	@Override
	public void addRegion(RegionBean region) throws ParseException {
		// TODO Auto-generated method stub
		regionDao.addRegion(region);
	}

}
