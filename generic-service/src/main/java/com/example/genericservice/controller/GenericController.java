package com.example.genericservice.controller;

import org.springframework.hateoas.EntityModel;

import com.example.genericservice.dto.LoginStatus;
import com.example.genericservice.dto.Status;
import com.example.genericservice.entity.User;

public interface GenericController {
	
	EntityModel<Status> register(User user);
	EntityModel<LoginStatus> login(User user);
	String hello();
}
