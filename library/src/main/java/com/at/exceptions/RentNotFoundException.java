package com.at.exceptions;

public class RentNotFoundException extends Exception {

	private static final long serialVersionUID = -9011595554383296825L;

	public RentNotFoundException() {
		super("The rent was not found");
	}
	
}
