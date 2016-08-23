package com.at.library.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Booking implements Serializable {

	private static final long serialVersionUID = 1560692319680192039L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@GeneratedValue
	private String reservationNumber;
	
	private Client client;
	
	private Book book;

	public String getReservationNumber() {
		return reservationNumber;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "booking")
	public Client getClient() {
		return client;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "booking")
	public void setClient(Client client) {
		this.client = client;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
}
