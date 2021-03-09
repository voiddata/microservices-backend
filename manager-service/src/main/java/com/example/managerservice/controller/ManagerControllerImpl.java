package com.example.managerservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.managerservice.dto.LeaveRequestList;
import com.example.managerservice.service.ManagerService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@RestController
public class ManagerControllerImpl implements ManagerController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private ManagerService managerService;
	
	@Override
	@GetMapping("/getRequestList/{email}")
	@CircuitBreaker(name = "default")
	public @ResponseBody List<LeaveRequestList> getRequestList(@PathVariable("email") String email) {
		
		return managerService.fetchRequestList(email);
	}

	@Override
	@PostMapping("/approve")
	@CircuitBreaker(name = "default")
	public void approve(@RequestBody int recordId) {
		
		managerService.approve(recordId);
	}

	@Override
	@GetMapping("/hello")
	public String hello() {
		return "hello from " + environment.getProperty("local.server.port");
	}
	

}
