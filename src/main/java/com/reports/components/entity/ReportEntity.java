package com.reports.components.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

@Entity
@SqlResultSetMapping(name = "MessageToDisplay", entities = { @EntityResult(entityClass = ReportEntity.class, fields = {
		@FieldResult(name = "id", column = "id"), @FieldResult(name = "userName", column = "user_name"),
		@FieldResult(name = "groupName", column = "group_name"),
		@FieldResult(name = "countryName", column = "country_name"),
		@FieldResult(name = "locationName", column = "location_name"), @FieldResult(name = "language", column = "body"),
		@FieldResult(name = "latitude", column = "latitude"), @FieldResult(name = "longitude", column = "longitude"),
		@FieldResult(name = "url", column = "url"), @FieldResult(name = "method", column = "method"),
		@FieldResult(name = "params", column = "params"), @FieldResult(name = "dateOpened", column = "date_opened"),
		@FieldResult(name = "dateClosed", column = "date_closed") }) })
public class ReportEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String userName;

	private String groupName;

	private String countryName;

	private String locationName;

	private String language;

	private BigDecimal latitude;

	private BigDecimal longitude;

	private String url;

	private String method;

	private String params;

	private Date dateOpened;

	private Date dateClosed;

	public ReportEntity() {

	}

	public ReportEntity(Integer id, String userName, String groupName, String countryName, String locationName,
			String language, BigDecimal latitude, BigDecimal longitude, String url, String method, String params,
			Date dateOpened, Date dateClosed) {
		this.id = id;
		this.userName = userName;
		this.groupName = groupName;
		this.countryName = countryName;
		this.locationName = locationName;
		this.language = language;
		this.latitude = latitude;
		this.longitude = longitude;
		this.url = url;
		this.method = method;
		this.params = params;
		this.dateOpened = dateOpened;
		this.dateClosed = dateClosed;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Date getDateOpened() {
		return dateOpened;
	}

	public void setDateOpened(Date dateOpened) {
		this.dateOpened = dateOpened;
	}

	public Date getDateClosed() {
		return dateClosed;
	}

	public void setDateClosed(Date dateClosed) {
		this.dateClosed = dateClosed;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
