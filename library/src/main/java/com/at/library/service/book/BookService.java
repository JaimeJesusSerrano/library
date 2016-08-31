package com.at.library.service.book;

import java.util.Map;
import java.util.Set;

import com.at.library.dto.BookGetDTO;
import com.at.library.dto.BookPostDTO;
import com.at.library.model.Book;

public interface BookService {

	/**
	 * Get all books of the system
	 * 
	 * @param optionals requestParams to search
	 * @return set of books
	 */
	public Set<BookGetDTO> findAll(Map<String,String> requestParams);

	/**
	 * Transform book to bookPostDTO
	 * 
	 * @param book
	 * @return bookPostDTO
	 */
	public BookGetDTO transform(Book book);

	/**
	 * Transform bookPostDTO to book
	 * 
	 * @param bookPostDTO
	 * @return book
	 */
	public Book transform(BookPostDTO bookPostDTO);
	
	/**
	 * Find book by id
	 * 
	 * @param id
	 * @return bookDTO
	 */
	public BookGetDTO findById(Integer id);
	
	/**
	 * Create a book
	 * 
	 * @param bookDTO
	 * @return bookDTO created
	 */
	public BookGetDTO create(BookPostDTO bookPostDTO);
	
	/**
	 * Update a book
	 * 
	 * @param bookDTO
	 */
	public void update(BookPostDTO bookDTO);
	
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
	public BookGetDTO getBookDTOById(Integer id);
	
	/**
	 * Get book by id
	 * 
	 * @param id
	 * @return Book
	 */
	public Book getBookById(Integer id);
	
}
