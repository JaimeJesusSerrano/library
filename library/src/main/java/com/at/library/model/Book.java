package com.at.library.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.at.library.enums.BookStatusEnum;

@Entity
public class Book implements Serializable {

	private static final long serialVersionUID = 6374272004167410735L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	private String isbn;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String author;

	@Enumerated(EnumType.STRING)
	private BookStatusEnum status;
	
	@OneToOne(optional = true)
	private Bookshelf bookshelf;

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

	public Bookshelf getBookshelf() {
		return bookshelf;
	}

	public void setBookshelf(Bookshelf bookshelf) {
		this.bookshelf = bookshelf;
	}

}
