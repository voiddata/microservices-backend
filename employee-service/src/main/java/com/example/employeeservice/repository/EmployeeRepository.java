package com.example.employeeservice.repository;

import java.util.List;


import com.example.employeeservice.entity.LeaveRecord;

public interface EmployeeRepository {

	List<LeaveRecord> fetchByMail(String email);
	Object save(Object obj);
	List<LeaveRecord> fetchLeaveDates(String email);
	List<LeaveRecord> fetchHistory(String email);

}
