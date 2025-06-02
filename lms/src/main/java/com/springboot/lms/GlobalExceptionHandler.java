package com.springboot.lms;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springboot.lms.exception.NotEnrolledInCourseException;
import com.springboot.lms.exception.ResourceNotFoundException;

import io.jsonwebtoken.security.SignatureException;

@ControllerAdvice
public class GlobalExceptionHandler {

	Logger logger = LoggerFactory.getLogger("GlobalExceptionHandler");
	
	//implementing loggerrrrrrrrrrrrrr

	/*
	 * Whenever a RuntimeException is thrown in Controller, this method gets called
	 */
	@ExceptionHandler(exception = RuntimeException.class)
	public ResponseEntity<?> handleRuntime(RuntimeException e) {
		logger.info(e.getMessage());
		Map<String, String> map = new HashMap<>();
		map.put("msg", e.getMessage());
		logger.error(e.getMessage(), e.getClass());
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(map);
	}

	/*
	 * Whenever a Custom ResourseNotFoundException is thrown in Controller,
	 * this method gets called
	 */
	@ExceptionHandler(exception = ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
		Map<String, String> map = new HashMap<>();
		map.put("msg", e.getMessage());
		logger.error(e.getMessage(), e.getClass());
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(map);
	}

	/*
	 * Whenever any Unforeseen Exception is thrown in Controller,
	 * this method gets called
	 */
	@ExceptionHandler(exception = Exception.class)
	public ResponseEntity<?> handleException(Exception e) {
		Map<String, String> map = new HashMap<>();
		map.put("msg", e.getMessage());
		logger.error(e.getMessage(), e.getClass());
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(map);
	}

	/*
	 * Whenever a NotEnrolledInCourseException is thrown in Controller/service,
	 * this method gets called
	 */
	@ExceptionHandler(exception = NotEnrolledInCourseException.class)
	public ResponseEntity<?> handleNotEnrolledInCourseException(Exception e) {
		Map<String, String> map = new HashMap<>();
		map.put("msg", e.getMessage());
		logger.error(e.getMessage(), e.getClass());
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(map);
	}

	/*
	 * Whenever a token is invalid ,
	 * this method gets called
	 */
	@ExceptionHandler(exception = SignatureException.class)
	public ResponseEntity<?> handleSignatureException(Exception e) {
		Map<String, String> map = new HashMap<>();
		map.put("msg", e.getMessage());
		logger.error(e.getMessage(), e.getClass());
		return ResponseEntity
				.status(HttpStatus.UNAUTHORIZED)
				.body(map);
	}

}