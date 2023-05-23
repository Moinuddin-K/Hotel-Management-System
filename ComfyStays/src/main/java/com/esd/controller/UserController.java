package com.esd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.esd.dao.AdminDAO;
import com.esd.dao.UserDAO;
import com.esd.exception.AdminException;
import com.esd.exception.UserException;
import com.esd.pojo.Booking;
import com.esd.pojo.Room;
import com.esd.pojo.User;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class UserController {
//	@GetMapping("/user/book.htm")
//	public ModelAndView getUserBook(ModelAndView mav) {
//		mav.setViewName("user-dashboard");
//		return mav;
//	}
	
	@PostMapping("/user/book.htm")
	public ModelAndView postUserBook(ModelAndView mav, HttpServletRequest request, UserDAO userDao) throws UserException {
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype == null || logintype=="admin" || logintype=="employee") {
			mav.setViewName("redirect:/login.htm");
			return mav;
		}
		String checkInInput = request.getParameter("checkin");
		String checkOutInput = request.getParameter("checkout");
		String hotelId = request.getParameter("hotelId");
		List<Booking> currentBooking = userDao.getAllBookings();
		List<Room> roomsList = userDao.getAllRooms();
		List<Room> availableRoom = new ArrayList<>();
		List<Integer> bookedRoomId = new ArrayList<Integer>();
		Date checkIn = new Date();
		Date checkOut = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
        	checkIn = dateFormat.parse(checkInInput);
        	checkOut = dateFormat.parse(checkOutInput);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        checkOut.ge
        int days = (int) ((checkOut.getTime()-checkIn.getTime()) /(1000 * 60 * 60 * 24));
        System.out.println("no of days= "+days);
		for (Booking b:currentBooking) {
//			Make a list of booked room id's
//			Check the check in and check out date of the booking
//			System.out.println("Table"+b.getCheckInDate());
//			System.out.println("Input"+checkInInput);
			if (checkOut.before(b.getCheckInDate()) || checkIn.after(b.getCheckOutDate()) 
					|| b.getCheckOutDate().before(checkIn) || b.getCheckInDate().after(checkOut)) {
				System.out.println("Booking can be done");
				
			}
			else {
				System.out.println("Booking can't be done");
				bookedRoomId.add(b.getRoom().getRoomId());
			}
		}
//		Iterate over all the rooms and check if it exists in booked rooms. If it exists skip the room 
		for (Room room:roomsList) {
			if (!bookedRoomId.contains(room.getRoomId())) {
				availableRoom.add(room);
			}
		}
//		System.out.println("Rooms available"+availableRoom.size()+availableRoom.get(2).getRoomPrice());
		mav.addObject("rooms", availableRoom);
//		request.setAttribute("checkInDate",checkInInput);
//		request.setAttribute("checkOutDate",checkOutInput);
		mav.addObject("checkInDate",checkInInput);
		mav.addObject("checkOutDate",checkOutInput);
		mav.addObject("noOfDays",days);
		mav.setViewName("user-searchRooms");
		return mav;
	}
	
	@PostMapping("/user/bookRoom.htm")
	public ModelAndView postUserBookRoom(ModelAndView mav, HttpServletRequest request, UserDAO userDao) throws UserException, ParseException {
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype == null || logintype=="admin" || logintype=="employee") {
			mav.setViewName("redirect:/login.htm");
			return mav;
		}
		int roomId = Integer.parseInt(request.getParameter("roomId"));
		String checkInDate = request.getParameter("checkInDate");
		String checkOutDate = request.getParameter("checkOutDate");
		int noOfDays = Integer.parseInt(request.getParameter("noOfDays"));
		System.out.println("Room ID from room selection"+roomId);
//		System.out.println("check in"+request.getParameter("checkInDate"));
//		Get user
//		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("userid");
		User currentUser = userDao.getUserFromUserId(userId);
		Room room = userDao.getRoom(roomId);
		Booking newBooking = userDao.bookRoom(room,currentUser,checkInDate,checkOutDate,noOfDays);
