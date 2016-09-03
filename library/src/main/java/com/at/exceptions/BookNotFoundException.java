package com.at.exceptions;

public class BookNotFoundException extends Exception {

	private static final long serialVersionUID = -419542501508191144L;

	public BookNotFoundException(Integer id) {
		super("The book with id "+ id +" was not found");
	}
	
}
