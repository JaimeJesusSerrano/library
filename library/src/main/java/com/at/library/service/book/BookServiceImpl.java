package com.at.library.service.book;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
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
	public Set<BookDTO> findAll(Map<String,String> requestParams) {
		
		final Iterable<Book> books;
		if (requestParams.isEmpty()) {
			books = bookDao.findAll();
		}
		else {
			books = search(requestParams);
		}
		
		final Iterator<Book> iteratorBooks = books.iterator();
		final Set<BookDTO> BooksDTO = new HashSet<>();
		while (iteratorBooks.hasNext()) {
			final Book book = iteratorBooks.next();
			final BookDTO bookDTO = transform(book);
			log.debug(String.format("bookDTO search : %s", bookDTO));
			BooksDTO.add(bookDTO);
		}
		return BooksDTO;
	}
	
	private Set<Book> search(Map<String,String> requestParams) {
		String isbn = requestParams.get("isbn");
		String title = requestParams.get("title");
		String author = requestParams.get("author");
		
		return bookDao.search(isbn, title, author);
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
//		We need added exception to verify the options
		final Book book = transform(bookDTO);
		transform(bookDao.save(book));
	}

	@Override
	public void delete(Integer id) {
//		We need added exception to controller to avoid not exist
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

	@Override
	public BookDTO getBookDTOById(Integer id) {
		return transform(getBookById(id));
	}

	@Override
	public Book getBookById(Integer id) {
		return bookDao.findOne(id);
	}

}
