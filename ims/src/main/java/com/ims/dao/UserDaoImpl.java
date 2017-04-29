package com.ims.dao;

/**
 * @author vikrantraje
 * <b>This is an implemention class for user related operations</b>
 */
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.ims.beans.UserBean;
import com.ims.entity.Region;
import com.ims.entity.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
	
	@Override
	public void saveUser(UserBean userBean) {
		
		User user =  new User();
//		Role role = roleService.findRoleById((long)1);
		user.setUserName(userBean.getUserName());
		user.setPassword(passwordEncoder.encode(userBean.getPassword()));
		if(userBean.getRegion() != null && !userBean.getRegion().equals("")){
		TypedQuery<Region> query = entityManager.createNamedQuery("Region.getRegion",Region.class).setParameter("regionId",userBean.getRegion());
//		user.setRole(role);	
		user.setRegion(query.getSingleResult());
		}
		entityManager.persist(user);
	}

	/**
	 * @author vikrantraje
	 * @param userName
	 * @return User
	 */
	@Override
	public User findUserByName(String userName) {
		
		TypedQuery<User> query = entityManager.createNamedQuery("User.findUser",User.class).setParameter("userName", userName);
		try{
			return query.getSingleResult();
		}catch(NoResultException ex){
			return null;
		}
	}

}
