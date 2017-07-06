package com.reports.integtests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.reports.GenReportsApplication;
import com.reports.components.entity.UserGroup;
import com.reports.components.interfaces.IUserGroup;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = GenReportsApplication.class)
@WebAppConfiguration
@Transactional
public class GroupIntegrationTest {

	@Autowired
	private IUserGroup iUserGroup;
	
	private UserGroup userGroup;
	
	private static final String GROUPNAME = "ADMIN";
	private static final Integer ID = 1;	
	
	@Before
	public void setUp() {
		userGroup = new UserGroup(GROUPNAME);
	}

	@Rollback(false)
	@Test
	public void createGroupTest() {
		Integer sizeBeforeSave = iUserGroup.getUserGroups().size();
		iUserGroup.saveUserGroup(userGroup);
		assertNotEquals(Integer.valueOf(iUserGroup.getUserGroups().size()), sizeBeforeSave);
	}
	
	@Rollback(false)
	@Test
	public void updateGroupTest() {
		UserGroup findGroup = iUserGroup.getUserGroupById(ID);
		findGroup.setGroupName(GROUPNAME);
		iUserGroup.updateUserGroup(ID, findGroup);
		assertEquals(iUserGroup.getUserGroupById(ID).getGroupName(), GROUPNAME);
	}
	
	@Rollback(false)
	@Test
	public void deleteGroupTest() {
		Integer sizeBeforeDelete = iUserGroup.getUserGroups().size();
		iUserGroup.deleteUserGroup(ID);
		assertNotEquals(Integer.valueOf(iUserGroup.getUserGroups().size()), sizeBeforeDelete);
	}

}

