package com.esd.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="userroles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userRoleId", unique = true)
	private Integer userRoleId;
	@OneToOne(fetch=FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name="userId")
	private User user;
	@Column(name = "role")    
	private String role;
	
	
	
	public Role() {
		super();
	}
	public Role(String role) {
		super();
		this.role = role;
	}
	public int getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	} 
//	@Override
//	public String toString() {
//		return "Role [ user=" + user + ", role=" + role + "]";
//	}
	
}
