package com.reports.components.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.reports.components.entity.Request;
import com.reports.components.interfaces.IRequest;

/**
 * CRUD for entity Request
 * 
 * @author cortes
 *
 */
@Repository
public class RequestDao implements IRequest {

	private static final String SQL_GET_REQUESTS = "FROM Request request";
	private static final String SQL_GET_REQUEST_BY_ID = "FROM Request request WHERE id = :id";
	private static final String SQL_DELETE_REQUEST_BY_ID = "DELETE FROM Request WHERE id IN(:id)";
	private static final String SQL_UPDATE_REQUEST_BY_ID = "UPDATE Request request SET url = :url, method = :method, params = :params WHERE id=:id";

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Method that return all requests in database
	 * 
	 * @return return - list of requests
	 */
	@Transactional
	@Override
	public List<Request> getRequests() {
		return sessionFactory.openSession().createQuery(SQL_GET_REQUESTS).list();
	}

	/**
	 * Delete request by it's id
	 * 
	 * @param id
	 *            id - id of request in database
	 */
	@Transactional
	@Override
	public void deleteRequest(Integer id) {
		sessionFactory.openSession().createQuery(SQL_DELETE_REQUEST_BY_ID).setParameter("id", id).executeUpdate();
	}

	/**
	 * Update request's information
	 * 
	 * @param id
	 *            id - id of request in database
	 * @param request
	 *            request - all information about request that will write to
	 *            database
	 */
	@Transactional
	@Override
	public void updateRequest(Integer id, Request request) {
		sessionFactory.openSession().createQuery(SQL_UPDATE_REQUEST_BY_ID).setParameter("url", request.getUrl())
				.setParameter("method", request.getMethod()).setParameter("params", request.getParams())
				.setParameter("id", id).executeUpdate();
	}

	/**
	 * Get request by id
	 * 
	 * @param id
	 *            id - id of request in database
	 * @return return information about of request
	 */
	@Transactional
	@Override
	public Request getRequestById(Integer id) {
		return (Request) sessionFactory.openSession().createQuery(SQL_GET_REQUEST_BY_ID).setParameter("id", id)
				.uniqueResult();
	}

	/**
	 * Save request to database
	 * 
	 * @param request
	 *            request - all information about request that will save to
	 *            database
	 */
	@Transactional
	@Override
	public void saveRequest(Request request) {
		sessionFactory.openSession().save(request);
	}

}
