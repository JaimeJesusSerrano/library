package com.at.library.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.at.library.model.Book;
import com.at.library.model.Rent;

@Repository
public interface RentDao extends CrudRepository<Rent, Integer> {

	@Query(value = "SELECT rent FROM Rent AS rent WHERE rent.rentPK.book = :book")
	public Rent getRentOfBook(@Param("book") Book book);
	
}

