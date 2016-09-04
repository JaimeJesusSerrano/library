package com.at.library.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.at.library.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer> {

	@Query(value = "SELECT user FROM User AS user WHERE (user.name like %:name% OR :name is NULL) AND (user.dni like %:dni% OR :dni is NULL)")
	public Page<User> findAll(
			Pageable pageable,
			@Param("name") String name,
			@Param("dni") String dni
			);

}