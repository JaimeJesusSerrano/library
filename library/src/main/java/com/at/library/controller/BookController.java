package com.at.library.controller;

import java.util.List;

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
public class BookController {
	
	private static final Logger log = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookService bookservice;

	@RequestMapping(method = { RequestMethod.GET })
	public List<BookDTO> getAll() {
		log.debug(String.format("Buscando todos los libros del sistema"));
		return bookservice.findAll();
	}
	
//	Crear
	@RequestMapping(method = { RequestMethod.POST })
	public BookDTO create(@RequestBody BookDTO bookDTO) {
		log.debug(String.format("Vamos a crear el libro siguiente", bookDTO));
		return bookservice.create(bookDTO);
	}
	
//	Recuperar
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public BookDTO read(@PathVariable("id") Integer id) {
		log.debug(String.format("Vamos a recuperar el libro con el siguiente id", id));
		return bookservice.findOne(id);
	}
	
//	Modificar
	@RequestMapping(method = { RequestMethod.PUT })
	public void update(@RequestBody BookDTO bookDTO) {
		log.debug(String.format("Vamos a recuperar el libro con el siguiente id", id));
		return bookservice.get(id);
	}
	
//	Borrar
	

}
