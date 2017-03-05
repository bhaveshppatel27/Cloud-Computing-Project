package com.neu.edu.wtp.dao;

import java.util.Map;

import org.joda.time.LocalDate;

import com.neu.edu.wtp.pojo.BankAccount;
import com.neu.edu.wtp.pojo.CreditCard;

public class CardDAO extends DAO {

	public CardDAO() {

	}

	public CreditCard createCreditCard(String cardOwner, Map<String, String> param,BankAccount toAccount) {
		CreditCard creditCard = new CreditCard();

		String cardNumber = param.get("cardNumber");
		String cardName = param.get("cardName");
		int cvv = Integer.parseInt(param.get("cvv"));
		int pinCode = Integer.parseInt(param.get("pinCode"));
		LocalDate validFrom = LocalDate.parse(param.get("validFrom"));

		creditCard.setCardNumber(cardNumber);
		creditCard.setCardOwner(cardOwner);
		creditCard.setCardName(cardName);
		creditCard.setCvv(cvv);
		creditCard.setPinCode(pinCode);
		creditCard.setValidFrom(validFrom);
		creditCard.setValidThrough(validFrom.plusYears(5));
		creditCard.setLinkedToAccount(toAccount);
		return creditCard;
//		try {
//			begin();
//			getSession().save(creditCard);
//			commit();
//			close();
//			return creditCard;
//
//		} catch (HibernateException e) {
//			rollback();
//			e.printStackTrace();
//			return null;

//		}

		

	}
}
