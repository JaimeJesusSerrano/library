package com.at.library.service.book;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.at.library.dao.BookDao;
import com.at.library.dto.BookDTO;
import com.at.library.enums.StatusEnum;
import com.at.library.model.Book;

@Service
public class BookServiceImpl implements BookService {
	
	private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
	private BookDao bookDao;

	@Autowired
	private DozerBeanMapper dozer;

	@Override
	@Transactional(readOnly = true)
	public Set<BookDTO> findAll() {
		final Iterable<Book> findAll = bookDao.findAll();
		final Iterator<Book> iterator = findAll.iterator();
		final Set<BookDTO> res = new HashSet<>();
		while (iterator.hasNext()) {
			final Book b = iterator.next();
			final BookDTO bDTO = transform(b);
			res.add(bDTO);
		}
		return res;
	}

	@Override
	public BookDTO transform(Book book) {
		return dozer.map(book, BookDTO.class);
	}

	@Override
	public Book transform(BookDTO bookDTO) {
		return dozer.map(bookDTO, Book.class);
	}

	@Override
	public BookDTO findById(Integer id) {
		final Book book = bookDao.findOne(id);
		return transform(book);
	}

	@Override
	public BookDTO create(BookDTO bookDTO) {
		final Book book = transform(bookDTO);
		book.setStartDate(new Date());
		book.setStatus(StatusEnum.valueOf("ACTIVE"));
		return transform(bookDao.save(book));
	}

	@Override
	public void update(BookDTO bookDTO) {
		final Book book = transform(bookDTO);
		transform(bookDao.save(book));
	}

	@Override
	public void delete(Integer id) {
//		We need aded expetion to controller to avoid not exist
		Integer numberOfTimesRented = numberOfTimesRented(id);
		log.debug(String.format("Number of times rented the book with id %s : %s", id, numberOfTimesRented));
		if (numberOfTimesRented == 0) {
			bookDao.delete(id);
		}
	}

	@Override
	public Integer numberOfTimesRented(Integer id) {
		Book book = bookDao.findOne(id);
		return bookDao.hasBeenUsed(book);
	}

}
