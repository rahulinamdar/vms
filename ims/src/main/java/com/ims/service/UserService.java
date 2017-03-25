package com.ims.service;

import com.ims.entity.User;


public interface UserService {
	
	void saveUser(User user);
	

	User findUserByName(String userName);
	
	boolean authenticateUser(String rawPasword, User user);
	
	
	
}
