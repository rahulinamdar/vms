/**
 * 
 */
package com.ims.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.dao.CommonDao;
import com.ims.entity.OrderType;

/**
 * @author rahul
 *
 *
 */
@Transactional
@Service
public class CommonServiceImpl implements CommonService{

	@Autowired
	CommonDao dao;
	
	@Override
	public List<Map<String, Object>> getOrderTypes() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> oTypes = new ArrayList<>();
		List<OrderType> orderTypes = dao.getOrderTypes();
		Iterator<OrderType> ots = orderTypes.iterator();
				while(ots.hasNext()){
					OrderType orderType = ots.next();
					
					Map<String, Object> ot = new HashMap<>();
					ot.put("orderTypeId", orderType.getOrderTypeId());
					ot.put("orderTypeDesc", orderType.getOrderTypeDesc());
					oTypes.add(ot);
				}
		
		return oTypes;
	}

}
