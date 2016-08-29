package com.at.library.service.rent;

import java.util.Set;

import com.at.library.dto.RentPostDTO;
import com.at.library.model.Rent;

public interface RentService {

	/**
	 * Get all rents of the system
	 * 
	 * @return set of rents
	 */
	public Set<RentPostDTO> findAll();
	
	/**
	 * Transform rent to rentPostDTO
	 * 
	 * @param rent
	 * @return rentPostDTO
	 */
	public RentPostDTO transform(Rent rent);
	
	/**
	 * Transform rentPostDTO to rent
	 * 
	 * @param rentPostDTO
	 * @return rent
	 */
	public Rent transform(RentPostDTO rentPostDTO);
	
	/**
	 * Find rent by id
	 * 
	 * @param id
	 * @return rentPostDTO
	 */
	public RentPostDTO findById(Integer id);
	
	/**
	 * Create a rent
	 * 
	 * @param rentPostDTO
	 * @return rentPostDTO created
	 */
	public RentPostDTO create(RentPostDTO rentPostDTO);
	
	/**
	 * Update a rent
	 * 
	 * @param RentPostDTO
	 */
	public void update (RentPostDTO rentPostDTO);
	
}
