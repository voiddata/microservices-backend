package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoggedUser;
import com.example.demo.dto.LoginStatus;
import com.example.demo.dto.Status;
import com.example.demo.dto.Status.StatusType;
import com.example.demo.entity.User;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin
public class UserControllerImpl implements UserController {

	
	@Autowired
	private UserService userService;
	
	@Override
	@PostMapping("/register")
	public @ResponseBody EntityModel<Status> register(@RequestBody User user) {
		
		Status status = new Status();
		
		try {
			userService.register(user);
			
			status.setMessage("Registered Successfully!");
			status.setStatus(StatusType.SUCCESS);
			
		} catch(UserAlreadyExistsException e) {
			status.setMessage(e.getMessage());
			status.setStatus(StatusType.FAILED);
		}
		
		EntityModel<Status> resourceEntityModel = EntityModel.of(status);
		return resourceEntityModel;
	}

	@Override
	@PostMapping("/login")
	public @ResponseBody EntityModel<LoginStatus> login(@RequestBody User user) {
		
		LoginStatus loginStatus = new LoginStatus();
		
		try {
			User fetchedUser = userService.login(user);
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
	
	
}
