package com.reports.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reports.components.entity.Request;
import com.reports.components.interfaces.IRequest;

@Service
public class RequestService {

	@Autowired
	private IRequest iRequest;
	
	/**
	 * Method that return all requests in database
	 * 
	 * @return return - list of requests
	 */
	public List<Request> getRequests(){
		return iRequest.getRequests();
	}

	/**
	 * Delete request by it's id
	 * 
	 * @param id
	 *            id - id of request in database
	 */
	public void deleteRequest(Integer id){
		iRequest.deleteRequest(id);
	}

	/**
	 * Update request's information
	 * 
	 * @param id
	 *            id - id of request in database
	 * @param request
	 *            request - all information about request that will write to database
	 */
	public void updateRequest(Integer id, Request request){
		iRequest.updateRequest(id, request);
	}

	/**
	 * Get request by id
	 * 
	 * @param id
	 *            id - id of request in database
	 * @return return information about of request
	 */
	public Request getRequestById(Integer id){
		return iRequest.getRequestById(id);
	}

	/**
	 * Save request to database
	 * 
	 * @param request
	 *            request - all information about request that will save to database
	 */
	public void saveRequest(Request request){
		iRequest.saveRequest(request);
	}

}
