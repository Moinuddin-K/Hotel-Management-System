package com.esd.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.esd.exception.AdminException;
import com.esd.exception.UserException;
import com.esd.pojo.Admin;
import com.esd.pojo.Booking;
import com.esd.pojo.Employee;
import com.esd.pojo.Hotel;
import com.esd.pojo.Room;
import com.esd.pojo.User;

@Component
public class AdminDAO extends DAO{
	public Hotel addHotel(Hotel hotel) throws AdminException{
		try {
			begin();
			Hotel h = new Hotel(hotel.getHotelName(), hotel.getAddress(), hotel.getCity(), hotel.getState(),
					hotel.getZipCode(), hotel.getContactNo());
//			h.setAdmin(admin);
			getSession().save(h);
			commit();
			close();
			return h;
		} catch (HibernateException e) {
			// TODO: handle exception
			rollback();
			throw new AdminException("Couldn't add the hotel. The following exception occured "+e.getMessage());

		}
//		return hotel;
	}
	
	public Admin getAdminByEmail(String email) throws AdminException{
		try {
			begin();
			Admin admin;
			System.out.println(email);
			Query<User> q = getSession().createQuery("FROM User WHERE emailId = :inputEmailId", User.class);
			List<User> existingUser = q.setParameter("inputEmailId", email).getResultList();
			commit();
			close();
		    if (existingUser.size()==0) {
		    	System.out.println("COuldn't find admin ID");
		    	return null;
		    }
		    begin();
		    User u = existingUser.get(0);
		    System.out.println("Id="+u.getUserId());
		    Query<Admin> a = getSession().createQuery("FROM Admin WHERE userId = :inputUserId", Admin.class);
			List<Admin> listAdmin  = a.setParameter("inputUserId", u.getUserId()).getResultList();
			commit();
			close();
		    if (listAdmin.size()==0) {
		    	System.out.println("Couldn't find admin ID");
		    	return null;
		    }
		    System.out.println("Admin Id="+listAdmin.get(0).getAdminId());
		    return listAdmin.get(0);
		}catch(HibernateException e) {
			rollback();
			throw new AdminException("Couldn't add the hotel. The following exception occured "+e.getMessage());

		}
	}
	
	public Admin getAdminById(int id) throws AdminException{ 
		try {
			begin();
			System.out.println("Id="+id);
		    Query<Admin> a = getSession().createQuery("FROM Admin WHERE userId = :inputUserId", Admin.class);
			List<Admin> listAdmin  = a.setParameter("inputUserId", id).getResultList();
			commit();
			close();
		    if (listAdmin.size()==0) {
		    	System.out.println("Couldn't find admin ID");
		    	return null;
		    }
		    System.out.println("Admin Id="+listAdmin.get(0).getAdminId());
		    return listAdmin.get(0);
		}catch(HibernateException e) {
			rollback();
			throw new AdminException("Couldn't add the hotel. The following exception occured "+e.getMessage());
		}
	}
	
	public List<Hotel> getHotels() throws AdminException{
		try {
			begin();
			List<Hotel> listHotels;
//			System.out.println("Admin Id="+adminId);
			Query q = getSession().createQuery("FROM Hotel");
//			q.setParameter("inputAdminId", adminId);
			listHotels = q.getResultList();
			commit();
			close();
			return listHotels;
		}catch(HibernateException e) {
			rollback();
			throw new AdminException("Couldn't get the hotel list. The following exception occured "+e.getMessage());
		}
	}
	
	public int getAdminId (int userId) throws AdminException{
		try {
			begin();
			List<Admin> admin;
			Query q = getSession().createQuery("FROM Admin WHERE user.id=:inputUserId");
			q.setParameter("inputUserId", userId);
			admin=q.getResultList();
			commit();
			close();
			return admin.get(0).getAdminId();
		}catch(HibernateException e) {
			rollback();
			throw new AdminException("Couldn't get the adminID. The following exception occured "+e.getMessage());
		}
	}
	
	public String getHotelName(int hotelId) throws AdminException{
		try {
			begin();
			List<Hotel> hotel;
			Query q = getSession().createQuery("FROM Hotel WHERE hotelId=:inputHotelId");
			q.setParameter("inputHotelId", hotelId);
			hotel = q.getResultList();
			commit();
			close();
			return hotel.get(0).getHotelName();
		}catch(HibernateException e) {
			rollback();
			throw new AdminException("Couldn't get the Hotel name from Hotel id."
					+ " The following exception occured "+e.getMessage());
		}
	}
	
	public Hotel getHotelById(int hotelId) throws AdminException{
		try {
			begin();
			List<Hotel> hotel;
			Query q = getSession().createQuery("FROM Hotel WHERE hotelId=:inputHotelId");
			q.setParameter("inputHotelId", hotelId);
			hotel = q.getResultList();
			commit();
			close();
			return hotel.get(0);
		}catch(HibernateException e) {
			rollback();
			throw new AdminException("Couldn't get the Hotel from Hotel id."
					+ " The following exception occured "+e.getMessage());
		}
	}
	
