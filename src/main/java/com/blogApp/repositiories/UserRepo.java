package com.blogApp.repositiories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApp.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
