package com.ims.service;

import javax.transaction.Transactional;

import org.jboss.logging.annotations.Transform.TransformType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ims.dao.UserDao;
import com.ims.dao.UserDaoImpl;
import com.ims.entity.User;

@Transactional
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		userDao.saveUser(user);
	}

}
