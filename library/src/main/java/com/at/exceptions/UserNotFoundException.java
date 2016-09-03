package com.at.exceptions;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 9059815153778805795L;

	public UserNotFoundException(Integer id){
		super("The user with id "+ id +" was not found");
	}
	
}
