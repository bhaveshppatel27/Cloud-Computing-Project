package com.neu.edu.wtp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.hibernate.HibernateException;
import org.joda.time.DateTime;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neu.edu.wtp.Validators.UserValidator;
import com.neu.edu.wtp.dao.ChequeDepositRequestDAO;
import com.neu.edu.wtp.dao.CustomerDAO;
import com.neu.edu.wtp.dao.UserDAO;
import com.neu.edu.wtp.pojo.BankAccount;
import com.neu.edu.wtp.pojo.ChequeDepositRequest;
import com.neu.edu.wtp.pojo.Customer;
import com.neu.edu.wtp.pojo.Person;
import com.neu.edu.wtp.pojo.User;
import com.neu.edu.wtp.pojo.UserType;
import com.neu.edu.wtp.utility.PasswordEncryption;
import com.neu.edu.wtp.utility.PasswordStrengthRegEx;
//import com.neu.edu.wtp.utility.SessionCount;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	


	@Autowired
	@Qualifier("userDAO")
	UserDAO userDAO;
		
	@Autowired
	@Qualifier("customerDAO")
	CustomerDAO customerDAO;

	@Autowired
	@Qualifier("user")
	User user;

	@Autowired
	@Qualifier("chequeDepositRequestDAO")
	ChequeDepositRequestDAO chequeDepositRequestDAO;

	@Autowired
	@Qualifier("passwordEncryption")
	PasswordEncryption passwordEncryption;

	@Autowired
	@Qualifier("userValidator")
	UserValidator userValidator;
	HttpSession session;
//	 private static final Logger logger =
//	 LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
	
		model.addAttribute("user", user);
		//

		return "home";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String homePost(Locale locale, Model model) {
		home(locale, model);

		return "home";
	}

	@RequestMapping(value = "/signIn.htm", method = RequestMethod.POST)
	public String signIn(Locale locale, @ModelAttribute("user") User user, HttpServletRequest req,
			HttpServletResponse res, BindingResult result) {
		
		
		String uname = user.getUserName();
		boolean isRememberMeChecked = false;
		try {
			String rememberMe = req.getParameter("rememberMe");
			if (rememberMe!=null) {//.equals("rememberMe")
				isRememberMeChecked = true;
			}

		} catch (NullPointerException ne) {
			ne.printStackTrace();

		}
		if (isRememberMeChecked) {
			Cookie c1 = new Cookie("userNameCookie", user.getUserName());
			Cookie c2 = new Cookie("passwordCookie", user.getPassword());
			c1.setMaxAge(24 * 60 * 60);
			c2.setMaxAge(24 * 60 * 60);
			res.addCookie(c1);
			res.addCookie(c2);
		}

		
			
			User u = userDAO.get(uname, user.getPassword());
			userValidator.validate(u, result);
			if (result.hasErrors()) {
				return "home";
			}
			System.out.println(uname+" "+new Date(System.currentTimeMillis()));
			if (u.getUserType().equals(UserType.Customer)) {
			
				session = req.getSession();
				Customer c = customerDAO.get(u.getUserName());
				session.setAttribute("statementOffset", 1);
				session.setAttribute("customer", c);
				
				return "customerHome";
			}
			if (u.getUserType().equals(UserType.Employee)) {
				req.setAttribute("user", u);
				session = req.getSession();
				session.setAttribute("loggedInUser", u);
				req.setAttribute("person", (Person) u);
				Customer customer = new Customer();
				BankAccount bankAccount = new BankAccount();

				req.setAttribute("customer", customer);
				req.setAttribute("bankAccount", bankAccount);
//				
				ArrayList<ChequeDepositRequest> requestList = new ArrayList<ChequeDepositRequest>();
				requestList = chequeDepositRequestDAO.getAll();
				req.setAttribute("requestList", requestList);
				return "employeeHome";
			}
			if (u.getUserType().equals(UserType.Manager)) {
				req.setAttribute("user", u);
				return "managerHome";
			}
			if (u.getUserType().equals(UserType.Admin)) {
				
				req.getSession().setAttribute("adminUser", u);
				req.getSession().setAttribute("username", u.getUserName());
				req.setAttribute("employee", new User());
				return "adminHome";
			}
		

		return "error";
	}



	@RequestMapping(value = "/signIn.htm", method = RequestMethod.GET)
	public String signInGET(Locale locale, @ModelAttribute("user") User user, HttpServletRequest req,
			HttpServletResponse res, BindingResult result) {
		return "error1";
	}

	@RequestMapping(value = "/signUp.htm", method = RequestMethod.POST)
	public String signUp(@ModelAttribute("user") User user, BindingResult result, HttpServletRequest req,
			HttpServletResponse res) {
		try {
//			System.out.println(user.getUserName() + "/" + user.getPassword() + "/" + user.getEmailId());

			userDAO.create(user.getUserName(), user.getPassword(), user.getEmailId());

		} catch (HibernateException he) {

		}

		req.setAttribute("user", user);
		return "signedUp";

	}

	@RequestMapping(value = "/logout.htm", method = RequestMethod.POST)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		
		request.getSession().invalidate();
		User userNew = new User();
		request.setAttribute("user", userNew);
		return "home";
	}

	// /resetPassword.htm

	@RequestMapping(value = "/resetPassword.htm", method = RequestMethod.POST)
	public void resetPassword(HttpServletResponse res, HttpServletRequest req,
			@RequestParam Map<String, String> param) {

		String password = param.get("password");
		String reenterPassword = param.get("reenterPassword");
//		System.out.println("password " + password + "/" + "reenterPassword " + reenterPassword);
		session = req.getSession();
		PrintWriter out;
		JSONObject json = new JSONObject();
		User u = (User) session.getAttribute("customer");
		if (password.equals(reenterPassword)) {
			PasswordStrengthRegEx passwordReEx = new PasswordStrengthRegEx();
			boolean satisfiesPolicy = passwordReEx.validate(password);
//			System.out.println(satisfiesPolicy);

			if (satisfiesPolicy) {
				
				boolean resetStatus = userDAO.resetPassword(u, password);
				if (resetStatus) {
					json.put("returnMessage", "Password Successfully Changed.Please log out for change to be updated.");
					json.put("status", true);
					try {
						out = res.getWriter();
						out.print(json);

					} catch (IOException io) {
						io.printStackTrace();

					}
				}
			} else {
				json.put("returnMessage",
						"Password Strength too weak.Please select a password that satisfies safe password policy.");
				json.put("status", false);
				try {
					out = res.getWriter();
					out.print(json);

				} catch (IOException io) {
					io.printStackTrace();

				}
			}
		} else {
			json.put("returnMessage", "Please enter same password in both fields.");
			json.put("status", false);
			try {
				out = res.getWriter();
				out.print(json);

			} catch (IOException io) {
				io.printStackTrace();

			}
		}

	}

	// raiseRequest.htm
	@RequestMapping(value = "/raiseRequest.htm", method = RequestMethod.GET)
	public String makeRequest() {

		return "sendRequest";
	}

	@RequestMapping(value = "/velocityView.htm", method = RequestMethod.POST)
	public ModelAndView velocityReq(HttpServletRequest req, HttpServletResponse res) {
		return new ModelAndView("index", "now", new DateTime());

	}
}
