package com.neu.edu.wtp.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

//import java.util.HashMap;

@Entity
@Table(name = "bankaccount")
public class BankAccount {

	// @GeneratedValue//equivalent to generator=native in XML
	// @GeneratedValue(strategy=GenerationType.TABLE, generator="ACC")
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "accountnumber")
	@Digits(integer=10, fraction=0)
	private long accountNumber;

	
	@Column(name = "accountbalance")
	private float accountBalance;

	@Column(name = "accounttype")
	private String accountType;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL,mappedBy="linkedToAccount")
	private DebitCard associatedDebitCard;

	@OneToMany(fetch = FetchType.EAGER,mappedBy="linkedToAccount",cascade=CascadeType.ALL)
	private Set<CreditCard> registeredCreditCardsList;
	
	
	@OneToOne(fetch = FetchType.EAGER)
	private Customer belongToCustomer;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "beneficiaryList")
	private Set<Customer> beneficiaryCustomer;

	public Customer getBelongToCustomer() {
		return belongToCustomer;
	}

	public void setBelongToCustomer(Customer belongToCustomer) {
		this.belongToCustomer = belongToCustomer;
	}

	public void setRegisteredCreditCardsList(Set<CreditCard> registeredCreditCardsList) {
		this.registeredCreditCardsList = registeredCreditCardsList;
	}

	public BankAccount() {
		this.registeredCreditCardsList=new HashSet<CreditCard>();
	}

	public BankAccount(long accountNumber) {
		this.accountNumber = accountNumber;
		
		this.registeredCreditCardsList = new HashSet<CreditCard>();
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public float getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(float accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	

	public DebitCard getAssociatedDebitCard() {
		return associatedDebitCard;
	}

	public void setAssociatedDebitCard(DebitCard associatedDebitCard) {
		this.associatedDebitCard = associatedDebitCard;
	}

	public Set<CreditCard> getRegisteredCreditCardsList() {
		return registeredCreditCardsList;
	}

	public void setRegisteredCreditCardsList(HashSet<CreditCard> registeredCreditCardsList) {
		this.registeredCreditCardsList = registeredCreditCardsList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (accountNumber ^ (accountNumber >>> 32));
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccount other = (BankAccount) obj;
		if (accountNumber != other.accountNumber)
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		return true;
	}
	
}
