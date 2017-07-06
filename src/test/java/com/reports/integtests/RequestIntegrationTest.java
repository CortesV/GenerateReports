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
import com.reports.components.entity.Request;
import com.reports.components.interfaces.IRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = GenReportsApplication.class)
@WebAppConfiguration
@Transactional
public class RequestIntegrationTest {

	@Autowired
	private IRequest iRequest;

	private Request request;

	private static final String URL = "https://www.google.com.ua/";
	private static final String METHOD = "GET";
	private static final String PARAMS = "filter";
	private static final Integer ID = 1;

	@Before
	public void setUp() {
		request = new Request(URL, METHOD, PARAMS);
	}

	@Rollback(false)
	@Test
	public void createRequestTest() {
		Integer sizeBeforeSave = iRequest.getRequests().size();
		iRequest.saveRequest(request);
		;
		assertNotEquals(Integer.valueOf(iRequest.getRequests().size()), sizeBeforeSave);
	}

	@Rollback(false)
	@Test
	public void updateRequestTest() {
		Request findRequest = iRequest.getRequestById(ID);
		findRequest.setUrl(URL);
		iRequest.updateRequest(ID, findRequest);
		assertEquals(iRequest.getRequestById(ID).getUrl(), URL);
	}

	@Rollback(false)
	@Test
	public void deleteRequestTest() {
		Integer sizeBeforeDelete = iRequest.getRequests().size();
		iRequest.deleteRequest(ID);
		assertNotEquals(Integer.valueOf(iRequest.getRequests().size()), sizeBeforeDelete);
	}

}
