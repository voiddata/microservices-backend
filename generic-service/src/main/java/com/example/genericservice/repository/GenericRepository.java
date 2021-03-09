package com.example.genericservice.repository;

import java.util.List;

import com.example.genericservice.entity.User;

public interface GenericRepository {

	List<User> findByEmail(String email);

	List<User> findById(int managerId);

	Object save(Object obj);
	
	List<User> findByEmailAndPasswordAndRole(String email, String password, String role);

}