	public Hotel updateRoomHotel(Room room, Hotel hotel) throws AdminException{
		try {
			begin();
			Set hotelRooms = hotel.getRoom();
			hotelRooms.add(room);
			hotel.setRoom(hotelRooms);
			System.out.println(hotelRooms.size());
			room.setHotel(hotel);
//			room.setAvailability("Available");
			getSession().update(hotel);
			getSession().save(room);
			commit();
			close();
			return hotel;
		}catch(HibernateException e) {
			rollback();
			throw new AdminException("Couldn't update the Hotel with the room."
					+ " The following exception occured "+e.getMessage());
		}
	}
	
	public void deleteHotel(Hotel hotel) throws AdminException{
		try {
			begin();
//			getSession().delete(hotel);
//			commit();
//			close();
//			Change 04/27
			Query q = getSession().createQuery("DELETE FROM Hotel WHERE hotelId=: inputHotelId");
			q.setParameter("inputHotelId", hotel.getHotelId());
//			delbooking = (Booking) q.uniqueResult();
//			getSession().delete(delbooking);
			q.executeUpdate();
			commit();
			close();
		}catch(HibernateException e) {
			rollback();
			throw new AdminException("Couldn't delete the Hotel."
					+ " The following exception occured "+e.getMessage());
		}
	}
	
	public Hotel updateHotel(Hotel hotel, Hotel oldhotel) throws AdminException{
		try {
			begin();
			Hotel newHotel = new Hotel();
//			Id will be same as the previous id
			newHotel.setHotelId(oldhotel.getHotelId());
//			Rest all fields will be new input reads
			newHotel.setHotelName(hotel.getHotelName());
			newHotel.setAddress(hotel.getAddress());
			newHotel.setCity(hotel.getCity());
			newHotel.setState(hotel.getState());
			newHotel.setZipCode(hotel.getZipCode());
			newHotel.setContactNo(hotel.getContactNo());
			newHotel.setEmployees(oldhotel.getEmployees());
//			newHotel.setAdmin(oldhotel.getAdmin());
//			Copy the rooms and bookings of previous object into new
			newHotel.setBooking(oldhotel.getBooking());
			newHotel.setRoom(oldhotel.getRoom());
			
			getSession().update(newHotel);
			commit();
			close();
			return newHotel;
		}catch(HibernateException e) {
			rollback();
			throw new AdminException("Couldn't update the Hotel."
					+ " The following exception occured "+e.getMessage());
		}
	}
	
	public List<User> getEmployeeList() throws AdminException{
		try {
			begin();
			List<User> listEmployee;
//			System.out.println("Admin Id="+adminId);
			Query q = getSession().createQuery("FROM User WHERE userRole =:employeeType");
			q.setParameter("employeeType", "employee");
			listEmployee = q.list();
			commit();
			close();
			return listEmployee;
		}catch(HibernateException e) {
			rollback();
			throw new AdminException("Couldn't get the hotel list. The following exception occured "+e.getMessage());
		}
	}
	
	public User getUserById(int userId) throws AdminException{
		try {
			begin();
			List<User> listUser;
//			System.out.println("Admin Id="+adminId);
			Query q = getSession().createQuery("FROM User WHERE userId =:inputUserID");
			q.setParameter("inputUserID", userId);
			listUser = q.list();
			commit();
			close();
			return listUser.get(0);
		}catch(HibernateException e) {
			rollback();
			throw new AdminException("Couldn't get the user. The following exception occured "+e.getMessage());
		}
	}
	
	public User updateEmployee(Hotel empHotel, User empUser) throws AdminException{
		try {
			begin();
			empUser.setAssignedHotel(empHotel);
			getSession().update(empUser);
			commit();
			close();
			return empUser;
		}catch(HibernateException e) {
			rollback();
			throw new AdminException("Couldn't update the user. The following exception occured "+e.getMessage());
		}
	}
	
	public List<Booking> getAllBookings() throws AdminException{
		try {
			begin();
			List<Booking> hotelBookings;
			Query q = getSession().createQuery("FROM Booking");
			hotelBookings = q.getResultList();
			commit();
			close();
			return hotelBookings;
		}catch(HibernateException e) {
			rollback();
			throw new AdminException("Exception while getting list of hotel bookings. The following exception occured"+e.getMessage());
		
		}
	}
	
	public void deleteBooking(int bookingId) throws AdminException {
		try {
			begin();
			Booking delbooking;
			Query q = getSession().createQuery("DELETE FROM Booking WHERE bookingId=: inputBookingId");
			q.setParameter("inputBookingId", bookingId);
			q.executeUpdate();
			commit();
			close();
		}catch(HibernateException e) {
			rollback();
			throw new AdminException("Exception while getting list of bookings. The following exception occured"+e.getMessage());
		
		}
	}
}
