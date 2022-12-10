package com.org.ozod.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.ozod.entity.User;

public interface IUserrepository extends JpaRepository<User, Integer> {

}