//		String message = "Booking confirmed with booking id"+ newBooking.getBookingId() + "at" + newBooking.getHotel().getHotelName();
		mav.addObject("bookingHotelName",newBooking.getHotel().getHotelName());
		mav.addObject("bookingId",newBooking.getBookingId());
		mav.setViewName("addRoomSuccess");
		return mav;
	}
	
	@PostMapping("/user/cancel")
	public ModelAndView handleHotelTask(ModelAndView mav, HttpServletRequest request, UserDAO userDao)
			throws AdminException, UserException {
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype == null || logintype=="admin" || logintype=="employee") {
			mav.setViewName("redirect:/login.htm");
			return mav;
		}
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("cancelBooking")) {
			return UserController.cancelBooking(mav, request, userDao);
		}
		return null;
	}
	
	public static ModelAndView cancelBooking(ModelAndView mav, HttpServletRequest request, UserDAO userDao)
			throws UserException {
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype == null || logintype=="admin" || logintype=="employee") {
			mav.setViewName("redirect:/login.htm");
			return mav;
		}
		System.out.println("Add Room post");
//		System.out.println(request.getParameter("hotelId"));
		int bookingId = Integer.parseInt(request.getParameter("bookingId"));
		System.out.println(request.getParameter("bookingId"));
		userDao.deleteBooking(bookingId);
//		String message = "Your Booking with " +deletedBooking.getHotel().getHotelName()+" (Booking Id:"+
//				deletedBooking.getBookingId() + ") has been cancelled successfully";
//		request.setAttribute("deletedMessage", message);
//		request.setAttribute("deletedBookingId", deletedBooking.getBookingId());
//		mav.addObject("Room", new Room());
		mav.setViewName("redirect:/user/user-dashboard.htm");
		return mav;
	}
	
	@PostMapping("/employee/cancel")
	public ModelAndView handleHotelEmpTask(ModelAndView mav, HttpServletRequest request, UserDAO userDao)
			throws AdminException, UserException {
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype == null || logintype=="admin" || logintype=="user") {
			mav.setViewName("redirect:/login.htm");
			return mav;
		}
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("cancelBooking")) {
			return UserController.cancelEmpBooking(mav, request, userDao);
		}
		return null;
	}
	
	public static ModelAndView cancelEmpBooking(ModelAndView mav, HttpServletRequest request, UserDAO userDao)
			throws UserException {
//		System.out.println(request.getParameter("hotelId"));
		int bookingId = Integer.parseInt(request.getParameter("bookingId"));
		System.out.println(request.getParameter("bookingId"));
		userDao.deleteBooking(bookingId);
		mav.setViewName("redirect:/employee/employee-dashboard.htm");
		return mav;
	}
	
	@PostMapping("/employee/addNewBooking")
	public ModelAndView addNewEmpBooking(ModelAndView mav,HttpServletRequest request, UserDAO userDao) throws UserException {
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype == null || logintype=="admin" || logintype=="user") {
			mav.setViewName("redirect:/login.htm");
			return mav;
		}
		List<User> listUsers = userDao.getAllUsers();
		int hotelId = Integer.parseInt(request.getParameter("empHotelBook"));
		mav.addObject("listUsers", listUsers);
		mav.addObject("hotelId",hotelId);
		mav.setViewName("Emp-new-booking");
		return mav;
	}
	
	
	@PostMapping("/employee/addNewBooking/Rooms")
	public ModelAndView addNewEmpBookingRooms(ModelAndView mav,HttpServletRequest request, UserDAO userDao) throws UserException {
//		List<User> listUsers = userDao.getAllUsers();
//		mav.addObject("listUsers", listUsers);
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype == null || logintype=="admin" || logintype=="user") {
			mav.setViewName("redirect:/login.htm");
			return mav;
		}
		System.out.println(request.getParameter("userId"));
		int userId = Integer.parseInt(request.getParameter("userId"));
		int hotelId = Integer.parseInt(request.getParameter("empHotelBook"));
		
		String checkInInput = request.getParameter("checkin");
		String checkOutInput = request.getParameter("checkout");
		List<Booking> currentBooking = userDao.getAllBookings();
		List<Room> roomsList = userDao.getAllRooms();
		List<Room> availableRoom = new ArrayList<>();
		List<Integer> bookedRoomId = new ArrayList<Integer>();
		Date checkIn = new Date();
		Date checkOut = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
        	checkIn = dateFormat.parse(checkInInput);
        	checkOut = dateFormat.parse(checkOutInput);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        checkOut.ge
        int days = (int) ((checkOut.getTime()-checkIn.getTime()) /(1000 * 60 * 60 * 24));
        System.out.println("no of days= "+days);
		for (Booking b:currentBooking) {
//			Make a list of booked room id's
//			Check the check in and check out date of the booking
//			System.out.println("Table"+b.getCheckInDate());
//			System.out.println("Input"+checkInInput);
			if (checkOut.before(b.getCheckInDate()) || checkIn.after(b.getCheckOutDate()) 
					|| b.getCheckOutDate().before(checkIn) || b.getCheckInDate().after(checkOut)){
//					) && b.getHotel().getHotelId() == hotelId) {
				System.out.println("Booking can be done");
				
			}
			else {
				System.out.println("Booking can't be done");
				bookedRoomId.add(b.getRoom().getRoomId());
			}
		}
