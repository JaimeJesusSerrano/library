package com.at.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.model.Building;

@Repository
public interface BuildingDao extends CrudRepository<Building, Integer> {

}