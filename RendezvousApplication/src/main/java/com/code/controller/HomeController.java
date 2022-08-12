package com.code.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.code.MailUtil;
import com.code.dao.UserRegistrationDao;
import com.code.model.User;

@Controller
public class HomeController {
	
	@Autowired
	private User user;
	
	@Autowired
	private UserRegistrationDao userRegistrationDao;
	
	@RequestMapping("/register")
	public String registerPage() {
		return "register";
	}
	
	@RequestMapping("/login")
	public String loginPage() {
		return "login";
	}
	

	@RequestMapping("/validate")
	public String validateUser(Model model, @RequestParam("email") String email,
			@RequestParam("password") String password) {		
		user = userRegistrationDao.findByEmail(email);
		if (user==null) {
			model.addAttribute("error","This email/password does not exist!");
			return "login";
		}
//		System.out.println(user.getEmail());
//		System.out.println(user.getFullName());
//		System.out.println(user.getPassword());
		boolean checkPassword = BCrypt.checkpw(password, user.getPassword());
//		if(! (email.equals(user.getEmail()) && password.equals(user.getPassword()))) {
//				model.addAttribute("error", "Invalid login credentials!");
//			return "login";
//		}
			if (!checkPassword) {
				model.addAttribute("error", "Email/Password does not match!");
				return "login";
			}
		return "index";
	}
	
//	@RequestMapping("/validateEmail")
//	public String validateEmail(Model model, @RequestParam("email") String email,
//			@RequestParam("password") String password) {
//		
//		user = userRegistrationDao.findEmail(email);
//		if (user==null) {
//			model.addAttribute("error","This email does not exist!");
//			return "login";)
//		}
//		System.out.println(user.getEmail());
//		System.out.println(user.getFullName());
//		System.out.println(user.getPassword());
//		
//		if(! (email.equals(user.getEmail()) && password.equals(user.getPassword()))) {
//				model.addAttribute("error", "Invalid login credentials!");
//			return "login";
//		}
//		return "index";
//	}
	
	@RequestMapping("/saveUser")
	public String saveUserRegistration(Model model, @RequestParam("email") String email, @RequestParam("fullname") String fullname,
			@RequestParam("password") String password,@RequestParam("confirmpassword") String confirmpassword) {

		user.setEmail(email);
		user.setFullName(fullname);
		String salt = BCrypt.gensalt(10);
		String hpassword = BCrypt.hashpw(password, salt);
		user.setPassword(hpassword);
		user.setConfirmpassword(confirmpassword);

		int result = userRegistrationDao.saveUserRegistration(user);
		if(result > 0) {
			System.out.println("Successfully saved the data!");
		}
		return "login";
	}
	
	@RequestMapping("/list")
	public String listPage(Model model) {
		List<User> listofUsers = userRegistrationDao.listofUsers();
		model.addAttribute("listofUsers", listofUsers);
		return "list";
	}
	
	@RequestMapping("/editUser")
	public String editUserPage(Model model, @RequestParam("userId") int userId) {
		user = userRegistrationDao.findByUserId(userId);
		model.addAttribute("user", user);
		return "edit";
	}
	
	@RequestMapping("/editUser2")
	public String editUserPage2(Model model, @RequestParam("userId") int userId, @PathParam("error") String error) {
		user = userRegistrationDao.findByUserId(userId);
		model.addAttribute("user", user);
		model.addAttribute("error", error);
//		model.addAttribute("error", "Email Already Exists!");
		return "edit";
	}
	
	@RequestMapping("/update")
	public String updateUserPage(Model model, @RequestParam("fullname") String name, @RequestParam("email") String email, @RequestParam("userId") int userId) {
			//check first for email address is it exists
			user = userRegistrationDao.findByEmail(email);
			if(user == null) {
				User user = new User();
				user.setUserId(userId);
				user.setFullName(name);
				user.setEmail(email);
				userRegistrationDao.updateUserRegistration(user);
				return "redirect:/list";
			}
			if(user.getUserId() != userId) {
				return "redirect:editUser2?userId=" + userId + "&error= Email Already Exists!";
//				return "redirect:editUser2?userId=" + userId;
//				model.addAttribute("error", "This email exists!");
//				return editUserPage(model, userId);
			}
			user.setFullName(name);
			user.setEmail(email);
			user.setUserId(userId);
			userRegistrationDao.updateUserRegistration(user);
			return "redirect:/list";
	}
	
	@RequestMapping("/deleteUser")
	public String deleteUserPage(@RequestParam("userId") int userId){
		userRegistrationDao.deleteUserRegistration(userId);
		return "redirect:/list";
	}
	
	@RequestMapping("/resetpassword")
	public String resetPassword() {
		return "resetpassword";
	}
	
	@RequestMapping("/resetpass")
	public String resetPasswordPage(Model model, @RequestParam("email") String email) {
//		model.addAttribute("email", email);
		MailUtil.sendMail(email);
		return "resetpword";
	}
	
	@RequestMapping("/resetpasswd/{email}")
	public String resetPasswd(Model model, @PathVariable("email") String email) {
		user = userRegistrationDao.findByEmail(email);
		model.addAttribute("email", email);
		return "resetpword";
	}
	
	@RequestMapping("/savepass")
	public String savePass(Model model, @RequestParam("email") String email, 
			@RequestParam("password") String password) {

		userRegistrationDao.savePassword(email,password);
		String salt = BCrypt.gensalt(10);
		String hpassword = BCrypt.hashpw(password, salt);
		user.setPassword(hpassword);
		return "redirect:/login";
	}
	
}
