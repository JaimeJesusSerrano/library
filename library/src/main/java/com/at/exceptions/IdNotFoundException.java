package com.at.exceptions;

public class IdNotFoundException extends Exception {

	private static final long serialVersionUID = -4433671458893726911L;

	public IdNotFoundException(){
		super("The id was not found");
	}
	
}
