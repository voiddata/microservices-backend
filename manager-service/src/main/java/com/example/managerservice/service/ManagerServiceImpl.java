package com.example.managerservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.managerservice.dto.LeaveRequestList;
import com.example.managerservice.entity.Dates;
import com.example.managerservice.entity.LeaveRecord;
import com.example.managerservice.entity.User;
import com.example.managerservice.repository.ManagerRepository;

@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {
	
	@Autowired
	private ManagerRepository managerRepository;
	
	@Override
	public List<LeaveRequestList> fetchRequestList(String email) {
		User managerUser = managerRepository.findByEmail(email);
		
		int mgrId = managerUser.getId();
		
		List<User> employeesList = managerRepository.fetchEmployeesList(mgrId);
		
		List<LeaveRequestList> result = new ArrayList<LeaveRequestList>();
		
		for (User user : employeesList) {
			List<LeaveRecord> records = managerRepository.fetchByMail(user.getEmail());
			for (LeaveRecord record : records) {
				if (record.getRequestStatus().equals("NOT_APPROVED")) {
					LeaveRequestList list = new LeaveRequestList();
					list.setDays(record.getDays());
					list.setEmployeeMail(record.getUserMail());
					list.setReason(record.getReason());
					list.setRecordId(record.getId());
					
					List<LocalDate> leaveDates = new ArrayList<LocalDate>();
					for (Dates dates : record.getDates()) {
						leaveDates.add(dates.getLeaveDate());
					}
					list.setDates(leaveDates);
					result.add(list);
				}
			}
		}
		
		return result;
	}


	@Override
	public void approve(int recordId) {
		LeaveRecord record = managerRepository.fetch(LeaveRecord.class, recordId);
		
		record.setRequestStatus("APPROVED");
		
		managerRepository.save(record);
	}

}
