package com.example.managerservice.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.managerservice.entity.LeaveRecord;
import com.example.managerservice.entity.User;

@Repository
public class ManagerRepositoryImpl implements ManagerRepository {

	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<User> fetchEmployeesList(int mgrId) {
		
		return entityManager.createQuery("select u from User u where u.managerId = :id", User.class)
				.setParameter("id", mgrId)
				.getResultList();
	}

	@Override
	public <E> E fetch(Class<E> clazz, Object pk) {
		E e = entityManager.find(clazz, pk); 
		return e;
	}
	
	@Override
	public List<LeaveRecord> fetchByMail(String email) {
		return entityManager.createQuery("select l from LeaveRecord l where l.userMail = :userMail", LeaveRecord.class)
				.setParameter("userMail", email)
				.getResultList();
	}
	
	@Override
	public Object save(Object obj) {
		Object updatedObj = entityManager.merge(obj);
		return updatedObj;
	}

	@Override
	public User findByEmail(String email) {
		return entityManager.createQuery("select u from User u where u.email = :email", User.class)
				.setParameter("email", email)
				.getSingleResult();
	}

}
