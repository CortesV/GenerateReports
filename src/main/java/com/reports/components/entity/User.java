package com.reports.components.entity;

import java.io.Serializable;
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
 * Simple Java object that represent user
 * 
 * @author cortes
 *
 */

@Entity
@Table(name = "user", catalog = "task")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "user_name")
	private String userName;

	@ManyToOne
	@JoinColumn(name = "location_id", insertable = false, updatable = false, nullable = false)
	@JsonBackReference
	private Location location;

	@ManyToOne
	@JoinColumn(name = "user_group_id")
	@JsonBackReference
	private UserGroup group;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<NetworkSession> sessions = new HashSet<>();

	public User() {

	}

	public User(String userName) {
		this.userName = userName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Set<NetworkSession> getSessions() {
		return sessions;
	}

	public void setSessions(Set<NetworkSession> sessions) {
		this.sessions = sessions;
	}

}
