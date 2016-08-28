package com.at.library.service.rent;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.at.library.dao.BookDao;
import com.at.library.dao.ClientDao;
import com.at.library.dao.RentDao;
import com.at.library.dto.RentPostDTO;
import com.at.library.model.Book;
import com.at.library.model.Client;
import com.at.library.model.Rent;

@Service
public class RentServiceImpl implements RentService {
	
	@Autowired
	private RentDao rentDao;
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private ClientDao clientDao;
	
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
		
		Book book = bookDao.findOne(rentPostDTO.getIdBook());
		Client client = clientDao.findOne(rentPostDTO.getIdClient());
		
		rent.setBook(book);
		rent.setClient(client);
		
		final Date date = new Date();
//		final Calendar calendar = Calendar.getInstance();
		
		rent.setInitDate(date);
//		calendar.setTime(date);
//        calendar.add(Calendar.DAY_OF_YEAR, 3);
//        final Date endDate = calendar.getTime();
//		rent.setEndDate(endDate);
		
		return transform(rentDao.save(rent));
	}

}
