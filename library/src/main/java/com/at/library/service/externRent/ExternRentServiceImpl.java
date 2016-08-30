package com.at.library.service.externRent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.at.library.dto.BookDTO;
import com.at.library.dto.ExternRentDTO;
import com.at.library.enums.StatusEnum;
import com.at.library.service.book.BookService;

@Service
public class ExternRentServiceImpl implements ExternRentService {
	
	@Autowired
	private BookService bookService;
	
	private static final Logger log = LoggerFactory.getLogger(ExternRentServiceImpl.class);
	
	public void migrationOfBooksInExternRents() {
		
		RestTemplate restTemplate = new RestTemplate();
		ExternRentDTO[] externRentsDTO;
		
		Integer page = 0;
		Integer size = 20;
		do {
			final String url = "http://192.168.11.57:8080/rent?page="+page+"&size="+size;
			externRentsDTO = restTemplate.getForObject(url, ExternRentDTO[].class);
			page++;
			
			for (ExternRentDTO ExternRentDTO: externRentsDTO) {
				BookDTO bookDTO = ExternRentDTO.getBookDTO();
				bookDTO.setStatus(StatusEnum.valueOf("ACTIVE"));
				bookService.create(bookDTO);
				log.debug(String.format("BookDTO %s", bookDTO.toString()));
			}
			
		} while (externRentsDTO.length > 0);
	}
	
}
