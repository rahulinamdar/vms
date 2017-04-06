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
import com.ims.entity.Status;
import com.ims.entity.Uom;

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

	/* (non-Javadoc)
	 * @see com.ims.service.CommonService#getAllUom()
	 */
	@Override
	public List<Map<String, Object>> getAllUom() {
		// TODO Auto-generated method stub
		
		
		List<Map<String, Object>> uoms = new ArrayList<>();
		List<Uom> orderTypes = dao.getAllUom();
		Iterator<Uom> ots = orderTypes.iterator();
				while(ots.hasNext()){
					Uom uom = ots.next();
					
					Map<String, Object> ot = new HashMap<>();
					ot.put("uomId", uom.getUomid());
					ot.put("uom", uom.getUom());
					uoms.add(ot);
				}
		
		return uoms;
	}

	/* (non-Javadoc)
	 * @see com.ims.service.CommonService#getAllStatus()
	 */
	@Override
	public List<Map<String, Object>> getAllStatus() {
		// TODO Auto-generated method stub
		

		List<Map<String, Object>> statuses = new ArrayList<>();
		List<Status> orderTypes = dao.getAllStatus();
		Iterator<Status> ots = orderTypes.iterator();
				while(ots.hasNext()){
					Status st = ots.next();
					
					Map<String, Object> ot = new HashMap<>();
					ot.put("statusId", st.getStatusId());
					ot.put("status", st.getStatus());
					statuses.add(ot);
				}
		
		return statuses;
	}

}
