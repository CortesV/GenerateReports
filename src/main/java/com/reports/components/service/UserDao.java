package com.reports.components.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.reports.components.entity.User;
import com.reports.components.interfaces.IUser;

/**
 * CRUD for entity User
 * 
 * @author cortes
 *
 */
@Repository
public class UserDao implements IUser {

	private static final String SQL_GET_USERS = "FROM User user";
	private static final String SQL_GET_USER_BY_ID = "FROM User user WHERE id = :id";
	private static final String SQL_GET_USER_BY_USERNAME = "FROM User user WHERE user_name = :userName";
	private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM User WHERE id IN(:id)";
	private static final String SQL_UPDATE_USER_BY_ID = "UPDATE User user SET user_name = :userName WHERE id=:id";

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Get user by username
	 * 
	 * @param username
	 *            username - username of user in database
	 * @return return information about of user
	 */
	@Transactional
	@Override
	public User findByUsername(String username) {
		return (User) sessionFactory.openSession().createQuery(SQL_GET_USER_BY_USERNAME).uniqueResult();
	}

	/**
	 * Method that return all users in database
	 * 
	 * @return return - list of users
	 */
	@Transactional
	@Override
	public List<User> getUsers() {
		return sessionFactory.openSession().createQuery(SQL_GET_USERS).list();
	}

	/**
	 * Delete user by it's id
	 * 
	 * @param id
	 *            id - id of user in database
	 */
	@Transactional
	@Override
	public void deleteUser(Integer id) {
		sessionFactory.openSession().createQuery(SQL_DELETE_USER_BY_ID).setParameter("id", id).executeUpdate();
	}

	/**
	 * Update user's information
	 * 
	 * @param id
	 *            id - id of user in database
	 * @param user
	 *            user - all information about user that will write to database
	 */
	@Transactional
	@Override
	public void updateUser(Integer id, User user) {
		sessionFactory.openSession().createQuery(SQL_UPDATE_USER_BY_ID).setParameter("userName", user.getUserName())
				.executeUpdate();
	}

	/**
	 * Get user by id
	 * 
	 * @param id
	 *            id - id of user in database
	 * @return return information about of user
	 */
	@Transactional
	@Override
	public User getUserById(Integer id) {
		return (User) sessionFactory.openSession().createQuery(SQL_GET_USER_BY_ID).setParameter("id", id)
				.uniqueResult();
	}

	/**
	 * Save user to database
	 * 
	 * @param user
	 *            user - all information about user that will save to database
	 */
	@Transactional
	@Override
	public void saveUser(User user) {
		sessionFactory.openSession().save(user);
	}

}
