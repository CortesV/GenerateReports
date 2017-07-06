package com.reports.components.interfaces;

import java.util.List;

import com.reports.components.entity.UserGroup;

public interface IUserGroup {

	/**
	 * Method that return all user groups in database
	 * 
	 * @return return - list of groups
	 */
	public List<UserGroup> getUserGroups();

	/**
	 * Delete user group by it's id
	 * 
	 * @param id
	 *            id - id of user group in database
	 */
	public void deleteUserGroup(Integer id);

	/**
	 * Update user group information
	 * 
	 * @param id
	 *            id - id of user group in database
	 * @param userGroup
	 *            userGroup - all information about user group that will write to database
	 */
	public void updateUserGroup(Integer id, UserGroup userGroup);

	/**
	 * Get user group by id
	 * 
	 * @param id
	 *            id - id of user group in database
	 * @return return information about of user group
	 */
	public UserGroup getUserGroupById(Integer id);

	/**
	 * Save user group to database
	 * 
	 * @param userGroup
	 *            userGroup - all information about user group that will save to database
	 */
	public void saveUserGroup(UserGroup userGroup);

}
