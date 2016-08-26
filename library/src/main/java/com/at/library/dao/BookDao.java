package com.at.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.model.Book;

@Repository
public interface BookDao extends CrudRepository<Book, Integer> {

//	@Query (value="SELECT new com.at.library.dto.BookDTO(b.id, b.title, b.author) FROM Book AS b WHERE b.id IN (SELECT r.pk.book FROM Rent AS r WHERE r.endDate IS null)")
//public List<BookDTO> findUnavailable();
}
