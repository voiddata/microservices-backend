package com.example.employeeservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.employeeservice.dto.Leave;
import com.example.employeeservice.dto.LeaveHistory;
import com.example.employeeservice.entity.Dates;
import com.example.employeeservice.entity.LeaveRecord;
import com.example.employeeservice.exception.LeaveAppliedAlready;
import com.example.employeeservice.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@Override
	public void recordLeave(Leave leave) throws LeaveAppliedAlready {
		
		List<LeaveRecord> record = employeeRepository.fetchByMail(leave.getUserMail());
		
		for (LeaveRecord leaveRecord : record) {
			List<Dates> leaveTaken = leaveRecord.getDates();
			
			for (Dates dates : leaveTaken) {
				LocalDate dateTaken = dates.getLeaveDate();
				
				for (LocalDate requesteDate : leave.getDates()) {
					if (requesteDate.equals(dateTaken) && leaveRecord.getRequestStatus().equals("NOT_APPROVED")) {
						throw new LeaveAppliedAlready("Leave requested already in the specified date(s)! But not approved!");
					}
				}
			}
		}
		
		
		LeaveRecord validRecord = new LeaveRecord();
		validRecord.setDays(leave.getDays());
		validRecord.setReason(leave.getReason());
		validRecord.setUserMail(leave.getUserMail());
		validRecord.setRequestStatus("NOT_APPROVED");
		
		List<Dates> validDates = new ArrayList<Dates>();
		
		for (LocalDate requestedDate : leave.getDates()) {
			Dates dates = new Dates();
			dates.setLeaveDate(requestedDate);
			dates.setLeaveRecord(validRecord);
			
			validDates.add(dates);
		}
		
		validRecord.setDates(validDates);
		
		employeeRepository.save(validRecord);
		
	}


	@Override
	public List<LocalDate> fetchLeaveDates(String email) {
		
		List<LeaveRecord> records = employeeRepository.fetchLeaveDates(email);
		
		List<LocalDate> leaveDates = new ArrayList<LocalDate>();
		
		for (LeaveRecord record : records) {
			for (Dates dates : record.getDates()) {
				leaveDates.add(dates.getLeaveDate());
			}
		}
		
		return leaveDates;
	}


	@Override
	public List<LeaveHistory> fetchHistory(String email) {
		
		List<LeaveRecord> records = employeeRepository.fetchHistory(email);
		
		List<LeaveHistory> history = new ArrayList<LeaveHistory>();
		
		for (LeaveRecord record : records) {
			LeaveHistory hist = new LeaveHistory();
			hist.setDays(record.getDays());
			hist.setReason(record.getReason());
			hist.setStatus(record.getRequestStatus());
			
			List<LocalDate> historyDates = new ArrayList<LocalDate>();
			for (Dates date : record.getDates()) {
				historyDates.add(date.getLeaveDate());
			}
			
			hist.setDates(historyDates);
			history.add(hist);
		}
		
		return history;
	}

}
