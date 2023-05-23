package com.esd.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name="hotel")
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int hotelId;
	private String hotelName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="adminId")
	private Admin admin;

	private String address;
	
	private String city;
	
	private String state;
	
	private String zipCode;
	
	private long contactNo;
	
	@OneToMany(mappedBy="assignedHotel", fetch = FetchType.LAZY)
	private List<User> employees;

	//	change here for making delete work
//	Made fetchtype lazy from eager
	@OneToMany(mappedBy = "hotel", fetch =  FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Booking> booking = new HashSet<>();
	
	@OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Room> room = new HashSet<>();
	
	@OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Employee> employee = new HashSet<>();
	
	public Hotel() {
		super();
	}


	public Hotel(String hotelName, String address, String city, String state, String zipCode, long contactNo) {
		super();
		this.hotelName = hotelName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.contactNo = contactNo;
	}
	
	public List<User> getEmployees() {
		return employees;
	}


	public void setEmployees(List<User> employees) {
		this.employees = employees;
	}
	
	public Set<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(Set<Employee> employee) {
		this.employee = employee;
	}
	
	public Set<Booking> getBooking() {
		return booking;
	}

	public void setBooking(Set<Booking> booking) {
		this.booking = booking;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getContactNo() {
		return contactNo;
	}

	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Set<Room> getRoom() {
		return room;
	}

	public void setRoom(Set<Room> room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "Hotel [hotelName=" + hotelName + ", address=" + address + ", city=" + city + ", state=" + state
				+ ", zipCode=" + zipCode + ", contactNo=" + contactNo + "]";
	}

//	@Override
//	public String toString() {
//		return "Hotel [hotelId=" + hotelId + ", hotelName=" + hotelName + ", admin=" + admin + ", address=" + address
//				+ ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + ", contactNo=" + contactNo
//				+ ", booking=" + booking + ", room=" + room + "]";
//	}
	
	
}
