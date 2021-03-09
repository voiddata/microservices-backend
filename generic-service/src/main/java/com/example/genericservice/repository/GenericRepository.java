package com.example.genericservice.repository;

import com.example.genericservice.entity.User;

public interface GenericRepository {

	User findByEmail(String email);

	User findById(int managerId);

	Object save(Object obj);
	
	User findByEmailAndPasswordAndRole(String email, String password, String role);

}
