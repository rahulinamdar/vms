package com.ims.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ims.entity.Role;
@Repository
@Transactional
public class RoleDaoImpl implements RoleDao{

	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Role findRoleById(Long id) {
		// TODO Auto-generated method stub
		
		Role role = entityManager.find(Role.class,id);
		
		
		return role;
	}
	
	

}
