package com.at.library.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.at.library.model.Book;

@Repository
public interface BookDao extends CrudRepository<Book, Integer> {

//	@Query (value="SELECT new com.at.library.dto.BookDTO(b.id, b.title, b.author) FROM Book AS b WHERE b.id IN (SELECT r.pk.book FROM Rent AS r WHERE r.endDate IS null)")
//public List<BookDTO> findUnavailable();

	@Query(value = "SELECT COUNT(rent) FROM Rent AS rent WHERE rent.rentPK.book = :book")
	public Integer hasBeenUsed(@Param("book") Book book);
}
