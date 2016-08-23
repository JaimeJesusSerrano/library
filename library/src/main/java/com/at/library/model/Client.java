package com.at.library.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Client extends User {

	private static final long serialVersionUID = -24600065830793754L;
	
	private Booking booking;

	@ManyToOne(fetch = FetchType.LAZY)
	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

}
