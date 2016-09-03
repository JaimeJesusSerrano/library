package com.at.library.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.RentPostDTO;
import com.at.library.service.rent.RentService;

@RestController
@RequestMapping(value = "/rent")
public class RentController {

	@Autowired
	private RentService rentService;

	@RequestMapping(method = { RequestMethod.GET })
	public Set<RentPostDTO> getAll(@RequestParam(required = false) Map<String,String> requestParams) {
		return rentService.findAll();
	}

	@RequestMapping(method = { RequestMethod.POST })
	public RentPostDTO create(@RequestBody RentPostDTO rentPostDTO) throws Exception {
		return rentService.create(rentPostDTO);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public RentPostDTO findById(@PathVariable("id") Integer id) {
		return rentService.findById(id);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	public void update(Integer id, RentPostDTO rentPostDTO) {
//		rentService.update(rentPostDTO);
	}

	@RequestMapping(value = "/{idBook}", method = { RequestMethod.DELETE })
	public void delete(@PathVariable("idBook") Integer idBook) throws Exception {
		rentService.delete(idBook);
	}

}
