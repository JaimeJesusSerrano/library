package com.at.exceptions;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 9059815153778805795L;

	public UserNotFoundException(){
		super("The user was not found");
	}
	
}
