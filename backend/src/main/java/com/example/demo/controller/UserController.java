package com.example.demo.controller;

import org.springframework.hateoas.EntityModel;

import com.example.demo.dto.LoginStatus;
import com.example.demo.dto.Status;
import com.example.demo.entity.User;

public interface UserController {

	EntityModel<Status> register(User user);
	EntityModel<LoginStatus> login(User user);
	
}