//		Iterate over all the rooms and check if it exists in booked rooms. If it exists skip the room 
		for (Room room:roomsList) {
			if (!bookedRoomId.contains(room.getRoomId())) {
//				Add only the particular hotel to the available list
				if (room.getHotel().getHotelId()==hotelId) {
					availableRoom.add(room);
				}
			}
		}
//		System.out.println("Rooms available"+availableRoom.size()+availableRoom.get(2).getRoomPrice());
		mav.addObject("rooms", availableRoom);
//		request.setAttribute("checkInDate",checkInInput);
//		request.setAttribute("checkOutDate",checkOutInput);
		mav.addObject("checkInDate",checkInInput);
		mav.addObject("checkOutDate",checkOutInput);
		mav.addObject("noOfDays",days);
		mav.addObject("userEmpId",userId);
		mav.setViewName("employee-searchRooms");
		return mav;
	}
	
	@PostMapping("/employee/addNewBooking/Rooms/book.htm")
	public ModelAndView addNewEmpBookingRoomsBook(ModelAndView mav,HttpServletRequest request, UserDAO userDao) throws UserException, ParseException {
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype == null || logintype=="admin" || logintype=="employee") {
			mav.setViewName("redirect:/login.htm");
			return mav;
		}
		int roomId = Integer.parseInt(request.getParameter("roomId"));
		String checkInDate = request.getParameter("checkInDate");
		String checkOutDate = request.getParameter("checkOutDate");
		int noOfDays = Integer.parseInt(request.getParameter("noOfDays"));
		System.out.println("Room ID from room selection"+roomId);
//		System.out.println("check in"+request.getParameter("checkInDate"));
//		Get user
//		HttpSession session = request.getSession();
//		int userId = (int) session.getAttribute("userid");
		int userId = Integer.parseInt(request.getParameter("userEmpId"));
		User currentUser = userDao.getUserFromUserId(userId);
		Room room = userDao.getRoom(roomId);
		Booking newBooking = userDao.bookRoom(room,currentUser,checkInDate,checkOutDate,noOfDays);
//		String message = "Booking confirmed with booking id"+ newBooking.getBookingId() + "at" + newBooking.getHotel().getHotelName();
		mav.addObject("bookingHotelName",newBooking.getHotel().getHotelName());
		mav.addObject("bookingId",newBooking.getBookingId());
		mav.setViewName("addRoomBookEmp");
		return mav;
	}
}
