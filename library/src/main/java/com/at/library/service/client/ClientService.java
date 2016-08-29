package com.at.library.service.client;

import java.util.Set;

import com.at.library.dto.ClientDTO;
import com.at.library.model.Client;

public interface ClientService {

	/**
	 * Get all clients of the system
	 * 
	 * @return set of clients
	 */
	public Set<ClientDTO> findAll();
	
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
	
	/**
	 * Find client by id
	 * 
	 * @param id
	 * @return clientDTO
	 */
	public ClientDTO findById(Integer id);
	
	/**
	 * Create a client
	 * 
	 * @param clientDTO
	 * @return clientDTO created
	 */
	public ClientDTO create(ClientDTO clientDTO);
	
	/**
	 * Auto penalize clients
	 * 
	 */
	public void penalize();
	
	/**
	 * Auto forgive clients
	 * 
	 */
	public void forgive();
}
