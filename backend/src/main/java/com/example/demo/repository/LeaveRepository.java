package com.example.demo.repository;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.LeaveRecord;
import com.example.demo.entity.User;

@Repository
public class LeaveRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	
	public List<LeaveRecord> fetchByMail(String email) {
		return entityManager.createQuery("select l from LeaveRecord l where l.userMail = :userMail", LeaveRecord.class)
				.setParameter("userMail", email)
				.getResultList();
	}
	
	public Object save(Object obj) {
		Object updatedObj = entityManager.merge(obj);
		return updatedObj;
	}

	public List<LeaveRecord> fetchLeaveDates(String email) {
		
		return entityManager.createQuery("select l from LeaveRecord l where l.userMail = :userMail and l.requestStatus = :status", LeaveRecord.class)
				.setParameter("userMail", email)
				.setParameter("status", "APPROVED")
				.getResultList();
		
		
	}

	public List<LeaveRecord> fetchHistory(String email) {

		return entityManager.createQuery("select l from LeaveRecord l where l.userMail = :userMail", LeaveRecord.class)
				.setParameter("userMail", email)
				.getResultList();
		
	}

	public List<User> fetchEmployeesList(int mgrId) {
	
		return entityManager.createQuery("select u from User u where u.managerId = :id", User.class)
				.setParameter("id", mgrId)
				.getResultList();
	}

	public <E> E fetch(Class<E> clazz, Object pk) {
		E e = entityManager.find(clazz, pk); 
		return e;
	}
}
