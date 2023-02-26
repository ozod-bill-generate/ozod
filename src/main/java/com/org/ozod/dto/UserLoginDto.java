package com.org.ozod.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserLoginDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Mobile is mandatory")
	private String mobile;

	@NotBlank(message = "Password is mandatory")
	private String password;

}
