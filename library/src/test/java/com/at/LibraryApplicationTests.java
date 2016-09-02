package com.at;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.at.library.dto.BookPostDTO;

public class LibraryApplicationTests {

	@Test
	public void contextLoads() {
		final String url = "http//:localhost:8080/book";
		final BookPostDTO bookDTO = new RestTemplate().getForObject(url, BookPostDTO.class);
	}

}
