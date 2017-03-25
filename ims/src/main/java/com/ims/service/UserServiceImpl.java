package com.ims.service;

import javax.transaction.Transactional;

import org.jboss.logging.annotations.Transform.TransformType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
	
	@Override
	@Transactional
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userDao.saveUser(user);
	}

	/**
	 * @author vikrantraje
	 * <p>Authenticate user</p>
	 * @param user 
	 * @return user
	 */
	@Override
	public boolean authenticateUser(String rawPassword, User user) {
		// TODO Auto-generated method stub
		boolean isAuthenticateUser;
		if(passwordEncoder.matches(rawPassword, user.getPassword())){
			isAuthenticateUser = true;
		}else {
			isAuthenticateUser = false;
		}
		return isAuthenticateUser;
	}

	
	/**
	 * @author vikrantraje
	 * <p>Find user by user name</p>
	 * @param userName
	 */
	@Override
	public User findUserByName(String userName) {
		// TODO Auto-generated method stub
		return userDao.findUserByName(userName);
	}


}
