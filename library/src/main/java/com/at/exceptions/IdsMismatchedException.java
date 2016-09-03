package com.at.exceptions;

public class IdsMismatchedException extends Exception {

	private static final long serialVersionUID = 8730211946602780569L;
	
	public IdsMismatchedException(Integer id1, Integer id2) {
		super("The ids "+ id1 +" and "+ id2 +" mismatched");
	}

}
