package com.esd.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="roomAvailability")
public class RoomAvailability {
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	private int roomAvailabilityId;
//	private Date startDate;
//	private Date endDate;
//	private boolean available;
//	@ManyToOne
//	@JoinColumn(name = "roomId")
//	private Room room;
	
	public RoomAvailability() {
		super();
	}
		
//	public RoomAvailability(Date startDate, Date endDate, boolean available) {
//		super();
//		this.startDate = startDate;
//		this.endDate = endDate;
//		this.available = available;
//	}

//	public int getRoomAvailabilityId() {
//		return roomAvailabilityId;
//	}
//	public void setRoomAvailabilityId(int roomAvailabilityId) {
//		this.roomAvailabilityId = roomAvailabilityId;
//	}
//	public Date getStartDate() {
//		return startDate;
//	}
//	public void setStartDate(Date startDate) {
//		this.startDate = startDate;
//	}
//	public Date getEndDate() {
//		return endDate;
//	}
//	public void setEndDate(Date endDate) {
//		this.endDate = endDate;
//	}
//	public boolean isAvailable() {
//		return available;
//	}
//	public void setAvailable(boolean available) {
//		this.available = available;
//	}
//	public Room getRoom() {
//		return room;
//	}
//	public void setRoom(Room room) {
//		this.room = room;
//	}
}
