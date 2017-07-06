package com.reports.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reports.components.entity.NetworkSession;
import com.reports.components.interfaces.ISession;

@Service
public class SessionService {

	@Autowired
	private ISession iSession;
	
	/**
	 * Method that return all sessions in database
	 * 
	 * @return return - list of sessions
	 */
	public List<NetworkSession> getSessions(){
		return iSession.getSessions();
	}

	/**
	 * Delete session by it's id
	 * 
	 * @param id
	 *            id - id of session in database
	 */
	public void deleteSession(Integer id){
		iSession.deleteSession(id);
	}

	/**
	 * Update session's information
	 * 
	 * @param id
	 *            id - id of session in database
	 * @param networkSession
	 *            networkSession - all information about session that will write to
	 *            database
	 */
	public void updateSession(Integer id, NetworkSession session){
		iSession.updateSession(id, session);
	}

	/**
	 * Get session by id
	 * 
	 * @param id
	 *            id - id of session in database
	 * @return return information about of session
	 */
	public NetworkSession getSesionById(Integer id){
		return iSession.getSesionById(id);
	}

	/**
	 * Save session to database
	 * 
	 * @param networkSession
	 *            networkSession - all information about session that will save to
	 *            database
	 */
	public void saveSession(NetworkSession session){
		iSession.saveSession(session);
	}

}
