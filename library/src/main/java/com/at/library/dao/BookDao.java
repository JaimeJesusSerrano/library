package com.at.library.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.at.library.model.Book;

@Repository
public interface BookDao extends CrudRepository<Book, Integer> {

//	(:isbn is NULL OR book.isbn like %:isbn%)
	@Query(value = "SELECT book FROM Book AS book WHERE book.isbn like %:isbn% OR book.title like %:title%")
	public Set<Book> search(
			@Param("isbn") String isbn,
			@Param("title") String title
			);

	@Query(value = "SELECT COUNT(rent) FROM Rent AS rent WHERE rent.rentPK.book = :book")
	public Integer hasBeenUsed(@Param("book") Book book);
	
}
