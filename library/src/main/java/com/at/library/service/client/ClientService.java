package com.at.library.service.client;

import java.util.List;

import com.at.library.dto.ClientDTO;
import com.at.library.model.Client;

public interface ClientService {

	/**
	 * Get all clients of the system
	 * 
	 * @return book list
	 */
	public List<ClientDTO> findAll();
	
	/**
	 * Transform client to clientDTO
	 * 
	 * @param client
	 * @return clientDTO
	 */
	public ClientDTO transform(Client client);

	/**
	 * Transform clientDTO to client
	 * 
	 * @param clientDTO
	 * @return client
	 */
	public Client transform(ClientDTO clientDTO);
}
