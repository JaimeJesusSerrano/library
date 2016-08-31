package com.at.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.model.Person;

@Repository
public interface PersonDao extends CrudRepository<Person, Integer> {

}