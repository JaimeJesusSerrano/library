package com.at.library.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.at.library.model.Client;

@Repository
public interface ClientDao extends CrudRepository<Client, Integer> {

	@Query(value = "SELECT client FROM Client AS client WHERE client.name like %:name% OR client.surname like %:surname% OR client.dni like %:dni%")
	public Set<Client> search(
			@Param("name") String name,
			@Param("surname") String surname,
			@Param("dni") String dni
			);

}