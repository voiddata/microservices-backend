package com.example.genericservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.genericservice.entity.User;
import com.example.genericservice.exception.InvalidManagerException;
import com.example.genericservice.exception.UserAlreadyExistsException;
import com.example.genericservice.repository.GenericRepository;

@Service
@Transactional
public class GenericServiceImpl implements GenericService {

	@Autowired
	private GenericRepository genericRepository;
	
	@Override
	public void register(User user) throws UserAlreadyExistsException, InvalidManagerException {
		
		if (user.getRole().equals("emp")) {
			if (genericRepository.findByEmail(user.getEmail()).size() > 0) {
				throw new UserAlreadyExistsException("User Already Registered!");
			} else if(genericRepository.findById(user.getManagerId()).size() == 0) {
				throw new InvalidManagerException("Invalid Manager ID!");
			} else {
				genericRepository.save(user);
			}
		} else if (user.getRole().equals("man")) {
			if (genericRepository.findByEmail(user.getEmail()).size() > 0) {
				throw new UserAlreadyExistsException("User Already Registered!");
			} else {
				genericRepository.save(user);
			}
		}
		
	}


	@Override
	public User login(User user) throws UserAlreadyExistsException {
		
		List<User> fetchedUser = genericRepository.findByEmailAndPasswordAndRole(user.getEmail(), user.getPassword(), user.getRole());
		
		if (fetchedUser.size() == 0) {
			throw new UserAlreadyExistsException("Invalid User!");
		}
		
		return fetchedUser.get(0);
	}
}
