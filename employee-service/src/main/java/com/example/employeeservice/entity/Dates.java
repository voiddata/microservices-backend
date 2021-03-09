package com.example.employeeservice.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_dates")
public class Dates {

	@Id
	@GeneratedValue(generator = "seq-gen-3", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq-gen-3", sequenceName = "dates_seq", initialValue = 1, allocationSize = 1)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "record_id")
	private LeaveRecord leaveRecord;

	private LocalDate leaveDate;

	public Dates() {
	}

	public Dates(int id, LeaveRecord leaveRecord, LocalDate leaveDate) {
		super();
		this.id = id;
		this.leaveRecord = leaveRecord;
		this.leaveDate = leaveDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LeaveRecord getLeaveRecord() {
		return leaveRecord;
	}

	public void setLeaveRecord(LeaveRecord leaveRecord) {
		this.leaveRecord = leaveRecord;
	}

	public LocalDate getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(LocalDate leaveDate) {
		this.leaveDate = leaveDate;
	}

	@Override
	public String toString() {
		return "Dates [id=" + id + ", leaveRecord=" + leaveRecord + ", date=" + leaveDate + "]";
	}

}
