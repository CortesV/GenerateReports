package com.reports.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reports.components.entity.NetworkSession;
import com.reports.service.SessionService;

@RestController
@RequestMapping(value = "/rest/test/v1/session/")
public class SessionController {

	@Autowired
	private SessionService sessionService;

	/**
	 * Method that return all sessions in database
	 * 
	 * @return return - list of sessions
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<NetworkSession> getSessions() {
		return sessionService.getSessions();
	}

	/**
	 * Delete session by it's id
	 * 
	 * @param id
	 *            id - id of session in database
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteSession(@PathVariable("id") Integer id) {
		sessionService.deleteSession(id);
	}

	/**
	 * Update session's information
	 * 
	 * @param id
	 *            id - id of session in database
	 * @param networkSession
	 *            networkSession - all information about session that will write
	 *            to database
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateSession(@PathVariable("id") Integer id, @RequestBody NetworkSession session) {
		sessionService.updateSession(id, session);
	}

	/**
	 * Get session by id
	 * 
	 * @param id
	 *            id - id of session in database
	 * @return return information about of session
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public NetworkSession getSesionById(@PathVariable("id") Integer id) {
		return sessionService.getSesionById(id);
	}

	/**
	 * Save session to database
	 * 
	 * @param networkSession
	 *            networkSession - all information about session that will save
	 *            to database
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void saveSession(@RequestBody NetworkSession session) {
		sessionService.saveSession(session);
	}

}
