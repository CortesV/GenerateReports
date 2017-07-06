package com.reports.components.interfaces;

import java.util.List;

import com.reports.components.entity.Country;

public interface ICountry {

	/**
	 * Method that return all countries in database
	 * 
	 * @return return - list of countries
	 */
	public List<Country> getCountries();

	/**
	 * Delete country by it's id
	 * 
	 * @param id
	 *            id - id of country in database
	 */
	public void deleteCountry(Integer id);

	/**
	 * Update country's information
	 * 
	 * @param id
	 *            id - id of country in database
	 * @param country
	 *            country - all information about country that will write to
	 *            database
	 */
	public void updateCountry(Integer id, Country country);

	/**
	 * Get country by id
	 * 
	 * @param id
	 *            id - id of country in database
	 * @return return information about of country
	 */
	public Country getCountryById(Integer id);

	/**
	 * Save country to database
	 * 
	 * @param country
	 *            country - all information about country that will save to database
	 */
	public void saveCountry(Country country);

}
