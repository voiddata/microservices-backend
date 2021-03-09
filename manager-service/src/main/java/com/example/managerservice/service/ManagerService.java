package com.example.managerservice.service;

import java.util.List;

import com.example.managerservice.dto.LeaveRequestList;

public interface ManagerService {
	
	List<LeaveRequestList> fetchRequestList(String email);

	void approve(int recordId);

}
