package com.org.ozod.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.org.ozod.dto.UserDto;
import com.org.ozod.dto.UserLoginDto;
import com.org.ozod.dto.UserResponseDto;
import com.org.ozod.entity.User;
import com.org.ozod.exception.ErrorResponse;
import com.org.ozod.mapper.UserMapper;
import com.org.ozod.repository.IUserrepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserrepository repo;

	@Autowired
	private UserMapper usermapper;

	@Override
	public ResponseEntity<?> add(UserDto user) {
		User availableUser = repo.findByMobile(user.getMobile().trim());
		if (null == availableUser) {
			User entity = usermapper.userToUserDto(user);
			entity.setRecStat(true);
			return new ResponseEntity<>(usermapper.userDtoToUser(repo.save(entity)), HttpStatus.CREATED);
		}
		return new ResponseEntity<>(new ErrorResponse("Mobile is already used..", LocalDateTime.now()),
				HttpStatus.BAD_REQUEST);

	}

	@Override
	public ResponseEntity<?> findByMobileAndPassword(UserLoginDto user) {
		User availableUser = repo.findByMobileAndPassword(user.getMobile().trim(), user.getPassword().trim());
		if (null == availableUser) {
			return new ResponseEntity<>(new ErrorResponse("Invalid Login Detail.", LocalDateTime.now()),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(availableUser, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> findById(Long id) {
		return new ResponseEntity<>(usermapper.userToUserResponse(repo.findById(id).get()), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> update(UserResponseDto user) {
		User availableUser = repo.findById(user.getId()).get();
		availableUser.setAddress(user.getAddress());
		availableUser.setEmail(user.getEmail());
		availableUser.setName(user.getName());
		availableUser.setPassword(user.getPassword());
		availableUser.setStoreName(user.getStoreName());
		repo.save(availableUser);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
