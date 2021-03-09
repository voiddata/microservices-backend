package com.example.employeeservice.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.employeeservice.entity.LeaveRecord;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	
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
	public List<LeaveRecord> fetchLeaveDates(String email) {
		
		return entityManager.createQuery("select l from LeaveRecord l where l.userMail = :userMail and l.requestStatus = :status", LeaveRecord.class)
				.setParameter("userMail", email)
				.setParameter("status", "APPROVED")
				.getResultList();
		
		
	}

	@Override
	public List<LeaveRecord> fetchHistory(String email) {

		return entityManager.createQuery("select l from LeaveRecord l where l.userMail = :userMail", LeaveRecord.class)
				.setParameter("userMail", email)
				.getResultList();
		
	}

}
