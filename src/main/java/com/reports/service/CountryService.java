package com.reports.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reports.components.entity.Country;
import com.reports.components.interfaces.ICountry;

@Service
public class CountryService {

	@Autowired
	private ICountry iCountry;
	
	/**
	 * Method that return all countries in database
	 * 
	 * @return return - list of countries
	 */
	public List<Country> getCountries(){
		return iCountry.getCountries();
	}

	/**
	 * Delete country by it's id
	 * 
	 * @param id
	 *            id - id of country in database
	 */
	public void deleteCountry(Integer id){
		iCountry.deleteCountry(id);
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
	public void updateCountry(Integer id, Country country){
		iCountry.updateCountry(id, country);
	}

	/**
	 * Get country by id
	 * 
	 * @param id
	 *            id - id of country in database
	 * @return return information about of country
	 */
	public Country getCountryById(Integer id){
		return iCountry.getCountryById(id);
	}

	/**
	 * Save country to database
	 * 
	 * @param country
	 *            country - all information about country that will save to database
	 */
	public void saveCountry(Country country){
		iCountry.saveCountry(country);
	}

}
