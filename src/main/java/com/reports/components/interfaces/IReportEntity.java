package com.reports.components.interfaces;

import java.util.List;

import com.reports.components.entity.ReportEntity;

public interface IReportEntity {

	/**
	 * Method that return all datas for report
	 * 
	 * @return return - list of ReportEntites
	 */
	public List<ReportEntity> getReportList(String filter);
}
