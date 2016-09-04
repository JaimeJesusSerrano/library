package com.at.library.dao;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.at.library.model.Book;
import com.at.library.model.Rent;

@Repository
public interface BookDao extends CrudRepository<Book, Integer> {

//	(:isbn is NULL OR book.isbn like %:isbn%)
	@Query(value = "SELECT book FROM Book AS book WHERE (book.isbn like %:isbn% OR :isbn is NULL) AND (book.title like %:title% OR :title is NULL)")
	public Page<Book> findAll(
			Pageable pageable,
			@Param("isbn") String isbn,
			@Param("title") String title
			);


	@Query(value = "SELECT COUNT(rent) FROM Rent AS rent WHERE rent.rentPK.book = :book")
	public Integer hasBeenUsed(@Param("book") Book book);
	
	@Query(value = "SELECT rent FROM Rent AS rent WHERE rent.rentPK.book = :book")
	public Set<Rent> getRentsOfBook(@Param("book") Book book);
}
