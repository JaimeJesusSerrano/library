package com.at.library.service.book;

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
import org.springframework.web.client.RestTemplate;

import com.at.library.dao.BookDao;
import com.at.library.dto.BookGetDTO;
import com.at.library.dto.BookPostDTO;
import com.at.library.dto.ExternRentDTO;
import com.at.library.enums.BookStatusEnum;
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
	public Set<BookGetDTO> findAll(Map<String,String> requestParams) {
		
		final Iterable<Book> books;
		if (requestParams.isEmpty()) {
			books = bookDao.findAll();
		}
		else {
			books = search(requestParams);
		}
		
		final Iterator<Book> iteratorBooks = books.iterator();
		final Set<BookGetDTO> booksDTO = new HashSet<>();
		while (iteratorBooks.hasNext()) {
			final Book book = iteratorBooks.next();
			final BookGetDTO bookDTO = transform(book);
			log.debug(String.format("bookDTO search : %s", bookDTO));
			booksDTO.add(bookDTO);
		}
		return booksDTO;
	}
	
	private Set<Book> search(Map<String,String> requestParams) {
		String isbn = requestParams.get("isbn");
		String title = requestParams.get("title");
		String author = requestParams.get("author");
		
		return bookDao.search(isbn, title, author);
	}

	@Override
	public BookGetDTO transform(Book book) {
		return dozer.map(book, BookGetDTO.class);
	}

	@Override
	public Book transform(BookPostDTO bookPostDTO) {
		return dozer.map(bookPostDTO, Book.class);
	}

	@Override
	public BookGetDTO findById(Integer id) {
		final Book book = bookDao.findOne(id);
		return transform(book);
	}

	@Override
	public BookGetDTO create(BookPostDTO bookPostDTO) {
		final Book book = transform(bookPostDTO);
		book.setStatus(BookStatusEnum.valueOf("OK"));
		bookDao.save(book);
		
		BookGetDTO bookGetDTO = transform(book);
		return getAndSetExternalExtraData(bookGetDTO);
	}

	@Override
	public void update(BookPostDTO bookDTO) {
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
	public BookGetDTO getBookDTOById(Integer id) {
		return transform(getBookById(id));
	}

	@Override
	public Book getBookById(Integer id) {
		return bookDao.findOne(id);
	}
	
	private BookGetDTO getAndSetExternalExtraData(BookGetDTO bookGetDTO) {
//		https://www.googleapis.com/books/v1/volumes?q=
		final String url = "https://www.googleapis.com/books/v1/volumes?q="+ bookGetDTO.getTitle();
//		GoogleBookDTO googleBookDTO = new RestTemplate().getForObject(url, BookGetDTO.class);
//		externRentsDTO = restTemplate.getForObject(url, ExternRentDTO[].class);
		
//		bookGetDTO.setYear(year);
//		bookGetDTO.setImage(image);
//		bookGetDTO.setDescription(description);
		
		return bookGetDTO;
	}

}
