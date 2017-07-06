package com.reports.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reports.components.entity.Location;
import com.reports.components.interfaces.ILocation;

@Service
public class LocationService {
	
	@Autowired
	private ILocation iLocation;

	/**
	 * Method that return all locations in database
	 * 
	 * @return return - list of locations
	 */
	public List<Location> getLocations(){
		return iLocation.getLocations();
	}

	/**
	 * Delete location by it's id
	 * 
	 * @param id
	 *            id - id of location in database
	 */
	public void deleteLocation(Integer id){
		iLocation.deleteLocation(id);
	}

	/**
	 * Update location's information
	 * 
	 * @param id
	 *            id - id of location in database
	 * @param location
	 *            location - all information about location that will write to database
	 */
	public void updateLocation(Integer id, Location location){
		iLocation.updateLocation(id, location);
	}

	/**
	 * Get location by id
	 * 
	 * @param id
	 *            id - id of location in database
	 * @return return information about of location
	 */
	public Location getLocationById(Integer id){
		return iLocation.getLocationById(id);
	}

	/**
	 * Save location to database
	 * 
	 * @param location
	 *            location - all information about location that will save to database
	 */
	public void saveLocation(Location location){
		iLocation.saveLocation(location);
	}

}
