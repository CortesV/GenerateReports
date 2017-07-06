package com.reports.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reports.components.entity.Request;
import com.reports.service.RequestService;

@RestController
@RequestMapping(value = "/rest/test/v1/request/")
public class RequestController {

	@Autowired
	private RequestService requestService;
	
	/**
	 * Method that return all requests in database
	 * 
	 * @return return - list of requests
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Request> getRequests(){
		return requestService.getRequests();
	}

	/**
	 * Delete request by it's id
	 * 
	 * @param id
	 *            id - id of request in database
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteRequest(@PathVariable("id") Integer id){
		requestService.deleteRequest(id);
	}

	/**
	 * Update request's information
	 * 
	 * @param id
	 *            id - id of request in database
	 * @param request
	 *            request - all information about request that will write to database
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateRequest(@PathVariable("id") Integer id, @RequestBody Request request){
		requestService.updateRequest(id, request);
	}

	/**
	 * Get request by id
	 * 
	 * @param id
	 *            id - id of request in database
	 * @return return information about of request
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Request getRequestById(@PathVariable("id") Integer id){
		return requestService.getRequestById(id);
	}

	/**
	 * Save request to database
	 * 
	 * @param request
	 *            request - all information about request that will save to database
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void saveRequest(@RequestBody Request request){
		requestService.saveRequest(request);
	}

}
