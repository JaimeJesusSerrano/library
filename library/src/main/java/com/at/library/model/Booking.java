package com.at.library.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Booking implements Serializable {

	private static final long serialVersionUID = 1560692319680192039L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@GeneratedValue
	private String reservationNumber;
}
