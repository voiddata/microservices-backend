package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exception.UserAlreadyExistsException;

public interface UserService {
	void register(User user) throws UserAlreadyExistsException;

	User login(User user) throws UserAlreadyExistsException;
}
