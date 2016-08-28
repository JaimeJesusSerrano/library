package com.at.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.ClientDTO;
import com.at.library.service.client.ClientService;

@RestController
@RequestMapping(value = "/client")
public class ClientController extends Controller<ClientDTO> {
	
	@Autowired
	private ClientService clientService;

	@Override
	public List<ClientDTO> getAll() {
		clientService.findAll();
		return null;
	}

	@Override
	public ClientDTO create(ClientDTO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientDTO findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Integer id, ClientDTO t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
