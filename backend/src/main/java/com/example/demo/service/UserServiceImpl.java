package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.Leave;
import com.example.demo.dto.LeaveHistory;
import com.example.demo.dto.LeaveRequestList;
import com.example.demo.entity.Dates;
import com.example.demo.entity.LeaveRecord;
import com.example.demo.entity.User;
import com.example.demo.exception.LeaveAppliedAlready;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.repository.LeaveRepository;
import com.example.demo.repository.UserRepository;


@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LeaveRepository leaveRepository;
	
	
	@Override
	public void register(User user) throws UserAlreadyExistsException {
		
		if (user.getRole().equals("emp")) {
			if (userRepository.findByEmail(user.getEmail()) != null) {
				throw new UserAlreadyExistsException("User Already Registered!");
			} else if(!userRepository.findById(user.getManagerId()).isPresent()) {
				throw new UserAlreadyExistsException("Invalid Manager ID!");
			} else {
				userRepository.save(user);
			}
		} else if (user.getRole().equals("man")) {
			if (userRepository.findByEmail(user.getEmail()) != null) {
				throw new UserAlreadyExistsException("User Already Registered!");
			} else {
				userRepository.save(user);
			}
		}
		
	}


	@Override
	public User login(User user) throws UserAlreadyExistsException {
		
		User fetchedUser = userRepository.findByEmailAndPasswordAndRole(user.getEmail(), user.getPassword(), user.getRole());
		
		if (fetchedUser == null) {
			throw new UserAlreadyExistsException("Invalid User!");
		}
		
		return fetchedUser;
	}


	@Override
	public void recordLeave(Leave leave) throws LeaveAppliedAlready {
		
		List<LeaveRecord> record = leaveRepository.fetchByMail(leave.getUserMail());
		
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
		
		leaveRepository.save(validRecord);
		
	}


	@Override
	public List<LocalDate> fetchLeaveDates(String email) {
		
		List<LeaveRecord> records = leaveRepository.fetchLeaveDates(email);
		
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
		
		List<LeaveRecord> records = leaveRepository.fetchHistory(email);
		
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


	@Override
	public List<LeaveRequestList> fetchRequestList(String email) {
		User managerUser = userRepository.findByEmail(email);
		
		int mgrId = managerUser.getId();
		
		List<User> employeesList = leaveRepository.fetchEmployeesList(mgrId);
		
		List<LeaveRequestList> result = new ArrayList<LeaveRequestList>();
		
		for (User user : employeesList) {
			List<LeaveRecord> records = leaveRepository.fetchByMail(user.getEmail());
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
		LeaveRecord record = leaveRepository.fetch(LeaveRecord.class, recordId);
		
		record.setRequestStatus("APPROVED");
		
		leaveRepository.save(record);
	}

}
