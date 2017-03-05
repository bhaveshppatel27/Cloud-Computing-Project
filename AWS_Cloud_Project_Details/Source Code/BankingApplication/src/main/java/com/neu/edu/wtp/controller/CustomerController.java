package com.neu.edu.wtp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neu.edu.wtp.Validators.BankValidator;
import com.neu.edu.wtp.Validators.CardValidator;
import com.neu.edu.wtp.dao.BankAccountDAO;
import com.neu.edu.wtp.dao.CardDAO;
import com.neu.edu.wtp.dao.ChequeDepositRequestDAO;
import com.neu.edu.wtp.dao.CustomerDAO;
import com.neu.edu.wtp.dao.TransactionDAO;
import com.neu.edu.wtp.pojo.BankAccount;
import com.neu.edu.wtp.pojo.ChequeDepositRequest;
import com.neu.edu.wtp.pojo.CreditCard;
import com.neu.edu.wtp.pojo.Customer;
import com.neu.edu.wtp.pojo.Transaction;
import com.neu.edu.wtp.utility.EmailUtility;
import com.neu.edu.wtp.utility.FileUploadUtility;
import com.neu.edu.wtp.utility.PasswordEncryption;


@Controller
public class CustomerController {

	@Autowired
	@Qualifier("customerDAO")
	CustomerDAO customerDAO;

	@Autowired
	@Qualifier("bankAccountDAO")
	BankAccountDAO bankAccountDAO;

	@Autowired
	@Qualifier("cardDAO")
	CardDAO cardDAO;

	@Autowired
	@Qualifier("transactionDAO")
	TransactionDAO transactionDAO;

	@Autowired
	@Qualifier("chequeDepositRequestDAO")
	ChequeDepositRequestDAO chequeDepositRequestDAO;

	@Autowired
	@Qualifier("bankValidator")
	BankValidator bankValidator;

	// cardValidator
	@Autowired
	@Qualifier("cardValidator")
	CardValidator cardValidator;

	@Autowired
	@Qualifier("fileUploadUtility")
	FileUploadUtility fileUploadUtility;

	@Autowired
	@Qualifier("emailUtility")
	EmailUtility emailUtility;

	@Autowired
	@Qualifier("passwordEncryption")
	PasswordEncryption passwordEncryption;

	HttpSession session;


	@RequestMapping(value = "/makeTransfer.htm", method = RequestMethod.POST)
	public String fundTransferLink(Model model) {
		/// transaction
		Transaction transaction = new Transaction();
		
		model.addAttribute("transaction", transaction);
		return "fundTransfer";
	}

	@RequestMapping(value = "/makeTransfer.htm", method = RequestMethod.GET)
	public String fundTransferLinkGet(HttpServletRequest req, Model model) {

		return "error1";
	}

	@RequestMapping(value = "/beneficiary.htm", method = RequestMethod.POST)
	public String manageBeneficiary() {
		return "beneficiary";
	}

	@RequestMapping(value = "/beneficiary.htm", method = RequestMethod.GET)
	public String manageBeneficiaryGet(HttpServletRequest req) {

		return "error1";
	}

	@RequestMapping(value = "/customerHome.htm", method = RequestMethod.POST)
	public String returnHome(HttpServletRequest req) {

		req.getSession().setAttribute("statementOffset", 1);
		return "customerHome";
	}

	@RequestMapping(value = "/customerHome.htm", method = RequestMethod.GET)
	public String returnHomeGet(HttpServletRequest req) {
		if (req.getSession().getAttribute("customer") == null) {
			return "home";
		} else {
			returnHome(req);
		}

		return "error";
	}

	//@RequestMapping(value = "/viewCards.htm", method = RequestMethod.POST)
	public String viewCards() {

		return "viewCards";
	}

	//fremarker view cards

