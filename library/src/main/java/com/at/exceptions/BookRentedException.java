package com.at.exceptions;

public class BookRentedException extends Exception {

	private static final long serialVersionUID = 2848097821571891205L;

	public BookRentedException(Integer id) {
		super("The book with id "+ id +" is rented");
	}
	
}
