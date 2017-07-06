package com.reports.components.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.reports.components.entity.ReportEntity;
import com.reports.components.interfaces.IReportEntity;

@Repository
public class ReportEntityDao implements IReportEntity {

	private static final String SQL_GET_FIRST_PART_REPORT = "SELECT u.id, u.user_name, ug.group_name, c.country_name, l.location_name, c.language, l.latitude, l.longitude, "
			+ "r.url, r.method, r.params, s.date_opened, s.date_closed FROM user AS u INNER JOIN user_group AS ug ON u.user_group_id = ug.id "
			+ "INNER JOIN session AS s ON s.user_id = u.id " + "INNER JOIN request AS r ON r.session_id = s.id "
			+ "INNER JOIN location AS l ON l.id = u.location_id " + "INNER JOIN country AS c ON c.id = l.country_id ";
	private static final String SQL_LIKE_FIRST = "WHERE s.date_opened LIKE'";
	private static final String SQL_LIKE_SECOND = "%'";
	private static final String SQL_GET_SECOND_PART_REPORT = " ORDER BY u.user_name, c.country_name ASC";

	@PersistenceContext
	private EntityManager em;

	/**
	 * Method that return all datas for report
	 * 
	 * @return return - list of ReportEntites
	 */
	@Transactional
	@Override
	public List<ReportEntity> getReportList(String filterRequest) {
		String filter = "";
		if (StringUtils.isNotBlank(filterRequest)) {
			filter = SQL_LIKE_FIRST + filterRequest + SQL_LIKE_SECOND;
		}
		String sqlLQuery = SQL_GET_FIRST_PART_REPORT + filter + SQL_GET_SECOND_PART_REPORT;
		Query query = em.createNativeQuery(sqlLQuery, ReportEntity.class);
		return (List<ReportEntity>) query.getResultList();
	}
}
