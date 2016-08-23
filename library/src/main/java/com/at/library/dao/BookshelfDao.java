package com.at.library.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.at.library.model.Bookshelf;

@Repository
public interface BookshelfDao extends CrudRepository<Bookshelf, Integer> {

}