package com.reports.components.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * Simple Java object that represent session
 * 
 * @author cortes
 *
 */

@Entity
@Table(name = "session", catalog = "task")
public class NetworkSession implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "date_opened")
	private Date dateOpened;

	@Column(name = "date_closed")
	private Date dateClosed;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "networkSession", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Request> requests = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;

	public NetworkSession() {

	}

	public NetworkSession(Date dateOpened, Date dateClosed) {
		this.dateOpened = dateOpened;
		this.dateClosed = dateClosed;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Set<Request> getRequests() {
		return requests;
	}

	public void setRequests(Set<Request> requests) {
		this.requests = requests;
	}

}
