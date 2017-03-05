package com.neu.edu.wtp.dao;

import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.neu.edu.wtp.pojo.BankAccount;
import com.neu.edu.wtp.pojo.Customer;
//import com.yusuf.spring.exception.AdException;
//import com.neu.edu.wtp.pojo.DebitCard;
import com.neu.edu.wtp.pojo.DebitCard;
import com.neu.edu.wtp.pojo.UserType;
import com.neu.edu.wtp.utility.PasswordEncryption;
import com.neu.edu.wtp.utility.RandomNumberUtility;

public class CustomerDAO extends DAO {

	@Autowired
	@Qualifier("bankAccountDAO")
	BankAccountDAO bankAccountDAO;

	public CustomerDAO() {
	}

	private RandomNumberUtility randomNumberUtility;

	public RandomNumberUtility getRandomNumberUtility() {
		return randomNumberUtility;
	}

	public void setRandomNumberUtility(RandomNumberUtility randomNumberUtility) {
		this.randomNumberUtility = randomNumberUtility;
	}

	// public void setBankAccountDAO(BankAccountDAO bankAccountDAO) {
	// this.bankAccountDAO = bankAccountDAO;
	// }

	// get by UserName
	public Customer get(String username) {
		try {
			begin();

			Query q = getSession().createQuery("from Customer where userName = :username");
			q.setString("username", username);
			Customer customer = (Customer) q.uniqueResult();
			commit();
			close();
			return customer;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not get user " + username, e);
		}
		return null;
	}

	// get by personID
	public Customer get(long personId) {
		try {
			begin();
			Query q = getSession().createQuery("from Customer where personId = :personId");
			q.setString("personid", Long.toString(personId));
			Customer customer = (Customer) q.uniqueResult();
			commit();
			close();
			return customer;
		} catch (HibernateException e) {
			e.printStackTrace();
			rollback();
			// throw new AdException("Could not get user " + username, e);
		}
		return null;
	}

	public Customer create(Map<String, String> params) {
		try {
//			System.out.println(params.get("firstName"));
			Customer customer = new Customer();
			customer.setFirstName(params.get("firstName"));
			customer.setLastName(params.get("lastName"));
			customer.setUserName(params.get("userName"));

			customer.setPassword(PasswordEncryption.encrypt(params.get("password")));

			customer.setGender(params.get("gender"));
			customer.setEmailId(params.get("emailId"));
			customer.setDateOfBirth(LocalDate.parse(params.get("dateOfBirth")));
			customer.setUserType(UserType.Customer);

			BankAccount bankAccount = creatAccount(customer, params.get("accountType"), params.get("cardName"),
					params.get("cardNumber"));

			if (bankAccount != null) {
				try {

					begin();
					customer.getBankAccounts().add(bankAccount);
					getSession().save(customer);

					commit();
					close();
					return customer;
				} catch (ConstraintViolationException cve) {
					cve.printStackTrace();
					rollback();
					return null;
				} catch (HibernateException e) {
					e.printStackTrace();
					rollback();
					return null;

				}

			} else {
				return null;
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			rollback();
			return null;
		}

	}

	public BankAccount creatAccount(Customer customer, String accountType, String cardName, String cardNumber) {

		BankAccount bankAccount = new BankAccount();
		bankAccount.setAccountBalance(2000);
		bankAccount.setAccountType(accountType);
		bankAccount.setBelongToCustomer(customer);
		DebitCard debitCard = new DebitCard();
		debitCard.setCardOwner(customer.getFirstName() + " " + customer.getLastName());
		debitCard.setCvv(randomNumberUtility.generateCvv());
		debitCard.setPinCode(randomNumberUtility.generatePincode());
		debitCard.setValidFrom(new LocalDate().minusDays(1));
		String cardNum = Long.toString(randomNumberUtility.generateCardNumber());
//		System.out.println("############################" + cardNum);
		debitCard.setCardNumber(cardNum);
		debitCard.setValidThrough(new LocalDate().plusYears(5));
		debitCard.setCardName(cardName);
		debitCard.setDailyMaxWithDrawlLimit(800);
		debitCard.setMinimumAmountPerWithDrawl(20);
		debitCard.setLinkedToAccount(bankAccount);
		getSession().save(debitCard);
		bankAccount.setAssociatedDebitCard(debitCard);
		return bankAccount;

		// Below code is commented as BankAccount and Debit Cards are child of
		// Customer.
		// When I tried to save it before saving customer, I got the following
		// error:
		// object references an unsaved transient instance - save the transient
		// instance before flushing

		// try {
		// begin();
		// getSession().save(bankAccount);
		// commit();
		// close();
		//
		// return bankAccount;
		// } catch (HibernateException he) {
		// he.printStackTrace();
		// rollback();
		// return null;
		// }

	}

	public void delete(Customer customer) {
		try {
			begin();
			getSession().delete(customer);
			commit();
			close();
		} catch (HibernateException e) {
			rollback();
			e.printStackTrace();

		}
	}

	public String addBeneficiary(long accountNumber, String emailId, Customer loggedInCustomer) {

		BankAccount beneficiaryAccount;
//		System.out.println("Inside DAO");
		try {

			begin();
			Query q = getSession().createQuery("from BankAccount where accountNumber = :accountNumber");
			q.setLong("accountNumber", accountNumber);
			beneficiaryAccount = (BankAccount) q.uniqueResult();

			close();
			if (beneficiaryAccount == null) {
				return null;
			}

			begin();

			loggedInCustomer.getBeneficiaryList().add(beneficiaryAccount);

			getSession().update(loggedInCustomer);
			commit();
			close();
			return "Beneficiary Added.";

		} catch (HibernateException he) {
			he.printStackTrace();
			rollback();
			return "Beneficiary already added in your account.";

		}

	}
}
