package com.at.library.service.book;

import java.util.List;

import com.at.library.dto.BookDTO;
import com.at.library.model.Book;

public interface BookService {

	/**
	 * Realiza la busqueda de todos los libros existentes
	 * 
	 * @return listado de libros
	 */
	public List<BookDTO> findAll();

	/**
	 * Transfrma un libro en un libroDTO
	 * 
	 * @param book
	 * @return
	 */
	public BookDTO transform(Book book);

	/**
	 * Transforma un libroDTO en un libro
	 * 
	 * @param book
	 * @return
	 */
	public Book transform(BookDTO book);
	
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
	 * Delete a book never rented
	 * 
	 * @param id
	 */
	public void delete(Integer id);
	
	/**
	 * A book has been used
	 * 
	 * @param id
	 * @return
	 */
	public boolean hasBeenUsed(Integer id);

}
