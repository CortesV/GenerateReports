package com.reports.integtests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.Date;

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
import com.reports.components.entity.NetworkSession;
import com.reports.components.interfaces.ISession;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = GenReportsApplication.class)
@WebAppConfiguration
@Transactional
public class SessionIntergationTest {

	@Autowired
	private ISession iSession;

	private NetworkSession session;

	private static final Date DATE_OPENED = new Date(12, 12, 12);
	private static final Date DATE_CLOSED = new Date(12, 12, 12);
	private static final Integer ID = 1;

	@Before
	public void setUp() {
		session = new NetworkSession(DATE_OPENED, DATE_CLOSED);
	}

	@Rollback(false)
	@Test
	public void createSessionTest() {
		Integer sizeBeforeSave = iSession.getSessions().size();
		iSession.saveSession(session);
		assertNotEquals(Integer.valueOf(iSession.getSessions().size()), sizeBeforeSave);
	}

	@Rollback(false)
	@Test
	public void updateSessionTest() {
		NetworkSession findSession = iSession.getSesionById(ID);
		findSession.setDateClosed(DATE_CLOSED);
		iSession.updateSession(ID, findSession);
		assertEquals(iSession.getSesionById(ID).getDateClosed(), DATE_CLOSED);
	}

	@Rollback(false)
	@Test
	public void deleteSessionTest() {
		Integer sizeBeforeDelete = iSession.getSessions().size();
		iSession.deleteSession(ID);
		assertNotEquals(Integer.valueOf(iSession.getSessions().size()), sizeBeforeDelete);
	}

}
