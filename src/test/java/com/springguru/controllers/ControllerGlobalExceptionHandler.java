package com.springguru.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerGlobalExceptionHandler {

	
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NumberFormatException.class)
	public ModelAndView handleNumberFormatException(Exception exception)
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error400");
		modelAndView.addObject("message", exception.getMessage());
		
		return modelAndView;
	}
}
