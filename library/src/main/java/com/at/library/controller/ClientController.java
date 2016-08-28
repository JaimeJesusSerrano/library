package com.at.library.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.ClientDTO;
import com.at.library.service.client.ClientService;

@RestController
@RequestMapping(value = "/client")
public class ClientController extends Controller<ClientDTO> {
	
	@Autowired
	private ClientService clientService;

	@Override
	@RequestMapping(method = { RequestMethod.GET })
	public Set<ClientDTO> getAll() {
		return clientService.findAll();
	}

	@Override
	@RequestMapping(method = { RequestMethod.POST })
	public ClientDTO create(@RequestBody ClientDTO clientDTO) {
		return clientService.create(clientDTO);
	}

	@Override
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public ClientDTO findById(Integer id) {
		return clientService.findById(id);
	}

	@Override
	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	public void update(Integer id, ClientDTO t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
