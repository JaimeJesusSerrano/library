package com.at.library.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class RentId implements Serializable {
	
	private static final long serialVersionUID = -7738526754452207141L;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date initDate;

	@OneToOne
	private Book book;
	
	public Date getInitDate() {
		return initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
}
