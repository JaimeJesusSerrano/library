package com.at.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.model.Booking;

@Repository
public interface BookingDao extends CrudRepository<Booking, Integer> {

}

