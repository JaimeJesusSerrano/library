package com.at.library.dto;

public class ExternRentDTO extends DTO {

	private static final long serialVersionUID = 1508948806250109607L;

	private String init;
	
	private String endDate;
	
	private BookDTO bookDTO;

	public String getInit() {
		return init;
	}

	public void setInit(String init) {
		this.init = init;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public BookDTO getBookDTO() {
		return bookDTO;
	}

	public void setBook(BookDTO bookDTOP) {
		this.bookDTO = bookDTOP;
	}

	@Override
	public String toString() {
		return "ExternRentDTO [init=" + init + ", endDate=" + endDate + ", book=" + bookDTO + "]";
	}
	
}
