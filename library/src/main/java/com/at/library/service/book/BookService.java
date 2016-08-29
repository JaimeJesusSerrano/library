package com.at.library.service.book;

import java.util.Map;
import java.util.Set;

import com.at.library.dto.BookDTO;
import com.at.library.model.Book;

public interface BookService {

	/**
	 * Get all books of the system
	 * 
	 * @param optionals requestParams to search
	 * @return set of books
	 */
	public Set<BookDTO> findAll(Map<String,String> requestParams);

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
	
	/**
	 * Find book by id
	 * 
	 * @param id
	 * @return bookDTO
	 */
	public BookDTO findById(Integer id);
	
	/**
	 * Create a book
	 * 
	 * @param bookDTO
	 * @return bookDTO created
	 */
	public BookDTO create(BookDTO bookDTO);
	
	/**
	 * Update a book
	 * 
	 * @param bookDTO
	 */
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
	
	/**
	 * Get bookDTO by id
	 * 
	 * @param id
	 * @return BookDTO
	 */
	public BookDTO getBookDTOById(Integer id);
	
	/**
	 * Get book by id
	 * 
	 * @param id
	 * @return Book
	 */
	public Book getBookById(Integer id);
	
}
