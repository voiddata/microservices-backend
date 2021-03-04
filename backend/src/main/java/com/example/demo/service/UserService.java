package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.dto.Leave;
import com.example.demo.dto.LeaveHistory;
import com.example.demo.dto.LeaveRequestList;
import com.example.demo.entity.User;
import com.example.demo.exception.LeaveAppliedAlready;
import com.example.demo.exception.UserAlreadyExistsException;

public interface UserService {
	
	void register(User user) throws UserAlreadyExistsException;

	User login(User user) throws UserAlreadyExistsException;

	void recordLeave(Leave leave) throws LeaveAppliedAlready;

	List<LocalDate> fetchLeaveDates(String email);

	List<LeaveHistory> fetchHistory(String email);

	List<LeaveRequestList> fetchRequestList(String email);

	void approve(int recordId);
}
