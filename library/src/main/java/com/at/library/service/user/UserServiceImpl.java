package com.at.library.service.client;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.at.library.dao.ClientDao;
import com.at.library.dto.ClientDTO;
import com.at.library.enums.StatusEnum;
import com.at.library.model.Client;

@Service
public class ClientServiceImpl implements ClientService {
	
	private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);
	
	@Autowired
	private ClientDao clientDao;

	@Autowired
	private DozerBeanMapper dozer;
	
//	@Override
//	@Scheduled(cron = "0 0 0/4 1/1 * ? *")
//	public void penalize() {
//		log.debug("Time starts to penalize users");
//	}
//	
//	@Override
//	@Scheduled(cron = "0 0 0/4 1/1 * ? *")
//	public void forgive() {
//		log.debug("Time starts to forgive users");
//	}

	@Override
	@Transactional(readOnly = true)
	public Set<ClientDTO> findAll(Map<String,String> requestParams) {
		
		final Iterable<Client> clients;
		if (requestParams.isEmpty()) {
			clients = clientDao.findAll();
		}
		else {
			clients = search(requestParams);
		}
		
		final Iterator<Client> iteratorClients = clients.iterator();
		final Set<ClientDTO> clientsDTO = new HashSet<>();
		while (iteratorClients.hasNext()) {
			final Client client = iteratorClients.next();
			final ClientDTO clientDTO = transform(client);
			log.debug(String.format("clientDTO search : %s", clientDTO));
			clientsDTO.add(clientDTO);
		}
		return clientsDTO;
	}
	
	private Set<Client> search(Map<String,String> requestParams) {
		String name = requestParams.get("name");
		String surname = requestParams.get("surname");
		String dni = requestParams.get("dni");
		
		return clientDao.search(name, surname, dni);
	}

	@Override
	public ClientDTO transform(Client client) {
		return dozer.map(client, ClientDTO.class);
	}

	@Override
	public Client transform(ClientDTO clientDTO) {
		return dozer.map(clientDTO, Client.class);
	}

	@Override
	public ClientDTO findById(Integer id) {
		Client client = clientDao.findOne(id);
		return transform(client);
	}

	@Override
	public ClientDTO create(ClientDTO clientDTO) {
		final Client client = transform(clientDTO);
		client.setRegistrationDate(new Date());
		client.setStatus(StatusEnum.valueOf("ACTIVE"));
		return transform(clientDao.save(client));
	}

	@Override
	public ClientDTO getClientDTOById(Integer id) {
		return transform(getClientById(id));
	}

	@Override
	public Client getClientById(Integer id) {
		return clientDao.findOne(id);
	}

}
