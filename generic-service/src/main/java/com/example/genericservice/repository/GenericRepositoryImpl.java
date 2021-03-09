package com.example.genericservice.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.genericservice.entity.User;

@Repository
public class GenericRepositoryImpl implements GenericRepository {

	@Autowired
	private EntityManager entityManager;
	
	
	@Override
	public User findByEmail(String email) {
		
		return entityManager
				.createQuery("select u from User u where u.email = :email", User.class)
				.setParameter("email", email)
				.getSingleResult();
	}

	@Override
	public User findById(int managerId) {
		
		return entityManager
				.createQuery("select u from User u where u.id = :id", User.class)
				.setParameter("id", managerId)
				.getSingleResult();
		
	}

	@Override
	public Object save(Object obj) {
		Object updatedObj = entityManager.merge(obj);
		return updatedObj;
	}

	@Override
	public User findByEmailAndPasswordAndRole(String email, String password, String role) {
		return entityManager
				.createQuery("select u from User u where u.email = :email and u.password = :password and u.role = :role", User.class)
				.setParameter("email", email)
				.setParameter("password", password)
				.setParameter("role", role)
				.getSingleResult();
	}

}
