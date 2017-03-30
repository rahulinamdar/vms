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

import org.springframework.stereotype.Repository;

import com.ims.entity.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void saveUser(User user) {
		
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
