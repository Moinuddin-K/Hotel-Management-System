package com.esd.pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
//import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "user")
public class User {
	@Id
	@Basic
	@Column(name = "userid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String firstName;
	private String lastName;
//	@Column(name = "Email")
	private String emailId;
	private String password;
	private String userRole;
	@ManyToOne
	@JoinColumn(name="assignedHotelId")
	private Hotel assignedHotel;
//	private Role userRole;

	public Integer getUserId() {
		return userId;
	}

	public Hotel getAssignedHotel() {
		return assignedHotel;
	}

	public void setAssignedHotel(Hotel assignedHotel) {
		this.assignedHotel = assignedHotel;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", userRole=" + userRole + "password=" + password + "]";
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User() {
		super();
	}
	
	public User( String emailId, String password) {
		super();
		this.emailId = emailId;
		this.password = password;
	}

	public User(String firstName, String lastName, String emailId, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;
	}

}
