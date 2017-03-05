package com.neu.edu.wtp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neu.edu.wtp.Validators.PasswordValidator;
import com.neu.edu.wtp.Validators.UserValidator;
import com.neu.edu.wtp.dao.UserDAO;
import com.neu.edu.wtp.pojo.User;

@Controller
public class AdminController {
	@Autowired
	@Qualifier("userDAO")
	UserDAO userDAO;

	@Autowired
	@Qualifier("user")
	User autowiredUser;

	@Autowired
	@Qualifier("userValidator")
	UserValidator userValidator;

	@Autowired
	@Qualifier("passwordValidator")
	PasswordValidator passwordValidator;

	static Error notUnique=new Error("User name not Unique");

	static JSONObject json = new JSONObject();




	@RequestMapping(value = "/addEmployee.htm", method = RequestMethod.POST)
	public String addManager(@Valid @ModelAttribute("employee") User user, BindingResult result, HttpServletRequest req,
			HttpServletResponse res) {

		if (result.hasErrors()) {
			return "adminHome";
		}
		if(userDAO.isUsernameUnique(user.getUserName())){
			userDAO.createEmployee(user.getFirstName(), user.getLastName(), user.getUserName(), user.getPassword(),
					user.getEmailId(), user.getGender(), user.getDateOfBirth().toString());
			req.setAttribute("addErrorMessage", "Successfully added new user :"+user.getUserName());
			req.setAttribute("employee", new User());

			return "adminHome";
		}else{
			result.addError(new ObjectError("employee.userName", "User Name not Unique"));
			req.setAttribute("addErrorMessage", "ERORRRRRR :"+user.getUserName());
			return "adminHome";
		}

	}




//	@RequestMapping(value = "/adminHome.htm", method = RequestMethod.POST)
//	public ModelAndView returnHome(/*
//	 * Locale locale, @ModelAttribute("user")
//	 * User user,HttpServletRequest
//	 * req,HttpServletResponse res
//	 */) {
//		ModelAndView mv = new ModelAndView();
//		User user = new User();
//		mv.addObject("employee", user);
//		mv.setViewName("adminHome");
//		return mv;
//	}

	@RequestMapping(value = "/adminHome.htm", method = RequestMethod.GET)
	public String returnHomeGet(/*
	 * Locale locale, @ModelAttribute("user")
	 * User user,HttpServletRequest
	 * req,HttpServletResponse res
	 */) {

		return "error1";
	}

	@RequestMapping(value = "/searchManager.htm", method = RequestMethod.GET)
	public void ajaxUserSearch(HttpServletResponse res, HttpServletRequest req,
			@RequestParam Map<String, String> param) {

		List<User> usersList;
		PrintWriter out;
		try {

			res.setHeader("content-Type", "application/json");

			usersList = userDAO.getListOfUsers(param.get("searchKey"));
			out = res.getWriter();
			ArrayList<String> namesList = new ArrayList<String>();
			for (User u : usersList) {
				namesList.add(u.getUserName());
			}

			json.put("usersList", namesList);
			out.print(json);
		} catch (JSONException je) {
			je.printStackTrace();
		} catch (IOException e) {
			//
			e.printStackTrace();
		} catch (StackOverflowError so) {
			so.printStackTrace();
		}

	}

	// updateEmployee.htm
	@RequestMapping(value = "/updateEmployee.htm", method = RequestMethod.POST)
	public String updateEmployee(HttpServletResponse res, HttpServletRequest req,
			@RequestParam Map<String, String> param) {

		String userName = param.get("searchEmployee");
		//int id;
		User user = userDAO.getUserByUserName(userName);
		if (null == user) {
			req.setAttribute("errorMessage", "You provided incorrect Username previously");
			req.setAttribute("employee", new User());
			return "adminHome";
		}
		req.setAttribute("employee", user);
		return "updateEmoloyee";
	}

	@RequestMapping(value = "/updateEmployee.htm", method = RequestMethod.GET)
	public String updateEmployeeGet(HttpServletResponse res, HttpServletRequest req,
			@RequestParam Map<String, String> param) {

		return "error1";
	}

	//saveChanges.htm
	@RequestMapping(value = "/saveChanges.htm", method = RequestMethod.POST)
	public String saveChanges(@Valid @ModelAttribute("employee") User user, BindingResult result, HttpServletRequest req,
			HttpServletResponse res) {
		passwordValidator.validate(user, result);
		if (result.hasErrors()) {
			req.setAttribute("employee", user);
			return "updateEmoloyee";
		}




		boolean isUpdated= userDAO.updateUser(user,user.getPersonID());
		if (isUpdated) {
			req.setAttribute("errorMessage", "Successfully updated User"+user.getUserName() );
			req.setAttribute("employee", new User());
			return "adminHome";
		}else{
			req.setAttribute("erroMessage", "Unable to update User.");
			return "updateEmoloyee";
		}

	}

	@RequestMapping(value = "/saveChanges.htm", method = RequestMethod.GET)
	public String saveChangesGet(@Valid @ModelAttribute("employee") User user, BindingResult result, HttpServletRequest req,
			HttpServletResponse res) {
		return "error1";
	}

}
