package com.at.library.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackages = {"com.at.library.controller"})
public class ControllerFails {

//	@ResponseBody
//	@ExceptionHandler(UserNotFound.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public ApiErrorDTO error(Exception e) {
//		(404, e.getMessage());
//	}
}
