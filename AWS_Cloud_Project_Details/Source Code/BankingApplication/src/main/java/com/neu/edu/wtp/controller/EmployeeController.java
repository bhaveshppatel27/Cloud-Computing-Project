package com.neu.edu.wtp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.neu.edu.wtp.Validators.CustomerValidator;
import com.neu.edu.wtp.dao.CustomerDAO;
import com.neu.edu.wtp.dao.UserDAO;
//import com.neu.edu.wtp.pojo.BankAccount;
import com.neu.edu.wtp.pojo.Customer;
//import com.neu.edu.wtp.pojo.User;
import com.neu.edu.wtp.pojo.User;

@Controller
public class EmployeeController {
	@Autowired
	@Qualifier("userDAO")
	UserDAO userDAO;

	
	
	@Autowired
	@Qualifier("customerDAO")
	CustomerDAO customerDAO;
	
	@Autowired
	@Qualifier("customerValidator")
	CustomerValidator customerValidator;

	@RequestMapping(value = "/createCustomer.htm", method = RequestMethod.POST)
	public String submit(HttpServletRequest req,@Valid @ModelAttribute("customer") Customer customer,BindingResult result ,
			@RequestParam Map<String, String> params) {

		customerValidator.validate(customer, result);
		if(result.hasErrors()){
			return "employeeHome";
		}
		Customer customer1 = customerDAO.create(params);

		if ( null==customer1) {
			req.setAttribute("message", "Failed to add Customer");
			return "employeeHome";
			

		} else {
			String newUserAddedMessage = "New User added";
			req.setAttribute("message", newUserAddedMessage);
			req.setAttribute("customer", customer1);
		}

		return "employeeHome";

	}
	
	@RequestMapping(value = "/createCustomer.htm", method = RequestMethod.GET)
	public String submitGet(HttpServletRequest req,@Valid @ModelAttribute("customer") Customer customer,BindingResult result ,
			@RequestParam Map<String, String> params) {
		
	return "error1";
	}

	@RequestMapping(value = "/searchUser.htm", method = RequestMethod.GET)
	public void ajaxUserSearch(HttpServletResponse res, HttpServletRequest req,
			@RequestParam Map<String, String> param) {

		List<User> usersList;
		PrintWriter out;
		try {

			JSONObject json = new JSONObject();
			usersList = userDAO.getListOfUsers(param.get("searchKey"));
			out = res.getWriter();
			json.put("usersList", usersList);
			out.print(json);
		} catch (IOException e) {
			// 
			e.printStackTrace();
		}catch(StackOverflowError so){
			so.printStackTrace();
		}

	}
}
