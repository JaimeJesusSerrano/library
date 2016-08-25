package com.at.library.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Rent implements Serializable {

	private static final long serialVersionUID = 1560692319680192039L;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	private Date initDate;
	
	@Id
	@OneToOne
	private Book book;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Employee employee;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Client client;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;


	public Date getInitDate() {
		return initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
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
