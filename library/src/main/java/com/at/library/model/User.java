package com.at.library.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class User extends Person {

	private static final long serialVersionUID = -24600065830793754L;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Rent> rents;

	public List<Rent> getRents() {
		return rents;
	}

	public void setRents(List<Rent> rents) {
		this.rents = rents;
	}

}
