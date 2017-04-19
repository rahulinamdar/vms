package com.ims.service;

import com.ims.beans.UserBean;
import com.ims.entity.User;


public interface UserService {
	
	void saveUser(UserBean userBean);
	

	User findUserByName(String userName);
	
	boolean authenticateUser(String rawPasword, User user);
	
	
	
}
