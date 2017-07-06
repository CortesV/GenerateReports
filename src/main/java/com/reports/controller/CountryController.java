package com.reports.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reports.components.entity.Country;
import com.reports.service.CountryService;

@RestController
@RequestMapping(value = "/rest/test/v1/country/")
public class CountryController {

	@Autowired
	private CountryService countryService;

	/**
	 * Method that return all countries in database
	 * 
	 * @return return - list of countries
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Country> getCountries() {
		return countryService.getCountries();
	}

	/**
	 * Get country by id
	 * 
	 * @param id
	 *            id - id of country in database
	 * @return return information about of country
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Country getCountryById(@PathVariable("id") Integer id) {
		return countryService.getCountryById(id);
	}

	/**
	 * Save country to database
	 * 
	 * @param country
	 *            country - all information about country that will save to database
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void saveCountry(@RequestBody Country country) {
		countryService.saveCountry(country);
	}

	/**
	 * Delete country by it's id
	 * 
	 * @param id
	 *            id - id of country in database
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteCountry(@PathVariable("id") Integer id) {
		countryService.deleteCountry(id);
	}

	/**
	 * Update country's information
	 * 
	 * @param id
	 *            id - id of country in database
	 * @param country
	 *            country - all information about country that will write to
	 *            database
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateCountry(@PathVariable("id") Integer id, @RequestBody Country country) {
		countryService.updateCountry(id, country);
	}

}
