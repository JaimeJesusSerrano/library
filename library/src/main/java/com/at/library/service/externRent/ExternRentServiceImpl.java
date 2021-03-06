package com.at.library.service.externRent;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.at.library.dto.BookPostDTO;
import com.at.library.dto.ExternRentDTO;
import com.at.library.enums.BookStatusEnum;
import com.at.library.service.book.BookService;

@Service
public class ExternRentServiceImpl implements ExternRentService {
	
	@Autowired
	private BookService bookService;
	
	private static final Logger log = LoggerFactory.getLogger(ExternRentServiceImpl.class);
	
	public void migrationOfBooksInExternRents() throws Exception {
		
		RestTemplate restTemplate = new RestTemplate();
		ExternRentDTO[] externRentsDTO;
		
		Integer page = 0;
		Integer size = 20;
		do {
			final String url = "http://192.168.11.57:8080/rent?page="+page+"&size="+size;
			externRentsDTO = restTemplate.getForObject(url, ExternRentDTO[].class);
			page++;
			
			List<Integer> bookIds = new ArrayList<>();
			Integer bookId;
			for (ExternRentDTO ExternRentDTO: externRentsDTO) {
				BookPostDTO bookPostDTO = ExternRentDTO.getBookDTO();
//				bookId = bookPostDTO.getId();
				bookId = 4;
				
				if (bookId != null && bookIds.get(bookId) == null) {
					bookIds.add(bookId, bookId);
					
					bookService.create(bookPostDTO);
					log.debug(String.format("BookDTO %s", bookPostDTO.toString()));
				}
			}
			
		} while (externRentsDTO.length > 0);
	}
	
}
