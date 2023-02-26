package com.org.ozod.service;

import org.springframework.http.ResponseEntity;

import com.org.ozod.dto.UserDto;
import com.org.ozod.dto.UserLoginDto;
import com.org.ozod.dto.UserResponseDto;

public interface IUserService {

	ResponseEntity<?> add(UserDto user);

	ResponseEntity<?> findByMobileAndPassword(UserLoginDto user);

	ResponseEntity<?> findById(Long id);

	ResponseEntity<?> update(UserResponseDto user);

}
