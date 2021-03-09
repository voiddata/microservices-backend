package com.example.employeeservice.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeservice.dto.Status.StatusType;
import com.example.employeeservice.exception.LeaveAppliedAlready;
import com.example.employeeservice.dto.Leave;
import com.example.employeeservice.dto.LeaveHistory;
import com.example.employeeservice.dto.Status;
import com.example.employeeservice.service.EmployeeService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@CrossOrigin
public class EmployeeControllerImpl implements EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private Environment environment;

	@Override
	@PostMapping("/requestLeave")
	@CircuitBreaker(name = "default", fallbackMethod = "hello")
	public @ResponseBody EntityModel<Status> requestLeave(@RequestBody Leave leave) {

		Status status = new Status();

		try {
			employeeService.recordLeave(leave);

			status.setMessage("Leave Request Recorded!");
			status.setStatus(StatusType.SUCCESS);

		} catch (LeaveAppliedAlready e) {
			status.setMessage(e.getMessage());
			status.setStatus(StatusType.FAILED);
		}

		EntityModel<Status> resourceEntityModel = EntityModel.of(status);
		return resourceEntityModel;
	}

	@Override
	@GetMapping("/getLeaveDates/{email}")
	@CircuitBreaker(name = "default", fallbackMethod = "hello")
	public @ResponseBody List<LocalDate> getLeaveDates(@PathVariable("email") String email) {

		return employeeService.fetchLeaveDates(email);
	}

	@Override
	@GetMapping("/getHistory/{email}")
	@CircuitBreaker(name = "default", fallbackMethod = "hello")
	public @ResponseBody List<LeaveHistory> getHistory(@PathVariable("email") String email) {

		return employeeService.fetchHistory(email);
	}

	@Override
	@GetMapping("/hello")
	public String hello() {
		return "hello from " + environment.getProperty("local.server.port");
	}

}
