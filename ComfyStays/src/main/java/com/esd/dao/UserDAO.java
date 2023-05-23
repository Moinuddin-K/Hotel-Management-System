package com.esd.dao;

import com.esd.pojo.User;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.esd.exception.UserException;
import com.esd.pojo.Admin;
import com.esd.pojo.Employee;
import com.esd.pojo.Hotel;
import com.esd.pojo.Room;
import com.esd.pojo.Booking;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class UserDAO extends DAO{
	
	public UserDAO() {
		super();
	}
	
	public User signup(User u, String type) throws UserException {
		try {
			begin();
			User user = new User(u.getFirstName(),u.getLastName(),u.getEmailId(),u.getPassword());
			user.setUserRole(type);
			getSession().save(user);
			if (type.equalsIgnoreCase("admin")) {
				Admin admin = new Admin(user);
				getSession().save(admin);
			}
			else if (type.equalsIgnoreCase("employee")) {
				Employee employee = new Employee(user);
				getSession().save(employee);
			}
			commit();
			close();
			return u;
	}catch (HibernateException e) {
		// TODO: handle exception
		rollback();
		throw new UserException("Couldn't Signup the user. The following exception occured"+e.getMessage());
		
	}
	}
	
	public User findUserByEmail(String emailId) throws UserException{
		try {
			begin();
			Query<User> q = getSession().createQuery("FROM User WHERE emailId = :inputEmailId", User.class);
		    List<User> existingUser = q.setParameter("inputEmailId", emailId).getResultList();
		    commit();
		    close();
		    if (existingUser.size()==0) {
		    	return null;
		    }
		    return existingUser.get(0);
		}catch(HibernateException e) {
			rollback();
			throw new UserException("Exception while signing up(Email already exists). The following exception occured"+e.getMessage());
		}
	}
	
	public User checkCredentials(String email,String password) throws UserException {
		try {
			begin();
			List<User> currentUser;
			Query<User> q = getSession().createQuery("FROM User WHERE emailId = :currentEmail AND password =:currentPassword",User.class);
			q.setParameter("currentEmail", email);
			q.setParameter("currentPassword", password);
			currentUser = q.getResultList();
			commit();
			close();
			return ((currentUser.size() == 0)?null:currentUser.get(0));
		}catch(HibernateException e) {
			rollback();
			throw new UserException("Exception while logging in. The following exception occured"+e.getMessage());
		}
		
	}
	
	public List<Hotel> getAllHotels() throws UserException{
		try {
			begin();
			List<Hotel> hotel = new ArrayList<Hotel>();
			Query q = getSession().createQuery("FROM Hotel");
			hotel.addAll(q.getResultList());
			commit();
			close();
			return hotel;
		}catch(HibernateException e) {
			rollback();
			throw new UserException("Exception while getting list of hotels. The following exception occured"+e.getMessage());
		}
	}

	public List<Booking> getAllBookings() throws UserException {
		// TODO Auto-generated method stub
		try {
			begin();
			List<Booking> currentBooking ;
			Query q = getSession().createQuery("FROM Booking WHERE availability=:booked");
			q.setParameter("booked", "booked");
			currentBooking = q.getResultList();
			commit();
			close();
			return currentBooking;
		}catch(HibernateException e) {
			rollback();
			throw new UserException("Exception while getting list of bookings. The following exception occured"+e.getMessage());
		}	
	}
	
	public List<Room> getAllRooms() throws UserException{
		try {
			begin();
			List<Room> allRooms;
			Query q = getSession().createQuery("FROM Room");
			allRooms = q.getResultList();
			commit();
			close();
			return allRooms;
		}catch(HibernateException e) {
			rollback();
			throw new UserException("Exception while getting list of rooms. The following exception occured"+e.getMessage());
		}
	}
	
	public User getUserFromUserId(int userId) throws UserException{
		try {
			begin();
			List<User> users;
			Query q = getSession().createQuery("FROM User WHERE userId=: inputUserId");
			q.setParameter("inputUserId", userId);
			users = q.getResultList();
			commit();
			close();
			return users.get(0);
		}catch(HibernateException e) {
			rollback();
			throw new UserException("Exception while getting list of users. The following exception occured"+e.getMessage());
		
		}
	}
	
	
	public Room getRoom(int roomId) throws UserException{
		try {
			begin();
			List<Room> rooms;
			Query q = getSession().createQuery("FROM Room WHERE roomId=: inputRoomId");
			q.setParameter("inputRoomId", roomId);
			rooms = q.getResultList();
			commit();
			close();
			return rooms.get(0);
		}catch(HibernateException e) {
			rollback();
			throw new UserException("Exception while getting list of users. The following exception occured"+e.getMessage());
		
		}
	}
	
	public Booking bookRoom(Room room,User currentUser,String checkInDate,String checkOutDate,
			int noOfDays) throws UserException, ParseException{
		try {
			begin();
//			List<Room> rooms;
			Booking newBooking = new Booking();
			newBooking.setUser(currentUser);
			newBooking.setHotel(room.getHotel());
			newBooking.setRoom(room);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date checkIn = dateFormat.parse(checkInDate);
			Date checkOut = dateFormat.parse(checkOutDate);
			newBooking.setCheckInDate(checkIn);
			newBooking.setCheckOutDate(checkOut);
			double perNightPrice = room.getRoomPrice();
			newBooking.setPrice(noOfDays*perNightPrice);
			newBooking.setAvailability("booked");
			getSession().save(newBooking);
			commit();
			close();
			return newBooking;
		}catch(HibernateException e) {
			rollback();
			throw new UserException("Exception while getting list of users. The following exception occured"+e.getMessage());
		
		}
	}
	
	
	public List<Booking> getAllUserBookings(int userId) throws UserException {
		try {
			begin();
			List<Booking> booking;
			Query q = getSession().createQuery("FROM Booking WHERE userId=: inputUserId");
			q.setParameter("inputUserId", userId);
			booking = q.getResultList();
			commit();
			close();
			return booking;
		}catch(HibernateException e) {
			rollback();
			throw new UserException("Exception while getting list of bookings. The following exception occured"+e.getMessage());
		
		}
	}
	
	public void deleteBooking(int bookingId) throws UserException {
		try {
			begin();
			Booking delbooking;
			Query q = getSession().createQuery("DELETE FROM Booking WHERE bookingId=: inputBookingId");
			q.setParameter("inputBookingId", bookingId);
//			delbooking = (Booking) q.uniqueResult();
//			getSession().delete(delbooking);
			q.executeUpdate();
			commit();
			close();
//			return delbooking;
		}catch(HibernateException e) {
			rollback();
			throw new UserException("Exception while getting list of bookings. The following exception occured"+e.getMessage());
		
		}
	}
	
	public Hotel getHotelOfEmployee(int userEmpId) throws UserException{
		try {
			begin();
			Hotel empHotel;
			Query q = getSession().createQuery("FROM User WHERE userId=: inputUserId");
			q.setParameter("inputUserId", userEmpId);
			User empUser = (User) q.uniqueResult();
			empHotel = empUser.getAssignedHotel();
			commit();
			close();
			return empHotel;
		}catch(HibernateException e) {
			rollback();
			throw new UserException("Exception while getting list of bookings. The following exception occured"+e.getMessage());
		
		}
	}
	
	public List<Booking> getAllHotelBookings (int hotelId) throws UserException{
		try {
			begin();
			List<Booking> hotelBookings;
			Query q = getSession().createQuery("FROM Booking WHERE hotel.hotelId=: inputHotelId");
			q.setParameter("inputHotelId", hotelId);
			hotelBookings = q.getResultList();
			commit();
			close();
			return hotelBookings;
		}catch(HibernateException e) {
			rollback();
			throw new UserException("Exception while getting list of hotel bookings. The following exception occured"+e.getMessage());
		
		}
	}
	
	public List<User> getAllUsers()throws UserException{
		try {
			begin();
			List<User> usersList;
			Query q = getSession().createQuery("FROM User WHERE userRole =:userType");
			q.setParameter("userType", "user");
			usersList = q.getResultList();
			commit();
			close();
			return usersList;
		}catch(HibernateException e) {
			rollback();
			throw new UserException("Exception while getting list of users. The following exception occured"+e.getMessage());
		
		}
	}
}
