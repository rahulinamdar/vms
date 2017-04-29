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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ims.dao.CommonDao;
import com.ims.entity.OrderType;
import com.ims.entity.ProductCategory;
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

	/* (non-Javadoc)
	 * @see com.ims.service.CommonService#getAllCategories()
	 */
	@Override
	public List<Map<String, Object>> getAllCategories() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> categories = new ArrayList<>();
		List<ProductCategory> orderTypes = dao.getAllCategories();
		Iterator<ProductCategory> ots = orderTypes.iterator();
				while(ots.hasNext()){
					ProductCategory st = ots.next();
					
					Map<String, Object> ot = new HashMap<>();
					ot.put("category", st.getCategory());
					ot.put("categoryId", st.getCategoryId());
					categories.add(ot);
				}
				return categories;
			}

	/* (non-Javadoc)
	 * @see com.ims.service.CommonService#addUom()
	 */
	@Override
	public void addUom(Uom uom) {
		// TODO Auto-generated method stub
		dao.addUom(uom);
	}

	/* (non-Javadoc)
	 * @see com.ims.service.CommonService#addStatus(com.ims.entity.Status)
	 */
	@Override
	public void addStatus(Status status) {
		dao.addStatus(status);
	}

	/* (non-Javadoc)
	 * @see com.ims.service.CommonService#addCategory(com.ims.entity.ProductCategory)
	 */
	@Override
	public void addCategory(ProductCategory category) {
		dao.addCategory(category);
	}

	/* (non-Javadoc)
	 * @see com.ims.service.CommonService#deleteStatus(java.lang.String)
	 */
	@Override
	public void deleteStatus(String statusId) {
		dao.deleteStatus(statusId);		
	}

	/* (non-Javadoc)
	 * @see com.ims.service.CommonService#deleteUom(java.lang.String)
	 */
	@Override
	public void deleteUom(String uom) {
		// TODO Auto-generated method stub
		dao.deleteUom(uom);	
	}

	/* (non-Javadoc)
	 * @see com.ims.service.CommonService#deleteCategory(java.lang.String)
	 */
	@Override
	public void deleteCategory(String category) {
		// TODO Auto-generated method stub
		dao.deleteCategory(category);	
	}

	/* (non-Javadoc)
	 * @see com.ims.service.CommonService#deleteRegion(java.lang.String)
	 */
	@Override
	public void deleteRegion(String regionId) {
		// TODO Auto-generated method stub
		dao.deleteRegion(regionId);
	}

	/* (non-Javadoc)
	 * @see com.ims.service.CommonService#addOrderType(com.ims.entity.OrderType)
	 */
	@Override
	public void addOrderType(OrderType oType) {
		// TODO Auto-generated method stub
		dao.addOrderType(oType);
		
		
	}

}
