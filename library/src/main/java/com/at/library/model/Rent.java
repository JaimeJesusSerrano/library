package com.at.library.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.at.library.enums.RentStatusEnum;

@Entity
public class Rent implements Serializable {

	private static final long serialVersionUID = 1560692319680192039L;
	
//	public Rent(){};
//	
//	public Rent(Book book, Date initDate, Employee employee, Client client, Date endDate, RentStatusEnum status) {
//		super();
//		this.rentPK.setBook(book);
//		this.rentPK.setInitDate(initDate);
//		this.employee = employee;
//		this.client = client;
//		this.endDate = endDate;
//		this.status = status;
//	}
//	
//	public Rent(Book book, Date initDate, Client client) {
//		super();
//		this.rentPK.setBook(book);
//		this.rentPK.setInitDate(initDate);
////		this.employee = employee;
//		this.client = client;
////		this.endDate = endDate;
////		this.status = status;
//	}

	@EmbeddedId
	private RentPK rentPK;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Employee employee;
	
	@OneToOne
	private Client client;
	
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@Enumerated(EnumType.STRING)
	private RentStatusEnum status;
	
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
		this.rentPK.setBook(book);
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

	public RentStatusEnum getStatus() {
		return status;
	}

	public void setStatus(RentStatusEnum status) {
		this.status = status;
	}
	
}
