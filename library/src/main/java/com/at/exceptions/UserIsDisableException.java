package com.at.exceptions;

public class UserIsDisableException extends Exception {

	private static final long serialVersionUID = -4696427300895831008L;

	public UserIsDisableException() {
		super("The user is disable");
	}
	
}
