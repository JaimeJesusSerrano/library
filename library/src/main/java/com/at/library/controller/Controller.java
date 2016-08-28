package com.at.library.controller;

import java.util.Set;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public abstract class Controller<T> {

	@RequestMapping(method = { RequestMethod.GET })
	public abstract Set<T> getAll();

	@RequestMapping(method = { RequestMethod.POST })
	public abstract T create(@RequestBody T t);

	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public abstract T findById(@PathVariable("id") Integer id);

	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	public abstract void update(@PathVariable("id") Integer id, @RequestBody T t);

	@RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
	public abstract void delete(@PathVariable("id") Integer id);
	
}
