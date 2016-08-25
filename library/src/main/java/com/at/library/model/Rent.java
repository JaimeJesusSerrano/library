package com.at.library.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Rent implements Serializable {

	private static final long serialVersionUID = 1560692319680192039L;
	
	@EmbeddedId
	private RentPK rentPK;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Employee employee;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Client client;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	
	@Transient
	public Date getInitDate() {
		return rentPK.getInitDate();
	}

	@Transient
	public void setInitDate(Date initDate) {
		rentPK.setInitDate(initDate);
	}

	@Transient
	public Book getBook() {
		return rentPK.getBook();
	}

	@Transient
	public void setBook(Book book) {
		rentPK.setBook(book);
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
