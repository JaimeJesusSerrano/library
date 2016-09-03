package com.at.library.service.rent;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.at.exceptions.BookNotFoundException;
import com.at.exceptions.BookRentedException;
import com.at.exceptions.RentNotFoundException;
import com.at.exceptions.UserIsDisableException;
import com.at.exceptions.UserNotFoundException;
import com.at.library.dao.RentDao;
import com.at.library.dto.RentPostDTO;
import com.at.library.enums.BookStatusEnum;
import com.at.library.enums.RentStatusEnum;
import com.at.library.enums.UserStatusEnum;
import com.at.library.model.Book;
import com.at.library.model.Rent;
import com.at.library.model.RentPK;
import com.at.library.model.User;
import com.at.library.service.book.BookService;
import com.at.library.service.user.UserService;

@Service
public class RentServiceImpl implements RentService {
	
//	private static final Logger log = LoggerFactory.getLogger(RentServiceImpl.class);
	
	@Autowired
	private RentDao rentDao;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
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
			final RentPostDTO rentPostDTO = new RentPostDTO();
			rentPostDTO.setIdBook(rent.getBook().getId());
			rentPostDTO.setIdUser(rent.getUser().getId());
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
		final RentPostDTO rentPostDTO = new RentPostDTO();
		rentPostDTO.setIdBook(rent.getBook().getId());
		rentPostDTO.setIdUser(rent.getUser().getId());
		return rentPostDTO;
	}

	@Override
	public RentPostDTO create(RentPostDTO rentPostDTO) throws Exception {
		
		final Book book = bookService.getBookById(rentPostDTO.getIdBook());
		if (book == null) throw new BookNotFoundException();
		if (book.getStatus() == BookStatusEnum.RENTED) throw new BookRentedException(rentPostDTO.getIdBook());
		
		final User user = userService.getUserById(rentPostDTO.getIdUser());
		if (user == null) throw new UserNotFoundException();
		if (user.getStatus() == UserStatusEnum.DISABLE) throw new UserIsDisableException();
		
		RentPK rentPK = new RentPK();
		rentPK.setBook(book);
		rentPK.setInitDate(new Date());
		
		Rent rent = new Rent();
		rent.setRentPK(rentPK);
		rent.setUser(user);
		rent.setStatus(RentStatusEnum.ACTIVE);
		rentDao.save(rent);

		book.setStatus(BookStatusEnum.RENTED);
		bookService.update(book);
		
		RentPostDTO newRentPostDTO = new RentPostDTO();
		newRentPostDTO.setIdBook(rent.getBook().getId());
		newRentPostDTO.setIdUser(rent.getUser().getId());
		
		return newRentPostDTO;
	}

	@Override
	public void update(RentPostDTO rentPostDTO) {
		final Rent rent = transform(rentPostDTO);
		rentDao.save(rent);
	}

	@Override
	public void delete(Integer idBook) throws Exception {
		final Book book = bookService.getBookById(idBook);
		if (book == null) throw new BookNotFoundException();
		
		final Rent rent = rentDao.getRentOfBook(book);
		if (rent == null) throw new RentNotFoundException();
		
		rent.setStatus(RentStatusEnum.COMPLETED);
		rent.setEndDate(new Date());
		rentDao.save(rent);
		
		book.setStatus(BookStatusEnum.OK);
		bookService.update(book);		
	}
	
}
