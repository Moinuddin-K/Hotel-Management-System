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

@Entity
@Table(name = "rooms")
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roomId;
	private String roomType;
	private int numberOfGuests;
	private double roomPrice;
//	@OneToMany(mappedBy = "rooms", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
//	private RoomAvailability availability;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hotelId")
	private Hotel hotel;

	public Room() {
		super();
	}

	public Room(String roomType, int numberOfGuests, double roomPrice) {
		super();
		this.roomType = roomType;
		this.numberOfGuests = numberOfGuests;
		this.roomPrice = roomPrice;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public double getRoomPrice() {
		return roomPrice;
	}

	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public int getNumberOfGuests() {
		return numberOfGuests;
	}

	public void setNumberOfGuests(int numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}

//	public RoomAvailability getAvailability() {
//		return availability;
//	}
//
//	public void setAvailability(RoomAvailability availability) {
//		this.availability = availability;
//	}

}
