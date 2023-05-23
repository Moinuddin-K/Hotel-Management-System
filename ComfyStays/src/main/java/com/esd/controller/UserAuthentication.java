package com.esd.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.esd.dao.UserDAO;
import com.esd.exception.UserException;
import com.esd.pojo.Hotel;
import com.esd.pojo.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserAuthentication {
	@GetMapping("/login.htm")
	public ModelAndView handleLoginGet(ModelAndView mav) {
		mav.addObject("user", new User());
		mav.setViewName("login-view");
		return mav;
	}

	@PostMapping("/login.htm")
	public ModelAndView handleLoginPost(@ModelAttribute("user") User users, UserDAO userDao, HttpServletRequest request,
			RedirectAttributes redirectAttributes, ModelAndView mav) throws UserException {
		users = userDao.checkCredentials(users.getEmailId(), users.getPassword());
		System.out.println(users);
		HttpSession session = request.getSession();
		
		if (users != null) {
//			session.setAttribute("id", users.getUserId());
//			session.setAttribute("email", users.getEmailId());
//			session.setAttribute("name", users.getFirstName() + " " + users.getLastName());
			session.setAttribute("type", users.getUserRole());

			if (users.getUserRole().equalsIgnoreCase("user")) {
				System.out.println("Check user dashboard");
				session.setAttribute("userid", users.getUserId());
				session.setAttribute("useremail", users.getEmailId());
				session.setAttribute("username", users.getFirstName() + " " + users.getLastName());
				session.setAttribute("usertype", users.getUserRole());
				mav.setViewName("redirect:/user/user-dashboard.htm");
//				mav.setViewName("user-dashboard");
				return mav;
			} else if (users.getUserRole().equalsIgnoreCase("admin")) {
				session.setAttribute("adminid", users.getUserId());
				session.setAttribute("adminemail", users.getEmailId());
				session.setAttribute("adminname", users.getFirstName() + " " + users.getLastName());
				session.setAttribute("admintype", users.getUserRole());
				System.out.println("Check admin dashboard");
				redirectAttributes.addFlashAttribute("successMsg", "You have successfully logged in!");
				mav.setViewName("redirect:/admin/admin-dashboard.htm");
				return mav;
//				return "redirect:/admin/admin-dashboard.htm";
			} else if (users.getUserRole().equalsIgnoreCase("employee")) {
				System.out.println("Check Employee dashboard");
				session.setAttribute("employeeid", users.getUserId());
				session.setAttribute("employeeemail", users.getEmailId());
				session.setAttribute("employeename", users.getFirstName() + " " + users.getLastName());
				session.setAttribute("employeetype", users.getUserRole());
//				redirectAttributes.addFlashAttribute("successMsg", "You have successfully logged in!");
//				return "redirect:/employee/employee-dashboard.htm";
				mav.setViewName("redirect:/employee/employee-dashboard.htm");
				return mav;
			} else {
				System.out.println("Check login dashboard");
//				redirectAttributes.addFlashAttribute("successMsg", "You have successfully logged in!");
//				return "redirect:/login-view.htm";
				mav.setViewName("login-view");
				return mav;
			}
		} else {
			request.setAttribute("error", "Invalid Credentials");
			mav.setViewName("login-view");
			return mav;
		}
	}

	@GetMapping("/signup.htm")
	public ModelAndView handleSignupGet(HttpServletRequest request) {
		String type = request.getParameter("type");
		request.setAttribute("type", type);
		return new ModelAndView("signup-view");
	}

	@PostMapping("/signup.htm")
	public ModelAndView handleSignupPost(@ModelAttribute("user") User users, UserDAO userDao,
			HttpServletRequest request) throws UserException {
		System.out.println("Start");
//		Check if the user already exists
		String type = request.getParameter("type");
		User checkExisting = userDao.findUserByEmail(users.getEmailId());
		if (checkExisting != null) {
			request.setAttribute("error", "Email Id already exists. Please login with the ID or use another email");
			return new ModelAndView("signup-view");
		}
		users = userDao.signup(users, type);
		System.out.println(users.getFirstName());
		System.out.println("Signup successful");
//		if (type.equalsIgnoreCase("employee")){
//			return new ModelAndView("admin-dashboard");
//		}
		return new ModelAndView("login-view");
	}
	
	@GetMapping("/admin/signup.htm")
	public ModelAndView handleSignupAdminGet(HttpServletRequest request,ModelAndView mav) {
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype==null || logintype=="user" || logintype=="employee") {
			mav.setViewName("redirect:/login.htm");
			return mav;
		}
		String type = request.getParameter("type");
		request.setAttribute("type", type);
		return new ModelAndView("signup-view");
	}
	
	@PostMapping("/admin/signup.htm")
	public ModelAndView handleSignupAdminPost(@ModelAttribute("user") User users,ModelAndView mav, UserDAO userDao,
			HttpServletRequest request) throws UserException {
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype==null || logintype=="user" || logintype=="employee") {
			mav.setViewName("redirect:/login.htm");
			return mav;
		}
		System.out.println("Start");
//		Check if the user already exists
		String type = request.getParameter("type");
		User checkExisting = userDao.findUserByEmail(users.getEmailId());
		if (checkExisting != null) {
			request.setAttribute("error", "Email Id already exists. Please login with the ID or use another email");
			return new ModelAndView("signup-view");
		}
		users = userDao.signup(users, type);
		System.out.println(users.getFirstName());
		System.out.println("Signup successful");
		if (type.equalsIgnoreCase("employee")){
			return new ModelAndView("admin-dashboard");
		}
		return new ModelAndView("login-view");
	}
	
	@GetMapping("/employee/signup.htm")
	public ModelAndView handleSignupEmployeeGet(HttpServletRequest request, ModelAndView mav) {
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype==null || logintype=="user" || logintype=="admin") {
			mav.setViewName("redirect:/login.htm");
			return mav;
		}
		String type = request.getParameter("type");
		request.setAttribute("type", type);
		return new ModelAndView("signup-view");
	}
	
	@PostMapping("/employee/signup.htm")
	public ModelAndView handleSignupEmployeePost(@ModelAttribute("user") User users, UserDAO userDao,
			HttpServletRequest request, ModelAndView mav) throws UserException {
		HttpSession session = request.getSession();
		String logintype = (String) session.getAttribute("type");
		if (logintype==null || logintype=="user" || logintype=="admin") {
			mav.setViewName("redirect:/login.htm");
			return mav;
		}
		System.out.println("Start");
//		Check if the user already exists
		String type = request.getParameter("type");
		User checkExisting = userDao.findUserByEmail(users.getEmailId());
		if (checkExisting != null) {
			request.setAttribute("error", "Email Id already exists. Please login with the ID or use another email");
			return new ModelAndView("signup-view");
		}
		users = userDao.signup(users, type);
		System.out.println(users.getFirstName());
		System.out.println("Signup successful");
//		if (type.equalsIgnoreCase("user")){
		return new ModelAndView("redirect:/employee/employee-dashboard.htm");
//		}
//		return new ModelAndView("login-view");
	}
	
	@GetMapping("/logout")
	public ModelAndView handlelogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
//		request.
		return new ModelAndView("index");
	}
}
