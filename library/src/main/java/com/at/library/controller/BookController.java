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

import com.at.library.dto.BookGetDTO;
import com.at.library.dto.BookPostDTO;
import com.at.library.dto.HistoryRentedDTO;
import com.at.library.service.book.BookService;

@RestController
@RequestMapping(value = "/book")
public class BookController {
	
	private static final Logger log = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookService bookservice;

	@RequestMapping(method = { RequestMethod.GET })
	public Set<BookGetDTO> getAll(@RequestParam(required = false) Map<String,String> requestParams) {
		return bookservice.findAll(requestParams);
	}

	@RequestMapping(method = { RequestMethod.POST })
	public BookGetDTO create(@RequestBody BookPostDTO bookPostDTO) {
		return bookservice.create(bookPostDTO);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public BookGetDTO findById(@PathVariable("id") Integer id) {
		return bookservice.findById(id);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	public void update(@PathVariable("id") Integer bookId, @RequestBody BookPostDTO bookPostDTO) {
		bookservice.update(bookId, bookPostDTO);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
	public void delete(@PathVariable("id") Integer id) {
		bookservice.delete(id);
	}
	
	@RequestMapping(value = "/{id}/rent", method = { RequestMethod.GET })
	public Set<HistoryRentedDTO> getHistoryRented(@PathVariable("id") Integer bookId) {
		return bookservice.getHistoryRented(bookId);
	}

}
