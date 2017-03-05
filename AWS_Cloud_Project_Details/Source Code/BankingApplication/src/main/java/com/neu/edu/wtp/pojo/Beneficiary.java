package com.neu.edu.wtp.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="beneficiaryTable")
public class Beneficiary {

	@Id
	@Column(name="beneficiaryID")
	private int beneficiaryID;
	
	@Column(name="beneficiaryname")
	private String beneficiaryname;
	
	@Column(name="beneficiaryAccountNumber")
	private long beneficiaryAccountNumber;
	

	public String getBeneficiaryname() {
		return beneficiaryname;
	}


	public int getBeneficiaryID() {
		return beneficiaryID;
	}


	public void setBeneficiaryID(int beneficiaryID) {
		this.beneficiaryID = beneficiaryID;
	}


	public void setBeneficiaryname(String beneficiaryname) {
		this.beneficiaryname = beneficiaryname;
	}


	public long getBeneficiaryAccountNumber() {
		return beneficiaryAccountNumber;
	}

	public void setBeneficiaryAccountNumber(long beneficiaryAccountNumber) {
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
	}


	
}
