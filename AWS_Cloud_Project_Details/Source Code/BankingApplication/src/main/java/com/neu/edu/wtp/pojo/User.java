package com.neu.edu.wtp.pojo;


//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

import com.neu.edu.wtp.utility.UserTypeConverter;


@Entity
@Cacheable(value="user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "usertable")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "personID")
public class User extends Person {

	
	@Size(min=6,max=10,message="User name must be 6-10 characters long")
	@Pattern(regexp="^[a-z0-9_-]{6,10}$",message="Invalid User name as per the policy.")
	@Column(name = "username")
	private String userName;

	@NotNull
	//@Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})",message="Hibernate Validator-Please set password as per the policy.")
	@Column(name = "password")
	private String password;

	@NotNull
	@Pattern(regexp="^[\\w!#$%&�*+/=?`{|}~^-]+(?:\\.[\\w!#$%&�*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",message="Invalid Email format.")
	@Column(name = "emailid")
	private String emailId;

	
	@Convert(converter = UserTypeConverter.class)
	private UserType userType;

	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;

	}

	public User() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}


}
