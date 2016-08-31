package com.at.library.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.UserDTO;
import com.at.library.service.user.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController extends Controller<UserDTO> {
	
	@Autowired
	private UserService userService;

	@Override
	@RequestMapping(method = { RequestMethod.GET })
	public Set<UserDTO> getAll(@RequestParam(required = false) Map<String,String> requestParams) {
		return userService.findAll(requestParams);
	}

	@Override
	@RequestMapping(method = { RequestMethod.POST })
	public UserDTO create(@RequestBody UserDTO userDTO) {
		return userService.create(userDTO);
	}

	@Override
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public UserDTO findById(Integer id) {
		return userService.findById(id);
	}

	@Override
	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	public void update(Integer id, UserDTO t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
