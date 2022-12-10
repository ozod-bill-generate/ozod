package com.org.ozod.mapper;

import org.mapstruct.Mapper;

import com.org.ozod.dto.UserDto;
import com.org.ozod.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	User userToUserDto(UserDto dto);
	
	UserDto userDtoToUser(User user);
}
