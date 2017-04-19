package com.ims.dao;

import com.ims.beans.UserBean;
import com.ims.entity.User;

public interface UserDao {

	void saveUser(UserBean user);
	
	User findUserByName(String userName);
	
}
