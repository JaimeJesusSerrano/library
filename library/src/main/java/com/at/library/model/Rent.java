package com.at.library.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
	
	private Set<Book> books;

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

	@ManyToMany(fetch = FetchType.LAZY, targetEntity = Book.class, mappedBy = "rents")
	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	
}
