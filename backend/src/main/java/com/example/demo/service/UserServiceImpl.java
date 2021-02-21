package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.repository.UserRepository;


@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public void register(User user) throws UserAlreadyExistsException {
		
		if (userRepository.findByEmail(user.getEmail()) != null) {
			throw new UserAlreadyExistsException("User Already Registered!");
		} else {
			userRepository.save(user);
		}
	}


	@Override
	public User login(User user) throws UserAlreadyExistsException {
		
		User fetchedUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
		
		if (fetchedUser == null) {
			throw new UserAlreadyExistsException("Invalid User!");
		}
		
		return fetchedUser;
	}

}
