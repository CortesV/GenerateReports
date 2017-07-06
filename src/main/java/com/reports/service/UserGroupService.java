package com.reports.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reports.components.entity.UserGroup;
import com.reports.components.interfaces.IUserGroup;

@Service
public class UserGroupService {

	@Autowired
	private IUserGroup iUserGroup;
	
	/**
	 * Method that return all user groups in database
	 * 
	 * @return return - list of groups
	 */
	public List<UserGroup> getUserGroups(){
		return iUserGroup.getUserGroups();
	}

	/**
	 * Delete user group by it's id
	 * 
	 * @param id
	 *            id - id of user group in database
	 */
	public void deleteUserGroup(Integer id){
		iUserGroup.deleteUserGroup(id);
	}

	/**
	 * Update user group information
	 * 
	 * @param id
	 *            id - id of user group in database
	 * @param userGroup
	 *            userGroup - all information about user group that will write to database
	 */
	public void updateUserGroup(Integer id, UserGroup userGroup){
		iUserGroup.updateUserGroup(id, userGroup);
	}

	/**
	 * Get user group by id
	 * 
	 * @param id
	 *            id - id of user group in database
	 * @return return information about of user group
	 */
	public UserGroup getUserGroupById(Integer id){
		return iUserGroup.getUserGroupById(id);
	}

	/**
	 * Save user group to database
	 * 
	 * @param userGroup
	 *            userGroup - all information about user group that will save to database
	 */
	public void saveUserGroup(UserGroup userGroup){
		iUserGroup.saveUserGroup(userGroup);
	}

}
