package com.reports.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reports.components.entity.Location;
import com.reports.service.LocationService;

@RestController
@RequestMapping(value = "/rest/test/v1/location/")
public class LocationController {

	@Autowired
	private LocationService locationService;

	/**
	 * Method that return all locations in database
	 * 
	 * @return return - list of locations
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Location> getLocations(){
		return locationService.getLocations();
	}

	/**
	 * Delete location by it's id
	 * 
	 * @param id
	 *            id - id of location in database
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteLocation(@PathVariable("id") Integer id){
		locationService.deleteLocation(id);
	}

	/**
	 * Update location's information
	 * 
	 * @param id
	 *            id - id of location in database
	 * @param location
	 *            location - all information about location that will write to database
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateLocation(@PathVariable("id") Integer id, @RequestBody Location location){
		locationService.updateLocation(id, location);
	}

	/**
	 * Get location by id
	 * 
	 * @param id
	 *            id - id of location in database
	 * @return return information about of location
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Location getLocationById(@PathVariable("id") Integer id){
		return locationService.getLocationById(id);
	}

	/**
	 * Save location to database
	 * 
	 * @param location
	 *            location - all information about location that will save to database
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void saveLocation(@RequestBody Location location){
		locationService.saveLocation(location);
	}

}
