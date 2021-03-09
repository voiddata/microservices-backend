package com.example.genericservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.genericservice.dto.LoggedUser;
import com.example.genericservice.dto.Status.StatusType;
import com.example.genericservice.exception.InvalidManagerException;
import com.example.genericservice.exception.UserAlreadyExistsException;
import com.example.genericservice.service.GenericService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import com.example.genericservice.dto.LoginStatus;
import com.example.genericservice.dto.Status;
import com.example.genericservice.entity.User;


@RestController
@CrossOrigin
public class GenericControllerImpl implements GenericController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private GenericService genericService;
	
	@Override
	@PostMapping("/register")
	@CircuitBreaker(name = "default", fallbackMethod = "hello")
	public @ResponseBody EntityModel<Status> register(@RequestBody User user) {
		
		Status status = new Status();
		
		try {
			genericService.register(user);
			
			status.setMessage("Registered Successfully!");
			status.setStatus(StatusType.SUCCESS);
			
		} catch(UserAlreadyExistsException | InvalidManagerException e) {
			status.setMessage(e.getMessage());
			status.setStatus(StatusType.FAILED);
		}
		
		EntityModel<Status> resourceEntityModel = EntityModel.of(status);
		return resourceEntityModel;
	}

	@Override
	@PostMapping("/login")
	@CircuitBreaker(name = "default", fallbackMethod = "hello")
	public @ResponseBody EntityModel<LoginStatus> login(@RequestBody User user) {
		
		LoginStatus loginStatus = new LoginStatus();
		
		try {
			User fetchedUser = genericService.login(user);
			LoggedUser loggedUser = new LoggedUser();
			loggedUser.setFirstName(fetchedUser.getFirstName());
			loggedUser.setLastName(fetchedUser.getLastName());
			loggedUser.setEmail(fetchedUser.getEmail());
			loggedUser.setRole(fetchedUser.getRole());
			
			loginStatus.setLoggedUser(loggedUser);
			loginStatus.setMessage("Logged in Successfully!");
			loginStatus.setStatus(StatusType.SUCCESS);
			
		} catch(UserAlreadyExistsException e) {
			loginStatus.setLoggedUser(null);
			loginStatus.setMessage(e.getMessage());
			loginStatus.setStatus(StatusType.FAILED);
		}
		
		EntityModel<LoginStatus> resourceEntityModel = EntityModel.of(loginStatus);
		return resourceEntityModel;
	}

	@Override
	@GetMapping("/hello")
	public String hello() {
		return "no response from the service \n hello from " + environment.getProperty("local.server.port");
	}

}