	@RequestMapping(value="/viewCards.htm" ,method=RequestMethod.POST)
	public ModelAndView viewCardsFreeMarker(HttpServletRequest req){
		ModelAndView mv=new ModelAndView();
		Customer customer=(Customer)req.getSession().getAttribute("customer");
		mv.addObject("bankAccounts",customer.getBankAccounts() );
		mv.addObject("customerName", customer.getFirstName() +" "+customer.getLastName() );
		mv.setViewName("viewMyCards");		
		return mv;
	}






	@RequestMapping(value = "/viewCards.htm", method = RequestMethod.GET)
	public String viewCardsGet(HttpServletRequest req) {

		return "error1";
	}

	// manageProfile.htm
	@RequestMapping(value = "/manageProfile.htm", method = RequestMethod.POST)
	public String manageProfile() {
		return "manageProfile";
	}

	@RequestMapping(value = "/manageProfile.htm", method = RequestMethod.GET)
	public String manageProfileGet(HttpServletRequest req) {

		return "error1";
	}

	@RequestMapping(value = "/registerCard.htm", method = RequestMethod.POST)
	public String registerCard(Model model) {

		// creditCard
		CreditCard creditCard = new CreditCard();
		model.addAttribute("creditCard", creditCard);
		return "registerCard";
	}

	@RequestMapping(value = "/registerCard.htm", method = RequestMethod.GET)
	public String registerCardGet(HttpServletRequest req, Model model) {

		return "error1";

	}

	@RequestMapping(value = "/chequeDeposit.htm", method = RequestMethod.POST)
	public String chequeDepositPage(Model model) {
		ChequeDepositRequest cdr = new ChequeDepositRequest();
		model.addAttribute("cdr", cdr);
		return "sendCheque";
	}

	@RequestMapping(value = "/chequeDeposit.htm", method = RequestMethod.GET)
	public String chequeDepositPageGet(HttpServletRequest req, Model model) {

		return "error1";
	}

