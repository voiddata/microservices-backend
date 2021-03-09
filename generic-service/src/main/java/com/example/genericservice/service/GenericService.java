package com.example.genericservice.service;

import com.example.genericservice.exception.InvalidManagerException;
import com.example.genericservice.exception.UserAlreadyExistsException;
import com.example.genericservice.entity.User;

public interface GenericService {
	
	void register(User user) throws UserAlreadyExistsException, InvalidManagerException;

	User login(User user) throws UserAlreadyExistsException;

}
