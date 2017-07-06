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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.reports.GenReportsApplication;
import com.reports.components.entity.Country;
import com.reports.components.interfaces.ICountry;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = GenReportsApplication.class)
@WebAppConfiguration
@Transactional
public class CountryIntegrationTest {

	@Autowired
	private ICountry iCountry;
	
	private Country country;
	
	private static final String COUNTRY_NAME = "Poland";
	private static final String LANGUAGE = "Polish";
	private static final Integer ID = 1;
	
	@Before
	public void setUp() {
		country = new Country(COUNTRY_NAME, LANGUAGE);
	}

	@Rollback(false)
	@Test
	public void createCountryTest() {
		Integer sizeBeforeSave = iCountry.getCountries().size();
		iCountry.saveCountry(country);
		assertNotEquals(Integer.valueOf(iCountry.getCountries().size()), sizeBeforeSave);
	}
	
	@Rollback(false)
	@Test
	public void updateCountryTest() {
		Country findCountry = iCountry.getCountryById(ID);
		findCountry.setLanguage(LANGUAGE);
		iCountry.updateCountry(ID, findCountry);
		assertEquals(iCountry.getCountryById(ID).getLanguage(), LANGUAGE);
	}
	
	@Rollback(false)
	@Test
	public void deleteCountryTest() {
		Integer sizeBeforeDelete = iCountry.getCountries().size();
		iCountry.deleteCountry(ID);
		assertNotEquals(Integer.valueOf(iCountry.getCountries().size()), sizeBeforeDelete);
	}

}
