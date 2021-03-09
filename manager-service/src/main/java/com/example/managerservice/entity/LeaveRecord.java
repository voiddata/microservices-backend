package com.example.managerservice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tbl_leave_record")
public class LeaveRecord {

	@Id
	@GeneratedValue(generator = "seq-gen-2", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq-gen-2", sequenceName = "leave_record_seq", initialValue = 1, allocationSize = 1)
	private int id;

	private String reason;
	private int days;

	private String userMail;
	private String requestStatus;

	@JsonIgnore
	@OneToMany(mappedBy = "leaveRecord", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private List<Dates> dates;

	public LeaveRecord() {
	}

	public LeaveRecord(int id, String reason, int days, String userMail, String requestStatus, List<Dates> dates) {
		super();
		this.id = id;
		this.reason = reason;
		this.days = days;
		this.userMail = userMail;
		this.requestStatus = requestStatus;
		this.dates = dates;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public List<Dates> getDates() {
		return dates;
	}

	public void setDates(List<Dates> dates) {
		this.dates = dates;
	}

	@Override
	public String toString() {
		return "LeaveRecord [id=" + id + ", reason=" + reason + ", days=" + days + ", dates=" + dates + "]";
	}

}
