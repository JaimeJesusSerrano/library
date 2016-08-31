package com.at.library.dto;

public class RentPostDTO extends DTO {

	private static final long serialVersionUID = 8455358489135351607L;

	private Integer idBook;
	
	private Integer idUser;

	public Integer getIdBook() {
		return idBook;
	}

	public void setIdBook(Integer idBook) {
		this.idBook = idBook;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	@Override
	public String toString() {
		return "RentPostDTO [idBook=" + idBook + ", idUser=" + idUser + "]";
	}

}
