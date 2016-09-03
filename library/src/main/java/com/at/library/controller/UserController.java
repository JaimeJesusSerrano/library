package com.at.library.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.at.library.dto.UserDTO;
import com.at.library.service.user.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(method = { RequestMethod.GET })
	public Set<UserDTO> getAll(@RequestParam(required = false) Map<String,String> requestParams) {
		return userService.findAll(requestParams);
	}

	@RequestMapping(method = { RequestMethod.POST })
	public UserDTO create(@RequestBody UserDTO userDTO) {
		return userService.create(userDTO);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public UserDTO findById(@PathVariable("id") Integer id) {
		return userService.findById(id);
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	public void update(Integer id, UserDTO t) {
		// TODO Auto-generated method stub
		
	}

	@RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
	public void delete(@PathVariable("id") Integer id) throws Exception {
		userService.delete(id);
	}

}
