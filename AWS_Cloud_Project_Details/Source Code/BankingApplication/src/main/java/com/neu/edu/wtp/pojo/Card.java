package com.neu.edu.wtp.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="cards")
@Inheritance(strategy=InheritanceType.JOINED)
public class Card {
	
	@Id
	@Column(name="cardnumber")
	@PrimaryKeyJoinColumn
	@Size(max=16,min=16, message="Card Number must be of 16 digits.")
	private String cardNumber;
	
	@Column(name="cardowner")
	private String cardOwner;
	
	
	@Column(name="validfrom")
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate validFrom;
	
	
	@Future
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="validthrough")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate validThrough;
	
	
	@Digits(integer=3, fraction=0,message="Hibernate validator-Only 3 digits allowed")
	@Column(name="cvv")
	private int cvv;
	
	
	@Digits(integer=4, fraction=0,message="Hibernate validator-Only 4 digits allowed")
	@Column(name="pincode")
	private int pinCode;
	
	public Card(){
		
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardOwner() {
		return cardOwner;
	}
	public void setCardOwner(String cardOwner) {
		this.cardOwner = cardOwner;
	}
	public LocalDate getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(LocalDate validFrom) {
		this.validFrom = validFrom;
	}
	public LocalDate getValidThrough() {
		return validThrough;
	}
	public void setValidThrough(LocalDate validThrough) {
		this.validThrough = validThrough;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public int getPinCode() {
		return pinCode;
	}
	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + cvv;
		result = prime * result + pinCode;
		result = prime * result + ((validFrom == null) ? 0 : validFrom.hashCode());
		result = prime * result + ((validThrough == null) ? 0 : validThrough.hashCode());
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
		Card other = (Card) obj;
		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
			return false;
		if (cvv != other.cvv)
			return false;
		if (pinCode != other.pinCode)
			return false;
		if (validFrom == null) {
			if (other.validFrom != null)
				return false;
		} else if (!validFrom.equals(other.validFrom))
			return false;
		if (validThrough == null) {
			if (other.validThrough != null)
				return false;
		} else if (!validThrough.equals(other.validThrough))
			return false;
		return true;
	}
	
	
}


