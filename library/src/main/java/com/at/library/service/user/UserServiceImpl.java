package com.at.library.service.user;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.at.exceptions.UserNotFoundException;
import com.at.library.dao.UserDao;
import com.at.library.dto.UserDTO;
import com.at.library.enums.UserStatusEnum;
import com.at.library.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private DozerBeanMapper dozer;
	
	@Override
	@Scheduled(cron = "0 0 0/4 1/1 * ?")
	public void penalize() {
		log.debug("Time starts to penalize users");
	}
	
	@Override
	@Scheduled(cron = "0 0 0/4 1/1 * ?")
	public void forgive() {
		log.debug("Time starts to forgive users");
	}

	@Override
	@Transactional(readOnly = true)
	public Set<UserDTO> findAll(Map<String,String> requestParams) {
		
		Integer page = 1;
		if (requestParams.containsKey("page")) {
			page = Integer.parseInt(requestParams.get("page"));
			if (page < 1) page = 1;
		}
		Integer size = 2;
		if (requestParams.containsKey("size")) {
			size = Integer.parseInt(requestParams.get("size"));
			if (size < 1 || size > 10) size = 2;
		}		
		
		Pageable pageable = new PageRequest(page - 1, size);
		String name = requestParams.get("name");
		String dni = requestParams.get("dni");
		
		Page<User> users = userDao.findAll(pageable, name, dni);
		
		final Iterator<User> iteratorUsers = users.iterator();
		final Set<UserDTO> usersDTO = new HashSet<>();
		while (iteratorUsers.hasNext()) {
			final User user = iteratorUsers.next();
			final UserDTO userDTO = transform(user);
			usersDTO.add(userDTO);
		}
		return usersDTO;
	}

	@Override
	public UserDTO transform(User user) {
		return dozer.map(user, UserDTO.class);
	}

	@Override
	public User transform(UserDTO userDTO) {
		return dozer.map(userDTO, User.class);
	}

	@Override
	public UserDTO findById(Integer id) {
		User user = userDao.findOne(id);
		return transform(user);
	}

	@Override
	public UserDTO create(UserDTO userDTO) {
		final User user = transform(userDTO);
		user.setRegistrationDate(new Date());
		user.setStatus(UserStatusEnum.ENABLE);
		return transform(userDao.save(user));
	}

	@Override
	public UserDTO getUserDTOById(Integer id) {
		return transform(getUserById(id));
	}

	@Override
	public User getUserById(Integer id) {
		return userDao.findOne(id);
	}

	@Override
	public void delete(Integer id) throws Exception {
		final User user = userDao.findOne(id);
		if (user == null) throw new UserNotFoundException();
		user.setStatus(UserStatusEnum.DISABLE);
		userDao.save(user);
	}

}
