package com.at.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.model.Client;

@Repository
public interface ClientDao extends CrudRepository<Client, Integer> {

}