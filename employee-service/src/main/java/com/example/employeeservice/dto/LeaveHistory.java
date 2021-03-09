package com.example.employeeservice.dto;

import java.time.LocalDate;
import java.util.List;

public class LeaveHistory {
	
	private String reason;
	private String status;
	private int days;
	
	private List<LocalDate> dates;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public List<LocalDate> getDates() {
		return dates;
	}

	public void setDates(List<LocalDate> dates) {
		this.dates = dates;
	}
	
	

}
