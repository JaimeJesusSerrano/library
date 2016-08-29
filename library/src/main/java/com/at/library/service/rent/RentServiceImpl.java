package com.at.library.service.rent;

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
import com.at.library.dao.ClientDao;
import com.at.library.dao.RentDao;
import com.at.library.dto.BookDTO;
import com.at.library.dto.ClientDTO;
import com.at.library.dto.RentPostDTO;
import com.at.library.enums.StatusEnum;
import com.at.library.model.Book;
import com.at.library.model.Client;
import com.at.library.model.Rent;
import com.at.library.service.book.BookService;
import com.at.library.service.book.BookServiceImpl;
import com.at.library.service.client.ClientService;

@Service
public class RentServiceImpl implements RentService {
	
	private static final Logger log = LoggerFactory.getLogger(RentServiceImpl.class);
	
	@Autowired
	private RentDao rentDao;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private DozerBeanMapper dozer;

	@Override
	@Transactional(readOnly = true)
	public Set<RentPostDTO> findAll() {
		final Iterable<Rent> rents = rentDao.findAll();
		final Iterator<Rent> iteratorRents = rents.iterator();
		final Set<RentPostDTO> rentsPostDTO = new HashSet<>();
		while(iteratorRents.hasNext()) {
			final Rent rent = iteratorRents.next();
			final RentPostDTO rentPostDTO = transform(rent);
			rentsPostDTO.add(rentPostDTO);
		}
		return rentsPostDTO;
	}

	@Override
	public RentPostDTO transform(Rent rent) {
		return dozer.map(rent, RentPostDTO.class);
	}

	@Override
	public Rent transform(RentPostDTO rentPostDTO) {
		return dozer.map(rentPostDTO, Rent.class);
	}

	@Override
	public RentPostDTO findById(Integer id) {
		Rent rent = rentDao.findOne(id);
		return transform(rent);
	}

	@Override
	public RentPostDTO create(RentPostDTO rentPostDTO) {
		final Rent rent = transform(rentPostDTO);
		log.debug(String.format("RentDTS %s : ", rentPostDTO));
		log.debug(String.format("Rent %s : ", rent));
		


		
		
//		final BookDTO bookDTO = bookService.findById(rentPostDTO.getIdBook());
//		final Book book = bookService.transform(bookDTO);
		final Book book = bookService.getBookById(rentPostDTO.getIdBook());
		final BookDTO bookDTO = bookService.transform(book);
		log.debug(String.format("Book %s : ", book));
		log.debug(String.format("BookDTO %s : ", bookDTO));
		Rent rent2 = new Rent();
		rent2.setBook(book);
		log.debug(String.format("Book %s : ", book));
//		if (book == null || book.getStatus().equals(StatusEnum.DISABLE)) {
//			Throw exception
//		}
//		final ClientDTO clientDTO = clientService.findById(rentPostDTO.getIdClient());
//		final Client client = clientService.transform(clientDTO);
		final Client client = clientService.getClientById(rentPostDTO.getIdClient());
		log.debug(String.format("Client %s : ", client));
		
//		rent.setBook(book);
//		rent.setClient(client);
		rent.setInitDate(new Date());
		
		return transform(rentDao.save(rent));
		
//		log.debug(String.format("RentDTS %s : ", rentPostDTO));
//
//		
//		final Book book = bookService.getBookById(rentPostDTO.getIdBook());
//		final Date initDate = new Date();
//		final Client client = clientService.getClientById(rentPostDTO.getIdClient());
//
//		Rent rent = new Rent(book, initDate, client);
//		log.debug(String.format("Rent %s : ", rent));
//
//		return transform(rentDao.save(rent));
	}

	@Override
	public void update(RentPostDTO rentPostDTO) {
		final Rent rent = transform(rentPostDTO);
		rentDao.save(rent);
	}

}
