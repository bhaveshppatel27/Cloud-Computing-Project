package com.neu.edu.wtp.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name="workrequest")
public class WorkRequest {

	@Column(name="requestDate")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime requestDate;
	
	@OneToOne(fetch = FetchType.EAGER,mappedBy="personId",cascade=CascadeType.ALL)
	private Customer fromCustomer;
	
	@NotNull
	@Column(name="description")
	private String description;
	
	@Column(name="resolvedDate")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime resolvedDate;
	
	@Column(name="status")
	private String status;
	
	@Column(name="handledBy")
	private String handledBy;
	
	public WorkRequest(){
		
	}


	public DateTime getRequestDate() {
		return requestDate;
	}


	public void setRequestDate(DateTime requestDate) {
		this.requestDate = requestDate;
	}


	public Customer getFromCustomer() {
		return fromCustomer;
	}


	public void setFromCustomer(Customer fromCustomer) {
		this.fromCustomer = fromCustomer;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public DateTime getResolvedDate() {
		return resolvedDate;
	}


	public void setResolvedDate(DateTime resolvedDate) {
		this.resolvedDate = resolvedDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getHandledBy() {
		return handledBy;
	}


	public void setHandledBy(String handledBy) {
		this.handledBy = handledBy;
	}
	
	
	
}
