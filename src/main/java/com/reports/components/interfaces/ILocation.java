package com.reports.components.interfaces;

import java.util.List;

import com.reports.components.entity.Location;

public interface ILocation {

	/**
	 * Method that return all locations in database
	 * 
	 * @return return - list of locations
	 */
	public List<Location> getLocations();

	/**
	 * Delete location by it's id
	 * 
	 * @param id
	 *            id - id of location in database
	 */
	public void deleteLocation(Integer id);

	/**
	 * Update location's information
	 * 
	 * @param id
	 *            id - id of location in database
	 * @param location
	 *            location - all information about location that will write to database
	 */
	public void updateLocation(Integer id, Location location);

	/**
	 * Get location by id
	 * 
	 * @param id
	 *            id - id of location in database
	 * @return return information about of location
	 */
	public Location getLocationById(Integer id);

	/**
	 * Save location to database
	 * 
	 * @param location
	 *            location - all information about location that will save to database
	 */
	public void saveLocation(Location location);

}
