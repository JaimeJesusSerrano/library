package com.at.library.service.book;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.at.exceptions.BookNotFoundException;
import com.at.exceptions.IdsMismatchedException;
import com.at.library.dao.BookDao;
import com.at.library.dto.BookGetDTO;
import com.at.library.dto.BookPostDTO;
import com.at.library.dto.HistoryRentedDTO;
import com.at.library.enums.BookStatusEnum;
import com.at.library.model.Book;
import com.at.library.model.Rent;

@Service
public class BookServiceImpl implements BookService {
	
//	private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
	private BookDao bookDao;

	@Autowired
	private DozerBeanMapper dozer;

	@Override
	@Transactional(readOnly = true)
	public Set<BookGetDTO> findAll(Map<String,String> requestParams) throws Exception {
		
		final Iterable<Book> books;
		if (requestParams.isEmpty()) {
			books = bookDao.findAll();
		}
		else {
			books = search(requestParams);
		}
		
		final Iterator<Book> iteratorBooks = books.iterator();
		final Set<BookGetDTO> booksGetDTO = new HashSet<>();
		while (iteratorBooks.hasNext()) {
			final Book book = iteratorBooks.next();
			final BookGetDTO bookGetDTO = transform(book);
			getAndSetExternalExtraData(bookGetDTO);
			booksGetDTO.add(bookGetDTO);
		}
		return booksGetDTO;
	}
	
	private Set<Book> search(Map<String,String> requestParams) {
		String isbn = requestParams.get("isbn");
		String title = requestParams.get("title");
		
		return bookDao.search(isbn, title);
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
	public BookGetDTO findById(Integer id) throws Exception {
		final Book book = bookDao.findOne(id);
		BookGetDTO bookGetDTO = transform(book);
		return getAndSetExternalExtraData(bookGetDTO);
	}

	@Override
	public BookGetDTO create(BookPostDTO bookPostDTO) throws Exception {
		final Book book = transform(bookPostDTO);
		book.setStatus(BookStatusEnum.valueOf("OK"));
		
		bookDao.save(book);
		BookGetDTO bookGetDTO = transform(book);
		getAndSetExternalExtraData(bookGetDTO);
		
		return bookGetDTO;
	}

	@Override
	public void update(Integer bookId, BookPostDTO bookPostDTO) throws Exception {
		if (bookPostDTO.getId() == null || bookId != bookPostDTO.getId()) throw new IdsMismatchedException(bookId, bookPostDTO.getId());
		
		final Book book = bookDao.findOne(bookId);
		if (book == null) throw new BookNotFoundException(bookId);
		
		if (bookPostDTO.getAuthor() != null) {book.setAuthor(bookPostDTO.getAuthor());}
		if (bookPostDTO.getIsbn() != null) {book.setIsbn(bookPostDTO.getIsbn());}
		if (bookPostDTO.getTitle() != null) {book.setAuthor(bookPostDTO.getTitle());}
		transform(bookDao.save(book));
	}

	@Override
	public void delete(Integer id) throws Exception {
		final Book book = bookDao.findOne(id);
		if (book == null) throw new BookNotFoundException(id);
		if (numberOfTimesRented(id) == 0) {
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
	
	private BookGetDTO getAndSetExternalExtraData(BookGetDTO bookGetDTO) throws Exception {
		final String url = "https://www.googleapis.com/books/v1/volumes?maxResults=1&q="+ bookGetDTO.getTitle();
		String externalStringJSON = new RestTemplate().getForObject(url, String.class);	
		JSONObject externalJsonObjects  = new JSONObject(externalStringJSON);
		
		String description = "";
		String image = "";
		String year = "";
		
		if (externalJsonObjects.getInt("totalItems") != 0) {
			JSONObject externalJsonObject = externalJsonObjects.getJSONArray("items").getJSONObject(0);
			
			if (externalJsonObject.getJSONObject("volumeInfo").has("description")) {
				description = externalJsonObject.getJSONObject("volumeInfo").getString("description");
			}
			
			if (externalJsonObject.getJSONObject("volumeInfo").getJSONObject("imageLinks").has("thumbnail")) {
				image = externalJsonObject.getJSONObject("volumeInfo").getJSONObject("imageLinks").getString("thumbnail");
			}
			
			if (externalJsonObject.getJSONObject("volumeInfo").has("publishedDate")) {
				year = externalJsonObject.getJSONObject("volumeInfo").getString("publishedDate");
			}
			
			if (year.contains("-")) {
				String segments[] = year.split("-");
				year = segments[0];
			}
			
			bookGetDTO.setYear(Integer.parseInt(year));
		}

		bookGetDTO.setImage(image);
		bookGetDTO.setDescription(description);
		
		return bookGetDTO;
	}

	@Override
	public Set<HistoryRentedDTO> getHistoryRented(Integer bookId) {
		final Book book = bookDao.findOne(bookId);
		final Set<Rent> rents = bookDao.getRentsOfBook(book);
		
		
		final Iterator<Rent> iteratorRents = rents.iterator();
		final Set<HistoryRentedDTO> historiesRentedDTO = new HashSet<>();
		while (iteratorRents.hasNext()) {
			final Rent rent = iteratorRents.next();
			final HistoryRentedDTO historyRentedDTO = new HistoryRentedDTO();
			historyRentedDTO.setIdBook(rent.getBook().getId());
			historyRentedDTO.setInit(rent.getInitDate());
			historyRentedDTO.setTitle(rent.getBook().getTitle());
			historyRentedDTO.setEnd(rent.getEndDate());
			historiesRentedDTO.add(historyRentedDTO);
		}
		 
		return historiesRentedDTO;
	}

}
