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
import com.reports.components.entity.User;
import com.reports.components.interfaces.IUser;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = GenReportsApplication.class)
@WebAppConfiguration
@Transactional
public class UserIntegrationTest {

	@Autowired
	private IUser iUser;

	private User user;

	private static final String USERNAME = "ADMIN";
	private static final Integer ID = 1;

	@Before
	public void setUp() {
		user = new User(USERNAME);
	}

	@Rollback(false)
	@Test
	public void createUserTest() {
		Integer sizeBeforeSave = iUser.getUsers().size();
		iUser.saveUser(user);
		assertNotEquals(Integer.valueOf(iUser.getUsers().size()), sizeBeforeSave);
	}

	@Rollback(false)
	@Test
	public void updateUserTest() {
		User findUser = iUser.getUserById(ID);
		findUser.setUserName(USERNAME);
		iUser.updateUser(ID, findUser);
		assertEquals(iUser.getUserById(ID).getUserName(), USERNAME);
	}

	@Rollback(false)
	@Test
	public void deleteUserTest() {
		Integer sizeBeforeDelete = iUser.getUsers().size();
		iUser.deleteUser(ID);
		assertNotEquals(Integer.valueOf(iUser.getUsers().size()), sizeBeforeDelete);
	}

}
