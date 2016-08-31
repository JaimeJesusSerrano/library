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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.at.library.dao.UserDao;
import com.at.library.dto.UserDTO;
import com.at.library.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;

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
	public Set<UserDTO> findAll(Map<String,String> requestParams) {
		
		final Iterable<User> users;
		if (requestParams.isEmpty()) {
			users = userDao.findAll();
		}
		else {
			users = search(requestParams);
		}
		
		final Iterator<User> iteratorUsers = users.iterator();
		final Set<UserDTO> usersDTO = new HashSet<>();
		while (iteratorUsers.hasNext()) {
			final User user = iteratorUsers.next();
			final UserDTO userDTO = transform(user);
			usersDTO.add(userDTO);
		}
		return usersDTO;
	}
	
	private Set<User> search(Map<String,String> requestParams) {
		String name = requestParams.get("name");
		String dni = requestParams.get("dni");
		
		return userDao.search(name, dni);
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

}
