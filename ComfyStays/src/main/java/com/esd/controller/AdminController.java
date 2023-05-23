package com.esd.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.esd.dao.AdminDAO;
import com.esd.dao.UserDAO;
import com.esd.exception.AdminException;
import com.esd.exception.UserException;
import com.esd.pojo.Admin;
import com.esd.pojo.Booking;
import com.esd.pojo.Employee;
import com.esd.pojo.Hotel;
//import com.esd.pojo.User;
import com.esd.pojo.Room;
import com.esd.pojo.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	@GetMapping("/admin/manage-hotels")
	public ModelAndView handleAdminHotelsGet(ModelAndView mav, HttpServletRequest request, AdminDAO adminDao)
			throws AdminException {
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype==null || logintype=="user" || logintype=="employee") {
			mav.setViewName("redirect:/login.htm");
			return mav;
		}
		mav.addObject("user", new Hotel());
		List<Hotel> hotelList = adminDao.getHotels();
		mav.addObject("hotelList", hotelList);
		System.out.println("Array size" + hotelList.size());
		String message = request.getParameter("message");
		mav.addObject("message", message);
		mav.setViewName("manage-hotels");
		return mav;
	}
	
	@PostMapping("/admin/manage-hotels/addHotel")
	public ModelAndView handleAddHotelPost(@ModelAttribute("addHotel") Hotel hotel, AdminDAO adminDao,
			HttpServletRequest request, RedirectAttributes redirectAttributes, ModelAndView mav) throws AdminException {
		System.out.println(hotel.getAddress());
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype==null || logintype=="user" || logintype=="employee") {
			mav.setViewName("redirect:/login.htm");
			return mav;
		}
		int adminUserId = (int) session.getAttribute("adminid");
		System.out.println(adminUserId);
		Admin admin = adminDao.getAdminById(adminUserId);
		Hotel newHotel = adminDao.addHotel(hotel);
		System.out.println("Hotel added successfully");
		String message = "Hotel " + newHotel.getHotelName() + " added successfully!";
		request.setAttribute("message", message);
		System.out.println(message);
		mav.addObject("message", message);
		mav.setViewName("redirect:/admin/manage-hotels");
		return mav;
	}
	
	@PostMapping("/admin/manage-hotels/updateHotel")
	public ModelAndView handleUpdateHotelPost(@ModelAttribute("updateHotel") Hotel hotel, AdminDAO adminDao,
			HttpServletRequest request, RedirectAttributes redirectAttributes, ModelAndView mav) 
					throws AdminException{
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype==null || logintype=="user" || logintype=="employee") {
			mav.setViewName("redirect:/login.htm");
			return mav;
		}
		int hotelId = Integer.parseInt(request.getParameter("hotelId"));
		Hotel oldHotel = adminDao.getHotelById(hotelId);
		Hotel updatedHotel = adminDao.updateHotel(hotel,oldHotel);
		String message = "Updated Hotel " + updatedHotel.getHotelName();
		mav.addObject("message", message);
		mav.setViewName("redirect:/admin/manage-hotels");
		return mav;
	}
	

	@PostMapping("/admin/manage-hotels/task")
	public ModelAndView handleHotelTask(ModelAndView mav, HttpServletRequest request, AdminDAO adminDao)
			throws AdminException {
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype==null || logintype=="user" || logintype=="employee") {
			mav.setViewName("redirect:/login.htm");
			return mav;
		}
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("addRooms")) {
			return AdminController.addRoom(mav, request, adminDao);
		} else if (action.equalsIgnoreCase("deleteRooms")) {
			return AdminController.deleteHotel(mav, request, adminDao);
		} else if (action.equalsIgnoreCase("modifyRooms")) {
			return AdminController.updateHotel(mav, request, adminDao);
		} else {
			System.out.println("Couldn't find the action type");
			return null;
		}
	}

	public static ModelAndView addRoom(ModelAndView mav, HttpServletRequest request, AdminDAO adminDao)
			throws AdminException {
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype==null || logintype=="user" || logintype=="employee") {
			mav.setViewName("redirect:/login.htm");
			return mav;
		}
		System.out.println("Add Room post");
		System.out.println(request.getParameter("hotelId"));
		int hotelId = Integer.parseInt(request.getParameter("hotelId"));
		String hotelName = adminDao.getHotelName(hotelId);
		request.setAttribute("hotelName", hotelName);
		request.setAttribute("hotelId", hotelId);
		mav.addObject("Room", new Room());
		mav.setViewName("addHotelRoom");
		return mav;
	}

	public static ModelAndView updateHotel(ModelAndView mav, HttpServletRequest request, AdminDAO adminDao)
			throws AdminException {
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype==null || logintype=="user" || logintype=="employee") {
			mav.setViewName("redirect:/login.htm");
			return mav;
		}
		int hotelId = Integer.parseInt(request.getParameter("hotelId"));
		Hotel hotel = adminDao.getHotelById(hotelId);
		mav.addObject(hotel);
		mav.addObject("newHotel",new Hotel());
		mav.setViewName("updateHotel");
		return mav;
	}

	public static ModelAndView deleteHotel(ModelAndView mav, HttpServletRequest request, AdminDAO adminDao)
			throws AdminException {
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype==null || logintype=="user" || logintype=="employee") {
			mav.setViewName("redirect:/login.htm");
			return mav;
		}
		System.out.println(request.getParameter("hotelId"));
		int hotelId = Integer.parseInt(request.getParameter("hotelId"));
		Hotel hotel = adminDao.getHotelById(hotelId);
		adminDao.deleteHotel(hotel);
		String message = "Hotel " + hotel.getHotelName() + " deleted successfully";
		mav.addObject("message", message);
		request.setAttribute("message", message);
		mav.setViewName("redirect:/admin/manage-hotels");
		return mav;
	}

	@PostMapping("/admin/manage-hotels/addRoom/submit")
	public ModelAndView handleAddHotelRoomSubmissionPost(@ModelAttribute("Room") Room room, ModelAndView mav,
			HttpServletRequest request, AdminDAO adminDao) throws AdminException {
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype==null || logintype=="user" || logintype=="employee") {
			mav.setViewName("redirect:/login.htm");
			return mav;
		}
		int hotelId = Integer.parseInt(request.getParameter("hotelId"));
		Hotel hotel = adminDao.getHotelById(hotelId);
		Hotel updatedHotel = adminDao.updateRoomHotel(room, hotel);
		System.out.println("Room added successfully");
		String message = "Room added to " + updatedHotel.getHotelName() + " successfully";
		mav.addObject("message", message);
		mav.setViewName("redirect:/admin/manage-hotels");
		return mav;
	}

	@GetMapping("/admin/manage-employees")
	public ModelAndView handleAdminEmployeesGet(ModelAndView mav, HttpServletRequest request, AdminDAO adminDao)
			throws AdminException {
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype==null || logintype=="user" || logintype=="employee") {
			mav.setViewName("redirect:/login.htm");
			return mav;
		}
		List<User> employeeList = adminDao.getEmployeeList();
		System.out.println(employeeList.size());
		mav.addObject("eee", employeeList);
		mav.setViewName("manage-employees");
		return mav;
	}
	
	@PostMapping("/admin/manage-employees/assign")
	public ModelAndView handleAdminEmployeesAssign(ModelAndView mav, HttpServletRequest request, AdminDAO adminDao)
		throws AdminException{
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype==null || logintype=="user" || logintype=="employee") {
			mav.setViewName("redirect:/login.htm");
			return mav;
		}
		int userEmpId = Integer.parseInt(request.getParameter("assign"));
		System.out.println("userId="+userEmpId);
		List<Hotel> listHotels = adminDao.getHotels();
		mav.addObject("userEmpId",userEmpId);
		mav.addObject("listH",listHotels);
		mav.setViewName("assignEmployeeHotel");
		return mav;
	}	
	
	@PostMapping("/admin/manage-employees/assign/submit")
	public ModelAndView handleAdminEmployeesAssignSubmit(ModelAndView mav, HttpServletRequest request, AdminDAO adminDao)
		throws AdminException{
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype==null || logintype=="user" || logintype=="employee") {
			mav.setViewName("redirect:/login.htm");
			return mav;
		}
		int hotelId = Integer.parseInt(request.getParameter("hotelIdForm"));
		int userId = Integer.parseInt(request.getParameter("userEmpId"));
		System.out.println("hotelIdForm="+hotelId +"user id"+userId);
		Hotel selectedHotel = adminDao.getHotelById(hotelId);
		User currentUserEmployee = adminDao.getUserById(userId);
		User updatedUser = adminDao.updateEmployee(selectedHotel,currentUserEmployee);
