package com.org.ozod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.ozod.dto.UserDto;
import com.org.ozod.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService service;
	
	//Test Commit
	
	@PostMapping("/add")
	public UserDto add(@RequestBody UserDto user) {
		return service.add(user);
	}

}
