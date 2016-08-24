package com.at;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.at.library.dto.BookDTO;

public class LibraryApplicationTests {

	@Test
	public void contextLoads() {
		final String url = "http//:localhost:8080/book";
		final BookDTO bookDTO = new RestTemplate().getForObject(url, BookDTO.class);
	}

}
