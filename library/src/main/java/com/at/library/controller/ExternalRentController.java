package com.at.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.service.externRent.ExternRentService;

@RestController
@RequestMapping(value = "/externalrent")
public class ExternalRentController {

	@Autowired
	private ExternRentService externRentService;
	
	@RequestMapping(method = { RequestMethod.GET })
	public void getAll() throws Exception {
		externRentService.migrationOfBooksInExternRents();
	}
	
}
