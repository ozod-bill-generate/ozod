package com.org.ozod.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.ozod.dto.UserDto;
import com.org.ozod.dto.UserLoginDto;
import com.org.ozod.service.IUserService;
@CrossOrigin(origins = {"${ui.url}"},maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService service;
	
	@PostMapping(value="/add",consumes = {"application/x-www-form-urlencoded","application/json"})
	public ResponseEntity<?> add(@RequestBody @Valid UserDto user) {
		return service.add(user);
	}
	
	@PostMapping(value="/login",consumes = {"application/x-www-form-urlencoded","application/json"})
	public ResponseEntity<?> add(@RequestBody @Valid UserLoginDto user) {
		return service.findByMobileAndPassword(user);
	}

}
