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
public class RentController extends Controller<RentPostDTO> {

	@Autowired
	private RentService rentService;

	@Override
	@RequestMapping(method = { RequestMethod.GET })
	public Set<RentPostDTO> getAll(@RequestParam(required = false) Map<String,String> requestParams) {
		return rentService.findAll();
	}

	@Override
	@RequestMapping(method = { RequestMethod.POST })
	public RentPostDTO create(@RequestBody RentPostDTO rentPostDTO) {
		return rentService.create(rentPostDTO);
	}

	@Override
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public RentPostDTO findById(Integer id) {
		return rentService.findById(id);
	}

	@Override
	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	public void update(Integer id, RentPostDTO rentPostDTO) {
//		rentService.update(rentPostDTO);
	}

	@Override
	@RequestMapping(value = "/{idLibro}", method = { RequestMethod.DELETE })
	public void delete(@PathVariable("idLibro") Integer idLibro) {
		rentService.delete(idLibro);
	}

}
