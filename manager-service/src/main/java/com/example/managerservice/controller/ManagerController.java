package com.example.managerservice.controller;

import java.util.List;

import com.example.managerservice.dto.LeaveRequestList;

public interface ManagerController {

	List<LeaveRequestList> getRequestList(String email);
	void approve(int recordId);
	String hello();
}
