package com.reports.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reports.service.ReportEntityService;

@RestController
@RequestMapping(value = "/rest/test/v1/report/")
public class ReportEntityController {

	@Autowired
	private ReportEntityService reportEntityService;

	/**
	 * Send report on client's email
	 * @param email
	 * @param format
	 * @param filter
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void getReport(@RequestParam("email") String email, @RequestParam("doc_format") String format, HttpServletRequest request) throws FileNotFoundException, IOException {
		reportEntityService.getReportList(email, format, request.getParameter("filter"));
	}

}