	@RequestMapping(value = "/addBeneficiary.htm", method = RequestMethod.POST)
	public void addBeneficiary(HttpServletResponse res, HttpServletRequest req,
			@RequestParam Map<String, String> param) {
		session = req.getSession();

		// USE QUERY BY EXAMPLE TO SEARCH USER WITH MULTIPLE FIELD VALUES
		Customer loggedInCustomer = (Customer) session.getAttribute("customer");

		long accountNumber = Long.parseLong(param.get("accountNumber"));
		String emailId = param.get("emailId");
		//String status;
		PrintWriter out;
		//boolean addingOtherCustomer = true;
		
		try {
//			for (BankAccount ba : loggedInCustomer.getBankAccounts()) {
//				if (ba.getAccountNumber() == accountNumber) {
//					System.out.println(ba.getAccountNumber());
//					addingOtherCustomer = false;
//					break;
//				}
//			}
			JSONObject json = new JSONObject();
			out = res.getWriter();
			BankAccount bankAccount=bankAccountDAO.getBankAccount(accountNumber);
			if(bankAccount==null){
				json.put("returnMessage", "Incorrect beneficiary account number.");
				json.put("status", false);
				out.print(json);
			}else{
				if(!loggedInCustomer.getBankAccounts().contains(bankAccount)){
					//status = customerDAO.addBeneficiary(accountNumber, emailId, loggedInCustomer);
					
					if(loggedInCustomer.getBeneficiaryList().contains(bankAccount)){
						json.put("returnMessage", "Already Exists");
						json.put("status", false);
					}else{
						customerDAO.addBeneficiary(accountNumber, emailId, loggedInCustomer);
						json.put("returnMessage", "Added");
						json.put("status", true);
					}
					
					
					
//					if (status.equals("Beneficiary Added.")) {
//						json.put("returnMessage", status);
//						json.put("status", true);
//					} else {
//						// already added or no record found
//						json.put("returnMessage", status);
//						json.put("status", false);
//					}
					out.print(json);
				}else {
					json = new JSONObject();
					out = res.getWriter();
					json.put("returnMessage", "You cannot add yourself as Beneficiary.");
					json.put("status", false);
					out.print(json);
				}
			}
			
			

//			if (addingOtherCustomer) {
//				status = customerDAO.addBeneficiary(accountNumber, emailId, loggedInCustomer);
//				JSONObject json = new JSONObject();
//				out = res.getWriter();
//				if (status.equals("Beneficiary Added.")) {
//					json.put("returnMessage", status);
//					json.put("status", true);
//				} else {
//					// already added or no record found
//					json.put("returnMessage", status);
//					json.put("status", false);
//				}
//				out.print(json);
//			} else {
//				JSONObject json = new JSONObject();
//				out = res.getWriter();
//				json.put("returnMessage", "You cannot add yourself as Beneficiary.");
//				json.put("status", false);
//				out.print(json);
//			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/addBeneficiary.htm", method = RequestMethod.GET)
	public String addBeneficiaryGet(HttpServletResponse res, HttpServletRequest req,
			@RequestParam Map<String, String> param) {
		return "error1";
	}

	@RequestMapping(value = "/fundTransfer.htm", method = RequestMethod.POST)
	public String fundTransfer(HttpServletResponse res, HttpServletRequest req, @RequestParam Map<String, String> param,
			@Valid @ModelAttribute("transaction") Transaction transaction, BindingResult result) {
		bankValidator.validate(transaction, result);
		if (result.hasErrors()) {
			return "fundTransfer";
		}

//		System.out.println("Inside fund transfer method");
		try {
			Long fromAccount = Long.parseLong(param.get("fromAccount"));

			Float amount = Float.parseFloat(param.get("amount"));
			if (amount > bankAccountDAO.getBankAccount(fromAccount).getAccountBalance()) {
				req.setAttribute("transferError", "Insuffecient balance.");
				return "fundTransfer";
			}

			transaction = transactionDAO.createTransaction(param);
//			System.out.println("From/To/Amount/Description" + transaction.getFromAccount() + "/"
//					+ transaction.getToAccount() + "/" + transaction.getAmount() + "/"
//					+ transaction.getShortDescription() + "/" + transaction.getTransactionDate());
			bankAccountDAO.updateAccountsAfterTrasaction(transaction.getFromAccount(), transaction.getToAccount(),
					transaction.getAmount());
			Customer customer = (Customer) session.getAttribute("customer");

			emailUtility.sendEmail(customer.getEmailId(), transaction);
			refreshCustomer(req);

		} catch (Exception e) {
			e.printStackTrace();

		}

		req.setAttribute("transferMessage", "Fund Transfer Complete.");
		return "fundTranferCompleted";
	}

	@RequestMapping(value = "/fundTransfer.htm", method = RequestMethod.GET)
	public String fundTransferGet(HttpServletResponse res, HttpServletRequest req,
			@RequestParam Map<String, String> param, @Valid @ModelAttribute("transaction") Transaction transaction,
			BindingResult result) {

		return "error1";

	}

	@RequestMapping(value = "/processCardRegistration.htm", method = RequestMethod.POST)
	public String processCardRegistration(HttpServletResponse res, HttpServletRequest req,
			@RequestParam Map<String, String> param, @Valid @ModelAttribute("creditCard") CreditCard creditCard,
			BindingResult result) {

		cardValidator.validate(creditCard, result);
		if (result.hasErrors()) {
			return "registerCard";
		}
		try {
			session = req.getSession();
			Customer customer = (Customer) session.getAttribute("customer");
			String cardOwner = customer.getFirstName() + " " + customer.getLastName();

			Long toAccount = Long.parseLong(param.get("toAccount"));
			BankAccount bankAccount = bankAccountDAO.getBankAccount(toAccount);

			creditCard = cardDAO.createCreditCard(cardOwner, param, bankAccount);
			PrintWriter out;
			JSONObject json = new JSONObject();
			if (creditCard != null) {
				boolean isAdded = bankAccountDAO.registerCreditCard(creditCard, toAccount);

				if (isAdded) {
					refreshCustomer(req);
					json.put("returnMessage", "Card Added");
					json.put("status", true);
					try {
						out = res.getWriter();
						out.print(json);
					} catch (IOException io) {
						io.printStackTrace();

					}
					return "registerCard";

				}


			}
		} catch (NullPointerException ne) {
			ne.printStackTrace();
		}

		return "registerCard";

	}

	@RequestMapping(value = "/processCardRegistration.htm", method = RequestMethod.GET)
	public String processCardRegistrationGet(HttpServletResponse res, HttpServletRequest req,
			@RequestParam Map<String, String> param, @Valid @ModelAttribute("creditCard") CreditCard creditCard,
			BindingResult result) {

		return "error1";

	}

	// viewStatement
	@RequestMapping(value = "/viewStatement.htm", method = RequestMethod.POST)
	public String viewStatement() {
		return "viewStatement";
	}

	@RequestMapping(value = "/viewStatement.htm", method = RequestMethod.GET)
	public String viewStatementGet(HttpServletRequest req) {
		return "error1";
	}

	// getStatement
	// @RequestMapping(value = "/generateStatement.htm", method =
	// RequestMethod.POST)
	public String getStatement(HttpServletResponse res, HttpServletRequest req,
			@RequestParam Map<String, String> param) {

		ObjectMapper objectMapper=new ObjectMapper();

		session = req.getSession();
		int offset = (Integer) session.getAttribute("statementOffset");
//		System.out.println("OFFSET" + offset);
		List<Transaction> paginatedList = transactionDAO.paginateTransaction(offset,
				Long.parseLong(param.get("forAccount")));
		offset++;
		session.setAttribute("statementOffset", offset);
		long accountNumber = Long.parseLong(param.get("forAccount"));
		session.setAttribute("customerAccountNumber", accountNumber);
		try {
			objectMapper.writeValueAsString(paginatedList);

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		req.setAttribute("statement", paginatedList);
		req.setAttribute("customerAccountNumber", param.get("forAccount"));
		for (Transaction t : paginatedList) {
//			System.out.println(t.getAmount() + "/" + t.getFromAccount() + "/" + t.getToAccount() + "/"
//					+ t.getShortDescription() + "/" + t.getTransactionId() + "/" + t.getTransactionDate());
		}
		return "viewStatement";

	}

	// statement,customerAccountNumber
	@RequestMapping(value = "/generateStatement.htm", method = RequestMethod.POST)
	public ModelAndView velocityStatementReq(HttpServletResponse res, HttpServletRequest req,
			@RequestParam Map<String, String> param) {
		ModelAndView mv = new ModelAndView();
		session = req.getSession();
		ArrayList<Transaction> statement = transactionDAO.statement(Long.parseLong(param.get("forAccount")));
		session.setAttribute("customerAccountNumber",Long.parseLong(param.get("forAccount")));
		mv.addObject("customerAccountNumber", Long.parseLong(param.get("forAccount")));
		Customer customer=(Customer)session.getAttribute("customer");
		mv.addObject("customerName", customer.getFirstName()+" "+customer.getLastName());
		mv.addObject("statement", statement);
		mv.addObject("contextPath",req.getContextPath());
		mv.setViewName("statement");
		return mv;

	}

	@RequestMapping(value = "/generateStatement.htm", method = RequestMethod.GET)
	public String getStatementGet(HttpServletResponse res, HttpServletRequest req,
			@RequestParam Map<String, String> param) {

		return "error1";
	}

	@RequestMapping(value = "/generateNextStatement.htm", method = RequestMethod.POST)
	public void generateNextStatement(HttpServletResponse res, HttpServletRequest req) {
//		System.out.println("In generate function---->");

		PrintWriter out;
		JSONObject json = new JSONObject();
		session = req.getSession();
		int offset = (Integer) session.getAttribute("statementOffset");
//		System.out.println("statementOffset---->" + offset);
		long forAccount = (Long) session.getAttribute("customerAccountNumber");
//		System.out.println("customerAccountNumber--->" + forAccount);
		List<Transaction> paginatedList = transactionDAO.paginateTransaction(offset, forAccount);
		offset++;
//		System.out.println(offset);
//		System.out.println("Paginated List---->");
		ArrayList<Transaction> listForJson = new ArrayList<Transaction>();

		for (Transaction t : paginatedList) {
			Transaction tran = new Transaction();

			tran.setAmount(t.getAmount());
			tran.setFromAccount(t.getFromAccount());
			tran.setToAccount(t.getToAccount());
			tran.setShortDescription(t.getShortDescription());
			tran.setTransactionId(t.getTransactionId());
			tran.setTransactionDate(t.getTransactionDate());

			listForJson.add(tran);

//			System.out.println(t.getAmount() + "/" + t.getFromAccount() + "/" + t.getToAccount() + "/"
//					+ t.getShortDescription() + "/" + t.getTransactionId() + "/" + t.getTransactionDate());
		}
		session.setAttribute("statementOffset", offset);
		res.setHeader("Content-Type", "application/json");

		json.put("transactionList", listForJson);
		try {
			out = res.getWriter();
			out.print(json);

		} catch (JSONException jsonE) {
			jsonE.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	@RequestMapping(value = "/generateNextStatement.htm", method = RequestMethod.GET)
	public String generateNextStatementGet(HttpServletResponse res, HttpServletRequest req) {
		return "error1";
	}

	// sendCheque.htm

	@RequestMapping(value = "/sendCheque.htm", method = RequestMethod.POST)
	public String sendCheque(@RequestParam("imageFile") MultipartFile file, @RequestParam("imageName") String imageName,
			Model model, @RequestParam Map<String, String> param, @ModelAttribute("cdr") ChequeDepositRequest cdr,
			HttpServletRequest request) {

//		System.out.println("Context Path" + request.getContextPath());
		session = request.getSession();

//		System.out.println(imageName);
		Customer customer = (Customer) session.getAttribute("customer");
		try {
			String newName = fileUploadUtility.upImage(imageName, file);
			cdr.setImageName(newName);
			cdr.setCustomer(customer);
			chequeDepositRequestDAO.addRequest(cdr);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("sendStatus", "Error");
			return "sendCheque";
		}
		request.setAttribute("sendStatus", "Success");
		return "sendCheque";
	}

	// getPdfDocument.htm
	@RequestMapping(value = "/getPdfDocument.htm", method = RequestMethod.GET)
	public ModelAndView getPdfDocument(HttpServletResponse res, HttpServletRequest req) {
		session = req.getSession();
		long customerAccountNumber = (Long) session.getAttribute("customerAccountNumber");
		ArrayList<Transaction> transactionList = transactionDAO.statement(customerAccountNumber);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("pdfDoc");
		mv.addObject("transactionList", transactionList);
		mv.addObject("customerAccountNumber", customerAccountNumber);
		return mv;

	}

	@RequestMapping(value = "/sendCheque.htm", method = RequestMethod.GET)
	public String sendChequeGet(@RequestParam("imageFile") MultipartFile file,
			@RequestParam("imageName") String imageName, Model model, @RequestParam Map<String, String> param,
			@ModelAttribute("cdr") ChequeDepositRequest cdr, HttpServletRequest req) {
		if (req.getSession().getAttribute("customer") == null) {
			return "home";
		} else {
			sendCheque(file, imageName, model, param, cdr, req);
		}
		return "error";
	}

	/* 
	 * Whenever a new card is added or the transaction is done, the user account
	 should reflect the changes. If I do not do the reload of customer object in
	 session, changes are not relfected.Hence this boiler-plate code is written
	 separately

	 */
	public void refreshCustomer(HttpServletRequest req) {
		session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		customer = customerDAO.get(customer.getUserName());
		session.setAttribute("customer", customer);
	}
}
