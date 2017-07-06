package com.reports.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reports.components.entity.ReportEntity;
import com.reports.components.interfaces.IReportEntity;
import com.reports.config.LetterSendConfiguration;

/**
 * Service that create and send report
 * 
 * @author cortes
 *
 */
@Service
public class ReportEntityService {

	private final static String[] titles;
	private final static String DOC_EXTENSION = "doc";
	private final static String DOC_FILE = "report.docx";
	private final static String XLS_FILE = "report.xls";
	private final static String MESSAGE_SUBJECT = "Report";
	private final static String BODY_TEXT = "This is message body";
	private final static String SHEET = "RequestsReport";
	private final static String TIME_FORMAT = "dd.mm.yyyy";

	static {
		titles = new String[20];
		titles[0] = "id";
		titles[1] = "user_name";
		titles[2] = "group_name";
		titles[3] = "country_name";
		titles[4] = "location_name";
		titles[5] = "language";
		titles[6] = "latitude";
		titles[7] = "longitude";
		titles[8] = "url";
		titles[9] = "method";
		titles[10] = "params";
		titles[11] = "date_opened";
		titles[12] = "date_closed";
	}

	@Autowired
	private IReportEntity iReportEntity;

	private LetterSendConfiguration letterSendConfiguration;

	@Autowired
	public ReportEntityService(LetterSendConfiguration letterSendConfiguration) {
		this.letterSendConfiguration = letterSendConfiguration;
	}

	/**
	 * Send report on client's email
	 * 
	 * @param email
	 * @param format
	 * @param filter
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void getReportList(String email, String format, String filter) throws FileNotFoundException, IOException {
		sendReport(email, format, filter);
	}

	private void sendReport(String recipient, String format, String filter) throws FileNotFoundException, IOException {

		try {
			Transport.send(createMessage(format, filter, recipient));
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	private Message createMessage(String format, String filter, String recipient)
			throws AddressException, MessagingException, FileNotFoundException, IOException {

		Session session = Session.getInstance(letterSendConfiguration.propertiesReport(),
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(letterSendConfiguration.getUsername(),
								letterSendConfiguration.getPassword());
					}
				});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(letterSendConfiguration.getUsername()));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
		message.setSubject(MESSAGE_SUBJECT);

		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText(BODY_TEXT);

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		messageBodyPart = new MimeBodyPart();
		String filename = getFilePath(format, filter);
		DataSource source = new FileDataSource(filename);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(filename);
		multipart.addBodyPart(messageBodyPart);
		message.setContent(multipart);
		return message;
	}

	private String getFilePath(String format, String filter) throws FileNotFoundException, IOException {
		if (format.equals(DOC_EXTENSION)) {
			return createDocReport(filter);
		} else {
			return createXlsReport(filter);
		}
	}

	private String createDocReport(String filter) throws IOException {
		XWPFDocument document = new XWPFDocument();
		File file = new File(DOC_FILE);
		FileOutputStream out = new FileOutputStream(file);
		XWPFTable table = document.createTable();
		createTitlesColumnDocx(table);

		List<ReportEntity> results = iReportEntity.getReportList(filter);

		for (int i = 0; i < results.size(); i++) {
			writeDateToDocx(table, i, results);
		}

		document.write(out);
		out.close();
		return file.getAbsolutePath();
	}

	private String createXlsReport(String filter) throws FileNotFoundException, IOException {
		Workbook book = new HSSFWorkbook();
		Sheet sheet = book.createSheet(SHEET);
		List<ReportEntity> results = iReportEntity.getReportList(filter);

		createTitlesColumnXls(sheet);

		for (int i = 0; i < results.size(); i++) {
			writeDateToXls(book, sheet, i, results);
		}

		sheet.autoSizeColumn(1);
		File file = new File(XLS_FILE);
		book.write(new FileOutputStream(file));
		book.close();
		return file.getAbsolutePath();
	}

	private void createTitlesColumnDocx(XWPFTable table) {
		XWPFTableRow tableRowOne = table.getRow(0);
		tableRowOne.getCell(0).setText(titles[0]);
		for (int i = 1; i < titles.length; i++) {
			tableRowOne.addNewTableCell().setText(titles[i]);
		}
	}

	private void writeDateToDocx(XWPFTable table, int i, List<ReportEntity> results) {
		XWPFTableRow tableRowOne = table.createRow();
		tableRowOne.getCell(0).setText(results.get(i).getId().toString());
		tableRowOne.getCell(1).setText(results.get(i).getUserName());
		tableRowOne.getCell(2).setText(results.get(i).getGroupName());
		tableRowOne.getCell(3).setText(results.get(i).getCountryName());
		tableRowOne.getCell(4).setText(results.get(i).getLocationName());
		tableRowOne.getCell(5).setText(results.get(i).getLanguage());
		tableRowOne.getCell(6).setText(results.get(i).getLatitude().toString());
		tableRowOne.getCell(7).setText(results.get(i).getLongitude().toString());
		tableRowOne.getCell(8).setText(results.get(i).getUrl());
		tableRowOne.getCell(9).setText(results.get(i).getMethod());
		tableRowOne.getCell(10).setText(results.get(i).getParams());
		tableRowOne.getCell(11).setText(new Date(results.get(i).getDateOpened().getTime()).toString());
		tableRowOne.getCell(12).setText(new Date(results.get(i).getDateClosed().getTime()).toString());
	}

	private void createTitlesColumnXls(Sheet sheet) {
		Row titlesSheet = sheet.createRow(0);
		for (int i = 0; i < titles.length; i++) {
			Cell cell = titlesSheet.createCell(i);
			cell.setCellValue(titles[i]);
		}
	}

	private void writeDateToXls(Workbook book, Sheet sheet, int i, List<ReportEntity> results) {
		Row row = sheet.createRow(i + 1);

		Cell id = row.createCell(0);
		id.setCellValue(results.get(i).getId());

		Cell userName = row.createCell(1);
		userName.setCellValue(results.get(i).getUserName());

		Cell groupName = row.createCell(2);
		groupName.setCellValue(results.get(i).getGroupName());

		Cell countryName = row.createCell(3);
		countryName.setCellValue(results.get(i).getCountryName());

		Cell locationName = row.createCell(4);
		locationName.setCellValue(results.get(i).getLocationName());

		Cell language = row.createCell(5);
		language.setCellValue(results.get(i).getLanguage());

		Cell latitude = row.createCell(6);
		latitude.setCellValue(results.get(i).getLatitude().toString());

		Cell longtitude = row.createCell(7);
		longtitude.setCellValue(results.get(i).getLongitude().toString());

		Cell url = row.createCell(8);
		url.setCellValue(results.get(i).getUrl());

		Cell method = row.createCell(9);
		method.setCellValue(results.get(i).getMethod());

		Cell params = row.createCell(10);
		params.setCellValue(results.get(i).getParams());

		Cell open = row.createCell(11);
		DataFormat format = book.createDataFormat();
		CellStyle dateStyle = book.createCellStyle();
		dateStyle.setDataFormat(format.getFormat(TIME_FORMAT));
		open.setCellStyle(dateStyle);
		open.setCellValue(new Date(results.get(i).getDateOpened().getTime()));

		Cell close = row.createCell(12);
		close.setCellStyle(dateStyle);
		close.setCellValue(new Date(results.get(i).getDateClosed().getTime()));
	}
}
