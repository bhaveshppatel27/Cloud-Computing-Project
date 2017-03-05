package com.neu.edu.wtp.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="debitcards")
@Inheritance(strategy=InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="cardnumber")
public class DebitCard extends Card{

	@Column(name="cardname")
	private String cardName;//example gold card,titanium card
	
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	private BankAccount linkedToAccount;
	
	@Column(name="dailymaxwithdrawllimit")
	private float dailyMaxWithDrawlLimit; //eample- $800/day
	
	@Column(name="minimumamountperwithdrawl")
	private float minimumAmountPerWithDrawl;// example minimum of $10/ withdrawl

	public DebitCard(){
		
	}
	public BankAccount getLinkedToAccount() {
		return linkedToAccount;
	}

	public void setLinkedToAccount(BankAccount linkedToAccount) {
		this.linkedToAccount = linkedToAccount;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public float getDailyMaxWithDrawlLimit() {
		return dailyMaxWithDrawlLimit;
	}

	public void setDailyMaxWithDrawlLimit(float dailyMaxWithDrawlLimit) {
		this.dailyMaxWithDrawlLimit = dailyMaxWithDrawlLimit;
	}

	public float getMinimumAmountPerWithDrawl() {
		return minimumAmountPerWithDrawl;
	}

	public void setMinimumAmountPerWithDrawl(float minimumAmountPerWithDrawl) {
		this.minimumAmountPerWithDrawl = minimumAmountPerWithDrawl;
	}
	
	
	
}
