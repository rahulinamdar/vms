package com.ims.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.dao.RoleDao;
import com.ims.entity.Role;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public Role findRoleById(Long id) {
		// TODO Auto-generated method stub
		
		return roleDao.findRoleById(id);
	}

	
	
}
