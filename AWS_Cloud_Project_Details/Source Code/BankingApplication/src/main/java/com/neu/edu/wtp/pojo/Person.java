package com.neu.edu.wtp.pojo;

//import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Cacheable(value="user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="person")
@Inheritance(strategy=InheritanceType.JOINED)
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="personID")
	private long personID;

	@NotNull(message="Please provide first name.")
	@Pattern(regexp="^[\\p{L} .'-]+$",message="Name cannot contain numbers/multiple spaces/special characters(Except . ' -)")
	@Column(name="firstName")
	private String firstName;

	@NotNull(message="Please provide last name.")
	@Pattern(regexp="^[\\p{L} .'-]+$",message="Name cannot contain numbers/multiple spaces/special characters(Except . ' -)")
	@Column(name="lastName")
	private String lastName;


	@NotNull(message="Provide a value.")
	@Column(name="gender")
	private String gender;


	@Past(message="Please use format yyyy-mm-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="dateofbirth")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate dateOfBirth;


	//private User userAccount;

	public Person(){

	}

	public long getPersonID() {
		return personID;
	}

	//public void setPersonID(long personID) {
	//	this.personID = personID;
	//}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


}
