package com.at.library.service.book;

import java.util.Map;
import java.util.Set;

import com.at.library.dto.BookGetDTO;
import com.at.library.dto.BookPostDTO;
import com.at.library.dto.HistoryRentedDTO;
import com.at.library.model.Book;

public interface BookService {

	/**
	 * Get all books of the system
	 * 
	 * @param optionals requestParams to search
	 * @return set of books
	 */
	public Set<BookGetDTO> findAll(Map<String,String> requestParams) throws Exception;

	/**
	 * Transform Book to BookGetDTO
	 * 
	 * @param Book
	 * @return BookGetDTO
	 */
	public BookGetDTO transform(Book book);

	/**
	 * Transform BookPostDTO to Book
	 * 
	 * @param BookPostDTO
	 * @return Book
	 */
	public Book transform(BookPostDTO bookPostDTO);
	
	/**
	 * Find book by id
	 * 
	 * @param id
	 * @return bookDTO
	 */
	public BookGetDTO findById(Integer id) throws Exception;
	
	/**
	 * Create a book
	 * 
	 * @param bookDTO
	 * @return bookDTO created
	 */
	public BookGetDTO create(BookPostDTO bookPostDTO) throws Exception;
	
	/**
	 * Update a book
	 * 
	 * @param bookId
	 * @param BookPostDTO
	 */
	public void update(Integer bookId, BookPostDTO bookPostDTO) throws Exception;
	
	/**
	 * Update a book
	 * 
	 * @param Book
	 */
	public void update(Book book) throws Exception;
	
	/**
	 * Delete a book that never has been rented
	 * 
	 * @param id
	 */
	public void delete(Integer id) throws Exception;
	
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
	
	/**
	 * Get history rented of book
	 * 
	 * @param id
	 * @return Set<HistoryRentedDTO>
	 */
	public Set<HistoryRentedDTO> getHistoryRented(Integer bookId);
	
}
