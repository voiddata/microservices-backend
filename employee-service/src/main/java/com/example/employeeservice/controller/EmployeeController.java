package com.example.employeeservice.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.hateoas.EntityModel;

import com.example.employeeservice.dto.Leave;
import com.example.employeeservice.dto.LeaveHistory;
import com.example.employeeservice.dto.Status;

public interface EmployeeController {
	
	EntityModel<Status> requestLeave(Leave leave);
	List<LocalDate> getLeaveDates(String email);
	List<LeaveHistory> getHistory(String email);
	String hello();

}
