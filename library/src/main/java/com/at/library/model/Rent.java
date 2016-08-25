package com.at.library.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Rent implements Serializable {

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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "rent")
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
}
