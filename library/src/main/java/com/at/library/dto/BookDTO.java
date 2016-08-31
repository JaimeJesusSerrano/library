package com.at.library.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.at.library.enums.BookStatusEnum;

public class BookDTO extends DTO {

	private static final long serialVersionUID = 1583585532736761521L;

	private Integer id;

	private String isbn;

	private String title;

	private String author;
	
	@Enumerated(EnumType.STRING)
	private BookStatusEnum status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
		public BookStatusEnum getStatus() {
		return status;
	}

	public void setStatus(BookStatusEnum status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BookDTO [id=" + id + ", isbn=" + isbn + ", title=" + title + ", author=" + author + ", status=" + status
				+ "]";
	}

}
