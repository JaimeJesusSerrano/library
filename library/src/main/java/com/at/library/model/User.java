package com.at.library.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.at.library.enums.StatusEnum;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Client extends User {

	private static final long serialVersionUID = -24600065830793754L;
	
	@Enumerated(EnumType.STRING)
	private StatusEnum status;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
	private List<Rent> rents;

	public List<Rent> getRents() {
		return rents;
	}

	public void setRents(List<Rent> rents) {
		this.rents = rents;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

}
