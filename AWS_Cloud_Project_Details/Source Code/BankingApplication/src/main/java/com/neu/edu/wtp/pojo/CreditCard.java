package com.neu.edu.wtp.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="creditcards")
@Inheritance(strategy=InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="cardnumber")
public class CreditCard extends Card{
	
	@Column(name="currentdue")
	private float currentDue=0;//initialized for testing
	
	@Column(name="cardName")
	private String cardName;
	
	@OneToOne(fetch=FetchType.EAGER)
	//@JoinColumn(name="accountNumber")
	private BankAccount linkedToAccount;
	
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	@Column(name="maxcreditlimit")
	private float maxCreditlimit=2000;//initialized for testing
	
	@Column(name="availablecreditbalance")
	private float availableCreditBalace=500;//initialized for testing
	
	public float getCurrentDue() {
		return currentDue;
	}
	public void setCurrentDue(float currentDue) {
		this.currentDue = currentDue;
	}
	public float getMaxCreditlimit() {
		return maxCreditlimit;
	}
	public void setMaxCreditlimit(float maxCreditlimit) {
		this.maxCreditlimit = maxCreditlimit;
	}
	public float getAvailableCreditBalace() {
		return availableCreditBalace;
	}
	public void setAvailableCreditBalace(float availableCreditBalace) {
		this.availableCreditBalace = availableCreditBalace;
	}
	public BankAccount getLinkedToAccount() {
		return linkedToAccount;
	}
	public void setLinkedToAccount(BankAccount linkedToAccount) {
		this.linkedToAccount = linkedToAccount;
	}

	
	
}
