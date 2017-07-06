package com.reports.components.interfaces;

import java.util.List;

import com.reports.components.entity.NetworkSession;

public interface ISession {

	/**
	 * Method that return all sessions in database
	 * 
	 * @return return - list of sessions
	 */
	public List<NetworkSession> getSessions();

	/**
	 * Delete session by it's id
	 * 
	 * @param id
	 *            id - id of session in database
	 */
	public void deleteSession(Integer id);

	/**
	 * Update session's information
	 * 
	 * @param id
	 *            id - id of session in database
	 * @param networkSession
	 *            networkSession - all information about session that will write to
	 *            database
	 */
	public void updateSession(Integer id, NetworkSession session);

	/**
	 * Get session by id
	 * 
	 * @param id
	 *            id - id of session in database
	 * @return return information about of session
	 */
	public NetworkSession getSesionById(Integer id);

	/**
	 * Save session to database
	 * 
	 * @param networkSession
	 *            networkSession - all information about session that will save to
	 *            database
	 */
	public void saveSession(NetworkSession session);

}
