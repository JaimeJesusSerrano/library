package com.at.library.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Bookshelf implements Serializable {

	private static final long serialVersionUID = -7067308147736914174L;
	
	@Id
	private String code;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bookshelf")
	private List<Book> books = new ArrayList<>();
	
	@OneToOne
	private Room room;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}
