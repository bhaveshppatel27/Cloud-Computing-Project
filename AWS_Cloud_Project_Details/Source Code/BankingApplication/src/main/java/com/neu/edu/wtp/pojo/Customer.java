package com.neu.edu.wtp.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

@Entity
@Cacheable(value="user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="customertable")
@PrimaryKeyJoinColumn(name="username")

public class Customer extends User{

	@Column(name="ssn")
	@Pattern(regexp="^(\\d{3}-?\\d{2}-?\\d{4}|XXX-XX-XXXX)$",message="Please enter a valid SSN number")
	private String sSN;
	
	//@Temporal(TemporalType.)
	
	@Column(name="phoneNumber")
	@Digits(integer=10,fraction=0,message="Please enter a valid phone number of 10 digits")
	private long phoneNumber;
	
	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL,mappedBy="belongToCustomer")
	private Set<BankAccount> bankAccounts;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="customer_bankaccount",joinColumns={@JoinColumn(name="personID")},inverseJoinColumns={@JoinColumn(name="accountNumber")})
	private Set<BankAccount> beneficiaryList;
	
	
	public Customer(){
		this.bankAccounts=new HashSet<BankAccount>();
		this.beneficiaryList=new HashSet<BankAccount>();
	}
	public Set<BankAccount> getBankAccounts() {
		return bankAccounts;
	}
	public void setBankAccounts(Set<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}
	public Set<BankAccount> getBeneficiaryList() {
		return beneficiaryList;
	}
	public void setBeneficiaryList(Set<BankAccount> beneficiaryList) {
		this.beneficiaryList = beneficiaryList;
	}
	public String getsSN() {
		return sSN;
	}
	public void setsSN(String sSN) {
		this.sSN = sSN;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
