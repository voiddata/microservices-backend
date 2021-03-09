package com.example.employeeservice.dto;

import java.time.LocalDate;
import java.util.List;

public class Leave {

	private int days;
	private String userMail;
	private String reason;
	private List<LocalDate> dates;
	
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public List<LocalDate> getDates() {
		return dates;
	}
	public void setDates(List<LocalDate> dates) {
		this.dates = dates;
	}
	@Override
	public String toString() {
		return "Leave [days=" + days + ", employeeMail=" + userMail + ", reason=" + reason + ", dates=" + dates
				+ "]";
	}
	
	
	
	
}
