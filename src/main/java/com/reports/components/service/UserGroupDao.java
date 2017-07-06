package com.reports.components.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.reports.components.entity.UserGroup;
import com.reports.components.interfaces.IUserGroup;

/**
 * CRUD for entity UserGroup
 * 
 * @author cortes
 *
 */
@Repository
public class UserGroupDao implements IUserGroup {

	private static final String SQL_GET_USERGROUPSS = "FROM UserGroup userGroup";
	private static final String SQL_GET_USERGROUP_BY_ID = "FROM UserGroup userGroup WHERE id = :id";
	private static final String SQL_DELETE_USERGROUP_BY_ID = "DELETE FROM UserGroup WHERE id IN(:id) ";
	private static final String SQL_UPDATE_USERGROUPBY_ID = "UPDATE UserGroup userGroup SET group_name = :groupName WHERE id=:id";

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Method that return all user groups in database
	 * 
	 * @return return - list of groups
	 */
	@Transactional
	@Override
	public List<UserGroup> getUserGroups() {
		return sessionFactory.openSession().createQuery(SQL_GET_USERGROUPSS).list();
	}

	/**
	 * Delete user group by it's id
	 * 
	 * @param id
	 *            id - id of user group in database
	 */
	@Transactional
	@Override
	public void deleteUserGroup(Integer id) {
		sessionFactory.openSession().createQuery(SQL_DELETE_USERGROUP_BY_ID).setParameter("id", id).executeUpdate();
	}

	/**
	 * Update user group information
	 * 
	 * @param id
	 *            id - id of user group in database
	 * @param userGroup
	 *            userGroup - all information about user group that will write
	 *            to database
	 */
	@Transactional
	@Override
	public void updateUserGroup(Integer id, UserGroup userGroup) {
		sessionFactory.openSession().createQuery(SQL_UPDATE_USERGROUPBY_ID)
				.setParameter("groupName", userGroup.getGroupName()).setParameter("id", id).executeUpdate();
	}

	/**
	 * Get user group by id
	 * 
	 * @param id
	 *            id - id of user group in database
	 * @return return information about of user group
	 */
	@Transactional
	@Override
	public UserGroup getUserGroupById(Integer id) {
		return (UserGroup) sessionFactory.openSession().createQuery(SQL_GET_USERGROUP_BY_ID).setParameter("id", id)
				.uniqueResult();
	}

	/**
	 * Save user group to database
	 * 
	 * @param userGroup
	 *            userGroup - all information about user group that will save to
	 *            database
	 */
	@Transactional
	@Override
	public void saveUserGroup(UserGroup userGroup) {
		sessionFactory.openSession().save(userGroup);
	}

}
