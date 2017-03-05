package com.neu.edu.wtp.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name="transactions")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class Transaction {

	@Id
	@GeneratedValue
	@Column(name="transactionId")
	private long transactionId;
	//private String type;
	
	
	@Column(name="amount")
	private float amount;
	
	
	@Column(name="fromAccount")
	private long fromAccount;
	
	@Column(name="toAccount")
	private long toAccount;
	
	@Column(name="shortDescription")
	private String shortDescription;
	
	
	@Column(name="transactionDate")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	
	private DateTime transactionDate;
	
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	
	public long getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(long fromAccount) {
		this.fromAccount = fromAccount;
	}
	public long getToAccount() {
		return toAccount;
	}
	public void setToAccount(long toAccount) {
		this.toAccount = toAccount;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public DateTime getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(DateTime transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	

	
	
}
