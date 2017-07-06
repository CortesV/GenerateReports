package com.reports.components.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.reports.components.entity.Country;
import com.reports.components.interfaces.ICountry;

/**
 * CRUD for entity Country
 * 
 * @author cortes
 *
 */
@Repository
public class CountryDao implements ICountry {

	private static final String SQL_GET_COUNTRIES = "FROM Country country";
	private static final String SQL_GET_COUNTRY_BY_ID = "FROM Country country WHERE id = :id";
	private static final String SQL_DELETE_COUNTRY_BY_ID = "DELETE FROM Country WHERE id  IN(:id)";
	private static final String SQL_UPDATE_COUNTRY_BY_ID = "UPDATE Country country SET country_name = :countryName, language = :language WHERE id=:id";

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Method that return all countries in database
	 * 
	 * @return return - list of countries
	 */
	@Transactional
	@Override
	public List<Country> getCountries() {
		return sessionFactory.openSession().createQuery(SQL_GET_COUNTRIES).list();
	}

	/**
	 * Delete country by it's id
	 * 
	 * @param id
	 *            id - id of country in database
	 */
	@Transactional
	@Override
	public void deleteCountry(Integer id) {
		sessionFactory.openSession().createQuery(SQL_DELETE_COUNTRY_BY_ID).setParameter("id", id).executeUpdate();
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
	@Transactional
	@Override
	public void updateCountry(Integer id, Country country) {
		sessionFactory.openSession().createQuery(SQL_UPDATE_COUNTRY_BY_ID).setParameter("countryName", country.getCountryName())
				.setParameter("language", country.getLanguage()).setParameter("id", id).executeUpdate();
	}

	/**
	 * Get country by id
	 * 
	 * @param id
	 *            id - id of country in database
	 * @return return information about of country
	 */
	@Transactional
	@Override
	public Country getCountryById(Integer id) {
		return (Country) sessionFactory.openSession().createQuery(SQL_GET_COUNTRY_BY_ID).setParameter("id", id).uniqueResult();
	}

	/**
	 * Save country to database
	 * 
	 * @param country
	 *            country - all information about country that will save to
	 *            database
	 */
	@Transactional
	@Override
	public void saveCountry(Country country) {
		sessionFactory.openSession().save(country);
	}

}
