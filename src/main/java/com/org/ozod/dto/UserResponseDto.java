package com.org.ozod.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id; 
	
	private String name;

	private String email;

	private String password;
	
	private String storeName;
	
	private String address;

}
