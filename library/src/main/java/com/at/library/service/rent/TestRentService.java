package com.at.library.service.rent;

import java.util.Date;

import org.dozer.DozerBeanMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.at.library.dao.RentDao;
import com.at.library.dto.RentPostDTO;
import com.at.library.model.Rent;

@RunWith(MockitoJUnitRunner.class)
public class TestRentService {
	
	private static final Date INITDATE = new Date();
	
	private Rent rent = new Rent();
	
	@InjectMocks
	final RentService rentService = new RentServiceImpl();
	
	@Mock
	private DozerBeanMapper dozer;
	
	@Mock
	private RentDao rentDao;
	
	@Before
	public void init() {
		final RentPostDTO rentPostDTO = new RentPostDTO();
		rent.setInitDate(INITDATE);
		Mockito.when(dozer.map(rent, RentPostDTO.class)).thenReturn(rentPostDTO);
		Mockito.when(rentDao.findOne(1)).thenReturn(rent);
	}
	
	@Test
	@Ignore
	public void create() {
		createRent();
	}
	
	@Test
	public void transformRent() {
		final RentPostDTO rentPostDTO = rentService.transform(rent);
		Assert.assertEquals("Rent date", rentPostDTO, INITDATE);
	}
	
	private void createRent() {
		rent.setInitDate(INITDATE);
	}

}
