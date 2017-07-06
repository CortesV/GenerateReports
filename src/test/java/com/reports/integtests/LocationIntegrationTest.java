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
import com.reports.components.entity.Location;
import com.reports.components.interfaces.ILocation;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = GenReportsApplication.class)
@WebAppConfiguration
@Transactional
public class LocationIntegrationTest {

	@Autowired
	private ILocation iLocation;

	private Location location;

	private static final String LOCATION_NAME = "Madrid";
	private static final Double LATITUDE = 11.2;
	private static final Double LONGITUDE = 123.2;
	private static final Integer ID = 1;

	@Before
	public void setUp() {
		location = new Location(LOCATION_NAME, LATITUDE, LONGITUDE);
	}

	@Rollback(false)
	@Test
	public void createLocationTest() {
		Integer sizeBeforeSave = iLocation.getLocations().size();
		iLocation.saveLocation(location);
		;
		assertNotEquals(Integer.valueOf(iLocation.getLocations().size()), sizeBeforeSave);
	}

	@Rollback(false)
	@Test
	public void updateLocationTest() {
		Location findLocation = iLocation.getLocationById(ID);
		findLocation.setLocationName(LOCATION_NAME);
		;
		iLocation.updateLocation(ID, findLocation);
		assertEquals(iLocation.getLocationById(ID).getLocationName(), LOCATION_NAME);
	}

	@Rollback(false)
	@Test
	public void deleteLocationTest() {
		Integer sizeBeforeDelete = iLocation.getLocations().size();
		iLocation.deleteLocation(ID);
		assertNotEquals(Integer.valueOf(iLocation.getLocations().size()), sizeBeforeDelete);
	}

}


