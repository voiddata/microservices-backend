package com.example.employeeservice.service;

import java.time.LocalDate;
import java.util.List;

import com.example.employeeservice.dto.Leave;
import com.example.employeeservice.dto.LeaveHistory;
import com.example.employeeservice.exception.LeaveAppliedAlready;

public interface EmployeeService {
	
	void recordLeave(Leave leave) throws LeaveAppliedAlready;

	List<LocalDate> fetchLeaveDates(String email);

	List<LeaveHistory> fetchHistory(String email);

}
