package com.at.library.service.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.at.library.dao.ClientDao;
import com.at.library.dto.ClientDTO;
import com.at.library.model.Client;

public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientDao clientDao;

	@Autowired
	private DozerBeanMapper dozer;

	@Override
	@Transactional(readOnly = true)
	public List<ClientDTO> findAll() {
		final Iterable<Client> findAll = clientDao.findAll();
		final Iterator<Client> iterator = findAll.iterator();
		final List<ClientDTO> res = new ArrayList<>();
		while (iterator.hasNext()) {
			final Client c = iterator.next();
			final ClientDTO cDTO = transform(c);
			res.add(cDTO);
		}
		
		return res;
	}

	@Override
	public ClientDTO transform(Client client) {
		return dozer.map(client, ClientDTO.class);
	}

	@Override
	public Client transform(ClientDTO clientDTO) {
		return dozer.map(clientDTO, Client.class);
	}

}
