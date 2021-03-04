package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.hateoas.EntityModel;

import com.example.demo.dto.Leave;
import com.example.demo.dto.LeaveHistory;
import com.example.demo.dto.LeaveRequestList;
import com.example.demo.dto.LoginStatus;
import com.example.demo.dto.Status;
import com.example.demo.entity.User;

public interface UserController {

	EntityModel<Status> register(User user);
	EntityModel<LoginStatus> login(User user);
	EntityModel<Status> requestLeave(Leave leave);
	List<LocalDate> getLeaveDates(String email);
	List<LeaveHistory> getHistory(String email);
	List<LeaveRequestList> getRequestList(String email);
	void approve(int recordId);
	
}
