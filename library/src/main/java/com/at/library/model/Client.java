package com.at.library.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Client extends User {

	private static final long serialVersionUID = -24600065830793754L;
	
	private Rent booking;

	@ManyToOne(fetch = FetchType.LAZY)
	public Rent getBooking() {
		return booking;
	}

	public void setBooking(Rent booking) {
		this.booking = booking;
	}

}
