package com.reports.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reports.components.entity.UserGroup;
import com.reports.service.UserGroupService;

@RestController
@RequestMapping(value = "/rest/test/v1/group/")
public class UserGroupController {

	@Autowired
	private UserGroupService userGroupService;

	/**
	 * Method that return all user groups in database
	 * 
	 * @return return - list of groups
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserGroup> getUserGroups() {
		return userGroupService.getUserGroups();
	}

	/**
	 * Delete user group by it's id
	 * 
	 * @param id
	 *            id - id of user group in database
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteUserGroup(@PathVariable("id") Integer id) {
		userGroupService.deleteUserGroup(id);
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
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateUserGroup(@PathVariable("id") Integer id, @RequestBody UserGroup userGroup) {
		userGroupService.updateUserGroup(id, userGroup);
	}

	/**
	 * Get user group by id
	 * 
	 * @param id
	 *            id - id of user group in database
	 * @return return information about of user group
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserGroup getUserGroupById(@PathVariable("id") Integer id) {
		return userGroupService.getUserGroupById(id);
	}

	/**
	 * Save user group to database
	 * 
	 * @param userGroup
	 *            userGroup - all information about user group that will save to
	 *            database
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void saveUserGroup(@RequestBody UserGroup userGroup) {
		userGroupService.saveUserGroup(userGroup);
	}

}
