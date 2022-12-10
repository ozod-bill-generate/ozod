package com.org.ozod.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private String email;

	private Integer mobile;

	private String password;

	private LocalDateTime createdTime;

	private LocalDateTime updatedTime;

}
