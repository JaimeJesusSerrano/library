package com.at.library.dto;

public class ClientDTO extends DTO {

	private static final long serialVersionUID = -1541813734126064124L;
	
	private String id;
	
	private String name;
	
	private String surname;
	
	private String dni;

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "ClientDTO [id=" + id + ", name=" + name + ", surname=" + surname + ", dni=" + dni + "]";
	}

}
