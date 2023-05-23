package com.esd.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.esd.dao.UserDAO;
import com.esd.exception.UserException;
import com.esd.pojo.Booking;
import com.esd.pojo.Hotel;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

//import com.esd.pojo.User;

@Controller
public class DashboardController {
	@GetMapping("/user/user-dashboard.htm")
	public ModelAndView handleUserDashboardGet(ModelAndView mav, UserDAO userDao, HttpServletRequest request) throws UserException {
		System.out.println("Check user dashboard in controller");
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype == null || logintype=="admin" || logintype=="employee") {
			mav.setViewName("redirect:/login.htm");
			return mav;
		}
		int userId = (int) session.getAttribute("userid");
		List<Hotel> hotels = userDao.getAllHotels();
		List<Booking> bookings = userDao.getAllUserBookings(userId);
		mav.addObject("hotels", hotels);
		mav.addObject("bookings",bookings);
		mav.setViewName("user-dashboard");
		return mav;
	}
	
//	@PostMapping("/user/user-dashboard.htm")
//	public ModelAndView handleUserDashboardPost(ModelAndView mav, UserDAO userDao) throws UserException {
//		System.out.println("Check user dashboard in controller");
//		
//		List<Hotel> hotels = userDao.getAllHotels();
//		mav.addObject("hotels", hotels);
//		mav.setViewName("user-dashboard");
//		return mav;
//	}
	
	@GetMapping("/admin/admin-dashboard.htm")
	public ModelAndView handleAdminDashboardGet(ModelAndView mav, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype==null || logintype=="user" || logintype=="employee") {
			mav.setViewName("redirect:/login.htm");
			return mav;
		}
		mav.setViewName("admin-dashboard");
		return mav;
	}
	
	@GetMapping("/admin/")
	public String handleAdminGet(ModelAndView mav, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype==null || logintype=="user" || logintype=="employee") {
			return "redirect:/login.htm";
		}
		return "redirect:/admin/admin-dashboard.htm";
	}
	
	@GetMapping("/user/")
	public String handleUserGet(ModelAndView mav, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype==null || logintype=="admin" || logintype=="employee") {
			return "redirect:/login.htm";
			
		}
		return "redirect:/user/user-dashboard.htm";
	}
	
	@GetMapping("/employee/employee-dashboard.htm")
	public ModelAndView handleEmployeeDashboardGet(ModelAndView mav,UserDAO userDao, HttpServletRequest request) throws UserException {
		
		List<Booking> bookings; 
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype==null || logintype=="user" || logintype=="admin") {
			mav.setViewName("redirect:/login.htm");
			return mav;
		}
		int userEmpId = (int) session.getAttribute("employeeid");
		Hotel currentEmpHotel = userDao.getHotelOfEmployee(userEmpId);
		if (currentEmpHotel!=null) {
			bookings = userDao.getAllHotelBookings(currentEmpHotel.getHotelId());
			mav.addObject("empHotel",currentEmpHotel);
			mav.addObject("bookingsEmp",bookings);
		}
		mav.setViewName("employee-dashboard");
		return mav;
	}
}