//		List<Hotel> listHotels = adminDao.getHotels();
//		mav.addObject("listH",listHotels);
		mav.setViewName("redirect:/admin/manage-employees");
		return mav;
	}
	

	@GetMapping("/admin/manage-bookings")
	public ModelAndView handleAdminBookingsGet(ModelAndView mav, HttpServletRequest request, AdminDAO adminDao)
			throws AdminException {
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype==null || logintype=="user" || logintype=="employee") {
			mav.setViewName("redirect:/login.htm");
			return mav;
		}
		List<Booking> bookings = adminDao.getAllBookings();
		mav.addObject("bookingsAdm",bookings);
		mav.setViewName("manage-bookings");
		return mav;
	}
	
	@PostMapping("/admin/cancel")
	public ModelAndView handleHotelEmpTask(ModelAndView mav, HttpServletRequest request, AdminDAO adminDao)
			throws AdminException {
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype==null || logintype=="user" || logintype=="employee") {
			mav.setViewName("redirect:/login.htm");
			return mav;
		}
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("cancelBooking")) {
			return AdminController.cancelAdmBooking(mav, request, adminDao);
		}
		return null;
	}
	
	public static ModelAndView cancelAdmBooking(ModelAndView mav, HttpServletRequest request, AdminDAO adminDao)
			throws AdminException {
//		System.out.println(request.getParameter("hotelId"));
		int bookingId = Integer.parseInt(request.getParameter("bookingId"));
		System.out.println(request.getParameter("bookingId"));
		adminDao.deleteBooking(bookingId);
		mav.setViewName("redirect:/admin/manage-bookings");
		return mav;
	}
}
