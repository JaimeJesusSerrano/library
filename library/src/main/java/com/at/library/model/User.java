package com.at.library.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.at.library.enums.UserStatusEnum;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class User extends Person {

	private static final long serialVersionUID = -24600065830793754L;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Rent> rents;
	
	@Enumerated(EnumType.STRING)
	private UserStatusEnum status;
	
	public List<Rent> getRents() {
		return rents;
	}

	public void setRents(List<Rent> rents) {
		this.rents = rents;
	}

	public UserStatusEnum getStatus() {
		return status;
	}

	public void setStatus(UserStatusEnum status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [rents=" + rents + ", status=" + status + "]";
	}

}
