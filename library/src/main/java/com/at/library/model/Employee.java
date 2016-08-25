package com.at.library.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Employee extends User {

	private static final long serialVersionUID = 3926211548637428563L;

}
