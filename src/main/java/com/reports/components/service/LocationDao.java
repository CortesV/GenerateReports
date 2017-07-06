package com.reports.components.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.reports.components.entity.Location;
import com.reports.components.interfaces.ILocation;
import com.reports.config.HibernateConfiguration;

/**
 * CRUD for entity Location
 * 
 * @author cortes
 *
 */
@Repository
public class LocationDao implements ILocation {

	private static final String SQL_GET_LOCATIONS = "FROM Location location";
	private static final String SQL_GET_LOCATION_BY_ID = "FROM Location location WHERE id = :id";
	private static final String SQL_DELETE_LOCATION_BY_ID = "DELETE FROM Location WHERE id IN(:id)";
	private static final String SQL_UPDATE_LOCATION_BY_ID = "UPDATE Location location SET location_name = :locationName, latitude = :latitude, longitude = :longitude WHERE id=:id";

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateConfiguration hibernateUtil;

	/**
	 * Method that return all locations in database
	 * 
	 * @return return - list of locations
	 */
	@Transactional
	@Override
	public List<Location> getLocations() {
		return sessionFactory.openSession().createQuery(SQL_GET_LOCATIONS).list();
	}

	/**
	 * Delete location by it's id
	 * 
	 * @param id
	 *            id - id of location in database
	 */
	@Transactional
	@Override
	public void deleteLocation(Integer id) {
		sessionFactory.openSession().createQuery(SQL_DELETE_LOCATION_BY_ID).setParameter("id", id).executeUpdate();
	}

	/**
	 * Update location's information
	 * 
	 * @param id
	 *            id - id of location in database
	 * @param location
	 *            location - all information about location that will write to
	 *            database
	 */
	@Transactional
	@Override
	public void updateLocation(Integer id, Location location) {
		sessionFactory.openSession().createQuery(SQL_UPDATE_LOCATION_BY_ID)
				.setParameter("locationName", location.getLocationName())
				.setParameter("latitude", location.getLatitude()).setParameter("longitude", location.getLongitude())
				.setParameter("id", id).executeUpdate();
	}

	/**
	 * Get location by id
	 * 
	 * @param id
	 *            id - id of location in database
	 * @return return information about of location
	 */
	@Transactional
	@Override
	public Location getLocationById(Integer id) {
		return (Location) sessionFactory.openSession().createQuery(SQL_GET_LOCATION_BY_ID).setParameter("id", id)
				.uniqueResult();
	}

	/**
	 * Save location to database
	 * 
	 * @param location
	 *            location - all information about location that will save to
	 *            database
	 */
	@Transactional
	@Override
	public void saveLocation(Location location) {
		sessionFactory.openSession().save(location);
	}

}
