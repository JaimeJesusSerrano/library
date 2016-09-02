package com.at.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.at.exceptions.IdNotFoundException;
import com.at.library.dto.ApiErrorDTO;

@ControllerAdvice(basePackages = {"com.at.library.controller"})
public class ControllerFails {

//	@ResponseBody
//	@ExceptionHandler(UserNotFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public ApiErrorDTO error(Exception e) {
////		return new ApiError(404, e.getMessage());
//	}
	
//	@ResponseBody
//	@ExceptionHandler(UserNotFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public UserNotFound error(Exception e) {
//		return new UserNotFound(404, e.getMessage());
//	}
	
	@ResponseBody
	@ExceptionHandler(IdNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrorDTO error(Exception e) {
		return new ApiErrorDTO(404, e.getMessage());
	}
}
