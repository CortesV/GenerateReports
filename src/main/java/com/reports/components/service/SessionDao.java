package com.reports.components.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.reports.components.entity.NetworkSession;
import com.reports.components.interfaces.ISession;

/**
 * CRUD for entity Session
 * 
 * @author cortes
 *
 */
@Repository
public class SessionDao implements ISession {

	private static final String SQL_GET_SESSIONS = "FROM NetworkSession session";
	private static final String SQL_GET_SESSION_BY_ID = "FROM NetworkSession session WHERE id = :id";
	private static final String SQL_DELETE_SESSION_BY_ID = "DELETE FROM NetworkSession WHERE id IN(:id)";
	private static final String SQL_UPDATE_SESSION_BY_ID = "UPDATE NetworkSession session SET date_opened = :dateOpened, date_closed = :dateClosed WHERE id=:id";

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * Method that return all sessions in database
	 * 
	 * @return return - list of sessions
	 */
	@Transactional
	@Override
	public List<NetworkSession> getSessions() {
		return sessionFactory.openSession().createQuery(SQL_GET_SESSIONS).list();
	}

	/**
	 * Delete session by it's id
	 * 
	 * @param id
	 *            id - id of session in database
	 */
	@Transactional
	@Override
	public void deleteSession(Integer id) {
		sessionFactory.openSession().createQuery(SQL_DELETE_SESSION_BY_ID).setParameter("id", id).executeUpdate();
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
	@Transactional
	@Override
	public void updateSession(Integer id, NetworkSession networkSession) {
		sessionFactory.openSession().createQuery(SQL_UPDATE_SESSION_BY_ID)
				.setParameter("dateOpened", networkSession.getDateOpened())
				.setParameter("dateClosed", networkSession.getDateClosed()).executeUpdate();
	}

	/**
	 * Get session by id
	 * 
	 * @param id
	 *            id - id of session in database
	 * @return return information about of session
	 */
	@Transactional
	@Override
	public NetworkSession getSesionById(Integer id) {
		return (NetworkSession) sessionFactory.openSession().createQuery(SQL_GET_SESSION_BY_ID).setParameter("id", id)
				.uniqueResult();
	}

	/**
	 * Save session to database
	 * 
	 * @param networkSession
	 *            networkSession - all information about session that will save
	 *            to database
	 */
	@Transactional
	@Override
	public void saveSession(NetworkSession networkSession) {
		sessionFactory.openSession().save(networkSession);
	}

}
