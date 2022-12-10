package com.org.ozod.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Integer id;

	private String name;

	private String email;

	private Integer mobile;

	private String password;

	@Column(name = "CREATED_TIMESTAMP")
	private LocalDateTime createdTime;

	@Column(name = "UPDATED_TIMESTAMP")
	private LocalDateTime updatedTime;

}
