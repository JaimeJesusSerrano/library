package com.at.library.dto;

public class RentPostDTO extends DTO {

	private static final long serialVersionUID = 8455358489135351607L;

	private Integer idBook;
	
	private Integer idClient;
	
	private Integer idEmployee;

	public Integer getIdBook() {
		return idBook;
	}

	public void setIdBook(Integer idBook) {
		this.idBook = idBook;
	}

	public Integer getIdClient() {
		return idClient;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	public Integer getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(Integer idEmployee) {
		this.idEmployee = idEmployee;
	}

	@Override
	public String toString() {
		return "RentPostDTO [idLibro=" + idBook + ", idClient=" + idClient + ", idEmployee=" + idEmployee + "]";
	}
	
}
