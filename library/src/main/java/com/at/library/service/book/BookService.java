package com.at.library.service.book;

import java.util.List;

import com.at.library.dto.BookDTO;
import com.at.library.model.Book;

public interface BookService {

	/**
	 * Realiza la busqueda de todos los libros existentes
	 * 
	 * @return book list
	 */
	public List<BookDTO> findAll();

	/**
	 * Transform book to bookDTO
	 * 
	 * @param book
	 * @return bookDTO
	 */
	public BookDTO transform(Book book);

	/**
	 * Transform bookDTO to book
	 * 
	 * @param bookDTO
	 * @return book
	 */
	public Book transform(BookDTO bookDTO);
	
	public BookDTO findById(Integer id);
	
	/**
	 * Create a book
	 * 
	 * @param bookDTO
	 * @return
	 */
	public BookDTO create(BookDTO bookDTO);
	
	public void update(BookDTO bookDTO);
	
	/**
	 * Delete a book that never has been rented
	 * 
	 * @param id
	 */
	public void delete(Integer id);
	
	/**
	 * Number of times that a book has been rented
	 * 
	 * @param id
	 * @return number of times rented
	 */
	public Integer numberOfTimesRented(Integer id);

}
