package com.example.managerservice.repository;

import java.util.List;

import com.example.managerservice.entity.LeaveRecord;
import com.example.managerservice.entity.User;

public interface ManagerRepository {

	User findByEmail(String email);
	List<LeaveRecord> fetchByMail(String email);
	Object save(Object obj);
	List<User> fetchEmployeesList(int mgrId);
	<E> E fetch(Class<E> clazz, Object pk);
	
}
