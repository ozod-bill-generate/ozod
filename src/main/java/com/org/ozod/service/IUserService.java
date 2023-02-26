package com.org.ozod.service;

import org.springframework.http.ResponseEntity;

import com.org.ozod.dto.UserDto;
import com.org.ozod.dto.UserLoginDto;

public interface IUserService {

	ResponseEntity<?> add(UserDto user);

	ResponseEntity<?> findByMobileAndPassword(UserLoginDto user);

}
