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

import com.at.library.dao.RentDao;
import com.at.library.dto.RentPostDTO;
import com.at.library.model.Book;
import com.at.library.model.User;
import com.at.library.model.Rent;
import com.at.library.service.book.BookService;
import com.at.library.service.user.UserService;

@Service
public class RentServiceImpl implements RentService {
	
	private static final Logger log = LoggerFactory.getLogger(RentServiceImpl.class);
	
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
			final RentPostDTO rentPostDTO = transform(rent);
			rentsPostDTO.add(rentPostDTO);
		}
		return rentsPostDTO;
	}

	@Override
	public RentPostDTO transform(Rent rent) {
//		return dozer.map(rent, RentPostDTO.class);
		RentPostDTO rentPostDTO = new RentPostDTO();
		rentPostDTO.setIdBook(rent.getBook().getId());
		rentPostDTO.setIdUser(rent.getUser().getId());
		
		return rentPostDTO;
	}

	@Override
	public Rent transform(RentPostDTO rentPostDTO) {
//		return dozer.map(rentPostDTO, Rent.class);	
		final Book book = bookService.getBookById(rentPostDTO.getIdBook());
		final User user = userService.getUserById(rentPostDTO.getIdUser());
		
		Rent rent = new Rent();
		rent.setBook(book);
		rent.setUser(user);
		
		return rent;
	}

	@Override
	public RentPostDTO findById(Integer id) {
		Rent rent = rentDao.findOne(id);
		return transform(rent);
	}

	@Override
	public RentPostDTO create(RentPostDTO rentPostDTO) {
		
		Rent rent = transform(rentPostDTO);
		rent.setInitDate(new Date());
		
		return transform(rentDao.save(rent));
	}

	@Override
	public void update(RentPostDTO rentPostDTO) {
		final Rent rent = transform(rentPostDTO);
		rentDao.save(rent);
	}
	
}
