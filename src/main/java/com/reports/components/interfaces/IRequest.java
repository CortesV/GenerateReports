package com.reports.components.interfaces;

import java.util.List;

import com.reports.components.entity.Request;


public interface IRequest {

	/**
	 * Method that return all requests in database
	 * 
	 * @return return - list of requests
	 */
	public List<Request> getRequests();

	/**
	 * Delete request by it's id
	 * 
	 * @param id
	 *            id - id of request in database
	 */
	public void deleteRequest(Integer id);

	/**
	 * Update request's information
	 * 
	 * @param id
	 *            id - id of request in database
	 * @param request
	 *            request - all information about request that will write to database
	 */
	public void updateRequest(Integer id, Request request);

	/**
	 * Get request by id
	 * 
	 * @param id
	 *            id - id of request in database
	 * @return return information about of request
	 */
	public Request getRequestById(Integer id);

	/**
	 * Save request to database
	 * 
	 * @param request
	 *            request - all information about request that will save to database
	 */
	public void saveRequest(Request request);

}
