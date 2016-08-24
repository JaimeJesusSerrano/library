package com.at.library.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Client extends User {

	private static final long serialVersionUID = -24600065830793754L;
	
	private Rent rent;

	@ManyToOne(fetch = FetchType.LAZY)
	public Rent getRent() {
		return rent;
	}

	public void setRent(Rent rent) {
		this.rent = rent;
	}

}
