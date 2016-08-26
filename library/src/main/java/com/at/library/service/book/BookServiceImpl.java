package com.at.library.service.book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.at.library.controller.BookController;
import com.at.library.dao.BookDao;
import com.at.library.dto.BookDTO;
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
	public List<BookDTO> findAll() {
		BookDTO book1 = new BookDTO();
		book1.setIsbn("00001");
		book1.setTitle("La pata coja");
		book1.setAuthor("Yo mismo");
		Book book2 = transform(book1);
		bookDao.save(book2);
		
		
		final Iterable<Book> findAll = bookDao.findAll();
		final Iterator<Book> iterator = findAll.iterator();
		final List<BookDTO> res = new ArrayList<>();
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
	public Book transform(BookDTO book) {
		return dozer.map(book, Book.class);
	}

	@Override
	public BookDTO findById(Integer id) {
		final Book book = bookDao.findOne(id);
		return transform(book);
	}

	@Override
	public BookDTO create(BookDTO bookDTO) {
		final Book book = transform(bookDTO);
		return transform(bookDao.save(book));
	}

	@Override
	public void update(BookDTO bookDTO) {
		final Book book = transform(bookDTO);
		transform(bookDao.save(book));
	}

	@Override
	public void delete(Integer id) {
		Boolean isit = hasBeenUsed(id);
		log.debug(String.format("Buscando %s", isit));
		if (hasBeenUsed(id)) {
			bookDao.delete(id);
		}
	}

	@Override
	public boolean hasBeenUsed(Integer id) {
		return bookDao.hasBeenUsed(id);
	}

}
