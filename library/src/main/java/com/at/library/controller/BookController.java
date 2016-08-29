package com.at.library.controller;

import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.BookDTO;
import com.at.library.service.book.BookService;

@RestController
@RequestMapping(value = "/book")
public class BookController extends Controller<BookDTO> {
	
	private static final Logger log = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookService bookservice;

	@Override
	@RequestMapping(method = { RequestMethod.GET })
	public Set<BookDTO> getAll(@RequestParam(required = false) Map<String,String> requestParams) {
		log.debug(String.format("The aditional parameters is %s", requestParams.toString()));
		log.debug(String.format("Getting all books"));
		return bookservice.findAll();
	}

	@Override
	@RequestMapping(method = { RequestMethod.POST })
	public BookDTO create(@RequestBody BookDTO bookDTO) {
		log.debug(String.format("Create the book %s", bookDTO));
		return bookservice.create(bookDTO);
	}

	@Override
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public BookDTO findById(@PathVariable("id") Integer id) {
		log.debug(String.format("Getting a book with id %s", id));
		return bookservice.findById(id);
	}

	@Override
	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	public void update(@PathVariable("id") Integer id, @RequestBody BookDTO bookDTO) {
		log.debug(String.format("Update a book with id %s", id));
		bookservice.update(bookDTO);
	}

	@Override
	@RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
	public void delete(@PathVariable("id") Integer id) {
		log.debug(String.format("Delete book with id %s", id));
		bookservice.delete(id);
	}

}
