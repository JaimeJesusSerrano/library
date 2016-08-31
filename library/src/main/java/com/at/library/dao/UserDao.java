package com.at.library.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.at.library.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {

	@Query(value = "SELECT user FROM User AS user WHERE user.name like %:name% OR user.dni like %:dni%")
	public Set<User> search(
			@Param("name") String name,
			@Param("dni") String dni
			);

}