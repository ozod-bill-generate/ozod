package com.org.ozod.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.ozod.dto.UserDto;
import com.org.ozod.mapper.UserMapper;
import com.org.ozod.repository.IUserrepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserrepository repo;

	@Autowired
	private UserMapper usermapper;

	@Override
	public UserDto add(UserDto user) {
		return usermapper.userDtoToUser(repo.save(usermapper.userToUserDto(user)));
	}

}
