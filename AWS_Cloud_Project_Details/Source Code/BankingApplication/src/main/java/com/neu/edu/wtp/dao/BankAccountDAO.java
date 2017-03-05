package com.neu.edu.wtp.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.joda.time.LocalDate;

import com.neu.edu.wtp.pojo.BankAccount;
import com.neu.edu.wtp.pojo.CreditCard;
import com.neu.edu.wtp.pojo.Customer;
import com.neu.edu.wtp.pojo.DebitCard;
import com.neu.edu.wtp.utility.RandomNumberUtility;

public class BankAccountDAO extends DAO {
	
	private RandomNumberUtility randomNumberUtility;
	public BankAccountDAO() {

	}

	public RandomNumberUtility getRandomNumberUtility() {
		return randomNumberUtility;
	}



	public void setRandomNumberUtility(RandomNumberUtility randomNumberUtility) {
		this.randomNumberUtility = randomNumberUtility;
	}



	public BankAccount creatAccount(Customer customer, String accountType, String cardName, String cardNumber) {

		BankAccount bankAccount = new BankAccount();
		bankAccount.setAccountBalance(2000);
		bankAccount.setAccountType(accountType);

		DebitCard debitCard = new DebitCard();
		debitCard.setCardOwner(customer.getFirstName() + " " + customer.getLastName());
		debitCard.setCvv(randomNumberUtility.generateCvv());
		debitCard.setPinCode(randomNumberUtility.generatePincode());
		debitCard.setValidFrom(new LocalDate());
		debitCard.setCardNumber(Long.toString(randomNumberUtility.generateCardNumber()));
		debitCard.setValidThrough(new LocalDate().plusYears(5));
		debitCard.setCardName(cardName);
		debitCard.setDailyMaxWithDrawlLimit(800);
		debitCard.setMinimumAmountPerWithDrawl(20);

		try {
			debitCard.setLinkedToAccount(bankAccount);
			getSession().save(debitCard);
			commit();

			begin();
			getSession().save(bankAccount);
			commit();
			close();
			return bankAccount;
		} catch (HibernateException he) {
			he.printStackTrace();
			rollback();
			return null;
		}

	}

	public BankAccount getBankAccount(long accountNumber) {
		BankAccount ba;
		System.out.println("In BankAccount DAO");

		try {

			begin();
			Query q = getSession().createQuery("from BankAccount where accountNumber = :accountNumber");
			q.setLong("accountNumber", accountNumber);
			ba = (BankAccount) q.uniqueResult();
			commit();
			close();

			return ba;
		} catch (HibernateException he) {
			he.printStackTrace();
			rollback();
		}
		return null;
	}

	public Void updateAccountsAfterTrasaction(long fromAccount, long toAccount, float amount) {

		try {

			BankAccount fromBankAccount = getBankAccount(fromAccount);
			float currentBalance;
			currentBalance = fromBankAccount.getAccountBalance();

			fromBankAccount.setAccountBalance(currentBalance - amount);

			begin();
			getSession().update(fromBankAccount);
			commit();
			close();

			BankAccount toBankAccount = getBankAccount(toAccount);
			currentBalance = toBankAccount.getAccountBalance();

			toBankAccount.setAccountBalance(currentBalance + amount);

			begin();
			getSession().update(toBankAccount);
			commit();
			close();

		} catch (HibernateException he) {
			he.printStackTrace();
			rollback();
		}

		return null;
	}

	public boolean registerCreditCard(CreditCard creditCard, long toAccount) {

		try {

			BankAccount toBankAccount = getBankAccount(toAccount);
			if(toBankAccount.getRegisteredCreditCardsList().contains(creditCard)){
				return false;
			}
			
			toBankAccount.getRegisteredCreditCardsList().add(creditCard);
			begin();
			getSession().update(toBankAccount);
			commit();
			close();
			return true;
		} catch (HibernateException he) {
			he.printStackTrace();
			rollback();
			return false;
		}

	}

}
