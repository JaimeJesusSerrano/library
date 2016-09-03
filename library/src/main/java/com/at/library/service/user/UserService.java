package com.at.library.service.user;

import java.util.Map;
import java.util.Set;

import com.at.library.dto.UserDTO;
import com.at.library.model.User;

public interface UserService {

	/**
	 * Get all users of the system
	 * 
	 * @return set of users
	 */
	public Set<UserDTO> findAll(Map<String,String> requestParams);
	
	/**
	 * Transform user to userDTO
	 * 
	 * @param user
	 * @return userDTO
	 */
	public UserDTO transform(User user);

	/**
	 * Transform userDTO to user
	 * 
	 * @param userDTO
	 * @return user
	 */
	public User transform(UserDTO userDTO);
	
	/**
	 * Find user by id
	 * 
	 * @param id
	 * @return userDTO
	 */
	public UserDTO findById(Integer id);
	
	/**
	 * Create a user
	 * 
	 * @param userDTO
	 * @return userDTO created
	 */
	public UserDTO create(UserDTO userDTO);
	
	/**
	 * Get userDTO by id
	 * 
	 * @param id
	 * @return userDTO
	 */
	public UserDTO getUserDTOById(Integer id);
	
	/**
	 * Get user by id
	 * 
	 * @param id
	 * @return user
	 */
	public User getUserById(Integer id);
	
	/**
	 * Change user status to disable
	 * 
	 * @param id
	 */
	public void delete(Integer id) throws Exception;
	
	/**
	 * Auto penalize users
	 * 
	 */
	public void penalize();
	
	/**
	 * Auto forgive users
	 * 
	 */
	public void forgive();
}
