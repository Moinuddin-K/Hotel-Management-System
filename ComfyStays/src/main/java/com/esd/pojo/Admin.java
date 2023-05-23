package com.esd.pojo;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.transaction.Transactional;

import java.util.Set;
import java.util.HashSet;


@Entity
@Table(name="admin")
public class Admin {
	@Id
	@Basic
	@Column(name = "adminid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;
	
	@OneToOne
	@JoinColumn(name="userId")
	private User user;
	
	@OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private Set<Hotel> hotels = new HashSet<>();

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Hotel> getHotels() {
		return hotels;
	}
	

	public Admin() {
		super();
	}

	public Admin(int adminId, User user, Set<Hotel> hotels) {
		super();
		this.adminId = adminId;
		this.user = user;
		this.hotels = hotels;
	}

	public Admin(User user) {
		super();
		this.user = user;
	}

	public void setHotels(Set<Hotel> hotels) {
		this.hotels = hotels;
	}
}
