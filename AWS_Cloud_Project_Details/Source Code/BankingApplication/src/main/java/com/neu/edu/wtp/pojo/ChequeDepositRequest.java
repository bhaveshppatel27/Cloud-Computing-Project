package com.neu.edu.wtp.pojo;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="chequeDepositRequest")
public class ChequeDepositRequest {
	
	@Id
	@GeneratedValue
	@Column
	private int id;
	
	@Column(name="imageName")
	private String imageName;
	
	@OneToOne
	@JoinColumn(name="personID")
	private Customer customer;
	
	@Transient
	private MultipartFile imageFile;
	
	public ChequeDepositRequest(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
	
	
	

}
