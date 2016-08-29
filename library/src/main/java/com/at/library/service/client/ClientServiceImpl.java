package com.at.library.service.client;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.at.library.dao.ClientDao;
import com.at.library.dto.ClientDTO;
import com.at.library.enums.StatusEnum;
import com.at.library.model.Client;
import com.at.library.service.book.BookServiceImpl;

@Service
public class ClientServiceImpl implements ClientService {
	
	private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);
	
	@Autowired
	private ClientDao clientDao;

	@Autowired
	private DozerBeanMapper dozer;
	
	@Override
	@Scheduled(cron = "0 0 0/4 1/1 * ? *")
	public void penalize() {
		log.debug("Time starts to penalize users");
	}
	
	@Override
	@Scheduled(cron = "0 0 0/4 1/1 * ? *")
	public void forgive() {
		log.debug("Time starts to forgive users");
	}

	@Override
	@Transactional(readOnly = true)
	public Set<ClientDTO> findAll() {
		final Iterable<Client> findAll = clientDao.findAll();
		final Iterator<Client> iterator = findAll.iterator();
		final Set<ClientDTO> res = new HashSet<>();
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

}
